package meo.store.controllers.url;

public interface CategoryUrl {

	String RESOURCE_NAME = "/categories";

	String CATEGORY_ID = "/{uuid}";

	String GET_BY_ID = RESOURCE_NAME + CATEGORY_ID;

	String CREATE = RESOURCE_NAME;

	String UPDATE = RESOURCE_NAME + CATEGORY_ID;

	String DELETE = RESOURCE_NAME + CATEGORY_ID;
}
