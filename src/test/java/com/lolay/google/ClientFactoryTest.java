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
package com.lolay.google;

import junit.framework.TestCase;

import com.lolay.google.geocode.GeocodeClient;

public class ClientFactoryTest extends TestCase {
	public void testGeocode() throws Exception {
		ClientFactory factory = new ClientFactory("http://maps.googleapis.com");
		GeocodeClient geocode1 = factory.getGeocode();
		assertNotNull(geocode1);
		GeocodeClient geocode2 = factory.getGeocode();
		assertNotNull(geocode2);
		assertSame(geocode1, geocode1);
	}
}
