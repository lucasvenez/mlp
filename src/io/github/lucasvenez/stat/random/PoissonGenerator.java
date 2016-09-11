package io.github.lucasvenez.stat.random;

import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.copySign;
import static java.lang.Math.exp;
import static java.lang.Math.floor;
import static java.lang.Math.log;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * 
 * @author <a href="http://lucasvenez.github.io/">Lucas Venezian Povoa</a>
 * 
 * @see This code was directly adpted from R base project. 
 * Check source code in C <a href="http://goo.gl/iPBHBp">here</a>. 
 */
public class PoissonGenerator extends SampleGenerator implements PoissonConstants {

	private ExponentialGenerator expRandom = new ExponentialGenerator();
	
	private GammaGenerator gammaRandom = new GammaGenerator();
	
	/*
	 * These are static --- persistent between calls for same mu :
	 */
	private static int l = 0, m = 0;

	private static double b1 = 0, b2 = 0, c = 0, c0 = 0, c1 = 0, c2 = 0, c3 = 0;

	private static double p0 = 0, p = 0, q = 0, s = 0, d = 0, omega = 0;

	private static double[] pp = new double[36];
	
	/**
	 * Generates a random number from a Poisson distribution [1].
	 * 
	 * <br/><br/>
	 * <a href="http://dl.acm.org/citation.cfm?id=355997">[1]</a> Ahrens, J.H. and Dieter, U. (1982). 
	 * Computer generation of Poisson deviates from modified normal distributions. ACM Trans. 
	 * Math. Software 8, 163-179.
	 * 
	 * @param n is the number of elements into the sample 
	 * @param lambda 
	 * @return a sample following an approximate poisson distribution with n elements with mu = lambda
	 */
	public double[] generate(int n, double lambda) {
		
	    if(n <= 0 || lambda < 0)
	    	throw new ArithmeticException("Invalid function parameters: mu < 0 or size <= 0");

	    double[] result = new double[(int)n];
	    
	    for (int i = 0; i < n; i++)
	    	result [i] = (lambda == 0) ? 0 : this.generate(gammaRandom. generate(n, lambda / (double)n));
	     
	     return result;
	}
	
