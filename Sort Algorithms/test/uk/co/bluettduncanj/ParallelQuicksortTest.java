/**
 * ParallelQuicksortTest.java
 */

package uk.co.bluettduncanj;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.co.bluettduncanj.parallel.ParallelQuicksort;


/**
 * @author Jonathan
 */
public class ParallelQuicksortTest {
  
  private Random r = new Random();
  public static final int arrSize  = 10000000  /* 30 */;
  public static final int intRange =    20000  /* 10 */;
  private int[] intArr;
  private long[] longArr;
  private short[] shortArr;
  private byte[] byteArr;
  private char[] charArr;
  private double[] doubleArr;
  private float[] floatArr;
  private String[] strArr;
  private BigInteger[] bIntArr;
  private BigDecimal[] bDecArr;
  private DummyObject[] dummyArr;
  private long start, end;

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    start = 0; end = 0;
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
    start = 0; end = 0;
  }

  /**
   * Test method for {@link uk.co.bluettduncanj.parallel.QuicksortAction#quicksort(int[])}.
   * @throws Exception 
   */
  @Test
  public void testQuicksortIntArray() {
    
    // Run test
    System.out.println("Parallel Quicksort @ (" + ParallelQuicksort.class.getSimpleName() + ".java)...");
    
    // Test int
    System.out.println("\nSetting up int sort data...");
    intArr = new int[arrSize];
    for (int i = 0; i < arrSize; i++) {
      intArr[i] = r.nextInt(intRange) + 1;
    }
    System.out.println("Finished setup.");
    System.out.println("Start int...");
    //System.out.println("Before sorting: " + show(array));
    start = System.currentTimeMillis();
    ParallelQuicksort.sort(intArr);
    end = System.currentTimeMillis();
    //System.out.println("After sorting:  " + show(array));
    System.out.println("Finished.");
    System.out.println("Run time: " + (end - start) + " milliseconds.");
    boolean isSorted = isSorted(intArr);
    System.out.println("Sorted: " + isSorted + ".");
    assertTrue("int not sorted!", isSorted);
    intArr = null;
    gc();
    
    /*
    // Test long
    System.out.println("\nSetting up long sort data...");
    longArr = new long[arrSize];
    for (int i = 0; i < arrSize; i++) {
      longArr[i] = r.nextInt(range) + 1;
    }
    System.out.println("Start long...");
    //System.out.println("Before sorting: " + show(array));
    start = System.currentTimeMillis();
    ParallelQuicksort.sort(longArr);
    end = System.currentTimeMillis();
    //System.out.println("After sorting:  " + show(array));
    System.out.println("Finished.");
    System.out.println("Run time: " + (end - start) + " milliseconds.");
    isSorted = isSorted(longArr);
    System.out.println("Sorted: " + isSorted + ".");
    assertTrue("long not sorted!", isSorted);
    longArr = null;
    gc();
    
    // Test short
    System.out.println("\nSetting up short sort data...");
    shortArr = new short[arrSize];
    for (int i = 0; i < arrSize; i++) {
      shortArr[i] = (short) (r.nextInt(range) + 1);
    }
    System.out.println("Start short...");
    //System.out.println("Before sorting: " + show(array));
    start = System.currentTimeMillis();
    ParallelQuicksort.sort(shortArr);
    end = System.currentTimeMillis();
    //System.out.println("After sorting:  " + show(array));
    System.out.println("Finished.");
    System.out.println("Run time: " + (end - start) + " milliseconds.");
    isSorted = isSorted(shortArr);
    System.out.println("Sorted: " + isSorted + ".");
    assertTrue("short not sorted!", isSorted);
    shortArr = null;
    gc();
    
    // Test byte
    System.out.println("\nSetting up byte sort data...");
    byteArr = new byte[arrSize];
    for (int i = 0; i < arrSize; i++) {
      byteArr[i] = (byte) (r.nextInt(range) + 1);
    }
    System.out.println("Start byte...");
    //System.out.println("Before sorting: " + show(array));
    start = System.currentTimeMillis();
    ParallelQuicksort.sort(byteArr);
    end = System.currentTimeMillis();
    //System.out.println("After sorting:  " + show(array));
    System.out.println("Finished.");
    System.out.println("Run time: " + (end - start) + " milliseconds.");
    isSorted = isSorted(byteArr);
    System.out.println("Sorted: " + isSorted + ".");
    assertTrue("byte not sorted!", isSorted);
    shortArr = null;
    gc();
    
    // Test char
    System.out.println("\nSetting up char sort data...");
    charArr = new char[arrSize];
    for (int i = 0; i < arrSize; i++) {
      charArr[i] = (char) (r.nextInt(range) + 1);
    }
    System.out.println("Start char...");
    //System.out.println("Before sorting: " + show(array));
    start = System.currentTimeMillis();
    ParallelQuicksort.sort(charArr);
    end = System.currentTimeMillis();
    //System.out.println("After sorting:  " + show(array));
    System.out.println("Finished.");
    System.out.println("Run time: " + (end - start) + " milliseconds.");
    isSorted = isSorted(charArr);
    System.out.println("Sorted: " + isSorted + ".");
    assertTrue("char not sorted!", isSorted);
    charArr = null;
    gc();
    
    // Test double
    System.out.println("\nSetting up double sort data...");
    doubleArr = new double[arrSize];
    for (int i = 0; i < arrSize; i++) {
      doubleArr[i] = Math.cos(r.nextInt(range) + 1);
    }
    System.out.println("Start double...");
    //System.out.println("Before sorting: " + show(array));
    start = System.currentTimeMillis();
    ParallelQuicksort.sort(doubleArr);
    end = System.currentTimeMillis();
    //System.out.println("After sorting:  " + show(array));
    System.out.println("Finished.");
    System.out.println("Run time: " + (end - start) + " milliseconds.");
    isSorted = isSorted(doubleArr);
    System.out.println("Sorted: " + isSorted + ".");
    assertTrue("double not sorted!", isSorted);
    doubleArr = null;
    gc();
    
    // Test float
    System.out.println("\nSetting up float sort data...");
    floatArr = new float[arrSize];
    for (int i = 0; i < arrSize; i++) {
      floatArr[i] = Math.nextUp((r.nextInt(range) + 1) * 0.8f);
    }
    System.out.println("Start float...");
    //System.out.println("Before sorting: " + show(array));
    start = System.currentTimeMillis();
    ParallelQuicksort.sort(floatArr);
    end = System.currentTimeMillis();
    //System.out.println("After sorting:  " + show(array));
    System.out.println("Finished.");
    System.out.println("Run time: " + (end - start) + " milliseconds.");
    isSorted = isSorted(floatArr);
    System.out.println("Sorted: " + isSorted + ".");
    assertTrue("float not sorted!", isSorted);
    floatArr = null;
    gc();
    
    // Test String
    System.out.println("\nSetting up String sort data...");
    strArr = new String[arrSize];
    for (int i = 0; i < arrSize; i++) {
      int stringSize = 3;
      StringBuilder sb = new StringBuilder(stringSize);
      for (int j = 0; j < stringSize; j++) {
        sb.append((char) (r.nextInt(range) + 1));
      }
      strArr[i] = sb.toString();
      sb = null;
    }
    gc();
    System.out.println("Start String...");
    //System.out.println("Before sorting: " + show(array));
    start = System.currentTimeMillis();
    ParallelQuicksort.sort(strArr);
    end = System.currentTimeMillis();
    //System.out.println("After sorting:  " + show(array));
    System.out.println("Finished.");
    System.out.println("Run time: " + (end - start) + " milliseconds.");
    isSorted = isSorted(strArr);
    System.out.println("Sorted: " + isSorted + ".");
    assertTrue("String not sorted!", isSorted);
    strArr = null;
    gc();
    
    // Test DummyObject
    // Test String
    System.out.println("\nSetting up DummyObject sort data...");
    dummyArr = new DummyObject[arrSize];
    for (int i = 0; i < arrSize; i++) {
      dummyArr[i] = new DummyObject(r.nextInt(range)+1, r.nextInt(range)+1);
    }
    System.out.println("Start DummyObject...");
    //System.out.println("Before sorting: " + show(array));
    start = System.currentTimeMillis();
    ParallelQuicksort.sort(dummyArr, new DummyComparator());
    end = System.currentTimeMillis();
    //System.out.println("After sorting:  " + show(array));
    System.out.println("Finished.");
    System.out.println("Run time: " + (end - start) + " milliseconds.");
    isSorted = isSorted(dummyArr);
    System.out.println("Sorted: " + isSorted + ".");
    assertTrue("DummyObject not sorted!", isSorted);
    dummyArr = null;
    gc();
    */
    
    System.out.println("\nSetting up int sort data...");
    intArr = new int[arrSize];
    for (int i = 0; i < arrSize; i++) {
      intArr[i] = r.nextInt(intRange) + 1;
    }
    System.out.println("Starting Java 7 Arrays.sort() (for int array)...");
    //System.out.println("Before sorting: " + show(array));
    start = System.currentTimeMillis();
    Arrays.sort(intArr);
    Collections.sort(new ArrayList<Integer>());
    end = System.currentTimeMillis();
    //System.out.println("After sorting:  " + show(array));
    System.out.println("Finished.");
    System.out.println("Run time: " + (end - start) + " milliseconds.");
    System.out.println("Sorted: " + isSorted(intArr) + ".");
  }
  
  private static String show(int[] array) {
    StringBuilder printBuilder = new StringBuilder(array.length);
    for (int element : array) {
      printBuilder.append(element).append(" ");
    }
    return printBuilder.toString();
  }

  private static boolean isSorted(int[] array) {
    for (int i = 1; i < array.length; i++)
      if (array[i-1] > array[i])
        return false;
    return true;
  }
  private static boolean isSorted(long[] array) {
    for (int i = 1; i < array.length; i++)
      if (array[i-1] > array[i])
        return false;
    return true;
  }
  private static boolean isSorted(short[] array) {
    for (int i = 1; i < array.length; i++)
      if (array[i-1] > array[i])
        return false;
    return true;
  }
  private static boolean isSorted(byte[] array) {
    for (int i = 1; i < array.length; i++)
      if (array[i-1] > array[i])
        return false;
    return true;
  }
  private static boolean isSorted(char[] array) {
    for (int i = 1; i < array.length; i++)
      if (array[i-1] > array[i])
        return false;
    return true;
  }
  private static boolean isSorted(double[] array) {
    for (int i = 1; i < array.length; i++)
      if (array[i-1] > array[i])
        return false;
    return true;
  }
  private static boolean isSorted(float[] array) {
    for (int i = 1; i < array.length; i++)
      if (array[i-1] > array[i])
        return false;
    return true;
  }
  private static boolean isSorted(String[] array) {
    for (int i = 1; i < array.length; i++)
      if (array[i-1].compareTo(array[i]) > 0)
        return false;
    return true;
  }
  private static boolean isSorted(DummyObject[] array) {
    DummyComparator c = new DummyComparator();
    for (int i = 1; i < array.length; i++)
      if (/* array[i-1] > array[i] */ c.compare(array[i-1], array[i]) > 0)
        return false;
    return true;
  }
  
  private void gc() {
    System.gc();
  }

}
