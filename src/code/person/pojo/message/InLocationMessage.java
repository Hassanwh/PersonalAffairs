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
public class InLocationMessage  extends InMessage implements Serializable
{

	// Fields
	private long msgId;
	private double location_X;
	private double location_Y;
	private Integer scale;
	private String label;
	
	
	// Constructors
	public InLocationMessage() {
	}	

	// Property accessors	
	public long getMsgId() {
		return this.msgId;
	}

	/*
	 * @param Long msgId (中文含意：消息ID;　数据存储类型：未知)
	 */
	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}

	public double getLocation_X() {
		return this.location_X;
	}

	/*
	 * @param double location_X (中文含意：地理位置纬度;　数据存储类型：DECIMAL(9,6))
	 */
	public void setLocation_X(double location_X) {
		this.location_X = location_X;
	}

	public double getLocation_Y() {
		return this.location_Y;
	}

	/*
	 * @param double location_Y (中文含意：地理位置经度;　数据存储类型：DECIMAL(9,6))
	 */
	public void setLocation_Y(double location_Y) {
		this.location_Y = location_Y;
	}

	public Integer getScale() {
		return this.scale;
	}

	/*
	 * @param Integer scale (中文含意：地图缩放大小;　数据存储类型：INT)
	 */
	public void setScale(Integer scale) {
		this.scale = scale;
	}

	public String getLabel() {
		return this.label;
	}

	/*
	 * @param String label (中文含意：地理位置信息;　数据存储类型：VARCHAR(200))
	 */
	public void setLabel(String label) {
		this.label = label;
	}


	
}