package io.github.lucasvenez.ann.mlp.layer;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import io.github.lucasvenez.ann.function.ActivationFunction;
import io.github.lucasvenez.ann.mlp.neuron.ParallelProcessingNeuron;

public class ParallelProcessingLayer extends ProcessingLayer {

	public ParallelProcessingLayer(int numberOfNeurons, ActivationFunction activationFunction) {
		super(numberOfNeurons, activationFunction);
	}
	
	public ParallelProcessingLayer(int numberOfNeurons, ActivationFunction activationFunction, Layer previousLayer) {
		super(numberOfNeurons, activationFunction, previousLayer);
	}

	/**
	 * It is slower than sequencial execution
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Double> process(List<Double> inputs) {

		final ExecutorService executor = Executors.newWorkStealingPool();
		
		List<Double> result = new LinkedList<Double>();

		{

			Future<Double>[] futures = new Future[super.neurons.size()];
			
			for (int i = 0; i < super.neurons.size(); i++)
				futures[i] = 
					executor.submit(
						new ParallelProcessingNeuron(
							super.neurons.get(i), inputs));
			
			for (Future<Double> fut : futures)
				try {
					result.add(fut.get());
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
		}

		executor.shutdown();
		
		return result;
	}
}
