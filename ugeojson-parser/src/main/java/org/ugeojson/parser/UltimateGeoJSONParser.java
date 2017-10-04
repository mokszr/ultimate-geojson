package org.ugeojson.parser;

import org.ugeojson.model.GeoJSONObjectDto;
import org.ugeojson.model.GeoJSONObjectTypeEnum;
import org.ugeojson.model.PositionDto;
import org.ugeojson.model.feature.FeatureCollectionDto;
import org.ugeojson.model.feature.FeatureDto;
import org.ugeojson.model.geometry.GeometryCollectionDto;
import org.ugeojson.model.geometry.LineStringDto;
import org.ugeojson.model.geometry.MultiLineStringDto;
import org.ugeojson.model.geometry.MultiPointDto;
import org.ugeojson.model.geometry.MultiPolygonDto;
import org.ugeojson.model.geometry.PointDto;
import org.ugeojson.model.geometry.PolygonDto;
import org.ugeojson.parser.deserializer.FeatureCollectionDeserializer;
import org.ugeojson.parser.deserializer.FeatureDeserializer;
import org.ugeojson.parser.deserializer.GeometryCollectionDeserializer;
import org.ugeojson.parser.deserializer.LineStringDeserializer;
import org.ugeojson.parser.deserializer.MultiLineStringDeserializer;
import org.ugeojson.parser.deserializer.MultiPointDeserializer;
import org.ugeojson.parser.deserializer.MultiPolygonDeserializer;
import org.ugeojson.parser.deserializer.PointDeserializer;
import org.ugeojson.parser.deserializer.PolygonDeserializer;
import org.ugeojson.parser.deserializer.PositionDeserializer;

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
		gsonBuilder.registerTypeAdapter(GeometryCollectionDto.class, new GeometryCollectionDeserializer());
		gsonBuilder.registerTypeAdapter(FeatureDto.class, new FeatureDeserializer());
		gsonBuilder.registerTypeAdapter(FeatureCollectionDto.class, new FeatureCollectionDeserializer());
		gson = gsonBuilder.create();
	}

}
