package com.erumi.ugeojson.parser.deserializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.erumi.ugeojson.model.PositionDto;
import com.erumi.ugeojson.model.geometry.LineStringDto;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Deserializer for LineString
 * 
 * @author moksuzer
 *
 */
public class LineStringDeserializer implements JsonDeserializer<LineStringDto> {

	@Override
	public LineStringDto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {

		LineStringDto dto = new LineStringDto();
		List<PositionDto> positions = new ArrayList<>();
		dto.setPositions(positions);

		JsonObject asJsonObject = json.getAsJsonObject();
		JsonArray jsonArray = asJsonObject.get("coordinates").getAsJsonArray();
		for (int i = 0; i < jsonArray.size(); i++) {
			PositionDto position = context.deserialize(jsonArray.get(i), PositionDto.class);
			positions.add(position);
		}

		return dto;
	}

}
