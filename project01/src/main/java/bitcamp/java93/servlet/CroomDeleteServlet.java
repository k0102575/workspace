/* 회원 관리 만들기 : 회원 삭제하기 */
package bitcamp.java93.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java93.dao.CroomDao;

@WebServlet(urlPatterns="/croom/delete")
public class CroomDeleteServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    
    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>교실관리</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>교실 삭제</h1>");


    try {
      CroomDao croomDao = (CroomDao) this.getServletContext().getAttribute("croomDao");

      int no = Integer.parseInt(req.getParameter("no"));

      int count = croomDao.delete(no);
      if (count < 1) {
        throw new Exception(no + "번 회원을 찾지 못했습니다.");
      }
      out.println("<p>삭제 성공 입니다.</p>");
      
      res.addHeader("Refresh", "1;url=list");
      
    } catch (Exception e) {
      req.setAttribute("error", e);
      RequestDispatcher  rd = req.getRequestDispatcher("/croom/error");
      rd.forward(req, res);
      
      return;
    }

    out.println("</body>");
    out.println("</html>");

    System.out.printf("RemoteAddress: %s\n", req.getRemoteAddr());

  } // service()

}