package io.github.lucasvenez.mlp;

import java.util.List;

import br.edu.ifsp.mlp.HiddenLayer;
import br.edu.ifsp.mlp.function.IdentityFunction;
import io.github.lucasvenez.mlp.function.ActivationFunction;
import io.github.lucasvenez.mlp.function.ThresholdFunction;

/**
 * 
 * @author <a href="http://lucasvenez.github.io">Lucas Venezian Povoa</a>
 *
 */
public class MultilayerPerceptron {
	
	private InputLayer inputLayer;
	
	private List<HiddenLayer> hiddenLayers;
	
	private OutputLayer outputLayer;

	public void addHiddenLayer(int numberOfNeurons, ActivationFunction thresholdFunction) {
		// TODO Auto-generated method stub
	}

	public void setInputLayer(int i, IdentityFunction identityFunction) {
		// TODO Auto-generated method stub
		
	}

	public void addHiddenLayer(HiddenLayer hiddenLayer) {
		// TODO Auto-generated method stub
		
	}

	public void setOutputLayer(int i, ThresholdFunction thresholdFunction) {
		// TODO Auto-generated method stub
		
	}

	public void process(Double ... inputs) {
		// TODO Auto-generated method stub
		
	}
	
	public void process(Integer ... inputs) {
		
		Double dInputs[] = new Double[inputs.length];
		
		for (int i = 0; i < dInputs.length; i++)
			dInputs[i] = new Double(inputs[i]);
		
		this.process(dInputs);
	}
}
