package com.erumi.ugeojson.parser.deserializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.erumi.ugeojson.model.GeoJSONObjectTypeEnum;
import com.erumi.ugeojson.model.geometry.GeometryCollectionDto;
import com.erumi.ugeojson.model.geometry.GeometryDto;
import com.erumi.ugeojson.parser.util.BoundingBoxParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Deserializer for GeometryCollection
 * 
 * @author moksuzer
 *
 */
public class GeometryCollectionDeserializer implements JsonDeserializer<GeometryCollectionDto> {

	@Override
	public GeometryCollectionDto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {

		GeometryCollectionDto dto = new GeometryCollectionDto();
		List<GeometryDto> geometries = new ArrayList<>();
		dto.setGeometries(geometries);

		JsonObject asJsonObject = json.getAsJsonObject();
		JsonArray jsonArray = asJsonObject.get("geometries").getAsJsonArray();
		if (jsonArray == null) {
			return dto;
		}

		for (int i = 0; i < jsonArray.size(); i++) {
			JsonObject geometryElement = jsonArray.get(i).getAsJsonObject();
			String typeOfGeometry = geometryElement.get("type").getAsString();
			GeoJSONObjectTypeEnum typeEnum = GeoJSONObjectTypeEnum.valueOf(typeOfGeometry);

			GeometryDto geometryDto = context.deserialize(geometryElement, typeEnum.getDtoClass());
			geometries.add(geometryDto);
		}

		dto.setBbox(BoundingBoxParser.parseBbox(asJsonObject, context));

		return dto;
	}

}
