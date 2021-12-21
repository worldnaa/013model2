package spring.model2.service.user.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;

import spring.model2.control.Controller;
import spring.model2.service.user.dao.UserDao;
import spring.model2.service.user.vo.UserVO;

// Client Request (logon.do) 처리하는 Controller
public class LogonController implements Controller {

	// Method
	public void excute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("[LogonController.excute() start...]");
		
		// getSession(boolean create) : 연결된 현재 HttpSession 을 반환
		// 현재 세션이 없고, create 이 true 이면 새 세션을 반환
		HttpSession session = req.getSession(true);
		
		//==> Controller :: 권한 / 인증처리
		//==> session Object Scope 에 저장된 UserVO 객체를 이용하여 인증
		
		//==> 경우 1 : 기존에 세션이 없으면, session Object Scope 에 userVO 인스턴스 생성 및 저장
		//==> isNew() : client가 세션에 대해 모르거나, 세션에 참여하지 않기로 선택한 경우 true 반환
		if(session.isNew() || session.getAttribute("userVO") == null) {
			session.setAttribute("userVO", new UserVO());
		// setAttribute(String name, Object value) : 지정된 name을 사용해서 세션에 개체(value)를 바인딩
		// getAttribute(String name) : 지정된 name을 가진 개체를 반환하거나, 없으면 null 반환	
		}
		
		//==> 경우 2 : session Object Scope userVO 추출
		//==> (UserVO)하는이유 : getAttribute()는 Object를 리턴하므로, UserVO로 형변환
		UserVO userVO = (UserVO)session.getAttribute("userVO");
		
		//==> Controller :: Navigaion (forward / sendRedirect view page 결정)
		//==> Navigation 디폴트 페이지 지정
		String requestPage = "/user/logon.jsp";
			
		//==> UserVO.active 를 이용하여 로그인 유무 판단
		if(userVO.isActive()) {
			requestPage = "/user/home.jsp";
		}
		
		//==> Controller :: Navigation (최종 결정된 page forward)
		RequestDispatcher rd = req.getRequestDispatcher(requestPage);
		rd.forward(req, res);
		
		System.out.println("[LogonController.excute() end...]");
	}

}//end of class
