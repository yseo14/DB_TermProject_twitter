package twitter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.LineBorder;


public class User extends JFrame {
	public Base m = null;
	private int login_user;
	private int id;
	private String login;
	private String nickname;
	private String desc;
	private String createdDate;
	private Database db = new Database();
	private int count_post = 0;
	int C_F_ING = 0;
	int C_F_ER = 0;
	int C_T = 0;
	int C_C = 0;
	int following_num;
	int follower_num;
	int reply_num;
	int like_num;
	int retweet_num;
	JPanel[] following_n = new JPanel[1000];
	JPanel[] follower_n = new JPanel[1000];
	JPanel[] tweet_n = new JPanel[1000];
	JPanel[] comment_n = new JPanel[1000];
	private GridBagLayout Gbag = new GridBagLayout();
	private JButton[] post = new JButton[count_post];
	private ImageIcon u_img = new ImageIcon("./image/standard.png");
	private ImageIcon f_img = new ImageIcon("./image/following.png");
	private ImageIcon uf_img = new ImageIcon("./image/unfollow.png");

	public User(int login_user, int user_number, String l_id, String l_name, Base m) {
		this.login_user = login_user;
		id = user_number;
		login = l_name;
		this.nickname = l_name;
		this.m = m;
	}

	public JPanel profile(JPanel otherProfile_page, JPanel lastPanel) {
		following_num = db.getFollowingNum(id);
		follower_num = db.getFollowerNum(id);
		nickname = db.getNick(id);
		desc = db.getDesc(id);
		createdDate = db.getCreated(id);
		otherProfile_page.removeAll();
		JPanel M = new JPanel();
		M.setBorder(new LineBorder(new Color(192, 192, 192)));
		M.setBackground(new Color(0, 0, 64));
		M.setBounds(0, 0, 604, 845);
		M.setLayout(null);

		JLabel profileImageLabel = new JLabel();
		profileImageLabel.setForeground(new Color(255, 255, 255));
		profileImageLabel.setBounds(25, 108, 163, 128);
		profileImageLabel.setIcon(size(u_img,50,50));

		M.add(profileImageLabel);

		JPanel P_panel = new JPanel();
		P_panel.setBorder(new LineBorder(new Color(192, 192, 192)));
		P_panel.setBackground(new Color(21, 32, 43));
		P_panel.setBounds(0, 173, 603, 238);
		M.add(P_panel);
		P_panel.setLayout(null);

		JButton editButton = new JButton("Edit profile");
		editButton.setBounds(448, 10, 144, 23);
		P_panel.add(editButton);
		editButton.setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		editButton.setForeground(new Color(255, 255, 255));
		editButton.setBackground(new Color(21, 32, 43));

		JButton followButton = new JButton("Follow");
		followButton.setBounds(448, 10, 144, 23);
		P_panel.add(followButton);
		followButton.setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		followButton.setForeground(new Color(255, 255, 255));
		followButton.setBackground(new Color(21, 32, 43));

		JButton unfollowButton = new JButton("Unfollow");
		unfollowButton.setBounds(448, 10, 144, 23);
		P_panel.add(unfollowButton);
		unfollowButton.setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		unfollowButton.setForeground(new Color(255, 255, 255));
		unfollowButton.setBackground(new Color(21, 32, 43));

		if (db.checkFollowing(login_user, getNumber()) == 1) {
			if (login_user == getNumber()) {
				editButton.setVisible(true);
				unfollowButton.setVisible(false);
				followButton.setVisible(false);
			} else {
				unfollowButton.setVisible(true);
				editButton.setVisible(false);
				followButton.setVisible(false);
			}
		} else {
			if (login_user == getNumber()) {
				editButton.setVisible(true);
				followButton.setVisible(false);
				unfollowButton.setVisible(false);
			} else {
				followButton.setVisible(true);
				editButton.setVisible(false);
				unfollowButton.setVisible(false);
			}
		}

		JLabel nameLabel = new JLabel(nickname);
		nameLabel.setBackground(new Color(64, 0, 64));
		nameLabel.setBounds(12, 65, 580, 25);
		P_panel.add(nameLabel);
		nameLabel.setFont(new Font("HY헤드라인M", Font.PLAIN, 21));
		nameLabel.setForeground(new Color(255, 255, 255));

		JLabel introLabel = new JLabel(desc);
		introLabel.setBounds(12, 100, 580, 25);
		P_panel.add(introLabel);
		introLabel.setForeground(new Color(255, 255, 255));
		introLabel.setFont(new Font("HY헤드라인M", Font.PLAIN, 13));

		JLabel creartedDateLabel = new JLabel(createdDate);
		creartedDateLabel.setBackground(new Color(21, 32, 43));
		creartedDateLabel.setBounds(20, 161, 170, 19);
		P_panel.add(creartedDateLabel);
		creartedDateLabel.setFont(new Font("HY헤드라인M", Font.PLAIN, 13));
		creartedDateLabel.setForeground(SystemColor.controlShadow);

		JLabel calendar = new JLabel();
		calendar.setIcon(new ImageIcon());
		calendar.setBounds(16, 157, 31, 23);
		P_panel.add(calendar);

		JLabel following_count = new JLabel(following_num + " ");
		following_count.setFont(new Font("HY헤드라인M", Font.PLAIN, 16));
		following_count.setBounds(230, 157, 152, 27);
		following_count.setForeground(new Color(255, 255, 255));
		P_panel.add(following_count);

		JButton following = new JButton("Following");
		following.setFont(new Font("HY헤드라인M", Font.PLAIN, 16));
		following.setForeground(new Color(255, 255, 255));
		following.setBounds(215, 157, 152, 27);
		following.setBorderPainted(false);
		following.setContentAreaFilled(false);
		P_panel.add(following);

		JLabel follower_count = new JLabel(follower_num + " ");
		follower_count.setFont(new Font("HY헤드라인M", Font.PLAIN, 16));
		follower_count.setBounds(432, 157, 152, 27);
		follower_count.setForeground(new Color(255, 255, 255));
		P_panel.add(follower_count);

		JButton follower = new JButton("Follower");
		follower.setFont(new Font("HY헤드라인M", Font.PLAIN, 16));
		follower.setForeground(new Color(255, 255, 255));
		follower.setBounds(417, 159, 152, 23);
		follower.setBorderPainted(false);
		follower.setContentAreaFilled(false);
		P_panel.add(follower);

		JButton tweet = new JButton("Tweet");
		tweet.setFont(new Font("HY헤드라인M", Font.PLAIN, 16));
		tweet.setForeground(new Color(255, 255, 255));
		tweet.setBackground(new Color(21, 32, 43));
		tweet.setBounds(12, 204, 580, 34);
		tweet.setBorderPainted(false);
		tweet.setContentAreaFilled(false);
		P_panel.add(tweet);

		JPanel followingList = new JPanel();
		followingList.setBackground(new Color(21, 32, 43));
		followingList.setBounds(1, 408, 604, 451);
		followingList.setLayout(Gbag);

		JScrollPane Following_scrollPane = new JScrollPane(followingList);
		Following_scrollPane.setBounds(0, 408, 604, 450);
		Following_scrollPane.setVisible(true);
		M.add(Following_scrollPane);

		JPanel followerList = new JPanel();
		followerList.setBackground(new Color(21, 32, 43));
		followerList.setBounds(1, 408, 604, 451);
		followerList.setLayout(Gbag);

		JScrollPane Follower_scrollPane = new JScrollPane(followerList);
		Follower_scrollPane.setBounds(0, 408, 604, 450);
		Follower_scrollPane.setVisible(true);
		M.add(Follower_scrollPane);

		JPanel tweetList = new JPanel();
		tweetList.setBackground(new Color(21, 32, 43));
		tweetList.setBounds(1, 408, 604, 451);
		tweetList.setLayout(Gbag);

		JScrollPane Tweet_scrollPane = new JScrollPane(tweetList);
		Tweet_scrollPane.setBounds(0, 408, 604, 450);
		Tweet_scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		Tweet_scrollPane.setVisible(true);
		M.add(Tweet_scrollPane);

		following.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = db.getFollowing(id);
				int f_id;
				String f_login;
				String f_nickname;
				try {
					followingList.removeAll();
					C_F_ING=0;
					while (rs.next()) {
						f_id = rs.getInt(1);
						f_login = rs.getString(2);
						f_nickname = rs.getString(3);
						following_n[C_F_ING] = new JPanel();
						getFollowing(following_n[C_F_ING], f_nickname, f_login, f_id, otherProfile_page, lastPanel);
						create_form(following_n[C_F_ING], 0, (C_F_ING++ + 1) * 300, 604, 300, followingList);
						C_F_ING++;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				Follower_scrollPane.setVisible(false);
				Following_scrollPane.setVisible(true);
				Tweet_scrollPane.setVisible(false);
			}
		});

