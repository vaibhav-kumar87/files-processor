package com;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.domain.FileAttributes;
import com.io.DirectoryPoller;
import com.service.FileCache;
import com.service.PropertiesCache;

public class Application {

	public static void main(String[] args) {
		FileCache<FileAttributes, Long> fileCache = new FileCache<FileAttributes, Long>(Long.valueOf(PropertiesCache.getInstance().getProperty("expiryInMillis")));
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(new DirectoryPoller(fileCache, "F:\\Test"), 2, 5, TimeUnit.SECONDS);

	}
}
