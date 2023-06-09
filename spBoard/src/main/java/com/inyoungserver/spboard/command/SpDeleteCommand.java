package com.inyoungserver.spboard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.inyoungserver.spboard.dao.SpDao;

public class SpDeleteCommand implements SpCommand {

	@Override
	public void execute(Model model) {
		
		Map <String, Object> map = model.asMap();
		
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String num = request.getParameter("num");
		
		SpDao dao = new SpDao();
		dao.delete(num);
		
		
	}

}
