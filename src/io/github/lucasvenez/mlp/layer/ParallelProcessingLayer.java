package io.github.lucasvenez.mlp.layer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import io.github.lucasvenez.mlp.function.ActivationFunction;
import io.github.lucasvenez.mlp.neuron.ProcessingNeuron;

public class ParallelProcessingLayer extends ProcessingLayer {

	public ParallelProcessingLayer(int numberOfNeurons, ActivationFunction activationFunction) {
		super(numberOfNeurons, activationFunction);
	}

	@Override
	public List<Double> process(List<Double> inputs) {

		List<Double> outputs = new ArrayList<Double>();

		final int CORES = 3;//Runtime.getRuntime().availableProcessors();

		ExecutorService executor = Executors.newFixedThreadPool(CORES);

		List<Future<Double>> list = new ArrayList<Future<Double>>();
		
		for (int i = 0; i < super.neurons.size(); i += CORES) {
			
			for (int j = 0; j < CORES && i + j < super.neurons.size(); j++) {

				final ProcessingNeuron n = neurons.get(i + j);
				
				Future<Double> submit = executor.submit(new Callable<Double>() {

					@Override
					public Double call() throws Exception {
						return n.process(inputs);
					}
				});

				list.add(submit);
			}
		}

		for (Future<Double> output : list) {
			try {
				outputs.add(output.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		executor.shutdown();
		
		return outputs;
	}
}
