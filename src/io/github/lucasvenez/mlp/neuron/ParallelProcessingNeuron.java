package io.github.lucasvenez.mlp.neuron;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.github.lucasvenez.mlp.function.ActivationFunction;
import io.github.lucasvenez.mlp.layer.ProcessingLayer;

public class ParallelProcessingNeuron extends ProcessingNeuron implements Callable<Double> {

	private final List<Double> inputs = new ArrayList<Double>();
	
	public ParallelProcessingNeuron(ActivationFunction activationFunction, ProcessingLayer processingLayer) {
		super(activationFunction, processingLayer);
	}

	@Override
	public Double call() throws Exception {
		return super.process(this.inputs);
	}

	public List<Double> getInputs() {
		return inputs;
	}
	
	public void setInputs(List<Double> inputs) {
		this.inputs.clear();
		this.inputs.addAll(inputs);
	}
}
