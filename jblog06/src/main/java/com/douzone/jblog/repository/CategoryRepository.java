package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	@Autowired
	private SqlSession sqlSession;

	public CategoryVo findByName(String name, String id) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("id", id);
		return sqlSession.selectOne("category.findByNameAndId", map);
	}

	public int insert(String name, String desc, String id) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("desc", desc);
		map.put("id", id);
		return sqlSession.insert("category.insert", map);
	}

	public List<CategoryVo> findById(String id) {
		return sqlSession.selectList("category.findById", id);
	}

	public int delete(String id) {
		return sqlSession.delete("category.delete", id);
	}

	public List<Integer> getCategoryCnt(String id) {
		return sqlSession.selectList("category.countCategory", id);
	}
	
	
}
