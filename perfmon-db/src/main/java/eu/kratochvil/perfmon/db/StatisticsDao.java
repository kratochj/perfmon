package eu.kratochvil.perfmon.db;

import eu.kratochvil.perfmon.Monitor;
import eu.kratochvil.perfmon.MonitorFactory;
import eu.kratochvil.perfmon.MonitorFactoryImpl;
import eu.kratochvil.perfmon.MonitorId;
import eu.kratochvil.perfmon.db.utils.StatisticsMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class StatisticsDao {

    public static final Logger logger = LogManager.getLogger(StatisticsDao.class);

    public static final String INSERT_QUERY = "insert into STATISTICS (name, category, dt_Year, dt_Month, dt_Day, dt_Hour, " +
            "last_Updated, total_Calls, total_Time, longest, shorttest) values (?,?,?,?,?,?,?,?,?,?,?)";

    public static final String UPDATE_QUERY = "update statistics set total_calls=total_calls+?, total_time=total_time+?, " +
            "shorttest=?, longest=?, last_Updated=? " +
            "where name=? and category=? and dt_Year=? and dt_Month=? and dt_Day=? and dt_Hour=?";

    JdbcTemplate jdbcTemplate;

    public void saveStatistics(MonitorFactory monitorFactory) throws SQLException {
        assert monitorFactory != null;
        assert monitorFactory instanceof MonitorFactoryImpl;
        DateTime now = DateTime.now();

        for (Map.Entry<MonitorId, Monitor> entry : ((MonitorFactoryImpl) monitorFactory).getMonitorMap().entrySet()) {
            Statistics dbStatistics = loadMonitorStatistics(entry.getKey(), now);
            if (dbStatistics != null) {
                logger.debug("Updating monitor: {}", dbStatistics);
                updateMonitorStatistics(entry.getKey(), entry.getValue(), now);
                entry.getValue().reset();
            } else {
                logger.debug("Creating new db record for monitor: {}", entry.getKey());
                createMonitorStatistics(entry.getKey(), entry.getValue(), now);
                entry.getValue().reset();
            }
        }

    }

    public Statistics loadMonitorStatistics(MonitorId monitorId, DateTime now) throws SQLException {
        assert monitorId != null;
        List<Statistics> result = jdbcTemplate
                .query("SELECT * FROM STATISTICS " +
                        "WHERE name=? and category=? " +
                        "and dt_Year=? and dt_Month=? " +
                        "and dt_Day=? and dt_Hour=?", new Object[]{monitorId.getName(), monitorId.getCategory().toString(),
                        now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), now.getHourOfDay()},
                        new StatisticsMapper());
        if (result != null && !result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    public void createMonitorStatistics(MonitorId monitorId, Monitor monitor, DateTime now) throws SQLException {
        jdbcTemplate.update(INSERT_QUERY, monitorId.getName(), monitorId.getCategory().toString(),
                now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), now.getHourOfDay(), new Timestamp(now.getMillis()),
                monitor.getStatistics().getTotalCalls(), monitor.getStatistics().getTotalTime(),
                monitor.getStatistics().getLongest(), monitor.getStatistics().getShortest());
    }

    public void updateMonitorStatistics(MonitorId monitorId, Monitor monitor, DateTime now) throws SQLException {
        Statistics dbStats = loadMonitorStatistics(monitorId, now);
        assert dbStats != null;

        jdbcTemplate.update(UPDATE_QUERY, monitor.getStatistics().getTotalCalls(), monitor.getStatistics().getTotalTime(),
                getShortest(monitor, dbStats), getLongest(monitor, dbStats),
                new Timestamp(now.getMillis()), monitorId.getName(), monitorId.getCategory().toString(),
                now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), now.getHourOfDay());
    }

    private Long getLongest(Monitor monitor, Statistics dbStats) {
        if (monitor.getStatistics().getTotalCalls() == 0) {
            return null;
        }

        return dbStats.getLongest() < monitor.getStatistics().getLongest() ? monitor.getStatistics().getLongest() : dbStats.getLongest();
    }

    private Long getShortest(Monitor monitor, Statistics dbStats) {
        if (monitor.getStatistics().getTotalCalls() == 0) {
            return null;
        }

        return dbStats.getShorttest() > monitor.getStatistics().getShortest() ? monitor.getStatistics().getShortest() : dbStats.getShorttest();
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
