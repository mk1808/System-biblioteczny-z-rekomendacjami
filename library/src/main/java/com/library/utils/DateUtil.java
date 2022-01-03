package com.library.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class DateUtil {
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public LocalDateTime now() {
		return LocalDateTime.now();
	}
}
