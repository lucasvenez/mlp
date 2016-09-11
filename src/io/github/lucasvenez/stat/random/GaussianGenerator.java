package io.github.lucasvenez.stat.random;

/**
 * Gaussian sample generator
 * 
 * @author <a href="http://lucasvenez.github.io/">Lucas Venezian Povoa</a>
 */
public class GaussianGenerator extends SampleGenerator {
	
	public double[] generate(int n, double mean, double sd) {

		double[] result = new double[n];

		for (int i = 0; i < n; i++)
			result[i] = super.rnd.nextGaussian() * sd + mean;

		return result;
	}
}
