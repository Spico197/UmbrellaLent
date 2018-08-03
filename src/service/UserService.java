package service;

import java.math.BigDecimal;
import java.util.List;

import dao.StudentDao;
import dao.UmbrellaDao;
import dao.UserDao;
import entity.Student;
import entity.Umbrella;
import entity.User;

public class UserService {
	UserDao ud = new UserDao();
	UmbrellaDao umd = new UmbrellaDao();
	StudentDao sd = new StudentDao();
	/**
	 * 登录功能，通过查询用户输入的用户名和密码查询用户是否存在
	 * 若匹配成功，则登陆成功，且账号为普通用户，则返回0；若登录成功，且账号为管理员账号，则返回1。
	 * 若登录不成功，则返回-1
	 * @param username: <String> 用户输入的用户名
	 * @param password: <String >用户输入的密码
	 * @return: <int> [0|1|-1]
	 */
	public int login(String username, String password) {
		int flag = -1;
		User u = ud.select(username, password);
		if (u.getUser_name() != null) {
			if (u.getUser_department_id().compareTo(new BigDecimal(0)) == 0) flag = 0;
			else if (u.getUser_department_id().compareTo(new BigDecimal(1)) == 0) flag = 1;
			else flag = -1;
		} else flag = -1;
		return flag;
	}
	public User loginReturnUser(String username, String password) {
		User u = ud.select(username, password);
		return u;
	}
	/**
	 * 用户的注册功能
	 * 若数据库中已有该用户，则用户注册失败，返回-1
	 * @param u: 待注册的User对象
	 * @return: <int> 返回值
	 */
	public int register(User u) {
		int rows = 0;
		if (ud.select(u.getUser_name()).getUser_name() == null && u.getUser_department_id().compareTo(new BigDecimal(0)) == 0) {
			rows = ud.insert(u);
		} else {
			rows = -1;
		}
		return rows;
	}
	/**
	 * 通过用户名查找当前用户名下所借的所有伞
	 * @param username: <String> 用户名
	 * @return: List<Umbrella>
	 */
	public List<Umbrella> getOrder(String username) {
		List<Umbrella> umbrellas = umd.orderByUsername(username);
		return umbrellas;
	}
	/**
	 * 更改用户密码
	 * 若返回值为-1，则更改错误
	 * @param username: <String> 用户名
	 * @param newPassword: <String> 新密码
	 * @param stuIden: <String> 用户身份证号
	 * @param stuId: <String> 用户学号
	 * @return: <int>
	 */
	public int changePassword(String username, String newPassword, String stuIden, String stuId) {
		int rows = 0;
		User un = ud.select(username);
		Student s = sd.select(stuIden);
		if (un != null) {
			if (s.getStu_name() != null && s.getStu_iden().equals(un.getUser_stu_iden())
					&& s.getStu_id().equals(stuId)) {
				un.setUser_pswd(newPassword);
				rows = ud.update(un);
			} else rows = -1;
		} else rows = -1;
		return rows;
	}
}
