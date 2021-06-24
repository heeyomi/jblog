package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public CategoryVo findByNameAndID(String name, String id) {
		return categoryRepository.findByName(name, id);
	}

	public int insert(String name, String desc, String id) {
		return categoryRepository.insert(name, desc, id);
	}

	public List<CategoryVo> findById(String id) {
		List<CategoryVo> vo = categoryRepository.findById(id);
		for (int i = 0; i < vo.size(); i++) {
			vo.get(i).setPostCnt(getCategoryCnt(id).get(i));
		}
		return vo;
	}

	public int delete(String id) {
		return categoryRepository.delete(id);
	}

	public List<Integer> getCategoryCnt(String id) {
		return categoryRepository.getCategoryCnt(id);
	}


}
