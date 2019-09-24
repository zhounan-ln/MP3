/**
* <p>Title: UserDao.java</p>
* <p>Description: </p>
* @author 周楠
* @date 2019年9月5日
* @version 1.0
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;
import util.DBUtils;

/**
* <p>Title: UserDao</p>
* <p>Description:  依据用户名查询，如果找不到，返回null,
	 * 否则返回一个User对象（包含了用户的所有信息）
</p>
* @author 周楠
* @date 2019年9月5日
 */
public class UserDao {
	public User selectUser(String usename) {
		User user =null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement("select * from user where username = ?");
			ps.setString(1, usename);
			rs = ps.executeQuery();
			while(rs.next()) {
				user =new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setAge(rs.getInt("age"));
				user.setEmail(rs.getString("email"));
				user.setGender(rs.getString("gender"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			DBUtils.getClosed(conn, ps, rs);
		}
		return user;
		
	}
	/**
	 * 注册
	 */
	public void insertUser(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement("insert into user values(null,?,?,?,?,?)");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getGender());
			ps.setInt(4, user.getAge());
			ps.setString(5, user.getEmail());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			DBUtils.getClosed(conn, ps, null);
		}
	}
}
