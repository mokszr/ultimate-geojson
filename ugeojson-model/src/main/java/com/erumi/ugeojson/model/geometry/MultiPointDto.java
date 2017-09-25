package com.erumi.ugeojson.model.geometry;

import java.util.List;

import com.erumi.ugeojson.model.GeoJSONObjectDto;
import com.erumi.ugeojson.model.GeoJSONObjectTypeEnum;
import com.erumi.ugeojson.model.PositionDto;

/**
 * @author moksuzer
 *
 */
public class MultiPointDto extends GeoJSONObjectDto implements GeometryDto {

	private static final long serialVersionUID = 1L;

	private List<PositionDto> positions;
 
	public List<PositionDto> getPositions() {
		return positions;
	}

	public void setPositions(List<PositionDto> positions) {
		this.positions = positions;
	}

	@Override
	public GeoJSONObjectTypeEnum getGeoJSONObjectType() {
		return GeoJSONObjectTypeEnum.MultiPoint;
	}
}
