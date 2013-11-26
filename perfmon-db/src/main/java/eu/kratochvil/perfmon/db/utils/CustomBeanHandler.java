package eu.kratochvil.perfmon.db.utils;

import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class CustomBeanHandler<T> extends BeanHandler<T>{

	public CustomBeanHandler(Class<T> type) {
		this(type, new CustomBeanRowProcessor());
	}

	public CustomBeanHandler(Class<T> type, RowProcessor convert) {
		super(type, convert);
	}
}
