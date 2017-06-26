/* Spring MVC : Request Handler(요청을 처리하는 메서드)의 파라미터들 I */
package control;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/control/controller12/")

public class Controller12 {

  @RequestMapping("ok1")
  public void ok1(String name,int age, boolean working, HttpServletResponse res) throws Exception {
    res.setContentType("text/plain;charset=UTF-8");
    PrintWriter out = res.getWriter();
    out.println("ok1()");
    
    out.printf("name: %s\n", name);
    out.printf("age: %d\n", age);
    out.printf("working: %b\n", working);
  }
}
