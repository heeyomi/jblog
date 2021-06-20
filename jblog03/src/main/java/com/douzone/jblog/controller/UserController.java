package com.douzone.jblog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(UserVo vo) {
		return "user/login";
	}
	
	@RequestMapping(value="/join", method = RequestMethod.GET)
	private String join(@ModelAttribute UserVo userVo) {
		return "user/join";
	}

	@RequestMapping(value="/join", method = RequestMethod.POST)
	private String join(@ModelAttribute @Valid UserVo vo, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			return "user/join";
		}
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "/user/joinsuccess";
	}
	
	@RequestMapping("/logout")
	private String logout() {
		return "redirect:/";
	}
}
