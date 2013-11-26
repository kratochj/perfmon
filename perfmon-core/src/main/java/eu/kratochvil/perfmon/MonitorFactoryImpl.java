package eu.kratochvil.perfmon;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class MonitorFactoryImpl implements MonitorFactory {

	public static final Logger logger = LogManager.getLogger(MonitorFactoryImpl.class);

	Map<MonitorId, Monitor> monitorMap = new HashMap<MonitorId, Monitor>();

	@Override
	public Monitor getInstance(String monitorName, MonitorCategory monitorCategory) {
		MonitorId monitorId = new MonitorId(monitorName, monitorCategory);
		Monitor instance = monitorMap.get(monitorId);
		if (instance == null) {
			if (MonitorCategory.ASYNC.equals(monitorCategory)) {
				instance = new AsyncMonitorImpl(monitorId.getName(), monitorId.getCategory());
			} else {
				instance = new MonitorImpl(monitorId.getName(), monitorId.getCategory());
			}
			monitorMap.put(monitorId, instance);
		}
		return instance;
	}

	public Map<MonitorId, Monitor> getMonitorMap() {
		return monitorMap;
	}

	@Override
	public void reset() {
		logger.info("Reseting statistics");
		monitorMap = new HashMap<MonitorId, Monitor>();
	}
}
