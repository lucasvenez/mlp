package io.github.lucasvenez.math.random;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DistributionGeneratorTest {

	@Test
	public void gaussianTest() {
		
		DistributionGenerator gen = new DistributionGenerator();
		
		double[] r = gen.gaussian(10, 2, 1);
		
		assertEquals(new Integer(10), new Integer(r.length));
	}
}
