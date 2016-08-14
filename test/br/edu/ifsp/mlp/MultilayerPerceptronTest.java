package br.edu.ifsp.mlp;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.edu.ifsp.mlp.function.IdentityFunction;
import br.edu.ifsp.mlp.function.SigmoidFunction;
import io.github.lucasvenez.mlp.MultilayerPerceptron;
import io.github.lucasvenez.mlp.function.ThresholdFunction;

/**
 * This test uses weights and biases calculated with <a href="https://github.com/cbergmeir/RSNNS">RSNNS simulator</a>. This network receives three inputs:
 * two boolean values (0 = false, 1 = true) and an indicator of the logical operation 
 * (1 = xor, 2 = and, 3 = or). The result is the logical operation between the boolean values.  
 * @author Lucas Venezian Povoa
 * 
 */
public class MultilayerPerceptronTest {

	@Test
	public void mlpTest() {
		
		/*
		 * Creating and configuring neural network 
		 */
		MultilayerPerceptron mlp = new MultilayerPerceptron();
		
		mlp.setInputLayer(10, new IdentityFunction());
		
		mlp.addHiddenLayer(2, new SigmoidFunction());
		
		HiddenLayer hiddenLayer = new HiddenLayer();
		
		hiddenLayer.addNeuron(new HiddenNeuron(new SigmoidFunction()));
		
		mlp.addHiddenLayer(hiddenLayer);
		
		mlp.setOutputLayer(1, new ThresholdFunction(0.5));
		
		/*
		 * Training neural network
		 */
		Backpropagation back = new Backpropagation(mlp);

		final int TRUE = 1, FALSE = 0;
		final int AND = 0, OR = 1, XOR = 2;
		
		/*
		 * Creating training data
		 */
		List<Integer[]> inputs = new ArrayList<Integer[]>();
		List<Integer[]> outputs = new ArrayList<Integer[]>();

		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++) {
				inputs.add(new Integer[] {i,  j,  AND});
				outputs.add(new Integer[] {i + j == 2 ? 1 : 0});
				
				inputs.add(new Integer[] {i,  j,  OR});
				outputs.add(new Integer[] {i + j > 0 ? 1 : 0});
				
				inputs.add(new Integer[] {i,  j,  XOR});
				outputs.add(new Integer[] {i + j == 1 ? 1 : 0});
			}
		
		back.setTraineInput(inputs);
		
		back.setTraineOutput(outputs);
		
		back.traine();
		
		/*
		 * Using neural network
		 */
		
		mlp.process(TRUE, TRUE, AND);
		
		mlp.process(FALSE, TRUE, OR);
		
		mlp.process(TRUE, TRUE, XOR);
		
		assertTrue(true);
	}
}
