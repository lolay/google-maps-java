package com.lolay.google.geocode;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class GeocodeAddressComponentTypeAdapter extends XmlAdapter<String,GeocodeAddressComponentType> {
	@Override
	public GeocodeAddressComponentType unmarshal(String value) throws Exception {
		if (value == null || value.isEmpty()) {
			return null;
		}
		
		return GeocodeAddressComponentType.valueOfIgnoreCase(value);
	}

	@Override
	public String marshal(GeocodeAddressComponentType value) throws Exception {
		if (value == null) {
			return null;
		}
		
		return value.toString().toLowerCase();
	}
}