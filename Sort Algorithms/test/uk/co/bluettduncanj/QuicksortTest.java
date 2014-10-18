/**
 * QuicksortTest.java
 */

package uk.co.bluettduncanj;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.co.bluettduncanj.serial.Quicksort;


/**
 * @author Jonathan
 */
public class QuicksortTest {
  
  private Random r;
  private int[] array;
  public static final int arrSize = 3000000;
  public static final int range  = 10000;
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
   * Test method for {@link uk.co.bluettduncanj.serial.Quicksort#quicksort(int[], int, int)}.
   */
  @Test
  public void testQuicksortIntArrayIntInt() {
    System.out.println("Quicksort (Quicksort.java)");
    //System.out.println("Before sorting: " + print(array));
    start = System.currentTimeMillis();
    Quicksort.quicksort(array);
    end = System.currentTimeMillis();
    //System.out.println("After sorting:  " + print(array));
    System.out.println("Run time: " + (end - start) + " milliseconds.");
    
    System.out.println();
  }
  
  private static String print(int[] array) {
    StringBuilder printBuilder = new StringBuilder(array.length);
    for (int element : array) {
      printBuilder.append(element).append(" ");
    }
    return printBuilder.toString();
  }

}
