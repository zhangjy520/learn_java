package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StreamUtil.class);
	
	/**
	 * 从输入流中获取字符串
	 * @param is
	 * @return
	 */
	public static String getString(InputStream is){
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line;
			while(null != (line = reader.readLine())) {
				sb.append(line);
			}
		} catch (Exception e) {
			LOGGER.error("get string failure ", e);
			throw new RuntimeException(e);
		}
		return sb.toString();
	}
	
}
