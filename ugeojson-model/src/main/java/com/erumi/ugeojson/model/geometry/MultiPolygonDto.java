package com.erumi.ugeojson.model.geometry;

import java.util.List;

import com.erumi.ugeojson.model.GeoJSONObjectDto;
import com.erumi.ugeojson.model.GeoJSONObjectTypeEnum;

/**
 * @author moksuzer
 *
 */
public class MultiPolygonDto extends GeoJSONObjectDto implements GeometryDto {

	private static final long serialVersionUID = 1L;

	private List<PolygonDto> polygons;

	public List<PolygonDto> getPolygons() {
		return polygons;
	}

	public void setPolygons(List<PolygonDto> polygons) {
		this.polygons = polygons;
	}

	@Override
	public GeoJSONObjectTypeEnum getGeoJSONObjectType() {
		return GeoJSONObjectTypeEnum.MultiPolygon;
	}
}
