/* 파일 업로드 : POST 요청 데이터 형식 - multipart/form-data
 * => multipart/form-data
 *    - 웹 브라우저에서 웹 서버에게 바이너리를 포함한 데이터를 보낼 때 사용하는 인코딩 방식이다.
 *    - 보내는 요청 방식은 POST이다.
 *    - 그러나 일반적인 형식인 "변수=값&변수=값&..." 이 아닌,
 *      바이너리 데이터를 보내기 적합한 형식으로 보낸다.
 *    - 형식:
 *      => 바이너리 데이터를 구분하기 위해 각 데이터를 특별한 구분자를 사용하여 분리한다.
 *         그리고 그 구분자 사이에 데이터를 넣는다.
 *      1) 웹서버에 데이터를 보낼 때 어떤 구분자를 사용할 지 요청 헤더에 알려준다.
 *         예) 
 *         Content-Type: multipart/form-data; boundary=----WebKitFormBoundary4lpFkjYXAQS2YzjE
 *      2) message-body 순서에 데이터를 보낼 때 위에서 정의한 구분자를 사용한다.
 *         예) 한 개의 데이터 파트:
           ------WebKitFormBoundary4lpFkjYXAQS2YzjE
           Content-Disposition: form-data; name="name"
           (빈 줄)
           홍길동
 * => application/x-www-form-urlencoded
 *    - POST 요청 방식으로 웹 서버에게 데이터를 보낼 때 사용하는 기본 인코딩 방식이다.
 *    - 예) name=ABC%EB%B0%80%EB%B0%81%EB%BO%84&age=20&tel=1111-1111
 *    - file 타입의 입력 값을 서버에 보내면, 단지 파일의 이름만 서버에 전달된다.
 *    - 즉 서버에 바이너리 데이터를 보내지 않는다.
 */
package step14;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/step14/Servlet02")
@SuppressWarnings("serial")
public class Servlet02 extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    
    // multipart 형식으로 받은 데이터는 getParameter()를 호출하여 값을 꺼낼 수 없다.
    // multipart 형식의 데이터를 별도로 처리해야 한다.
    // 그러나 안타깝게도 Servlet API에서는 멀티파트 데이터를 처리하는 기능이 없다.
    // 해결책? 
    // - 개발자가 직접 클라이언트가 보낸 데이터를 해석해서 처리하라!
    // - 우린 기존의 개발자가 만들어 놓은 도구를 사용하겠다!
    //   apache.org 사이트에서 제공하는 도구를 사용해보자!
    // 
    // 어찌되었든, 그 도구를 사용하여 요청 데이터를 처리하기 전까지는
    // getParameter()의 리턴 값은 null이다.
    String name = req.getParameter("name");
    String file1 = req.getParameter("file1");
    String file2 = req.getParameter("file2");
    
    resp.setContentType("text/plain;charset=UTF-8");
    PrintWriter out = resp.getWriter();
    out.printf("name=%s\n", name);
    out.printf("file1=%s\n", file1);
    out.printf("file2=%s\n", file2);
  }
  
}

/* HTTP POST 요청 프로토콜
POST /web01-bit/step14/Servlet02 HTTP/1.1
Host: localhost:8080
Content-Length: 13653
Pragma: no-cache
Cache-Control: no-cache
Origin: http://localhost:8080
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.86 Safari/537.36
Content-Type: multipart/form-data; boundary=----WebKitFormBoundary4lpFkjYXAQS2YzjE
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,...
Referer: http://localhost:8080/web01-bit/step14/form2.html
Accept-Encoding: gzip, deflate, br
Accept-Language: ko-KR,ko;q=0.8,en-US;q=0.6,en;q=0.4

------WebKitFormBoundary4lpFkjYXAQS2YzjE
Content-Disposition: form-data; name="name"

홍길동
------WebKitFormBoundary4lpFkjYXAQS2YzjE
Content-Disposition: form-data; name="file1"; filename="images.jpeg"
Content-Type: image/jpeg

���� JFIF      �� �  ( %!1!%)+...383-7(-....
...
------WebKitFormBoundary4lpFkjYXAQS2YzjE
Content-Disposition: form-data; name="file2"; filename="img01.jpeg"
Content-Type: image/jpeg

���� JFIF      �� �  ( %!1!%)+...383-7(-.+...
..
------WebKitFormBoundary4lpFkjYXAQS2YzjE--

 */










