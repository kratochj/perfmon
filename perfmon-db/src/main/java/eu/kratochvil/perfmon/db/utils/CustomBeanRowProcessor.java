package eu.kratochvil.perfmon.db.utils;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class CustomBeanRowProcessor extends BasicRowProcessor {

	/**
	 * The default BeanProcessor instance to use if not supplied in the
	 * constructor.
	 */
	private static final BeanProcessor defaultConvert = new CustomBeanProcessor();

	/**
	 * The Singleton instance of this class.
	 */
	private static final BasicRowProcessor instance = new CustomBeanRowProcessor();

	public CustomBeanRowProcessor() {
		this(defaultConvert);
	}

	public CustomBeanRowProcessor(BeanProcessor convert) {
		super(convert);
	}
}
