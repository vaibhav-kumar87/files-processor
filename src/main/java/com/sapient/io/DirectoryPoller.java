package com.sapient.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import com.sapient.domain.FileAttributes;
import com.sapient.io.factory.CSVBatchProcessor;
import com.sapient.io.factory.TextBatchProcessor;
import com.sapient.service.FileCache;

public class DirectoryPoller extends Thread {
	private TextBatchProcessor textBatchProcessor;
	private CSVBatchProcessor csvBatchProcessor;
	private FileCache<FileAttributes, Long> fileCache;
	private Path directory;
	
	public DirectoryPoller(FileCache<FileAttributes, Long> fileCache, String directory) {
		super();
		this.textBatchProcessor = new TextBatchProcessor(fileCache);
		this.csvBatchProcessor = new CSVBatchProcessor(fileCache);
		this.directory = Paths.get(directory);
		this.fileCache = fileCache;
	}
	
	public void run()
	{
		try (Stream<Path> paths = Files.walk(Paths.get(directory.toUri()))) {
			paths.forEach(filePath -> {
				System.out.println(filePath);
				if (filePath.toString().endsWith(".txt")) {
					this.textBatchProcessor.processRecords(filePath);
				}
				if (filePath.toString().endsWith(".csv")) {
					this.csvBatchProcessor.processRecords(filePath);
				}
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public TextBatchProcessor getTextBatchProcessor() {
		return textBatchProcessor;
	}

	public void setTextBatchProcessor(TextBatchProcessor textBatchProcessor) {
		this.textBatchProcessor = textBatchProcessor;
	}

	public CSVBatchProcessor getCsvBatchProcessor() {
		return csvBatchProcessor;
	}

	public void setCsvBatchProcessor(CSVBatchProcessor csvBatchProcessor) {
		this.csvBatchProcessor = csvBatchProcessor;
	}

	public FileCache<FileAttributes, Long> getFileCache() {
		return fileCache;
	}

	public void setFileCache(FileCache<FileAttributes, Long> fileCache) {
		this.fileCache = fileCache;
	}
	
	

}