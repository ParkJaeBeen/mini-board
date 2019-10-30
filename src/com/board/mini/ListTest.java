package com.board.mini;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListTest 
{
	public List<Map<String,String>> Mymaplist(String s, String s2) 
	{	
		Map<String, String> map = new HashMap<String, String>();
		map.put(s,s2);	
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		list.add(map);  
		return list;
	}
}
