package view;

import java.math.BigDecimal;

public class Test {
	public static void main(String[] args) {
		ReturnUser reuser1 = new ReturnUser();
		while (!reuser1.status.equals("end")) {
			reuser1 = Page.homepage();
			if (reuser1.mod.equals("quit"))
				break;
			if (reuser1.user.getUser_name()==null) {
				reuser1.status = "fail";
				continue;
			}
			if (reuser1.mod.equals("login")) {
				ReturnUser reuser2 = new ReturnUser();
				reuser2.user = reuser1.user;
				System.out.println(reuser2);
				while (!reuser2.status.equals("end")) {
					if (reuser2.user.getUser_department_id().compareTo(new BigDecimal(0)) == 0) {
						reuser2 = Page.userpage(reuser2.user);
						ReturnUser reuser3 = new ReturnUser();
						reuser3.user = reuser2.user;
						while (!reuser3.status.equals("end")) {
							reuser3 = Page.userpage(reuser3.user);
							System.out.println(reuser3);
						}
					} else if (reuser2.user.getUser_department_id().compareTo(new BigDecimal(1)) == 0) {
						reuser2 = Page.adminpage(reuser2.user);
						if (reuser2.status.equals("end"))
							break;
						ReturnUser reuser3 = new ReturnUser();
						reuser3.user = reuser2.user;
						while (!reuser3.status.equals("end")) {
							reuser3 = Page.adminpage(reuser3.user);
							System.out.println(reuser3);
						}
						System.out.println(reuser2);
					}
				}
				System.out.println(reuser1);
			}
		}
	}
}