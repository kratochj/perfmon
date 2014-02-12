package eu.kratochvil.perfmon.rest;


import eu.kratochvil.perfmon.MonitorStats;
import eu.kratochvil.perfmon.jmx.Monitor;
import eu.kratochvil.perfmon.jmx.StatisticsJmx;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
@Named
@Path("/statistics")
public class StatisticsRestService {

    public static final Logger logger = LogManager.getLogger(StatisticsRestService.class);

    @Inject
    @Qualifier("statisticsJmxProxy")
    private StatisticsJmx statisticsJmx;


    @GET
    @Path("/")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Monitor> getAllStatistics() {
        List<Monitor> monitors = statisticsJmx.statistics();
        logger.debug("Statistics: {}", monitors);
        return monitors;
    }

    @GET
    @Path("/{name}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public MonitorStats getAllStatistics(@PathParam("name") String name) {
        List<Monitor> monitors = statisticsJmx.statistics();
        for (Monitor monitor : monitors) {
            if (name.equals(monitor.getName())) {
                logger.debug("Monitor: {}", monitor);
                return monitor.getStatistics();
            }
        }
        logger.debug("Monitor {} not found", name);
        throw new NotFoundException();
    }

}
