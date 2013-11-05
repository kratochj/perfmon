package eu.kratochvil.perfmon.jmx;

import java.io.Serializable;

import eu.kratochvil.perfmon.MonitorStats;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public interface Monitor extends Serializable {

	String getName();

	MonitorStats getStatistics();

}
