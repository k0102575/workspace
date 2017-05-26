/* 유틸리티 클래스 : properties 클래스 응용 */
package step08;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class Test08_2 {

  public static void main(String[] args) throws FileNotFoundException, IOException {
    System.out.println("step08 Test08_2 실행");
    System.out.println();
    
    Properties props = System.getProperties();
    
    Set<Object> keySet = props.keySet();
    for (Object key : keySet) {
      System.out.printf("%s = %s\n", key, props.get(key));
    }
    
  }

}
