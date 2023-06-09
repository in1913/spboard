package com.inyoungserver.spboard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.inyoungserver.spboard.dao.SpDao;

public class SpWriteCommand implements SpCommand {

	@Override
	public void execute(Model model) {
		Map <String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		// 이름, 비밀번호, 제목, 내용
		String uname = request.getParameter("uname");
		String upass = request.getParameter("upass");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		SpDao dao = new SpDao();
		
		dao.write(uname, upass, title, content);
	}

}
