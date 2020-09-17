package com.translate.service;

import java.io.File;
import java.util.List;

import com.translate.po.Content;
import com.translate.util.FileUtil;

public class ContentBusin {

	public static void main(String[] args) throws Exception {

		String srcPath = "C:\\Users\\misez\\Desktop\\翻译\\文件";
		String dstPath = "C:\\Users\\misez\\Desktop\\翻译\\文件\\dst";

		ContentBusin contentBusin = new ContentBusin();
		contentBusin.translateFromEnToZnReadAndWrite(srcPath, dstPath);

	}

	public void translateFromEnToZnReadAndWrite(String srcPath, String dstPath) throws Exception {

		List<File> fileListInDir = FileUtil.fileListInDir(srcPath);

		for (int i = 0; i < fileListInDir.size(); i++) {
			File file = fileListInDir.get(i);
			String name = file.getName();

			String fileContent = FileUtil.fileReadToString(file);

			System.out.println("翻译文件:" + name);
			System.out.println("文件内容:" + fileContent);

			Content content = new Content();
			content.setContent(fileContent);

			String translateResult = content.baiduTranslageBySentenses("en");

			File dstFile = new File(dstPath + "/" + name);
			FileUtil.writeIntoFile(dstFile, translateResult);
		}

	}

}
