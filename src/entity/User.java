/**
* <p>Title: User.java</p>
* <p>Description: </p>
* @author 周楠
* @date 2019年9月5日
* @version 1.0
 */
package entity;

/**
* <p>Title: User</p>
* <p>Description: 用户信息</p>
* @author 周楠
* @date 2019年9月5日
 */
public class User {
	private int id;
	private String username;
	private String password;
	private String gender;
	private int age;
	private String email;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", gender=" + gender + ", age="
				+ age + ", email=" + email + "]";
	}


}
