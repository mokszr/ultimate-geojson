package com.erumi.ugeojson.model.feature;

import java.util.List;

import com.erumi.ugeojson.model.GeoJSONObjectDto;
import com.erumi.ugeojson.model.GeoJSONObjectTypeEnum;

/**
 * @author moksuzer
 *
 */
public class FeatureCollectionDto extends GeoJSONObjectDto {
 
	private static final long serialVersionUID = 1L;
	
	private List<FeatureDto> features;

	@Override
	public GeoJSONObjectTypeEnum getGeoJSONObjectType() {
		return GeoJSONObjectTypeEnum.FeatureCollection;
	}

	public List<FeatureDto> getFeatures() {
		return features;
	}

	public void setFeatures(List<FeatureDto> features) {
		this.features = features;
	}
	
}
