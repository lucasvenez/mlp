package io.github.lucasvenez.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StatisticsTest {

	private final Statistics stat = new Statistics();
	
	@Test
	public void meanTest() {
		
		assertEquals(new Double(1.5), new Double(stat.mean(1, 2)));
		
		assertEquals(new Double(2.0), new Double(stat.mean(2, 2, 2, 2, 2)));
	}
	
	@Test
	public void sdTest() {
		
		assertEquals(new Double(0.5), new Double(stat.sd(1, 2)));
		
		assertEquals(new Double(0.0), new Double(stat.sd(2, 2, 2, 2, 2)));
	}
}
