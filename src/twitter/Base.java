package twitter;

public class Base {
	/*
	 * 새로운 클래스를 추가하고자 한다면
	 * 
	 * 1. 현재 클래스의 전체 변수로 새로운 클래스 선언
	 *  ex) SomeWindow sw = null;
	 *  
	 * 2. main 함수 안에 새로운 클래스의 오브젝트 생성
	 *  ex) main.sw = new SomeWindow(main);
	 *  
	 * 3. 현재 클래스(Main)을 파라미터로 넣어주는 것 잊지 마세요
	 *  ex ) new SomeWindow(main  <--- 이것 )
	 */
	
	// ProfileWindow, EditWindow 작업 중
	// ProfileWindow pw = null;
	// EditWindow ew = null;
	
	LoginWindow lw = null;
	SigninWindow sw = null;
	Main main = null;
	User user = null;
	
	Database db = null;
	
	public static void main(String[] args)
	{
		Base m = new Base();
		m.db = new Database();
		m.lw = new LoginWindow(m);
		m.sw = new SigninWindow(m);
		m.main = new Main(m);
	}
	
}
