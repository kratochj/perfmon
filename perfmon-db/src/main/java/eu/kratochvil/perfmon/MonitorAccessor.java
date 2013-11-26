package eu.kratochvil.perfmon;

import java.util.Map;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class MonitorAccessor {

	public static Map<MonitorId, Monitor> getMonitors(MonitorFactoryImpl monitorFactory) {
		return monitorFactory.monitorMap;
	}

}
