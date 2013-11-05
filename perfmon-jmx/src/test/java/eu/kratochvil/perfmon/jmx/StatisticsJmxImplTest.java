package eu.kratochvil.perfmon.jmx;

import javax.inject.Inject;
import javax.management.MBeanInfo;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import java.util.List;

import eu.kratochvil.perfmon.MonitorCategory;
import eu.kratochvil.perfmon.MonitorFactory;
import eu.kratochvil.perfmon.StopWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-perfmon-jmx-context.xml"})
public class StatisticsJmxImplTest {
	public static final Logger logger = LogManager.getLogger(StatisticsJmxImplTest.class);

	@Autowired
	private MBeanServerConnection clientConnector = null;

	@Autowired
	@Qualifier("statisticsJmxProxy")
	private StatisticsJmx statisticsJmx;

	@Inject
	MonitorFactory monitorFactory;

	@Before
	public void setUp() throws Exception {
		monitorFactory.reset();
		// Prepare data
		eu.kratochvil.perfmon.Monitor monitor1 = monitorFactory.getInstance("TEST1", MonitorCategory.STOP_WATCH);
		stopwatch(monitor1, 10);
		stopwatch(monitor1, 20);
		eu.kratochvil.perfmon.Monitor monitor2 = monitorFactory.getInstance("TEST2", MonitorCategory.STOP_WATCH);
		stopwatch(monitor2, 100);
		stopwatch(monitor2, 200);
		stopwatch(monitor2, 300);
	}

	@Test
	public void testMBeanServerConnection() throws Exception {
		MBeanInfo beanInfo = clientConnector.getMBeanInfo(new ObjectName("eu.kratochvil.perfmon.jmx:name=Statistics"));
		assertNotNull(beanInfo);
		logger.debug("Bean info: {}", beanInfo);
	}

	@Test
	public void testStatisticsJmxProxy() {
		List<Monitor> monitors = statisticsJmx.statistics();
		assertNotNull(monitors);
		logger.info("statistics={}", monitors);
	}

	private void stopwatch(eu.kratochvil.perfmon.Monitor monitor1, int sleep) throws InterruptedException {
		StopWatch sw = monitor1.getStopWatch().start();
		Thread.sleep(sleep);
		monitor1.save(sw);
	}
}
