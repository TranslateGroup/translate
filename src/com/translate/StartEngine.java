package com.translate;

import com.translate.service.Content;

public class StartEngine {

	public static void main(String[] args) {
		System.out.println("项目引擎启动");

		doTranslate();

		System.out.println("项目结束");
	}

	private static void doTranslate() {

		Content content = new Content();
		String translateContent = content.translate();
	}
}
