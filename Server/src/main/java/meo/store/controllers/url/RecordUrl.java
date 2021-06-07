package meo.store.controllers.url;

public interface RecordUrl {

	String RESOURCE_NAME = "/records";

	String RECORD_ID = "/{uuid}";

	String GET_BY_ID = RESOURCE_NAME + RECORD_ID;

	String CREATE = RESOURCE_NAME;

	String UPDATE = RESOURCE_NAME + RECORD_ID;

	String DELETE = RESOURCE_NAME + RECORD_ID;

}
