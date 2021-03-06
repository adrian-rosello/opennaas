package org.opennaas.extensions.router.junos.actionssets;

/*
 * #%L
 * OpenNaaS :: Router :: Junos ActionSet
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

import org.opennaas.extensions.router.capability.bgp.BGPActionSet;
import org.opennaas.extensions.router.capability.chassis.ChassisActionSet;
import org.opennaas.extensions.router.capability.gretunnel.GRETunnelActionSet;
import org.opennaas.extensions.router.capability.ip.IPActionSet;
import org.opennaas.extensions.router.capability.l3vlan.L3VlanActionset;
import org.opennaas.extensions.router.capability.ospf.OSPFActionSet;
import org.opennaas.extensions.router.capability.ospfv3.OSPFv3ActionSet;
import org.opennaas.extensions.router.capability.staticroute.StaticRouteActionSet;
import org.opennaas.extensions.router.capability.topologydiscovery.TopologyDiscoveryActionSet;
import org.opennaas.extensions.router.capability.vlanbridge.VLANBridgeActionSet;
import org.opennaas.extensions.router.capability.vrrp.VRRPActionSet;

public class ActionConstants {

	public static final String	GETCONFIG									= "getConfiguration";
	// public static final String CONFIGLT = "configureLogicalTunnel";

	public static final String	ISALIVE										= "isAlive";
	public static final String	VALIDATE									= "validate";

	// Chassis ActionSET
	public static final String	DELETESUBINTERFACE							= ChassisActionSet.DELETESUBINTERFACE;
	public static final String	CONFIGURESUBINTERFACE						= ChassisActionSet.CONFIGURESUBINTERFACE;
	public static final String	SET_TAGGEDETHERNET_ENCAPSULATION			= ChassisActionSet.SET_TAGGEDETHERNET_ENCAPSULATION;
	public static final String	REMOVE_TAGGEDETHERNET_ENCAPSULATION			= ChassisActionSet.REMOVE_TAGGEDETHERNET_ENCAPSULATION;
	public static final String	SET_VLANID									= ChassisActionSet.SET_VLANID;
	public static final String	SETENCAPSULATION							= "setEncapsulation";
	public static final String	SETENCAPSULATIONLABEL						= "setEncapsulationLabel";

	public static final String	CONFIGURESTATUS								= ChassisActionSet.CONFIGURESTATUS;

	// LogicalRouters
	public static final String	DELETELOGICALROUTER							= ChassisActionSet.DELETELOGICALROUTER;
	public static final String	CREATELOGICALROUTER							= ChassisActionSet.CREATELOGICALROUTER;
	public static final String	ADDINTERFACETOLOGICALROUTER					= ChassisActionSet.ADDINTERFACETOLOGICALROUTER;
	public static final String	REMOVEINTERFACEFROMLOGICALROUTER			= ChassisActionSet.REMOVEINTERFACEFROMLOGICALROUTER;

	// IP
	public static final String	SETIPv4										= IPActionSet.SET_IPv4;
	public static final String	SETIPv6										= IPActionSet.SET_IPv6;
	public static final String	SETINTERFACEDESCRIPTION						= IPActionSet.SET_INTERFACE_DESCRIPTION;
	public static final String	ADDIPv4										= IPActionSet.ADD_IPv4;
	public static final String	ADDIPv6										= IPActionSet.ADD_IPv6;
	public static final String	REMOVEIPv4									= IPActionSet.REMOVE_IPv4;
	public static final String	REMOVEIPv6									= IPActionSet.REMOVE_IPv6;

	// TODO THE SAME NAME FOR THE OTHER LIST LOGICAL ROUTERS
	public static final String	GETLOGICALROUTERS							= "getLogicalRouters";
	// TODO LISTLOGICALROUTER!!!
	public static final String	LISTLOGICALROUTER							= "listLogicalRouter";

	// GRETunnel ActionSET
	public static final String	CREATETUNNEL								= GRETunnelActionSet.CREATETUNNEL;
	public static final String	DELETETUNNEL								= GRETunnelActionSet.DELETETUNNEL;
	public static final String	GETTUNNELCONFIG								= GRETunnelActionSet.GETTUNNELCONFIG;
	public static final String	SHOWTUNNELS									= GRETunnelActionSet.SHOWTUNNELS;

	// OSPF actionset
	public static final String	OSPF_CONFIGURE								= OSPFActionSet.OSPF_CONFIGURE;
	public static final String	OSPF_CLEAR									= OSPFActionSet.OSPF_CLEAR;
	public static final String	OSPF_GET_CONFIGURATION						= OSPFActionSet.OSPF_GET_CONFIGURATION;
	public static final String	OSPF_ACTIVATE								= OSPFActionSet.OSPF_ACTIVATE;
	public static final String	OSPF_DEACTIVATE								= OSPFActionSet.OSPF_DEACTIVATE;
	public static final String	OSPF_ENABLE_INTERFACE						= OSPFActionSet.OSPF_ENABLE_INTERFACE;
	public static final String	OSPF_DISABLE_INTERFACE						= OSPFActionSet.OSPF_DISABLE_INTERFACE;
	public static final String	OSPF_CONFIGURE_AREA							= OSPFActionSet.OSPF_CONFIGURE_AREA;
	public static final String	OSPF_REMOVE_AREA							= OSPFActionSet.OSPF_REMOVE_AREA;
	public static final String	OSPF_ADD_INTERFACE_IN_AREA					= OSPFActionSet.OSPF_ADD_INTERFACE_IN_AREA;
	public static final String	OSPF_REMOVE_INTERFACE_IN_AREA				= OSPFActionSet.OSPF_REMOVE_INTERFACE_IN_AREA;

	// OSPFv3 actionset
	public static final String	OSPFv3_CONFIGURE							= OSPFv3ActionSet.OSPFv3_CONFIGURE;
	public static final String	OSPFv3_CLEAR								= OSPFv3ActionSet.OSPFv3_CLEAR;
	public static final String	OSPFv3_GET_CONFIGURATION					= OSPFv3ActionSet.OSPFv3_GET_CONFIGURATION;
	public static final String	OSPFv3_ACTIVATE								= OSPFv3ActionSet.OSPFv3_ACTIVATE;
	public static final String	OSPFv3_DEACTIVATE							= OSPFv3ActionSet.OSPFv3_DEACTIVATE;
	public static final String	OSPFv3_ENABLE_INTERFACE						= OSPFv3ActionSet.OSPFv3_ENABLE_INTERFACE;
	public static final String	OSPFv3_DISABLE_INTERFACE					= OSPFv3ActionSet.OSPFv3_DISABLE_INTERFACE;
	public static final String	OSPFv3_CONFIGURE_AREA						= OSPFv3ActionSet.OSPFv3_CONFIGURE_AREA;
	public static final String	OSPFv3_REMOVE_AREA							= OSPFv3ActionSet.OSPFv3_REMOVE_AREA;
	public static final String	OSPFv3_ADD_INTERFACE_IN_AREA				= OSPFv3ActionSet.OSPFv3_ADD_INTERFACE_IN_AREA;
	public static final String	OSPFv3_REMOVE_INTERFACE_IN_AREA				= OSPFv3ActionSet.OSPFv3_REMOVE_INTERFACE_IN_AREA;

	// Static Route actionset
	public static final String	STATIC_ROUTE_CREATE							= StaticRouteActionSet.STATIC_ROUTE_CREATE;
	public static final String	STATIC_ROUTE_DELETE							= StaticRouteActionSet.STATIC_ROUTE_DELETE;

	// BGP actionset
	public static final String	CONFIGURE_BGP								= BGPActionSet.CONFIGURE_BGP;
	public static final String	UNCONFIGURE_BGP								= BGPActionSet.UNCONFIGURE_BGP;

	// VRRP actionset
	public static final String	VRRP_CONFIGURE								= VRRPActionSet.VRRP_CONFIGURE;
	public static final String	VRRP_UNCONFIGURE							= VRRPActionSet.VRRP_UNCONFIGURE;
	public static final String	VRRP_UPDATE_IP_ADDRESS						= VRRPActionSet.VRRP_UPDATE_VIRTUAL_IP_ADDRESS;
	public static final String	VRRP_UPDATE_PRIORITY						= VRRPActionSet.VRRP_UPDATE_PRIORITY;
	public static final String	VRRP_UPDATE_VIRTUAL_LINK_ADDRESS			= VRRPActionSet.VRRP_UPDATE_VIRTUAL_LINK_ADDRESS;

	// VLAN BRIDGE actionset
	public static final String	VLAN_BRIDGE_CREATE_BRIDGE_DOMAIN			= VLANBridgeActionSet.CREATE_VLAN_BRIDGE_DOMAIN_ACTION;
	public static final String	VLAN_BRIDGE_REMOVE_BRIDGE_DOMAIN			= VLANBridgeActionSet.DELETE_VLAN_BRIDGE_DOMAIN_ACTION;
	public static final String	VLAN_BRIDGE_SET_IFACE_VLAN_OPTIONS			= VLANBridgeActionSet.SET_INTERFACE_VLAN_OPTIONS_ACTION;
	public static final String	VLAN_BRIDGE_UNSET_IFACE_VLAN_OPTIONS		= VLANBridgeActionSet.UNSET_INTERFACE_VLAN_OPTIONS_ACTION;

	// L3 VLAN actionset
	public static final String	L3VLAN_ADD_IP_TO_DOMAIN						= L3VlanActionset.L3VLAN_ADD_IP_TO_DOMAIN;
	public static final String	L3VLAN_REMOVE_IP_FROM_DOMAIN				= L3VlanActionset.L3VLAN_REMOVE_IP_FROM_DOMAIN;

	// TOPOLOGY DISCOVERY actionset
	public static final String	TOPOLOGY_DISCOVERY_GET_NEIGHBOURS			= TopologyDiscoveryActionSet.TOPOLOGY_DISCOVERY_GET_NEIGHBOURS;
	public static final String	TOPOLOGY_DISCOVERY_GET_LOCAL_INFORMATION	= TopologyDiscoveryActionSet.TOPOLOGY_DISCOVERY_GET_LOCAL_INFORMATION;
	public static final String	TOPOLOGY_DISCOVERY_GET_INTERFACE_NEIGHBOUR	= TopologyDiscoveryActionSet.TOPOLOGY_DISCOVERY_GET_INTERFACE_NEIGHBOUR;

}
