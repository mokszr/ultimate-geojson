package com.erumi.ugeojson.parser.deserializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.erumi.ugeojson.model.PositionDto;
import com.erumi.ugeojson.model.geometry.LineStringDto;
import com.erumi.ugeojson.model.geometry.MultiLineStringDto;
import com.erumi.ugeojson.parser.util.BoundingBoxParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

/**
 * Deserializer for MultiLineString
 * 
 * @author moksuzer
 *
 */
public class MultiLineStringDeserializer implements JsonDeserializer<MultiLineStringDto> {

	@Override
	public MultiLineStringDto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {

		MultiLineStringDto dto = new MultiLineStringDto();
		List<LineStringDto> lines = new ArrayList<>();
		dto.setLines(lines);

		JsonObject asJsonObject = json.getAsJsonObject();
		JsonArray jsonArray = asJsonObject.get("coordinates").getAsJsonArray();
		Type positionCollectionType = new TypeToken<List<PositionDto>>() {
		}.getType();
		for (int i = 0; i < jsonArray.size(); i++) {
			List<PositionDto> positions = context.deserialize(jsonArray.get(i), positionCollectionType);
			LineStringDto line = new LineStringDto();
			line.setPositions(positions);
			lines.add(line);
		}

		dto.setBbox(BoundingBoxParser.parseBbox(asJsonObject, context));
		
		return dto;
	}

}
