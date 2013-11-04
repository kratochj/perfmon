package eu.kratochvil.perfmon;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class MonitorImpl implements Monitor {

	long callsTotal = 0;

	long totalDuration = 0;

	long longestDuration = 0;

	long shortestDuration = Long.MAX_VALUE;

	String monitorName;

	MonitorCategory monitorCategory;

	String key = null;

	public MonitorImpl(String monitorName, MonitorCategory monitorCategory) {
		this.monitorName = monitorName;
		this.monitorCategory = monitorCategory;
	}

	@Override
	public StopWatch getStopWatch(String key) {
		return new StopWatch();
	}

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
		return new MonitorStats(callsTotal, totalDuration, totalDuration / callsTotal, longestDuration, shortestDuration);
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("name", monitorName).append("category", monitorCategory).append("callsTotal", callsTotal)
		       .append("totalDuration", totalDuration).append("longestDuration", longestDuration)
		       .append("shortestDuration", shortestDuration).append("averageCall", totalDuration / callsTotal);
		return builder.toString();
	}
}
