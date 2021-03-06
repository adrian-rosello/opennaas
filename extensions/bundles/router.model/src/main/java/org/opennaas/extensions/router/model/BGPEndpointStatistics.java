/**
 * This file was auto-generated by mofcomp -j version 1.0.0 on Wed Jan 12
 * 09:21:06 CET 2011.
 */

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

/**
 * This Class contains accessor and mutator methods for all properties defined in the CIM class BGPEndpointStatistics as well as methods comparable to
 * the invokeMethods defined for this class. This Class implements the BGPEndpointStatisticsBean Interface. The CIM class BGPEndpointStatistics is
 * described as follows:
 * 
 * This class contains statistical information for a connection on a BGPProtocolEndpoint. It is defined as a subclass of StatisticalData and
 * associated with the Endpoint using the relationship, ElementStatisticalData.
 */
public class BGPEndpointStatistics extends StatisticalData implements
		Serializable {

	/**
	 * This constructor creates a BGPEndpointStatisticsBeanImpl Class which implements the BGPEndpointStatisticsBean Interface, and encapsulates the
	 * CIM class BGPEndpointStatistics in a Java Bean. The CIM class BGPEndpointStatistics is described as follows:
	 * 
	 * This class contains statistical information for a connection on a BGPProtocolEndpoint. It is defined as a subclass of StatisticalData and
	 * associated with the Endpoint using the relationship, ElementStatisticalData.
	 */
	public BGPEndpointStatistics() {
	};

	/**
	 * The following constants are defined for use with the ValueMap/Values qualified property inUpdates.
	 */
	private long	inUpdates;

	/**
	 * This method returns the BGPEndpointStatistics.inUpdates property value. This property is described as follows:
	 * 
	 * This is the number of BGP UPDATE messages received on this router's BGP connection. This is a 32-bit counter.
	 * 
	 * @return long current inUpdates property value
	 * @exception Exception
	 */
	public long getInUpdates() {

		return this.inUpdates;
	} // getInUpdates

	/**
	 * This method sets the BGPEndpointStatistics.inUpdates property value. This property is described as follows:
	 * 
	 * This is the number of BGP UPDATE messages received on this router's BGP connection. This is a 32-bit counter.
	 * 
	 * @param long new inUpdates property value
	 * @exception Exception
	 */
	public void setInUpdates(long inUpdates) {

		this.inUpdates = inUpdates;
	} // setInUpdates

	/**
	 * The following constants are defined for use with the ValueMap/Values qualified property outUpdates.
	 */
	private long	outUpdates;

	/**
	 * This method returns the BGPEndpointStatistics.outUpdates property value. This property is described as follows:
	 * 
	 * This is the number of BGP UPDATE messages transmitted on this router's BGP connection. This is a 32-bit counter.
	 * 
	 * @return long current outUpdates property value
	 * @exception Exception
	 */
	public long getOutUpdates() {

		return this.outUpdates;
	} // getOutUpdates

	/**
	 * This method sets the BGPEndpointStatistics.outUpdates property value. This property is described as follows:
	 * 
	 * This is the number of BGP UPDATE messages transmitted on this router's BGP connection. This is a 32-bit counter.
	 * 
	 * @param long new outUpdates property value
	 * @exception Exception
	 */
	public void setOutUpdates(long outUpdates) {

		this.outUpdates = outUpdates;
	} // setOutUpdates

	/**
	 * The following constants are defined for use with the ValueMap/Values qualified property inTotalMessages.
	 */
	private long	inTotalMessages;

	/**
	 * This method returns the BGPEndpointStatistics.inTotalMessages property value. This property is described as follows:
	 * 
	 * This is the total number of BGP messages received on this router's BGP connection. This is a 32-bit counter.
	 * 
	 * @return long current inTotalMessages property value
	 * @exception Exception
	 */
	public long getInTotalMessages() {

		return this.inTotalMessages;
	} // getInTotalMessages

	/**
	 * This method sets the BGPEndpointStatistics.inTotalMessages property value. This property is described as follows:
	 * 
	 * This is the total number of BGP messages received on this router's BGP connection. This is a 32-bit counter.
	 * 
	 * @param long new inTotalMessages property value
	 * @exception Exception
	 */
	public void setInTotalMessages(long inTotalMessages) {

		this.inTotalMessages = inTotalMessages;
	} // setInTotalMessages

	/**
	 * The following constants are defined for use with the ValueMap/Values qualified property outTotalMessages.
	 */
	private long	outTotalMessages;

	/**
	 * This method returns the BGPEndpointStatistics.outTotalMessages property value. This property is described as follows:
	 * 
	 * This is the total number of BGP messages transmitted on this router's BGP connection. This is a 32-bit counter.
	 * 
	 * @return long current outTotalMessages property value
	 * @exception Exception
	 */
	public long getOutTotalMessages() {

		return this.outTotalMessages;
	} // getOutTotalMessages

	/**
	 * This method sets the BGPEndpointStatistics.outTotalMessages property value. This property is described as follows:
	 * 
	 * This is the total number of BGP messages transmitted on this router's BGP connection. This is a 32-bit counter.
	 * 
	 * @param long new outTotalMessages property value
	 * @exception Exception
	 */
	public void setOutTotalMessages(long outTotalMessages) {

		this.outTotalMessages = outTotalMessages;
	} // setOutTotalMessages

	/**
	 * The following constants are defined for use with the ValueMap/Values qualified property lastError.
	 */
	private short[]	lastError;

	/**
	 * This method returns the BGPEndpointStatistics.lastError property value. This property is described as follows:
	 * 
	 * This contains the last error code and error subcode for this router's BGP connection. If no error has occurred, then each integer in the array
	 * is zero.
	 * 
	 * @return short[] current lastError property value
	 * @exception Exception
	 */
	public short[] getLastError() {

		return this.lastError;
	} // getLastError

	/**
	 * This method sets the BGPEndpointStatistics.lastError property value. This property is described as follows:
	 * 
	 * This contains the last error code and error subcode for this router's BGP connection. If no error has occurred, then each integer in the array
	 * is zero.
	 * 
	 * @param short[] new lastError property value
	 * @exception Exception
	 */
	public void setLastError(short[] lastError) {

		this.lastError = lastError;
	} // setLastError

	/**
	 * The following constants are defined for use with the ValueMap/Values qualified property fsmEstablishedTransitions.
	 */
	private long	fsmEstablishedTransitions;

	/**
	 * This method returns the BGPEndpointStatistics.fsmEstablishedTransitions property value. This property is described as follows:
	 * 
	 * This contains the total number of times that the BGP Finite State Machine has transitioned into the established state for this router's BGP
	 * connection. This is a 32-bit counter.
	 * 
	 * @return long current fsmEstablishedTransitions property value
	 * @exception Exception
	 */
	public long getFsmEstablishedTransitions() {

		return this.fsmEstablishedTransitions;
	} // getFsmEstablishedTransitions

	/**
	 * This method sets the BGPEndpointStatistics.fsmEstablishedTransitions property value. This property is described as follows:
	 * 
	 * This contains the total number of times that the BGP Finite State Machine has transitioned into the established state for this router's BGP
	 * connection. This is a 32-bit counter.
	 * 
	 * @param long new fsmEstablishedTransitions property value
	 * @exception Exception
	 */
	public void setFsmEstablishedTransitions(long fsmEstablishedTransitions)
	{

		this.fsmEstablishedTransitions = fsmEstablishedTransitions;
	} // setFsmEstablishedTransitions

	/**
	 * The following constants are defined for use with the ValueMap/Values qualified property fsmEstablishedTime.
	 */
	private long	fsmEstablishedTime;

	/**
	 * This method returns the BGPEndpointStatistics.fsmEstablishedTime property value. This property is described as follows:
	 * 
	 * This indicates how long, in seconds, this peer has been in the established state, or how long since this peer was last in the established
	 * state. It is set to zero when a new peer is configured or when the router is booted. This has the semantics of a 32-bit gauge.
	 * 
	 * @return long current fsmEstablishedTime property value
	 * @exception Exception
	 */
	public long getFsmEstablishedTime() {

		return this.fsmEstablishedTime;
	} // getFsmEstablishedTime

	/**
	 * This method sets the BGPEndpointStatistics.fsmEstablishedTime property value. This property is described as follows:
	 * 
	 * This indicates how long, in seconds, this peer has been in the established state, or how long since this peer was last in the established
	 * state. It is set to zero when a new peer is configured or when the router is booted. This has the semantics of a 32-bit gauge.
	 * 
	 * @param long new fsmEstablishedTime property value
	 * @exception Exception
	 */
	public void setFsmEstablishedTime(long fsmEstablishedTime) {

		this.fsmEstablishedTime = fsmEstablishedTime;
	} // setFsmEstablishedTime

	/**
	 * The following constants are defined for use with the ValueMap/Values qualified property inUpdateElapsedTime.
	 */
	private long	inUpdateElapsedTime;

	/**
	 * This method returns the BGPEndpointStatistics.inUpdateElapsedTime property value. This property is described as follows:
	 * 
	 * This defines the time in seconds since the last BGP UPDATE message was received from the peer. This has the semantics of a 32-bit gauge.
	 * 
	 * @return long current inUpdateElapsedTime property value
	 * @exception Exception
	 */
	public long getInUpdateElapsedTime() {

		return this.inUpdateElapsedTime;
	} // getInUpdateElapsedTime

	/**
	 * This method sets the BGPEndpointStatistics.inUpdateElapsedTime property value. This property is described as follows:
	 * 
	 * This defines the time in seconds since the last BGP UPDATE message was received from the peer. This has the semantics of a 32-bit gauge.
	 * 
	 * @param long new inUpdateElapsedTime property value
	 * @exception Exception
	 */
	public void setInUpdateElapsedTime(long inUpdateElapsedTime) {

		this.inUpdateElapsedTime = inUpdateElapsedTime;
	} // setInUpdateElapsedTime

} // Class BGPEndpointStatistics
