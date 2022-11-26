package twitter;



import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SigninWindow extends JFrame {

	static Base m = null; // 메인 클래스와 동기화를 위해 추가 - 이준형
	public JFrame frame;
	private JTextField nameField;
	private JTextField PWDField;
	private JTextField PhoneNumField;
	private JTextField emailField;
	private JTextField nicknameField;
	private JLabel birthLabel;
	private JTextField birthField;

	//둥근 텍스트 필드
		public class RoundJTextField extends JTextField {
		       private Shape shape;
		       public RoundJTextField() {
		           super();
		           setOpaque(false); // As suggested by @AVD in comment.
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
		//둥근 버튼
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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SigninWindow window = new SigninWindow(m);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public SigninWindow(Base _m) {
		// 메인 클래스와의 연동을 위해 생성자 파라미터 및 메인 변수 할당 - 이준형
		m = _m;
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 51));
		frame.setTitle("회원가입");
		frame.setResizable(false);
		frame.getContentPane().setEnabled(false);
		frame.setBounds(100, 100, 400, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel createAccountLabel = new JLabel("계정을 생성하세요");
		createAccountLabel.setFont(new Font("HY헤드라인M", Font.PLAIN, 23));
		createAccountLabel.setForeground(Color.WHITE);
		createAccountLabel.setBounds(66, 59, 211, 68);
		frame.getContentPane().add(createAccountLabel);
		
		JLabel nameLabel = new JLabel("아이디");
		nameLabel.setFont(new Font("HY헤드라인M", Font.PLAIN, 15));
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setBounds(115, 138, 52, 21);
		frame.getContentPane().add(nameLabel);
		
		nameField = new RoundJTextField();
		nameField.setBounds(115, 156, 170, 30);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JLabel PWDLabel = new JLabel("비밀번호");
		PWDLabel.setForeground(Color.WHITE);
		PWDLabel.setFont(new Font("HY헤드라인M", Font.PLAIN, 15));
		PWDLabel.setBounds(115, 199, 113, 21);
		frame.getContentPane().add(PWDLabel);
		
		PWDField = new RoundJTextField();
		PWDField.setColumns(10);
		PWDField.setBounds(115, 217, 170, 30);
		frame.getContentPane().add(PWDField);
		
		JLabel nicknameLabel = new JLabel("닉네임");
		nicknameLabel.setForeground(Color.WHITE);
		nicknameLabel.setFont(new Font("HY헤드라인M", Font.PLAIN, 15));
		nicknameLabel.setBounds(115, 260, 86, 21);
		frame.getContentPane().add(nicknameLabel);

		nicknameField = new RoundJTextField();
		nicknameField.setColumns(10);
		nicknameField.setBounds(115, 277, 170, 30);
		frame.getContentPane().add(nicknameField);
		
		JLabel emailLabel = new JLabel("이메일");
		emailLabel.setForeground(Color.WHITE);
		emailLabel.setFont(new Font("HY헤드라인M", Font.PLAIN, 15));
		emailLabel.setBounds(115, 321, 86, 21);
		frame.getContentPane().add(emailLabel);

		emailField = new RoundJTextField();
		emailField.setColumns(10);
		emailField.setBounds(115, 338, 170, 30);
		frame.getContentPane().add(emailField);
		
		JLabel birthLabel = new JLabel("생일");
		birthLabel.setForeground(Color.WHITE);
		birthLabel.setFont(new Font("HY헤드라인M", Font.PLAIN, 15));
		birthLabel.setBounds(115, 380, 52, 21);
		frame.getContentPane().add(birthLabel);
		
		birthField = new RoundJTextField();
		birthField.setColumns(10);
		birthField.setBounds(115, 397, 170, 30);
		frame.getContentPane().add(birthField);
		
		/*
		birthLabel = new JLabel("생일");
		birthLabel.setForeground(Color.WHITE);
		birthLabel.setFont(new Font("HY헤드라인M", Font.PLAIN, 15));
		birthLabel.setBounds(115, 439, 52, 21);
		frame.getContentPane().add(birthLabel);
		
		birthField = new RoundJTextField();
		birthField.setColumns(10);
		birthField.setBounds(115, 456, 170, 30);
		frame.getContentPane().add(birthField);
		*/
		
		JButton completeButton = new RoundedButton("완료");
		completeButton.setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		completeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Database.java의 회원가입 구현 코드 - 이준형
				m.db.signupFunction(nameField.getText(), PWDField.getText(), nicknameField.getText(), emailField.getText(), birthField.getText());
				frame.setVisible(false);
			}
		});
		completeButton.setBounds(182, 500, 103, 30);
		frame.getContentPane().add(completeButton);
	}
}