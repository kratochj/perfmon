package eu.kratochvil.perfmon;

import java.util.UUID;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class MonitorId {

	String name;

	MonitorCategory category;

	String key;


	public MonitorId(String name, MonitorCategory category) {
		this.name = name;
		this.category = category;
		this.key = UUID.randomUUID().toString();
	}

	public String getName() {
		return name;
	}

	public MonitorCategory getCategory() {
		return category;
	}

	public String getKey() {
		return key;
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("name", name).append("category", category).append("key", key);
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

		if (!key.equals(monitorId.key)) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return key.hashCode();
	}
}
