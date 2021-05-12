package com.yzd.elastic.controller;

import com.yzd.elastic.dataobject.ESNewDO;
import com.yzd.elastic.service.SelectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("thymeleaf")
public class ThymeleafController {
	@Autowired
	private SelectService selectService;

	@RequestMapping("hello")
	public String hello(Model model) {
		model.addAttribute("msg", "Hello Thymeleaf");
		return "hello";
	}

	@GetMapping("/find")
	@ApiOperation(value = "根据标题查询信息",notes = "作为测试只返回一条数据")
	public String findByTitle(@RequestParam String title,Model model) {
		ESNewDO newDO = selectService.findByTitle(title);
		model.addAttribute("newDO",newDO);
		return "hello";
	}
}