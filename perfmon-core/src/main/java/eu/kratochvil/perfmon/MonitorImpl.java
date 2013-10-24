package eu.kratochvil.perfmon;

import org.apache.commons.lang.NotImplementedException;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class MonitorImpl implements Monitor {

	long callsTotal = 0;

	long totalDuration = 0;

	long longestDuration = 0;

	long shortestDuration = Long.MAX_VALUE;

	String key = null;

	@Override
	public StopWatch getStopWatch() {
		return new StopWatch();

	}

	@Override
	public void save(StopWatch stopWatch) {
		long elapsed = stopWatch.stop();


		callsTotal++;
		totalDuration += elapsed;

		if (longestDuration < elapsed) {
			longestDuration = elapsed;
		}
		if (shortestDuration > elapsed) {
			shortestDuration = elapsed;
		}
	}

	@Override
	public MonitorStats getStatistics() {
		throw new NotImplementedException();
	}

	@Override
	public String getKey() {
		return key;
	}
}
