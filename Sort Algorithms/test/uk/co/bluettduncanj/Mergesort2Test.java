/**
 * Mergesort2Test.java
 */

package uk.co.bluettduncanj;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.co.bluettduncanj.serial.MergeSort2;


/**
 * @author Jonathan
 */
public class Mergesort2Test {
  
  private Random r;
  private int[] array;
  public static final int arrSize = 3000000 /*30*/;
  public static final int range  = /*10000*/ /*10*/ 0;
  private long start, end;

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    r = new Random();
    start = 0; end = 0;
    array = new int[arrSize];
    for (int i = 0; i < arrSize; i++) {
      array[i] = r.nextInt(range+1);
    }
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
    start = 0; end = 0;
    array = null;
    r = null;
  }

  /**
   * Test method for {@link uk.co.bluettduncanj.serial.MergeSort2#sort(int[])}.
   */
  @Test
  public void testSort() {
    System.out.println("Mergesort (Mergesort2.java)");
    //System.out.println("Before sorting: " + print(array));
    start = System.currentTimeMillis();
    MergeSort2.sort(array);
    end = System.currentTimeMillis();
    //System.out.println("After sorting:  " + print(array));
    System.out.println("Run time: " + (end - start) + " milliseconds.");
    System.out.println("Sorted: " + isSorted(array) + ".");
    
    System.out.println();
  }
  
  private static String print(int[] array) {
    StringBuilder printBuilder = new StringBuilder(array.length);
    for (int element : array) {
      printBuilder.append(element).append(" ");
    }
    return printBuilder.toString();
  }

  private static boolean isSorted(int[] array) {
    for (int i = 1; i < array.length; i++) {
      if (array[i] < (array[i-1])) {
        return false;
      }
    }
    return true;
  }

}
