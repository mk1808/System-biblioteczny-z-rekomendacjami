package com.library.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class PropertiesConfig {
	@Autowired 
	private Environment env;
	
	public String getConfigValue(String configKey) {
		return env.getProperty(configKey);
	}
	
	
}
