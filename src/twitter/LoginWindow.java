package twitter;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import com.jgoodies.forms.layout.*;
import java.awt.event.*;


public class LoginWindow extends JFrame {

	static Base m = null; // 메인 클래스와 동기화를 위해 추가 - 이준형
	private JFrame frmLogin;
	private JTextField idField;
	private JPasswordField passwordField;


	//버튼 디자인
	   public class RoundedButton extends JButton {
	      public RoundedButton() { super(); decorate(); } 
	      public RoundedButton(String text) { super(text); decorate(); } 
	      public RoundedButton(Action action) { super(action); decorate(); } 
	      public RoundedButton(Icon icon) { super(icon); decorate(); } 
	      public RoundedButton(String text, Icon icon) { super(text, icon); decorate(); } 
	      protected void decorate() { setBorderPainted(false); setOpaque(false); }
	      @Override 
	      protected void paintComponent(Graphics g) {
	         Color c=new Color(0,2,82); //배경색 결정
	         Color o=new Color(255,255,255); //글자색 결정
	         int width = getWidth(); 
	         int height = getHeight(); 
	         Graphics2D graphics = (Graphics2D) g; 
	         graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
	         if (getModel().isArmed()) { graphics.setColor(c.darker()); } 
	         else if (getModel().isRollover()) { graphics.setColor(c.brighter()); } 
	         else { graphics.setColor(c); } 
	         graphics.fillRoundRect(0, 0, width, height, 10, 10); 
	         FontMetrics fontMetrics = graphics.getFontMetrics(); 
	         Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds(); 
	         int textX = (width - stringBounds.width) / 2; 
	         int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent(); 
	         graphics.setColor(o); 
	         graphics.setFont(getFont()); 
	         graphics.drawString(getText(), textX, textY); 
	         graphics.dispose(); 
	         super.paintComponent(g); 
	         }
	      }
	//텍스트 필드 둥글게
	   public class RoundJTextField extends JTextField {
	       private Shape shape;
	       public RoundJTextField() {
	           super();
	           setOpaque(false);
	       }
	       protected void paintComponent(Graphics g) {
	            g.setColor(getBackground());
	            g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
	            super.paintComponent(g);
	       }
	       protected void paintBorder(Graphics g) {
	            g.setColor(getForeground());
	            g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
	       }
	       public boolean contains(int x, int y) {
	            if (shape == null || !shape.getBounds().equals(getBounds())) {
	                shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
	            }
	            return shape.contains(x, y);
	       }
	   }
	   //패스워드 필드 둥글게
	   public class RoundJPasswordField extends JPasswordField {
	       private Shape shape;
	       public RoundJPasswordField() {
	           super();
	           setOpaque(false);
	       }
	       protected void paintComponent(Graphics g) {
	            g.setColor(getBackground());
	            g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
	            super.paintComponent(g);
	       }
	       protected void paintBorder(Graphics g) {
	            g.setColor(getForeground());
	            g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
	       }
	       public boolean contains(int x, int y) {
	            if (shape == null || !shape.getBounds().equals(getBounds())) {
	                shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
	            }
	            return shape.contains(x, y);
	       }
	   }
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow(m);
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public LoginWindow(Base _m) {
		// 메인 클래스와의 연동을 위해 생성자 파라미터 및 메인 변수 할당 - 이준형
		m = _m;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = 	new JFrame();
		frmLogin.setTitle("로그인");
		frmLogin.setResizable(false);
		frmLogin.getContentPane().setBackground(new Color(0, 0, 51));
		frmLogin.setBounds(100, 100, 300, 450);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		idField = new RoundJTextField();	//아이디 텍스트 필드
		idField.setBackground(Color.WHITE);
		idField.setHorizontalAlignment(SwingConstants.LEFT);
		idField.setBounds(90, 168, 120, 24);
		frmLogin.getContentPane().add(idField);
		idField.setColumns(10);
		
		passwordField = new RoundJPasswordField();	//비밀번호 텍스트 필드
		passwordField.setBackground(Color.WHITE);
		passwordField.setBounds(90, 197, 120, 24);
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		frmLogin.getContentPane().add(passwordField);
		
		RoundedButton btnNewButton = new RoundedButton("로그인");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("HY헤드라인M", Font.PLAIN, 10));
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isLoginCheck();
			}
		});
		btnNewButton.setBounds(90, 231, 120, 24);
		frmLogin.getContentPane().add(btnNewButton);
		
		
	
		
		JLabel IDLabel = new JLabel("아이디");
		IDLabel.setFont(new Font("HY헤드라인M", Font.PLAIN, 13));
		IDLabel.setForeground(Color.WHITE);
		IDLabel.setBounds(35, 168, 60, 24);
		frmLogin.getContentPane().add(IDLabel);
		
		JLabel PWDLabel = new JLabel("비밀번호");
		PWDLabel.setFont(new Font("HY헤드라인M", Font.PLAIN, 13));
		PWDLabel.setForeground(Color.WHITE);
		PWDLabel.setBounds(35, 197, 60, 24);
		frmLogin.getContentPane().add(PWDLabel);
		
		JLabel ImageLabel = new JLabel("");
		ImageLabel.setIcon(new ImageIcon(LoginWindow.class.getResource(".\\image\\twitter_logo.png")));
		ImageLabel.setBounds(118, 94, 64, 64);
		frmLogin.getContentPane().add(ImageLabel);
		
		JLabel NoAccountLabel = new JLabel("계정이 없으신가요?");
		NoAccountLabel.setFont(new Font("HY헤드라인M", Font.PLAIN, 10));
		NoAccountLabel.setForeground(Color.WHITE);
		NoAccountLabel.setBounds(35, 334, 108, 24);
		frmLogin.getContentPane().add(NoAccountLabel);
		
		RoundedButton signinButton = new RoundedButton("회원가입");
		signinButton.setBackground(Color.WHITE);
		signinButton.setFont(new Font("HY헤드라인M", Font.PLAIN, 10));
		signinButton.setHorizontalAlignment(JButton.CENTER);
		signinButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//frmLogin.setVisible(false);
				SigninWindow a = new SigninWindow(m);
				a.frame.setVisible(true);
				}
		});
		signinButton.setBounds(130, 335, 80, 21);
		frmLogin.getContentPane().add(signinButton);
		frmLogin.setVisible(true);
	}

	public void isLoginCheck() {
		String id = new String(idField.getText());
		String pwd = new String(passwordField.getPassword());
		
		// Database.java의 로그인 구현 코드 - 이준형
		if(m.db.loginFuction(id, pwd) == true)
		{
			m.db.loginId = id;
			m.db.setUserId(id);
			m.main.initialize();
			frmLogin.setVisible(false);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Wrong ID or password");
		}
	}
}

