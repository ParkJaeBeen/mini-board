package com.board.mini;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListTest 
{
	public void Mymaplist() 
	{	
		Map<String, String> map = new HashMap<String, String>();
		map.put("a","1");
		map.put("b","2");
		map.put("c","3");
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		list.add(map);   
		System.out.println(list);
	}
}
