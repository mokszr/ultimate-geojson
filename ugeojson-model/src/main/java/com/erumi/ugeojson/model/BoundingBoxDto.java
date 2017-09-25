package com.erumi.ugeojson.model;

import java.io.Serializable;

/**
 * @author moksuzer
 *
 */
public class BoundingBoxDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private PositionDto southWestCorner;
	private PositionDto northEastCorner;

	public PositionDto getSouthWestCorner() {
		return southWestCorner;
	}

	public void setSouthWestCorner(PositionDto southWestCorner) {
		this.southWestCorner = southWestCorner;
	}

	public PositionDto getNorthEastCorner() {
		return northEastCorner;
	}

	public void setNorthEastCorner(PositionDto northEastCorner) {
		this.northEastCorner = northEastCorner;
	}

}
