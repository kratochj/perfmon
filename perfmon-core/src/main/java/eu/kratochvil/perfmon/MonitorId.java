package eu.kratochvil.perfmon;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class MonitorId {

	String name;

	MonitorCategory category;

	public MonitorId(String name, MonitorCategory category) {
		this.name = name;
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public MonitorCategory getCategory() {
		return category;
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("monitorName", name).append("category", category);
		return builder.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		MonitorId monitorId = (MonitorId) o;

		if (!name.equals(monitorId.name)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 1;
		hash = hash * 17 + name.hashCode();
		hash = hash * 31 + category.hashCode();
		return hash;
	}
}
