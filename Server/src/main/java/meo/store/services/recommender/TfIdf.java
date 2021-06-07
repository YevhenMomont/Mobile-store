package meo.store.services.recommender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TfIdf {

	private Map<String, Double> tf(Map<UUID, List<String>> allUsersProducts, List<String> allproducts) {
		Map<String, Double> results = new HashMap<>();
		UUID uuid = UUID.randomUUID();
		for (String productUUID : allproducts) {
			double number = 0;
			for (List<String> doc : allUsersProducts.values()) {
				for (String userProduct : doc) {
					if (productUUID.equalsIgnoreCase(userProduct)) {
						number++;
					}
				}
			}
			if (number != 0) {
				results.put(productUUID, number / allproducts.size());
			}
		}

		return results;
	}

	private Map<String, Double> idf(Map<UUID, List<String>> allUsersProducts, List<String> allproducts) {
		Map<String, Double> results = new HashMap<>();
		for (String productUUID : allproducts) {
			double number = 0;
			for (List<String> doc : allUsersProducts.values()) {
				for (String userProduct : doc) {
					if (productUUID.equalsIgnoreCase(userProduct)) {
						number++;
						break;
					}
				}
			}
			if (number != 0) {
				results.put(productUUID, Math.log(allUsersProducts.size() / number));
			}
		}

		return results;
	}

	public Map<String, Double> tfIdf(List<String> allproducts, Map<UUID, List<String>> allUsersProducts) {
		Map<String, Double> tf = tf(allUsersProducts, allproducts);
		Map<String, Double> idf = idf(allUsersProducts, allproducts);

		Map<String, Double> tfIdf = new HashMap<>();
		if (tf.size() == idf.size()) {
			for (String key : tf.keySet()) {
				tfIdf.put(key, tf.get(key) * idf.get(key));
			}
		}
		return tfIdf;
	}

	public Map<String, Double> tfIdfUser(List<String> userProducts, Map<String, Double> tfIdf) {

		Map<String, Double> userMap = new HashMap<>();

		for (String product : tfIdf.keySet()) {
			for (String currentUserProduct : userProducts)
				if (product.equalsIgnoreCase(currentUserProduct)) {
					userMap.put(product, tfIdf.get(product));
					break;
				} else {
					userMap.put(product, 0d);
				}
		}

		return userMap;
	}


}
