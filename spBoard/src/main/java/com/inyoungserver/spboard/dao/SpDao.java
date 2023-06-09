package com.inyoungserver.spboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.inyoungserver.spboard.dto.SpDto;

// db 접근 클래스
public class SpDao {
	DataSource dataSource;
	public SpDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/spbbs");
		}catch(NamingException e) {
			e.printStackTrace();
		}
		
	}
	
	// 글쓰기
	public void write(String uname, String upass, String title, String content) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String sql = "insert into spboard (uname, upass, title, content) values (?, ?, ?, ?)";
			ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,  uname);
			ps.setString(2,  upass);
			ps.setString(3,  title);
			ps.setString(4,  content);
			
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys(); // 쿼리 실행 후 생성된 키 값
			ps.clearParameters(); // ps 다시 사용을 위해 비움.
			
			if(rs.next()) {
				int num = rs.getInt(1);
				try {
					String query = "update spboard set s_group = ? where num = ?";
					ps = conn.prepareStatement(query);
					ps.setInt(1, num);
					ps.setInt(2, num);
					ps.executeUpdate();
					
				}catch(Exception ee) {
					ee.printStackTrace();
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
				if(ps != null) ps.close();
				if(rs != null) rs.close();
			}catch(Exception ex) {}
		}
	}
	
	// 답글 쓰기
	public SpDto reply(String cNum) {
		int iNum = Integer.parseInt(cNum);
		SpDto dto = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "select * from spboard where num = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1,  iNum);
			rs = ps.executeQuery();
			
			// dto에 담기
			if(rs.next()) {
				dto = new SpDto();
				int num = rs.getInt("num");
				int s_group = rs.getInt("s_group");
				int s_step = rs.getInt("s_step");
				int s_indent = rs.getInt("s_indent");
				
				dto.setNum(num);
				dto.setS_group(s_group);
				dto.setS_step(s_step);
				dto.setS_indent(s_indent);				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
				if(ps != null) ps.close();
				if(rs != null) rs.close();
			}catch(Exception ee) {
				
			}
		}

		return dto;
	}
	
	// 답글 등록하기
	public void replyok(int s_group, int s_step, int s_indent, String uname, String upass, String title, String content) {
		replyUpdate(s_group, s_step);
		Connection conn = null;
		PreparedStatement ps = null;
		
		String sql = "insert into spboard (s_group, s_step, s_indent, uname, upass, title, content)"
				+ " values (?, ?, ?, ?, ?, ?, ?)";
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, s_group);
			ps.setInt(2, s_step);
			ps.setInt(3, s_indent);
			ps.setString(4, uname);
			ps.setString(5, upass);
			ps.setString(6, title);
			ps.setString(7, content);
			ps.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
				if(ps != null) ps.close();
			}catch(Exception ee) {}
		}
		
		
	}
	
	private void replyUpdate(int s_group, int s_step) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		String sql = "update spboard set s_step = s_step + 1 where s_group = ? and s_step = ?";
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, s_group);
			ps.setInt(2, s_step);
			ps.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
				if(ps != null) ps.close();
			}catch(Exception ee) {}
		}
	}
	public SpDto update(String cNum) {
	      int iNum = Integer.parseInt(cNum);
	      SpDto dto = null;
	       Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         conn = dataSource.getConnection();
	         String sql = "select * from spboard where num = ?";
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, iNum);
	         rs = pstmt.executeQuery();
	         
	         //dto에 담기
	         if(rs.next()) {
	            dto = new SpDto();
	            int num = rs.getInt("num");
	            String uname = rs.getString("uname");
	            String upass = rs.getString("upass");
	            String title = rs.getString("title");
	            String content = rs.getString("content");
	                
	            dto.setNum(num);
	            dto.setUname(uname);
	            dto.setUpass(upass);
	            dto.setTitle(title);
	            dto.setContent(content);
	         }         
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {
	         try {
	            if(rs != null) rs.close();
	            if(pstmt != null) pstmt.close();
	            if(conn != null) conn.close();
	         }catch(Exception ee) {
	            
	         }
	      }
	      
	      return dto;
	   }
	public void updateok(String num, String uname, String upass, String title, String content) {
		int inum = Integer.parseInt(num);
		Connection conn = null;
		PreparedStatement ps = null;
		
		String sql = "update spboard set uname = ?, upass = ?, title = ?, content = ? where num = ?";
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1,  uname);
			ps.setString(2, upass);
			ps.setString(3,  title);
			ps.setString(4,  content);
			ps.setInt(5,  inum);
			ps.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
				if(ps != null) ps.close();
			}catch(Exception ee) {}
		}
		
		
	}
	
	// 본문 보기
	public SpDto detail(String cNum) {
		int iNum = Integer.parseInt(cNum);
		hitAdd(iNum);
		SpDto dto = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "select * from spboard where num = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1,  iNum);
			rs = ps.executeQuery();
			if(rs.next()) {
				dto = new SpDto();
				dto.setNum(rs.getInt("num"));
				dto.setS_group(rs.getInt("s_group"));
				dto.setS_step(rs.getInt("s_step"));
				dto.setS_indent(rs.getInt("s_indent"));
				dto.setUname(rs.getString("uname"));
				dto.setUpass(rs.getString("upass"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setCt(rs.getInt("ct"));
				dto.setHit(rs.getInt("hit"));
				dto.setWdate(rs.getTimestamp("wdate"));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
				if(ps != null) ps.close();
				if(rs != null) rs.close();
			}catch(Exception ex) {}
		}
		return dto;
	}
	
	public void delete(String num) {
		int iNum = Integer.parseInt(num);
		Connection conn = null;
		PreparedStatement ps = null;
		
		String sql = "delete from spboard where num = ?";
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, iNum);
			ps.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
				if(ps != null) ps.close();
				
			}catch(Exception ex) {}
		}
	}
	
	private void hitAdd(int num) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "update spboard set hit = hit + 1 where num = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1,  num);
			int r = ps.executeUpdate();
			System.out.println("hit 업데이트 : " + r);
		}catch(Exception ee) {
			ee.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
				if(ps != null) ps.close();
				
			}catch(Exception ex) {}
		}
	}
	
	
	// 데이터를 받아서 SpDto에 담음
	public ArrayList <SpDto> list(){
		ArrayList <SpDto> dtos =  new ArrayList <SpDto> ();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "select * from spboard order by s_group desc, s_step asc";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				SpDto dto = new SpDto();
				dto.setNum(rs.getInt("num"));
				dto.setS_group(rs.getInt("s_group"));
				dto.setS_step(rs.getInt("s_step"));
				dto.setS_indent(rs.getInt("s_indent"));
				dto.setUname(rs.getString("uname"));
				dto.setUpass(rs.getString("upass"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setCt(rs.getInt("ct"));
				dto.setHit(rs.getInt("hit"));
				dto.setWdate(rs.getTimestamp("wdate"));
				dtos.add(dto);
			}
		}catch(Exception ee) {
			ee.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
				if(ps != null) ps.close();
				if(rs != null) rs.close();
			}catch(Exception ex) {}
		}
		return dtos;
	}
		

}
