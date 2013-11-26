package eu.kratochj.perfmon.db;

import eu.kratochvil.perfmon.db.Statistics;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testApplicationContext.xml")
public class TestDbConnection {

	@Autowired
	QueryRunner query;

	@Test
	public void testSelectEmpty() throws Exception {
		Statistics stats = query.query("select * from STATISTICS where rownum <= 1", new BeanHandler<Statistics>(Statistics.class));
		assertNull(stats);
	}
}
