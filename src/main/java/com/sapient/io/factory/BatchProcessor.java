package com.sapient.io.factory;

import java.nio.file.Path;
import java.util.Date;

import com.sapient.domain.FileAttributes;
import com.sapient.io.MTDFileGenerator;
import com.sapient.service.FileCache;

/**
 * Abstract class to process file attributes
 */
public abstract class BatchProcessor {

	protected FileCache<FileAttributes, Long> fileCache;
	protected Path filePath;
	private MTDFileGenerator fileGenerator;
	
	public BatchProcessor(FileCache<FileAttributes, Long> fileCache) {
		super();
		this.fileCache = fileCache;
		this.fileGenerator = new MTDFileGenerator();
	}

	//Factory method to create FileAttributes object
	protected abstract FileAttributes createRecord(); 

	public void processRecords(Path filePath)
	{
		this.filePath = filePath;
		FileAttributes fAttributes = new FileAttributes(filePath.getFileName().toString());
		if(this.fileCache.containsKey(fAttributes))
		{
			return;
		}
		FileAttributes fileAttributes = createRecord();
		if(!this.fileCache.containsKey(fileAttributes))
		{
			this.fileCache.put(fileAttributes, new Date().getTime());
			generateMTDFile(fileAttributes);
		}
	}

	private void generateMTDFile(FileAttributes fileAttributes) {
		fileGenerator.setFileAttributes(fileAttributes);
		fileGenerator.generateMTDFile();
	}

	public FileCache<FileAttributes, Long> getFileCache() {
		return fileCache;
	}

	public void setFileCache(FileCache<FileAttributes, Long> fileCache) {
		this.fileCache = fileCache;
	}

	public Path getFilePath() {
		return filePath;
	}

	public void setFilePath(Path filePath) {
		this.filePath = filePath;
	}

	public MTDFileGenerator getFileGenerator() {
		return fileGenerator;
	}

	public void setFileGenerator(MTDFileGenerator fileGenerator) {
		this.fileGenerator = fileGenerator;
	}
	
	

}
