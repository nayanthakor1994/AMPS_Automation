package com.base;

import java.util.Map;

import com.util.ExcelUtils;
import com.util.ReadPropertyFile;

public class Environment {
	private static ThreadLocal<String> environment = new ThreadLocal<>();
	
	public synchronized static void setEnvironment(String environmentType) {
		ReadPropertyFile prop = new ReadPropertyFile();
		if(environmentType.equalsIgnoreCase("alt")) {
			Map<String, String> map = ExcelUtils.getRowFromRowNumber(prop.getExcelFileName(), Excel.TestCases, environmentType);
			environment = ThreadLocal.withInitial(() -> map.get(Excel.URL));
		} else if(environmentType.equalsIgnoreCase("dot")) {
			environment = ThreadLocal.withInitial(() -> prop.getDotAMPSURL());
		} else if(environmentType.equalsIgnoreCase("row")) {
			environment = ThreadLocal.withInitial(() -> prop.getRowAMPSURL());
		}
	}
	
	public synchronized static String getEnvironment() {
		try {
			return environment.get();
		} catch (Exception e) {
			return environment.get();
		}
	}
}
