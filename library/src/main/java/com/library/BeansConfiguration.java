package com.library;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@Configuration
public class BeansConfiguration {
	AcceptHeaderLocaleResolver resolver;
	ResourceBundleMessageSource messageSource;
	
	@Bean
	public AcceptHeaderLocaleResolver localeResolver() {
		resolver = new AcceptHeaderLocaleResolver();
		resolver.setDefaultLocale(Locale.UK);
		return resolver;
	}
	
	@Bean
	public ResourceBundleMessageSource messageSource() {
		messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
		
	}

}
