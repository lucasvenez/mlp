package io.github.lucasvenez.mlp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.github.lucasvenez.mlp.exception.NeuralNetworkFowardException;
import io.github.lucasvenez.mlp.function.ActivationFunction;
import io.github.lucasvenez.mlp.function.IdentityFunction;
import io.github.lucasvenez.mlp.layer.HiddenLayer;
import io.github.lucasvenez.mlp.layer.InputLayer;
import io.github.lucasvenez.mlp.layer.OutputLayer;

/**
 * 
 * @author <a href="http://lucasvenez.github.io">Lucas Venezian Povoa</a>
 *
 */
public class MultilayerPerceptron {
	
	private InputLayer inputLayer;
	
	private final List<HiddenLayer> hiddenLayers = new ArrayList<HiddenLayer>();
	
	private OutputLayer outputLayer;

	public void addHiddenLayer(int numberOfNeurons, ActivationFunction activationFunction) {
		hiddenLayers.add(new HiddenLayer(numberOfNeurons, activationFunction));
	}

	public void setInputLayer(int numberOfNeurons, ActivationFunction activationFunction) {
		this.inputLayer = new InputLayer(numberOfNeurons, activationFunction);
	}

	public void addHiddenLayer(HiddenLayer hiddenLayer) {
		this.hiddenLayers.add(hiddenLayer);
	}

	public void setOutputLayer(int numberOfNeurons, ActivationFunction activationFunction) {
		this.outputLayer = new OutputLayer(numberOfNeurons, activationFunction);
	}
	
	public void setOutputLayer(int numberOfNeurons) {
		this.outputLayer = new OutputLayer(numberOfNeurons, new IdentityFunction());
	}

	public Double[] process(Double ... inputs) throws NeuralNetworkFowardException {
		return process(Arrays.asList(inputs)).toArray(new Double[outputLayer.getNeurons().size()]);
	}
	
	public List<Double> process(List<Double> inputs) throws NeuralNetworkFowardException {
		
		List<Double> outputs = this.inputLayer.process(inputs);
		
		for (int i = 0; i < hiddenLayers.size(); i++)
			outputs = hiddenLayers.get(i).process(outputs);
		
		return this.outputLayer.process(outputs);
	}
	
	public Double[] process(Integer ... inputs) throws NeuralNetworkFowardException {
		
		Double dInputs[] = new Double[inputs.length];
		
		for (int i = 0; i < dInputs.length; i++)
			dInputs[i] = new Double(inputs[i]);
		
		return this.process(dInputs);
	}
}
