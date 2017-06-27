/* Spring MVC : Request Handler(요청을 처리하는 메서드)의 파라미터들 I */
package control;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/control/controller12/")

public class Controller12 {

  @RequestMapping("ok1")
  public void ok1(String name, int age, boolean working, HttpServletResponse res) throws Exception {
    res.setContentType("text/plain;charset=UTF-8");
    PrintWriter out = res.getWriter();
    out.println("ok1()");
    
    out.printf("name: %s\n", name);
    out.printf("age: %d\n", age);
    out.printf("working: %b\n", working);
  }
  
  @RequestMapping("ok2")
  public void ok2(
      @RequestParam(name="name")String n, 
      @RequestParam(name="age")int a, 
      @RequestParam(name="working")boolean w, HttpServletResponse res) throws Exception {
    res.setContentType("text/plain;charset=UTF-8");
    PrintWriter out = res.getWriter();
    out.println("ok2()");
    
    out.printf("name: %s\n", n);
    out.printf("age: %d\n", a);
    out.printf("working: %b\n", w);
  }
  
  @RequestMapping("ok3")
  public void ok3(
      @RequestParam(name="name", required=false)String n, 
      @RequestParam(name="age", required=false)int a, 
      @RequestParam(name="working", required=false)boolean w, HttpServletResponse res) throws Exception {
    res.setContentType("text/plain;charset=UTF-8");
    PrintWriter out = res.getWriter();
    out.println("ok3()");
    
    out.printf("name: %s\n", n);
    out.printf("age: %d\n", a);
    out.printf("working: %b\n", w);
  }
  
}
