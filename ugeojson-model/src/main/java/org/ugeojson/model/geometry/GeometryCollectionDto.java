package org.ugeojson.model.geometry;

import java.util.List;

import org.ugeojson.model.GeoJSONObjectTypeEnum;

/**
 * @author moksuzer
 *
 */
public class GeometryCollectionDto extends GeometryDto {

	private static final long serialVersionUID = 1L;

	private List<GeometryDto> geometries;

	public List<GeometryDto> getGeometries() {
		return geometries;
	}

	public void setGeometries(List<GeometryDto> geometries) {
		this.geometries = geometries;
	}

	@Override
	public GeoJSONObjectTypeEnum getGeoJSONObjectType() {
		return GeoJSONObjectTypeEnum.GeometryCollection;
	}
}
