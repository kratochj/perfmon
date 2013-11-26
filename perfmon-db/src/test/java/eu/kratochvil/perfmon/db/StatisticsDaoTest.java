package eu.kratochvil.perfmon.db;

import java.sql.Timestamp;
import java.util.Date;

import eu.kratochvil.perfmon.MonitorCategory;
import eu.kratochvil.perfmon.MonitorId;
import eu.kratochvil.util.FreezeTime;
import eu.kratochvil.util.Snippet;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testApplicationContext.xml")
public class StatisticsDaoTest {

	public static final String INSERT_QUERY = "insert into STATISTICS (name, category, dt_Year, dt_Month, dt_Day, dt_Hour, " +
			"last_Updated, total_Calls, total_Time, longest, shorttest) values (?,?,?,?,?,?,?,?,?,?,?)";


	@Autowired
	QueryRunner runner;

	StatisticsDao statisticsDao;

	@Before
	public void setUp() throws Exception {
		statisticsDao = new StatisticsDao();
		statisticsDao.setRunner(runner);
	}

	@Test
	public void testLoadMonitorStatsEmpty() throws Exception {
		runner.update("delete from STATISTICS");

		MonitorId monitorId = new MonitorId("test", MonitorCategory.ASYNC);
		assertNull(statisticsDao.loadMonitorStatistics(monitorId));
	}

	@Test
	public void testLoadMonitorStats() throws Exception {
		runner.update(INSERT_QUERY, "test", "ASYNC", 2008, 9, 4, 10, new Timestamp(new Date()
				.getTime()), 5, 2342523, 23455, 12);

		FreezeTime.timeAt("2008-09-04T10:15:56").thawAfter(new Snippet() {{
			MonitorId monitorId = new MonitorId("test", MonitorCategory.ASYNC);
			Statistics stats = statisticsDao.loadMonitorStatistics(monitorId);
			assertNotNull(stats);

			assertEquals(5, stats.getTotalCalls());
			assertEquals(2342523, stats.getTotalTime());
			assertEquals(23455, stats.getLongest());
			assertEquals(12, stats.getShorttest());
		}});
	}
}
