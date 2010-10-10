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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@XmlRootElement(name="geometry")
@XmlAccessorType(value=XmlAccessType.FIELD)
public class GeocodeGeometry implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name="location",required=true)
	private GeocodeLocation location = null;
	@XmlElement(name="location_type",required=true)
	private GeocodeLocationType locationType = null;
	@XmlElement(name="viewport",required=true)
	private GeocodeFrame viewPort = null;
	@XmlElement(name="bounds")
	private GeocodeFrame bounds = null;

	public GeocodeLocation getLocation() {
		return location;
	}
	public void setLocation(GeocodeLocation location) {
		this.location = location;
	}
	public GeocodeLocationType getLocationType() {
		return locationType;
	}
	public void setLocationType(GeocodeLocationType locationType) {
		this.locationType = locationType;
	}
	public GeocodeFrame getViewPort() {
		return viewPort;
	}
	public void setViewPort(GeocodeFrame viewPort) {
		this.viewPort = viewPort;
	}
	public GeocodeFrame getBounds() {
		return bounds;
	}
	public void setBounds(GeocodeFrame bounds) {
		this.bounds = bounds;
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