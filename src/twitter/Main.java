package twitter;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import javax.swing.JCheckBox;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;

public class Main {
	static Base m = null;
	static String id;
	static int user_number;
	static String name;
	int C_H_P = 0;
	int C_E_P = 0;
	int C_B_P = 0;
	int C_L_P = 0;
	private Database db = new Database();
	public JFrame frame;
	private String[] totalUser;
	private int userCount = 0;
	private int totalUserCount;
	private GridBagLayout Gbag = new GridBagLayout();
	private GridBagLayout Gbag_EX = new GridBagLayout();
	private JTextField Search_field;
	public JPanel lastPanel;
	public JPanel otherProfile_page;
	private User[] user = new User[1000];
	JPanel[] data = new JPanel[1000];
	JPanel[] exploreData = new JPanel[1000];
	JPanel[] bookData = new JPanel[1000];
	JPanel[] likeData = new JPanel[1000];
	int x = 0, y = 0;
	private JTable Search_table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main(m);
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
	public Main(Base _m) {
		m = _m;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		totalUserCount = m.db.getUserCount();
		name = m.db.getNick();
		user_number = m.db.userId;
		id = m.db.getId();
		System.out.println(user_number + " number");
		user[user_number] = new User(user_number, user_number, id, name, m);
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(21, 32, 43));
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		// west
		JPanel West = new JPanel();
		West.setBorder(new LineBorder(new Color(192, 192, 192)));
		West.setBackground(new Color(21, 32, 43));
		West.setBounds(0, 0, 392, 832);
		frame.getContentPane().add(West);
		West.setLayout(null);

		ImageIcon Hi = new ImageIcon("./image/Home_icon.png");
		JButton Home_Icon = new JButton(size(Hi, 40, 40));
		Home_Icon.setForeground(new Color(255, 255, 255));
		Home_Icon.setBorderPainted(false);
		Home_Icon.setContentAreaFilled(false);
		Home_Icon.setBounds(91, 24, 141, 51);
		West.add(Home_Icon);

		ImageIcon H = new ImageIcon("./image/Home.png");
		JButton Home = new JButton("  Home", size(H, 40, 35));
		Home.setFont(new Font("굴림", Font.PLAIN, 15));
		Home.setForeground(new Color(255, 255, 255));
		Home.setBorderPainted(false);
		Home.setContentAreaFilled(false);
		Home.setBounds(107, 105, 146, 51);
		West.add(Home);

		ImageIcon E = new ImageIcon("./image/Explore.png");
		JButton Explore = new JButton("  Explore", size(E, 40, 40));
		Explore.setFont(new Font("굴림", Font.PLAIN, 15));
		Explore.setForeground(new Color(255, 255, 255));
		Explore.setBorderPainted(false);
		Explore.setContentAreaFilled(false);
		Explore.setBounds(111, 185, 146, 51);
		West.add(Explore);

		ImageIcon N = new ImageIcon("./image/notification.png");
		JButton Likes = new JButton("     Likes", size(N, 40, 40));
		Likes.setFont(new Font("굴림", Font.PLAIN, 15));
		Likes.setForeground(new Color(255, 255, 255));
		Likes.setBackground(new Color(21, 32, 43));
		Likes.setBorderPainted(false);
		Likes.setContentAreaFilled(false);
		Likes.setBounds(100, 270, 169, 45);
		West.add(Likes);

		ImageIcon M = new ImageIcon("./image/Bookmark.png");
		JButton Bookmark = new JButton("    Bookmark", size(M, 30, 30));
		Bookmark.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Bookmark.setFont(new Font("굴림", Font.PLAIN, 15));
		Bookmark.setForeground(new Color(255, 255, 255));
		Bookmark.setBackground(new Color(21, 32, 43));
		Bookmark.setBorderPainted(false);
		Bookmark.setContentAreaFilled(false);
		Bookmark.setBounds(107, 347, 169, 51);
		West.add(Bookmark);

		ImageIcon P = new ImageIcon("./image/Profile.png");
		JButton Profile = new JButton("  Profile", size(P, 40, 40));
		Profile.setFont(new Font("굴림", Font.PLAIN, 15));
		Profile.setForeground(new Color(255, 255, 255));
		Profile.setBackground(new Color(21, 32, 43));
		Profile.setBorderPainted(false);
		Profile.setContentAreaFilled(false);
		Profile.setBounds(105, 425, 150, 51);
		West.add(Profile);

