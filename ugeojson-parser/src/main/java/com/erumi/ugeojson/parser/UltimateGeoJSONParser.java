package com.erumi.ugeojson.parser;

import com.erumi.ugeojson.model.GeoJSONObjectDto;
import com.erumi.ugeojson.model.GeoJSONObjectTypeEnum;
import com.erumi.ugeojson.model.PositionDto;
import com.erumi.ugeojson.model.feature.FeatureCollectionDto;
import com.erumi.ugeojson.model.feature.FeatureDto;
import com.erumi.ugeojson.model.geometry.LineStringDto;
import com.erumi.ugeojson.model.geometry.MultiLineStringDto;
import com.erumi.ugeojson.model.geometry.MultiPointDto;
import com.erumi.ugeojson.model.geometry.MultiPolygonDto;
import com.erumi.ugeojson.model.geometry.PointDto;
import com.erumi.ugeojson.model.geometry.PolygonDto;
import com.erumi.ugeojson.parser.deserializer.FeatureCollectionDeserializer;
import com.erumi.ugeojson.parser.deserializer.FeatureDeserializer;
import com.erumi.ugeojson.parser.deserializer.LineStringDeserializer;
import com.erumi.ugeojson.parser.deserializer.MultiLineStringDeserializer;
import com.erumi.ugeojson.parser.deserializer.MultiPointDeserializer;
import com.erumi.ugeojson.parser.deserializer.MultiPolygonDeserializer;
import com.erumi.ugeojson.parser.deserializer.PointDeserializer;
import com.erumi.ugeojson.parser.deserializer.PolygonDeserializer;
import com.erumi.ugeojson.parser.deserializer.PositionDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @author moksuzer
 *
 */
public class UltimateGeoJSONParser {

	private Gson gson;

	private static final UltimateGeoJSONParser INSTANCE = new UltimateGeoJSONParser();

	private UltimateGeoJSONParser() {
		initialize();
	}

	public static UltimateGeoJSONParser getInstance() {
		return INSTANCE;
	}

	public GeoJSONObjectDto parse(String geoJson) {
		if (geoJson == null) {
			return null;
		}

		JsonParser parser = new JsonParser();
		JsonElement parsed = parser.parse(geoJson);
		JsonObject jsonObject = parsed.getAsJsonObject();
		String typeString = jsonObject.get("type").getAsString();
		GeoJSONObjectTypeEnum typeEnum = GeoJSONObjectTypeEnum.valueOf(typeString);
		return (GeoJSONObjectDto) gson.fromJson(parsed, typeEnum.getDtoClass());
	}

	private void initialize() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(PositionDto.class, new PositionDeserializer());
		gsonBuilder.registerTypeAdapter(PointDto.class, new PointDeserializer());
		gsonBuilder.registerTypeAdapter(LineStringDto.class, new LineStringDeserializer());
		gsonBuilder.registerTypeAdapter(PolygonDto.class, new PolygonDeserializer());
		gsonBuilder.registerTypeAdapter(MultiPointDto.class, new MultiPointDeserializer());
		gsonBuilder.registerTypeAdapter(MultiLineStringDto.class, new MultiLineStringDeserializer());
		gsonBuilder.registerTypeAdapter(MultiPolygonDto.class, new MultiPolygonDeserializer());
		gsonBuilder.registerTypeAdapter(FeatureDto.class, new FeatureDeserializer());
		gsonBuilder.registerTypeAdapter(FeatureCollectionDto.class, new FeatureCollectionDeserializer());
		gson = gsonBuilder.create();
	}

}
