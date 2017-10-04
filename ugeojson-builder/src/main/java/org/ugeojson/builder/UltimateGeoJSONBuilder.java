package org.ugeojson.builder;

import org.ugeojson.builder.common.BuilderConstants;
import org.ugeojson.builder.feature.FeatureBuilder;
import org.ugeojson.builder.feature.FeatureCollectionBuilder;
import org.ugeojson.builder.geometry.GeometryCollectionBuilder;
import org.ugeojson.builder.geometry.LineStringBuilder;
import org.ugeojson.builder.geometry.MultiLineStringBuilder;
import org.ugeojson.builder.geometry.MultiPointBuilder;
import org.ugeojson.builder.geometry.MultiPolygonBuilder;
import org.ugeojson.builder.geometry.PointBuilder;
import org.ugeojson.builder.geometry.PolygonBuilder;
import org.ugeojson.model.GeoJSONObjectDto;
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
 * @author moksuzer
 *
 */
public class UltimateGeoJSONBuilder {

	private static final UltimateGeoJSONBuilder INSTANCE = new UltimateGeoJSONBuilder();

	public static UltimateGeoJSONBuilder getInstance() {
		return INSTANCE;
	}

	private UltimateGeoJSONBuilder() {
	}

	public String toGeoJSON(GeoJSONObjectDto geoJsonObjectDto) {
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
