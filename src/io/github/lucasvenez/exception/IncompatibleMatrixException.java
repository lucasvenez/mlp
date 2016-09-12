package io.github.lucasvenez.exception;

/**
 * 
 * @author <a href="http://lucasvenez.github.io/">Lucas Venezian Povoa</a>
 */
public class IncompatibleMatrixException extends ArithmeticException {

	private static final long serialVersionUID = 5330163970045232963L;

	public IncompatibleMatrixException(Number[][] m1, Number[][] m2) {
		super(
			"First matrix has " + m1.length + " rows and " + m1[0].length + " columns. " +
			"Second one has " + m2.length + " rows and " + m2[0].length + " columns."
		);
	}
}
