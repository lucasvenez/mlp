package io.github.lucasvenez.stat.random;

public class UniformGenerator extends SampleGenerator {

	public double[] generate(int n, double min, double max) {
		
		double[] result = new double[n];
		
		for (int i = 0; i < n; i++)
			result[i] = min + (max - min) * rnd.nextDouble();
		
		return result;
	}
	
	
}
