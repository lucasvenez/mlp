package io.github.lucasvenez.stat.random;

public interface PoissonConstants {

	public final double a0 = -0.5;

	public final double a1 = 0.3333333;

	public final double a2 = -0.2500068;

	public final double a3 = 0.2000118;

	public final double a4 = -0.1661269;

	public final double a5 = 0.1421878;

	public final double a6 = -0.1384794;

	public final double a7 = 0.1250060;

	public final double one_7 = 0.1428571428571428571;

	public final double one_12 = 0.0833333333333333333;

	public final double one_24 = 0.0416666666666666667;

	/* 
	 * Factorial Table (0:9)! 
	 */
	public final double[] fact = { 1.0, 1.0, 2.0, 6.0, 24.0, 120.0, 720.0, 5040.0, 40320.0, 362880.0 };
}
