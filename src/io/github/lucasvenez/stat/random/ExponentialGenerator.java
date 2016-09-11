package io.github.lucasvenez.stat.random;

/**
 * 
 * @author <a href="http://lucasvenez.github.io/">Lucas Venezian Povoa</a>
 * @see https://github.com/SurajGupta/r-source/blob/db0ce241899769e0f89c00fb520fa4296ebe88e9/src/nmath/sexp.c
 */
public class ExponentialGenerator extends SampleGenerator implements ExponentialConstants {

	public double generate() {

		double a = 0.0;

		double u = rnd.nextDouble(); /* precaution if u = 0 is ever returned */

		while (u <= 0. || u >= 1.)
			u = rnd.nextDouble();

		while (true) {

			u += u;

			if (u > 1.)
				break;

			a += q[0];

		}

		u -= 1.0;

		if (u <= q[0])
			return a + u;

		int i = 0;

		double ustar = rnd.nextDouble(), umin = ustar;

		do {

			ustar = rnd.nextDouble();

			if (umin > ustar)
				umin = ustar;

			i++;

		} while (u > q[i]);

		return a + umin * q[0];
	}
}
