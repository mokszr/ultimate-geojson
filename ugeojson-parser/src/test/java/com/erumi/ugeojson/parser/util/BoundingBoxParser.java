package com.erumi.ugeojson.parser.util;

import com.erumi.ugeojson.model.BoundingBoxDto;
import com.erumi.ugeojson.model.PositionDto;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * @author moksuzer
 *
 */
public class BoundingBoxParser {

	private static final int TWO_DIMENSIONAL_BBOX_LENGTH = 4;

	private BoundingBoxParser() {
	}

	public static BoundingBoxDto parseBbox(JsonObject asJsonObject, JsonDeserializationContext context) {
		BoundingBoxDto bboxDto = null;
		JsonElement bboxElement = asJsonObject.get("bbox");
		if (bboxElement != null) {
			double[] bbox = context.deserialize(bboxElement, double[].class);
			bboxDto = new BoundingBoxDto();
			if (bbox.length == TWO_DIMENSIONAL_BBOX_LENGTH) {
				bboxDto.setSouthWestCorner(new PositionDto(bbox[0], bbox[1]));
				bboxDto.setNorthEastCorner(new PositionDto(bbox[2], bbox[3]));
			} else {
				bboxDto.setSouthWestCorner(new PositionDto(bbox[0], bbox[1], bbox[2]));
				bboxDto.setNorthEastCorner(new PositionDto(bbox[3], bbox[4], bbox[5]));
			}

		}
		return bboxDto;
	}

}
