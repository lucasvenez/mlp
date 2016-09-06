package io.github.lucasvenez.mlp.neuron;

import java.util.ArrayList;
import java.util.List;

import io.github.lucasvenez.mlp.exception.NeuralNetworkFowardException;
import io.github.lucasvenez.mlp.function.ActivationFunction;
import io.github.lucasvenez.mlp.layer.Layer;
import io.github.lucasvenez.mlp.layer.ProcessingLayer;

import static java.util.Arrays.asList;

/**
 * 
 * @author <a href="http://lucasvenez.github.io">Lucas Venezian Povoa</a>
 *
 */
public class ProcessingNeuron extends Neuron<ProcessingLayer> {

	private final List<Double> weights = new ArrayList<Double>();

	private Double lastInducedLocalField = null;
	
	private final List<Double> lastDeltaWeights = new ArrayList<Double>();

	private final List<Double> lastInputs = new ArrayList<Double>();
	
	public ProcessingNeuron(ActivationFunction activationFunction, ProcessingLayer processingLayer) {
		super(activationFunction, processingLayer);
	}

	/**
	 * 
	 * @param inputs
	 * @return
	 * @throws NeuralNetworkFowardException
	 */
	public Double process(List<Double> inputs) throws NeuralNetworkFowardException {

		if (inputs.size() + (parentLayer.hasBiases() ? 1 : 0) != this.weights.size())
			throw new NeuralNetworkFowardException(
				"The number of inputs (" + (inputs.size() + (parentLayer.hasBiases() ? 1 : 0)) + ") should be equals to the number of weights ("
					+ (this.weights.size()) + ") at a neuron.");

		Double result = 0.0;
		
		lastInputs.clear();
		
		/*
		 * If has bias we should consider
		 * first weight and the value +1.0 as 
		 * first input 
		 */
		if (this.parentLayer.hasBiases()) {
			this.lastInputs.add(1.0);
			result = this.weights.get(0);
		}
		
		this.lastInputs.addAll(inputs);
		
		for (int j = 0, i = this.parentLayer.hasBiases() ? 1 : 0; i < weights.size(); i++)
			result += this.weights.get(i) * inputs.get(j++);

		this.lastInducedLocalField = result;
		
		return this.activationFunction.apply(result);
	}

	/**
	 * 
	 */
	public void initializeWeightsRandomly() {

		int numberOfConnections = 0; 
		
		if (this.parentLayer.getPreviousLayer() != null) {
			numberOfConnections = this.parentLayer.getPreviousLayer().getNeurons().size();
			
			if (this.parentLayer.hasBiases())
				numberOfConnections++;
		}

		for (int i = 0; i < numberOfConnections; i++)
			weights.add(Math.random() * (Math.random() < .5 ? -1 : 1));
	}

	/**
	 * 
	 * @param weights
	 */
	public void setWeights(List<Double> weights) {

		
		int n = this.parentLayer.getPreviousLayer().getNumberOfNeurons() + 
				(this.parentLayer.hasBiases() ? 1 : 0);
		
		if (weights.size() !=  n)
			try {
				throw new Exception("Invalid number of weights. It is expected " + n + " but it was " + weights.size());
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		this.weights.clear();
		this.lastDeltaWeights.clear();
		
		for (Double w : weights) {
			this.weights.add(w);
			this.lastDeltaWeights.add(w);
		}
	}
	
	public void setWeights(Double ... weights) {
		this.setWeights(asList(weights));
	}

	/**
	 * 
	 * @return
	 */
	public Layer getLayer() {
		return this.parentLayer;
	}

	/**
	 * 
	 * @param weights
	 */
	public void updateWeights(Double momentum, Double learningRate, Double localGradient) {
		
		for (int i = 0; i < this.weights.size(); i++) {
			
			final Double w = this.weights.get(i) + //momentum * this.lastDeltaWeights.get(i) +
							learningRate * localGradient * this.lastInputs.get(i);
			
			this.lastDeltaWeights.set(i, w - this.weights.get(i));
			
			this.weights.set(i, w);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Double> getWeights() {
		return this.weights;
	}

	/**
	 * 
	 * @return
	 */
	public List<Double> getLastDeltaWeights() {
		return this.lastDeltaWeights;
	}
	
	/**
	 * @return
	 */
	public Double getLastInducedLocalField() {
		return this.lastInducedLocalField;
	}

	public Double derivativeOfLastInducedLocalField() {
		return this.activationFunction.derivative(this.lastInducedLocalField);
	}

	public Double getWeight(int index) {
		return this.weights.get(index);
	}
}
