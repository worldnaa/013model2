package spring.model2.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Controller 객체의 일반적,공통적 행위(Method)를 정의,규정하는 interface
// Controller 객체는 Control에서 수행하는 실질적,핵심적 역할을 수행
// Servlet API 핵심,주요한 객체인 HttpServletRequest , HttpServletResponse 를 인자로 전달받음

public interface Controller {
	
	public void excute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException;
	
}//end of class