		follower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = db.getFollower(id);
				int f_id;
				String f_login;
				String f_nickname;
				try {
					followerList.removeAll();
					C_F_ING=0;
					while (rs.next()) {
						f_id = rs.getInt(1);
						f_login = rs.getString(2);
						f_nickname = rs.getString(3);
						follower_n[C_F_ING] = new JPanel();
						getFollower(follower_n[C_F_ING], f_nickname, f_login, f_id, otherProfile_page, lastPanel);
						create_form(follower_n[C_F_ING], 0, (C_F_ER++ + 1) * 300, 604, 300, followerList);
						C_F_ER++;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				Following_scrollPane.setVisible(false);
				Follower_scrollPane.setVisible(true);
				Tweet_scrollPane.setVisible(false);
			}
		});

		tweet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Following_scrollPane.setVisible(false);
				Follower_scrollPane.setVisible(false);
				Tweet_scrollPane.setVisible(true);
				ResultSet rs = db.getUserTweet(id);
				tweetList.removeAll();
				C_T=0;
				try {
					while (rs.next()) {
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
						tweet_n[C_T] = new JPanel();
						makeTweet(tweet_n[C_T], t_id, w_id, c, date, n_like, n_retweet, n_reply, is_tweet, is_retweet,
								is_reply, o_id, location, otherProfile_page, lastPanel);
						create_form(tweet_n[C_T], 0, (C_T++ + 1) * 300, 604, 300, tweetList);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditWindow a = new EditWindow(m);
				a.frame.setVisible(true);
				
			}
		});

		followButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db.followFunction(getNumber(), login_user);
				followButton.setVisible(false);
				unfollowButton.setVisible(true);
				following_num++;
			}
		});

		unfollowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db.unfollowFunction(getNumber(), login_user);
				unfollowButton.setVisible(false);
				followButton.setVisible(true);
				following_num--;
			}
		});
		return M;
	}

	public void getFollowing(JPanel following_list, String f_nickname, String login_id, int f_id,
			JPanel otherProfile_page, JPanel lastPanel) {
		following_list.setBorder(new LineBorder(new Color(192, 192, 192)));
		following_list.setBackground(new Color(21, 32, 43));
		following_list.setPreferredSize(new Dimension(604, 80));
		following_list.setLayout(new BorderLayout(0, 0));

		JButton profile_btn = new JButton(f_nickname, size(u_img, 80, 80));
		profile_btn.setFont(new Font("HY헤드라인M", Font.PLAIN, 22));
		profile_btn.setForeground(new Color(255, 255, 255));
		profile_btn.setBackground(new Color(21, 32, 43));
		profile_btn.setBorderPainted(false);
		profile_btn.setContentAreaFilled(false);
		following_list.add(profile_btn, BorderLayout.WEST);

		JButton unfollow_btn = new JButton(size(uf_img, 120, 60));
		unfollow_btn.setBorderPainted(false);
		unfollow_btn.setContentAreaFilled(false);
		unfollow_btn.setVisible(true);

		JButton following_btn = new JButton(size(f_img, 120, 60));
		following_btn.setBorderPainted(false);
		following_btn.setContentAreaFilled(false);
		following_btn.setVisible(false);

		if (login_user == id) {
			if (db.checkFollowing(login_user, f_id) == 1) {
				following_list.add(unfollow_btn, BorderLayout.EAST);
			} else {
				following_list.add(following_btn, BorderLayout.EAST);
			}
		}
		profile_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				User other = new User(login_user, f_id, login_id, f_nickname, m);
				System.out.println(f_nickname);
				lastPanel.setVisible(false);
				otherProfile_page.removeAll();
				otherProfile_page.add(other.profile(otherProfile_page, lastPanel));
				otherProfile_page.setVisible(true);
				otherProfile_page.repaint();
				lastPanel.repaint();
			}
		});

		following_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 팔로잉 취소 기능 추가하세요
				db.followFunction(f_id, login_user);
				following_btn.setVisible(false);
				unfollow_btn.setVisible(true);
				following_list.add(unfollow_btn, BorderLayout.EAST);
			}
		});

		unfollow_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("unfollow");
				db.unfollowFunction(f_id, login_user);
				following_btn.setVisible(true);
				unfollow_btn.setVisible(false);
				following_list.add(following_btn, BorderLayout.EAST);
			}
		});
	}

	public void getFollower(JPanel follower_list, String f_nickname, String login_id, int f_id,
			JPanel otherProfile_page, JPanel lastPanel) {
		follower_list.setBorder(new LineBorder(new Color(192, 192, 192)));
		follower_list.setBackground(new Color(21, 32, 43));
		follower_list.setPreferredSize(new Dimension(604, 80));
		follower_list.setLayout(new BorderLayout(0, 0));

		JButton profile_btn = new JButton(f_nickname, size(u_img, 80, 80));
		profile_btn.setForeground(new Color(255, 255, 255));
		profile_btn.setBackground(new Color(21, 32, 43));
		profile_btn.setFont(new Font("HY헤드라인M", Font.PLAIN, 22));
		profile_btn.setBorderPainted(false);
		profile_btn.setContentAreaFilled(false);
		follower_list.add(profile_btn, BorderLayout.WEST);

		// JDBC 함수가져와서 rs 뽑아서

		JButton following_btn = new JButton(size(f_img, 120, 60));
		following_btn.setBorderPainted(false);
		following_btn.setContentAreaFilled(false);

		JButton unfollow_btn = new JButton(size(uf_img, 120, 60));
		unfollow_btn.setBorderPainted(false);
		unfollow_btn.setContentAreaFilled(false);

		if (login_user == id) {
			if (db.checkFollowing(login_user, f_id) == 1) {
				follower_list.add(unfollow_btn, BorderLayout.EAST);
			} else {
				follower_list.add(following_btn, BorderLayout.EAST);
			}
		}

		profile_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				User other = new User(login_user, f_id, login_id, f_nickname, m);
				lastPanel.setVisible(false);
				otherProfile_page.removeAll();
				otherProfile_page.add(other.profile(otherProfile_page, lastPanel));
				otherProfile_page.setVisible(true);
				otherProfile_page.repaint();
				lastPanel.repaint();
			}
		});

		following_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 팔로잉 취소 기능 추가하세요
				db.followFunction(f_id, login_user);
				following_btn.setVisible(false);
				follower_list.add(unfollow_btn, BorderLayout.EAST);
			}
		});

		unfollow_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 팔로잉 추가 기능 추가하세요
				db.unfollowFunction(f_id, login_user);
				unfollow_btn.setVisible(false);
				follower_list.add(following_btn, BorderLayout.EAST);
			}
		});
	}

	public void makeTweet(JPanel data, int t_id, int w_id, String c, String date, int n_like, int n_retweet,
			int n_reply, int is_tweet, int is_retweet, int is_reply, int o_id, String location,
			JPanel otherProfile_page, JPanel lastPanel) {

		int count_comment = db.countReply(t_id);
		int count_like = db.countLike(t_id);
		int count_retweet = db.countRetweet(t_id);
		
		String log_id = db.getId(w_id);
		String nickname = db.getName(w_id);
		
		data.setPreferredSize(new Dimension(604, 300));
		data.setBackground(new Color(21, 32, 43));
		data.setLayout(new BorderLayout(0, 0));
		data.setBorder(new LineBorder(new Color(192, 192, 192)));

		JPanel Panel_T = new JPanel();
		Panel_T.setBackground(new Color(21, 32, 43));

		data.add(Panel_T, BorderLayout.NORTH);
		Panel_T.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		ImageIcon img = new ImageIcon("./Image/standard.png");
		JButton User = new JButton(size(img, 80, 60));
		User.setBorderPainted(false);
		User.setContentAreaFilled(false);
		Panel_T.add(User);

		JButton Name = new JButton(nickname);
		Panel_T.add(Name);
		Name.setFont(new Font("굴림", Font.PLAIN, 28));
		Name.setForeground(new Color(255, 255, 255));
		Name.setBorderPainted(false);
		Name.setContentAreaFilled(false);
		Name.setHorizontalAlignment(SwingConstants.LEFT);
		Name.setBounds(100, 20, 300, 41);
		
		JButton delete = new JButton("                                         DELETE");
		delete.setFont(new Font("굴림", Font.PLAIN, 15));
		delete.setForeground(new Color(255, 255, 255));
		delete.setBorderPainted(false);
		delete.setContentAreaFilled(false);
		delete.setHorizontalAlignment(SwingConstants.RIGHT);
		if(w_id == m.db.userId) {
			Panel_T.add(delete);
		}

		JButton Content = new JButton(c);
		Content.setBackground(new Color(21, 32, 43));
		Content.setFont(new Font("굴림", Font.PLAIN, 28));
		Content.setBorderPainted(false);
		Content.setContentAreaFilled(false);
		Content.setForeground(new Color(255, 255, 255));
		Content.setHorizontalAlignment(SwingConstants.CENTER);
		Content.setBounds(86, 69, 500, 173);
		data.add(Content, BorderLayout.CENTER);

		JPanel panel_S = new JPanel();
		panel_S.setBackground(new Color(21, 32, 43));
		panel_S.setPreferredSize(new Dimension(604, 50));
		data.add(panel_S, BorderLayout.SOUTH);
		panel_S.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		ImageIcon img2 = new ImageIcon("./image/comment.png");
		JButton Comment = new JButton(count_comment + "     ", size(img2, 40, 40));
		Comment.setFont(new Font("굴림", Font.PLAIN, 28));
		Comment.setForeground(new Color(255, 255, 255));
		Comment.setBorderPainted(false);
		Comment.setContentAreaFilled(false);
		panel_S.add(Comment);

		ImageIcon img3 = new ImageIcon("./image/retweet.png");
		JButton Retweet = new JButton(count_retweet + "     ", size(img3, 40, 40));
		Retweet.setFont(new Font("굴림", Font.PLAIN, 28));
		Retweet.setForeground(new Color(255, 255, 255));
		Retweet.setBorderPainted(false);
		Retweet.setContentAreaFilled(false);
		panel_S.add(Retweet);

		ImageIcon img4 = new ImageIcon("./image/likes.png");
		JButton Like = new JButton(count_like + "     ", size(img4, 40, 40));
		Like.setFont(new Font("굴림", Font.PLAIN, 28));
		Like.setForeground(new Color(255, 255, 255));
		Like.setBorderPainted(false);
		Like.setContentAreaFilled(false);
		panel_S.add(Like);

		ImageIcon img5 = new ImageIcon("./image/bookmark.png");
		JButton BookMark = new JButton("", size(img5, 40, 40));
		BookMark.setBorderPainted(false);
		BookMark.setContentAreaFilled(false);
		panel_S.add(BookMark);

		User.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				User other = new User(login_user, w_id, log_id, nickname, m);
				lastPanel.setVisible(false);
				otherProfile_page.add(other.profile(otherProfile_page, lastPanel));
				otherProfile_page.setVisible(true);
				otherProfile_page.repaint();
				lastPanel.repaint();
			}
		});

		Content.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lastPanel.setVisible(false);
				otherProfile_page.add(largeTweetPanel(t_id, w_id, c, otherProfile_page, lastPanel));
				otherProfile_page.setVisible(true);
				otherProfile_page.repaint();
				lastPanel.repaint();
			}
		});
		
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				db.deleteTweet(t_id);
			}
		});
		
		Comment.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lastPanel.setVisible(false);
				Comment.setText(count_comment + "     ");
				otherProfile_page.add(largeTweetPanel(t_id, w_id, c, otherProfile_page, lastPanel));
				otherProfile_page.setVisible(true);
				otherProfile_page.repaint();
				lastPanel.repaint();
			}
		});
		
		Like.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Like.setText(count_like + "     ");
				if(db.checkLikes(t_id) == 1) {
					db.likeFunction(t_id);
				}else {
					db.unLikeFunction(t_id);
				}
			}
		});
		
		BookMark.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				db.bookmarkFunction(t_id);
			}
		});
		
		Retweet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Retweet.setText(count_retweet + "     ");
				db.retweetFunction(t_id);
			}
		});
	}

	public JPanel largeTweetPanel(int t_id, int w_id, String c, JPanel otherProfile_page, JPanel lastPanel) {
		reply_num = db.countReply(t_id);
		like_num = db.countLike(t_id);
		retweet_num = db.countRetweet(t_id);
		nickname = db.getNick(w_id);
		System.out.println(w_id + " " + nickname);
		otherProfile_page.removeAll();
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(192, 192, 192)));
		panel.setBackground(new Color(21, 32, 43));
		panel.setBounds(0,0,604,822);
		panel.setLayout(null);

		JPanel postPanel = new JPanel();
		postPanel.setBorder(new LineBorder(new Color(192, 192, 192)));
		postPanel.setBounds(0, 0, 602, 446);
		postPanel.setBackground(new Color(21, 32, 43));
		panel.add(postPanel);
		postPanel.setLayout(null);

		JButton user = new JButton(size(u_img, 70, 60));
		user.setBounds(0, 0, 137, 137);
		user.setForeground(new Color(255, 255, 255));
		user.setBorderPainted(false);
		user.setContentAreaFilled(false);
		postPanel.add(user);
		
		JLabel name = new JLabel(nickname);
		name.setFont(new Font("굴림", Font.PLAIN, 28));
		name.setBounds(136, 10, 234, 96);
		name.setForeground(new Color(255, 255, 255));
		postPanel.add(name);
		
		JLabel content = new JLabel(c);
		content.setBounds(71, 106, 531, 258);
		content.setFont(new Font("굴림", Font.PLAIN, 28));
		content.setForeground(new Color(255, 255, 255));
		postPanel.add(content);
		
		ImageIcon img2 = new ImageIcon("./image/bookmark.png");
		JButton bookmark = new JButton(size(img2,40,40));
		bookmark.setBorderPainted(false);
		bookmark.setContentAreaFilled(false);
		bookmark.setBounds(459, 400, 65, 38);
		postPanel.add(bookmark);
		
		ImageIcon img3 = new ImageIcon("./image/likes.png");
		JButton like = new JButton(size(img3,40,40));
		like.setBorderPainted(false);
		like.setContentAreaFilled(false);
		like.setBounds(310, 400, 65, 38);
		postPanel.add(like);
		
		ImageIcon img4 = new ImageIcon("./image/retweet.png");
		JButton retweet = new JButton(size(img4,40,40));
		retweet.setBorderPainted(false);
		retweet.setContentAreaFilled(false);
		retweet.setBounds(161, 400, 65, 38);
		postPanel.add(retweet);
		
		ImageIcon img5 = new ImageIcon("./image/comment.png");
		JButton comment = new JButton(size(img5,40,40));
		comment.setBorderPainted(false);
		comment.setContentAreaFilled(false);
		comment.setBounds(12, 400, 65, 38);
		postPanel.add(comment);
		
		JLabel c_comment = new JLabel(reply_num + "");
		c_comment.setFont(new Font("굴림", Font.PLAIN, 20));
		c_comment.setForeground(new Color(255, 255, 255));
		c_comment.setBounds(78, 400, 59, 38);
		postPanel.add(c_comment);
		
		JLabel c_retweet = new JLabel(retweet_num + "");
		c_retweet.setFont(new Font("굴림", Font.PLAIN, 20));
		c_retweet.setForeground(new Color(255, 255, 255));
		c_retweet.setBounds(227, 400, 59, 38);
		postPanel.add(c_retweet);
		
		JLabel c_likes = new JLabel(like_num + "");
		c_likes.setFont(new Font("굴림", Font.PLAIN, 20));
		c_likes.setForeground(new Color(255, 255, 255));
		c_likes.setBounds(376, 400, 59, 38);
		postPanel.add(c_likes);
		
		JLabel c_bookmark = new JLabel("2");
		c_bookmark.setFont(new Font("굴림", Font.PLAIN, 20));
		c_bookmark.setForeground(new Color(255, 255, 255));
		c_bookmark.setBounds(525, 400, 59, 38);
		postPanel.add(c_bookmark);
		
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new LineBorder(new Color(192, 192, 192)));
		contentPanel.setBackground(new Color(21, 32, 43));
		contentPanel.setBounds(0, 559, 602, 241);
		panel.add(contentPanel);
		contentPanel.setLayout(null);
		
		String createdD = db.getCreatedTweet(t_id);
		JLabel Date = new JLabel(createdD);
		Date.setFont(new Font("굴림",Font.PLAIN, 20));
		Date.setForeground(new Color(255, 255, 255));
		Date.setBounds(12,300,200,120);
		postPanel.add(Date);
		
		JPanel commentListPanel = new JPanel();
		commentListPanel.setBackground(new Color(21,32,43));
		commentListPanel.setBounds(1,1,602,0);
		commentListPanel.setLayout(Gbag);
		
		JScrollPane commentScroll =new JScrollPane(commentListPanel);
		commentScroll.getVerticalScrollBar().setUnitIncrement(16);
		commentScroll.setBackground(new Color(21, 32, 43));
		commentScroll.setBounds(0,0,602,334);
		contentPanel.add(commentScroll);

		commentListPanel.removeAll();
		C_C = 0;
		ResultSet rs = db.getComment(t_id);
		try {
			while (rs.next()) {
				int tweet_id = rs.getInt(1);
				int write_id = rs.getInt(2);
				String comments = rs.getString(3);
				String date = rs.getString(4);
				int n_like = rs.getInt(5);
				int n_retweet = rs.getInt(6);
				int n_reply = rs.getInt(7);
				int is_tweet = rs.getInt(8);
				int is_retweet = rs.getInt(9);
				int is_reply = rs.getInt(10);
				int o_id = rs.getInt(11);
				String location = rs.getString(12);
				comment_n[C_C] = new JPanel();
				makeReply(comment_n[C_C], tweet_id, write_id, comments, date, n_like, n_retweet, n_reply, is_tweet, is_retweet,
						is_reply, o_id, location, otherProfile_page, lastPanel);
				create_form(comment_n[C_C], 0, (C_C++ + 1) * 150, 604, 150, commentListPanel);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		JPanel reply = new JPanel();
		reply.setBackground(new Color(21, 32, 43));
		reply.setBorder(new LineBorder(new Color(192, 192, 192)));
		reply.setBounds(0, 442, 604, 117);
		panel.add(reply);
		reply.setLayout(null);
		
		JButton user_p = new JButton(size(u_img, 50, 50));
		user_p.setBounds(12, 10, 84, 55);
		user_p.setBorderPainted(false);
		user_p.setContentAreaFilled(false);
		reply.add(user_p);
		
		ImageIcon img7 = new ImageIcon("./image/reply.png");
		JButton Reply_accept = new JButton(size(img7,100,50));
		Reply_accept.setBorderPainted(false);
		Reply_accept.setContentAreaFilled(false);
		Reply_accept.setBounds(480, 75, 112, 39);
		reply.add(Reply_accept);
		
		JTextArea comment_Text = new JTextArea();
		comment_Text.setBounds(108, 31, 365, 48);
		comment_Text.setForeground(new Color(192, 192, 192));
		comment_Text.setText("Tweet your reply");
		comment_Text.setFont(new Font("굴림", Font.PLAIN, 25));
		comment_Text.setBackground(new Color(21, 32, 43));
		comment_Text.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				comment_Text.setText("");
				comment_Text.setForeground(new Color(255, 255, 255));
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
		reply.add(comment_Text);
		
		user.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				User other = new User(login_user, w_id, login, nickname, m);
				lastPanel.setVisible(false);
				otherProfile_page.add(other.profile(otherProfile_page, lastPanel));
				otherProfile_page.setVisible(true);
				otherProfile_page.repaint();
				lastPanel.repaint();
			}
		});
		
		Reply_accept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int count = db.getTweetCount();
				String content = comment_Text.getText();
				db.makeComment(count + 1, login_user, content, 0, 0, 0, t_id, "Seoul");
				ResultSet rs = db.getComment(t_id);
				commentListPanel.removeAll();
				C_C=0;
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
						comment_n[C_C] = new JPanel();
						makeReply(comment_n[C_C], t_id, w_id, c, date, n_like, n_retweet, n_reply, is_tweet, is_retweet, is_reply, 
								o_id, location, otherProfile_page, lastPanel);
						create_form(comment_n[C_C], 0, (C_C++ + 1) * 150, 604, 150, commentListPanel);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				reply_num++;
				c_comment.setText(reply_num + "");
			}
		});
		
		user_p.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				User other = new User(login_user, w_id, login, nickname, m);
				lastPanel.setVisible(false);
				otherProfile_page.add(other.profile(otherProfile_page, lastPanel));
				otherProfile_page.setVisible(true);
				otherProfile_page.repaint();
				lastPanel.repaint();
			}
		});
		
		return panel;
	}
	
	public void makeReply(JPanel data, int t_id, int w_id, String c, String date, int n_like, int n_retweet,
			int n_reply, int is_tweet, int is_retweet, int is_reply, int o_id, String location,
			JPanel otherProfile_page, JPanel lastPanel) {

		String log_id = db.getId(w_id);
		String nickname = db.getName(w_id);
		data.setPreferredSize(new Dimension(604, 150));
		data.setBackground(new Color(21, 32, 43));
		data.setLayout(new BorderLayout(0, 0));
		data.setBorder(new LineBorder(new Color(192, 192, 192)));

		JPanel Panel_T = new JPanel();
		Panel_T.setBackground(new Color(21, 32, 43));

		data.add(Panel_T, BorderLayout.NORTH);
		Panel_T.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		ImageIcon img = new ImageIcon("./Image/standard.png");
		JButton User = new JButton(size(img, 80, 60));
		User.setBorderPainted(false);
		User.setContentAreaFilled(false);
		Panel_T.add(User);

		JButton Name = new JButton(nickname);
		Panel_T.add(Name);
		Name.setFont(new Font("굴림", Font.PLAIN, 28));
		Name.setForeground(new Color(255, 255, 255));
		Name.setBorderPainted(false);
		Name.setContentAreaFilled(false);
		Name.setHorizontalAlignment(SwingConstants.LEFT);
		Name.setBounds(100, 20, 300, 41);

		JButton delete = new JButton("                                         DELETE");
		delete.setFont(new Font("굴림", Font.PLAIN, 15));
		delete.setForeground(new Color(255, 255, 255));
		delete.setBorderPainted(false);
		delete.setContentAreaFilled(false);
		delete.setHorizontalAlignment(SwingConstants.RIGHT);
		if(w_id == m.db.userId) {
			Panel_T.add(delete);
		}
		
		JButton Content = new JButton(c);
		Content.setBackground(new Color(21, 32, 43));
		Content.setFont(new Font("굴림", Font.PLAIN, 20));
		Content.setBorderPainted(false);
		Content.setContentAreaFilled(false);
		Content.setForeground(new Color(255, 255, 255));
		Content.setHorizontalAlignment(SwingConstants.CENTER);
		Content.setBounds(86, 69, 500, 173);
		data.add(Content, BorderLayout.CENTER);

		JPanel panel_S = new JPanel();
		panel_S.setBackground(new Color(21, 32, 43));
		panel_S.setPreferredSize(new Dimension(604, 50));
		data.add(panel_S, BorderLayout.SOUTH);
		panel_S.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		ImageIcon img2 = new ImageIcon("./image/comment.png");
		JButton Comment = new JButton("           ", size(img2, 40, 40));
		Comment.setBorderPainted(false);
		Comment.setContentAreaFilled(false);
		panel_S.add(Comment);

		ImageIcon img3 = new ImageIcon("./image/retweet.png");
		JButton Retweet = new JButton("          ", size(img3, 40, 40));
		Retweet.setBorderPainted(false);
		Retweet.setContentAreaFilled(false);
		panel_S.add(Retweet);

		ImageIcon img4 = new ImageIcon("./image/likes.png");
		JButton Like = new JButton("           ", size(img4, 40, 40));
		Like.setBorderPainted(false);
		Like.setContentAreaFilled(false);
		panel_S.add(Like);

		ImageIcon img5 = new ImageIcon("./image/bookmark.png");
		JButton BookMark = new JButton("           ", size(img5, 40, 40));
		BookMark.setBorderPainted(false);
		BookMark.setContentAreaFilled(false);
		panel_S.add(BookMark);
		

		User.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				User other = new User(login_user, w_id, log_id, nickname, m);
				lastPanel.setVisible(false);
				otherProfile_page.add(other.profile(otherProfile_page, lastPanel));
				otherProfile_page.setVisible(true);
				otherProfile_page.repaint();
				lastPanel.repaint();
			}
		});

		Content.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lastPanel.setVisible(false);
				System.out.println(w_id + " "  + c);
				otherProfile_page.add(largeTweetPanel(t_id, w_id, c, otherProfile_page, lastPanel));
				otherProfile_page.setVisible(true);
				otherProfile_page.repaint();
				lastPanel.repaint();
			}
		});
		
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				db.deleteTweet(t_id);
			}
		});
		
		Comment.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lastPanel.setVisible(false);
				System.out.println(w_id + " "  + c);
				otherProfile_page.add(largeTweetPanel(t_id, w_id, c, otherProfile_page, lastPanel));
				otherProfile_page.setVisible(true);
				otherProfile_page.repaint();
				lastPanel.repaint();
			}
		});
	}
	
	public void create_form(Component cmpt, int x, int y, int w, int h, JPanel panel) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		this.Gbag.setConstraints(cmpt, gbc);

		panel.add(cmpt);
		panel.updateUI();
	}

	public ImageIcon size(ImageIcon img, int width, int height) {
		Image tmp = img.getImage();
		Image updateImg = tmp.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon updateIcon = new ImageIcon(updateImg);
		return updateIcon;
	}

	public int getNumber() {
		return id;
	}

	public String getId() {
		return login;
	}


}
