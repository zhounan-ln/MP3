package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MusicDao;
import dao.UserDao;
import entity.Music;
import entity.User;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    protected void service(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    	String uri = request.getRequestURI();
    	String path = uri.substring(uri.lastIndexOf("/")+1, uri.lastIndexOf("."));
    	 	if("register" .equals(path)) {
    		   processRegister(request,response);
    	 	}else if("login".equals(path)) {
    			processLogin(request,response);
    		}else if("findMusic".equals(path)) {
    			processFindMusic(request,response);
    		}else if("cancel".equals(path)) {
    			processCancel(request,response);
    		}else if("message".equals(path)) {
    			processMessage(request,response);
    		}
    	
    }
	/**
	* <p>Title: processMessage</p>
	* <p>Description:留言 </p>
	* @param request
	* @param response
	 * @throws IOException 
	 * @throws ServletException 
	*/
	private void processMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String comment = request.getParameter("comment");
		Date date = new Date();
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
		String time = sFormat.format(date);
		String str = username +"留言" + comment+" "+time;
		List<String> message = (List<String>) request.getSession().getAttribute("messages");
		if(message == null) {
			message = new ArrayList<String>();
		}
		message.add(str);
		request.getSession().setAttribute("messages", message);
		request.getRequestDispatcher("message.jsp").forward(request, response);
	}
	/**
	* <p>Title: processCancel</p>
	* <p>Description:注销登录 </p>
	* @param request
	* @param response
	 * @throws IOException 
	*/
	private void processCancel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		request.getSession().invalidate();         //删除session
		response.sendRedirect("index.jsp");	
	}
	/**
	* <p>Title: processFindMusic</p>
	* <p>Description: 查找歌单</p>
	* @param request
	* @param response
	 * @throws IOException 
	 * @throws ServletException 
	*/
	private void processFindMusic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String str = request.getParameter("str");
		MusicDao musicDao = new MusicDao();
		List<Music> musics = null;
		if(str != null) {
			musics = musicDao.ifMusics(str);		//关键字查询
		}else {
			musics = musicDao.findAllMusic();
		}
		for(Music music : musics) {
			System.out.println(music.getUrl());
		}
		request.setAttribute("findMusic", musics);
		request.getRequestDispatcher("show.jsp").forward(request, response);
	}
	/**
	* <p>Title: processLogin</p>
	* <p>Description: 登录</p>
	* @param request
	* @param response
	 * @throws IOException 
	 * @throws ServletException 
	*/
	private void processLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		
		String password = request.getParameter("password");
		String validatecode = request.getParameter("validatecode").toLowerCase();
		UserDao userDao = new UserDao();
		try {
			User user = userDao.selectUser(username);
			if(user!=null && user.getPassword() .equals(password)) {
				String code = request.getSession().getAttribute("code").toString().toLowerCase();		//获取绑定数据
				if(! validatecode.equals(code)) {
					request.setAttribute("login", "验证码错误");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
				request.getSession().setAttribute("user", user);				//绑定数据
				request.getRequestDispatcher("index.jsp").forward(request, response);		//转发到首页
			
		}else {
			request.setAttribute("login", "用户名或密码错误！");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "网络不稳定，请稍候重试");
			request.getRequestDispatcher("err.jsp").forward(request, response);
		}
	}
	/**
	* <p>Title: processRegister</p>
	* <p>Description: </p>
	* @param request
	* @param response
	 * @throws IOException 
	 * @throws ServletException 
	*/
	private void processRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		System.out.println(username);
		String password = request.getParameter("password");
		String OKpassword = request.getParameter("OKpassword");
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");
		String email = request.getParameter("email");
		if(username == null || password ==null || age == null || email == null || username == "" || password =="" || age =="" || email == "") {
			request.setAttribute("error", "内容不能·为空");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		}else {
			if(password.equals(OKpassword)) {
				UserDao userDao = new UserDao();
				User user = new User();
				user.setUsername(username);
				user.setPassword(password);
				user.setEmail(email);
				user.setGender(gender);
				user.setAge(Integer.parseInt(age));
				try {
					userDao.insertUser(user);
					response.sendRedirect("login.jsp");    //重定向到登录界面
				}catch(Exception e) {
					e.printStackTrace();
					request.setAttribute("msg", "网路不稳定，请稍候重试");
					request.getRequestDispatcher("err.jsp").forward(request, response);
				}
			}
		}
	}
}
