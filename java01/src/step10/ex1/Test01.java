/* 슈퍼 클래스의 메서드를 재정의 하는 방법 */

package step10.ex1;

public class Test01 {

  public static void main(String[] args) {
    System.out.println("step10 ex1 Test01 실행");
    System.out.println();
    
    Student s = new Student();
    
    s.name = "홍길동";
    s.age = 20;
    s.print();
  }

}