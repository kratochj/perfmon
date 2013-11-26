package eu.kratochvil.perfmon.db.utils;

import java.beans.PropertyDescriptor;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang.StringUtils;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class CustomBeanProcessor extends BeanProcessor {

	/**
	 * ResultSet column to bean property name overrides.
	 */
	private final Map<String, String> columnToPropertyOverrides;

	public CustomBeanProcessor() {
		this(new HashMap<String, String>());
	}

	public CustomBeanProcessor(Map<String, String> columnToPropertyOverrides) {
		this.columnToPropertyOverrides = columnToPropertyOverrides;
	}

	@Override
	protected int[] mapColumnsToProperties(ResultSetMetaData rsmd, PropertyDescriptor[] props) throws SQLException {

		int cols = rsmd.getColumnCount();
		int[] columnToProperty = new int[cols + 1];
		Arrays.fill(columnToProperty, PROPERTY_NOT_FOUND);

		for (int col = 1; col <= cols; col++) {
			String columnName = rsmd.getColumnLabel(col);
			if (null == columnName || 0 == columnName.length()) {
				columnName = rsmd.getColumnName(col);
			}
			String propertyName = columnToPropertyOverrides.get(columnName);
			if (propertyName == null) {
				propertyName = columnName;
			}
			for (int i = 0; i < props.length; i++) {

				if (propertyName.equalsIgnoreCase(props[i].getName()) || removeDashes(propertyName).equalsIgnoreCase(props[i].getName())) {
					columnToProperty[col] = i;
					break;
				}
			}
		}

		return columnToProperty;
	}

	private String removeDashes(String value) {
		return StringUtils.remove(value, "_");

	}
}
