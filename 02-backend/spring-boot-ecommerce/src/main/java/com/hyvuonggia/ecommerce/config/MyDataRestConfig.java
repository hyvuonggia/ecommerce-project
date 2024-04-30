package com.hyvuonggia.ecommerce.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.hyvuonggia.ecommerce.entity.Country;
import com.hyvuonggia.ecommerce.entity.Product;
import com.hyvuonggia.ecommerce.entity.ProductCategory;
import com.hyvuonggia.ecommerce.entity.State;

import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

	private EntityManager entityManager;

	@Autowired
	public MyDataRestConfig(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		HttpMethod[] unSupportedActions = { HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE };

		// disable HTTP methods for Product: PUT, POST, DELETE
		disableHttpMethods(config, unSupportedActions, Product.class);

		// disable HTTP methods for ProductCategory: PUT, POST, DELETE
		disableHttpMethods(config, unSupportedActions, ProductCategory.class);

		// disable HTTP methods for ProductCategory: PUT, POST, DELETE
		disableHttpMethods(config, unSupportedActions, Country.class);

		// disable HTTP methods for ProductCategory: PUT, POST, DELETE
		disableHttpMethods(config, unSupportedActions, State.class);

		// call an internal helper method
		exposeIds(config);
	}

	private void disableHttpMethods(RepositoryRestConfiguration config, HttpMethod[] unSupportedActions,
			Class<?> clazz) {
		config.getExposureConfiguration().forDomainType(clazz)
				.withItemExposure((metdata, httpMethods) -> httpMethods.disable(unSupportedActions))
				.withCollectionExposure((metdata, httpMethod) -> httpMethod.disable(unSupportedActions));
	}

	private void exposeIds(RepositoryRestConfiguration config) {
		Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

		List<Class> entityClasses = new ArrayList<>();

		for (EntityType entityType : entities) {
			entityClasses.add(entityType.getJavaType());

		}

		Class[] domainTypes = entityClasses.toArray(new Class[0]);
		config.exposeIdsFor(domainTypes);

	}
}
