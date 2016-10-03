package io.github.lucasvenez.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MatrixUtilsTest {

	private final MatrixUtils utils = new MatrixUtils();
	
	@Test
	public void determinant2x2Test() {

		Double[][] matrix = new Double[][] {
			{1.0, 0.0}, 
			{0.0, 1.0}};
		
		assertEquals(new Double(1.0), utils.det(matrix));
		
		matrix = new Double[][] {
			{1.0, 3.0}, 
			{2.0, 4.0}};
		
		assertEquals(new Double(-2.0), utils.det(matrix));
		
		matrix = new Double[][] {
			{0.0, 0.0}, 
			{0.0, 0.0}};
			
		assertEquals(new Double(0.0), utils.det(matrix));
	}
	
	@Test
	public void determinant3x3Test() {

		Double[][] matrix = new Double[][] {
			{1.0, 0.0, 0.0}, 
			{0.0, 1.0, 0.0}, 
			{0.0, 0.0, 1.0}};
		
		assertEquals(new Double(1.0), utils.det(matrix));
		
		matrix = new Double[][] {
			{1.0, 4.0, 7.0}, 
			{2.0, 5.0, 8.0}, 
			{3.0, 6.0, 9.0}};
		
		assertEquals(new Double(0), utils.det(matrix));
		
		matrix = new Double[][] {
			{3.0, 9.0, 7.0}, 
			{1.0, 3.0, 6.0}, 
			{7.0, 5.0, 2.0}};
		
		assertEquals(new Double(176.0), utils.det(matrix));
		
		matrix = new Double[][] {
			{3.0, 9.0, 7.0}, 
			{1.0, 6.0, 4.0}, 
			{2.0, 4.0, 3.0}};
		
		assertEquals(new Double(-5), utils.det(matrix));
	}
	
	@Test
	public void determinant4x4Test() {
		
		Double[][] matrix = new Double[][] {
			{1.0, 0.0, 0.0, 0.0}, 
			{0.0, 1.0, 0.0, 0.0}, 
			{0.0, 0.0, 1.0, 0.0}, 
			{0.0, 0.0, 0.0, 1.0}};
		
		assertEquals(new Double(1.0), utils.det(matrix));
		
		matrix = new Double[][] {
			{1.0, 5.0, 9.0,  13.0}, 
			{2.0, 6.0, 10.0, 14.0}, 
			{3.0, 7.0, 11.0, 15.0}, 
			{4.0, 8.0, 12.0, 16.0}};
		
		assertEquals(new Double(0.0), utils.det(matrix));
		
		matrix = new Double[][] {
			{3.0, 9.0, 6.0, 1.0}, 
			{3.0, 3.0, 2.0, 7.0}, 
			{1.0, 5.0, 4.0, 9.0}, 
			{7.0, 7.0, 6.0, 8.0}};
			
		assertEquals(new Double(356.0), utils.det(matrix));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void removeRowAndColumnTest() {
		
		Double[][] matrix = new Double[][] {
			{1.0, 2.0, 3.0},
			{4.0, 5.0, 6.0},
			{7.0, 8.0, 9.0}
		};
		
		Double[][] expect = new Double[][] {
			{1.0, 2.0},
			{7.0, 8.0}
		};
		
		assertEquals(expect, utils.removeRowAndColumn(matrix, 1, 2));
		
		matrix = new Double[][] {
			{1.0, 2.0, 3.0, 6.0},
			{4.0, 5.0, 6.0, 6.0},
			{7.0, 8.0, 9.0, 6.0}
		};
		
		expect = new Double[][] {
			{1.0, 2.0, 3.0},
			{7.0, 8.0, 9.0}
		};
		
		assertEquals(expect, utils.removeRowAndColumn(matrix, 1, 3));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void identityTest() {
		
		Double[][] expect = new Double[][] {
			{1.0, 0.0, 0.0},
			{0.0, 1.0, 0.0},
			{0.0, 0.0, 1.0}
		};
		
		assertEquals(expect, utils.identity(3));
	}
}
