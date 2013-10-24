package eu.kratochvil.perfmon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class StopWatch {

	public static final Logger logger = LogManager.getLogger(StopWatch.class);

	long started;

	boolean running;

	public StopWatch start() {
		if (running) {
			throw new IllegalStateException("Stopwatch is allready running");
		}
		running = true;
		logger.trace("Time - started");
		started = System.nanoTime();
		return this;
	}

	public long stop() {
		long elapsed = System.nanoTime() - started;
		if (!running) {
			throw new IllegalStateException("Stopwatch was not started");
		}
		logger.trace("Time - stopped");
		logger.trace("Time elapsed: {} ns", elapsed);
		running = false;
		return elapsed;
	}




}
