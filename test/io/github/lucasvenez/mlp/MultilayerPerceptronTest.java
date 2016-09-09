package io.github.lucasvenez.mlp;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import io.github.lucasvenez.mlp.exception.NeuralNetworkBuildingException;
import io.github.lucasvenez.mlp.exception.NeuralNetworkFowardException;
import io.github.lucasvenez.mlp.function.IdentityFunction;
import io.github.lucasvenez.mlp.function.SigmoidFunction;
import io.github.lucasvenez.mlp.function.ThresholdSigmoidFunction;
import io.github.lucasvenez.mlp.training.Backpropagation;

/**
 * This test uses weights and biases calculated with <a href="https://github.com/cbergmeir/RSNNS">RSNNS simulator</a>. This network receives three inputs:
 * two boolean values (0 = false, 1 = true) and an indicator of the logical operation 
 * (1 = xor, 2 = and, 3 = or). The result is the logical operation between the boolean values.  
 * 
 * @author Lucas Venezian Povoa
 * 
 */
public class MultilayerPerceptronTest {

	private final double TRUE = 1, FALSE = 0;
	
	private final double AND = 0, OR = 1, XOR = 2;
	
	
	@Test
	public void mlpTest() throws NeuralNetworkFowardException, NeuralNetworkBuildingException {
		
		/*
		 * Creating and configuring neural network 
		 */
		MultilayerPerceptron mlp = new MultilayerPerceptron();
		
		mlp.setInputLayer(3, new IdentityFunction());
		
		mlp.addHiddenLayer(6, new SigmoidFunction());
		
		mlp.setOutputLayer(1, new ThresholdSigmoidFunction());
		
		assertEquals(new Integer(31), new Integer(mlp.countWeights()));
		
		/*
		 * Training neural network
		 */
		Backpropagation back = new Backpropagation(mlp);
		
		/*
		 * Creating training data
		 */
		List<Double[]> inputs = new ArrayList<Double[]>();
		List<Double[]> outputs = new ArrayList<Double[]>();

		for (double i = 0; i < 2; i++)
			for (double j = 0; j < 2; j++) {
				inputs.add(new Double[] {i,  j,  AND});
				outputs.add(new Double[] {i + j == 2 ? TRUE : FALSE});
				
				inputs.add(new Double[] {i,  j,  OR});
				outputs.add(new Double[] {i + j > 0 ? TRUE : FALSE});
				
				inputs.add(new Double[] {i,  j,  XOR});
				outputs.add(new Double[] {i + j == 1 ? TRUE : FALSE});
			}
		
		back.setTraineInput(inputs);
		
		back.setTraineOutput(outputs);
		
		back.traine();
		
		/*
		 * Using neural network
		 */		
		assertEquals(new Double(FALSE), mlp.process(TRUE,  TRUE,  XOR)[0]);
		assertEquals(new Double(TRUE),  mlp.process(TRUE,  FALSE, XOR)[0]);
		assertEquals(new Double(TRUE),  mlp.process(FALSE, TRUE,  XOR)[0]);
		assertEquals(new Double(FALSE), mlp.process(FALSE, FALSE, XOR)[0]);
		
		assertEquals(new Double(TRUE),  mlp.process(TRUE,  TRUE,  OR)[0]);
		assertEquals(new Double(TRUE),  mlp.process(TRUE,  FALSE, OR)[0]);
		assertEquals(new Double(TRUE),  mlp.process(FALSE, TRUE,  OR)[0]);
		assertEquals(new Double(FALSE), mlp.process(FALSE, FALSE, OR)[0]);
		
		assertEquals(new Double(TRUE),  mlp.process(TRUE,  TRUE,  AND)[0]);
		assertEquals(new Double(FALSE), mlp.process(TRUE,  FALSE, AND)[0]);
		assertEquals(new Double(FALSE), mlp.process(FALSE, TRUE,  AND)[0]);
		assertEquals(new Double(FALSE), mlp.process(FALSE, FALSE, AND)[0]);
	}
	
	@Test
	public void parallelMlpTest() throws NeuralNetworkFowardException, NeuralNetworkBuildingException {
		
		/*
		 * Creating and configuring neural network 
		 */
		MultilayerPerceptron mlp = new MultilayerPerceptron();
		
		mlp.setInputLayer(3, new IdentityFunction());
		
		mlp.addParallelHiddenLayer(6, new SigmoidFunction());
		
		mlp.setParallelOutputLayer(1, new ThresholdSigmoidFunction());
		
		assertEquals(new Integer(31), new Integer(mlp.countWeights()));
		
		/*
		 * Training neural network
		 */
		Backpropagation back = new Backpropagation(mlp);

		/*
		 * Creating training data
		 */
		List<Double[]> inputs = new ArrayList<Double[]>();
		List<Double[]> outputs = new ArrayList<Double[]>();

		for (double i = 0; i < 2; i++)
			for (double j = 0; j < 2; j++) {
				inputs.add(new Double[] {i,  j,  AND});
				outputs.add(new Double[] {i + j == 2 ? TRUE : FALSE});
				
				inputs.add(new Double[] {i,  j,  OR});
				outputs.add(new Double[] {i + j > 0 ? TRUE : FALSE});
				
				inputs.add(new Double[] {i,  j,  XOR});
				outputs.add(new Double[] {i + j == 1 ? TRUE : FALSE});
			}
		
		back.setTraineInput(inputs);
		
		back.setTraineOutput(outputs);
		
		back.traine();
		
		/*
		 * Using neural network
		 */		
		assertEquals(new Double(FALSE), mlp.process(TRUE,  TRUE,  XOR)[0]);
		assertEquals(new Double(TRUE),  mlp.process(TRUE,  FALSE, XOR)[0]);
		assertEquals(new Double(TRUE),  mlp.process(FALSE, TRUE,  XOR)[0]);
		assertEquals(new Double(FALSE), mlp.process(FALSE, FALSE, XOR)[0]);
		
		assertEquals(new Double(TRUE),  mlp.process(TRUE,  TRUE,  OR)[0]);
		assertEquals(new Double(TRUE),  mlp.process(TRUE,  FALSE, OR)[0]);
		assertEquals(new Double(TRUE),  mlp.process(FALSE, TRUE,  OR)[0]);
		assertEquals(new Double(FALSE), mlp.process(FALSE, FALSE, OR)[0]);
		
		assertEquals(new Double(TRUE),  mlp.process(TRUE,  TRUE,  AND)[0]);
		assertEquals(new Double(FALSE), mlp.process(TRUE,  FALSE, AND)[0]);
		assertEquals(new Double(FALSE), mlp.process(FALSE, TRUE,  AND)[0]);
		assertEquals(new Double(FALSE), mlp.process(FALSE, FALSE, AND)[0]);
	}
}
