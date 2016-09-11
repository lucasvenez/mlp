package io.github.lucasvenez.ann.mlp.layer;

import java.util.ArrayList;
import java.util.List;

import io.github.lucasvenez.ann.function.ActivationFunction;
import io.github.lucasvenez.ann.mlp.neuron.ParallelProcessingNeuron;

public class ParallelProcessingLayer extends ProcessingLayer {

	public ParallelProcessingLayer(int numberOfNeurons, ActivationFunction activationFunction) {
		super(numberOfNeurons, activationFunction);
	}

	/**
	 * It is slower than sequencial execution
	 */
	@Override
	public List<Double> process(List<Double> inputs) {

		List<Double> outputs = new ArrayList<Double>();

		final int CORES = 6;// Runtime.getRuntime().availableProcessors();

		final List<ParallelProcessingNeuron> parallelNeurons = new ArrayList<ParallelProcessingNeuron>();
		final List<Thread> threads = new ArrayList<Thread>();
		
		for (int i = 0; i < super.neurons.size(); i += CORES) {

			for (int j = 0; j < CORES && i + j < super.neurons.size(); j++) {

				final ParallelProcessingNeuron parallelNeuron = 
					new ParallelProcessingNeuron(super.neurons.get(i + j), inputs);

				threads.add(new Thread(parallelNeuron));
				parallelNeurons.add(parallelNeuron);
				
				threads.get(threads.size() - 1).start();
			}
			
			for (int n = 0; n < threads.size(); n++) {
				
				try {
					threads.get(n).join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				outputs.add(parallelNeurons.get(n).getOutput());
			}
			
			parallelNeurons.clear();
			threads.clear();
		}

		return outputs;
	}
}
