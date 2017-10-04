package org.ugeojson.parser.deserializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.ugeojson.model.PositionDto;
import org.ugeojson.model.geometry.MultiPointDto;
import org.ugeojson.parser.util.BoundingBoxParser;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Deserializer for MultiPoint
 * 
 * @author moksuzer
 *
 */
public class MultiPointDeserializer implements JsonDeserializer<MultiPointDto> {

	@Override
	public MultiPointDto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {

		MultiPointDto dto = new MultiPointDto();
		List<PositionDto> positions = new ArrayList<>();
		dto.setPositions(positions);

		JsonObject asJsonObject = json.getAsJsonObject();
		JsonArray jsonArray = asJsonObject.get("coordinates").getAsJsonArray();
		for (int i = 0; i < jsonArray.size(); i++) {
			PositionDto position = context.deserialize(jsonArray.get(i), PositionDto.class);
			positions.add(position);
		}

		dto.setBbox(BoundingBoxParser.parseBbox(asJsonObject, context));

		return dto;
	}

}
