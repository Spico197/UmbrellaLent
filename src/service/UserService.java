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
	 * ��¼���ܣ�ͨ����ѯ�û�������û����������ѯ�û��Ƿ����
	 * ��ƥ��ɹ������½�ɹ������˺�Ϊ��ͨ�û����򷵻�0������¼�ɹ������˺�Ϊ����Ա�˺ţ��򷵻�1��
	 * ����¼���ɹ����򷵻�-1
	 * @param username: <String> �û�������û���
	 * @param password: <String >�û����������
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
	 * �û���ע�Ṧ��
	 * �����ݿ������и��û������û�ע��ʧ�ܣ�����-1
	 * @param u: ��ע���User����
	 * @return: <int> ����ֵ
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
	 * ͨ���û������ҵ�ǰ�û��������������ɡ
	 * @param username: <String> �û���
	 * @return: List<Umbrella>
	 */
	public List<Umbrella> getOrder(String username) {
		List<Umbrella> umbrellas = umd.orderByUsername(username);
		return umbrellas;
	}
	/**
	 * �����û�����
	 * ������ֵΪ-1������Ĵ���
	 * @param username: <String> �û���
	 * @param newPassword: <String> ������
	 * @param stuIden: <String> �û����֤��
	 * @param stuId: <String> �û�ѧ��
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
