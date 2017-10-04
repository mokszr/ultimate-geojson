package org.ugeojson.model;

import org.ugeojson.model.feature.FeatureCollectionDto;
import org.ugeojson.model.feature.FeatureDto;
import org.ugeojson.model.geometry.GeometryCollectionDto;
import org.ugeojson.model.geometry.LineStringDto;
import org.ugeojson.model.geometry.MultiLineStringDto;
import org.ugeojson.model.geometry.MultiPointDto;
import org.ugeojson.model.geometry.MultiPolygonDto;
import org.ugeojson.model.geometry.PointDto;
import org.ugeojson.model.geometry.PolygonDto;

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
