/**
 * InsertionSort2Test.java
 */

package uk.co.bluettduncanj;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.co.bluettduncanj.serial.InsertionSort2;


/**
 * @author Jonathan
 */
public class InsertionSort2Test {
  
  private Random r = new Random();
  private int[] array;
  public static final int arrSize = /* 10000000 */  30;
  public static final int range =   /* 10000 */     10;
  private long start, end;

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    start = 0; end = 0;
    array = new int[arrSize];
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
    array = null;
    start = 0; end = 0;
  }

  /**
   * Test method for {@link uk.co.bluettduncanj.serial.InsertionSort2#sort(int[], int, int)}.
   */
  @Test
  public void testSort() {
    arraySetUp();
    
    // Run test
    System.out.println("\nStarting (" + InsertionSort2.class.getSimpleName() + ".java)...");
    //System.out.println("Before sorting: " + show(array));
    start = System.currentTimeMillis();
    InsertionSort2.sort(array, 0, array.length-1);
    end = System.currentTimeMillis();
    //System.out.println("After sorting:  " + show(array));
    System.out.println("Finished.");
    System.out.println("Run time: " + (end - start) + " milliseconds.");
    boolean isSorted = isSorted(array);
    System.out.println("Sorted: " + isSorted + ".");
    assertTrue("Not sorted!", isSorted);
  }
  
  private void arraySetUp() {
    System.out.println("\nSetting up sort data...");
    for (int i = 0; i < arrSize; i++) {
      array[i] = r.nextInt(range + 1);
    }
    System.out.println("Finished setup.");
  }
  
  private static String show(int[] array) {
    StringBuilder printBuilder = new StringBuilder(array.length);
    for (int element : array) {
      printBuilder.append(element).append(" ");
    }
    return printBuilder.toString();
  }
  
  private static boolean isSorted(int[] array) {
    boolean sorted = true;
    for (int i = 1; i < array.length; i++) {
      if (array[i-1] > array[i]) {
        sorted = false;
        break;
      }
    }
    return sorted;
  }

}
