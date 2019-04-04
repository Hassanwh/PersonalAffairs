package code.person.pojo.customer;

import java.io.Serializable;


/**
 * Description:
 * <br/>Copyright (C), 2001-2011, 
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:	2015-01-04
 * @author  ldh
 * @version  2.0
 */
public class QuestionDatabase implements Serializable
{

	// Fields
	private Integer id;
	private String question;
	private String answer;
	
	
	// Constructors
	public QuestionDatabase() {
	}	

	// Property accessors	
	public Integer getId() {
		return this.id;
	}

	/*
	 * @param Integer id (���ĺ��⣺���;�����ݴ洢���ͣ�INT)
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	public String getQuestion() {
		return this.question;
	}

	/*
	 * @param String question (���ĺ��⣺����;�����ݴ洢���ͣ�VARCHAR(400))
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return this.answer;
	}

	/*
	 * @param String answer (���ĺ��⣺��;�����ݴ洢���ͣ�VARCHAR(400))
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}


	
}