package io.github.lucasvenez.stat;

import static java.lang.Math.pow;

import io.github.lucasvenez.exception.InvalidVectorSizeException;

/**
 * 
 * @author <a href="http://lucasvenez.github.io/">Lucas Venezian Povoa</a>
 */
public class Stat {

	/**
	 * Calculates the mean of a sample
	 * @param sample is an array
	 * @return
	 */
	public double mean(double ... sample) {
		
		double result = 0;
		
		for (double d : sample)
			result += d;
		
		return result / sample.length;
	}
	
	/**
	 * Calculates the standard-deviation of a sample
	 * @param sample
	 * @return
	 */
	public double sd(double ... sample) {
		
		double result = 0;
		
		double mean = this.mean(sample);
		
		for (double d : sample)
			result += Math.pow(d - mean, 2);
		
		return Math.sqrt(result / sample.length);
	}
	
	/**
	 * Calculates the variance of a sample
	 * @param sample
	 * @return
	 */
	public double var(double ... sample) {
		
		double result = 0;
		
		double mean = this.mean(sample);
		
		for (double d : sample)
			result += Math.pow(d - mean, 2);
		
		return result / sample.length;
	}
	
	/**
	 * Calculates the sum of a sample of doubles numbers
	 * @param sample
	 * @return
	 */
	public double sum(double ... sample) {
		
		double result = 0.0;
		
		for (double d : sample)
			result += d;
		
		return result;
	}

	/**
	 * Calculates the sum of a sample of Doubles
	 * @param sample
	 * @return
	 */
	public Double sum(Double ... sample) {
		
		Double result = 0.0;
		
		for (Double d : sample)
			result += d;
		
		return result;
	}
	
	/**
	 * Calculates the sum of a sample of Integers
	 * @param sample an array of Integers
	 * @return sum of the sample array
	 */
	public Integer sum(Integer ... sample) {
		
		Integer result = 0;
		
		for (Integer i : sample)
			result += i;
		
		return result;
	}

	/**
	 * Transforms a number to positive
	 * @param value is a number
	 * @return a positive number
	 */
	public Double toPositive(Double value) {
		return value < 0.0 ? -value : value;
	}

	/**
	 * Transforms a number to negative
	 * @param value is a number
	 * @return a negative number
	 */
	public Double toNegative(Double value) {
		return value > 0.0 ? -value : value;
	}

	/**
	 * 
	 * @param numerator
	 * @param denominator
	 * @return
	 */
	public Integer mod(Integer numerator, Integer denominator) {
		return (numerator % denominator + denominator) % denominator;
	}
	
	/**
	 * 
	 * @param numerator
	 * @param denominator
	 * @return
	 */
	public Long mod(Long numerator, Long denominator) {
		return (numerator % denominator + denominator) % denominator;
	}
	
	/**
	 * 
	 * @param numerator
	 * @param denominator
	 * @return
	 */
	public Float mod(Float numerator, Float denominator) {
		return (numerator % denominator + denominator) % denominator;
	}
	
	/**
	 * 
	 * @param numerator
	 * @param denominator
	 * @return
	 */
	public Double mod(Double numerator, Double denominator) {
		return (numerator % denominator + denominator) % denominator;
	}
	
	public int sing(int[] permutation) throws InvalidVectorSizeException {
		
		Double[] arr = new Double[permutation.length];
		
		for (int i = 0; i < permutation.length; i++)
			arr[i] = new Double(permutation[i]);
		
		return this.sing(arr).intValue();
	}
	
	/**
	 * 
	 * @param permutation
	 * @return
	 * @throws InvalidVectorSizeException 
	 * @see https://en.wikipedia.org/wiki/Inversion_(discrete_mathematics)
	 */
	public Double sing(Double[] permutation) throws InvalidVectorSizeException {
		
		if (permutation.length < 2)
			throw new InvalidVectorSizeException(permutation, 2);
		
		int n = 0;
		
		for (int i = 0; i < permutation.length - 1; i++)
			for (int j = i + 1; j < permutation.length; j++)
				if (permutation[i] > permutation[j])
					n++;
		
		return pow(-1.0, n);
	}

	public Double prod(Double[] array) {
		
		Double result = 1.0;
		
		for (Double d : array)
			result *= d;
		
		return result;
	}
}
