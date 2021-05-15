package com.sapient.domain;

import java.nio.file.Path;

public class FileAttributes {
	private String name;
	private Path path;
	private int words;
	private int vowels;
	private int specialChars;

	public FileAttributes(String name, Path path, int words, int vowels, int specialChars) {
		super();
		this.name = name;
		this.path = path;
		this.words = words;
		this.vowels = vowels;
		this.specialChars = specialChars;
	}
	
	public FileAttributes(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	public int getWords() {
		return words;
	}

	public void setWords(int words) {
		this.words = words;
	}

	public int getVowels() {
		return vowels;
	}

	public void setVowels(int vowels) {
		this.vowels = vowels;
	}

	public int getSpecialChars() {
		return specialChars;
	}

	public void setSpecialChars(int specialChars) {
		this.specialChars = specialChars;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileAttributes other = (FileAttributes) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return words + "," + vowels + "," + specialChars;
	}

	
}
