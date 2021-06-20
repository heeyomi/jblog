package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogRepository blogRepository;

	public BlogVo getBlog(String id) {
		return blogRepository.getBlog(id);
	}

	public int updateMain(String id, String file, String title) {
		return blogRepository.updateMain(id, file, title);
	}

}
