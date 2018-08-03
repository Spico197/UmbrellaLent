package view;

import java.math.BigDecimal;
import java.util.List;

import entity.Location;
import entity.School;
import entity.Shelf;
import entity.Umbrella;
import entity.User;
import service.UmbrellaService;
import service.UserService;
import tools.InputDButil;

public class UserView {
	static UserService uus = new UserService();
	static UmbrellaService uums = new UmbrellaService();
	
	public static ReturnUser userLogin() {
		ReturnUser user = new ReturnUser();
		System.out.println("press enter to return to homepage");
		System.out.println("press \"+fgt\" to reset password");
		System.out.println("username: ");
		String username = InputDButil.nextLine();
		if (username.equals("")) {
			user.mod = "main";
		} else if (username.equals("+fgt")) {
			user.mod = "fgt";
		} else {
			System.out.println("password: ");
			String password = InputDButil.nextLine();
			UserService us = new UserService();
			if (us.login(username, password) < 0) {
				System.out.println("username or password wrong");
				user.status = "end";
			} else {
				User u = us.loginReturnUser(username, password);
				user.user = u;
				System.out.println(u);
				user.status = "ok";
			}
		}
		return user;
	}
	public static ReturnUser userResetPassword() {
		String choice = "";
		ReturnUser user = new ReturnUser();
		System.out.println("press enter to return to homepage");
		System.out.println("username: ");
		String username = InputDButil.nextLine();
		if (!username.equals("")) {
			System.out.println("identification");
			String stuIden = InputDButil.next();
			System.out.println("student id: ");
			String stuId = InputDButil.next();
			System.out.println("new password: ");
			String password = InputDButil.next();
			System.out.println("check password: ");
			String checkPswd = InputDButil.next();
			if (checkPswd.equals(password)) {
				UserService us = new UserService();
				int rows = us.changePassword(username, password, stuIden, stuId);
				if (rows <= 0) {
					System.out.println("ended");
					choice = "main";
				}
				else {
					choice = "ok";
					user.user = us.loginReturnUser(username, password);
				}
			} else {
				System.out.println("password not match");
				user.status = "end";
			}
		} else {
			choice = "main";
		}
		user.status = choice;
		return user;
	}
	public static ReturnUser userRegister() {
		String choice = "";
		ReturnUser user = new ReturnUser();
		User u = new User();
		UserService us = new UserService();
		System.out.println("press enter to return to homepage");
		System.out.println("username: ");
		String username = InputDButil.nextLine();
		if (!username.equals("")) {
			u.setUser_name(username);
			System.out.println("password: ");
			String pass1 = InputDButil.nextLine();
			System.out.println("password check: ");
			String pass2 = InputDButil.nextLine();
			if (pass2.equals(pass1)) {
				u.setUser_pswd(pass1);
				System.out.println("student identification");
				u.setUser_stu_iden(InputDButil.nextLine());
				System.out.println("student id");
				u.setUser_id(InputDButil.nextBigDecimal());
				u.setUser_department_id(new BigDecimal(0));
				u.setUser_wallet(new BigDecimal(10.0));
				if (us.register(u) > 0) {
					choice = "ok";
					User uN = us.loginReturnUser(username, pass1);
					user.user = uN;
					System.out.println(uN);
				}
				else {
					System.out.println("ended");
					choice = "main";
				}
			} else {
				System.out.println("password not equals!");
				user.status = "end";
			}
		} else {
			choice = "main";
		}
		user.status = choice;
		return user;
	}
	
