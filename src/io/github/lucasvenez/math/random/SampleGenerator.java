package io.github.lucasvenez.math.random;

import java.util.Random;

public class DistributionGenerator {

	private final Random rnd = new Random();

	public double[] gaussian(int n, double mean, double sd) {

		double[] result = new double[n];

		for (int i = 0; i < n; i++)
			result[i] = rnd.nextGaussian() * sd + mean;

		return result;
	}
	
	public double[] uniform(int n, double min, double max) {
		
		double[] result = new double[n];
		
		for (int i = 0; i < n; i++)
			result[i] = min + (max - min) * rnd.nextDouble();
		
		return result;
	}
}
