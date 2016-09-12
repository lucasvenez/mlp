package io.github.lucasvenez.ann.mlp.neuron;

import java.util.List;

import io.github.lucasvenez.exception.NeuralNetworkFowardException;

/**
 * 
 * @author <a href="http://lucasvenez.github.io">Lucas Venezian Povoa</a>
 *
 */
public class ParallelProcessingNeuron implements Runnable {

	private Double output;
	
	private final List<Double> inputs;

	private final ProcessingNeuron processingNeuron;

	public ParallelProcessingNeuron(final ProcessingNeuron neuron, final List<Double> inputs) {
		this.processingNeuron = neuron;
		this.inputs = inputs;
	}

	/**
	 * 
	 */
	@Override
	public void run() {
		
		try {
			this.output = this.processingNeuron.process(inputs);
		} catch (NeuralNetworkFowardException e) {
			e.printStackTrace();
		}
		
		if (output == null)
			System.out.println("ERROR! " + output);
	}

	public void setInputs(List<Double> inputs) {
		this.inputs.clear();
		this.inputs.addAll(inputs);
	}

	public Double getOutput() {
		return this.output;
	}
}
