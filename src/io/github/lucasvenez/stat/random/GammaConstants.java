package io.github.lucasvenez.stat.random;

public interface GammaConstants {

	public final double sqrt32 = 5.656854;
	//public final double exp_m1 = 0.36787944117144232159; /* exp(-1) = 1/e */

	/* Coefficients q[k] - for q0 = sum(q[k]*a^(-k))
	 * Coefficients a[k] - for q = q0+(t*t/2)*sum(a[k]*v^k)
	 * Coefficients e[k] - for exp(q)-1 = sum(e[k]*q^k)
	 */
	public final double q1 = 0.04166669;
	public final double q2 = 0.02083148;
	public final double q3 = 0.00801191;
	public final double q4 = 0.00144121;
	public final double q5 = -7.388e-5;
	public final double q6 = 2.4511e-4;
	public final double q7 = 2.424e-4;

	public final double a1 = 0.3333333;
	public final double a2 = -0.250003;
	public final double a3 = 0.2000062;
	public final double a4 = -0.1662921;
	public final double a5 = 0.1423657;
	public final double a6 = -0.1367177;
	public final double a7 = 0.1233795;
}
