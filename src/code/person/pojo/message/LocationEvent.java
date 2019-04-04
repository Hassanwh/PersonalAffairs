package code.person.pojo.message;

import java.io.Serializable;


/**
 * Description:
 * <br/>Copyright (C), 2001-2011, 
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:	2014-10-23
 * @author  ldh
 * @version  2.0
 */
public class LocationEvent extends InMessage implements Serializable
{

	private double latitude;
	private double longitude;
	private double precision;
	
	
	// Constructors
	public LocationEvent() {
	}	

	public double getLatitude() {
		return this.latitude;
	}

	/*
	 * @param double latitude (中文含意：地理位置纬度;　数据存储类型：DECIMAL(9,6))
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	/*
	 * @param double longitude (中文含意：地理位置经度;　数据存储类型：DECIMAL(9,6))
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getPrecision() {
		return this.precision;
	}

	/*
	 * @param double precision (中文含意：地理位置精度;　数据存储类型：DECIMAL(9,6))
	 */
	public void setPrecision(double precision) {
		this.precision = precision;
	}


	
}