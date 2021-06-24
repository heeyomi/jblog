package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.repository.UserRepository;
import com.douzone.jblog.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	public void join(UserVo vo) {
		userRepository.insert(vo);
		blogRepository.insert(vo.getId());
		categoryRepository.insert("기본카테고리", "기본 카테고리입니다.", vo.getId());
	}

	public UserVo getUser(String id) {
		return userRepository.findById(id);
	}
	
	public UserVo getUser(String id, String password) {
		return userRepository.findbyIdAndPassword(id, password);
	}

	public void login(UserVo vo) {
		
	}
	
}
