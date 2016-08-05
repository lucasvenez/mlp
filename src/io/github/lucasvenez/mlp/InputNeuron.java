package io.github.lucasvenez.mlp;

import java.util.ArrayList;
import java.util.List;

public class InputNeuron {

	private List<Double> biases = new ArrayList<Double>();
	
	public Double activation(Double input, Double[] activations) throws Exception {

		Double result = 0.0;
		
		for (int i = 0; i < activations.length; i++)
			result += activations[i] * biases.get(i);
		
		return input + result;		
	}
	
	public void setBias(int index, Double bias) {
		this.biases.add(index, bias);
	}
	
	public Double getBias(int index) {
		return this.biases.get(index);
	}
}
