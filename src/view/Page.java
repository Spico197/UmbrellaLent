package view;

import java.io.IOException;

import entity.User;
import javafx.scene.layout.Border;
import tools.InputDButil;

public class Page {
	public static ReturnUser homepage() {
		String choice = "";
				String welcomeString = 
						"+---------------------------------------------------------+\r\n" + 
								"|                                                         |\r\n" + 
								"|                            ^                            |\r\n" + 
								"|                            |                            |\r\n" + 
								"|          <------------------------------------>         |\r\n" + 
								"|        <+        ++        |        ++        +>        |\r\n" + 
								"|       <+        ++         |         ++        +>       |\r\n" + 
								"|      <+        ++          |          ++        +>      |\r\n" + 
								"|     <+        ++           |           ++        +>     |\r\n" + 
								"|    -----------v-------------------------v---------->    |\r\n" + 
								"|                            |                            |\r\n" + 
								"|                            |                            |\r\n" + 
								"|                            |                            |\r\n" + 
								"|                            |                            |\r\n" + 
								"|                            |                            |\r\n" + 
								"|                            |                            |\r\n" + 
								"|                            |                            |\r\n" + 
								"|                       ^    |                            |\r\n" + 
								"|                       +----+                            |\r\n" + 
								"|                                                         |\r\n" + 
								"+---------------------------------------------------------+\r\n";
				System.out.println(welcomeString);
		System.out.println("欢迎进入贵大信伞租借平台~");
		System.out.println("请输入您要进行的操作");
		System.out.println("1. Login");
		System.out.println("2. Register");
		System.out.println("3. Reset Password");
		System.out.println("4. Quit");
		choice = InputDButil.nextLine();
		System.out.println("choice: " + choice);
		ReturnUser reUser = new ReturnUser();
		switch (choice) {
		case "1":
			reUser = UserView.userLogin();
			reUser.mod = "login";
			break;
		case "2":
			reUser = UserView.userRegister();
			reUser.mod = "register";
			break;
		case "3":
			reUser = UserView.userResetPassword();
			reUser.mod = "reset_password";
			break;
		case "4":
			reUser.status = "end";
			reUser.mod = "quit";
			break;
		default:
			reUser.status = "ok";
			break;
		}
		return reUser;
	}

	public static ReturnUser userpage(User u) {
		System.out.println("Welcome! User " + u.getUser_name() + "\tYour wallet remains: " + u.getUser_wallet() + " ￥");
		System.out.println("1. borrow an umbrella");
		System.out.println("2. see my order");
		System.out.println("3. logout");
		String input = InputDButil.nextLine();
		ReturnUser re = new ReturnUser();
		re.user = u;
		switch (input) {
		case "1":
			re = UserView.borrow(u);
			break;
		case "2":
			re = UserView.userorder(u);
			break;
		case "3":
			re.mod = "logout";
			re.status = "end";
			break;
		default :
			re.status = "ok";
			break;
		}
		return re;
	}

	public static ReturnUser adminpage(User u) {
		ReturnUser ru = new ReturnUser();
		ru.user = u;
		System.out.println("Welcome! Admin " + u.getUser_name());
		System.out.println("1. Modify a shelf");
		System.out.println("2. Insert a shelf");
		System.out.println("To be continued...");
		System.out.println("3. logout");
		String input = InputDButil.nextLine();
		ru.user = u;
		switch (input) {
		case "1":
			ru = UserView.modifyShelf(ru.user);
			break;
		case "2":
			ru = UserView.insertShelf(ru.user);
			break;
		case "3":
			ru.mod = "logout";
			ru.status = "end";
			break;
		default :
			ru.status = "ok";
			break;
		}
		return ru;
	}

	public static void newpage() {
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}
}
