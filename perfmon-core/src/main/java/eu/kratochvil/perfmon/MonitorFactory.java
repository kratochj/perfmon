package eu.kratochvil.perfmon;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public interface MonitorFactory {

	Monitor getInstance(String monitorName, MonitorCategory monitorCategory);

	void reset();

}
