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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GeocodeInvoker implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(GeocodeInvoker.class);
	
	private Long warningLimit = 10000L;
	private String address = null;
	private String latlng = null;
	private String bounds = null;
	private String region = null;
	private String language = null;
	private Boolean sensor = null;
	
	public Long getWarningLimit() {
		return warningLimit;
	}
	public void setWarningLimit(Long warningLimit) {
		this.warningLimit = warningLimit;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLatlng() {
		return latlng;
	}
	public void setLatlng(String latlng) {
		this.latlng = latlng;
	}
	public String getBounds() {
		return bounds;
	}
	public void setBounds(String bounds) {
		this.bounds = bounds;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public Boolean getSensor() {
		return sensor;
	}
	public void setSensor(Boolean sensor) {
		this.sensor = sensor;
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
	
	public GeocodeResponse geocode(GeocodeClient client) {
		GeocodeResponse response;
		
		Long start = System.currentTimeMillis();

		response = client.geocode(getAddress(), getLatlng(), getBounds(), getRegion(), getLanguage(), getSensor());
		
		Long end = System.currentTimeMillis();
		Long diff = end - start;
		
		if (log.isTraceEnabled()) {
			log.trace(String.format("Google Maps geocode took %s milliseconds", diff));
		} else if (log.isWarnEnabled() && diff > getWarningLimit()) {
			log.warn(String.format("Google Maps geocode took %s milliseconds which is longer than the threshold %s milliseconds", diff, getWarningLimit()));
		}
		
		return response;
		
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder implements Serializable {
		private static final long serialVersionUID = 1L;
		
		private GeocodeInvoker instance = new GeocodeInvoker();
		private Builder() { }
		
		public Builder warningLimit(Long warningLimit) {
			instance.setWarningLimit(warningLimit);
			return this;
		}
		
		public Builder address(String address) {
			instance.setAddress(address);
			return this;
		}
		
		public Builder latlng(Double latitude, Double longitude) {
			instance.setLatlng(latitude + "," + longitude);
			return this;
		}
		
		public Builder bounds(Double southwestLatitude, Double southwestLongitude, Double northeastLatitude, Double northeastLongitude) {
			instance.setBounds(southwestLatitude + "," + southwestLongitude + "|" + northeastLatitude + "," + northeastLongitude);
			return this;
		}
		
		public Builder region(String region) {
			instance.setRegion(region);
			return this;
		}
		
		public Builder sensor(Boolean sensor) {
			instance.setSensor(sensor);
			return this;
		}
		
		public GeocodeInvoker build() {
			return instance;
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
}