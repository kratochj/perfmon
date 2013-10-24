package eu.kratochvil.perfmon;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public interface Monitor {

	StopWatch getStopWatch();

	void save(StopWatch stopWatch);

	MonitorStats getStatistics();

	String getKey();

}