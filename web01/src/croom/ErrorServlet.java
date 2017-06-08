/* 에러 처리 서블릿 */

package croom;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/croom/error")
public class ErrorServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    
    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();
    
    
    
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>강의실 관리</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>오류 발생!</h1>");
    
    Exception e = (Exception) req.getAttribute("error");
    
    out.println("<a href='list'>목록</a>");
    
    if (e != null) {
    out.println("<pre>");
    e.printStackTrace(out);
    out.println("</pre>");
    }
    
    out.println("</body>");
    out.println("</html>");
    


    System.out.printf("RemoteAddress: %s\n", req.getRemoteAddr());

  } // service()

}