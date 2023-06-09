package com.inyoungserver.spboard.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.inyoungserver.spboard.dao.SpDao;
import com.inyoungserver.spboard.dto.SpDto;

public class SpListCommand implements SpCommand {

	@Override
	public void execute(Model model) {
		
		SpDao dao = new SpDao();
		ArrayList <SpDto> dtos = dao.list();
		
		model.addAttribute("list", dtos);
	}

}
