package io.github.lucasvenez.mlp.layer;

import java.util.List;

import io.github.lucasvenez.mlp.neuron.Neuron;

/**
 * 
 * @author <a href="http://lucasvenez.github.io">Lucas Venezian Povoa</a>
 *
 */
public interface Layer {

	List<? extends Neuron<? extends Layer>> getNeurons();

}
