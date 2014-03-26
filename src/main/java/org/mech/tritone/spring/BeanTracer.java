package org.mech.tritone.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * <p>
 * The BeanTracer is class that just log trace bean creation process
 * </p>
 * <p>
 * Date: 7.2.2012 13:30:07
 * </p>
 * 
 */
public class BeanTracer implements BeanPostProcessor {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(BeanTracer.class);

	@Override
	public Object postProcessAfterInitialization(Object bean, String name)
			throws BeansException {
		LOGGER.trace("bean initialized [name=" + name + ", bean=" + bean + "] ");
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String name)
			throws BeansException {
		return bean;
	}

}
