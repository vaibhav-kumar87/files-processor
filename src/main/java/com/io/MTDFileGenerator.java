package com.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import com.domain.FileAttributes;

public class MTDFileGenerator {

	private FileAttributes fileAttributes;

	public MTDFileGenerator() {
		super();
	}

	public MTDFileGenerator(FileAttributes fileAttributes) {
		super();
		this.fileAttributes = fileAttributes;
	}

	public boolean generateMTDFile() {
		boolean isCreated = false;
		Path mtd = Paths.get(fileAttributes.getPath().toString() + ".mtd");
		try {
			Files.write(mtd, Arrays.asList(fileAttributes.toString()));
			isCreated = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isCreated = false;
		}
		return isCreated;

	}

	public FileAttributes getFileAttributes() {
		return fileAttributes;
	}

	public void setFileAttributes(FileAttributes fileAttributes) {
		this.fileAttributes = fileAttributes;
	}

}
