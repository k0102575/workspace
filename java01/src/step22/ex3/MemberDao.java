// memb 테이블의 데이터를 다루는 메서드를 모아둔 클래스이다.

// try를 쓰는이유는 자동으로 닫기위해 
package step22.ex3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {
  DBConnectionPool conPool;
  
  public MemberDao(DBConnectionPool conPool){
    this.conPool = conPool;
  } // 생성자
  
  public List<Member> selectList() throws Exception {
    Connection con = conPool.getConnection();
    // connection을 닫지 않기 위해 try문 안에 넣지 않는다.
    
    try (
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select mno, name, tel, email from memb");) {
      
      ArrayList<Member> list = new ArrayList<>();
      Member member = null;
      
      while (rs.next()) {
        member = new Member();
        member.setNo(rs.getInt("mno"));
        member.setName(rs.getString("name"));
        member.setTel(rs.getString("tel"));
        member.setEmail(rs.getString("email"));
        
        list.add(member);
      } // while
      
      return list;
    } finally {
      conPool.returnConnection(con);
    }
    
    
  } // selectList()
  
  public int insert(Member member) throws Exception {
    Connection con = conPool.getConnection();
    try(
        PreparedStatement stmt = con.prepareStatement(
            "insert into memb(name, tel, email, pwd) values(?, ?, ?, password(?))");
        ) {
        stmt.setString(1, member.getName());
        stmt.setString(2, member.getTel());
        stmt.setString(3, member.getEmail());
        stmt.setString(4, member.getPassword());
        
        return stmt.executeUpdate();
        
        } finally {
          conPool.returnConnection(con);
        }
    
  } // insert()
  
  public int delete(int no) throws Exception {
    Connection con = conPool.getConnection();
    try(
        PreparedStatement stmt = con.prepareStatement("delete from memb where mno= ?");
        ) {
      stmt.setInt(1, no);
      return stmt.executeUpdate();
      
    } finally {
      conPool.returnConnection(con);
    }
    
  } // delete()
  
  public int update(Member member) throws Exception {
    Connection con = conPool.getConnection();
    try(
        PreparedStatement stmt = con.prepareStatement(
            "update memb set name =?, email=?, tel=?, pwd=password(?) where mno=?");
        ) {
        stmt.setString(1, member.getName());
        stmt.setString(2, member.getEmail());
        stmt.setString(3, member.getTel());
        stmt.setString(4, member.getPassword());
        stmt.setInt(5, member.getNo());
        
        return stmt.executeUpdate();

        } finally {
          conPool.returnConnection(con);
        }
    
  } // insert()
  
}
