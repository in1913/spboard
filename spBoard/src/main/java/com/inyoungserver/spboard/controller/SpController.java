package com.inyoungserver.spboard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.inyoungserver.spboard.command.SpCommand;
import com.inyoungserver.spboard.command.SpDeleteCommand;
import com.inyoungserver.spboard.command.SpDetailCommand;
import com.inyoungserver.spboard.command.SpListCommand;
import com.inyoungserver.spboard.command.SpReplyCommand;
import com.inyoungserver.spboard.command.SpReplyokCommand;
import com.inyoungserver.spboard.command.SpUpdateCommand;
import com.inyoungserver.spboard.command.SpUpdateokCommand;
import com.inyoungserver.spboard.command.SpWriteCommand;

@Controller
public class SpController {
	
	// 모든 command가 갖고 있는 인터페이스 타입을 선언
	SpCommand command;
	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("list() 실행");
		
		command = new SpListCommand();
		command.execute(model);
		return "list";
	}
	
	@RequestMapping("/detail")
	public String detail(HttpServletRequest request, Model model) {
		System.out.println("detail() 실행");
		model.addAttribute("request", request);
		command = new SpDetailCommand();
		command.execute(model);
		return "detail";
	}
	
	@RequestMapping("/write")
	public String write(Model model) {
		System.out.println("write() 실행");
		return "write";
	}
	
	@RequestMapping(value="/writeok", method=RequestMethod.POST)
	public String writeok(HttpServletRequest request, Model model) {
		System.out.println("writeok");
		model.addAttribute("request", request);
		command = new SpWriteCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model) {
		System.out.println("reply() 실행");
		
		model.addAttribute("request", request);
		command = new SpReplyCommand();
		command.execute(model);
		return "reply";
	}
	
	@RequestMapping(value="/replyok", method=RequestMethod.POST)
	public String replyok(HttpServletRequest request, Model model) {
		System.out.println("replyok() 실행");
		
		model.addAttribute("request", request);
		command = new SpReplyokCommand();
		command.execute(model);
		return "redirect:list";
	}
	
	@RequestMapping(value="/update")
	public String update(HttpServletRequest request, Model model) {
		System.out.println("update() 실행");
		
		model.addAttribute("request", request);
		command = new SpUpdateCommand();
		command.execute(model);
		return "upadte";
	}
	
	@RequestMapping(value="/updateok", method=RequestMethod.POST)
	public String updateok(HttpServletRequest request, Model model) {
		System.out.println("updateok() 실행");
		
		model.addAttribute("request", request);
		command = new SpUpdateokCommand();
		command.execute(model);
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("delete() 실행");
		
		model.addAttribute("request", request);
		command = new SpDeleteCommand();
		command.execute(model);
		return "redirect:list";
	}
	
}
