/* 로그인 여부를 검사하는 필터 */

package bitcamp.java93.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java93.domain.Member;

@WebFilter("/member/*")
public class AuthCheckFilter implements Filter{

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    
    String sessionId = httpRequest.getParameter("sessionId");

    if (sessionId == null) {
      httpResponse.sendRedirect("../auth/login.html");
      return;
    }
    
    Member loginmember = (Member) request.getServletContext().getAttribute("id_" + sessionId);
    
    if (loginmember == null) {
      httpResponse.sendRedirect("../auth/login.html");
      return;
    }
    
    chain.doFilter(httpRequest, httpResponse);
  }

  @Override
  public void destroy() {
    
  }

}
