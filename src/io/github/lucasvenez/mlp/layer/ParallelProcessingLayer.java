package io.github.lucasvenez.mlp.layer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import io.github.lucasvenez.mlp.neuron.ParallelProcessingNeuron;

public class ParallelProcessingLayer implements Layer {

	private final List<ParallelProcessingNeuron> neurons = 
						new ArrayList<ParallelProcessingNeuron>();

	
	public List<Double> processInParallel(final List<Double> inputs) throws InterruptedException, ExecutionException {
		
		List<Double> outputs = new ArrayList<Double>();
		
		int cores = Runtime.getRuntime().availableProcessors();
		
		ExecutorService executor = Executors.newFixedThreadPool(cores);
		
		List<Future<Double>> list = new ArrayList<Future<Double>>();
		
		for (ParallelProcessingNeuron n : this.neurons) {
			
			n.setInputs(inputs);
			
            Future<Double> submit = executor.submit(n);
            
            list.add(submit);
		}
		
		for (Future<Double> output : list)
			outputs.add(output.get());
		
		return outputs;
	}
	
	@Override
	public List<ParallelProcessingNeuron> getNeurons() {
		return this.neurons;
	}

	@Override
	public int getNumberOfNeurons() {
		return this.neurons.size();
	}
}
