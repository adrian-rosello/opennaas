package org.opennaas.extensions.gim.model.core;

/*
 * #%L
 * GIM :: GIModel and APC PDU driver
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

import java.util.List;

import org.opennaas.extensions.gim.model.core.entities.PowerConsumer;
import org.opennaas.extensions.gim.model.core.entities.PowerSupply;
import org.opennaas.extensions.gim.model.load.DeliveryRatedLoad;

/**
 * 
 * @author Isart Canyameres Gimenez (i2cat Foundation)
 * 
 */
public interface IPowerDelivery {

	public String getId();

	/**
	 * 
	 * @return @code{IPowerSupply}s this system takes energy from.
	 */
	public List<PowerSupply> getPowerSupplies();

	/**
	 * 
	 * @return @code{IPowerConsumer}s this system delivers energy to.
	 */
	public List<PowerConsumer> getPowerConsumers();

	/**
	 * 
	 * @return the DeliveryRatedLoad (both input and output) this system is designated to handle
	 */
	public DeliveryRatedLoad getDeliveryRatedLoad();

	public String toString();

}
