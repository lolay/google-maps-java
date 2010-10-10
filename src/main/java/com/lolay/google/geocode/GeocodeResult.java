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

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@XmlRootElement(name="result")
@XmlAccessorType(value=XmlAccessType.FIELD)
public class GeocodeResult implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name="type",required=true)
	@XmlJavaTypeAdapter(value=GeocodeResultTypeAdapter.class)
	private List<GeocodeResultType> types = null;
	@XmlElement(name="formatted_address")
	private String formattedAddress = null;
	@XmlElement(name="address_component")
	private List<GeocodeAddressComponent> addressComponents = null;
	@XmlElement(name="geometry",required=true)
	private GeocodeGeometry geometry = null;
	
	public List<GeocodeResultType> getTypes() {
		return types;
	}
	public void setTypes(List<GeocodeResultType> types) {
		this.types = types;
	}
	public String getFormattedAddress() {
		return formattedAddress;
	}
	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}
	public List<GeocodeAddressComponent> getAddressComponents() {
		return addressComponents;
	}
	public void setAddressComponents(List<GeocodeAddressComponent> addressComponents) {
		this.addressComponents = addressComponents;
	}
	public GeocodeGeometry getGeometry() {
		return geometry;
	}
	public void setGeometry(GeocodeGeometry geometry) {
		this.geometry = geometry;
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	@Override
	public boolean equals(Object obj) {
	   return EqualsBuilder.reflectionEquals(this, obj);
	}
	@Override
	public String toString() {
	   return ToStringBuilder.reflectionToString(this);
	}
}