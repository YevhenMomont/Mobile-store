package meo.store.services.recommender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import meo.store.dto.ProductDto;
import meo.store.dto.UserDto;
import meo.store.services.ProductService;
import meo.store.services.UserService;

@Service
public class RecommendService {

	private ProductService productService;

	private UserService userService;

	private TfIdf tfIdf;

	private List<ProductDto> productDtoList;

	private List<UserDto> users;

	private UserDto currentUser;

	private List<String> allProductsUUID;

	private List<String> currentUserProducts;

	private Map<UUID, List<String>> allUsersProducts;

	private Map<String, Double> tfIdfAllProducts;

	private Map<String, Double> currentUserTfIdf;

	private Map<UUID, Map<String, Double>> allUsersTfIdf;

	private List<Double> currentUserTfIdfList;

	public List<ProductDto> getRecommendationByCosineSimilarity(UUID uuid) {
		initializeFields(uuid);

		Map<UUID, Double> recommendResults = new HashMap<>();
		for (Map.Entry<UUID, Map<String, Double>> allUsersCurrentUser : allUsersTfIdf.entrySet()) {

			List<Double> allUsersCurrentUserList = new ArrayList<>(allUsersCurrentUser.getValue().values());

			if (allUsersCurrentUserList.size() != 0) {
				recommendResults.put(allUsersCurrentUser.getKey(), CosineSimilarity.computeSimilarity(allUsersCurrentUserList, currentUserTfIdfList));
			}
		}

		Double maxValue = recommendResults.values().stream().max(Double::compareTo).orElseThrow();

		Set<UUID> products = users.stream()
				.filter(userDto -> userDto.getId().equals(recommendResults
						.entrySet()
						.stream()
						.filter(entry -> maxValue
								.equals(entry.getValue()))
						.map(Map.Entry::getKey)
						.findFirst().orElseThrow())).findFirst()
				.orElseThrow().getPurchasedProducts();

		products.removeIf(x -> currentUser.getPurchasedProducts().stream().anyMatch(y -> y.equals(x)));

		productDtoList.removeIf(x -> !products.contains(x.getUuid()));

		return productDtoList;
	}

	public List<ProductDto> getRecommendationByMSE(UUID uuid) {
		initializeFields(uuid);

		Map<UUID, Double> recommendResults = new HashMap<>();
		for (Map.Entry<UUID, Map<String, Double>> allUsersCurrentUser : allUsersTfIdf.entrySet()) {

			List<Double> allUsersCurrentUserList = new ArrayList<>(allUsersCurrentUser.getValue().values());

			if (allUsersCurrentUserList.size() != 0) {
				recommendResults.put(allUsersCurrentUser.getKey(), MSE.computeMSE(allUsersCurrentUserList, currentUserTfIdfList));
			}
		}

		Double maxValue = recommendResults.values().stream().max(Double::compareTo).orElseThrow();

		Set<UUID> products = users.stream()
				.filter(userDto -> userDto.getId().equals(recommendResults
						.entrySet()
						.stream()
						.filter(entry -> maxValue
								.equals(entry.getValue()))
						.map(Map.Entry::getKey)
						.findFirst().orElseThrow())).findFirst()
				.orElseThrow().getPurchasedProducts();

		products.removeIf(x -> currentUser.getPurchasedProducts().stream().anyMatch(y -> y.equals(x)));

		productDtoList.removeIf(x -> !products.contains(x.getUuid()));

		return productDtoList;
	}

	public List<ProductDto> getRecommendationByPearsonCorrelation(UUID uuid) {
		initializeFields(uuid);

		Map<UUID, Double> recommendResults = new HashMap<>();
		for (Map.Entry<UUID, Map<String, Double>> allUsersCurrentUser : allUsersTfIdf.entrySet()) {

			List<Double> allUsersCurrentUserList = new ArrayList<>(allUsersCurrentUser.getValue().values());

			if (allUsersCurrentUserList.size() != 0) {
				recommendResults.put(allUsersCurrentUser.getKey(), PearsonCorrelation.computeCorrelation(currentUserTfIdfList, allUsersCurrentUserList));
			}
		}

		Double maxValue = recommendResults.values().stream().max(Double::compareTo).orElseThrow();

		Set<UUID> products = users.stream()
				.filter(userDto -> userDto.getId().equals(recommendResults
						.entrySet()
						.stream()
						.filter(entry -> maxValue
								.equals(entry.getValue()))
						.map(Map.Entry::getKey)
						.findFirst().orElseThrow())).findFirst()
				.orElseThrow().getPurchasedProducts();

		products.removeIf(x -> currentUser.getPurchasedProducts().stream().anyMatch(y -> y.equals(x)));

		productDtoList.removeIf(x -> !products.contains(x.getUuid()));

		return productDtoList;
	}

	private void initializeFields(UUID uuid) {
		tfIdf = new TfIdf();

		productDtoList = productService.findAllProducts();
		users = userService.findAllUsers();
		currentUser = userService.findUserById(uuid);

		allProductsUUID = productDtoList
				.stream()
				.map(ProductDto::getUuid)
				.map(UUID::toString)
				.collect(Collectors.toList());

		currentUserProducts = currentUser
				.getPurchasedProducts()
				.stream()
				.map(UUID::toString)
				.collect(Collectors.toList());

		allUsersProducts = users.stream()
				.collect(Collectors.toMap(UserDto::getId, x -> x.getPurchasedProducts()
								.stream()
								.map(UUID::toString)
								.collect(Collectors.toList())
						)
				);

		tfIdfAllProducts = tfIdf.tfIdf(allProductsUUID, allUsersProducts);

		currentUserTfIdf = tfIdf.tfIdfUser(currentUserProducts, tfIdfAllProducts);

		users.removeIf(userDto -> userDto.getId().equals(currentUser.getId()));

		allUsersProducts = users.stream()
				.collect(Collectors.toMap(UserDto::getId, x -> x.getPurchasedProducts()
								.stream()
								.map(UUID::toString)
								.collect(Collectors.toList())
						)
				);

		allUsersTfIdf = new HashMap<>();

		for (Map.Entry<UUID, List<String>> allUsersCurrentUser : allUsersProducts.entrySet()) {
			allUsersTfIdf.put(allUsersCurrentUser.getKey(), this.tfIdf.tfIdfUser(allUsersCurrentUser.getValue(), tfIdfAllProducts));
		}


		currentUserTfIdfList = new ArrayList<>(currentUserTfIdf.values());
	}

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
