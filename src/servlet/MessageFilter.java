/**
* <p>Title: MessageFilter.java</p>
* <p>Description: </p>
* @author 周楠
* @date 2019年9月7日
* @version 1.0
 */
package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* <p>Title: MessageFilter</p>
* <p>Description: </p>
* @author 周楠
* @date 2019年9月7日
 */
public class MessageFilter implements Filter {
	private List<String> listLllegals = new ArrayList<String>();

	/* （非 Javadoc）
	* <p>Title: destroy</p>
	* <p>Description: </p>
	* @see javax.servlet.Filter#destroy()
	*/
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/* （非 Javadoc）
	* <p>Title: doFilter</p>
	* <p>Description: </p>
	* @param arg0
	* @param arg1
	* @param arg2
	* @throws IOException
	* @throws ServletException
	* @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	*/
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request= (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String comment = request.getParameter("comment");
		for(int i = 0; i<comment.length(); i++) {
			String c = comment.substring(i, i+1);
			System.out.println(c); 
			if(this.listLllegals.contains(c)) {
				response.sendRedirect("message.jsp");
				return;
			}
		}
		arg2.doFilter(request, response);
	}

	/* （非 Javadoc）
	* <p>Title: init</p>
	* <p>Description: </p>
	* @param arg0
	* @throws ServletException
	* @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	*/
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		String illegal = arg0.getInitParameter("illegal");
		String[] illegals = illegal.split(",");
		for(String string : illegals) {
			this.listLllegals.add(string);
		}
		System.out.println(this.listLllegals);
	}

}
