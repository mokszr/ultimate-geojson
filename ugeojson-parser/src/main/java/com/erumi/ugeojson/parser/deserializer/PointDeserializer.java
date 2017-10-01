package com.erumi.ugeojson.parser.deserializer;

import java.lang.reflect.Type;

import com.erumi.ugeojson.model.PositionDto;
import com.erumi.ugeojson.model.geometry.PointDto;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Deserializer for Point
 * 
 * @author moksuzer
 *
 */
public class PointDeserializer implements JsonDeserializer<PointDto> {

	@Override
	public PointDto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {

		PointDto point = new PointDto();

		JsonObject asJsonObject = json.getAsJsonObject();
		JsonElement jsonElement = asJsonObject.get("coordinates");
		PositionDto positionDto = context.deserialize(jsonElement, PositionDto.class);
		point.setPosition(positionDto);

		JsonElement bboxElement = asJsonObject.get("bbox");

		return point;
	}

}
