package com.translate.po;

import java.util.ArrayList;
import java.util.List;

import com.translate.util.BaiduTranslateUtil;

public class Content {

	private String content;
	private List<Phrase> phraseList;

	public List<Phrase> getPhraseList() {
		return phraseList;
	}

	public void setPhraseList(List<Phrase> phraseList) {
		this.phraseList = phraseList;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String baiduTranslageBySentenses(String language) {

		String translateResult = "";

		this.phraseList = new ArrayList<Phrase>();

		String srcFormatToEn = BaiduTranslateUtil.srcFormatToEn(content, language);
		String[] split = srcFormatToEn.split("\\r\\n");
		for (int i = 0; i < split.length; i++) {
			String phraseContent = split[i];
			Phrase phrase = new Phrase();
			this.phraseList.add(phrase);

			System.out.println("分段内容:" + phraseContent);

			phrase.setPhrase(phraseContent);
			String phraseTranslate = phrase.baiduTranslageBySentenses(language);

			translateResult += "\r\n\t" + phraseTranslate + "\r\n";
		}

		return translateResult;
	}

}
