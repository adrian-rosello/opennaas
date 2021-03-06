package org.opennaas.extensions.router.model;

/*
 * #%L
 * OpenNaaS :: CIM Model
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

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LogicalTunnelPort extends NetworkPort implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -8777159572331512197L;
	private long				peer_unit;

	public void setPeer_unit(long peer_unit) {
		this.peer_unit = peer_unit;
	}

	public long getPeer_unit() {
		return peer_unit;
	}

	public void merge(LogicalTunnelPort hashLogicalPort) {
		super.merge(hashLogicalPort);
		if (peer_unit == 0)
			peer_unit = hashLogicalPort.getPeer_unit();

	}

}
