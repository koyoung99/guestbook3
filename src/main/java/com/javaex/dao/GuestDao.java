package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestVo;

public class GuestDao {

	public List<GuestVo> guestSelect() {

		List<GuestVo> guestList = new ArrayList<GuestVo>();

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/guestbook_db";
			conn = DriverManager.getConnection(url, "guestbook", "guestbook");

			// 3. SQL문 준비 / 바인딩 / 실행

			String query = "";
			query += " select no, name, password, content, reg_date ";
			query += " from guestbook";

			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String pw = rs.getString("password");
				String content = rs.getString("content");
				String regDate = rs.getString("reg_date");

				GuestVo guestVo = new GuestVo(no, name, pw, content, regDate);

				guestList.add(guestVo);

			}
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return guestList;

	} // getSelect()

	public int guestInsert(GuestVo guestVo) {

		int count = -1;
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/guestbook_db";
			conn = DriverManager.getConnection(url, "guestbook", "guestbook");

			String query = "";
			query += " insert into guestbook ";
			query += " values(null, ?, ?, ?,now()) ";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, guestVo.getName());
			pstmt.setString(2, guestVo.getPassword());
			pstmt.setString(3, guestVo.getContent());

			count = pstmt.executeUpdate();

			System.out.println(count + "건 등록되었습니다.");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}

		return count;
	}// guestInsert()

	public int guestDelete(int no, String password) {
		int count = -1;
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/guestbook_db";
			conn = DriverManager.getConnection(url, "guestbook", "guestbook");

			// 3. SQL문 준비 / 바인딩 / 실행

			// SQL문 준비
			String query = "";
			query += " delete from guestbook ";
			query += " where no=? and password=?";

			// 바인딩
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, no);
			pstmt.setString(2, password);

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 삭제되었습니다.");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return count;
	}

}