	/**
	 * 
	 * @param mu
	 * @return
	 */
	public double generate(double mu) {

		/*
		 * integer "w/o overflow"
		 */
		double big_l = 0.0;

		/*
		 * muold = 0.0
		 */
		double muprev = 0., muprev2 = 0.;

		/*
		 * Local Vars [initialize some for -Wall]:
		 */
		double del, difmuk = 0.0, E = 0.0, fk = 0.0, fx, fy, g, px, py, t = 0, u = 0.0, v, x;

		double pois = -1.;

		boolean kflag = true, big_mu, new_big_mu = false;

		int k;

		if (mu <= 0.)
			return 0.0;

		big_mu = mu >= 10.0;

		if (big_mu)
			new_big_mu = false;

		/*
		 * maybe compute new persistent par.s
		 */
		if (!(big_mu && mu == muprev)) {

			if (big_mu) {

				new_big_mu = true;

				/*
				 * Case A. (recalculation of s,d,l because mu has changed): The
				 * poisson probabilities pk exceed the discrete normal
				 * probabilities fk whenever k >= m(mu).
				 */
				muprev = mu;

				s = sqrt(mu);

				d = 6. * mu * mu;

				big_l = floor(mu - 1.1484);

				/*
				 * = an upper bound to m(mu) for all mu >= 10.
				 */

			} else {

				/*
				 * Small mu ( < 10) -- not using normal approx.
				 *
				 * Case B. (start new table and calculate p0 if necessary)
				 *
				 * muprev = 0.;-* such that next time, mu != muprev ..
				 */
				if (mu != muprev) {

					muprev = mu;

					m = max(1, (int) mu);

					/*
					 * pp[] is already ok up to pp[l]
					 */
					l = 0;

					q = p0 = p = exp(-mu);
				}

				while (true) {

					/*
					 * Step U. uniform sample for inversion method
					 */
					u = rnd.nextDouble();

					if (u <= p0)
						return 0.0;

					/*
					 * Step T. table comparison until the end pp[l] of the
					 * pp-table of cumulative poisson probabilities (0.458 > ~=
					 * pp[9](= 0.45792971447) for mu=10 )
					 */
					if (l != 0) {

						for (k = (u <= 0.458) ? 1 : min(l, m); k <= l; k++)
							if (u <= pp[k])
								return (double) k;

						if (l == 35) /* u > pp[35] */
							continue;
					}

					/*
					 * Step C. creation of new poisson probabilities p[l..] and
					 * their cumulatives q =: pp[k]
					 */
					l++;

					for (k = l; k <= 35; k++) {

						p *= mu / k;

						q += p;

						pp[k] = q;

						if (u <= q) {

							l = k;

							return (double) k;
						}
					}

					l = 35;

				}
			}

		}

		/*
		 * Only if mu >= 10 : ----------------------- Step N. normal sample
		 */
		g = mu + s * rnd.nextGaussian();

		/*
		 * norm_rand() ~ N(0,1), standard normal
		 */
		if (g >= 0.) {

			pois = floor(g);

			/*
			 * Step I. immediate acceptance if pois is large enough
			 */
			if (pois >= big_l)
				return pois;
			/*
			 * Step S. squeeze acceptance
			 */
			fk = pois;

			difmuk = mu - fk;

			u = rnd.nextDouble(); /* ~ U(0,1) - sample */

			if (d * u >= difmuk * difmuk * difmuk)
				return pois;
		}

		/*
		 * Step P. preparations for steps Q and H. (recalculations of parameters
		 * if necessary)
		 */

		if (new_big_mu || mu != muprev2) {
			/*
			 * Careful! muprev2 is not always == muprev because one might have
			 * exited in step I or S
			 */
			muprev2 = mu;
			omega = 1 / sqrt(2 * PI) / s;

			/*
			 * The quantities b1, b2, c3, c2, c1, c0 are for the Hermite
			 * approximations to the discrete normal probabilities fk.
			 */
			b1 = one_24 / mu;

			b2 = 0.3 * b1 * b1;

			c3 = one_7 * b1 * b2;

			c2 = b2 - 15. * c3;

			c1 = b1 - 6. * b2 + 45. * c3;

			c0 = 1. - b1 + 3. * b2 - 15. * c3;

			/*
			 * guarantees majorization by the 'hat'-function.
			 */
			c = 0.1069 / mu;
		}

		/*
		 * 'Subroutine' F is called (kflag=false for correct return)
		 */
		if (g >= 0.)
			kflag = false;

		while (true) {

			if (kflag) {
				/*
				 * Step E. Exponential Sample ~ Exp(1) (standard exponential)
				 */
				E = expRandom.generate();

				/*
				 * sample t from the laplace 'hat' (if t <= -0.6744 then pk < fk
				 * for all mu >= 10.)
				 */
				u = 2 * rnd.nextDouble() - 1.;

				t = 1.8 + copySign(E, u);
			}

			if (!kflag || t > -0.6744) {

				if (!kflag) {

					pois = floor(mu + s * t);

					fk = pois;

					difmuk = mu - fk;

					/*
					 * 'subroutine' F is called (kflag = 1 for correct return)
					 */
					kflag = true;
				}

				/*
				 * use factorial from table fact[]
				 */
				if (pois < 10) {

					px = -mu;

					py = pow(mu, pois) / fact[(int) pois];

				} else {

					/*
					 * Case pois >= 10 uses polynomial approximation a0-a7 for
					 * accuracy when advisable
					 */
					del = one_12 / fk;

					del = del * (1. - 4.8 * del * del);

					v = difmuk / fk;

					if (abs(v) <= 0.25)
						px = fk * v * v
								* (((((((a7 * v + a6) * v + a5) * v + a4) * v + a3) * v + a2) * v + a1) * v + a0) - del;

					else /* |v| > 1/4 */
						px = fk * log(1. + v) - difmuk - del;

					py = 1 / sqrt(2 * PI) / sqrt(fk);
				}

				x = (0.5 - difmuk) / s;

				x *= x;

				fx = -0.5 * x;

				fy = omega * (((c3 * x + c2) * x + c1) * x + c0);

				/*
				 * Step H. Hat acceptance (E is repeated on rejection)
				 */
				if (kflag && c * abs(u) <= py * exp(px + E) - fy * exp(fx + E))
					break;

				/*
				 * Step Q. Quotient acceptance (rare case)
				 */
				if (fy - u * fy <= py * exp(px - fx))
					break;
			}
		}

		return pois;
	}
}