	public static ReturnUser borrow(User u) {
		ReturnUser ru = new ReturnUser();
		ru.user = u;
		ru.mod = "borrow";
		UmbrellaService ums = new UmbrellaService();
		System.out.println("school name: ");
		List<School> schoolList = ums.schoolList();
		for (School s : schoolList) {
			System.out.println(s);
		}
		String schoolName = InputDButil.nextLine();
		System.out.println("locations: ");
		List<Location> locs = ums.selectListBySchoolName(schoolName);
		for (Location l : locs) {
			System.out.println(l);
		}
		
		String locationName = InputDButil.nextLine();
		System.out.println("Umbrellas: ");
		List<Umbrella> umList = ums.searchByLocAndSchool(locationName, schoolName);
		for (Umbrella um : umList) {
			System.out.println(um);
		}
		System.out.println("umbrella id (ublId) u'd like to borrow:");
		BigDecimal umbrellaNumber = InputDButil.nextBigDecimal();
		Umbrella um = ums.searchById(umbrellaNumber);
		System.out.println("days u'd like to borrow:");
		int days = InputDButil.nextInt();
		int rows = ums.borrow(u, um, days);
		if (rows > 0) {
			System.out.println(um);
			System.out.println("borrow ok");
			ru.status = "ok";
		}
		else ru.status = "failed";
		return ru;
	}

	public static ReturnUser userorder(User u) {
		ReturnUser ru = new ReturnUser();
		ru.user = u;
		UserService us = new UserService();
		System.out.println("which umbrella would u like to operate (ubl_id)?");
		System.out.println("input enter to return");
		
		List<Umbrella> umList = us.getOrder(u.getUser_name());
		for (Umbrella um : umList) {
			System.out.println(um);
		}
		String in = InputDButil.nextLine();
		if (!in.equals("")) {
			BigDecimal umNumber = new BigDecimal(in);
			for (Umbrella um : umList) {
				if (um.getUbl_id().compareTo(umNumber) == 0) {
					System.out.println("what do you want to do? ");
					System.out.println("press enter to back");
					System.out.println("1. give back");
					System.out.println("2. report of loss");
					String operationNumber = InputDButil.nextLine();
					if (operationNumber.equals("1")) {
						ru.mod = "giveback";
						int rows = uums.giveBack(u, um);
						if (rows > 0) {
							ru.status = "ok";
							System.out.print("giveback ok");
							System.out.println(um);
							ru.user = uus.loginReturnUser(u.getUser_name(), u.getUser_pswd());
						}
						else ru.status = "end";
					} else if (operationNumber.equals("2")) {
						ru.mod = "loss";
						int rows = uums.reportOfLoss(u, um);
						if (rows > 0) ru.status = "ok";
						else ru.status = "end";
					} else {
						ru.status = "ok";
					}
					break;
				}
			}
		} else {
			ru.status = "end";
		}
		return ru;
	}
	public static ReturnUser modifyShelf(User u) {
		ReturnUser ru = new ReturnUser();
		ru.user = u;
		System.out.println("School you want to List: ");
		List<School> schoolList = uums.schoolList();
		for (School s : schoolList) {
			System.out.println(s);
		}
		String schoolName = InputDButil.nextLine();
		System.out.println("id you want to modify: ");
		List<Shelf> shList = uums.shelfList(schoolName);
		for (Shelf shelf : shList) {
			System.out.println(shelf);
		}
		BigDecimal shelfId = InputDButil.nextBigDecimal();
		System.out.println("loc_id you want to change to: ");
		BigDecimal shelfLocId = InputDButil.nextBigDecimal();
		Shelf sh = new Shelf(shelfId, shelfLocId, null);
		int rows = uums.updateShelf(sh);
		if (rows > 0) {
			ru.mod = "shelfupdate";
			ru.status = "ok";
		} else {
			ru.status = "fail";
		}
		return ru;
	}
	public static ReturnUser insertShelf(User u) {
		ReturnUser ru = new ReturnUser();
		ru.user = u;
		ru.mod = "insertshelf";
		
		System.out.println("School you want to List: ");
		List<School> schoolList = uums.schoolList();
		for (School s : schoolList) {
			System.out.println(s);
		}
		String schoolName = InputDButil.nextLine();
		System.out.println("place you want to place: ");
		List<Location> locs = uums.selectListBySchoolName(schoolName);
		for (Location l : locs) {
			System.out.println(l);
		}
		
		BigDecimal shelfLocId = InputDButil.nextBigDecimal();
		int rows = uums.insertShelf(shelfLocId);
		if (rows > 0) {
			ru.mod = "shelfinsert";
			ru.status = "ok";
		} else {
			ru.status = "fail";
		}
		return ru;
	}
}
