/**
* <p>Title: DBUtils.java</p>
* <p>Description: </p>
* @author 周楠
* @date 2019年9月5日
* @version 1.0
 */
package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;

/**
* <p>Title: DBUtils</p>
* <p>Description: 数据库</p>
* @author 周楠
* @date 2019年9月5日
 */
public class DBUtils {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/music?useUnicode=true&characterEncoding=utf-8";
	private static String user = "root";
	private static String password = "";
	public static Connection conn;
	public static Connection getConnection() throws SQLException {
		
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url, user, password);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return conn;
			
		
	}
	public static void getClosed(Connection conn, Statement st, ResultSet rs) {
			try {
				if(rs != null) rs.close();
				if(st != null) st.close();
				if(conn != null) 
					conn.setAutoCommit(true);  		//归还之前开启自动提交
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	}
}
