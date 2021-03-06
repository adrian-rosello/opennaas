package org.opennaas.core.resources;

/*
 * #%L
 * OpenNaaS :: Core :: Resources
 * %%
 * Copyright (C) 2007 - 2014 Fundació Privada i2CAT, Internet i Innovació a Catalunya
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.Filter;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.util.tracker.ServiceTracker;

public abstract class AbstractActivator {
	/** Logger */
	private static Log	log	= LogFactory.getLog(AbstractActivator.class);

	/**
	 * Create a Filter to give to the service tracker based on the information about the service to lookup in the properties
	 * 
	 * @throws InvalidSyntaxException
	 */
	protected static Filter createServiceFilter(String clazz, Properties properties) throws InvalidSyntaxException {
		StringBuilder query = new StringBuilder();
		query.append("(&");
		query.append("(").append(Constants.OBJECTCLASS).append("=").append(clazz).append(")");
		for (String key : properties.stringPropertyNames()) {
			String value = properties.getProperty(key);
			query.append("(").append(key).append("=").append(value).append(")");
		}
		query.append(")");
		return FrameworkUtil.createFilter(query.toString());
	}

	/**
	 * Fetch a service from OSGI Registry
	 * 
	 * @param bundleContext
	 * @param filter
	 * @return Object service instance
	 * @throws ActivatorException
	 *             if service could not be retrieved
	 */
	protected static Object getServiceFromRegistry(BundleContext bundleContext, Filter filter)
			throws ActivatorException {

		log.debug("Looking up Service from registry with properties: " + filter);

		if (bundleContext == null) {
			throw new ActivatorException(ErrorConstants.ERROR_ACTIVATOR_SERVICE_NOTFOUND + " BundleContext is null");
		}

		ServiceTracker tracker = new ServiceTracker(bundleContext, filter, null);
		Object service = getServiceUsingTracker(tracker);
		if (service != null) {
			return service;
		}

		throw new ActivatorException(ErrorConstants.ERROR_ACTIVATOR_SERVICE_NOTFOUND + " Service filter: " + filter);
	}

	/**
	 * Fetch a service from OSGI Registry
	 * 
	 * @param bundleContext
	 * @param clazz
	 * @return Object service instance
	 * @throws ActivatorException
	 *             if service could not be retrieved
	 */
	protected static Object getServiceFromRegistry(BundleContext bundleContext, String clazz)
			throws ActivatorException {

		log.debug("Looking up Service from registry from class: " + clazz);

		if (bundleContext == null) {
			throw new ActivatorException(ErrorConstants.ERROR_ACTIVATOR_SERVICE_NOTFOUND + " BundleContext is null");
		}

		ServiceTracker tracker = new ServiceTracker(bundleContext, clazz, null);
		Object service = getServiceUsingTracker(tracker);
		if (service != null) {
			return service;
		}

		throw new ActivatorException(ErrorConstants.ERROR_ACTIVATOR_SERVICE_NOTFOUND);
	}

	protected static Object getServiceUsingTracker(ServiceTracker tracker) throws ActivatorException {
		tracker.open();
		Object service = null;
		try {
			service = tracker.waitForService(20000);
		} catch (InterruptedException e) {
			throw new ActivatorException(e);
		}
		tracker.close();

		return service;
	}

}
