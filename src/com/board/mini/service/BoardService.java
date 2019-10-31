package com.board.mini.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.board.mini.common.DBCon;
import com.board.mini.common.DBExecutor;

public class BoardService 
{
	public List<Map<String,String>> getBoardList()
	{
		Connection con = DBCon.getCon();
		DBExecutor dbe = new DBExecutor();
		String sql = "select * from board_table order by bt_num desc";
		try {
			PreparedStatement ps = dbe.prepared(con, sql);
			ResultSet rs = dbe.executeQuery();
			List<Map<String,String>> btList = new ArrayList<>();
			while(rs.next())
			{
				Map<String,String> bt = new HashMap<>();
				bt.put("btNum",rs.getString("bt_num"));
				bt.put("btTitle",rs.getString("bt_title"));
				bt.put("btContent",rs.getString("bt_content"));
				bt.put("utNum",rs.getString("ut_num"));
				bt.put("credat",rs.getString("credat"));
				bt.put("cretim",rs.getString("cretim"));
				bt.put("moddat",rs.getString("moddat"));
				bt.put("modtim",rs.getString("modtim"));
				bt.put("active",rs.getString("active"));
				btList.add(bt);
			}
			return btList;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBCon.closeCon();
			dbe.closeAll();
		}
		
		return null;
	}
	
	public int setBoardList(Map<String,String> board)
	{
		Connection con = DBCon.getCon();
		DBExecutor dbe = new DBExecutor();
		
		String sql = "insert into board_table(bt_num,bt_title,bt_content,ut_num,credat,cretim,moddat,modtim) ";
		sql += "values(seq_bt_num.nextval,?,?,?,to_char(sysdate,'YYYY-MM-DD'),to_char(sysdate,'HH24:MI:SS'),to_char(sysdate,'YYYY-MM-DD'),to_char(sysdate,'HH24:MI:SS'))";
		
		try {
			PreparedStatement ps = dbe.prepared(con, sql);
			ps.setString(1,board.get("btTitle"));
			ps.setString(2,board.get("btContent"));
			ps.setString(3,board.get("utNum"));
			
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
