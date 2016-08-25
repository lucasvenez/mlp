package io.github.lucasvenez.mlp.layer;

import java.util.ArrayList;
import java.util.List;

import io.github.lucasvenez.mlp.exception.NeuralNetworkFowardException;
import io.github.lucasvenez.mlp.function.ActivationFunction;

/**
 * 
 * @author <a href="http://lucasvenez.github.io">Lucas Venezian Povoa</a>
 *
 */
public class InputLayer extends Layer {

	/**
	 * 
	 */
	public InputLayer() {
		super(false);
	}
	
	/**
	 * 
	 * @param numberOfNeurons
	 * @param activationFunction
	 */
	public InputLayer(int numberOfNeurons, ActivationFunction activationFunction) {
		super(numberOfNeurons, activationFunction);
	}

	@Override
	public List<Double> process(List<Double> inputs) throws NeuralNetworkFowardException {
		
		if (inputs.size() != neurons.size())
			throw new NeuralNetworkFowardException("The number of inputs should be equals to the number of input neurons.");
		
		List<Double> outputs = new ArrayList<Double>();
		
		for (int i = 0; i < inputs.size(); i++)
			outputs.add(neurons.get(i).process(inputs.get(i)));		
		
		return outputs;
	}
}
