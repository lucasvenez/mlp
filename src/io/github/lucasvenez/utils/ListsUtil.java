package io.github.lucasvenez.utils;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ListsUtil<T extends Object> {

	/**
	 * @param list
	 */
	public int[] shuffle(final List<T> list) {

		int[] result = new int[list.size()];
		
		Random rnd = ThreadLocalRandom.current();

		for (int i = 0; i < list.size(); i++) {

			int index = rnd.nextInt(list.size());

			T a = list.get(index);

			list.set(index, list.get(i));

			list.set(i, a);
			
			result[i] = index;
		}
		
		return result;
	}

	private void shuffle(final List<T> list, int oldIndex, int newIndex) {
		
		T a = list.get(newIndex);

		list.set(newIndex, list.get(oldIndex));

		list.set(oldIndex, a);
	}

	public int[] shuffle(final List<T> list, int[] newOrder) {
		
		for (int j = 0; j < newOrder.length; j++)
			shuffle(list, j, newOrder[j]);
		
		return newOrder;
	}
}
