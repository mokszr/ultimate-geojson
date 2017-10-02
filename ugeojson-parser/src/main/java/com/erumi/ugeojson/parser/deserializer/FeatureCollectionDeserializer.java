package com.erumi.ugeojson.parser.deserializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.erumi.ugeojson.model.feature.FeatureCollectionDto;
import com.erumi.ugeojson.model.feature.FeatureDto;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Deserializer for FeatureCollection
 * 
 * @author moksuzer
 *
 */
public class FeatureCollectionDeserializer implements JsonDeserializer<FeatureCollectionDto> {

	@Override
	public FeatureCollectionDto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {

		FeatureCollectionDto dto = new FeatureCollectionDto();
		List<FeatureDto> features = new ArrayList<>();
		dto.setFeatures(features);

		JsonObject asJsonObject = json.getAsJsonObject();
		JsonArray jsonArray = asJsonObject.get("features").getAsJsonArray();
		if (jsonArray == null) {
			return dto;
		}

		for (int i = 0; i < jsonArray.size(); i++) {
			JsonObject featureElement = jsonArray.get(i).getAsJsonObject();
			FeatureDto geometryDto = context.deserialize(featureElement, FeatureDto.class);
			features.add(geometryDto);
		}

		return dto;
	}

}
