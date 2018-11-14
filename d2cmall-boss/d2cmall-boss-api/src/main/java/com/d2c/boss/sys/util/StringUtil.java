package com.d2c.boss.sys.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtil {

	private static Logger log = LoggerFactory.getLogger(StringUtil.class);
	public static final String FULLEND = ".";
	public static final String COMMA = ",";
	public static final String SEMICOLON = ";";
	public static final String LEFTPARENTHESIS = "(";
	public static final String RIGHTPARENTHESIS = ")";
	public static final String CURRENCY_PATTERN = "######";
	public static final String LEFT_NEUTRALLY = "[";
	public static final String MSSQL_NEUTRALLY = "[[]";

	public StringUtil() {
	}

	public static String getLength(String s, int digits) {
		return getLength(s, digits, true);
	}

	public static String getLength(String s, int digits, boolean countSpecialCharsTwice) {
		if (s == null)
			s = "";
		int length = s.length();
		int zero = 0;
		int l = 0;
		do {
			l = s.indexOf(zero, l);
			if (l < 0)
				break;
			length--;
			l++;
		} while (true);
		if (countSpecialCharsTwice) {
			int i1 = 0;
			do {
				i1 = s.indexOf('\n', i1);
				if (i1 < 0)
					break;
				length++;
				i1++;
			} while (true);
		}
		if (countSpecialCharsTwice) {
			int j1 = 0;
			do {
				j1 = s.indexOf('\r', j1);
				if (j1 < 0)
					break;
				length++;
				j1++;
			} while (true);
		}
		String s1 = Integer.toString(length);
		int numb_length = s1.length();
		for (int l1 = 0; l1 < digits - numb_length; l1++)
			s1 = (new StringBuilder("0")).append(s1).toString();

		return s1;
	}

	public static String replaceInString(String fromString, String toString, String inString) {
		if (inString == null)
			return null;
		if (inString.length() == 0)
			return inString;
		if (inString.length() < fromString.length())
			return inString;
		StringBuffer stringbuffer = new StringBuffer();
		char inStringCh[] = inString.toCharArray();
		char fromStringCh[] = fromString.toCharArray();
		int i;
		for (i = 0; i < (inStringCh.length - fromStringCh.length) + 1; i++) {
			boolean flag = true;
			for (int k = 0; k < fromStringCh.length; k++) {
				if (inStringCh[i + k] == fromStringCh[k])
					continue;
				flag = false;
				break;
			}

			if (!flag) {
				stringbuffer.append(inStringCh[i]);
			} else {
				stringbuffer.append(toString);
				i = (i + fromString.length()) - 1;
			}
		}

		if (i < inStringCh.length) {
			for (int j = i; j < inStringCh.length; j++)
				if (j >= 0)
					stringbuffer.append(inStringCh[j]);

		}
		return stringbuffer.toString();
	}

	public static String replaceNonQuotedAmpersands(String s) {
		if (s == null)
			return null;
		if (s.length() == 0)
			return s;
		char ac[] = s.toCharArray();
		StringBuffer stringbuffer = new StringBuffer();
		for (int i = 0; i < s.length(); i++)
			if (ac[i] != '&') {
				stringbuffer.append(ac[i]);
			} else {
				boolean flag = false;
				if (i + 4 < s.length())
					if (ac[i + 1] == 'a' && ac[i + 2] == 'm' && ac[i + 3] == 'p' && ac[i + 4] == ';')
						flag = true;
					else if (ac[i + 1] == '#')
						flag = true;
				if (flag)
					stringbuffer.append("&");
				else
					stringbuffer.append("&amp;");
			}

		return stringbuffer.toString();
	}

	public static String[] findSubStrings(String inString, char separator) {
		if (inString.length() == 0)
			return new String[0];
		char ac[] = inString.toCharArray();
		Vector<String> vector = new Vector<String>();
		int i = 0;
		for (int j = 0; j < ac.length; j++)
			if (ac[j] == separator) {
				vector.addElement(inString.substring(i, j));
				i = j + 1;
			}

		if (i != ac.length)
			vector.addElement(inString.substring(i, ac.length));
		else
			vector.addElement("");
		String as[] = new String[vector.size()];
		vector.copyInto(as);
		return as;
	}

	public static String findEndPropertyName(String propertyName) {
		int i = 0;
		do {
			int j = propertyName.indexOf('.', i);
			if (j < 0)
				return propertyName.substring(i);
			i = j + 1;
		} while (i < propertyName.length());
		return "";
	}

	public static String[] findHierarchyStrings(String inString, char separator) {
		int i = 0;
		Vector<String> vector = new Vector<String>();
		do {
			int j = inString.indexOf(separator, i);
			if (j < 0)
				break;
			vector.addElement(inString.substring(0, j));
			i = j + 1;
		} while (i < inString.length());
		vector.addElement(inString);
		String as[] = new String[vector.size()];
		vector.copyInto(as);
		return as;
	}

	public static String findStringLeftOfLastOccurenceOf(String s, char separator) {
		if (s == null)
			return s;
		int i = 0;
		do {
			int j = s.indexOf(separator, i + 1);
			if (j < 0)
				if (i == 0)
					return s;
				else
					return s.substring(0, i);
			i = j;
		} while (true);
	}

	public static boolean checkIfStringIsProperId(String value) {
		if (value == null)
			return true;
		char ac[] = value.toCharArray();
		for (int i = 0; i < ac.length; i++) {
			char c = ac[i];
			int j = CAN_BE_USE_CHARS.indexOf(c);
			if (j < 0)
				return false;
		}

		return true;
	}

	public static String convertStringToProperXML(String value) {
		String s1 = replaceNonQuotedAmpersands(value);
		s1 = replaceInString("\"", "&quot;", s1);
		s1 = replaceInString(">", "&gt;", s1);
		s1 = replaceInString("<", "&lt;", s1);
		return s1;
	}

	public static void main(String args[]) {
		// String s = "a\nb";
		// System.out.println((new StringBuilder(String.valueOf(s.length())))
		// .append("/").append(s).toString());
		// s = "Hallo mir geht es gut!";
		// System.out.println((new StringBuilder(String.valueOf(s.length())))
		// .append("/").append(s).toString());
		// s = "Hallo\tmir\tgeht\tes\t\tgut!";
		// System.out.println((new StringBuilder(String.valueOf(s.length())))
		// .append("/").append(s).toString());
		// s = "Hallo\nmir\ngeht\nes\n\ngut!";
		// System.out.println((new StringBuilder(String.valueOf(s.length())))
		// .append("/").append(s).toString());
		// s = "Hallo\rmir\rgeht\res\r\rgut!";
		// System.out.println((new StringBuilder(String.valueOf(s.length())))
		// .append("/").append(s).toString());
		// s = "Hallo\rmir\rgeht\res\r\ngut!";
		// System.out.println((new StringBuilder(String.valueOf(s.length())))
		// .append("/").append(s).toString());

		String[] arrStr = { "yours", "aam", "ame", "amg", "qe", "aq", "I" };
		sortStringArray(Arrays.asList(arrStr));
		for (int i = 0; i < arrStr.length; i++) {
			System.out.println(arrStr[i]);
		}
	}

	public static String convertForSend(Object o) {
		if (m_decimalFormat == null) {
			DecimalFormatSymbols decimalformatsymbols = new DecimalFormatSymbols();
			decimalformatsymbols.setDecimalSeparator('.');
			m_decimalFormat = new DecimalFormat("#.0##########", decimalformatsymbols);
		}
		if (o == null)
			return null;
		String s = o.toString();
		if ((o instanceof BigDecimal) && s.indexOf('.') < 0)
			s = (new StringBuilder(String.valueOf(s))).append(".0").toString();
		else if ((o instanceof Float) && s.indexOf('E') > 0)
			s = m_decimalFormat.format(((Float) o).doubleValue());
		else if ((o instanceof Double) && s.indexOf('E') > 0)
			s = m_decimalFormat.format(((Double) o).doubleValue());
		s = s.replace('\b', '\001');
		s = s.replace('\t', '\002');
		s = s.replace('\n', '\003');
		s = s.replace('\f', '\004');
		s = s.replace('\r', '\005');
		s = s.replace('&', '\006');
		s = s.replace('<', '\035');
		s = s.replace('>', '\036');
		return s;
	}

	public static String convertForReceive(String text) {
		if (text == null) {
			return null;
		} else {
			text = text.replace('\001', '\b');
			text = text.replace('\002', '\t');
			text = text.replace('\003', '\n');
			text = text.replace('\004', '\f');
			text = text.replace('\005', '\r');
			text = text.replace('\006', '&');
			text = text.replace('\035', '<');
			text = text.replace('\036', '>');
			return text;
		}
	}

	public static String convertObjectIntoString(Object obj) {
		if (m_decimalFormat == null) {
			DecimalFormatSymbols decimalformatsymbols = new DecimalFormatSymbols();
			decimalformatsymbols.setDecimalSeparator('.');
			m_decimalFormat = new DecimalFormat("#.0", decimalformatsymbols);
		}
		if (obj == null)
			return "";
		String s = obj.toString();
		if ((obj instanceof BigDecimal) && s.indexOf('.') < 0)
			s = (new StringBuilder(String.valueOf(s))).append(".0").toString();
		else if ((obj instanceof Float) && s.indexOf('E') > 0)
			s = m_decimalFormat.format(((Float) obj).doubleValue());
		else if ((obj instanceof Double) && s.indexOf('E') > 0)
			s = m_decimalFormat.format(((Double) obj).doubleValue());
		return s;
	}

	public static String convertFileSeparators(String text) {
		if (text == null)
			return null;
		text = text.replace('\\', '/');
		if (s_fileSeparator.equals("/"))
			return text;
		else
			return replaceInString("/", s_fileSeparator, text);
	}

	public static String convertHTMLStringToSWTString(String htmlFormatedString) {
		if (htmlFormatedString == null)
			return null;
		for (int i = htmlFormatedString.indexOf("<"); i >= 0; i = htmlFormatedString.indexOf("<")) {
			int j = htmlFormatedString.indexOf(">");
			htmlFormatedString = (new StringBuilder(String.valueOf(htmlFormatedString.substring(0, i))))
					.append(htmlFormatedString.substring(j + 1)).toString();
		}

		htmlFormatedString = decodeXMLAttributeValue(htmlFormatedString);
		htmlFormatedString = replaceInString("&nbsp;", " ", htmlFormatedString);
		return htmlFormatedString;
	}

	public static String encodeXMLAttributeValue(String s) {
		if (s == null) {
			return null;
		} else {
			String s1 = s;
			s1 = replaceInString("<", "&lt;", s1);
			s1 = replaceInString(">", "&gt;", s1);
			s1 = replaceInString("&", "&amp;", s1);
			s1 = replaceInString("'", "&apos;", s1);
			s1 = replaceInString("\"", "&quot;", s1);
			s1 = replaceInString("\n", "&#10;", s1);
			return s1;
		}
	}

	public static String decodeXMLAttributeValue(String s) {
		if (s == null) {
			return null;
		} else {
			String s1 = s;
			s1 = replaceInString("&lt;", "<", s1);
			s1 = replaceInString("&gt;", ">", s1);
			s1 = replaceInString("&amp;", "&", s1);
			s1 = replaceInString("&apos;", "'", s1);
			s1 = replaceInString("&quot;", "\"", s1);
			s1 = replaceInString("&#10;", "\n", s1);
			return s1;
		}
	}

	public static String removeLineFeed(String s) {
		if (s == null) {
			return null;
		} else {
			String s1 = s;
			s1 = replaceInString("\n", "", s1);
			return s1;
		}
	}

	public static String[] separateIntoLines(String text) {
		ArrayList<String> arraylist = new ArrayList<String>();
		int i = -1;
		do {
			int j = text.indexOf("\n", i + 1);
			if (j < 0) {
				if (text.length() > i + 2)
					arraylist.add(text.substring(i + 1, text.length()));
				break;
			}
			arraylist.add(text.substring(i + 1, j));
			i = j;
		} while (true);
		String as[] = new String[arraylist.size()];
		arraylist.toArray(as);
		return as;
	}

	public static String firstCharToUpperCase(String s) {
		if (s == null)
			return null;
		if (s.equals(""))
			return s;
		if (s.length() == 1)
			return s.toUpperCase();
		else
			return (new StringBuilder(String.valueOf(s.substring(0, 1).toUpperCase())))
					.append(s.substring(1, s.length())).toString();
	}

	public static String findControlBasedHotkeyKeyCode(String name) {
		if (name == null)
			return null;
		int i = name.indexOf("~~");
		if (i >= 0 && i != name.length() - 2) {
			char c = name.charAt(name.indexOf("~~") + 2);
			if (c > 'a')
				c -= ' ';
			return (new Integer(c)).toString();
		} else {
			return null;
		}
	}

	public static String convertDoubleTildeToUTag(String text) {
		if (text == null)
			return null;
		int i = text.indexOf("~~");
		if (i < 0)
			return text;
		int j = 0;
		StringBuffer stringbuffer = new StringBuffer();
		for (; i >= 0 && i + 2 < text.length(); i = text.indexOf("~~", j)) {
			stringbuffer.append(text.substring(j, i));
			stringbuffer.append("<u>");
			stringbuffer.append(text.charAt(i + 2));
			stringbuffer.append("</u>");
			j = i + 3;
		}

		stringbuffer.append(text.substring(j, text.length()));
		return stringbuffer.toString();
	}

	public static int findLastIndexOf(String s, char c) {
		int i = -1;
		do {
			int j = s.indexOf(c, i + 1);
			if (j < 0)
				return i;
			i = j;
		} while (true);
	}

	public static boolean isNotEmptyId(String id) {
		if (id == null)
			return false;
		if (id.equals("0"))
			return false;
		if (id.equals(""))
			return false;
		return true;
	}

	public static boolean isNotEmpty(String str) {
		if (str == null)
			return false;
		if (str.trim().equals(""))
			return false;
		return true;
	}

	private static String CAN_BE_USE_CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_";
	private static DecimalFormat m_decimalFormat = null;
	static String s_fileSeparator = System.getProperty("file.separator");
	public static final String DOUBLETILDE = "~~";

	/**
	 * 取得文件类型后缀类型
	 * 
	 * @param name
	 * @return
	 */
	public static String getFileTypeSuffix(String name) {
		if (StringUtil.isNull(name))
			return "";
		int posi = name.lastIndexOf(FULLEND);
		if (posi < 0)
			return "";
		else {
			return name.substring(posi + 1, name.length());
		}
	}

	/**
	 * 根据url地址取得返回的字符串
	 * <p>
	 * 主要用于读取公司广告窗口
	 * 
	 * @param s_url
	 *            地址
	 * @return
	 */
	public static String getXMLStringFromURL(String s_url) {
		try {
			URL url = new URL(s_url);
			URLConnection urlconnection = url.openConnection();
			InputStream is = urlconnection.getInputStream();
			StringBuffer stringbuffer = new StringBuffer();
			InputStreamReader inputstreamreader = new InputStreamReader(is);
			BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
			do {
				String s4 = bufferedreader.readLine();
				if (s4 == null) {
					break;
				}
				stringbuffer.append(s4);
			} while (true);
			bufferedreader.close();

			return stringbuffer.toString();
		} catch (MalformedURLException e) {
			log.error(e.getMessage());
			return "";
		} catch (IOException e) {
			log.error(e.getMessage());
			return "";
		}
	}

	/**
	 * 把以分号隔开的字符串,转化成字符串数组
	 * 
	 * @param str
	 * @return
	 */
	public static String[] decodeString(String str, String separator) {
		if (str.length() == 0) {
			return new String[0];
		}
		Vector<String> vector = new Vector<String>();
		int i = 0;
		int j = str.indexOf(separator);
		String s2 = "";
		for (; j >= 0; j = str.indexOf(separator, j + 1)) {
			if (j == 0 || str.charAt(j - 1) != '\\') {
				s2 = s2 + str.substring(i, j);
				vector.addElement(s2);
				s2 = "";
			} else {
				s2 = s2 + str.substring(i, j - 1) + separator;
			}
			i = j + 1;
		}

		vector.addElement(s2 + str.substring(i, str.length()));
		String as[] = new String[vector.size()];
		vector.copyInto(as);
		return as;
	}

	/**
	 * 翻译星期
	 * 
	 * @param day_of_week
	 * @return
	 */
	public static String getCNWeek(int day_of_week) {
		String s_week = "";
		switch (day_of_week) {
		case 1:
			s_week = "星期日";
			break;
		case 2:
			s_week = "星期一";
			break;
		case 3:
			s_week = "星期二";
			break;
		case 4:
			s_week = "星期三";
			break;
		case 5:
			s_week = "星期四";
			break;
		case 6:
			s_week = "星期五";
			break;
		case 7:
			s_week = "星期六";
			break;
		default:
			s_week = "";
			break;
		}
		return s_week;
	}

	public static boolean isNumber(String id) {
		if (isNull(id))
			return false;
		try {
			Long.parseLong(id);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 检测字符串是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNull(String s) {
		if (s == null || "".equals(s.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 字符串转换
	 * 
	 * @param s
	 * @return
	 */
	public static String valueOf(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}

	/**
	 * 数据填零
	 * 
	 * @param no
	 * @param len
	 * @return
	 */
	public static String zeroFill(int no, int len) {
		String s = String.valueOf(no);
		String zero = "";
		for (int i = 0; i < len; i++) {
			zero = zero + "0";
		}
		if (s.length() >= len) {
			return s;
		}
		zero = zero.substring(0, zero.length() - s.length());
		return zero + s;
	}

	/**
	 * 数据填零
	 * 
	 * @param no
	 * @param len
	 * @return
	 */
	public static String emptyFill(String value, int len) {
		if (StringUtil.isNull(value)) {
			String empty = "";
			for (int i = 0; i < len; i++) {
				empty = empty + " ";
			}
			return empty;
		}
		if (value.length() >= len) {
			return value;
		}
		for (int j = value.length(); j < len; j++) {
			value = value + " ";
		}
		return value;
	}

	/**
	 * 按标识读取分词
	 * 
	 * @param s
	 * @param delim
	 */
	public static String[] getElements(String s, String delim) {
		if (s == null) {
			return new String[0];
		}
		List<String> elements = new ArrayList<String>();
		StringTokenizer tokenizer = new StringTokenizer(s, delim);
		while (tokenizer.hasMoreTokens()) {
			elements.add(tokenizer.nextToken().trim());
		}
		return elements.toArray(new String[elements.size()]);
	}

	/**
	 * 将字符窜数组合并为字符窜
	 * 
	 * @param elements
	 * @param delim
	 * @return
	 */
	public static String joinElements(String elements[], String delim) {
		if (elements == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < elements.length; i++) {
			if (i > 0) {
				sb.append(delim);
			}
			if (elements[i] != null) {
				sb.append(elements[i].trim());
			}
		}
		return sb.toString();
	}

	/**
	 * 检测数组中是否包含量值
	 * 
	 * @param elements
	 * @param element
	 * @return
	 */
	public static boolean contains(String[] elements, String element) {
		if (elements == null || element == null) {
			return false;
		}
		for (int i = 0; i < elements.length; i++) {
			if (element.equals(elements[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 定位
	 * 
	 * @param elements
	 * @param element
	 * @return
	 */
	public static int index(String[] elements, String element) {
		if (elements == null || element == null) {
			return -1;
		}
		for (int i = 0; i < elements.length; i++) {
			if (element.equals(elements[i])) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 替换字符
	 * 
	 * @param s
	 * @param oldSub
	 * @param newSub
	 * @return
	 */
	public static String replace(String s, char oldSub, char newSub) {
		return replace(s, oldSub, new Character(newSub).toString());
	}

	/**
	 * 替换字符
	 * 
	 * @param s
	 * @param oldSub
	 * @param newSub
	 * @return
	 */
	public static String replace(String s, char oldSub, String newSub) {
		if ((s == null) || (newSub == null)) {
			return null;
		}

		char[] c = s.toCharArray();

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < c.length; i++) {
			if (c[i] == oldSub) {
				sb.append(newSub);
			} else {
				sb.append(c[i]);
			}
		}

		return sb.toString();
	}

	/**
	 * 替换子串
	 * 
	 * @param s
	 * @param oldSub
	 * @param newSub
	 * @return
	 */
	public static String replace(String s, String oldSub, String newSub) {
		if ((s == null) || (oldSub == null) || (newSub == null)) {
			return null;
		}

		int y = s.indexOf(oldSub);

		if (y >= 0) {
			StringBuffer sb = new StringBuffer();

			int length = oldSub.length();
			int x = 0;

			while (x <= y) {
				sb.append(s.substring(x, y));
				sb.append(newSub);
				x = y + length;
				y = s.indexOf(oldSub, x);
			}

			sb.append(s.substring(x));

			return sb.toString();
		} else {
			return s;
		}
	}

	public static String shorten(String s) {
		return shorten(s, 20);
	}

	public static String shorten(String s, int length) {
		return shorten(s, length, "..");
	}

	public static String shorten(String s, String suffix) {
		return shorten(s, 20, suffix);
	}

	public static String shorten(String s, int length, String suffix) {
		if (s == null || suffix == null) {
			return null;
		}

		return substring(s, length, suffix);
	}

	/***
	 * 按字节长度截取字符串
	 * 
	 * @param str
	 *            将要截取的字符串参数
	 * @param toCount截取的字节长度
	 * @param more
	 *            字符串末尾补上的字符串
	 * @return 返回截取后的字符串
	 */
	public static String substring(String str, int toCount, String more) {
		int reInt = 0;
		String reStr = "";
		if (str == null)
			return "";
		char[] tempChar = str.toCharArray();
		for (int kk = 0; (kk < tempChar.length && toCount > reInt); kk++) {
			String s1 = String.valueOf(tempChar[kk]);
			byte[] b = s1.getBytes();
			reInt += b.length;
			if (reInt <= toCount) {
				reStr += tempChar[kk];
			} else {
				break;
			}
		}
		if (toCount == reInt || (toCount == reInt - 1))
			reStr += more;
		return reStr;
	}

	/**
	 * 根据类名取得类的小写全称,包括包名
	 * 
	 * @param clazz
	 * @return
	 */
	public static String classToLowerName(Class<?> clazz) {
		return clazz.getName().toLowerCase();
	}

	/**
	 * 转化类名为html页面名称
	 * 
	 * @param clazz
	 * @return
	 */
	public static String classToHtmlName(Class<?> clazz) {
		return classToLowerName(clazz) + ".html";
	}

	/**
	 * 格式化金额，不带千分位，用于输入框显示
	 * 
	 * @author yuq
	 * @param Double
	 * @return String
	 */
	public static String FormatNumber(Double data) {
		DecimalFormat myFormatter = new DecimalFormat(CURRENCY_PATTERN);
		return myFormatter.format(data);
	}

	/**
	 * 格式化金额，不带千分位，用于输入框显示
	 * 
	 * @author yuq
	 * @param double
	 * @return String
	 */
	public static String FormatNumber(double data) {
		DecimalFormat myFormatter = new DecimalFormat(CURRENCY_PATTERN);
		return myFormatter.format(data);
	}

	/**
	 * 将null字符串转换为""
	 * 
	 * @param str
	 * @return 若str不为null则返回原字符串，否则返回空字符串
	 */
	public static String formateNullStr(String str) {
		if (isNull(str))
			return "";
		else
			return str;
	}

	/**
	 * 口令强弱等级判定,强度算法有待商戳，功能基本实现 指示口令等级 0 不合法口令 1 太短 2 弱 3 一般 4 很好 5 极佳
	 * 
	 * @param pass
	 * @return 2005-12-14 16:55:03 Made In GamVan com.gamvan.club.servlet
	 */
	public static int rateUserPass(String pass) {
		/*
		 * i 值指示口令等级 0 不合法口令 1 太短 2 弱 3 一般 4 很好 5 极佳
		 */
		int i = 0;
		if (pass == null || pass.length() == 0) {
			return 0;
		}
		int hasLetter = matcherStr(pass, "[a-zA-Z]", "").length();
		int hasNumber = matcherStr(pass, "[0-9]", "").length();
		int passLen = pass.length();
		if (passLen >= 6) {
			/* 如果仅包含数字或仅包含字母 */
			if ((passLen - hasLetter) == 0 || (passLen - hasNumber) == 0) {
				if (passLen < 8) {
					i = 2;
				} else {
					i = 3;
				}
			}
			/* 如果口令大于6位且即包含数字又包含字母 */
			else if (hasLetter > 0 && hasNumber > 0) {
				if (passLen >= 10) {
					i = 5;
				} else if (passLen >= 8) {
					i = 4;
				} else {
					i = 3;
				}
			}
			/* 如果既不包含数字又不包含字母 */
			else if (hasLetter == 0 && hasNumber == 0) {
				if (passLen >= 7) {
					i = 5;
				} else {
					i = 4;
				}
			}
			/* 字母或数字有一方为0 */
			else if (hasNumber == 0 || hasLetter == 0) {
				if ((passLen - hasLetter) == 0 || (passLen - hasNumber) == 0) {
					i = 2;
				}
				/*
				 * 字母数字任意一种类型小于6且总长度大于等于6 则说明此密码是字母或数字加任意其他字符组合而成
				 */
				else {
					if (passLen > 8) {
						i = 5;
					} else if (passLen == 8) {
						i = 4;
					} else {
						i = 3;
					}
				}
			}
		} else { // 口令小于6位则显示太短
			if (passLen > 0) {
				i = 1; // 口令太短
			} else {
				i = 0;
			}
		}
		return i;
	}

	/**
	 * 循环找出匹配内容
	 * 
	 * @param str
	 * @param cp
	 * @param s
	 * @return 2005-12-11 18:45:25 Made In GamVan com.gamvan.tools
	 */
	public static String matcherStr(String str, String cp, String s) {
		if (str == null || str.equals("")) {
			return "";
		}
		String txt = str;
		if (str != null && !str.equals("")) {
			txt = str;
			Pattern p = Pattern.compile(cp, 2); // 参数2表示大小写不区分
			Matcher m = p.matcher(txt);
			StringBuffer sb = new StringBuffer();
			boolean result = m.find();
			// 使用循环将句子里所有匹配的内容找出并替换再将内容加到sb里
			while (result) {
				sb.append(m.group());
				sb.append(s);
				// 继续查找下一个匹配对象
				result = m.find();
			}
			txt = String.valueOf(sb);
		} else {
			txt = "";
		}
		return txt;
	}

	/**
	 * 取得leftLabel到rightLabel都匹配的内容
	 * 如"leftLabel悲催rightsLabel上班好累啊",返回"leftLabel悲催rightLabel"
	 * 
	 * @param content
	 * @param leftLabel
	 * @param rightLabel
	 * @return
	 */
	public static String getLabelContent(String content, String leftLabel, String rightLabel) {
		String reg = leftLabel + "(.+)" + rightLabel;
		java.util.regex.Pattern pat = java.util.regex.Pattern.compile(reg);
		java.util.regex.Matcher mat = pat.matcher(content);
		StringBuffer sb = new StringBuffer();
		if (mat.find()) {
			sb.append(leftLabel);
			sb.append(mat.group(1).toString());
			sb.append(rightLabel);
		}
		return sb.toString();
	}

	/**
	 * 拆开字符串
	 * 
	 * @param aSeparator
	 * @param aInput
	 * @return
	 */
	public static ArrayList<String> strSplit(final String aSeparator, final String aInput) {
		ArrayList<String> token = new ArrayList<String>();

		if (aSeparator == null || aSeparator.equals("")) {
			token.add(aInput);
			return token;
		}
		if (aInput == null || aInput.equals("")) {
			return token;
		}

		// startIdx and idxOld delimit various chunks of aInput; these
		// chunks always end where aOldPattern begins
		int startIdx = 0;
		int idxOld = 0;
		while ((idxOld = aInput.indexOf(aSeparator, startIdx)) >= 0) {
			// grab a part of aInput which does not include aOldPattern

			token.add(aInput.substring(startIdx, idxOld));
			// reset the startIdx to just after the current match, to see
			// if there are any further matches
			startIdx = idxOld + aSeparator.length();
		}
		// the final chunk will go to the end of aInput
		token.add(aInput.substring(startIdx));

		return token;
	}

	/**
	 * 
	 * 一个中文字符长度为2,其他为1
	 * 
	 * @param targetStr
	 * @return String长度(一个中文字符长度为2,其他为1)
	 */
	public static int getStrLength(String targetStr) {
		String input = targetStr;
		int count_b = input.length();
		int count_c = count_b + getChineseCount(input);
		return count_c;
	}

	/**
	 * 
	 * @param targetStr
	 * @return 返回中文字符个数
	 */
	public static int getChineseCount(String targetStr) {
		int count = 0;

		String input = targetStr;
		String temp = null;
		Pattern p = Pattern.compile("[\u4E00-\u9FA5]+");
		Matcher m = p.matcher(input);
		while (m.find()) {
			temp = m.group(0);
			count += temp.length();
		}

		return count;
	}

	public static List<String> toList(String s, String split) {
		List<String> list = new ArrayList<String>();
		String[] itemArr = s.split(split);
		for (int i = 0; i < itemArr.length; i++) {
			list.add(itemArr[i]);
		}
		return list;
	}

	public static String leftFill(int value, String fillValue, int totalLength) {
		String values = String.valueOf(value);
		while (values.length() < totalLength) {
			values = fillValue + values;
		}
		return values;
	}

	public static String rightFill(int value, String fillValue, int totalLength) {
		String values = String.valueOf(value);
		while (values.length() < totalLength) {
			values = values + fillValue;
		}
		return values;
	}

	public static void sortStringArray(String[] arrStr) {
		String temp;
		for (int i = arrStr.length - 1; i > 1; i--) {
			for (int j = 0; j < i; j++) {
				if (arrStr[i].length() > arrStr[j].length()) {
					temp = arrStr[j];
					arrStr[j] = arrStr[i];
					arrStr[i] = temp;
				}
			}
		}
	}

	public static void sortStringArray(List<String> arrStr) {
		String temp;
		for (int i = arrStr.size() - 1; i > 1; i--) {
			for (int j = 0; j < i; j++) {
				if (arrStr.get(i).length() > arrStr.get(j).length()) {
					temp = arrStr.get(j);
					arrStr.set(j, arrStr.get(i));
					arrStr.set(i, temp);
				}
			}
		}
	}
}