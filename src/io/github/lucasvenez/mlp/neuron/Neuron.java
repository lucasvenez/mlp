package io.github.lucasvenez.mlp.neuron;

import io.github.lucasvenez.mlp.function.ActivationFunction;
import io.github.lucasvenez.mlp.layer.Layer;

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
}
