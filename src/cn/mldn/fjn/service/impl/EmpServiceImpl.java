package cn.mldn.fjn.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.mldn.fjn.vo.Emp;

/**
 * 模拟一个业务调用
 * @author lenovo
 *
 */
public class EmpServiceImpl {
	public Map<String,Object> list(String column,String keyWord,long currentPage,int lineSize){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("allRecorders", 99L);
		List<Emp> all=new ArrayList<Emp>();
		for(long i=(currentPage-1)*lineSize;i<currentPage*lineSize;i++) {
			Emp vo=new Emp();
			vo.setEmpno(i);
			vo.setEname("{"+keyWord+"} = {"+column+"}-"+i);
			vo.setAge(10);
			all.add(vo);
		}
		map.put("allEmps", all);
		return map;
	}
}
