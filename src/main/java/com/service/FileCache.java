package com.service;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public class FileCache<K, Long> extends ConcurrentHashMap<K, Long> {

	private static final long serialVersionUID = 1L;

	private long expiryInMillis;

	public FileCache() {
		this.expiryInMillis = 10000;
		initialize();
	}

	public FileCache(long expiryInMillis) {
		this.expiryInMillis = expiryInMillis;
		initialize();
	}

	void initialize() {
		new CleanerThread().start();
	}

	class CleanerThread extends Thread {

		@Override
		public void run() {
			while (true) {
				cleanMap();
				try {
					Thread.sleep(expiryInMillis / 2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		private void cleanMap() {
			long currentTime = new Date().getTime();
			for (K key : keySet()) {
				if (currentTime > ((Date) get(key)).getTime()+expiryInMillis) {
					remove(key);
				}
			}
		}
	}
}