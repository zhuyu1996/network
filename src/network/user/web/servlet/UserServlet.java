package network.user.web.servlet;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import cn.itcast.commons.CommonUtils;

import network.user.domain.User;
import network.user.service.UserService;

public class UserServlet extends HttpServlet {
	private UserService userService = new UserService();
	/**
	 * 退出功能
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String quit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		return "r:/index.jsp";
	}
	
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 封装表单数据到form中
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		try {
			User user = userService.login(form);
			request.getSession().setAttribute("session_user", user);
			
			return "index.jsp";
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			return "login.jsp";
		}
	}
	public String regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 封装表单数据到form对象
		 * 保存错误信息、form到request域，转发到regist.jsp
		 * 调用service方法完成注册
		 * 保存错误信息、form到request域，转发到regist.jsp
		 * 保存成功信息转发到msg.jsp
		 */
		// 封装表单数据
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		/*
		 * 输入校验
		 * 1. 创建一个Map，用来封装错误信息，其中key为表单字段名称，值为错误信息
		 */
		Map<String,String> errors = new HashMap<String,String>();
		/*
		 * 2. 获取form中的username、password检验
		 */
		String username = form.getName();
		if(username == null || username.trim().isEmpty()) {
			errors.put("username", "用户名不能为空！");
		} else if(username.length() < 3 || username.length() > 10) {
			errors.put("username", "用户名长度必须在3~10之间！");
		}
		
		String password = form.getPassword();
		if(password == null || password.trim().isEmpty()) {
			errors.put("password", "密码不能为空！");
		} else if(password.length() < 3 || password.length() > 10) {
			errors.put("password", "密码长度必须在3~10之间！");
		}
		
		
		/*
		 * 3. 判断是否存在错误信息
		 */
		if(errors.size() > 0) {
			// 1. 保存错误信息
			// 2. 保存表单数据
			// 3. 转发到regist.jsp
			request.setAttribute("errors", errors);
			request.setAttribute("form", form);
			return "***.jsp";
		}
		
		/*
		 * 调用service的regist()方法
		 */
		try {
			userService.regist(form);
		} catch (Exception e) {
			/*
			 * 1. 保存异常信息
			 * 2. 保存form
			 * 3. 转发到regist.jsp
			 */
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			return "regist.jsp";
		}
		/*
		 * 1. 保存成功信息
		 * 2. 转发到msg.jsp
		 */
		request.setAttribute("msg", "恭喜，注册成功！");
		return "msg.jsp";
}
}
