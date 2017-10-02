package com.erumi.ugeojson.parser.deserializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.erumi.ugeojson.model.PositionDto;
import com.erumi.ugeojson.model.geometry.LineStringDto;
import com.erumi.ugeojson.model.geometry.MultiPolygonDto;
import com.erumi.ugeojson.model.geometry.PolygonDto;
import com.erumi.ugeojson.parser.util.BoundingBoxParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

/**
 * Deserializer for MultiPolygon
 * 
 * @author moksuzer
 *
 */
public class MultiPolygonDeserializer implements JsonDeserializer<MultiPolygonDto> {

	@Override
	public MultiPolygonDto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {

		MultiPolygonDto dto = new MultiPolygonDto();
		List<PolygonDto> polygons = new ArrayList<>();
		dto.setPolygons(polygons);

		JsonObject asJsonObject = json.getAsJsonObject();
		JsonArray jsonArray = asJsonObject.get("coordinates").getAsJsonArray();
		Type positionCollectionType = new TypeToken<List<PositionDto>>() {
		}.getType();
		for (int i = 0; i < jsonArray.size(); i++) {
			PolygonDto polygonDto = new PolygonDto();
			List<LineStringDto> rings = new ArrayList<>();
			polygonDto.setLinearRings(rings);
			JsonArray jsonPolygonElement = jsonArray.get(i).getAsJsonArray();
			for (int j = 0; j < jsonPolygonElement.size(); j++) {
				List<PositionDto> positions = context.deserialize(jsonPolygonElement.get(j), positionCollectionType);
				LineStringDto ring = new LineStringDto();
				ring.setPositions(positions);
				rings.add(ring);
			}

			polygons.add(polygonDto);
		}

		dto.setBbox(BoundingBoxParser.parseBbox(asJsonObject, context));
		
		return dto;
	}

}
