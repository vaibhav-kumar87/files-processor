package com.sapient.io.factory;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import com.sapient.domain.FileAttributes;
import com.sapient.service.FileCache;

public class TextBatchProcessor extends BatchProcessor {

	public TextBatchProcessor(FileCache<FileAttributes, Long> fileCache) {
		super(fileCache);
		// TODO Auto-generated constructor stub
	}

	@Override
	public FileAttributes createRecord() {

		int vowels = 0, specialChars = 0, words = 1;
		
		List<String> lines = null;
		try {
			lines = Files.readAllLines(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (String line : lines) {
			line = line.toLowerCase();
			for (int i = 0; i < line.length(); ++i) {
				char ch = line.charAt(i);
				if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
					++vowels;
				} else if (ch == '@' || ch == '#' || ch == '$' || ch == '*') {
					++specialChars;
				} else if (ch == ' ') {
					++words;
				}
			}
		}
		return new FileAttributes(filePath.getFileName().toString(), filePath, words, vowels, specialChars);

	}




}
