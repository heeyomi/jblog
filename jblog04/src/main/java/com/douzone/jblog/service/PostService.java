package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.PostVo;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	public int write(PostVo vo) {
		return postRepository.write(vo);
	}

	public List<PostVo> getPost(String id) {
		return postRepository.getPost(id);
	}

	public List<PostVo> getPostByCategoryNo(String id, Long categoryNo) {
		return postRepository.getPostByCategoryNo(id, categoryNo);
	}

	public PostVo getPostByNo(Long postNo) {
		return postRepository.getPostByNo(postNo);
	}

	public int deleteByCategoryNo(String CategoryNo) {
		return postRepository.deleteByCategoryNo(CategoryNo);
		
	}

}
