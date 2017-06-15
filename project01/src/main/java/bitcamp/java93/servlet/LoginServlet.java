package bitcamp.java93.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bitcamp.java93.domain.Member;
import bitcamp.java93.service.TeacherService;

@WebServlet(urlPatterns="/auth/login")
public class LoginServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    RequestDispatcher rd = req.getRequestDispatcher("/auth/form.jsp");
    rd.forward(req, res);
    
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String userType = req.getParameter("userType");
    String email = req.getParameter("email");
    String password = req.getParameter("password");

    try {
      Member member = null;
//      if(userType.equals("student")) {
////        StudentService studentService = this.getServletContext().getAttribute("studentService");
//        
//      } else 
      
        if (userType.equals("teacher")) {
        TeacherService teacherService = (TeacherService) this.getServletContext().getAttribute("teacherService");
        member = teacherService.getByEmailPassword(email, password);
      }
      
//      MemberService memberService = (MemberService)this.getServletContext().getAttribute("memberService");      
//      member = memberService.getByEmailPassword(email, password);

      if (member != null) {
        
        HttpSession session = req.getSession();
        
        session.setAttribute("loginMember", member);
        
        String saveEmail = req.getParameter("saveEmail");
        if (saveEmail != null) {
          Cookie cookie2 = new Cookie("email", email);
          cookie2.setMaxAge(60 * 60 * 24 * 7);
          res.addCookie(cookie2);
        } else {
          Cookie cookie2 = new Cookie("email", "");
          cookie2.setMaxAge(0);
          res.addCookie(cookie2);
        }

        res.sendRedirect("../teacher/list");

      } else {

        res.setContentType("text/html;charset=UTF-8");
        RequestDispatcher rd = req.getRequestDispatcher("/auth/fail.jsp");
        rd.forward(req, res);
        
      }

    } catch (Exception e) {
      req.setAttribute("error", e);
      RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
      rd.forward(req, res);
      return;
    }

  } // service()

}
