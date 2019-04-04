package code.person.util;
//规定一些格式
public abstract class Code {
	public static String getCurrencyName(String c) {//货币名字
		if ("01".equalsIgnoreCase(c)) {
			return "人民币";
		}
		return c;
	}
	public static String getCurrencySymbol(String c) {//货币符号
		if ("01".equalsIgnoreCase(c)) {
			return "￥";
		}
		return c;
	}
	public static String checkEmpty(String v, String convert) {//检查是不是空的
		return checkEmpty(v, convert, new String[0]);
	}
	public static String checkEmpty(String v, String convert, String... ss) {//检查空
		if (v == null) return convert;
		if (v.length() == 0) return convert;
		if (ss != null) {
			for(String s : ss) {
				if (v.equalsIgnoreCase(s))return convert;
			}
		}
		return v;
	}
	public static String mask(String input, int headKeep, int tailKeep, char maskChar, int maskCount) {//不明的
		if (input == null) return null;
		int il = input.length();
		if (il == 0) return input;
		if (il < headKeep || il < tailKeep) return input;
		StringBuilder output = new StringBuilder();
		output.append(input.substring(0,headKeep));
		for(int i = 0; i < maskCount; i++) {
			output.append(maskChar);
		}
		output.append(input.substring(input.length()-tailKeep));
		return output.toString();
	}
	public static String maskAccountNumber(String input) {//不明的账号
		return mask(input, 1, 4, '*', 3);
	}	
	public static String maskMobileNumber(String input) {//不明的手机号
		return mask(input, 3, 4, '*', 4);
	}
	public static String formatAmount(String unformated, boolean group) {//格式化
		if (!group) {
			return unformated;
		}
		StringBuilder formated = new StringBuilder();

		if (!unformated.contains(".")) {
			unformated = unformated + ".00";
		} else {
			int tl = unformated.length();
			int pl = unformated.lastIndexOf(".");
			if (tl - pl == 2) {
				unformated = unformated + "0";
			}
			unformated = unformated.substring(0,pl+2+1);	//20091116
		}

		int intLength = unformated.indexOf('.');
		int i = 0;
		int j = -1;
		if (intLength > 3) {
			j = intLength % 3;
		}
		for (i = 0; i < unformated.length(); i++) {
			if (i < intLength) {
				if (j == 0) {
					if (i > 0) {
						formated.append(',');
					}
					j = 3;
				}
			}
			formated.append(unformated.charAt(i));
			if (j > 0)
				j--;
		}
	
		return formated.toString();
	}
	public static String formatDate(String unformated) {//日期格式
		String i = unformated.trim();
		if (i.length() != 8) return unformated;
		StringBuilder o = new StringBuilder();
		o.append(i.substring(0,4));
		o.append('-');
		o.append(i.substring(4,6));
		o.append('-');
		o.append(i.substring(6,8));
		return o.toString();
	}
	public static String formatTime(String unformated) {//时间格式
		String i = unformated.trim();
		if (i.length() != 5 && i.length() != 6) return unformated;
		int a = 0;
		if (i.length() == 5) {
			a = 1;
		}
		StringBuilder o = new StringBuilder();
		if (a == 1) {
			o.append('0');
		}
		o.append(i.substring(0,2-a));
		o.append('-');
		o.append(i.substring(2-a,4-a));
		o.append('-');
		o.append(i.substring(4-a,6-a));
		return o.toString();
	}
	public static boolean isEmptyString(String s) {//是不是空的字符串
		if (s == null) return true;
		if (s.trim().length() == 0) return true;
		return false;
	}
}
