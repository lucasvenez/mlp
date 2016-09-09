package io.github.lucasvenez.mlp.neuron;

import io.github.lucasvenez.mlp.function.ActivationFunction;
import io.github.lucasvenez.mlp.layer.Layer;

/**
 * 
 * @author <a href="http://lucasvenez.github.io/lucasvenez">Lucas Venezian Povoa</a>
 *
 * @param <T>
 */
public abstract class Neuron<T extends Layer> {

	protected T parentLayer;
	
	protected ActivationFunction activationFunction;
	
	/**
	 * 
	 */
	public Neuron() {
		
	}

	/**
	 * 
	 * @param activationFunction
	 */
	public Neuron(ActivationFunction activationFunction) {
		this();
		this.activationFunction = activationFunction;
	}
	
	/**
	 * 
	 * @param activationFunction
	 * @param parentLayer
	 */
	public Neuron(ActivationFunction activationFunction, T parentLayer) {
		this(activationFunction);
		this.parentLayer = parentLayer;
	}
	
	/**
	 * 
	 * @param layer
	 */
	public void setLayer(T layer) {
		this.parentLayer = layer;
	}
	
	/**
	 * 
	 * @return
	 */
	public ActivationFunction getActivationFunction() {
		return activationFunction;
	}

	/**
	 * 
	 * @param activationFunction
	 */
	public void setActivationFunction(ActivationFunction activationFunction) {
		this.activationFunction = activationFunction;
	}
	
}
