package eu.kratochvil.perfmon.db;

import java.sql.SQLException;

import eu.kratochvil.perfmon.MonitorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class StatisticsPersistenceTask implements Runnable {

	public static final Logger logger = LogManager.getLogger(StatisticsPersistenceTask.class);

	StatisticsDao dao;

	MonitorFactory monitorFactory;

	@Override
	public void run() {
		assert dao != null;
		assert monitorFactory != null;
		try {
			dao.saveStatistics(monitorFactory);
		} catch (SQLException e) {
			logger.error("Error saving statistics: {}", e.getMessage(), e);
		} catch (AssertionError e) {
			logger.fatal("Configuration error: {}", e.getMessage());
			throw e;
		}
	}

	public void setDao(StatisticsDao dao) {
		this.dao = dao;
	}

	public void setMonitorFactory(MonitorFactory monitorFactory) {
		this.monitorFactory = monitorFactory;
	}
}
