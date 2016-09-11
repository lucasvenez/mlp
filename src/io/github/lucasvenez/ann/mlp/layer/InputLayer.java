package io.github.lucasvenez.ann.mlp.layer;

import java.util.ArrayList;
import java.util.List;

import io.github.lucasvenez.ann.function.ActivationFunction;
import io.github.lucasvenez.ann.mlp.neuron.InputNeuron;

/**
 * 
 * @author <a href="http://lucasvenez.github.io">Lucas Venezian Povoa</a>
 *
 */
public class InputLayer implements Layer {

	private final List<InputNeuron> neurons = new ArrayList<InputNeuron>(); 
	
	/**
	 * 
	 */
	public InputLayer() {}
	
	/**
	 * 
	 * @param numberOfNeurons
	 * @param activationFunction
	 */
	public InputLayer(int numberOfNeurons, ActivationFunction activationFunction) {
		this();

		for (int i = 0; i < numberOfNeurons; i++)
			neurons.add(new InputNeuron(activationFunction, this));
	}
	
	@Override
	public List<InputNeuron> getNeurons() {
		return this.neurons;
	}

	public List<Double> process(List<Double> inputs) {
		
		final List<Double> result = new ArrayList<Double>();
		
		for (int i = 0; i < neurons.size(); i++)
			result.add(neurons.get(i).process(inputs.get(i)));
		
		return result;
	}

	@Override
	public int getNumberOfNeurons() {
		return this.neurons.size();
	}
}
