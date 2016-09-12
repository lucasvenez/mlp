package io.github.lucasvenez.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 
 * @author <a href="http://lucasvenez.github.io/">Lucas Venezian Povoa</a>
 */
public class ArrayUtils {

	/**
	 * 
	 * @param primitive
	 * @return
	 */
	public static Double[] toObject(double[] primitive) {

		Double[] result = new Double[primitive.length];

		for (int i = 0; i < primitive.length; i++)
			result[i] = primitive[i];

		return result;
	}

	/**
	 * 
	 * @param object
	 * @return
	 */
	public static double[] toPrimitive(Double[] object) {

		double[] result = new double[object.length];

		for (int i = 0; i < object.length; i++)
			result[i] = object[i];

		return result;

	}

	/**
	 * 
	 * @param primitive
	 * @return
	 */
	public static Integer[] toObject(int[] primitive) {

		Integer[] result = new Integer[primitive.length];

		for (int i = 0; i < primitive.length; i++)
			result[i] = primitive[i];

		return result;
	}

	/**
	 * 
	 * @param object
	 * @return
	 */
	public static int[] toPrimitive(Integer[] object) {

		int[] result = new int[object.length];

		for (int i = 0; i < object.length; i++)
			result[i] = object[i];

		return result;
	}

	/**
	 * 
	 * @param primitive
	 * @return
	 */
	public static Boolean[] toObject(boolean[] primitive) {

		Boolean[] result = new Boolean[primitive.length];

		for (int i = 0; i < primitive.length; i++)
			result[i] = primitive[i];

		return result;
	}

	/**
	 * 
	 * @param object
	 * @return
	 */
	public static boolean[] toPrimitive(Boolean[] object) {

		boolean[] result = new boolean[object.length];

		for (int i = 0; i < object.length; i++)
			result[i] = object[i];

		return result;
	}

	/**
	 * @see http://stackoverflow.com/questions/1519736/random-shuffling-of-an-array
	 * @param array
	 */
	public static void shuffleArray(Double[] array) {

		Random rnd = ThreadLocalRandom.current();

		for (int i = array.length - 1; i > 0; i--) {

			int index = rnd.nextInt(i + 1);

			Double a = array[index];

			array[index] = array[i];

			array[i] = a;
		}
	}

	public static String toString(Double[] array) {

		final String separator = ", ";

		StringBuffer result = new StringBuffer();

		for (int i = 0; i < array.length; i++) {

			result.append(array[i]);

			result.append(separator);
		}

		result.setLength(result.length() - separator.length());

		return result.toString();
	}
	
	public static String toString(int[] array) {
		
		Double[] arr = new Double[array.length];
		
		for (int i = 0; i < array.length; i++)
			arr[i] = new Double(array[i]);
		
		return ArrayUtils.toString(arr);
	}
}
