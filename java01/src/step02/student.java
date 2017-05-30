package step02;

class Student {
    String name;
    int[] scores;
    int total;
    float aver;
  
  Student(String name, int kor, int eng, int math) {
    this.name = name;
    this.scores = new int[]{kor, eng, math};
    this.total = kor + eng + math;
    this.aver = this.total /3f;
  } // Student 생성자
  
  
  // Studnet의 인스턴스 값을 출력하는 함수인데,
  // 굳이 printStudent라고 이름을 지을 필요는 없다.
  // 이름에서 Student를 빼더라도 이클래스가 Student를 다루는지 알수있다.
  public static void print(Student s){
    System.out.printf("%s, %d, %d, %d, %d, %.1f\n",
        s.name,
        s.scores[0], s.scores[1], s.scores[2],
        s.total, s.aver); 
  } // print
  
} // Student class
