package com.translate.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class BaiduTranslateUtil {

	private static String TRANSLATE_URL = "http://api.fanyi.baidu.com/api/trans/vip/translate";
	private static String APPID = "20180103000111543";
	private static String P = "hKhTn4umupyJxY61Beny";
	private static String FROM = "auto";
	private static String TO = "zh";
	private static Integer LIMIT_CHINESE = 2000;
	private static Integer LIMIT_ENGLISH = 6000;

	// 语言简写 名称
	// auto 自动检测
	// zh 中文
	// en 英语
	// yue 粤语
	// wyw 文言文
	// jp 日语
	// kor 韩语
	// fra 法语
	// spa 西班牙语
	// th 泰语
	// ara 阿拉伯语
	// ru 俄语
	// pt 葡萄牙语
	// de 德语
	// it 意大利语

	// 请将单次请求长度控制在 6000 bytes以内。（汉字约为2000个）

	public static void main(String[] args) throws Exception {

		// Scanner sn = new Scanner(System.in);
		// String query = sn.nextLine();
		// sn.close();
		// String query = "this is a test(这是个测试).\r\ngive you some coler to see see";

		String src = "Dear Sir/Ma’m,\r\n" + "Good Morning/Afternoon/Evening!(根据实际情况修改)\r\n" + "\r\n" + "Thank you very much for your time to be concerned on our views and feedbacks to the HCS team (which is now known as LCS team) in China.\r\n" + "\r\n" + "	We have sent our feedbacks through emails to Diana Tang and Yuan Hongyu respectively when the China market first announced that the LSS and PCS would be combined. But these two people did not do any investigation. It is not until we sent the emails again to Duan Xiaoying – CEO in China, and the Legal Department that did some of the related investigations begin. Not unnaturally, we didn’t receive the results that we expected. We have a feeling that Diana Tang and Yuan Hongyu were hiding back from this situation, or in other words, in China, we need a strong social relationship and money to have problems solved. True or not, your people know.\r\n" + "\r\n" + "	As a multinational group who values credit, you must have had faith in your employees. However, it is a very long distance between China and USA. We hope that you could pay more attention to the development of the LCS team in China due to the much difference of principle between Chinese and Americans. We also wish that you could train more and more professional manages, instead of leaders that share profits with us but still having GE bonus.\r\n" + "\r\n" + "	Luo Ming, manager of Northeast area, is always asking us to share resources with him, like sharing profit. He also gains profit through other ways: such as, expelling other agents by cooperating a Da Lian agent (whose family name is Sun) in the long term because he asks the agent to help his child to get in a better school, being carrying on with many of the sales women in this agent company, and also having affairs with the female colleges in GE. He has regarded himself as Tiger Woods. Due to your long distance to the sales market, many of your sales managers have their own thoughts and actions which deviate from yours. They would always ask for profits from the agents, which is very common, especially in LCS. And we believe that Diana Tang and Yuan Hongyu have already heard of this. We have no other choices but to send the email to you, hoping that you could help to pay more attention to the problems in LCS market and that you could have a talk with Luo Ming that if he wants to open his own company, let him be, and we do not need to be here to do whatever he tells us to do; if not, please do what we believes in.\r\n" + "\r\n" + "We hope that we could get the results as soon as possible. We do not expect to see that the problems are solved because of relationship or money. We expect a healthy cooperation with GE. We have to be anonymous to avoid revenge because many people have already known the content of last anonymous report. However, we still wish to co-operate with a sincere and honest company.\r\n" + "\r\n" + "Thank you!\r\n" + "";

		System.out.println("请求文字节长度:" + src.getBytes().length);

		// src = "实际情况";

		System.out.println();

		List<String> translateresult = translateFromEnToZn(src);
		for (String result : translateresult)
			System.out.println(result);
	}

	private static List<String> translateFromEnToZn(String src) throws Exception {

		String srcFormat = srcFormat(src, "en");

		System.out.println("参与翻译的字符串:" + srcFormat);

		return translate(srcFormat, "en", "zh");
	}

	private static String srcFormat(String src, String string) {

		String srcStr = "";

		// 过滤掉中文
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(src);
		if (m.find()) {
			char[] charArray = src.toCharArray();
			for (int i = 0; i < charArray.length; i++) {
				String cStr = String.valueOf(charArray[i]);
				m = p.matcher(cStr);
				if (!m.find()) {
					srcStr += cStr;
				}
			}
		}

		// 标点符号转换
		if (srcStr.contains("‘"))
			srcStr = srcStr.replace("‘", "'");
		if (srcStr.contains("’"))
			srcStr = srcStr.replace("’", "'");
		if (srcStr.contains("”"))
			srcStr = srcStr.replace("“", "\"");
		if (srcStr.contains("”"))
			srcStr = srcStr.replace("“", "\"");
		if (srcStr.contains("："))
			srcStr = srcStr.replace("", ":");
		if (srcStr.contains("，"))
			srcStr = srcStr.replace("", ",");
		if (srcStr.contains("。"))
			srcStr = srcStr.replace("", ".");
		if (srcStr.contains("？"))
			srcStr = srcStr.replace("", "?");
		if (srcStr.contains("！"))
			srcStr = srcStr.replace("！", "!");
		if (srcStr.contains("（"))
			srcStr = srcStr.replace("（", "(");
		if (srcStr.contains("）"))
			srcStr = srcStr.replace("）", ")");
		if (srcStr.contains("《"))
			srcStr = srcStr.replace("《", "<");
		if (srcStr.contains("》"))
			srcStr = srcStr.replace("》", ">");

		return srcStr;
	}

	public static List<String> translate(String src, String from, String to) throws Exception {

		int length = src.getBytes().length;
		if (length > getLimitNum(from)) {
			throw new Exception("字节长度超长");
		}

		String q = src;
		if ("".equals(q)) {
			List<String> resultList = new ArrayList<String>();
			resultList.add(q);
			return resultList;
		}

		q = q.replace("–", "");

		Integer salt_int = (int) ((Math.random() * 100) / 1);
		String salt = String.valueOf(salt_int);

		String sign_source = APPID + q + salt_int + P;
		String sign = DigestUtils.md5Hex(sign_source);
		// get请求
		// q = URLEncoder.encode(q, "UTF-8");
		String result_url = TRANSLATE_URL + "?" + "q=" + q + "&from=" + from + "&to=" + to + "&appid=" + APPID + "&salt=" + salt + "&sign=" + sign;

		String result = HttpUtil.postWithParams(result_url);

		System.out.println("百度翻译响应结果>>>:" + result);

		List<String> transelated_result = analyzeResult(result, q);
		return transelated_result;
	}

	private static List<String> analyzeResult(String result, String q) throws Exception {

		JSONObject json = JSONObject.parseObject(result);

		String error_code = json.getString("error_code");
		if (null != error_code) {
			switch (error_code) {
			case "54001":
				throw new Exception("签名错误");
			}
		}

		JSONArray jsonArray = json.getJSONArray("trans_result");

		List<String> resultList = new ArrayList<String>();

		Iterator<Object> iterator = jsonArray.iterator();
		while (iterator.hasNext()) {
			JSONObject next = (JSONObject) iterator.next();
			String dst = next.getString("dst");
			resultList.add(dst);
		}

		return resultList;
	}

	public static Integer getLimitNum(String language) throws Exception {

		switch (language) {
		case "en":
			return LIMIT_ENGLISH;
		case "zh":
			return LIMIT_CHINESE;
		}

		throw new Exception("没有匹配类型");
	}
}
