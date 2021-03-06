package org.opennaas.extensions.openflowswitch.driver.floodlight.protocol.client;

/*
 * #%L
 * OpenNaaS :: OpenFlow Switch :: Floodlight driver v0.90
 * %%
 * Copyright (C) 2007 - 2014 Fundació Privada i2CAT, Internet i Innovació a Catalunya
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.apache.cxf.jaxrs.client.ProxyClassLoader;
import org.opennaas.core.resources.protocol.ProtocolSessionContext;
import org.opennaas.extensions.openflowswitch.driver.floodlight.protocol.FloodlightProtocolSession;
import org.opennaas.extensions.openflowswitch.driver.floodlight.protocol.client.serializers.json.CustomJSONProvider;

/**
 * @author Isart Canyameres Gimenez (i2cat Foundation)
 * @author Adrian Rosello (i2CAT)
 * 
 */
public class FloodlightClientFactory {

	public IFloodlightStaticFlowPusherClient createClient(ProtocolSessionContext sessionContext) {

		String uri = (String) sessionContext.getSessionParameters().get(ProtocolSessionContext.PROTOCOL_URI);
		String switchId = (String) sessionContext.getSessionParameters().get(FloodlightProtocolSession.SWITCHID_CONTEXT_PARAM_NAME);
		// TODO use switch id to instantiate the client

		// create CXF client
		ProxyClassLoader classLoader = new ProxyClassLoader();
		classLoader.addLoader(IFloodlightStaticFlowPusherClient.class.getClassLoader());
		classLoader.addLoader(JAXRSClientFactoryBean.class.getClassLoader());

		JAXRSClientFactoryBean bean = new JAXRSClientFactoryBean();
		bean.setAddress(uri);
		bean.setProvider(new CustomJSONProvider());
		bean.setResourceClass(IFloodlightStaticFlowPusherClient.class);
		bean.setClassLoader(classLoader);

		IFloodlightStaticFlowPusherClient cxfClient = (IFloodlightStaticFlowPusherClient) bean.create();

		// create mixed client using CXF and custom Java clients
		IFloodlightStaticFlowPusherClient client = new FloodlightStaticFlowPusherClient(cxfClient, sessionContext);

		return client;
	}

	public IFloodlightStaticFlowPusherClient destroyClient() {

		return null;

	}

}
