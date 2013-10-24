package eu.kratochvil.perfmon;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class MonitorImplTest {


	@Test
	public void testSimple() throws Exception {
		MonitorImpl monitor = new MonitorImpl();
		StopWatch watch = monitor.getStopWatch();
		watch.start();
		Thread.sleep(10);
		monitor.save(watch);

		assertEquals(1, monitor.callsTotal);
		assertNotSame(0, monitor.totalDuration);
		assertEquals(monitor.totalDuration, monitor.longestDuration);
		assertEquals(monitor.totalDuration, monitor.shortestDuration);
	}
}
