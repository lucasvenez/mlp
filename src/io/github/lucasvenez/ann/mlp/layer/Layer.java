package io.github.lucasvenez.ann.mlp.layer;

import java.util.List;

import io.github.lucasvenez.ann.mlp.neuron.Neuron;

/**
 * 
 * @author <a href="http://lucasvenez.github.io">Lucas Venezian Povoa</a>
 *
 */
public interface Layer {

	public List<Double> process(final List<Double> inputs) throws Exception;
	
	public List<? extends Neuron<? extends Layer>> getNeurons();
	
	public int getNumberOfNeurons();
}
