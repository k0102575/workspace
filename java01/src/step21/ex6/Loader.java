package step21.ex6;

public abstract class Loader extends Car {

  Object storage;

  public Loader() {
    super();
  }

  public Loader(String model, String maker, int cc) {
    super(model, maker, cc);
  }

  public void load(Object obj) {
    this.storage = obj;
    System.out.printf("%s 를 실었습니다.\n", obj);
  }
  
}