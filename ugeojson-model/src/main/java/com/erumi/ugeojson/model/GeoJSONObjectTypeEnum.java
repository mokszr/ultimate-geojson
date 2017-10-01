package com.erumi.ugeojson.model;

import com.erumi.ugeojson.model.feature.FeatureCollectionDto;
import com.erumi.ugeojson.model.feature.FeatureDto;
import com.erumi.ugeojson.model.geometry.GeometryCollectionDto;
import com.erumi.ugeojson.model.geometry.LineStringDto;
import com.erumi.ugeojson.model.geometry.MultiLineStringDto;
import com.erumi.ugeojson.model.geometry.MultiPointDto;
import com.erumi.ugeojson.model.geometry.MultiPolygonDto;
import com.erumi.ugeojson.model.geometry.PointDto;
import com.erumi.ugeojson.model.geometry.PolygonDto;

/**
 * GeoJSON object types
 * 
 * @author moksuzer
 *
 */
public enum GeoJSONObjectTypeEnum {

	Point(PointDto.class), MultiPoint(MultiPointDto.class), LineString(LineStringDto.class), MultiLineString(
			MultiLineStringDto.class), Polygon(PolygonDto.class), MultiPolygon(
					MultiPolygonDto.class), GeometryCollection(GeometryCollectionDto.class), Feature(
							FeatureDto.class), FeatureCollection(FeatureCollectionDto.class);

	private final Class dtoClass;

	private GeoJSONObjectTypeEnum(Class dtoClass) {
		this.dtoClass = dtoClass;
	}

	public Class getDtoClass() {
		return dtoClass;
	}
}
