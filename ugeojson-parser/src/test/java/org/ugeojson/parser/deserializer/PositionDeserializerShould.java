package org.ugeojson.parser.deserializer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Type;
import java.util.List;

import org.junit.Test;
import org.ugeojson.model.PositionDto;
import org.ugeojson.parser.deserializer.PositionDeserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class PositionDeserializerShould {

	@Test public void
	deserialize_position(){
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(PositionDto.class, new PositionDeserializer());
		Gson gson = gsonBuilder.create();
		 
		PositionDto positionDto = gson.fromJson(" [14.258,   \n47.141, 0.7890780] ", PositionDto.class);
		assertThat(positionDto.getLongitude(), equalTo(14.258));
		assertThat(positionDto.getLatitude(), equalTo(47.141));
		assertThat(positionDto.getElevation(), equalTo(0.7890780));
	}
	
	@Test public void
	deserialize_position_list(){
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(PositionDto.class, new PositionDeserializer());
		Gson gson = gsonBuilder.create();
		
		Type collectionType = new TypeToken<List<PositionDto>>(){}.getType();
		List<PositionDto> positionList = gson.fromJson("[ [14.244158,   \n47.149861, 0.7890780] ]", collectionType);
		assertThat(positionList.size(), equalTo(1));
		assertThat(positionList.get(0).getLongitude(), equalTo(14.244158));
		assertThat(positionList.get(0).getLatitude(), equalTo(47.149861));
		assertThat(positionList.get(0).getElevation(), equalTo(0.7890780));
	}
	
	@Test public void
	deserialize_bbox(){
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		
		double[] bbox = gson.fromJson("[-10.0, -10.0, 10.0, 10.0]", double[].class);

		assertThat(bbox[0], equalTo(-10.0));
		assertThat(bbox[2], equalTo(10.0));
		assertThat(bbox[3], equalTo(10.0));
	}
	
	
}
