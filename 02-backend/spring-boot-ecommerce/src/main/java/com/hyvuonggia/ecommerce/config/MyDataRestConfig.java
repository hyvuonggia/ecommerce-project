package com.hyvuonggia.ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.hyvuonggia.ecommerce.entity.Product;
import com.hyvuonggia.ecommerce.entity.ProductCategory;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		HttpMethod[] unSupportedActions = { HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE };

		// disable HTTP methods for Product: PUT, POST, DELETE
		config.getExposureConfiguration().forDomainType(Product.class)
				.withItemExposure((metdata, httpMethods) -> httpMethods.disable(unSupportedActions))
				.withCollectionExposure((metdata, httpMethod) -> httpMethod.disable(unSupportedActions));

		// disable HTTP methods for ProductCategory: PUT, POST, DELETE
		config.getExposureConfiguration().forDomainType(ProductCategory.class)
				.withItemExposure((metdata, httpMethods) -> httpMethods.disable(unSupportedActions))
				.withCollectionExposure((metdata, httpMethod) -> httpMethod.disable(unSupportedActions));
	}
}
