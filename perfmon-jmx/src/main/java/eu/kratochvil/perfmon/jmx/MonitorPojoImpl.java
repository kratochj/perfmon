package eu.kratochvil.perfmon.jmx;

import eu.kratochvil.perfmon.MonitorStats;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class MonitorPojoImpl implements Monitor {

	String name;

	MonitorStats statistics;

	public MonitorPojoImpl(String name, MonitorStats statistics) {
		this.name = name;
		this.statistics = statistics;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public MonitorStats getStatistics() {
		return statistics;
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("name", name).append("statistics", statistics);
		return builder.toString();
	}
}
