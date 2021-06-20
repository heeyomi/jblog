package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {

	@Autowired
	private BlogService blogService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private FileUploadService fileUploadService;

	@Autowired
	private PostService postService;

	@Autowired
	private ServletContext application;

	@RequestMapping({"", "/{pathNo1}", "/{pathNo1}/{pathNo2}"})
	public String index(@PathVariable(value="id") String id,
			@PathVariable("pathNo1") Optional<Long> pathNo1,
			@PathVariable("pathNo2") Optional<Long> pathNo2, Model model) {
		Long categoryNo = 0L;
		Long postNo = 0L;

		List<PostVo> postList = null;
		PostVo postVo = null;
		
		if (pathNo2.isPresent()) {
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
			postVo = postService.getPostByNo(postNo);
			postList = postService.getPostByCategoryNo(id, categoryNo);
		} else if (pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
			postList = postService.getPostByCategoryNo(id, categoryNo);
			if (!postList.isEmpty()) {
				postVo = postList.get(0);
			}
		} else {
			postList = postService.getPost(id);
			if (!postList.isEmpty()) {
				postVo = postList.get(0);
			}
		}

		application.setAttribute("title", blogService.getBlog(id).getTitle());
		model.addAttribute("postList", postList);
		model.addAttribute("postVo", postVo);
		model.addAttribute("blogVo", blogService.getBlog(id));
		model.addAttribute("categoryVo", categoryService.findById(id));
		model.addAttribute("id", id);
		return "blog/main";
	}

	@Auth
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable(value="id") String id, Model model) {
		model.addAttribute("blogVo", blogService.getBlog(id));
		return "blog/admin/basic";
	}

	@Auth
	@RequestMapping(value="/admin/update", method = RequestMethod.POST)
	public String update(@PathVariable(value="id") String id, @RequestParam(value="logo") MultipartFile file, @RequestParam String title) {
		blogService.updateMain(id, fileUploadService.resotre(file), title);
		application.setAttribute("title", blogService.getBlog(id).getTitle());
		return "redirect:/"+id+"/admin/basic";
	}

	@Auth
	@RequestMapping(value="/admin/category", method = RequestMethod.GET)
	public String adminCategory(@PathVariable(value = "id") String id, Model model) {
		List<CategoryVo> vo = categoryService.findById(id);
		model.addAttribute("categoryVo", vo);
		return "blog/admin/category";
	}

	@Auth
	@RequestMapping(value="/admin/category", method = RequestMethod.POST)
	public String adminCategory(@PathVariable(value = "id") String id, Model model, CategoryVo categoryVo) {
		categoryService.insert(categoryVo.getName(), categoryVo.getDesc(), id);
		return "redirect:/"+id+"/admin"+"/category";
	}

	@Auth
	@RequestMapping(value="/admin/write", method = RequestMethod.GET)
	public String adminWrite(@PathVariable(value="id") String id, Model model) {
		model.addAttribute("categoryVo", categoryService.findById(id));
		return "blog/admin/write";
	}

	@Auth
	@RequestMapping(value="/admin/write", method = RequestMethod.POST)
	public String adminWrite(@PathVariable(value="id") String id, @ModelAttribute PostVo vo) {
		postService.write(vo);
		return "redirect:/"+id;
	}

	@Auth
	@RequestMapping("/admin/delete/{no}")
	public String categoryDelete(@PathVariable(value = "id") String id,
			@PathVariable(value="no") String no) {
		postService.deleteByCategoryNo(no);
		categoryService.delete(no);
		return "redirect:/"+id +"/admin/category";
	}
}
