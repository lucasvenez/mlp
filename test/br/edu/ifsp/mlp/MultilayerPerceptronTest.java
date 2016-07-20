package br.edu.ifsp.mlp;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This test uses weights and biases calculated with <a href="https://github.com/cbergmeir/RSNNS">RSNNS simulator</a>. This network receives three inputs:
 * two boolean values (0 = false, 1 = true) and an indicator of the logical operation 
 * (1 = xor, 2 = and, 3 = or). The result is the logical operation between the boolean values.  
 * @author Lucas Venezian Povoa
 * 
 */
public class MultilayerPerceptronTest {

	public static final double TRUE  = 1;
	
	public static final double FALSE = 0;
	
	public static final double XOR   = 1;
	
	public static final double AND   = 2;
	
	public static final double OR    = 3;

	@Test
	public void test() {

		MultilayerPerceptron mlp = new MultilayerPerceptron(3, 6, 1);

		mlp.setBiases(new Double[] { -0.0876190811395645, 0.217407286167145, 0.103654146194458, -10.6149482727051,
				-1.18081986904144, -3.82895517349243, -3.88494157791138, -1.46863925457001, 0.804240643978119,
				-0.469541519880295 });

		mlp.setWeightsBetweenInputAndHidden(new Double[] { -0.845621883869171, -0.84678989648819, 4.63171911239624,
				4.10689640045166, 4.08154153823853, -0.259971171617508, -5.0947060585022, -5.08982944488525,
				6.00834178924561, 6.27885246276855, 6.28793430328369, -5.93942022323608, -0.0375205054879189,
				-0.214145436882973, -0.145714998245239, -3.77608919143677, -3.77428007125854, 0.323262423276901,
				10.5026502609253, 6.19403505325317, -9.94169330596924, -10.1965217590332, -0.862910747528076,
				-5.33192253112793 });

		try {

			mlp.setInputs(new Double[] { TRUE, TRUE, XOR });

			for (Double f : mlp.forward())
				assertTrue(f < 0.5);

			mlp.setInputs(new Double[] { TRUE, FALSE, XOR });

			for (Double f : mlp.forward())
				assertTrue(f >= 0.5);

			mlp.setInputs(new Double[] { TRUE, FALSE, AND });

			for (Double f : mlp.forward())
				assertTrue(f < 0.5);
			
			mlp.setInputs(new Double[] { TRUE, TRUE, AND });

			for (Double f : mlp.forward())
				assertTrue(f >= 0.5);
			
			mlp.setInputs(new Double[] { FALSE, FALSE, OR });

			for (Double f : mlp.forward())
				assertTrue(f < 0.5);
			
			mlp.setInputs(new Double[] { TRUE, TRUE, OR });

			for (Double f : mlp.forward())
				assertTrue(f >= 0.5);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
