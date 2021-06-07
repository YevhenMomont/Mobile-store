package meo.store.services.recommender;

import java.util.List;

public class CosineSimilarity {

	public static double computeSimilarity(List<Double> currentUser, List<Double> otherUser) {
		double dotProduct = 0.0;
		double normA = 0.0;
		double normB = 0.0;
		for (int i = 0; i < currentUser.size(); i++) {
			dotProduct += currentUser.get(i) * otherUser.get(i);
			normA += Math.pow(currentUser.get(i), 2);
			normB += Math.pow(otherUser.get(i), 2);
		}
		return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
	}
}
