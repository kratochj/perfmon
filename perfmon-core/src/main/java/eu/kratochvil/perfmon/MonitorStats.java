package eu.kratochvil.perfmon;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class MonitorStats implements Serializable {

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

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("totalCalls", totalCalls).append("totalTime", totalTime).append("avgTime", avgTime)
		       .append("longest", longest).append("shortest", shortest);
		return builder.toString();
	}
}
