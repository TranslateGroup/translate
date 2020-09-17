package com.translate.po;

import java.util.ArrayList;
import java.util.List;

public class Phrase {

	private String phrase;
	private List<Sentense> sentenseList;

	public List<Sentense> getSentenseList() {
		return sentenseList;
	}

	public void setSentenseList(List<Sentense> sentenseList) {
		this.sentenseList = sentenseList;
	}

	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}

	public String baiduTranslageBySentenses(String language) {

		String translateResult = "";
		if ("".equals(phrase.trim())) {
			return translateResult;
		}

		List<String> puncList = new ArrayList<String>();
		puncList.add(".");
		puncList.add("!");
		puncList.add("?");

		int pointLocation = 0;

		for (int i = 0; i < phrase.length(); i++) {
			if (puncList.contains(String.valueOf(phrase.charAt(i)))) {
				String sentenseContent = phrase.substring(pointLocation, i);
				pointLocation = i;

				Sentense sentense = new Sentense();
				sentense.setSentense(sentenseContent);

				System.out.println("句内容:" + sentenseContent);

				String sentenseTranselate = sentense.baiduTranslageSentense(language);
				translateResult += sentenseTranselate;
			}
		}

		return translateResult;
	}

}
