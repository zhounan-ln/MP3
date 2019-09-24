/**
* <p>Title: Test.java</p>
* <p>Description: </p>
* @author 周楠
* @date 2019年9月7日
* @version 1.0
 */
package test;
import dao.UserDao;
import entity.User;

public class Test {
	@org.junit.Test
	public void insert() {
		UserDao dao = new UserDao();
		User user = new User();
		user.setUsername("我是神麼");
		user.setPassword("775881089");
		user.setGender("男");
		user.setAge(24);
		user.setEmail("13476054425@163.com");
		dao.insertUser(user);
	}
	
	
	@org.junit.Test
	public void select() {
		UserDao dao = new UserDao();
		User user = dao.selectUser("我是神麼");
		System.out.println(user);
	}
}