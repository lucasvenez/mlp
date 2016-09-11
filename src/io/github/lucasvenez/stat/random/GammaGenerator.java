package io.github.lucasvenez.stat.random;

import static java.lang.Math.abs;
import static java.lang.Math.exp;
import static java.lang.Math.log;
import static java.lang.Math.sqrt;

public class GammaGenerator extends SampleGenerator implements GammaConstants {

	/* State variables [FIXME for threading!] : */
	private double aa = 0.;

	private double aaa = 0.;

	private double s, s2, d; /* no. 1 (step 1) */

	private double q0, b, si, c;/* no. 2 (step 4) */

	private final ExponentialGenerator expRandom = new ExponentialGenerator();

	public double generate(double a, double scale) {

		if (a <= 0.0 || scale <= 0.0) {

			if (scale == 0. || a == 0.)
				return 0.0;

			throw new ArithmeticException("error!");
		}

		/* 
		 * GS algorithm for parameters a < 1 
		 */
		double x, e;
		
		if (a < 1.0) {
			
			e = 1.0 + exp(-1) * a;

			while (true) {

				double p = e * rnd.nextDouble();

				if (p >= 1.0) {
					
					x = -log((e - p) / a);
					
					if (rnd.nextDouble() >= (1.0 - a) * log(x))
						break;
					
				} else {
					
					x = exp(log(p) / a);
					
					if (rnd.nextDouble() >= x)
						break;
				}
			}
			
			return scale * x;
		}

		/*
		 * --- a >= 1 : GD algorithm --- 
		 */

		/* 
		 * Step 1: Recalculations of s2, s, d if a has changed 
		 */
		if (a != aa) {
		
			aa = a;
			
			s2 = a - 0.5;
			
			s = sqrt(s2);
			
			d = sqrt32 - s * 12.0;
		}
		/*
		 * Step 2: t = standard normal deviate, x = (s,1/2) -normal deviate.
		 */

		/*
		 * immediate acceptance (i)
		 */
		double t = rnd.nextGaussian();
		
		x = s + 0.5 * t;
		
		double ret_val = x * x;
		
		if (t >= 0.0)
			return scale * ret_val;

		/*
		 * Step 3: u = 0,1 - uniform sample. squeeze acceptance (s)
		 */
		
		double u = rnd.nextDouble();
		
		if (d * u <= t * t * t)
			return scale * ret_val;

		/* 
		 * Step 4: recalculations of q0, b, si, c if necessary 
		 */

		if (a != aaa) {
			
			aaa = a;
			
			double r = 1.0 / a;
			
			q0 = ((((((q7 * r + q6) * r + q5) * r + q4) * r + q3) * r + q2) * r + q1) * r;

			/* Approximation depending on size of parameter a 
			 * The constants in the expressions for b, si and c
			 * were established by numerical experiments
			 */

			if (a <= 3.686) {
				
				b = 0.463 + s + 0.178 * s2;
				
				si = 1.235;
				
				c = 0.195 / s - 0.079 + 0.16 * s;
				
			} else if (a <= 13.022) {
				
				b = 1.654 + 0.0076 * s2;
				
				si = 1.68 / s + 0.275;
				
				c = 0.062 / s + 0.024;
				
			} else {
				
				b = 1.77;
				
				si = 0.75;
				
				c = 0.1515 / s;
			}
		}
		
		/* 
		 * Step 5: no quotient test if x not positive 
		 */

		double v, q;
		
		if (x > 0.0) {
			
			/* 
			 * Step 6: calculation of v and quotient q 
			 */
			v = t / (s + s);
			
			if (abs(v) <= 0.25)
				q = q0 + 0.5 * t * t * ((((((a7 * v + a6) * v + a5) * v + a4) * v + a3) * v + a2) * v + a1) * v;
			
			else
				q = q0 - s * t + 0.25 * t * t + (s2 + s2) * log(1.0 + v);
			
			/* 
			 * Step 7: quotient acceptance (q) 
			 */
			if (log(1.0 - u) <= q)
				return scale * ret_val;
		}

		while (true) {
			
			/*
			 * Step 8: e = standard exponential deviate u = 0,1 -uniform deviate
			 * t = (b,si)-double exponential (laplace) sample
			 */
			e = expRandom.generate();
			
			u = rnd.nextDouble();
			
			u = u + u - 1.0;
			
			if (u < 0.0)
				t = b - si * e;
			
			else
				t = b + si * e;
			
			/* 
			 * Step 9: rejection if t < tau(1) = -0.71874483771719 
			 */
			if (t >= -0.71874483771719) {
				
				/* 
				 * Step 10: calculation of v and quotient q 
				 */
				v = t / (s + s);

				if (abs(v) <= 0.25)
					q = q0 + 0.5 * t * t * ((((((a7 * v + a6) * v + a5) * v + a4) * v + a3) * v + a2) * v + a1) * v;
				
				else
					q = q0 - s * t + 0.25 * t * t + (s2 + s2) * log(1.0 + v);
				
				/* 
				 * Step 11: hat acceptance (h) 
				 */
				
				/* 
				 * if q not positive then go to step 8 
				 */
				if (q > 0.0) {
					
					double w = exp(q) - 1;
					
					/*
					 * original code had approximation with rel.err < 2e-7
					 */
					
					/* 
					 * if t is rejected sample again at step 8 
					 */
					if (c * abs(u) <= w * exp(e - 0.5 * t * t))
						break;
				}
			}
		} // repeat .. until `t' is accepted
		
		x = s + 0.5 * t;
		
		return scale * x * x;
	}

}