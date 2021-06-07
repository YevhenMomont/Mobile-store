package meo.store.services.recommender;

import java.util.List;

public class MSE {

	public static double computeMSE(List<Double> currentUser, List<Double> otherUser) {
		double sum = 0;

		for (int i = 0; i < currentUser.size(); ++i) {
			double diff = currentUser.get(i) - otherUser.get(i);
			sum += diff * diff;
		}

		return sum / currentUser.size();
	}
}
