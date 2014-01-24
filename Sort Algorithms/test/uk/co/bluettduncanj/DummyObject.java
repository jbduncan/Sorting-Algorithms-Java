/**
 * DummyObject.java
 */

package uk.co.bluettduncanj;


/**
 * @author Jonathan
 */
public class DummyObject {
  
  private int result;
  
  public DummyObject(int a, int b) {
    this.result = a - b;
  }
  
  public int dummyCalc() {
    return result;
  }
  
}
