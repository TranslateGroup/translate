package com.translate;

import com.translate.service.ContentBusin;

public class StartEngine {

	public static void main(String[] args) throws Exception {
		System.out.println("项目引擎启动");

		doTranslate();

		System.out.println("项目结束");
	}

	private static void doTranslate() throws Exception {

		String srcPath = "C:\\Users\\misez\\Desktop\\翻译\\文件";
		String dstPath = "C:\\Users\\misez\\Desktop\\翻译\\文件\\dst";

		ContentBusin contentBusin = new ContentBusin();
		contentBusin.translateFromEnToZnReadAndWrite(srcPath, dstPath);
	}
}
