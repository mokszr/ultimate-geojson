package com.erumi.ugeojson.parser.deserializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.erumi.ugeojson.model.PositionDto;
import com.erumi.ugeojson.model.geometry.LineStringDto;
import com.erumi.ugeojson.model.geometry.PolygonDto;
import com.erumi.ugeojson.parser.util.BoundingBoxParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

/**
 * Deserializer for Polygon
 * 
 * @author moksuzer
 *
 */
public class PolygonDeserializer implements JsonDeserializer<PolygonDto> {

	@Override
	public PolygonDto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {

		PolygonDto dto = new PolygonDto();
		List<LineStringDto> rings = new ArrayList<>();
		dto.setLinearRings(rings);

		JsonObject asJsonObject = json.getAsJsonObject();
		JsonArray jsonArray = asJsonObject.get("coordinates").getAsJsonArray();
		Type positionCollectionType = new TypeToken<List<PositionDto>>() {
		}.getType();
		for (int i = 0; i < jsonArray.size(); i++) {
			List<PositionDto> positions = context.deserialize(jsonArray.get(i), positionCollectionType);
			LineStringDto ring = new LineStringDto();
			ring.setPositions(positions);
			rings.add(ring);
		}
		
		dto.setBbox(BoundingBoxParser.parseBbox(asJsonObject, context));
		
		return dto;
	}

}
