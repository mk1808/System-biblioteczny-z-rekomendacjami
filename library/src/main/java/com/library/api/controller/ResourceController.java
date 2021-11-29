package com.library.api.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="test")
public class ResourceController {
	@Autowired 
	ResourceBundleMessageSource messageSource;
	
    @GetMapping("/getMessage")
    public String getLocaleMessage(@RequestHeader(name="Accept-Language", required=false) Locale locale) {
        return messageSource.getMessage("welcome.to.country",null,locale);
    }

}
