/* 회원 관리 만들기 : 회원 삭제하기 */
package bitcamp.java93.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java93.dao.LectDao;

@WebServlet(urlPatterns="/lect/delete")
public class LectDeleteServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    
    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>강의관리</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>강의 삭제</h1>");


    try {
      LectDao lectDao = (LectDao) this.getServletContext().getAttribute("lectDao");

      System.out.println(req.getParameter("no"));

      int no = Integer.parseInt(req.getParameter("no"));

      int count = lectDao.delete(no);
      if (count < 1) {
        throw new Exception(no + "번 회원을 찾지 못했습니다.");
      }
      out.println("<p>삭제 성공 입니다.</p>");
      
      res.setHeader("Refresh", "1;url=list");
      
    } catch (Exception e) {
      out.print("오류 발생!");
      out.println("<pre>");
      e.printStackTrace(out);
      out.println("</pre>");
      out.println("<a href='list'>목록</a>");
    }

    out.println("</body>");
    out.println("</html>");


  } // service()

}