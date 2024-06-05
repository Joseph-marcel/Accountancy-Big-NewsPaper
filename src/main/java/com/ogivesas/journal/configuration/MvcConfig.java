package com.ogivesas.journal.configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MvcConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		exposeDirectory("invoice-photos",registry);
	}

	private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		Path upLoadDir = Paths.get(dirName);
		String upLoadPath = upLoadDir.toFile().getAbsolutePath();
		
		registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/" + upLoadPath + "/");
	}
}
