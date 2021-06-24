package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.PostVo;

@Repository
public class PostRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public int write(PostVo vo) {
		return sqlSession.insert("post.insert", vo);
	}

	public List<PostVo> getPost(String id) {
		return sqlSession.selectList("post.findAll", id);
	}

	public List<PostVo> getPostByCategoryNo(String id, Long categoryNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("cno", categoryNo);
		return sqlSession.selectList("post.findByCategoryNo", map);
	}

	public PostVo getPostByNo(Long postNo) {
		return sqlSession.selectOne("post.findByNo", postNo);
	}

	public int deleteByCategoryNo(String CategoryNo) {
		return sqlSession.delete("post.deleteByCategoryNo",	CategoryNo);
	}

}
