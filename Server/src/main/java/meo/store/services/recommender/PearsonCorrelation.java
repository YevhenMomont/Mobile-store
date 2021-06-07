package meo.store.services.recommender;

import java.util.List;

public class PearsonCorrelation {

	public static double computeCorrelation(List<Double> currentUser, List<Double> otherUser) {
		double sumX = 0.0, sumY = 0.0, sumXX = 0.0, sumYY = 0.0, sumXY = 0.0;

		for (int i = 0; i < currentUser.size(); i++) {
			double x = currentUser.get(i);
			double y = otherUser.get(i);

			sumX += x;
			sumY += y;
			sumXX += x * x;
			sumYY += y * y;
			sumXY += x * y;
		}

		double cov = sumXY / currentUser.size() - sumX * sumY / currentUser.size() / currentUser.size();

		return cov
				/ Math.sqrt(sumXX / currentUser.size() - sumX * sumX / currentUser.size() / currentUser.size())
				/ Math.sqrt(sumYY / currentUser.size() - sumY * sumY / currentUser.size() / currentUser.size());
	}
}
