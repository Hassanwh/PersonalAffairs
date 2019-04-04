package code.person.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bsh.Interpreter;



public class Utility{
	
	private static Utility instance = null;
	private DecimalFormat df=new DecimalFormat("0.0");
	public static synchronized Utility getInstance() {
		if (instance == null)
			instance = new Utility();
		return instance;
	}

	public Utility(){}
	
	/**
	 * 
	 * @param a��action)
	 * @param b(action��
	 */
	public void evalSesAct(Object newAct,Object oldAct){
		Class newClass = newAct.getClass();
		Class oldClass = oldAct.getClass();
		Method[] methods = newClass.getDeclaredMethods();
		Field[] fields = newClass.getDeclaredFields();
		for(Field field:fields){
			String fieldName = field.getName();
			String type = field.getType().getName();
			if(type.indexOf("org.yz.credit.bean.")==-1){
				continue;
			}
			String setMethodStr = "set"+getFirstUpper(fieldName);
			String getMethodStr = "get"+getFirstUpper(fieldName);

			try {
				Method setMethod = newClass.getMethod(setMethodStr, field.getType());
				if(setMethod==null){
					continue;
				}
				Method getMethod = oldClass.getMethod(getMethodStr, null);
				if(getMethod==null){
					continue;
				}
				setMethod.invoke(newAct, getMethod.invoke(oldAct, null));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}
	
	/**
	 * �Զ���b��ģ�ͣ�Ϊ��׼������b��ģ�ͣ��е�ֵ��ֵ��a��action��
	 * @param a��action)
	 * @param b(ģ�ͣ�
	 */
	public void evalAction(Object a,Object b){
		Class action = a.getClass();
		Class mold = b.getClass();
		this.dgAction(a, b, action, mold);		
	}
	
	private void dgAction(Object a,Object b,Class action,Class mold){
		Method[] methods = mold.getDeclaredMethods();
		for(Method method:methods){
			String methodName = method.getName();
			String actMetName = "";
			String type = "";
			if(methodName.length()>3&&methodName.substring(0, 3).equals("get")){
				actMetName = "set"+methodName.substring(3, methodName.length());				
			}else if(methodName.length()>2&&methodName.substring(0, 2).equals("is")){
				actMetName = "set"+methodName.substring(2, methodName.length());
			}else{
				continue;
			}
			type = method.getReturnType().getName();

			Method actionMethod=null;			
			try {
				actionMethod = action.getMethod(actMetName, String.class);
				try {					
					Object objMold = method.invoke(b, null);		
					if(objMold!=null){
						String objStr = changeType(type,objMold);					
						actionMethod.invoke(a, objStr);
					}					
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					continue;
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					continue;
				} catch (InvocationTargetException e) {
					e.printStackTrace();
					continue;
				}
			} catch (SecurityException e) {
				e.printStackTrace();
				continue;
			} catch (NoSuchMethodException e) {
				System.out.println("����"+action.toString()+"��û���ҵ�������"+methodName);
				continue;
			}
		}
		if(mold.getGenericSuperclass()!=null){
			Class superAction = mold.getSuperclass();
			this.dgAction(a, b, action, superAction);
		}
	}
	
	/**
	 * �Զ���b��ģ�ͣ�Ϊ��׼������a��action���е�ֵ��ֵ��b��ģ�ͣ�
	 * @param a��action)
	 * @param b(ģ�ͣ�
	 */
	
	public void evalBean(Object a,Object b){
		Class action = a.getClass();
		Class mold = b.getClass();
		this.dgBean(a, b, action, mold);
	}
	
	private void dgBean(Object a,Object b,Class action,Class mold){
		Method[] methods = mold.getDeclaredMethods();
		for(Method method:methods){
			String methodName = method.getName();
			String actMetName = "";
			String type = "";
			if(methodName.length()>3&&methodName.substring(0, 3).equals("set")){
				if(method.getParameterTypes().length>0){
					type = method.getParameterTypes()[0].getName();
				}
				//action ������ȫ������String����
//				if(type.equals("boolean"))
//					actMetName = "is"+methodName.substring(3, methodName.length());		
//				else
				actMetName = "get"+methodName.substring(3, methodName.length());		
			}else{
				continue;
			}
			Method actionMethod=null;			
			try {
				actionMethod = action.getMethod(actMetName, null);				
				try {
					Object obj = actionMethod.invoke(a, null);
					if(obj!=null){
						Object objSet = changeType(type,(String)obj);	
						if(objSet==null)
							continue;
						method.invoke(b, objSet);
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					continue;
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					continue;
				} catch (InvocationTargetException e) {
					e.printStackTrace();
					continue;
				}
			} catch (SecurityException e) {
				e.printStackTrace();
				continue;
			} catch (NoSuchMethodException e) {
				System.out.println("����"+action.toString()+"��û���ҵ�������"+methodName);
				continue;
			}
		}
		if(mold.getGenericSuperclass()!=null){
			Class superAction = mold.getSuperclass();
			this.dgBean(a, b, action, superAction);
		}
	}	
	
	public void evalObj(Object a,Object b){
		Class action = a.getClass();
		Class mold = b.getClass();
		this.dgObj(a, b, action, mold);
	}
	
	private void dgObj(Object a,Object b,Class action,Class mold){
		Method[] methods = mold.getDeclaredMethods();
		for(Method method:methods){
			String methodName = method.getName();
			String actMetName = "";
			String type = "";
			if(methodName.length()>3&&methodName.substring(0, 3).equals("set")){
				if(method.getParameterTypes().length>0){
					type = method.getParameterTypes()[0].getName();
				}
				//action ������ȫ������String����
//				if(type.equals("boolean"))
//					actMetName = "is"+methodName.substring(3, methodName.length());		
//				else
				actMetName = "get"+methodName.substring(3, methodName.length());		
			}else{
				continue;
			}
			Method actionMethod=null;			
			try {
				actionMethod = action.getMethod(actMetName, null);				
				try {
					Object obj = actionMethod.invoke(a, null);
					if(obj!=null){						
						method.invoke(b, obj);
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					continue;
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					continue;
				} catch (InvocationTargetException e) {
					e.printStackTrace();
					continue;
				}
			} catch (SecurityException e) {
				e.printStackTrace();
				continue;
			} catch (NoSuchMethodException e) {
				System.out.println("����"+action.toString()+"��û���ҵ�������"+methodName);
				continue;
			}
		}
		if(mold.getGenericSuperclass()!=null){
			Class superAction = mold.getSuperclass();
			this.dgBean(a, b, action, superAction);
		}
	}	
	
	/**
	 * �Զ���b��ģ�ͣ�Ϊ��׼������a��action���е�ֵ��ֵ��b��ģ�ͣ�
	 * @param a��action)
	 * @param b(ģ�ͣ�
	 */
	
	public void evalMapToBean(HashMap a,Object b){
		Class mold = b.getClass();
		this.dgMapToBean(a, b, mold);
	}
	
	private void dgMapToBean(HashMap a,Object b,Class mold){
		Method[] methods = mold.getDeclaredMethods();
		for(Method method:methods){
			String methodName = method.getName();
			String actMetName = "";
			String key = "";
			String type = "";
			if(methodName.length()>3&&methodName.substring(0, 3).equals("set")){
				if(method.getParameterTypes().length>0){
					type = method.getParameterTypes()[0].getName();
				}
				key = this.getFirstLower(methodName.substring(3, methodName.length()));
			}else{
				continue;
			}		
			try {
				if(!a.containsKey(key))
					continue;
				
				Object obj = a.get(key);					
				if(obj!=null){
					Object objSet = changeType(type,(String)obj);	
					method.invoke(b, objSet);
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				continue;
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				continue;
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				continue;
			}
		}
		if(mold.getGenericSuperclass()!=null){
			Class superAction = mold.getSuperclass();
			this.dgMapToBean(a, b, superAction);
		}
	}	
	
	/**
	 * �Զ���b��ģ�ͣ�Ϊ��׼������a��action���е�ֵ��ֵ��b��ģ�ͣ�
	 * @param a��action)
	 * @param b(ģ�ͣ�
	 */
	
	public void evalBeanToMap(HashMap a,Object b){
		Class mold = b.getClass();
		this.dgBeanToMap(a, b, mold);
	}
	
	private void dgBeanToMap(HashMap a,Object b,Class mold){
		Method[] methods = mold.getDeclaredMethods();
		for(Method method:methods){
			String methodName = method.getName();
			String key = "";
			String type = "";
			if(methodName.length()>3&&methodName.substring(0, 3).equals("get")||methodName.length()>2&&methodName.substring(0, 2).equals("is")){				
				key = this.getFirstLower(methodName.substring(3, methodName.length()));
				type = method.getReturnType().getName();
				try {
					Object objMold = method.invoke(b, null);						
					if(objMold!=null){
						Object value = changeType(type,objMold);
						a.put(key, value);
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					continue;
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					continue;
				} catch (InvocationTargetException e) {
					e.printStackTrace();
					continue;
				}
			}else{
				continue;
			}
		}
		if(mold.getGenericSuperclass()!=null){
			Class superAction = mold.getSuperclass();
			this.dgBeanToMap(a, b, superAction);
		}
	}	
	
	private Object changeType(String type,String value){
		Object object = null;
		if(type.equals("java.lang.String")){
			object = value;
		}else if(type.equals("double")&&!value.equals("")){			
			object = Double.valueOf(value);
		}else if(type.equals("java.lang.Double")&&!value.equals("")){
			object = Double.valueOf(value);
		}else if(type.equals("int")&&!value.equals("")){
			object = Integer.valueOf(value);
		}else if(type.equals("long")&&!value.equals("")){
			object = Long.valueOf(value);
		}else if(type.equals("java.lang.Integer")&&!value.equals("")){
			object = Integer.valueOf(value);
		}else if(type.equals("boolean")&&!value.equals("")){
			object = Boolean.valueOf(value);
		}else if(type.equals("[Ljava.lang.String;")){
			String[] valueArray = value.split("\\|");
			object = valueArray;
		}else if(type.equals("java.util.Date")){
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				if(value.length()==8)
					object =  sdf.parse(value);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		return object;		
	}
	
	private String changeType(String type,Object value){
		String object=null;
		if(type.equals("java.lang.String")){
			object = (String)value;
		}else if(type.equals("double")){
			object = df.format((Double)value);
		}else if(type.equals("java.lang.Double")){
			object = df.format(value);
		}else if(type.equals("int")){
			object = ((Integer)value).toString();
		}else if(type.equals("java.lang.Integer")){
			object = ((Integer)value).toString();
		}else if(type.equals("boolean")){
			object = Boolean.toString((Boolean)value);
		}else if(type.equals("[Ljava.lang.String;")){
			String[] valueArray = (String[])value;
			for(int i=0;i<valueArray.length;i++){
				object = valueArray[i]+"\\|";
			}			
		}else if(type.equals("java.util.Date")){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			object =  sdf.format((Date)value);
		}		
		return object;		
	}
	
	public Object getBean(String beanId){
		return BeanFactory.getBean(beanId);
	}
	
	
	/**
	 * �õ����ڲ�ֵ *
	 *
	 * @param dateStr String
	 * @param cz int
	 * @return String
	 */
	public String getDate(String dateStr, int cz) {
		int yy = Integer.parseInt(dateStr.substring(0, 4), 10);
		int mm = Integer.parseInt(dateStr.substring(4, 6), 10);
		int dd = Integer.parseInt(dateStr.substring(6, 8), 10);
		java.sql.Date d = new java.sql.Date(yy - 1900, mm - 1, dd + cz);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(d);
	}

	/**
	 * �õ���ǰ�� ��ʽ��2005-9-25 *
	 *
	 * @param dateStr String
	 * @return int
	 */
	public int getCurrentMonth(String dateStr) {
		String date[] = dateStr.split("-");
		return Integer.parseInt(date[1], 10);
	}

	/**
	 * �õ��µ����� *
	 *
	 * @param year int
	 * @param month int
	 * @return int
	 */
	public int getMonthDays(int year, int month) {
		int days = 1;
		boolean isrn = (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) ? true
				: false;
		switch (month) {
		case 1:
			days = 31;
			break;
		case 2:
			if (isrn)
				days = 29;
			else
				days = 28;
			break;
		case 3:
			days = 31;
			break;
		case 4:
			days = 30;
			break;
		case 5:
			days = 31;
			break;
		case 6:
			days = 30;
			break;
		case 7:
			days = 31;
			break;
		case 8:
			days = 31;
			break;
		case 9:
			days = 30;
			break;
		case 10:
			days = 31;
			break;
		case 11:
			days = 30;
			break;
		case 12:
			days = 31;
		}
		return days;
	}

	/**
	 * �õ��µ�������������ǰ�¹���������*
	 *
	 * @param currDate String
	 * @param year int
	 * @param month int
	 * @return int
	 */
	public int getMonthDays(String currDate, int year, int month) {
		int days = 1;
		String date[] = currDate.split("-");
		if (Integer.parseInt(date[0]) == year
				&& Integer.parseInt(date[1]) == month) {
			days = Integer.parseInt(date[2]);
		} else {
			days = getMonthDays(year, month);
		}
		return days;
	}

	/**
	 * �õ���ǰ�� ��ʽ��2005-9-25 *
	 *
	 * @param dateStr String
	 * @return int
	 */
	public int getCurrentYear(String dateStr) {
		String date[] = dateStr.split("-");
		return Integer.parseInt(date[0], 10);
	}

	/**
	 * �õ���ǰ���� ��ʽ��2005-9-25 *
	 *
	 * @param dateStr String
	 * @return int
	 */
	public int getCurrentDay(String dateStr) {
		String date[] = dateStr.split("-");
		return Integer.parseInt(date[2], 10);
	}

	/**
	 * �õ�����ȵ����ڵ����� ��ʽ��2005-9-25 *
	 *
	 * @param dateStr String
	 * @return int
	 */
	public int getJiDuDays(String dateStr) {
		int days = 0;
		String date[] = dateStr.split("-");
		int day = Integer.parseInt(date[2], 10);
		int yy = Integer.parseInt(date[0], 10);
		boolean isrn = (((yy % 4 == 0) && (yy % 100 != 0)) || (yy % 400 == 0)) ? true
				: false;
		switch (Integer.parseInt(date[1], 10)) {
		case 1:
			days = day;
			break;
		case 2:
			days = 31 + day;
			break;
		case 3:
			if (isrn)
				days = 31 + 29 + day;
			else
				days = 31 + 28 + day;
			break;
		case 4:
			days = day;
			break;
		case 5:
			days = 30 + day;
			break;
		case 6:
			days = 61 + day;
			break;
		case 7:
			days = day;
			break;
		case 8:
			days = 31 + day;
			break;
		case 9:
			days = 62 + day;
			break;
		case 10:
			days = day;
			break;
		case 11:
			days = 31 + day;
			break;
		case 12:
			days = 61 + day;
			break;
		}
		return days;
	}

	/**
	 * �����������ڼ�������� *
	 *
	 * @param beginDate String
	 * @param endDate String
	 * @return int
	 */
	public int getBetweenDays(String beginDate, String endDate) {
		int sum = 0;
		int beginYear = getCurrentYear(beginDate);
		int beginMonth = getCurrentMonth(beginDate);
		int beginDay = getCurrentDay(beginDate);
		int endYear = getCurrentYear(endDate);
		int endMonth = getCurrentMonth(endDate);
		int endDay = getCurrentDay(endDate);
		String startDateStr = String.valueOf(beginYear) + bZero(beginMonth)
				+ "01";

		int sumMonth = (endYear - beginYear + 1) * 12 - (beginMonth)
				- (12 - endMonth);
		for (int i = 0; i < sumMonth; i++) {
			String dateStr = getDateStr(startDateStr, i);
			sum = sum
					+ getMonthDays(getCurrentYear(dateStr),
							getCurrentMonth(dateStr));
		}

		sum = sum - beginDay + endDay;
		return sum;
	}
	
	public Date getPassMonthDate(Date date, int passMonth)
	{
		return strToDate(getDateStr(dateToStr(date, ""), passMonth));
	}
	
	public int getBetweenDays(Date beginDate, Date endDate)
	{
		return getBetweenDays(dateToStr(beginDate, "-"), dateToStr(endDate, "-"));
	}

	/**
	 * �������ھ��������º������ *
	 *
	 * @param dateStr String
	 * @param hkm int
	 * @return String
	 */
	public String getDateStr(String dateStr, int hkm) {
		String reDateStr = "";
		int yy = Integer.parseInt(dateStr.substring(0, 4), 10);
		int mm = Integer.parseInt(dateStr.substring(4, 6), 10);
		int dd = Integer.parseInt(dateStr.substring(6, 8), 10);
		//int yy1=0,mm1=0,dd1=dd;
		int yy2 = 0, mm2 = 0, dd2 = dd;
		if ((mm + hkm) % 12 == 0) {
			yy2 = yy + (mm + hkm) / 12 - 1;
			mm2 = 12;
		} else {
			if ((mm + hkm) % 12 == 1) {
				yy2 = yy + (mm + hkm) / 12;
				mm2 = 1;
			} else {
				yy2 = yy + (mm + hkm) / 12;
				mm2 = (mm + hkm) % 12;
			}
		}
		reDateStr = String.valueOf(yy2) + "-" + bZero(mm2) + "-" + bZero(dd2);
		return reDateStr;
	}

	/**
	 * ������λ�����ִ� *
	 *
	 * @param sz int
	 * @return String
	 */
	public String bZero(int sz) {
		return (sz < 10 ? ("0" + String.valueOf(sz)) : String.valueOf(sz));
	}

	/**
	 * �ַ���("|")������ *
	 *
	 * @param string String
	 * @return String[]
	 */
	public String[] strToArray(String string) {
		String tmpString;
		String[] returnArray = null;
		if (string == null || string.equalsIgnoreCase("")) {
			returnArray = new String[1];
			returnArray[0] = "";
		} else {
			int j = 0, jsInt = 0;
			for (int i = 0; i < string.length(); i++) {
				if (string.charAt(i) == '|') {
					j++;
				}
			}
			if (j == 0) {
				returnArray = new String[1];
				returnArray[0] = string;
			} else {
				returnArray = new String[j];
				j = 0;
				for (int i = 0; i < string.length(); i++) {
					if (string.charAt(i) == '|') {
						if (j == i) {
							tmpString = " ";
						} else {
							tmpString = string.substring(j, i).trim();
						}
						j = i + 1;
						returnArray[jsInt] = tmpString;
						jsInt += 1;
					}
				}
			}
		}
		return returnArray;
	}

	/**
	 * �ַ��������� *
	 *
	 * @param string String
	 * @param separator char
	 * @return String[]
	 */
	public String[] strToArray(String string, char separator) {
		String[] returnArray;
		if (string == null || string.equalsIgnoreCase("")) {
			returnArray = new String[1];
			returnArray[0] = "";
		} else {
			int j = 0, lastpos = 0;
			ArrayList pos = new ArrayList();
			pos.add(String.valueOf(-1));
			for (int i = 0; i < string.length(); i++) {
				if (string.charAt(i) == separator) {
					j++;
					lastpos = i;
					pos.add(String.valueOf(i));
				}
			}
			if (lastpos != string.length()) {
				j++;
				pos.add(String.valueOf(string.length()));
			}

			int[] ps = new int[pos.size()];
			for (int i = 0; i < ps.length; i++) {
				ps[i] = Integer.parseInt(String.valueOf(pos.get(i)));
			}
			returnArray = new String[ps.length - 1];
			for (int i = 0; i < returnArray.length; i++) {
				returnArray[i] = string.substring(ps[i] + 1, ps[i + 1]).trim();
			}

		}
		return returnArray;
	}

	/**
	 * ���鵽�ִ�,��"|"���� *
	 *
	 * @param strArray String[]
	 * @return String
	 */
	public String arrayToStr(String strArray[]) {
		String reStr = "";
		if (strArray != null) {
			for (int i = 0; i < strArray.length; i++) {
				if (strArray[i] != null) {
					reStr = reStr + strArray[i].trim() + "|";
				} else {
					reStr = reStr + "|";
				}
			}
		}
		return reStr;
	}

	/**
	 * ת���� html ��ʽ*
	 *
	 * @param str String
	 * @return String
	 */
	public String htmlcode(String str) {
		String result = "";
		if (str == null || str.equalsIgnoreCase("")) {
			return result;
		}
		int l = str.length();
		for (int i = 0; i < l; i++) {
			char tmpChar = str.charAt(i);
			switch (tmpChar) {
			case '<':
				result = result + "&lt;";
				break;
			case '>':
				result = result + "&gt;";
				break;
			case 13:
				result = result + "<br>";
				break;
			case 34:
				result = result + "&quot;";
				break;
			case '&':
				result = result + "&amp;";
				break;
			case 32:
				result = result + "&nbsp;";
				break;
			case 9:
				result = result + "    ";
				break;
			default:
				result = result + String.valueOf(tmpChar);
			}
		}
		//System.out.println(result);
		return result;
	}

	/**
	 * isInteger
	 *
	 * @param num String
	 * @return boolean
	 */
	public boolean isInteger(String num) {
		boolean bl = false;
		try {
			int i = Integer.parseInt(num);
			bl = true;
		} catch (Exception ex) {
			bl = false;
		}
		return bl;
	}

	/**
	 * isLong
	 *
	 * @param num String
	 * @return boolean
	 */
	public boolean isLong(String num) {
		boolean bl = false;
		try {
			long i = Long.parseLong(num);
			bl = true;
		} catch (Exception ex) {
			bl = false;
		}
		return bl;
	}

	/**
	 * isDouble
	 *
	 * @param num String
	 * @return boolean
	 */
	public boolean isDouble(String num) {
		boolean bl = false;
		try {
			double i = Double.parseDouble(num);
			bl = true;
		} catch (Exception ex) {
			bl = false;
		}
		return bl;
	}

	/**
	 * isFloat
	 *
	 * @param num String
	 * @return boolean
	 */
	public boolean isFloat(String num) {
		boolean bl = false;
		try {
			float i = Float.parseFloat(num);
			bl = true;
		} catch (Exception ex) {
			bl = false;
		}
		return bl;
	}

	/**
	 * isBoolean
	 *
	 * @param str String
	 * @return boolean
	 */
	public boolean isBoolean(String str) {
		boolean bl = false;
		try {
			Boolean bl1 = new Boolean(str);
			bl = bl1.booleanValue();
		} catch (Exception ex) {
			bl = false;
		}
		return bl;
	}

	/**
	 * isDate
	 *
	 * @param dateStr String
	 * @return boolean
	 */
	public boolean isDate(String dateStr) {
		boolean bl = true;
		if (dateStr != null) {
			String[] date = dateStr.split("-");
			try {
				int yy = Integer.parseInt(date[0], 10);
				int mm = Integer.parseInt(date[1], 10);
				int dd = Integer.parseInt(date[2], 10);
				if (mm > 12 || mm <= 0 || dd <= 0 || dd > 31)
					return false;
				int rndd = ((yy % 4 == 0) && (yy % 100 != 0) || (yy % 400 == 0)) ? 29
						: 28;
				switch (mm) {
				case 4:
				case 6:
				case 9:
				case 11:
					if (dd > 30 || dd <= 0)
						bl = false;
					break;
				case 1:
				case 3:
				case 5:
				case 7:
				case 8:
				case 10:
				case 12:
					if (dd > 31 || dd <= 0)
						bl = false;
					break;
				case 2:
					if (dd > rndd || dd <= 0)
						bl = false;
					break;
				}
			} catch (Exception ex) {
				bl = false;
			}
		}
		return bl;
	}

	/**
	 * �Ƚ����ڴ�С *
	 *
	 * @param date1 String
	 * @param date2 String
	 * @return int
	 */
	public int compareDate(String date1, String date2) {
		int i = 0;
		String[] date1Array = date1.split("-");
		String[] date2Array = date2.split("-");
		Date date11 = new Date(Integer.parseInt(
				date1Array[0], 10), Integer.parseInt(date1Array[1], 10),
				Integer.parseInt(date1Array[2], 10));
		Date date22 = new Date(Integer.parseInt(
				date2Array[0], 10), Integer.parseInt(date2Array[1], 10),
				Integer.parseInt(date2Array[2], 10));
		return date11.compareTo(date22);
	}

	/**
	 * ���ַ��� ��ʽת���������� *
	 *
	 * @param dateStr String
	 * @return Date
	 */
	public Date strToDate(String dateStr) {
		String[] dateArray = dateStr.split("-");
		java.util.Date date = new java.util.Date(Integer.parseInt(dateArray[0],
				10) - 1900, Integer.parseInt(dateArray[1], 10) - 1, Integer
				.parseInt(dateArray[2], 10));
		return date;
	}

	/**
	 * ��������ת�����ַ����� *
	 *
	 * @param date Date
	 * @return String
	 */
	public String dateToStr(java.util.Date date) {
		String str = "";
		try {
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					"yyyy-MM-dd");
			str = sdf.format(date);
		} catch (Exception ex) {
			str = "";
		}
		return str;
	}

	/**
	 * ��������ת�����ַ����� *
	 *
	 * @param date Date
	 * @return String
	 */
	public String dateToStr(java.util.Date date, String fgf) {
		String str = "";
		try {
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					"yyyy" + fgf + "MM" + fgf + "dd");
			str = sdf.format(date);
		} catch (Exception ex) {
			str = "";
		}
		return str;
	}

	public List getSubZbID(String formula) {
		List list = new ArrayList();
		String param = getParam(formula, '{', '}');
		while (!param.equals("")) {
			String[] str = this.strToArray(param, '.');

			if (str.length == 2 || str.length == 1) {
				if (str.length == 2) {
					String id = getParam(param, '[', ']');
					String id2 = getParam(str[1], '[', ']');
					if (!id2.equalsIgnoreCase("02")) {
						if (!list.contains(id)) {
							list.add(id);
						}
					}
				} else {
					String id = getParam(param, '[', ']');
					if (!list.contains(id)) {
						list.add(id);
					}

				}
			}
			formula = replace(formula, '{' + param + '}', "");
			param = getParam(formula, '{', '}');
		}
		return list;
	}

	/**
	 * �õ������ַ�����ַ��� *
	 *
	 * @param string String
	 * @param leftFlag char
	 * @param rightFlag char
	 * @return String
	 */
	public String getParam(String string, char leftFlag, char rightFlag) {
		String param = "";

		while (string.indexOf(rightFlag) < string.indexOf(leftFlag))
			string = string.substring(string.indexOf(rightFlag) + 1);

		if (string.indexOf(leftFlag) >= 0 && string.indexOf(rightFlag) >= 0) {
			int pos1 = string.indexOf(leftFlag);
			int pos2 = string.indexOf(rightFlag);
			param = string.substring(pos1 + 1, pos2);
		}

		return param;
	}

	public String getParam(String string, char flag) {
		if (string == null || string.equalsIgnoreCase(""))
			return "";

		String param = "";
		int pos1 = string.indexOf(flag);
		int pos2 = 0;
		if (pos1 >= 0) {
			pos2 = string.indexOf(flag, pos1 + 1);

			if (pos2 > 0) {
				param = string.substring(pos1 + 1, pos2);
			}
		}
		return param;
	}

	/**
	 * ��str2 �滻 str��str1 ���ִ� *
	 *
	 * @param str String
	 * @param str1 String
	 * @param str2 String
	 * @return String
	 */
	public String replace(String str, String str1, String str2) {
		for (int pos = str.indexOf(str1); pos >= 0; pos = str.indexOf(str1))
			str = new String(new StringBuffer(str.substring(0, pos)).append(
					str2).append(str.substring(str1.length() + pos)));

		return str;
	}

	/**
	 * ����С��λ������������
	 *
	 * @param ys double
	 * @param xs int
	 * @return double
	 */
	public double sswl(double ys, int xs) {
		double res = 0;
		double sx = java.lang.Math.pow(10, xs);
		res = java.lang.Math.round(sx * ys) / sx;
		return res;
	}

	/**
	 * bzero
	 *
	 * @param num int
	 * @param bits int
	 * @return String
	 */
	public String bzero(int num, int bits) {
		String str = String.valueOf(num);
		String preStr = "";
		for (int i = 0; i < bits - str.length(); i++) {
			preStr = preStr + "0";
		}
		str = preStr + str;
		return str;
	}

	public String getFormulaValue(String string) {
		Interpreter interpreter = new Interpreter();
		DecimalFormat df = new DecimalFormat("0.00");
		if (string.equals(""))
			return "0";

		String result;

		string = replace(string, "MAX", "com.dtbridge.core.util.Function.Max");
		string = replace(string, "MIN", "com.dtbridge.core.util.Function.Min");
		string = replace(string, "INT", "com.dtbridge.core.util.Function.Int");
		string = replace(string, "ADD", "com.dtbridge.core.util.Function.Add");
		string = replace(string, "--", "+");
		string = replace(string, "+-", "-");

		try {
			//�ڼ�ȥ��ֵʱ�ᷢ������
			System.out.println("��ʽΪ��" + string);
			interpreter.eval("try { result=".concat(string)
					+ ";}catch(ArithmeticException e){result=0;}");
			result = interpreter.get("result").toString();

			if (result.equals("Infinity") || result.equals("NaN")
					|| result.equals("-��") || result.equals("��"))
				result = "0";
			else
				result = df.format(Double.parseDouble(result));
			if (result.equals("-��") || result.equals("��"))
				result = "0";
			//System.out.println("String:"+string+" Result:"+result);
		} catch (Exception ex) {
			//ex.printStackTrace();
			result = "0";
		}
		return result;
	}
	
	/**
	 * ��'9.99E10'ת��Ϊ99900000000.00��ʽ���ַ���
	 **/
	public String formatDouble(double source){
		double target = 0.0;
		String str = Double.toString(source);
		int index = str.indexOf('E');
		if(index!=-1){
			int bit = Integer.parseInt(str.substring(index+1, str.length()));
			String left = str.substring(0,index);
			if ((int)(left.substring(2,index).length())<=bit){
				String zero="";
				for ( int j=1;j<=(bit-index+4);j++) zero=zero+"0";
				left=left+zero;
			}
			StringBuffer s = new StringBuffer(left);
			s.deleteCharAt(left.indexOf('.'));
			s.insert(bit+1,'.');
			return s.toString();
		}else{
			target = source;
		}
		return Double.toString(target);
	}
	
	 /**
	   * ���� *
	   *
	   * @param source byte[]
	   * @return byte[]
	   */
	public byte[] desKey(byte[] source) {
	    byte[] reByte = new byte[source.length];
	    String key = "dlyzcom";
	    byte[] keyByte = key.getBytes();
	    for (int i = 0; i < source.length; i++) {
	      int j = i % keyByte.length;
	      reByte[i] = (byte) (source[i] - keyByte[j]);
	    }
	    return reByte;
	}

	  /**
	   * ���� *
	   *
	   * @param source String
	   * @return String
	   */
	public String addKey(String source) {
	    Descrypt des = new Descrypt();
	    return des.descrypt(source,"dlyzcom");
	}
	
	public String numberToChacters(double n){
		String je = this.formatDouble(n);
		String[] str = { "��", "Ҽ", "��", "��", "��", "��", "½", "��", "��", "��" };
		String[] str1 = { "", "ʰ", "��", "Ǫ" };
		int start = je.indexOf(".");
		if (start == -1)
			start = je.length();
		String rtn = "";
		try {
			if(Integer.parseInt(je.substring(start+1))==0)
				rtn = "��";
			else{
				if(Integer.parseInt(je.substring(start + 1, start + 2))==0){
					rtn = str[Integer.parseInt(je.substring(start + 1, start + 2))];
				}else{
					rtn = str[Integer.parseInt(je.substring(start + 1, start + 2))] + "��";
				}
				if(Integer.parseInt(je.substring(start+2,start+3)) > 0)
				{
					rtn = rtn + str[Integer.parseInt(je.substring(start + 2, start + 3))] + "��";
				}else{
					rtn = rtn + "��";
				}

			}
		}
		catch (StringIndexOutOfBoundsException e) {
			rtn = rtn + "��";
		}
		int index = 0; //����дstr1��Ԫ�أ���Ǯ�ĵ�λ
		int index1 = 0;
		int count = 1; //������ȡ��ĳ���
		int lastX = -1;
		double mount = 0; //
		double money = Double.parseDouble(je);
		while (true) {
			try {
				String je1 = je.substring(start - 4, start);
				start = start - 4;
				index1++;
				if (je1.equals("0000")){
					if(index1==1){
						rtn="Ԫ"+rtn;
					}else{
						if(!rtn.substring(0, 1).equals("��")&&!rtn.substring(0, 1).equals("Ԫ"))
							rtn="��"+rtn;
					}
					continue;
				}
				switch (index1) {
					case 1 :
						rtn = "Ԫ" + rtn;
						break;
					case 2 :
						rtn = "��" + rtn;
						break;
					case 3 :
						rtn = "��" + rtn;
						break;
					case 4 :
						rtn = "��" + rtn;
						break;
				}
				for (int i = 3; i >= 0; i--) {
					int x = Integer.parseInt(je1.substring(i, i + 1));
					if (x == 0 && lastX == 0)
						continue;
					if (x == 0 && i == 3) {
						lastX = x;
						continue;
					}
					if (x == 0)
						rtn = str[x] + rtn;
					else
						rtn = str[x] + str1[3 - i] + rtn;
					lastX = x;
				}
			}
			catch (StringIndexOutOfBoundsException e) {
				if(start==0)break;
				String je1 = je.substring(0, start);
				index1++;
				switch (index1) {
					case 1 :
						rtn = "Ԫ" + rtn;
						break;
					case 2 :
						rtn = "��" + rtn;
						break;
					case 3 :
						rtn = "��" + rtn;
						break;
					case 4 :
						rtn = "��" + rtn;
						break;
				}
				for (int i = je1.length() - 1; i >= 0; i--) {
					int x = Integer.parseInt(je1.substring(i, i + 1));
					if (x == 0 && lastX == 0)
						continue;
					if (x == 0 && 3 - i - (4 - je1.length()) == 0) {
						lastX = x;
						continue;
					}
					if (x == 0)
						rtn = str[x] + rtn;
					else
						rtn = str[x] + str1[3 - i - (4 - je1.length())] + rtn;
					lastX = x;
				}
				break;
			}
		}
		return rtn;
	}

	public String getFirstLower(String str){
		String resultStr = "";
		if(str.length()>0){
			String first = str.substring(0, 1);
			String change = first.toLowerCase();
			resultStr = change+str.substring(1);
		}
		return resultStr;
	}
	
	public String getFirstUpper(String str){
		String resultStr = "";
		if(str.length()>0){
			String first = str.substring(0, 1);
			String change = first.toUpperCase();
			resultStr = change+str.substring(1);
		}
		return resultStr;
	}
	
	public String douToStr(double number){
		String result = "0";
		result = df.format(number);
		return result;
	}
	
	public double strToDou(String s){
		double result = 0;
		try{
			result = Double.parseDouble(s);
		}catch(NumberFormatException e){
			e.printStackTrace();
			System.out.println("�ַ���"+s+"ת��Ϊdouble���ͳ������Ϸ����ַ�����");
			result = 0;
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param value ��Ҫת����Objectֵ
	 * @param format ��ʽ�������ַ��� ����:yyyy-MM-dd  yyyyMMdd  yyMMdd
	 * @return
	 * @throws Exception
	 */
	public Date ToDate(Object value,String format) throws Exception
	{
		Date date=null;
		try
		{
			DateFormat df=new SimpleDateFormat(format);
			date=df.parse((String)value);
		}
		catch(ParseException pe)
		{
			throw new Exception(pe.toString());
		}
		
		return date;
	}
	
	/**
	 * Stringת����Date [DateStr��ʽΪ yyyy-MM-dd]
	 * @param DateStr
	 * @return
	 */
	public Date StrToDate1(String DateStr) throws Exception
	{
		Pattern pattern = Pattern.compile("[0-9]{4}[-][1-9]{2}[-][1-9]{2}");
        Matcher matcher = pattern.matcher(DateStr);
        Date date=new Date();
        if(matcher.matches())
        {
			try
			{
				date=ToDate((Object)DateStr,"yyyy-MM-dd");
			}
			catch(Exception e)
			{
				throw new Exception(e.toString());
			}
        }
        else
        {
        	throw new Exception("�����ַ�����ʽ������ yyyy-MM-dd");
        }
		
		return date;
	}
	
	/**
	 * Stringת����Date [DateStr��ʽΪ yyyyMMdd]
	 * @param DateStr
	 * @return
	 */
	public Date StrToDate2(String DateStr) throws Exception
	{
		Pattern pattern = Pattern.compile("[0-9]{4}[1-9]{2}[1-9]{2}");
        Matcher matcher = pattern.matcher(DateStr);
        Date date=new Date();
        if(matcher.matches())
        {
			try
			{
				date=ToDate((Object)DateStr,"yyyyMMdd");
			}
			catch(Exception e)
			{
				throw new Exception(e.toString());
			}
        }
        else
        {
        	throw new Exception("�����ַ�����ʽ������ yyyyMMdd");
        }
		
		return date;
	}
	
	/**
	 * ��accordingΪ���ݲ��split�ַ���
	 * @param splited
	 * @param according
	 * @return
	 */
	public String[] StringToArray(String split,String according)
	{
		String[] result=split.split("[+"+according+"]");
		
		return result;
	}
	
	/**
	 * �ַ������ж��ٸ�ָ���ķָ��ַ�
	 * @param splited ���ָ��ַ���
	 * @param according �ָ��ַ������ַ�
	 * @return
	 */
	public int SplitCharCount(String splited,char according)
	{
		int count=0;
		for(int i=0;i<splited.length();i++)
		{
			if(splited.charAt(i)==according)
			{
				count++;
			}
			else
			{
				continue;
			}
		}
		
		return count;
	}
	
	/**
	 * ��arrayת������splitΪ�����һ���ַ���
	 * @param array
	 * @param split
	 * @return
	 */
	public String ArrayToString(String[] array,String split)
	{
		String result="";
		if(array!=null&&array.length>0)
		{
			for(int i=0;i<array.length;i++)
			{
				result+=array[i]+split;				
			}
		}
		
		return result;
	}
	
	public String getDate() 
	{
		DateFormat formate=new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		String result=formate.format(date);
		
		return result;
	}
	
	public String getTime()
	{
		Calendar calendar=new GregorianCalendar();
		return String.valueOf(calendar.get(calendar.HOUR_OF_DAY))
			+":"+String.valueOf(calendar.get(calendar.MINUTE))
			+":"+String.valueOf(calendar.get(calendar.SECOND));
	}
	
	/**
	 * ��ǰ��Ȼ����
	 * @return
	 */
	public static Date Now()
	{
		return new Date();
	}
	
	/**
	 * ��ǰ��Ȼ����(ȫ����)
	 * @return
	 */
	public static Date FullNow()
	{
		return new Date();
	}
	
	/**
	 * ��ǰϵͳ����
	 * @return
	 */
	public static Date SysNow()
	{
		return new Date();
	}

	/*
	 * ���ڵ�����
	 * @param baseDate����׼���� sum��������������Ϊ����kind��[year/month/day]������
	 * @return �������
	 */
	public static Date calDate(Date baseDate,int sum,String kind){
		Calendar calendar=new GregorianCalendar();
		calendar.setTime(baseDate);
		if(kind.equals("year")){
			calendar.add(Calendar.YEAR, sum);
		}else if(kind.equals("month")){
			calendar.add(Calendar.MONTH, sum);
		}else if(kind.equals("day")){
			calendar.add(Calendar.DATE, sum);
		}else{
			
		}
		return calendar.getTime();
	}

	/**
	 * ���ֽ�����ת��Ϊʮ�������ַ���
	 * 
	 * @param byteArray
	 * @return
	 */
	public static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}
	
	/**
	 * ���ֽ�ת��Ϊʮ�������ַ���
	 * 
	 * @param mByte
	 * @return
	 */
	public static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];

		String s = new String(tempArr);
		return s;
	}
	
}

