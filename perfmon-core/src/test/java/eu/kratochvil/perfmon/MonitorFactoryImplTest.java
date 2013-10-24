package eu.kratochvil.perfmon;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class MonitorFactoryImplTest {


	@Test
	public void testGetInstanceOnEmptyMap() throws Exception {
		MonitorFactory monitorFactory = new MonitorFactoryImpl();
		Monitor instance = monitorFactory.getInstance("TEST", MonitorCategory.STOP_WATCH);
		assertNotNull(instance);
		StopWatch watch = instance.getStopWatch().start();
		Thread.sleep(10);
		instance.save(watch);


	}
}
