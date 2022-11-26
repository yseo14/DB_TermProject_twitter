package twitter;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class tmp {
	
	private GridBagLayout Gbag = new GridBagLayout();
	private void getComment(JPanel panel) {
		JPanel Tweet_Panel = new JPanel();
		Tweet_Panel.setBackground(new Color(21, 32, 43));
		Tweet_Panel.setBounds(287, 0, 604, 770);
		panel.add(Tweet_Panel);
		Tweet_Panel.setLayout(null);
		
		JLabel Tweet = new JLabel("Tweet");
		Tweet.setForeground(new Color(255, 255, 255));
		Tweet.setFont(new Font("굴림", Font.PLAIN, 30));
		Tweet.setBackground(new Color(255, 255, 255));
		Tweet.setBounds(12, 10, 592, 109);
		Tweet_Panel.add(Tweet);
		
		JPanel Content_Panel = new JPanel();
		Content_Panel.setBackground(new Color(21, 32, 43));
		Content_Panel.setBounds(0, 113, 604, 299);
		Tweet_Panel.add(Content_Panel);
		Content_Panel.setLayout(null);
		
		ImageIcon img = new ImageIcon("./Image/standard.png");
		JButton user = new JButton(size(img, 80, 70));
		user.setBorderPainted(false);
		user.setContentAreaFilled(false);
		user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		user.setBounds(12, 10, 91, 72);
		Content_Panel.add(user);
		

		JLabel Name = new JLabel("New label");
		Name.setForeground(new Color(255, 255, 255));
		Name.setFont(new Font("굴림", Font.PLAIN, 28));
		Name.setBounds(115, 14, 207, 33);
		Content_Panel.add(Name);
		

		JLabel Content = new JLabel("New label");
		Content.setForeground(new Color(255, 255, 255));
		Content.setFont(new Font("굴림", Font.PLAIN, 28));
		Content.setBounds(125, 62, 382, 173);
		Content_Panel.add(Content);
		
		ImageIcon img2 = new ImageIcon("./image/comment.png");
		JButton comment = new JButton(size(img2,40,40));
		comment.setBorderPainted(false);
		comment.setContentAreaFilled(false);
		comment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		comment.setBounds(12, 245, 91, 44);
		Content_Panel.add(comment);
		
		ImageIcon img3 = new ImageIcon("./image/retweet.png");
		JButton retweet = new JButton(size(img3,40,40));
		retweet.setBorderPainted(false);
		retweet.setContentAreaFilled(false);
		retweet.setBounds(168, 245, 91, 44);
		Content_Panel.add(retweet);
		
		ImageIcon img4 = new ImageIcon("./image/likes.png");
		JButton like = new JButton(size(img4,40,40));
		like.setBorderPainted(false);
		like.setContentAreaFilled(false);
		like.setBounds(320, 245, 91, 44);
		Content_Panel.add(like);
		
		ImageIcon img5 = new ImageIcon("./image/bookmark.png");
		JButton bookmark = new JButton(size(img5,40,40));
		bookmark.setBorderPainted(false);
		bookmark.setContentAreaFilled(false);
		bookmark.setBounds(475, 245, 100, 44);
		Content_Panel.add(bookmark);
		
		JLabel calendar = new JLabel("New label");
		calendar.setFont(new Font("굴림", Font.PLAIN, 28));
		calendar.setForeground(new Color(255, 255, 255));
		calendar.setBounds(10, 193, 196, 44);
		Content_Panel.add(calendar);
		
		JPanel com = new JPanel();
		getComment(com, "이영찬", "ㅎㅇ");
		
		JScrollPane scrollPane = new JScrollPane(com);
		scrollPane.setBounds(0, 414, 604, 346);
		Tweet_Panel.add(scrollPane);
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
	
	public void getComment(JPanel data, String user_name, String content) {
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

		JButton Name = new JButton(user_name);
		Panel_T.add(Name);
		Name.setFont(new Font("굴림", Font.PLAIN, 28));
		Name.setForeground(new Color(255, 255, 255));
		Name.setBorderPainted(false);
		Name.setContentAreaFilled(false);
		Name.setHorizontalAlignment(SwingConstants.LEFT);
		Name.setBounds(100, 20, 300, 41);

		JButton Content = new JButton(content);
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
	}
	
	public ImageIcon size(ImageIcon img, int width, int height) {
		Image tmp = img.getImage();
		Image updateImg = tmp.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon updateIcon = new ImageIcon(updateImg);
		return updateIcon;
	}
}
