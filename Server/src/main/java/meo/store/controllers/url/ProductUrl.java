package meo.store.controllers.url;

public interface ProductUrl {

	String RESOURCE_NAME = "/products";

	String RESOURCE_NAME_CATEGORY = "/categories";

	String RECOMMENDATION = "/recommendation";

	String COSINE_SIMILARITY = "/cosineSimilarity";

	String MSE = "/mse";

	String PEARSON_CORRELATION = "/pearsonCorrelation";

	String UUID = "/{uuid}";

	String GET = RESOURCE_NAME + UUID;

	String GET_BY_COSINE_SIMILARITY = RESOURCE_NAME + RECOMMENDATION + COSINE_SIMILARITY + UUID;

	String GET_BY_MSE = RESOURCE_NAME + RECOMMENDATION + MSE + UUID;

	String GET_BY_PEARSON_CORRELATION = RESOURCE_NAME + RECOMMENDATION + PEARSON_CORRELATION + UUID;

	String GET_BY_CATEGORY = RESOURCE_NAME_CATEGORY + UUID + RESOURCE_NAME;

	String CREATE = RESOURCE_NAME;

	String UPDATE = RESOURCE_NAME + UUID;

	String DELETE = RESOURCE_NAME + UUID;
	
}
