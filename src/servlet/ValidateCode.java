package servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 校验码
 */
/**
 * Servlet implementation class ValidateCode
 */
@WebServlet("/ValidateCode")
public class ValidateCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
	   Random random = new Random();
	   
	   //创建空白的图片
	   BufferedImage bufferedImage = new BufferedImage(100, 24, BufferedImage.TYPE_INT_RGB);
	   
	   //获取图片画笔
	   Graphics graphics = bufferedImage.getGraphics();
	   
	   //设置画笔颜色
	   graphics.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
	   
	   //绘制矩形的背景
	   graphics.fillRect(0, 0, 100, 24);
	   
	   //获取字符串的方法
	   String code = getNumber(5);
	   
	   //session绑定验证
	   HttpSession session = request.getSession();
	   session.setAttribute("code", code);
	   
	   //重新设置画笔默认颜色
	   graphics.setColor(new Color(0, 0, 0));
	   
	   //设置字体颜色
	   graphics.setFont(new Font(null, Font.BOLD, 24));
	   
	   //绘制字符串
	   graphics.drawString(code, 15, 20);
	   
	   response.setContentType("image/jpeg; charset = utf-8");
	   OutputStream outputStream = response.getOutputStream();
	   ImageIO.write(bufferedImage, "jpeg", outputStream);
   }

/**
* <p>Title: getNumber</p>
* <p>Description: 获取指定长度的字母和数字组合的字符串
</p>
* @param size
* @return
*/
private String getNumber(int size) {
	// TODO Auto-generated method stub
	String code = "";
	String str = "ABCDEFGHIJKMLNOPQRSTUVWSYZabcdefghijklmnopqrstuvwxyz0123456789";
	Random random = new Random();
	for(int i = 0; i < size; i++) {
		code += str.charAt(random.nextInt(str.length()));
	}
	return code;
}
}