		ImageIcon T = new ImageIcon("./image/tweet.png");
		JButton Tweet = new JButton(size(T, 220, 70));
		Tweet.setForeground(new Color(255, 255, 255));
		Tweet.setBackground(new Color(21, 32, 43));
		Tweet.setBorderPainted(false);
		Tweet.setContentAreaFilled(false);
		Tweet.setBounds(91, 505, 248, 100);
		West.add(Tweet);

		// east
		JPanel East = new JPanel();
		East.setBorder(new LineBorder(new Color(192, 192, 192)));
		East.setBackground(new Color(21, 32, 43));
		East.setBounds(995, 0, 545, 832);

		frame.getContentPane().add(East);
		East.setLayout(null);

		// 대충 검색
		String header[] = {"", ""};
		String contents[][] = new String[totalUserCount][2];
		ResultSet user_count = db.getUser();
		totalUser = new String[totalUserCount];
		try {
			while(user_count.next()) {
				totalUser[userCount] = user_count.getString(1);
				userCount++;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		for(int i=0;i<userCount;i++) {
			contents[i][0] = totalUser[i];
			contents[i][1] = totalUser[i];
		}
		DefaultTableModel model = new DefaultTableModel(contents, header);
		Search_table = new JTable(model);
		Search_table.setBorder(null);
		Search_table.setRowHeight(50);
		Search_table.setBackground(new Color(21, 32, 43));
		Search_table.setForeground(new Color(255, 255, 255));
		Search_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		Search_table.setFont(new Font("굴림", Font.PLAIN, 20));
		Search_table.setBounds(106, 84, 280, 466);
		Search_table.setVisible(false);
		TableColumnModel columnModels = Search_table.getColumnModel();
		columnModels.getColumn(0).setPreferredWidth(-1);

		Search_table.getColumnModel().getColumn(0).setCellRenderer(new TableCell());
		Search_table.getColumnModel().getColumn(0).setCellEditor(new TableCell());
		East.add(Search_table);

		Search_field = new JTextField();
		Search_field.setFont(new Font("굴림", Font.PLAIN, 12));
		Search_field.setText("Search Twitter");
		Search_field.setBounds(106, 22, 280, 48);
		Search_field.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Search_field.setText("");
				Search_field.setFont(new Font("굴림", Font.PLAIN, 20));
			}

			public void mousePressed(MouseEvent e) {

			}

			public void mouseReleased(MouseEvent e) {

			}

			public void mouseEntered(MouseEvent e) {

			}

			public void mouseExited(MouseEvent e) {
				Search_field.setText("Search Twitter");
			}
		});
		Search_field.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String val = Search_field.getText();
				if (val.equals("")) {
					Search_table.setVisible(false);
				} else {
					TableRowSorter<TableModel> trs = new TableRowSorter<>(Search_table.getModel());
					Search_table.setRowSorter(trs);
					trs.setRowFilter(RowFilter.regexFilter(val));
					Search_table.setVisible(true);
				}
			}
		});
		East.add(Search_field);
		Search_field.setColumns(10);

		// center
		JPanel Center = new JPanel();
		Center.setBorder(new LineBorder(new Color(192, 192, 192)));
		Center.setBackground(new Color(21, 32, 43));
		Center.setBounds(391, 0, 604, 832);
		frame.getContentPane().add(Center);
		Center.setLayout(null);
		
		otherProfile_page = new JPanel();
		otherProfile_page.setBorder(new LineBorder(new Color(192, 192, 192)));
		otherProfile_page.setBackground(new Color(255, 255, 255));
		otherProfile_page.setBounds(0, 0, 604, 822);
		Center.add(otherProfile_page);
		otherProfile_page.setLayout(null);
		otherProfile_page.setVisible(false);
		
		JPanel Profile_page = new JPanel();
		Profile_page.setBorder(new LineBorder(new Color(192, 192, 192)));
		Profile_page.setLayout(null);
		Profile_page.setBackground(new Color(21, 32, 43));
		Profile_page.setBounds(0, 0, 604, 800);
		Center.add(Profile_page);
		Profile_page.setVisible(false);


		JPanel Home_page = new JPanel();
		Home_page.setBorder(new LineBorder(new Color(192, 192, 192)));
		Home_page.setForeground(new Color(192, 192, 192));
		Home_page.setBackground(new Color(21, 32, 43));
		Home_page.setBounds(0, 0, 604, 831);
		Center.add(Home_page);
		Home_page.setVisible(false);
		Home_page.setLayout(null);
		Home_page.setVisible(false);
		lastPanel = Home_page;
		lastPanel.setVisible(true);
		
		
		JPanel Home_posting = new JPanel();
		Home_posting.setBorder(new LineBorder(new Color(192, 192, 192)));
		Home_posting.setBackground(new Color(21, 32, 43));
		Home_posting.setBounds(0, 61, 604, 256);
		Home_page.add(Home_posting);
		ImageIcon img = new ImageIcon("./image/standard.png");
		JButton User = new JButton(size(img, 80, 60));
		User.setBounds(0, 10, 122, 64);
		Home_posting.setLayout(null);
		User.setBorderPainted(false);
		User.setContentAreaFilled(false);
		Home_posting.add(User);

		JButton Home_tweet = new JButton(new ImageIcon("./image/tweet_s.png"));
		Home_tweet.setBounds(448, 197, 144, 49);
		Home_tweet.setBorderPainted(false);
		Home_tweet.setContentAreaFilled(false);
		Home_posting.add(Home_tweet);

		String[] pp = { "public", "private" };
		JComboBox Public_private = new JComboBox(pp);
		Public_private.setBounds(122, 26, 65, 31);
		Public_private.setForeground(new Color(255, 255, 255));
		Public_private.setBackground(new Color(21, 32, 43));
		Public_private.setToolTipText("");
		Home_posting.add(Public_private);

		JTextArea Home_tweet_TA = new JTextArea();
		Home_tweet_TA.setForeground(new Color(192, 192, 192));
		Home_tweet_TA.setBounds(118, 84, 365, 108);
		Home_tweet_TA.setText("What's happening");
		Home_tweet_TA.setFont(new Font("굴림", Font.PLAIN, 30));
		Home_tweet_TA.setBackground(new Color(21, 32, 43));
		Home_tweet_TA.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Home_tweet_TA.setText("");
				Home_tweet_TA.setForeground(new Color(255, 255, 255));
			}

			public void mousePressed(MouseEvent e) {

			}

			public void mouseReleased(MouseEvent e) {

			}

			public void mouseEntered(MouseEvent e) {

			}

			public void mouseExited(MouseEvent e) {

			}
		});
		Home_posting.add(Home_tweet_TA);

		JLabel Home_label = new JLabel(" Home");
		Home_label.setBounds(0, 10, 604, 47);
		Home_page.add(Home_label);
		Home_label.setForeground(Color.WHITE);
		Home_label.setFont(new Font("굴림", Font.PLAIN, 30));
		Home_label.setBackground(Color.WHITE);

		JPanel Home_Tweet_Panel = new JPanel();
		Home_Tweet_Panel.setBackground(new Color(21, 32, 43));
		Home_Tweet_Panel.setBounds(1, 1, 602, 301);
		Home_Tweet_Panel.setLayout(Gbag);

		JScrollPane scrollHome = new JScrollPane(Home_Tweet_Panel);
		scrollHome.getVerticalScrollBar().setUnitIncrement(16);
		scrollHome.setBackground(new Color(21, 32, 43));
		scrollHome.setBounds(0, 316, 604, 489);
		Home_page.add(scrollHome);
		Home_Tweet_Panel.removeAll();
		C_H_P=0;
		ResultSet rs = db.getHomeTweet(user_number);
		try {
			while(rs.next()) {
				int t_id = rs.getInt(1);
				int w_id = rs.getInt(2);
				String c = rs.getString(3);
				String date = rs.getString(4);
				int n_like = rs.getInt(5);
				int n_retweet = rs.getInt(6);
				int n_reply = rs.getInt(7);
				int is_tweet = rs.getInt(8);
				int is_retweet = rs.getInt(9);
				int is_reply = rs.getInt(10);
				int o_id = rs.getInt(11);
				String location = rs.getString(12);
				data[C_H_P] = new JPanel();
				user[user_number].makeTweet(data[C_H_P], t_id, w_id, c, date, n_like, n_retweet, n_reply, is_tweet, is_retweet,
						is_reply, o_id, location, otherProfile_page, lastPanel);
				create_form(data[C_H_P], 0, (C_H_P++ + 1) * 300, 604, 300, Home_Tweet_Panel);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		

		JPanel Likes_Page = new JPanel();
		Likes_Page.setBorder(new LineBorder(new Color(192, 192, 192)));
		Likes_Page.setLayout(null);
		Likes_Page.setBackground(new Color(21, 32, 43));
		Likes_Page.setBounds(0, 0, 604, 800);
		Center.add(Likes_Page);
		Likes_Page.setVisible(false);

		JLabel lblNewLabel_2 = new JLabel(" Likes");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 30));
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setBounds(0, 10, 604, 68);
		Likes_Page.add(lblNewLabel_2);

		JPanel Likes_Panel = new JPanel();
		Likes_Panel.setBackground(new Color(255, 255, 255));
		Likes_Panel.setBounds(0, 0, 10, 10);
		Likes_Panel.setLayout(Gbag_EX);

		JScrollPane Likes_Scroll = new JScrollPane(Likes_Panel);
		Likes_Scroll.getVerticalScrollBar().setUnitIncrement(16);
		Likes_Scroll.setBackground(new Color(255, 255, 255));
		Likes_Scroll.setBounds(0, 150, 604, 650);
		Likes_Page.add(Likes_Scroll);

		JPanel Bookmark_page = new JPanel();
		Bookmark_page.setBorder(new LineBorder(new Color(192, 192, 192)));
		Bookmark_page.setLayout(null);
		Bookmark_page.setBackground(new Color(21, 32, 43));
		Bookmark_page.setBounds(0, 0, 604, 800);
		Center.add(Bookmark_page);
		Bookmark_page.setVisible(false);

		JLabel Bookmark_label = new JLabel(" Lists");
		Bookmark_label.setForeground(Color.WHITE);
		Bookmark_label.setFont(new Font("굴림", Font.PLAIN, 30));
		Bookmark_label.setBackground(Color.WHITE);
		Bookmark_label.setBounds(0, 10, 604, 68);
		Bookmark_page.add(Bookmark_label);

		JPanel List_Panel = new JPanel();
		List_Panel.setBackground(new Color(21, 32, 43));
		List_Panel.setBounds(0, 0, 10, 10);
		List_Panel.setLayout(Gbag_EX);
		
		JScrollPane Bookmark_Scroll = new JScrollPane(List_Panel);
		Bookmark_Scroll.getVerticalScrollBar().setUnitIncrement(16);
		Bookmark_Scroll.setBackground(new Color(21, 32, 43));
		Bookmark_Scroll.setBounds(0, 150, 604, 650);
		Bookmark_page.add(Bookmark_Scroll);

		JPanel Explore_page = new JPanel();
		Explore_page.setBorder(new LineBorder(new Color(192, 192, 192)));
		Explore_page.setLayout(null);
		Explore_page.setBackground(new Color(21, 32, 43));
		Explore_page.setBounds(0, 0, 604, 800);
		Center.add(Explore_page);
		Explore_page.setVisible(false);

		JLabel Explore_label = new JLabel(" Explore");
		Explore_label.setForeground(Color.WHITE);
		Explore_label.setFont(new Font("굴림", Font.PLAIN, 30));
		Explore_label.setBackground(Color.WHITE);
		Explore_label.setBounds(0, 10, 604, 68);
		Explore_page.add(Explore_label);

		JPanel Explore_Panel = new JPanel();
		Explore_Panel.setBackground(new Color(21, 32, 43));
		Explore_Panel.setBounds(1, 1, 602, 301);
		Explore_Panel.setLayout(Gbag_EX);


		JScrollPane Explore_Scroll = new JScrollPane(Explore_Panel);
		Explore_Scroll.getVerticalScrollBar().setUnitIncrement(16);
		Explore_Scroll.setBackground(new Color(21, 32, 43));
		Explore_Scroll.setBounds(0, 149, 604, 737);
		Explore_page.add(Explore_Scroll);

		// 기능할당
		Home_Icon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lastPanel.setVisible(false);
				Profile_page.setVisible(false);
				Home_page.setVisible(true);
				lastPanel = Home_page;
				otherProfile_page.setVisible(false);
			}
		});

		Home.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lastPanel.setVisible(false);
				Profile_page.setVisible(false);
				Home_page.setVisible(true);
				lastPanel = Home_page;
				otherProfile_page.setVisible(false);
				Home_Tweet_Panel.removeAll();
				C_H_P=0;
				ResultSet rs = db.getHomeTweet(user_number);
				try {
					while(rs.next()) {
						int t_id = rs.getInt(1);
						int w_id = rs.getInt(2);
						String c = rs.getString(3);
						String date = rs.getString(4);
						int n_like = rs.getInt(5);
						int n_retweet = rs.getInt(6);
						int n_reply = rs.getInt(7);
						int is_tweet = rs.getInt(8);
						int is_retweet = rs.getInt(9);
						int is_reply = rs.getInt(10);
						int o_id = rs.getInt(11);
						String location = rs.getString(12);
						data[C_H_P] = new JPanel();
						user[user_number].makeTweet(data[C_H_P], t_id, w_id, c, date, n_like, n_retweet, n_reply, is_tweet, is_retweet,
								is_reply, o_id, location, otherProfile_page, lastPanel);
						create_form(data[C_H_P], 0, (C_H_P++ + 1) * 300, 604, 300, Home_Tweet_Panel);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		Explore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lastPanel.setVisible(false);
				Explore_page.setVisible(true);
				lastPanel = Explore_page;
				otherProfile_page.setVisible(false);
				
				ResultSet Explore_Tweet = db.getExploreTweet();
				Explore_Panel.removeAll();
				C_E_P=0;
				try {
					while(Explore_Tweet.next()) {
						int t_id = Explore_Tweet.getInt(1);
						int w_id = Explore_Tweet.getInt(2);
						String c = Explore_Tweet.getString(3);
						String date = Explore_Tweet.getString(4);
						int n_like = Explore_Tweet.getInt(5);
						int n_retweet = Explore_Tweet.getInt(6);
						int n_reply = Explore_Tweet.getInt(7);
						int is_tweet = Explore_Tweet.getInt(8);
						int is_retweet = Explore_Tweet.getInt(9);
						int is_reply = Explore_Tweet.getInt(10);
						int o_id = Explore_Tweet.getInt(11);
						String location = Explore_Tweet.getString(12);
						exploreData[C_E_P] = new JPanel();
						user[user_number].makeTweet(exploreData[C_E_P], t_id, w_id, c, date, n_like, n_retweet, n_reply, 
								is_tweet, is_retweet, is_reply, o_id, location, otherProfile_page, Explore_page);
						create_form(exploreData[C_E_P], 0, (C_E_P++ + 1) * 300, 604, 300, Explore_Panel);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		Likes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lastPanel.setVisible(false);
				Likes_Page.setVisible(true);
				lastPanel = Likes_Page;
				otherProfile_page.setVisible(false);
				
				ResultSet Like_Tweet = db.getLikes();
				Likes_Panel.removeAll();
				C_L_P=0;
				try {
					while(Like_Tweet.next()) {
						int t_id = Like_Tweet.getInt(1);
						int w_id = Like_Tweet.getInt(2);
						String c = Like_Tweet.getString(3);
						String date = Like_Tweet.getString(4);
						int n_like = Like_Tweet.getInt(5);
						int n_retweet = Like_Tweet.getInt(6);
						int n_reply = Like_Tweet.getInt(7);
						int is_tweet = Like_Tweet.getInt(8);
						int is_retweet = Like_Tweet.getInt(9);
						int is_reply = Like_Tweet.getInt(10);
						int o_id = Like_Tweet.getInt(11);
						String location = Like_Tweet.getString(12);
						likeData[C_L_P] = new JPanel();
						user[user_number].makeTweet(likeData[C_L_P], t_id, w_id, c, date, n_like, n_retweet, n_reply, 
								is_tweet, is_retweet, is_reply, o_id, location, otherProfile_page, Likes_Page);
						create_form(likeData[C_L_P], 0, (C_L_P++ + 1) * 300, 604, 300, Likes_Panel);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		Bookmark.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lastPanel.setVisible(false);
				Bookmark_page.setVisible(true);
				lastPanel = Bookmark_page;
				otherProfile_page.setVisible(false);
				List_Panel.removeAll();
				C_B_P=0;
				ResultSet bookResult = db.getBookmark();
				try {
					while(bookResult.next()) {
						int t_id = bookResult.getInt(1);
						int w_id = bookResult.getInt(2);
						String c = bookResult.getString(3);
						String date = bookResult.getString(4);
						int n_like = bookResult.getInt(5);
						int n_retweet = bookResult.getInt(6);
						int n_reply = bookResult.getInt(7);
						int is_tweet = bookResult.getInt(8);
						int is_retweet = bookResult.getInt(9);
						int is_reply = bookResult.getInt(10);
						int o_id = bookResult.getInt(11);
						String location = bookResult.getString(12);
						bookData[C_B_P] = new JPanel();
						user[user_number].makeTweet(bookData[C_B_P], t_id, w_id, c, date, n_like, n_retweet, n_reply, is_tweet, is_retweet,
								is_reply, o_id, location, otherProfile_page, Bookmark_page);
						create_form(bookData[C_B_P], 0, (C_B_P++ + 1) * 300, 604, 300, List_Panel);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		Tweet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String str_content = Home_tweet_TA.getText();

			}
		});

		Profile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lastPanel.setVisible(false);
				Profile_page.removeAll();
				Profile_page.setVisible(true);
				lastPanel = Profile_page;
				Profile_page.add(user[user_number].profile(otherProfile_page, lastPanel));
				otherProfile_page.setVisible(false);
			}
		});
		
		Home_tweet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int count = db.getTweetCount();
				Home_Tweet_Panel.removeAll();
				C_H_P=0;
				String pp = Public_private.getSelectedItem().toString();
				String content = Home_tweet_TA.getText();
				db.makeTweet(count + 1, user_number, content, 0, 0, 0, count + 1, "Seoul");
				ResultSet rs = db.getHomeTweet(user_number);
				try {
					while(rs.next()) {
						int t_id = rs.getInt(1);
						int w_id = rs.getInt(2);
						String c = rs.getString(3);
						String date = rs.getString(4);
						int n_like = rs.getInt(5);
						int n_retweet = rs.getInt(6);
						int n_reply = rs.getInt(7);
						int is_tweet = rs.getInt(8);
						int is_retweet = rs.getInt(9);
						int is_reply = rs.getInt(10);
						int o_id = rs.getInt(11);
						String location = rs.getString(12);
						data[C_H_P] = new JPanel();
						user[user_number].makeTweet(data[C_H_P], t_id, w_id, c, date, n_like, n_retweet, n_reply, is_tweet, is_retweet, is_reply, 
								o_id, location, otherProfile_page, lastPanel);
						create_form(data[C_H_P], 0, (C_H_P++ + 1) * 300, 604, 300, Home_Tweet_Panel);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				Home_tweet_TA.setText("What's happening");
				Home_tweet_TA.setForeground(new Color(192, 192, 192));
				Home_tweet_TA.setFont(new Font("굴림", Font.PLAIN, 30));
			}
		});

		
		frame.setBounds(0, 0, 10000, 10000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

	}

	public void create_form(Component cmpt, int x, int y, int w, int h, JPanel panel) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		this.Gbag.setConstraints(cmpt, gbc);
		this.Gbag_EX.setConstraints(cmpt, gbc);
		panel.add(cmpt);
		panel.updateUI();
	}

	public ImageIcon size(ImageIcon img, int width, int height) {
		Image tmp = img.getImage();
		Image updateImg = tmp.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon updateIcon = new ImageIcon(updateImg);
		return updateIcon;
	}
	class TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer{

		JButton jb;
		
		public TableCell() {
			ImageIcon u_img = new ImageIcon("./image/standard.png");
			jb = new JButton(size(u_img,50,50));
			jb.setFont(new Font("굴림", Font.PLAIN, 20));
			jb.setForeground(new Color(255, 255, 255));
			jb.setBorderPainted(false);
			jb.setContentAreaFilled(false);
			jb.addActionListener(e -> {
				int f_id;
				String f_login, f_nickname;
				int x = Search_table.getSelectedRow();
				int y = 1;
				name = (String) Search_table.getValueAt(x, y);
				Search_table.setValueAt((Object)name, x, y);
				ResultSet rs = db.getUserWithNickName(name);
				System.out.println(Search_table.getValueAt(x, y));
				try {
					if(rs.next()) {
						f_id = rs.getInt(1);
						f_login = rs.getString(2);
						f_nickname = rs.getString(3);
						System.out.println(f_nickname);
						User other = new User(user_number, f_id, f_login, f_nickname, m);
						lastPanel.setVisible(false);
						otherProfile_page.removeAll();
						otherProfile_page.add(other.profile(otherProfile_page, lastPanel));
						otherProfile_page.setVisible(true);
						lastPanel = otherProfile_page;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			});
		}
		
		@Override
		public Object getCellEditorValue() {
			return null;
		}
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			// TODO Auto-generated method stub
			return jb;
		}
		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			return jb;
		}
		
	}
}
