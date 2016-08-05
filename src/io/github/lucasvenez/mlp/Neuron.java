package io.github.lucasvenez.mlp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Neuron {

	private List<Double> weights = new ArrayList<Double>();
	
	private Double bias;
	
	public Double k(List<Double> outputs) throws Exception {
		
		if (outputs.size() != weights.size())
			throw new Exception("Invalid input parameters [outputs size]");
		
		Double result = 0.0;
		
		for (int i = 0; i < outputs.size(); i++)
			result += outputs.get(i) * this.weights.get(i);
			
		return result + bias;
	}
	
	public Double k(Double[] inputs) throws Exception {
		return k(Arrays.asList(inputs));
	}
	
	public void setWeight(int index, Double weights) {
		this.weights.add(index, weights);
	}
	
	public Double getWeight(int index) {
		return this.weights.get(index);
	}

	public Double activation(Double[] outputs) throws Exception {

		Double r = k(outputs);
		
		return 1.0 / (1.0 + Math.pow(Math.E, -r));
	}

	public Double getBias() {
		return bias;
	}

	public void setBias(Double bias) {
		this.bias = bias;
	}
}
