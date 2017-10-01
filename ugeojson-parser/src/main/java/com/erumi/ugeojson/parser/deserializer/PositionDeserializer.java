package com.erumi.ugeojson.parser.deserializer;

import java.lang.reflect.Type;

import com.erumi.ugeojson.model.PositionDto;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

/**
 * Deserializer for position element
 * 
 * @author moksuzer
 *
 */
public class PositionDeserializer implements JsonDeserializer<PositionDto> {

	@Override
	public PositionDto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {

		JsonArray asJsonArray = json.getAsJsonArray();
		double[] numbers = new double[asJsonArray.size()];
		for (int i = 0; i < asJsonArray.size(); i++) {
			numbers[i] = asJsonArray.get(i).getAsDouble();
		}

		PositionDto positionDto = new PositionDto();
		positionDto.setNumbers(numbers);
		return positionDto;
	}

}
