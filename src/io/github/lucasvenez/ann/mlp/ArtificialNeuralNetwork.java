package io.github.lucasvenez.ann.mlp;

/**
 * 
 * @author <a href="http://lucasvenez.github.io/">Lucas Venezian Povoa</a>
 */
public abstract class ArtificialNeuralNetwork {

	/**
	 * 
	 * @param inputs
	 * @return
	 */
	public abstract Double[] apply(Double ... inputs);
} 