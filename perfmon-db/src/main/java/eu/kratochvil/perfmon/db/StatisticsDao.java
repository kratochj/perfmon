package eu.kratochvil.perfmon.db;

import java.sql.SQLException;
import java.util.Map;

import eu.kratochvil.perfmon.Monitor;
import eu.kratochvil.perfmon.MonitorAccessor;
import eu.kratochvil.perfmon.MonitorFactory;
import eu.kratochvil.perfmon.MonitorFactoryImpl;
import eu.kratochvil.perfmon.MonitorId;
import eu.kratochvil.perfmon.db.utils.CustomBeanHandler;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class StatisticsDao {

	public static final Logger logger = LogManager.getLogger(StatisticsDao.class);

	QueryRunner runner;

	public void saveStatistics(MonitorFactory monitorFactory) throws SQLException {
		assert monitorFactory != null;
		assert monitorFactory instanceof MonitorFactoryImpl;

		for (Map.Entry<MonitorId, Monitor> entry : MonitorAccessor.getMonitors((MonitorFactoryImpl) monitorFactory)
		                                                          .entrySet()) {
			Statistics dbStatistics = loadMonitorStatistics(entry.getKey());
			if (dbStatistics != null) {
				logger.debug("Updating monitor: {}", dbStatistics);
				// TODO Update
			} else {
				logger.debug("Creating new db record for monitor: {}", entry.getKey());
				// TODO Insert
			}
		}

	}

	public Statistics loadMonitorStatistics(MonitorId monitorId) throws SQLException {
		assert monitorId != null;
		DateTime now = DateTime.now();

		return runner
				.query("SELECT * FROM STATISTICS " +
						"WHERE name=? and category=? " +
						"and dt_Year=? and dt_Month=? " +
						"and dt_Day=? and dt_Hour=?", new CustomBeanHandler<Statistics>(Statistics.class),
						monitorId.getName(), monitorId.getCategory().toString(),
						now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), now.getHourOfDay());
	}




	public void setRunner(QueryRunner runner) {
		this.runner = runner;
	}
}
