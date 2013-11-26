package eu.kratochvil.perfmon.db.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.RowProcessor;

/**
 * <code>ResultSetHandler</code> implementation that converts a
 * <code>ResultSet</code> into a <code>List</code> of beans. This class is
 * thread safe.
 *
 * @param <T> the target bean type
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 * @see org.apache.commons.dbutils.ResultSetHandler
 */
public class CustomBeanListHandler<T> implements ResultSetHandler<List<T>> {


	/**
	 * The Class of beans produced by this handler.
	 */
	private final Class<T> type;

	/**
	 * The RowProcessor implementation to use when converting rows
	 * into beans.
	 */
	private final RowProcessor convert;

	/**
	 * Creates a new instance of BeanListHandler.
	 *
	 * @param type The Class that objects returned from <code>handle()</code>
	 *             are created from.
	 */
	public CustomBeanListHandler(Class<T> type) {
		this(type, new BasicRowProcessor(new CustomBeanProcessor()));
	}

	/**
	 * Creates a new instance of BeanListHandler.
	 *
	 * @param type    The Class that objects returned from <code>handle()</code>
	 *                are created from.
	 * @param convert The <code>RowProcessor</code> implementation
	 *                to use when converting rows into beans.
	 */
	public CustomBeanListHandler(Class<T> type, RowProcessor convert) {
		this.type = type;
		this.convert = convert;
	}

	/**
	 * Convert the whole <code>ResultSet</code> into a List of beans with
	 * the <code>Class</code> given in the constructor.
	 *
	 * @param rs The <code>ResultSet</code> to handle.
	 * @return A List of beans, never <code>null</code>.
	 * @throws java.sql.SQLException if a database access error occurs
	 * @see org.apache.commons.dbutils.RowProcessor#toBeanList(java.sql.ResultSet, Class)
	 */
	@Override
	public List<T> handle(ResultSet rs) throws SQLException {
		return this.convert.toBeanList(rs, type);
	}
}


