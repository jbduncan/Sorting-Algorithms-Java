/**
 * Mergesort2.java
 */

package uk.co.bluettduncanj.serial;


/**
 * @author Jonathan
 */
public class MergeSort2 {
  
  // Maximum size of a sub-array to be sorted using Insertion Sort instead of Mergesort
  private static int INSERTION_SORT_THRESHOLD = 7;
  
  public static void sort(int[] array) {
    //int[] aux = new int[array.length];
    //for (int i = 0; i < array.length; i++) {
    //  aux[i] = array[i];
    //}
    
    // Cloning is a bit faster than the above loop
    int[] aux = array.clone();
    sort(aux, array, 0, array.length-1);
  }
  
  private static void sort(int[] mergeFrom, int[] mergeTo, int start, int end) {
    // Top-down (recursive) mergesort method
    
    if (end <= start + INSERTION_SORT_THRESHOLD) { 
      insertionSort(mergeTo, start, end);
      return;
    }
    
    int mid = start + ((end - start) / 2);
    sort(mergeTo, mergeFrom, start, mid);
    sort(mergeTo, mergeFrom, mid + 1, end);
    
    if (!(mergeFrom[mid] > mergeFrom[mid + 1])) {
      //for (int i = start; i <= end; i++) {
        //mergeTo[i] = mergeFrom[i];
      //}
      System.arraycopy(mergeFrom, start, mergeTo, start, end - start + 1);
      return;
    }
    
    merge(mergeFrom, mergeTo, start, mid, end);
  }
  
  private static void merge(int[] mergeFrom, int[] mergeTo, int start, int mid, int end) {
    
    int i = start, j = mid + 1;
    for (int k = start; k <= end; k++) {
      if (i > mid) {
        mergeTo[k] = mergeFrom[j++];
      }
      else if (j > end) {
        mergeTo[k] = mergeFrom[i++];
      }
      else if (mergeFrom[j] < mergeFrom[i]) {
        mergeTo[k] = mergeFrom[j++];
      }
      else {
        mergeTo[k] = mergeFrom[i++];
      }
    }
    
  }
  
  private static void insertionSort(int[] array, int start, int end) {
    // Simple single-array implementation
    for (int i = start + 1; i <= end; i++) {
      for (int j = i; j > start && array[j] < array[j-1]; j--) {
        // Swap
        int temp = array[j];
        array[j] = array[j-1];
        array[j-1] = temp;
      }
    }
  }
  
}
