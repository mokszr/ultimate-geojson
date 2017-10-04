package org.ugeojson.model;

import java.io.Serializable;

/**
 * The fundamental geometry construct
 * 
 * @author moksuzer
 *
 */
public class PositionDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private double[] numbers;

	/**
	 * This constructor initializes numbers field to hold two double numbers. Do
	 * not use this constructor if you need the third elevation parameter.
	 */
	public PositionDto() {
		this.numbers = new double[2];
	}

	/**
	 * Simple coordinate position constructor
	 * 
	 * @param longitude
	 * @param latitude
	 */
	public PositionDto(double longitude, double latitude) {
		this.numbers = new double[] { longitude, latitude };
	}

	/**
	 * Use this constructor if you need elevation parameter
	 * 
	 * @param longitude
	 * @param latitude
	 * @param elevation
	 */
	public PositionDto(double longitude, double latitude, double elevation) {
		this.numbers = new double[] { longitude, latitude, elevation };
	}

	/**
	 * Copy constructor
	 * 
	 * @param position
	 */
	public PositionDto(PositionDto position) {
		if (position != null) {
			double[] copyNumbers = position.getNumbers();
			this.numbers = new double[copyNumbers.length];
			for (int i = 0; i < copyNumbers.length; i++) {
				this.numbers[i] = copyNumbers[i];
			}
		}
	}

	@Override
	public String toString() {
		if (numbers == null) {
			return "null numbers[]";
		}
		StringBuilder value = new StringBuilder("[");
		for (int i = 0; i < numbers.length; i++) {
			value.append(numbers[i]);
			if (i < numbers.length - 1) {
				value.append(", ");
			}
		}
		value.append("]");
		return value.toString();
	}

	public void setLongitude(double longitude) {
		this.numbers[0] = longitude;
	}

	public double getLongitude() {
		return numbers[0];
	}

	public void setLatitude(double latitude) {
		this.numbers[1] = latitude;
	}

	public double getLatitude() {
		return numbers[1];
	}

	public void setElevation(double elevation) {
		this.numbers[2] = elevation;
	}

	public double getElevation() {
		return numbers[2];
	}

	public double[] getNumbers() {
		return numbers;
	}

	public void setNumbers(double[] numbers) {
		this.numbers = numbers;
	}
}
