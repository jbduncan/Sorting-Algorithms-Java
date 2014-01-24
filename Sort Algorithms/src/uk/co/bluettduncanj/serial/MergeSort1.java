/**
 * Mergesort1.java
 */

package uk.co.bluettduncanj.serial;


/**
 * @author Jonathan
 */
public class MergeSort1 {
  
  private static int INSERTION_SORT_THRESHOLD = 7; // Max. size of a sub-array to be sorted using Insertion Sort instead of Mergesort

  public static void sort(int[] array) {
    int[] aux = new int[array.length];
    sort(array, aux, 0, array.length-1);
  }
  
  public static void sort(int[] array, int start, int end) {
    int[] aux = new int[end - start + 1];
    sort(array, aux, start, end);
  }
  
  // Mergesort array[start..end] using auxiliary array aux[start..end]
  private static void sort(int[] array, int[] aux, int start, int end) {
    // Top-down (recursive) mergesort method
    
    //if (end <= start) {
    //  return;
    //}
    
    if (end <= start + INSERTION_SORT_THRESHOLD) {
      insertionSort(array, start, end);
      return;
    }
    
    int mid = start + ((end - start) / 2);
    sort(array, aux, start, mid);
    sort(array, aux, mid + 1, end);
    merge(array, aux, start, mid, end);
  }
  
  private static void merge(int[] array, int[] aux, int start, int mid, int end) {
    
    // Copy to aux[]
    
    for (int k = start; k <= end; k++) {
      aux[k] = array[k];
    }
    
    // Merge and store back into array[]
    int i = start, j = mid + 1;
    for (int k = start; k <= end; k++) {
      if (i > mid) {
        // Indicates all elements in aux[start..mid] (equivalent to 'left sublist' in SimpleMergesort.java) 
        // have been merged back into array[].
        // Therefore we can only continue adding elements from aux[mid+1..end].
        array[k] = aux[j++];
      }
      else if (j > end) {
        // Indicates all elements in aux[mid+1..end] (equivalent to 'right sublist' in SimpleMergesort.java) 
        // have been merged back into array[].
        // Therefore we can only continue adding elements from aux[start..mid].
        array[k] = aux[i++];
      }
      else if (aux[j] < aux[i]) {
        // Equivalent operation to checking that the 'first' (head) element of 'right sublist' is less than the
        // 'first' element in 'left sublist' in SimpleMergesort.java...
        array[k] = aux[j++];
      }
      else {
        // Equivalent operation to checking that the 'first' (head) element of 'left sublist' is less than or
        // equal to the 'first' element in 'right sublist' in SimpleMergesort.java...
        array[k] = aux[i++];
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
