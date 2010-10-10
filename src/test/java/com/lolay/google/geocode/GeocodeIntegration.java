/*
 * Licensed to Lolay, Inc. under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  Lolay, Inc. licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://github.com/lolay/google-maps-java/raw/master/LICENSE
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.    
 */
package com.lolay.google.geocode;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lolay.google.ClientFactory;

import junit.framework.TestCase;

public class GeocodeIntegration extends TestCase {
	private static final Log testReverseLog = LogFactory.getLog(GeocodeIntegration.class.getName() + ".testReverse");
	private static final Log testForwardLog = LogFactory.getLog(GeocodeIntegration.class.getName() + ".testForward");
	private static final String baseUrl = "http://maps.googleapis.com";
	
	public void testReverse() throws Exception {
		Log log = testReverseLog;
		GeocodeClient client = new ClientFactory(baseUrl).getGeocode();
		GeocodeInvoker invoker = GeocodeInvoker.builder().latLng(40.714224D, -73.961452D).sensor(false).build();
		
		GeocodeResponse response = null;
		try {
			long start = System.currentTimeMillis();
			response = invoker.geocode(client);
			long end = System.currentTimeMillis();
			log.trace(String.format("Reverse geocode took %s ms", end - start));
		} catch (Exception e) {
			log.error(e);
			fail();
		}
		assertNotNull(response);
		assertNotNull(response.getStatus());
		assertEquals(GeocodeStatus.OK, response.getStatus());
		assertNotNull(response.getResults());
		assertEquals(8, response.getResults().size());
		
		GeocodeResult result1 = response.getResults().get(0);
		assertNotNull(result1.getType());
		assertEquals(GeocodeResultType.STREET_ADDRESS, result1.getType());
		assertEquals("279-281 Bedford Ave, Brooklyn, NY 11211, USA", result1.getFormattedAddress());
		assertNotNull(result1.getAddressComponents());
		assertEquals(8, result1.getAddressComponents().size());
		
		GeocodeAddressComponent addressComponent1 = result1.getAddressComponents().get(0);
		assertNotNull(addressComponent1.getType());
		assertEquals(GeocodeAddressComponentType.STREET_NUMBER, addressComponent1.getType());
		assertNotNull(addressComponent1.getLongName());
		assertEquals("279-281", addressComponent1.getLongName());
		assertNotNull(addressComponent1.getShortName());
		assertEquals("279-281", addressComponent1.getShortName());
		
		GeocodeGeometry geometry = result1.getGeometry();
		assertNotNull(geometry);
		assertNotNull(geometry.getLocationType());
		assertEquals(GeocodeLocationType.RANGE_INTERPOLATED, geometry.getLocationType());
		validateLocation(geometry.getLocation(), 40.7142215D, -73.9614454D);
		validateFrame(geometry.getViewPort(), 40.7110552D, -73.9646313D, 40.7173505D, -73.9583361D);
		validateFrame(geometry.getBounds(), 40.7139010D, -73.9616800D, 40.7145047D, -73.9612874D);
	}
	
	public void testForward() throws Exception {
		Log log = testForwardLog;
		GeocodeClient client = new ClientFactory(baseUrl).getGeocode();
		GeocodeInvoker invoker = GeocodeInvoker.builder().address("1600 Amphitheatre Parkway, Mountain View, CA").sensor(false).build();
		
		GeocodeResponse response = null;
		try {
			long start = System.currentTimeMillis();
			response = invoker.geocode(client);
			long end = System.currentTimeMillis();
			log.trace(String.format("Forward geocode took %s ms", end - start));
		} catch (Exception e) {
			log.error(e);
			fail();
		}
		assertNotNull(response);
		assertNotNull(response.getStatus());
		assertEquals(GeocodeStatus.OK, response.getStatus());
		assertNotNull(response.getResults());
		assertEquals(1, response.getResults().size());
		
		GeocodeResult result1 = response.getResults().get(0);
		assertNotNull(result1.getType());
		assertEquals(GeocodeResultType.STREET_ADDRESS, result1.getType());
		assertEquals("1600 Amphitheatre Pkwy, Mountain View, CA 94043, USA", result1.getFormattedAddress());
		assertNotNull(result1.getAddressComponents());
		assertEquals(8, result1.getAddressComponents().size());
		
		GeocodeAddressComponent addressComponent1 = result1.getAddressComponents().get(0);
		assertNotNull(addressComponent1.getType());
		assertEquals(GeocodeAddressComponentType.STREET_NUMBER, addressComponent1.getType());
		assertNotNull(addressComponent1.getLongName());
		assertEquals("1600", addressComponent1.getLongName());
		assertNotNull(addressComponent1.getShortName());
		assertEquals("1600", addressComponent1.getShortName());
		
		GeocodeGeometry geometry = result1.getGeometry();
		assertNotNull(geometry);
		assertNotNull(geometry.getLocationType());
		assertEquals(GeocodeLocationType.ROOFTOP, geometry.getLocationType());
		validateLocation(geometry.getLocation(), 37.4227820D, -122.0850990D);
		validateFrame(geometry.getViewPort(), 37.4196344D, -122.0882466D, 37.4259296D, -122.0819514D);
		assertNull(geometry.getBounds());
	}
	
	private void validateLocation(GeocodeLocation location, Double latitude, Double longitude) {
		assertNotNull(location);
		assertNotNull(location.getLat());
		assertEquals(latitude, location.getLat());
		assertNotNull(location.getLng());
		assertEquals(longitude, location.getLng());
	}
	
	private void validateFrame(GeocodeFrame frame, Double southWestLatitude, Double southWestLongitude, Double northEastLatitude, Double northEastLongitude) {
		assertNotNull(frame);
		validateLocation(frame.getSouthWest(), southWestLatitude, southWestLongitude);
		validateLocation(frame.getNorthEast(), northEastLatitude, northEastLongitude);
	}
}