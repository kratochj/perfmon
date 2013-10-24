package eu.kratochvil.perfmon;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class MonitorFactoryImpl implements MonitorFactory {

	Map<MonitorId, Monitor> monitorMap = new HashMap<MonitorId, Monitor>();

	@Override
	public Monitor getInstance(String monitorName, MonitorCategory monitorCategory) {
		MonitorId monitorId = new MonitorId(monitorName, monitorCategory);
		Monitor instance = monitorMap.get(monitorId);
		if (instance == null) {
			instance = new MonitorImpl();
			monitorMap.put(monitorId, instance);
		}
		return instance;
	}
}
