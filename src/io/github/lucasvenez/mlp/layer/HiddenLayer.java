package io.github.lucasvenez.mlp.layer;

import java.util.ArrayList;
import java.util.List;

import io.github.lucasvenez.mlp.Neuron;
import io.github.lucasvenez.mlp.exception.NeuralNetworkFowardException;
import io.github.lucasvenez.mlp.function.ActivationFunction;

public class HiddenLayer extends Layer {

	public HiddenLayer() {
		super(true);
	}
	
	public HiddenLayer(int numberOfNeurons, ActivationFunction activationFunction) {
		super(numberOfNeurons, activationFunction);
	}

	public List<Double> process(List<Double> inputs) throws NeuralNetworkFowardException {
		
		List<Double> outputs = new ArrayList<Double>();
		
		for (Neuron n : super.neurons)
			outputs.add(n.process(inputs));
		
		return outputs;
	}
}
