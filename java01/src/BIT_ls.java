import java.io.File;

public class BIT_ls {
  
  public static void displayDirectory(File dir, int level){
    File[] files = dir.listFiles();
    
    for (File file : files){
      for (int i = 0; i < level; i++)  System.out.print("  ");
      System.out.printf("%s %s %d\n",
          (file.isDirectory() ? "d" : "-"),
          file.getName(),
          (file.length()));
      if (file.isDirectory()){
        displayDirectory(file, level +1);
      }
  }
}
  
  public static void main(String[] args) {
    
    if(args.length == 0) {
    File f = new File("./");
    
    displayDirectory(f, 0);
    } else {
      System.out.println("[사용법]: java -cp -bin BIT_ls");
    }
  }

}
