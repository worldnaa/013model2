package spring.model2.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import spring.model2.service.user.dao.UserDao;
import spring.model2.service.user.vo.UserVO;

// DispatcherServlet 은 단순히 단일 인입점 역할만 한다
// 단일 인입점 이외의 모든 역할은 각각의 다른 Bean 으로 위임한다

public class DispatcherServlet extends HttpServlet {
	
	// service() Method
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("\n[DispatcherServlet.service() start...]");
		
		//==> Controller :: Client 요구사항 판단 :: URL/URI 이용
		//==> 아래 getURI() method 실행
		//==> URI : 포트번호 후단 / 부터 (ex. 8080/013model2/logon.do => /013model2/logon.do 가 URI)
		String actionPage = this.getURI(req.getRequestURI());
		System.out.println(":: URI ? => " + req.getRequestURI());
		System.out.println(":: client의 요구사항은 ? => " + actionPage);
		
		//==> Controller :: 선처리 / 공통처리 사항이 있다면..
		//==> 본 예제 : 한글처리
		req.setCharacterEncoding("utf-8");
		
		//////////////////////////// 변 경 된 부 분 (핵심) ///////////////////////////////
		
		//==> Controller :: Client 요구사항 처리 및 Business logic 처리
		//==> Business Logic 을 처리하는 Controller 선언
		Controller controller = null;
		
		//==> Client의 Request에 대응하는 Controller instance 생성
		//==> ControllerMapping을 통해 Client Request 를 처리할 Controller instance 생성 
		ControllerMapping cf = ControllerMapping.getInstance();
		controller = cf.getController(actionPage);
		
		//==> Controller.execute() method 를 호출
		controller.excute(req, res);
		
		///////////////////////////////////////////////////////////////////////////////
		
		System.out.println("[DispatcherServlet.service() end...]");
	
	}//end of service

	// Client 의 요구사항 판단 ==> requestURI = "/~~.do" 형식이므로 아래와 같이 진행
	private String getURI(String requestURI) {
		
		// lastIndexOf('/') ==> 문자열에서 '/' 가 포함된 마지막 index 반환 (여러개일 경우 제일 마지막 인덱스 반환)
		int start = requestURI.lastIndexOf('/')+1;
		int end = requestURI.lastIndexOf(".do");

		System.out.println(":: getURI()의 start : " + start);
		System.out.println(":: getURI()의 end : " + end);
		
		// substring(int beginIndex, int endIndex-1)
		// beginIndex 부터 시작해서 endIndex-1 까지의 문자열 반환
		String actionPage = requestURI.substring(start,end);
		return actionPage;
	}
	
}//end of class
















