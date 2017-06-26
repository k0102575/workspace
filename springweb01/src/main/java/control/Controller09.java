/* Spring MVC : 요청 프로토콜에 Accept 속성의 값에 따라 호출될 메서드 결정 */
package control;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/control/controller09/")

public class Controller09 {

  @RequestMapping(path="ok", produces="text/plain")

  public void get(HttpServletRequest req, HttpServletResponse res) throws Exception {
    res.setContentType("text/plain;charset=UTF-8");
    PrintWriter out = res.getWriter();

    out.println("text/plain 보냄!");
  }

  @RequestMapping(path="ok", produces="application/json")

  public void porst(HttpServletRequest req, HttpServletResponse res) throws Exception {
    res.setContentType("text/plain;charset=UTF-8");
    PrintWriter out = res.getWriter();

    out.println("application/json 보냄!");
  }

}
