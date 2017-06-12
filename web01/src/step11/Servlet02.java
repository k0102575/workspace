/* HttpServlet 추상 클래스를 상속받아 서블릿 만들기 II */

package step11;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/step11/Servlet02")
public class Servlet02 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    res.setContentType("text/plain;charset=UTF-8");
    PrintWriter out = res.getWriter();

    out.println("안녕하세요!");
    out.println(req.getMethod());

  }
}