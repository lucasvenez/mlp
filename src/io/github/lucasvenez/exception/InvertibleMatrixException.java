package io.github.lucasvenez.exception;

/**
 * 
 * @author <a href="http://lucasvenez.github.io/">Lucas Venezian Povoa</a>
 */
public class InvertibleMatrixException extends Exception {

	private static final long serialVersionUID = 2126144790221986775L;

	public InvertibleMatrixException() {
		super("Invertible matrix with determinant equals to zero.");
	}
}
