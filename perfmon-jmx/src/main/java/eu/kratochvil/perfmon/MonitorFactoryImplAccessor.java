package eu.kratochvil.perfmon;

import javax.inject.Inject;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
@Component
public class MonitorFactoryImplAccessor {

	@Inject
	MonitorFactoryImpl monitorFactory;


	public Map<MonitorId, Monitor> getMonitorMap() {
		return monitorFactory.monitorMap;
	}


}
