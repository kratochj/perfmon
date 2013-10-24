package eu.kratochvil.perfmon;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class MonitorStats {

	long totalCalls;

	long totalTime;

	long avgTime;

	long longest;

	long shortest;

	public MonitorStats(long totalCalls, long totalTime, long avgTime, long longest, long shortest) {
		this.totalCalls = totalCalls;
		this.totalTime = totalTime;
		this.avgTime = avgTime;
		this.longest = longest;
		this.shortest = shortest;
	}

	public long getTotalCalls() {
		return totalCalls;
	}

	public long getTotalTime() {
		return totalTime;
	}

	public long getAvgTime() {
		return avgTime;
	}

	public long getLongest() {
		return longest;
	}

	public long getShortest() {
		return shortest;
	}
}
