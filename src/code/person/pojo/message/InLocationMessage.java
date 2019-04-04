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
	 * @param Long msgId (���ĺ��⣺��ϢID;�����ݴ洢���ͣ�δ֪)
	 */
	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}

	public double getLocation_X() {
		return this.location_X;
	}

	/*
	 * @param double location_X (���ĺ��⣺����λ��γ��;�����ݴ洢���ͣ�DECIMAL(9,6))
	 */
	public void setLocation_X(double location_X) {
		this.location_X = location_X;
	}

	public double getLocation_Y() {
		return this.location_Y;
	}

	/*
	 * @param double location_Y (���ĺ��⣺����λ�þ���;�����ݴ洢���ͣ�DECIMAL(9,6))
	 */
	public void setLocation_Y(double location_Y) {
		this.location_Y = location_Y;
	}

	public Integer getScale() {
		return this.scale;
	}

	/*
	 * @param Integer scale (���ĺ��⣺��ͼ���Ŵ�С;�����ݴ洢���ͣ�INT)
	 */
	public void setScale(Integer scale) {
		this.scale = scale;
	}

	public String getLabel() {
		return this.label;
	}

	/*
	 * @param String label (���ĺ��⣺����λ����Ϣ;�����ݴ洢���ͣ�VARCHAR(200))
	 */
	public void setLabel(String label) {
		this.label = label;
	}


	
}