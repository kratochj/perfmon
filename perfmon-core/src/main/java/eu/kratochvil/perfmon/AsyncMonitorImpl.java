package eu.kratochvil.perfmon;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class AsyncMonitorImpl extends MonitorImpl implements Monitor {

	Map<String, StopWatch> stopWatches = new HashMap<String, StopWatch>();

	public AsyncMonitorImpl(String monitorName, MonitorCategory monitorCategory) {
		super(monitorName, monitorCategory);
	}

	@Override
	public StopWatch getStopWatch(String key) {
		StopWatch stopWatch = stopWatches.get(key);
		if (stopWatch == null) {
			stopWatch = new StopWatch();
			stopWatches.put(key, stopWatch);
		}
		return stopWatch;
	}

	@Override
	public StopWatch getStopWatch() {
		throw new IllegalArgumentException("Use getStopWatch with key!");
	}

}
