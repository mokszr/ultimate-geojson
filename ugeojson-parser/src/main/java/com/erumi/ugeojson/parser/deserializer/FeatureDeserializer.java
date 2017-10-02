package com.erumi.ugeojson.parser.deserializer;

import java.lang.reflect.Type;

import com.erumi.ugeojson.model.GeoJSONObjectTypeEnum;
import com.erumi.ugeojson.model.feature.FeatureDto;
import com.erumi.ugeojson.model.geometry.GeometryDto;
import com.erumi.ugeojson.parser.util.BoundingBoxParser;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Deserializer for Feature
 * 
 * @author moksuzer
 *
 */
public class FeatureDeserializer implements JsonDeserializer<FeatureDto> {

	@Override
	public FeatureDto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {

		FeatureDto dto = new FeatureDto();

		JsonObject asJsonObject = json.getAsJsonObject();
		JsonElement geometryElement = asJsonObject.get("geometry");
		if (geometryElement != null) {
			String typeOfGeometry = geometryElement.getAsJsonObject().get("type").getAsString();
			GeoJSONObjectTypeEnum typeEnum = GeoJSONObjectTypeEnum.valueOf(typeOfGeometry);
			GeometryDto geometryDto = context.deserialize(geometryElement, typeEnum.getDtoClass());
			dto.setGeometry(geometryDto);

		}

		JsonElement propertiesJsonElement = asJsonObject.get("properties");
		if (propertiesJsonElement != null) {
			dto.setProperties(propertiesJsonElement.toString());
		}

		JsonElement idJsonElement = asJsonObject.get("id");
		if (idJsonElement != null) {
			dto.setId(idJsonElement.getAsString());
		}

		dto.setBbox(BoundingBoxParser.parseBbox(asJsonObject, context));

		return dto;
	}

}
