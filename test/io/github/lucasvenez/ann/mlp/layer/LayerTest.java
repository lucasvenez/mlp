package io.github.lucasvenez.ann.mlp.layer;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import io.github.lucasvenez.ann.function.IdentityFunction;
import io.github.lucasvenez.ann.function.SigmoidFunction;
import io.github.lucasvenez.exception.NeuralNetworkFowardException;
import io.github.lucasvenez.stat.random.UniformGenerator;

public class LayerTest {

	private final List<Double> inputs = new ArrayList<Double>();
	
	private final int NUMBER_OF_NEURONS = 6;
	
	@Before
	public void setUp() {
		
		for (int i = 0; i < 3; i++)
			inputs.add(i / (double)NUMBER_OF_NEURONS);
	}
	
	@Test
	public void serialLayer() throws NeuralNetworkFowardException {

		InputLayer il = new InputLayer(3, new IdentityFunction());
		
		ProcessingLayer pl = new ProcessingLayer(NUMBER_OF_NEURONS, new SigmoidFunction(), il);
		
		pl.setWeights(new UniformGenerator().generate(NUMBER_OF_NEURONS * 4, 0.0, 1.0));
		
		pl.process(inputs);
	}
	
	@Test
	public void parallelLayer() throws NeuralNetworkFowardException {

		InputLayer il = new InputLayer(3, new IdentityFunction());
		
		ParallelProcessingLayer pl = 
				new ParallelProcessingLayer(
					NUMBER_OF_NEURONS, new SigmoidFunction(), il);
		
		pl.setWeights(new UniformGenerator().generate(NUMBER_OF_NEURONS * 4, -1.0, 1.0));
		
		pl.process(inputs);
	}
}
