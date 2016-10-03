package io.github.lucasvenez.ann.mlp.neuron;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

import io.github.lucasvenez.exception.NeuralNetworkFowardException;

/**
 * 
 * @author <a href="http://lucasvenez.github.io">Lucas Venezian Povoa</a>
 *
 */
public class ParallelProcessingNeuron implements Callable<Double> {

	private final List<Double> inputs = new ArrayList<Double>();

	private final ProcessingNeuron processingNeuron;

	public ParallelProcessingNeuron(final ProcessingNeuron neuron, final Collection<Double> inputs) {
		
		this.processingNeuron = neuron;
		
		this.inputs.addAll(inputs);
	}

	/**
	 * 
	 */
	@Override
	public Double call() {
		
		Double result = 0.0;
		
		try {
			result = this.processingNeuron.process(inputs);
		} catch (NeuralNetworkFowardException e) {
			e.printStackTrace();
		}

		return result;
	}
}
