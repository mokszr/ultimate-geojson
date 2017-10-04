package org.ugeojson.model.geometry;

import java.util.List;

import org.ugeojson.model.GeoJSONObjectTypeEnum;
import org.ugeojson.model.PositionDto;

/**
 * @author moksuzer
 *
 */
public class MultiPointDto extends GeometryDto {

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
