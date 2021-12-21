package spring.model2.service.user.dao;

import spring.model2.service.user.vo.UserVO;

/*
 *  :: DAO : Data Access Object
 *  :: Controller 독립적 Unit Test 를 위하여 아래와 같은 Test 전략
 *  	1) getUser() method 에서는 DBMS와 통신하지 않고, controller 에서 전달되는 VO 객체의
 *         id/pwd 값의 null check 및 3자 이상인지 확인하여 active 값 true 처리
 *         
 *  	2) 각 계층간 설계, Unit Test 되어 있다면, Control, Model(비지니스계증/퍼시스턴스계층) 각계층의 
 *         완성도가 높아진 다음에 통합 테스트를 진행하는 전략
 */
public class UserDao {
	
	// Field
	
	// Constructor
	public UserDao() {
		System.out.println("UserDAO Default Constructor");
	}
	
	// Method
	public void  getUser(UserVO userVO){
		
		System.out.println("\n[ UserDAO.getUser() start........]");
		
		// UserVO의 Value 확인
		System.out.println( userVO.toString() );
		
		if( userVO.getUserId() != null && userVO.getUserId().length() >=3 
			&& userVO.getUserPwd() != null && userVO.getUserPwd().length() >=3	){
			userVO.setActive(true);
		}
		
		// UserVO의 Value를 확인
		System.out.println( userVO.toString() );
		
		//==> 본 예제는 Presentation Layer 의 Controller 를 이해하는 예제이다
		//==> Business Layer(구체적으로 Persistence Layer)에서의 DAO는 아래의 역할은 진행한 것으로 한다
		//		1. Connection 객체 획득 (DataSource :: ConnectionPool)
		//		2. Statement 객체 획득  (CRUD 수행)
		//		3. ResultSet 객체 획득  (CRUD 결과 획득)
		//		4. UserVO의 id / pwd 값과, DBMS통신으로 얻은 Value 값을 비교
		//		5. UserVO의 active 변경 
		System.out.println("[ UserDAO.getUser() end........]\n");
		
	}//end of getUser()
}///end of class
