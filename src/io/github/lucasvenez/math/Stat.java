package io.github.lucasvenez.math;

public class Statistics {

	public double mean(double ... sample) {
		
		double result = 0;
		
		for (double d : sample)
			result += d;
		
		return result / sample.length;
	}
	
	public double sd(double ... sample) {
		
		double result = 0;
		
		double mean = this.mean(sample);
		
		for (double d : sample)
			result += Math.pow(d - mean, 2);
		
		return Math.sqrt(result / sample.length);
	}
	
	public double var(double ... sample) {
		
		double result = 0;
		
		double mean = this.mean(sample);
		
		for (double d : sample)
			result += Math.pow(d - mean, 2);
		
		return result / sample.length;
	}
	
	public double sum(double ... sample) {
		
		double result = 0.0;
		
		for (double d : sample)
			result += d;
		
		return result;
	}

	public Double sum(Double ... sample) {
		
		Double result = 0.0;
		
		for (Double d : sample)
			result += d;
		
		return result;
	}
}
