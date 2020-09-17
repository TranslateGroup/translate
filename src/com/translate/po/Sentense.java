package com.translate.po;

import java.util.List;

import com.translate.util.BaiduTranslateUtil;

public class Sentense {

	private String sentense;

	public String getSentense() {
		return sentense;
	}

	public void setSentense(String sentense) {
		this.sentense = sentense;
	}

	public String baiduTranslageSentense(String language) {

		String translateResult = "";

		try {
			Thread.sleep(1000);
			translateResult = translateFromEnToZn(sentense);
		} catch (Exception e) {
			translateResult = "\r\n异常:" + e.getMessage() + "\r\n";
			e.printStackTrace();
		}

		return translateResult;
	}

	public String translateFromEnToZn(String content) throws Exception {

		System.out.println("句入翻译:" + content);

		List<String> translateFromEnToZn = BaiduTranslateUtil.translateFromEnToZn(content);

		String result = "";
		for (int i = 0; i < translateFromEnToZn.size(); i++) {
			String tmp = translateFromEnToZn.get(i);
			result += tmp;
		}

		return result;
	}

}
