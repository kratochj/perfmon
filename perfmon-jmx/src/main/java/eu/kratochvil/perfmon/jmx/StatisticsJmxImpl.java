package eu.kratochvil.perfmon.jmx;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import eu.kratochvil.perfmon.MonitorFactoryImplAccessor;
import eu.kratochvil.perfmon.MonitorId;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
@Component
@ManagedResource(objectName = "eu.kratochvil.perfmon.jmx:name=Statistics",
		description = "Statistics by perfmon")
public class StatisticsJmxImpl implements StatisticsJmx {

	public static final Logger logger = LogManager.getLogger(StatisticsJmxImpl.class);

	@Inject
	MonitorFactoryImplAccessor monitorFactoryImplAccessor;


	@ManagedOperation(description="Gets online statistics.")
	public List<Monitor> statistics() {
		List<Monitor> monitors = new ArrayList<Monitor>();

		for (Map.Entry<MonitorId, eu.kratochvil.perfmon.Monitor> entry : monitorFactoryImplAccessor.getMonitorMap().entrySet()) {
			Monitor monitorPojo = new MonitorPojoImpl(entry.getKey().getName(), entry.getValue().getStatistics());
			monitors.add(monitorPojo);
		}
		return monitors;
	}
}
