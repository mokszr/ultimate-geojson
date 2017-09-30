package com.erumi.ugeojson.builder;

import com.erumi.ugeojson.builder.common.BuilderConstants;
import com.erumi.ugeojson.builder.feature.FeatureBuilder;
import com.erumi.ugeojson.builder.feature.FeatureCollectionBuilder;
import com.erumi.ugeojson.builder.geometry.GeometryCollectionBuilder;
import com.erumi.ugeojson.builder.geometry.LineStringBuilder;
import com.erumi.ugeojson.builder.geometry.MultiLineStringBuilder;
import com.erumi.ugeojson.builder.geometry.MultiPointBuilder;
import com.erumi.ugeojson.builder.geometry.MultiPolygonBuilder;
import com.erumi.ugeojson.builder.geometry.PointBuilder;
import com.erumi.ugeojson.builder.geometry.PolygonBuilder;
import com.erumi.ugeojson.model.GeoJSONObjectDto;
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
 * @author moksuzer
 *
 */
public class UltimateGeoJSONBuilder {

	public static String toGeoJSON(GeoJSONObjectDto geoJsonObjectDto) {
		if (geoJsonObjectDto == null) {
			return BuilderConstants.NULL_VALUE;
		}

		if (geoJsonObjectDto instanceof PointDto) {
			return PointBuilder.getInstance().toGeoJSON((PointDto) geoJsonObjectDto);
		}

		if (geoJsonObjectDto instanceof LineStringDto) {
			return LineStringBuilder.getInstance().toGeoJSON((LineStringDto) geoJsonObjectDto);
		}

		if (geoJsonObjectDto instanceof PolygonDto) {
			return PolygonBuilder.getInstance().toGeoJSON((PolygonDto) geoJsonObjectDto);
		}

		if (geoJsonObjectDto instanceof FeatureDto) {
			return FeatureBuilder.getInstance().toGeoJSON((FeatureDto) geoJsonObjectDto);
		}

		if (geoJsonObjectDto instanceof FeatureCollectionDto) {
			return FeatureCollectionBuilder.getInstance().toGeoJSON((FeatureCollectionDto) geoJsonObjectDto);
		}

		if (geoJsonObjectDto instanceof MultiPointDto) {
			return MultiPointBuilder.getInstance().toGeoJSON((MultiPointDto) geoJsonObjectDto);
		}

		if (geoJsonObjectDto instanceof MultiLineStringDto) {
			return MultiLineStringBuilder.getInstance().toGeoJSON((MultiLineStringDto) geoJsonObjectDto);
		}

		if (geoJsonObjectDto instanceof MultiPolygonDto) {
			return MultiPolygonBuilder.getInstance().toGeoJSON((MultiPolygonDto) geoJsonObjectDto);
		}

		if (geoJsonObjectDto instanceof GeometryCollectionDto) {
			return GeometryCollectionBuilder.getInstance().toGeoJSON((GeometryCollectionDto) geoJsonObjectDto);
		}

		return BuilderConstants.NULL_VALUE;
	}

}
