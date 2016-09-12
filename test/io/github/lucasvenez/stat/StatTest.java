package io.github.lucasvenez.stat;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.github.lucasvenez.stat.Stat;

public class StatTest {

	private final Stat stat = new Stat();
	
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
	
	@Test
	public void modTest() {
		
		assertEquals(new Double(2.0), stat.mod(-2.0, 4.0));
		
		assertEquals(new Double(6.0), stat.mod(-4.0, 10.0));
	}
}
