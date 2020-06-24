package by.htp.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.ishop.controller.command.Command;

public class ChangeLocal implements Command{

	private static final String REQUEST_LOCAL = "local";
	
	private static final String LAST_REQUEST = "lastRequest";
	private static final String GO_TO_MAIN = "Controller?command=go_to_main";

	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
 

		 HttpSession session = request.getSession(true);
		
		session.setAttribute(REQUEST_LOCAL, request.getParameter(REQUEST_LOCAL));
		
			
			if(session.getAttribute(LAST_REQUEST)!=null) {
			
			response.sendRedirect(session.getAttribute(LAST_REQUEST).toString());
			
			}else {
				response.sendRedirect(GO_TO_MAIN);
			}
			
		
 		
	}

}
