/**
 * InsertionSort2.java
 */

package uk.co.bluettduncanj.serial;


/**
 * @author Jonathan
 */
public class InsertionSort2 {

  public static void sort(int[] array, int start, int end) {
    int len = end - start + 1;

    // Put smallest element in position to serve as sentinel
    for (int i = end; i > start; i--)
      if (array[i] < array[i-1]) 
        swap(array, i, i - 1);

    // Insertion sort with half-exchanges
    for (int i = start + 2; i < len; i++) {
      int value = array[i];
      int j = i;
      while (value < array[j-1]) {
        array[j] = array[j-1];
        j--;
      }
      array[j] = value;
    }
  }
  
  private static void swap(int[] array, int start, int end) {
    int tmp = array[start];
    array[start] = array[end];
    array[end] = tmp;
  }
  
}
