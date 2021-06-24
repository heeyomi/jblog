package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;

@Repository
public class BlogRepository {

	@Autowired
	private SqlSession sqlSession;

	public BlogVo getBlog(String id) {
		return sqlSession.selectOne("blog.findById", id);
	}

	public int updateMain(BlogVo vo) {
		return sqlSession.update("blog.update", vo);
	}

	public int insert(String id) {
		return sqlSession.insert("blog.insert", id);
	}

	public int updateMain(String id, String file, String title) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("file", file);
		map.put("title", title);
		return sqlSession.update("blog.update", map);
	}
	
}
