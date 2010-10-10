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
	private static final String baseUrl = "http://maps.googleapis.com";
	
	public void testReverse() throws Exception {
		Log log = testReverseLog;
		GeocodeClient client = new ClientFactory(baseUrl).getGeocode();
		GeocodeInvoker invoker = GeocodeInvoker.builder().latlng(40.714224D, -73.961452D).sensor(false).build();
		
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
		assertEquals(GeocodeType.STREET_ADDRESS, result1.getType());
		assertEquals("279-281 Bedford Ave, Brooklyn, NY 11211, USA", result1.getFormattedAddress());
	}
}