package eu.kratochvil.perfmon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class StopWatchTest {

	public static final Logger logger = LogManager.getLogger(StopWatchTest.class);

	@Test
	public void testStopwatch() throws Exception {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Thread.sleep(10);
		long elapsed = stopWatch.stop();
		logger.debug("Duration (ns): {}", elapsed);
		assertNotSame(0, elapsed);
	}
}
