package com.board.mini.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;

import com.board.mini.common.DBCon;
import com.board.mini.common.DBExecutor;

public class UserService 
{
	public Map<String,Object> doLogin(String id, String pwd)
	{
		Connection con = DBCon.getCon();
		DBExecutor dbe = new DBExecutor();
		String sql = "select * from user_table where ut_id=? and ut_pwd=?";
		try {
			PreparedStatement ps  = dbe.prepared(con,sql);
			ps.setString(1, id);
			ps.setString(2, pwd);
			ResultSet rs = dbe.executeQuery();
			if(rs.next())
			{
				Map<String,Object> user = new HashMap<>();
				user.put("utNum",rs.getInt("ut_num"));
				user.put("utName",rs.getString("ut_name"));
				user.put("utid",rs.getString("ut_id"));
				return user;
			}
		} catch (SQLException e) { 
			e.printStackTrace();
		}finally {
			dbe.closeAll();
			DBCon.closeCon();
		}
		return null;
	}
	
	public Map<String,Object> doSignup(String utName, String utId, String utPwd)
	{
		DBExecutor dbe = new DBExecutor();
		try {
			Connection con = DBCon.getCon();
			String sql = "insert into user_table(ut_num,ut_name,ut_id,ut_pwd,credat,cretim,moddat,modtim) ";
			sql += "values(seq_ut_num.nextval,?,?,?,to_char(sysdate,'YYYY-MM-DD'),to_char(sysdate,'HH24:MI:SS'),to_char(sysdate,'YYYY-MM-DD'),to_char(sysdate,'HH24:MI:SS'))";
			
			PreparedStatement ps = dbe.prepared(con, sql);
			ps.setString(1, utName);
			ps.setString(2, utId);
			ps.setString(3, utPwd);
			
			if(ps.executeUpdate() == 1)
			{
				Map<String,Object> rMap = new HashMap<String,Object>();
				rMap.put("msg", utName + "님 회원가입 완료");
				rMap.put("url","/views/user/login");
				return rMap;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbe.closeAll();
			DBCon.closeCon();
		}
		return null;
	}
}
