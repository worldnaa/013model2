package spring.model2.control;

import spring.model2.service.user.view.HomeController;
import spring.model2.service.user.view.LogonActionController;
import spring.model2.service.user.view.LogonController;

// Client Request (요구사항,command)를 처리
// Controller 생성해서 리턴
// Command Factory Pattern

public class ControllerMapping {

	// Field 
	private static ControllerMapping controllerMapping;
	
	// Constructor (private 이므로 인스턴스 생성 불가)
	private ControllerMapping() {
	}
	
	// Method
	// 동일한 자신의 인스턴스를 return 하는 static method
	public static ControllerMapping getInstance() {
		
		// 인스턴스를 1개만 생성하도록 하는 기법 (필수는 X)
		if(controllerMapping == null) {
			controllerMapping = new ControllerMapping();
		}
		return controllerMapping;
	}
	
	// Client Request Page(actionPage)를 받아 Request 처리하고, Controller 생성해 return
	public Controller getController(String actionPage) {
		System.out.println("[ControllerMapping.getController() start...]");
		
		Controller controller = null;
		
		if(actionPage.equals("logon")) {
			controller = new LogonController();
		}else if(actionPage.equals("logonAction")) {
			controller = new LogonActionController();
		}else if(actionPage.equals("home")) {
			controller = new HomeController();
		}
		
		//==> 추가사항이 발생하면 아래와 같이 추가한다(예시)
		/*
		}else if(actionPage.equals("member")) {
			action = new MemberAction();
		}else if(actionPage.equals("logout")) {
			action = new LogoutAction();
		}
		*/
		
		System.out.println("[ControllerMapping.getController() end...]");
		return controller;
	}

}//end of class













