package eu.kratochvil.perfmon.db;

import eu.kratochvil.perfmon.*;
import eu.kratochvil.util.FreezeTime;
import eu.kratochvil.util.Snippet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testApplicationContext.xml")
public class StatisticsDaoTest {

	public static final String INSERT_QUERY = "insert into STATISTICS (name, category, dt_Year, dt_Month, dt_Day, dt_Hour, " + "last_Updated, total_Calls, total_Time, longest, shorttest) values (?,?,?,?,?,?,?,?,?,?,?)";

	public static final Logger logger = LogManager.getLogger(StatisticsDaoTest.class);

	@Autowired
    JdbcTemplate jdbcTemplate;

	StatisticsDao statisticsDao;

	@Before
	public void setUp() throws Exception {
		statisticsDao = new StatisticsDao();
		statisticsDao.setJdbcTemplate(jdbcTemplate);

        jdbcTemplate.update("delete from STATISTICS");
	}

	@Test
	public void testLoadMonitorStatsEmpty() throws Exception {
        jdbcTemplate.update("delete from STATISTICS");

		MonitorId monitorId = new MonitorId("test", MonitorCategory.ASYNC);
		assertNull(statisticsDao.loadMonitorStatistics(monitorId, DateTime.now()));
	}

	@Test
	public void testLoadMonitorStats() throws Exception {
        jdbcTemplate.update(INSERT_QUERY, "test", "ASYNC", 2008, 9, 4, 10, new Timestamp(new Date()
				.getTime()), 5, 2342523, 23455, 12);

		FreezeTime.timeAt("2008-09-04T10:15:56").thawAfter(new Snippet() {{
			MonitorId monitorId = new MonitorId("test", MonitorCategory.ASYNC);
			Statistics stats = statisticsDao.loadMonitorStatistics(monitorId, DateTime.now());
			assertNotNull(stats);

			assertEquals(5, stats.getTotalCalls());
			assertEquals(2342523, stats.getTotalTime());
			assertEquals(23455, stats.getLongest());
			assertEquals(12, stats.getShorttest());
		}});
	}

	@Test
	public void testInsertMonitorStats() throws Exception {
		FreezeTime.timeAt("2008-09-04T10:15:56").thawAfter(new Snippet() {{
			MonitorId monitorId = new MonitorId("test", MonitorCategory.ASYNC);
			Monitor monitor = new MonitorImpl("test", MonitorCategory.ASYNC) {
				@Override
				public MonitorStats getStatistics() {
					return new MonitorStats(5, 15, 3, 5, 1);
				}
			};
			statisticsDao.createMonitorStatistics(monitorId, monitor, DateTime.now());

			Statistics stats = statisticsDao.loadMonitorStatistics(monitorId, DateTime.now());

			assertNotNull(stats);
			logger.debug("Loaded: {}", stats);
			assertEquals(5, stats.getTotalCalls());
			assertEquals(15, stats.getTotalTime());
			assertEquals(5, stats.getLongest());
			assertEquals(1, stats.getShorttest());
		}});
	}

	@Test
	public void testUpdate() throws Exception {
        jdbcTemplate.update(INSERT_QUERY, "test", "ASYNC", 2008, 9, 4, 10, new Timestamp(new Date()
				.getTime()), 5, 2342523, 23455, 12);

		FreezeTime.timeAt("2008-09-04T10:15:56").thawAfter(new Snippet() {{
			MonitorId monitorId = new MonitorId("test", MonitorCategory.ASYNC);
			Monitor monitor = new MonitorImpl("test", MonitorCategory.ASYNC) {
				@Override
				public MonitorStats getStatistics() {
					return new MonitorStats(5, 15, 3, 500000, 1);
				}
			};

			statisticsDao.updateMonitorStatistics(monitorId, monitor, DateTime.now());

			Statistics stats = statisticsDao.loadMonitorStatistics(monitorId, DateTime.now());

			assertNotNull(stats);
			logger.debug("Loaded: {}", stats);
			assertEquals(10, stats.getTotalCalls());
			assertEquals(2342523 + 15, stats.getTotalTime());
			assertEquals(500000, stats.getLongest());
			assertEquals(1, stats.getShorttest());
		}});
	}

	@Test
	public void testUpdateDontAffectedLongestShortest() throws Exception {
        jdbcTemplate.update(INSERT_QUERY, "test", "ASYNC", 2008, 9, 4, 10, new Timestamp(new Date()
				.getTime()), 5, 2342523, 23455, 3);

		FreezeTime.timeAt("2008-09-04T10:15:56").thawAfter(new Snippet() {{
			MonitorId monitorId = new MonitorId("test", MonitorCategory.ASYNC);
			Monitor monitor = new MonitorImpl("test", MonitorCategory.ASYNC) {
				@Override
				public MonitorStats getStatistics() {
					return new MonitorStats(5, 15, 3, 50, 10);
				}
			};

			statisticsDao.updateMonitorStatistics(monitorId, monitor, DateTime.now());

			Statistics stats = statisticsDao.loadMonitorStatistics(monitorId, DateTime.now());

			assertNotNull(stats);
			logger.debug("Loaded: {}", stats);
			assertEquals(10, stats.getTotalCalls());
			assertEquals(2342523 + 15, stats.getTotalTime());
			assertEquals(23455, stats.getLongest());
			assertEquals(3, stats.getShorttest());
		}});
	}

	@Test
	public void testSaveStatistics() throws Exception {
        jdbcTemplate.update(INSERT_QUERY, "test", "ASYNC", 2008, 9, 4, 10, new Timestamp(new Date()
				.getTime()), 5, 2342523, 23455, 3);

		final Monitor monitor1 = new MonitorImpl("test1", MonitorCategory.ASYNC) {
			@Override
			public MonitorStats getStatistics() {
				return new MonitorStats(5, 15, 3, 50, 10);
			}
		};
		final Monitor monitor2 = new MonitorImpl("test2", MonitorCategory.ASYNC) {
			@Override
			public MonitorStats getStatistics() {
				return new MonitorStats(5, 15, 3, 50, 10);
			}
		};


		final MonitorFactory monitorFactory = new MonitorFactoryImpl() {
			@Override
			public Monitor getInstance(String monitorName, MonitorCategory monitorCategory) {
				if ("test1".equals(monitorName)) {
					getMonitorMap().put(new MonitorId(monitorName, monitorCategory), monitor1);
					return monitor1;
				} else {
					getMonitorMap().put(new MonitorId(monitorName, monitorCategory), monitor2);
					return monitor2;
				}
			}
		};

		monitorFactory.getInstance("test1", MonitorCategory.ASYNC);
		monitorFactory.getInstance("test2", MonitorCategory.ASYNC);

		FreezeTime.timeAt("2008-09-04T10:15:56").thawAfter(new Snippet() {{
			statisticsDao.saveStatistics(monitorFactory);

			Statistics stats1 = statisticsDao
					.loadMonitorStatistics(new MonitorId("test1", MonitorCategory.ASYNC), DateTime.now());
			assertNotNull(stats1);
		}});
	}
}
