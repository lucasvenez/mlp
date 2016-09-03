package io.github.lucasvenez.mlp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.github.lucasvenez.mlp.exception.NeuralNetworkBuildingException;
import io.github.lucasvenez.mlp.exception.NeuralNetworkFowardException;
import io.github.lucasvenez.mlp.function.ActivationFunction;
import io.github.lucasvenez.mlp.function.IdentityFunction;
import io.github.lucasvenez.mlp.layer.ProcessingLayer;
import io.github.lucasvenez.mlp.layer.InputLayer;
import io.github.lucasvenez.mlp.layer.Layer;

/**
 * 
 * @author <a href="http://lucasvenez.github.io">Lucas Venezian Povoa</a>
 *
 */
public class MultilayerPerceptron {

	private InputLayer inputLayer;

	private final List<ProcessingLayer> hiddenLayers 
							= new ArrayList<ProcessingLayer>();

	private ProcessingLayer outputLayer;

	/**
	 * 
	 * @param numberOfNeurons
	 * @param activationFunction
	 * @throws NeuralNetworkBuildingException
	 */
	public void setInputLayer(int numberOfNeurons, ActivationFunction activationFunction)
			throws NeuralNetworkBuildingException {

		if (numberOfNeurons < 1)
			throw new NeuralNetworkBuildingException("The input layer should have at least one neuron.");

		if (activationFunction == null)
			throw new NeuralNetworkBuildingException("It is not possible set null to activation functions");

		this.inputLayer = new InputLayer(numberOfNeurons, activationFunction);
		
		if (hiddenLayers.size() > 0)
			hiddenLayers.get(0).setPreviousLayer(this.inputLayer);
		
		else if (this.outputLayer != null)
			this.outputLayer.setPreviousLayer(this.inputLayer);
	}

	/**
	 * 
	 * @param numberOfNeurons
	 * @param activationFunction
	 */
	public void addHiddenLayer(int numberOfNeurons, ActivationFunction activationFunction) throws NeuralNetworkBuildingException {
		this.addHiddenLayer(new ProcessingLayer(numberOfNeurons, activationFunction));
	}
	
	/**
	 * 
	 * @param hiddenLayer
	 */
	public void addHiddenLayer(ProcessingLayer hiddenLayer) throws NeuralNetworkBuildingException {

		Layer previousLayer;

		if (this.hiddenLayers.size() > 0)
			previousLayer = this.hiddenLayers.get(this.hiddenLayers.size() - 1);
		else
			previousLayer = this.inputLayer;

		hiddenLayer.setPreviousLayer(previousLayer);

		this.hiddenLayers.add(hiddenLayer);
		
		if (this.outputLayer != null)
			this.outputLayer.setPreviousLayer(hiddenLayer);
	}

	/**
	 * 
	 * @param numberOfNeurons
	 * @param activationFunction
	 * @throws NeuralNetworkBuildingException
	 */
	public void setOutputLayer(int numberOfNeurons, ActivationFunction activationFunction)
			throws NeuralNetworkBuildingException {
		
		this.outputLayer = new ProcessingLayer(numberOfNeurons, activationFunction);

		Layer previousLayer;

		if (this.hiddenLayers.size() > 0)
			previousLayer = this.hiddenLayers.get(this.hiddenLayers.size() - 1);
		else
			previousLayer = this.inputLayer;

		this.outputLayer.setPreviousLayer(previousLayer);
	}

	/**
	 * 
	 * @param numberOfNeurons
	 */
	public void setOutputLayer(int numberOfNeurons) throws NeuralNetworkBuildingException {
		this.setOutputLayer(numberOfNeurons, new IdentityFunction());
	}

	/**
	 * 
	 * @param inputs
	 * @return
	 * @throws NeuralNetworkFowardException
	 */
	public Double[] process(Double... inputs) throws NeuralNetworkFowardException {
		return process(Arrays.asList(inputs)).toArray(new Double[outputLayer.getNeurons().size()]);
	}

	/**
	 * 
	 * @param inputs
	 * @return
	 * @throws NeuralNetworkFowardException
	 */
	public List<Double> process(List<Double> inputs) throws NeuralNetworkFowardException {

		List<Double> outputs = this.inputLayer.process(inputs);

		for (int i = 0; i < hiddenLayers.size(); i++)
			outputs = hiddenLayers.get(i).process(outputs);

		outputs = this.outputLayer.process(outputs);
				
		return outputs;
	}

	/**
	 * 
	 * @param inputs
	 * @return
	 * @throws NeuralNetworkFowardException
	 */
	public Double[] process(Integer... inputs) throws NeuralNetworkFowardException {

		Double dInputs[] = new Double[inputs.length];

		for (int i = 0; i < dInputs.length; i++)
			dInputs[i] = new Double(inputs[i]);

		return this.process(dInputs);
	}

	/**
	 * 
	 */
	public void initializeWeightsRandomly() {
		
		for (ProcessingLayer h : this.hiddenLayers)
			h.initializeWeightsRandomly();
		
		outputLayer.initializeWeightsRandomly();
	}
}
