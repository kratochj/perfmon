package eu.kratochj.perfmon.db;

import eu.kratochvil.perfmon.db.Statistics;
import eu.kratochvil.perfmon.db.utils.StatisticsMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testApplicationContext.xml")
public class TestDbConnection {

	@Autowired
    JdbcTemplate jdbcTemplate;

	@Test
	public void testSelectEmpty() throws Exception {
		List<Statistics> stats = jdbcTemplate.query("select * from STATISTICS where rownum <= 1", new StatisticsMapper());
		assertNotNull(stats);
        assertEquals(stats.size(), 0);
	}
}
