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

import java.util.Arrays;

public enum GeocodeAddressComponentType {
	STREET_ADDRESS,
	ROUTE,
	INTERSECTION,
	POLITICAL,
	COUNTRY,
	ADMINISTRATIVE_AREA_LEVEL_1,
	ADMINISTRATIVE_AREA_LEVEL_2,
	ADMINISTRATIVE_AREA_LEVEL_3,
	COLLOQUIAL_AREA,
	LOCALITY,
	SUBLOCALITY,
	NEIGHBORHOOD,
	PREMISE,
	SUBPREMISE,
	POSTAL_CODE,
	NATURAL_FEATURE,
	AIRPORT,
	PARK,
	POINT_OF_INTEREST,
	POST_BOX,
	STREET_NUMBER,
	FLOOR,
	ROOM;
	
	public static GeocodeAddressComponentType valueOfIgnoreCase(String value) {
		for (GeocodeAddressComponentType type : Arrays.asList(GeocodeAddressComponentType.values())) {
			if (type.toString().equalsIgnoreCase(value)) {
				return type;
			}
		}
		
		throw new IllegalArgumentException(String.format("Could not find a GeocodeAddressComponentType for %s", value));
	}
}