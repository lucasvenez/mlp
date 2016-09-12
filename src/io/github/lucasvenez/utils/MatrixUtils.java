package io.github.lucasvenez.utils;

import io.github.lucasvenez.exception.IncompatibleMatrixException;
import io.github.lucasvenez.exception.NotSquaredMatrixException;
import io.github.lucasvenez.stat.Stat;

import static java.lang.Math.pow;

public class MatrixUtils {

	private final Stat stat = new Stat();

	public Double[][] transpose(Double[][] matrix) {

		Double[][] result = new Double[matrix[0].length][matrix.length];

		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[0].length; j++)
				result[j][i] = matrix[i][j];

		return result;
	}

	public Double[][] inverse(Double[][] matrix) {
		return null;
	}

	public Double[][] sum(Double[][] m1, Double[][] m2) throws IncompatibleMatrixException {

		this.checkCompatibilityOfMatrices(m1, m2);

		Double[][] result = new Double[m1.length][m1[0].length];

		for (int i = 0; i < m1.length; i++)
			for (int j = 0; j < m1[0].length; j++)
				result[i][j] = m1[i][j] + m2[i][j];

		return result;
	}

	public Double[][] sum(Double scalar, Double[][] matrix) {

		Double[][] result = new Double[matrix.length][matrix[0].length];

		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[0].length; j++)
				result[i][j] = scalar + matrix[i][j];

		return result;
	}

	public Double[][] sum(Double[][] matrix, Double scalar) {

		Double[][] result = new Double[matrix.length][matrix[0].length];

		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[0].length; j++)
				result[i][j] = matrix[i][j] + scalar;

		return result;
	}

	public Double[][] difference(Double[][] m1, Double[][] m2) {

		this.checkCompatibilityOfMatrices(m1, m2);

		Double[][] result = new Double[m1.length][m1[0].length];

		for (int i = 0; i < m1.length; i++)
			for (int j = 0; j < m1[0].length; j++)
				result[i][j] = m1[i][j] - m2[i][j];

		return result;
	}

	public Double[][] difference(Double scalar, Double[][] matrix) {

		Double[][] result = new Double[matrix.length][matrix[0].length];

		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[0].length; j++)
				result[i][j] = scalar - matrix[i][j];

		return result;
	}

	public Double[][] difference(Double[][] matrix, Double scalar) {

		Double[][] result = new Double[matrix.length][matrix[0].length];

		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[0].length; j++)
				result[i][j] = matrix[i][j] - scalar;

		return result;
	}

	public Double[][] multipication(Double scalar, Double[][] matrix) {

		Double[][] result = new Double[matrix.length][matrix[0].length];

		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[0].length; j++)
				result[i][j] = scalar * matrix[i][j];

		return result;
	}

	/**
	 * It is an alias for determinant
	 * 
	 * @param matrix
	 * @return
	 */
	public Double det(Double[][] matrix) throws NotSquaredMatrixException {
		return determinant(matrix);
	}

	/**
	 * 
	 * @param matrix
	 * @return
	 * @throws NotSquaredMatrixException
	 * @see https://en.wikipedia.org/wiki/Determinant
	 */
	public Double determinant(Double[][] matrix) throws NotSquaredMatrixException {

		if (!isSquared(matrix))
			throw new NotSquaredMatrixException(matrix);

		int n = matrix.length;

		Double result = 0.0;

		if (n < 4)

			for (int j = 0; j < n - (n < 3 ? 1 : 0); j++) {

				Double[] partA = new Double[n], partB = new Double[n];

				int[] indexA = new int[n], indexB = new int[n];

				for (int i = 0; i < n; i++) {

					partA[i] = matrix[i][indexA[i] = (i + j) % n];

					partB[i] = matrix[i][indexB[i] = stat.mod(((n - 1) - (i + j)), n)];
				}

				result += stat.sing(indexA) * stat.prod(partA) + stat.sing(indexB) * stat.prod(partB);
			}

		else
			for (int i = 0; i < n; i++)
				result += pow(-1, 1 + i) * matrix[1][i] * 
							determinant(this.removeRowAndColumn(matrix, 1, i));

		return result;
	}

	public Double[][] removeRowAndColumn(Double[][] matrix, int row, int column) {

		Double[][] result = new Double[matrix.length - 1][matrix[0].length - 1];

		int k = 0, l = 0;

		for (int i = 0; i < matrix.length; i++) {

			if (i != row) {

				for (int j = 0; j < matrix[0].length; j++)
					if (j != column)
						result[k][l++] = matrix[i][j];

				k++;
			}

			l = 0;
		}

		return result;
	}

	private void checkCompatibilityOfMatrices(Double[][] m1, Double[][] m2) throws IncompatibleMatrixException {

		if (m1.length != m2.length || m1[0].length != m2[0].length)
			throw new IncompatibleMatrixException(m1, m2);
	}

	/**
	 * 
	 * @param matrix
	 * @return
	 */
	private boolean isSquared(Double[][] matrix) {
		return matrix.length == matrix[0].length;
	}

	/**
	 * 
	 * @param matrix
	 * @return
	 */
	public String toString(Double[][] matrix) {

		final String separator = ", ";

		StringBuffer result = new StringBuffer();

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				result.append(matrix[i][j]);
				result.append(separator);
			}

			result.setLength(result.length() - separator.length());

			result.append("\n");
		}

		return result.toString();
	}
}
