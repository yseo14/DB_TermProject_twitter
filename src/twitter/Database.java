/*
 * 파일명 : Database.java
 * 작성자 : 이준형
 * 설명 : 데이터베이스와 연동해 기능을 구현하는 클래스
 */
package twitter;

import java.sql.*;
import java.util.ArrayList;

public class Database {
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	PreparedStatement pstm = null;

	/*
	 * 이하 url, user, pw만 자신의 환경에 맞게 수정하세요
	 */

	// 주소와 포트 뒤 twitter 부분만 DB의 이름에 맞게 수정하세요
	private String url = "jdbc:mysql://localhost/termprojectdb";

	// 스트링 전체를 MySQL 유저이름에 맞게 수정하세요
	private String user = "root";

	// 스트링 전체를 MySQL 비밀번호에 맞게 수정하세요
	private String pw = "2413ys";

	// 로그인 이후 ID를 활용하는 변수
	public String loginId = null; // 로그인 아이디
	public int userId = 0; // 내부적인 숫자 식별자

	Database() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pw);
			System.out.println(con);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setUserId(String logid)
	{
		String findUid = "select user_id from user where login_id = \"" + logid + "\"";
		try
		{
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(findUid);
			if(rs.next())
			{
				this.userId = rs.getInt(1);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public String getId() {
		return this.loginId;
	}
	
	int getUserId() {
		return this.userId;
	}
	
	String getNick()
	{
		String nickname = null;
		try
		{
			stmt = con.createStatement();
			String get = "select nickname from user where user_id = " + userId;
			rs = stmt.executeQuery(get);
			
			if(rs.next())
			{
				nickname = rs.getString(1);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return nickname;
	}
	
	String getNick(int id)
	{
		String nickname = null;
		try
		{
			stmt = con.createStatement();
			String get = "select nickname from user where user_id = " + id;
			rs = stmt.executeQuery(get);
			
			if(rs.next())
			{
				nickname = rs.getString(1);
				return nickname;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return nickname;
	}
	
	String getPW()
	{
		String password = null;
		try
		{
			stmt = con.createStatement();
			String get = "select password from user where user_id = " + userId;
			rs = stmt.executeQuery(get);
			
			if(rs.next())
			{
				password = rs.getString(1);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return password;
	}
	public boolean loginFuction(String i, String p) {
		String idt = i;
		String pw = p;

		try {
			stmt = con.createStatement();
			String login = "select user_id from user where login_id = \"" + idt + "\" and password =\"" + pw + "\"";
			System.out.println(login);

			rs = stmt.executeQuery(login);

			if (rs.next()) {
				loginId = idt;
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean signupFunction(String id, String pw, String nick, String em, String bd)
	{
		try
		{
			stmt = con.createStatement();
			String dupCheck = "select login_id from user where login_id = \"" + id + "\"";
			rs = stmt.executeQuery(dupCheck);
			
			if(rs.next())
			{
				return false;
			}

			int num = getMaxNum("select max(user_id) from user");
			num++;
			
			String signup = "insert into user values (\'" + num + "\', \'" + id + "\', \'" + pw + "\', now(), \'" + em + "\', \'" + nick + "\', NULL, NULL, NULL, \'" + bd + "\')";
			System.out.println(signup);
			
			pstm = con.prepareStatement(signup);
			pstm.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return true;
	}

	// return -1 : self follow, -2 : user not found, 0 : already follow, 1 : success
	int followFunction(String fid) {
		try {
			stmt = con.createStatement();

			int fuid = -1;
			String getFuid = "select user_id where login_id = \"" + fid + "\"";
			rs = stmt.executeQuery(getFuid);

			if (!rs.next()) {
				return -2; // User not found
			}

			fuid = rs.getInt(1);

			if (fuid == userId) {
				return -1; // Can't follow yourself!
			}

			String dup = "select followee_id from follow where followee_id = \"" + fuid + "\" and follower_id = \""
					+ userId + "\"";
			rs = stmt.executeQuery(dup);

			if (rs.next()) {
				return 0; // already follow
			}

			int num = getMaxNum("select max(follow_id) from follow");
			num++;

			String follow = "insert into follower values (" + num + ", " + userId + ", " + fuid + ", curdate())";
			System.out.println(follow);

			pstm = con.prepareStatement(follow);
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 1;
	}

	boolean writeFunction(String text) {
		try {
			int num = getMaxNum("select max(tweet_id) from tweet");
			num++;

			String write = "insert into tweet values (" + num + ", " + userId + ", \"" + text
					+ "\", curdate(), 0, 0, 0, 1, 0, 0, NULL, NULL, NULL)";
			System.out.println(write);

			pstm = con.prepareStatement(write);
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

	void plusPostLikes(String post_id) {
		try {
			String write = "update tweet set like_count = like_count + 1 where tweet_id = \"" + post_id + "\"";
			pstm = con.prepareStatement(write);
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	int getNumUser() {
		try {
			stmt = con.createStatement();
			String write = "select count(user_id) from user";
			rs = stmt.executeQuery(write);
			if (!rs.next()) {
				return rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	int getNumUserPost(String user_id) {
		try {
			stmt = con.createStatement();
			String write = "select count(tweet_id) from tweet where user_id = \"" + user_id + "\"";
			rs = stmt.executeQuery(write);
			if (!rs.next()) {
				return rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	int getNumTotalPost() {
		try {
			stmt = con.createStatement();
			String write = "select count(tweet_id) from tweet";
			rs = stmt.executeQuery(write);
			if (!rs.next()) {
				return rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	int getFollowingNum(int id) {
		try {
			stmt = con.createStatement();
			String write = "select count(followee_id) from follow where follower_id = \"" + id + "\"";
			rs = stmt.executeQuery(write);
			if (rs.next()) {
				int count = rs.getInt(1);
				System.out.println(count);
				return count;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	int getFollowerNum(int id) {
		try {
			stmt = con.createStatement();
			String write = "select count(follower_id) from follow where followee_id = \"" + id + "\"";
			rs = stmt.executeQuery(write);
			if (rs.next()) {
				int count = rs.getInt(1);
				return count;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	ResultSet getFollowing(int id) {
		try {
			stmt = con.createStatement();
			String write = "select user_id,login_id, nickname from user where user_id in (select followee_id from follow where follower_id =  \""
					+ id + "\")";
			rs = stmt.executeQuery(write);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	ResultSet getFollower(int id) {
		try {
			stmt = con.createStatement();
			String write = "select user_id,login_id, nickname from user where user_id in (select follower_id from follow where followee_id =  \""
					+ id + "\")";
			rs = stmt.executeQuery(write);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	void unfollowFunction(int fid, int lid) {
		try {
			stmt = con.createStatement();
			String unfollow = "delete from follow where follower_id = \"" + lid + "\"" + " and followee_id = \"" + fid
					+ "\"";
			pstm = con.prepareStatement(unfollow);
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	void followFunction(int fid, int lid) {
		try {
			stmt = con.createStatement();
			String follow = "insert into follow values (\"" + 0 + "\", " + "\"" + lid + "\", " + "\"" + fid
					+ "\", now())";
			System.out.println(follow);
			pstm = con.prepareStatement(follow);
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	ResultSet getUserWithNickName(String nickname) {
		try {
			stmt = con.createStatement();
			String write = "select user_id, login_id, nickname from user where nickname = \"" + nickname + "\"";
			rs = stmt.executeQuery(write);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	ResultSet getUser() {
		try {
			stmt = con.createStatement();
			String write = "select nickname from user";
			rs = stmt.executeQuery(write);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	ResultSet getHomeTweet(int login_number) {
		try {
			stmt = con.createStatement();
			String following = "select followee_id from follow where follower_id = \"" + login_number + "\"";
			String write = "select * from tweet where writer_id in (" + following + ") or writer_id = \"" + login_number
					+ "\" order by created desc";
			rs = stmt.executeQuery(write);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	ResultSet getExploreTweet() {
		try {
			stmt = con.createStatement();
			String write = "select * from tweet order by created desc";
			rs = stmt.executeQuery(write);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	ResultSet getUserTweet(int login_number) {
		try {
			stmt = con.createStatement();
			String write = "select * from tweet where writer_id = \"" + login_number + "\" order by created desc";
			rs = stmt.executeQuery(write);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	int getUserCount() {
		try {
			stmt = con.createStatement();
			String write = "select count(nickname) from user";
			rs = stmt.executeQuery(write);
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	int getTweetCount() {
		try {
			stmt = con.createStatement();
			String write = "select count(tweet_id) from tweet";
			rs = stmt.executeQuery(write);
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	int checkFollowing(int user_number, int following_number) {
		try {
			stmt = con.createStatement();
			String write = "select* from follow where follower_id = \"" + user_number + "\" and followee_id = \""
					+ following_number + "\"";
			rs = stmt.executeQuery(write);
			if (rs.next()) {
				return 1;
			}
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	int countLike(int t_id){
		try {
			stmt = con.createStatement();
			String write = "select like_count from tweet where tweet_id = \"" + t_id + "\"";
			rs = stmt.executeQuery(write);
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	int checkLikes(int tid) {
		try {
			stmt = con.createStatement();
			String dup = "select like_id from tweet_like where tweet_id = " + tid + " and user_id = " + userId;
			rs = stmt.executeQuery(dup);
			if(rs.next()) {
				return 0;
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return 1;
	}
	
	void likeFunction(int tid)
	{
		try
		{
			stmt = con.createStatement();
			String dup = "select like_id from tweet_like where tweet_id = " + tid + " and user_id = " + userId;
			rs = stmt.executeQuery(dup);
			
			if(rs.next())
			{
				return;
			}
			
			String maxLike = "select max(like_id) from tweet_like";
			int likeNum = getMaxNum(maxLike);
			likeNum++;
			
			String like = "insert into tweet_like values (" + likeNum + ", " + userId + ", " + tid + ", now())";
			System.out.println(like);
			
			pstm = con.prepareStatement(like);
			pstm.executeUpdate();
			
			String likeUpdate = "update tweet set like_count = like_count + 1 where tweet_id = \'" + tid + "\'";
			stmt.executeUpdate(likeUpdate);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	void unLikeFunction(int tid)
	{
		try
		{
			stmt = con.createStatement();
			String dup = "select like_id from tweet_like where tweet_id = " + tid + " and user_id = " + userId;
			rs = stmt.executeQuery(dup);
			
			if(!rs.next())
			{
				return;
			}
			
			String unlike = "delete from tweet_like where tweet_id = " + tid + " and user_id = " + userId;
			System.out.println(unlike);
			
			pstm = con.prepareStatement(unlike);
			pstm.executeUpdate();
			
			String likeUpdate = "update tweet set like_count = like_count - 1 where tweet_id = \'" + tid + "\'";
			stmt.executeUpdate(likeUpdate);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	int countRetweet(int t_id){
		try {
			stmt = con.createStatement();
			String write = "select retweet_count from tweet where tweet_id = \"" + t_id + "\"";
			rs = stmt.executeQuery(write);
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	int countReply(int t_id){
		try {
			stmt = con.createStatement();
			String write = "select reply_count from tweet where tweet_id = \"" + t_id + "\"";
			rs = stmt.executeQuery(write);
			if (rs.next()) {
				System.out.println(rs.getInt(1));
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	void makeTweet(int t_id, int w_id, String content, int n_like, int n_re, int n_c, int o_id, String location) {
		try {
			stmt = con.createStatement();
			String n = "";
			String write = "insert into tweet values (\"" + t_id + "\", \"" + w_id + "\", \"" + content
					+ "\", now(), \"" + n_like + "\", \"" + n_re + "\", \"" + n_c + "\", \"" + 0 + "\", \"" + 0
					+ "\", \"" + 0 + "\", \"" + o_id + "\", \"" + location + "\", \"" + n + "\")";
			pstm = con.prepareStatement(write);
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	void makeComment(int t_id, int w_id, String content, int n_like, int n_re, int n_c, int o_id, String location) {
		try {
			stmt = con.createStatement();
			String n = "";
			String write = "insert into tweet values (\"" + t_id + "\", \"" + w_id + "\", \"" + content
					+ "\", now(), \"" + n_like + "\", \"" + n_re + "\", \"" + n_c + "\", \"" + 0 + "\", \"" + 0
					+ "\", \"" + 1 + "\", \"" + o_id + "\", \"" + location + "\", \"" + n + "\")";
			pstm = con.prepareStatement(write);
			pstm.executeUpdate();
			
			write = "update tweet set reply_count = reply_count + 1 where tweet_id = \"" + o_id + "\"";
			System.out.println(write);
			pstm = con.prepareStatement(write);
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	String getName(int login_id) {
		try {
			stmt = con.createStatement();
			String write = "select nickname from user where user_id = \"" + login_id + "\"";
			rs = stmt.executeQuery(write);
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	String getId(int login_id) {
		try {
			stmt = con.createStatement();
			String write = "select login_id from user where user_id = \"" + login_id + "\"";
			rs = stmt.executeQuery(write);
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	ResultSet getComment(int t_id) {
		try {
			stmt = con.createStatement();
			String write = "select * from tweet where original_tweet_id = \"" + t_id + "\" and is_reply = 1 order by created desc";
			rs = stmt.executeQuery(write);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// tweet function
	private int getMaxNum(String sql) {
		int num = -1;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				num = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return num;
	}

	String getContent(int tid) {
		String content = null;

		try {
			stmt = con.createStatement();

			String find = "select content from tweet where tweet_id = \"" + tid + "\"";
			rs = stmt.executeQuery(find);

			if (rs.next()) {
				content = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return content;
	}

	void bookmarkFunction(int tid) {
		try {
			stmt = con.createStatement();
			String dup = "select bookmark_id from bookmark where tweet_id = " + tid + " and user_user_id = " + userId;
			rs = stmt.executeQuery(dup);

			if (rs.next()) {
				return;
			}

			String maxBookmark = "select max(bookmark_id) from bookmark";
			int bookmarkNum = getMaxNum(maxBookmark);
			bookmarkNum++;

			String bookmark = "insert into bookmark values (" + bookmarkNum + ", " + tid + ", " + userId + ", now())";
			System.out.println(bookmark);

			pstm = con.prepareStatement(bookmark);
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	ResultSet getBookmark() {
		try {
			stmt = con.createStatement();
			String write = "select * from tweet where tweet_id in (select tweet_id from bookmark where user_user_id = \"" + userId  + "\")";
			System.out.println(write);
			rs = stmt.executeQuery(write);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	ResultSet getLikes() {
		try {
			stmt = con.createStatement();
			String write = "select * from tweet where tweet_id in (select tweet_id from tweet_like where user_id = \"" + userId  + "\")";
			System.out.println(write);
			rs = stmt.executeQuery(write);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	String getLoginIdFromTweet(int tid)
    {
        String result = null;
        int userIdFromTweet = 0;
        try
        {
            stmt = con.createStatement();
            String tweetIdToUserId = "select writer_id from tweet where tweet_id = " + tid;
            rs = stmt.executeQuery(tweetIdToUserId);

            if(rs.next())
            {
                userIdFromTweet = rs.getInt(1);
            }

            String UserIdToLoginId = "select login_id from user where user_id = " + userIdFromTweet;
            rs = stmt.executeQuery(UserIdToLoginId);

            if(rs.next())
            {
                result = rs.getString(1);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return result;
    }
	
	String getCreatedTweet(int t_id)
	{
		String created = null;

		try
		{
			stmt = con.createStatement();
			String getCreated = "select created from tweet where tweet_id = \"" + t_id + "\"";
			rs = stmt.executeQuery(getCreated);
			
			if(rs.next())
			{
				created = rs.getString(1);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return created;
	}
	
	void retweetFunction(int tid) {
		try {
			stmt = con.createStatement();
			String dupTweet = "select tweet_id from tweet where is_retweet = 1 and original_tweet_id = " + tid
					+ " and writer_id = " + userId;
			rs = stmt.executeQuery(dupTweet);

			if (rs.next()) {
				return;
			}

			String dupRetweet = "select tweet_id from tweet where is_retweet = 1 and original_tweet_id = " + tid;
			rs = stmt.executeQuery(dupRetweet);

			if (rs.next()) {
				return;
			}

			String maxTweet = "select max(tweet_id) from tweet";
			int tweetNum = getMaxNum(maxTweet);
			tweetNum++;

			String content = getContent(tid);
			String retweetSentence = "Retweet of " + getLoginIdFromTweet(tid) + " : \n";
			content = retweetSentence + content;

			String write = "insert into tweet values (" + tweetNum + ", " + userId + ", \"" + content
					+ "\", now(), 0, 0, 0, 0, 1, 0," + tid + ", NULL, NULL)";
			System.out.println(write);

			pstm = con.prepareStatement(write);
			pstm.executeUpdate();

			String likeUpdate = "update tweet set retweet_count = retweet_count + 1 where tweet_id = \'" + tid + "\'";
			stmt.executeUpdate(likeUpdate);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	void updateProfileFunction(String pw, String em, String nick, String loc, String desc, String bd)
	{
		try
		{
			stmt = con.createStatement();
			String likeUpdate = "update user set password = \"" + pw + "\", email = \"" + em + "\", nickname = \"" + nick + "\", location = \"" + loc + "\", description = \"" + desc + "\", birthday = \"" + bd + "\" where user_id = " + userId;
			stmt.executeUpdate(likeUpdate);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	String getCreated()
	{
		String created = null;

		try
		{
			stmt = con.createStatement();
			String getCreated = "select created from user where user_id = \"" + userId + "\"";
			rs = stmt.executeQuery(getCreated);
			
			if(rs.next())
			{
				created = rs.getString(1);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return created;
	}
	
	String getCreated(int l_n)
	{
		String created = null;

		try
		{
			stmt = con.createStatement();
			String getCreated = "select created from user where user_id = \"" + l_n + "\"";
			rs = stmt.executeQuery(getCreated);
			
			if(rs.next())
			{
				created = rs.getString(1);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return created;
	}
	
	String getEmail()
	{
		String email = null;
		try
		{
			stmt = con.createStatement();
			String get = "select email from user where user_id = " + userId;
			rs = stmt.executeQuery(get);
			
			if(rs.next())
			{
				email = rs.getString(1);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return email;
	}
	
	String getEmail(int l_n)
	{
		String email = null;
		try
		{
			stmt = con.createStatement();
			String get = "select email from user where user_id = " + l_n;
			rs = stmt.executeQuery(get);
			
			if(rs.next())
			{
				email = rs.getString(1);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return email;
	}
	
	String getDesc()
	{
		String nickname = null;

		try
		{
			stmt = con.createStatement();
			String getNick = "select ifnull(description, \" \") from user where user_id = " + userId;
			rs = stmt.executeQuery(getNick);
			
			if(rs.next())
			{
				nickname = rs.getString(1);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return nickname;
	}
	
	String getDesc(int l_n)
	{
		String nickname = null;

		try
		{
			stmt = con.createStatement();
			String getNick = "select ifnull(description, \" \") from user where user_id = " + l_n;
			rs = stmt.executeQuery(getNick);
			
			if(rs.next())
			{
				nickname = rs.getString(1);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return nickname;
	}
	
	String getLocation()
	{
		String location = null;
		try
		{
			stmt = con.createStatement();
			String get = "select ifnull(location, \" \") from user where user_id = " + userId;
			rs = stmt.executeQuery(get);
			
			if(rs.next())
			{
				location = rs.getString(1);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return location;
	}
	
	String getLocation(int l_n)
	{
		String location = null;
		try
		{
			stmt = con.createStatement();
			String get = "select ifnull(location, \" \") from user where user_id = " + l_n;
			rs = stmt.executeQuery(get);
			
			if(rs.next())
			{
				location = rs.getString(1);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return location;
	}
	
	String getBD()
	{
		String bd = null;
		try
		{
			stmt = con.createStatement();
			String get = "select ifnull(birthday, \" \") from user where user_id = " + userId;
			rs = stmt.executeQuery(get);
			
			if(rs.next())
			{
				bd = rs.getString(1);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return bd;
	}
	
	String getBD(int l_n)
	{
		String bd = null;
		try
		{
			stmt = con.createStatement();
			String get = "select ifnull(birthday, \" \") from user where user_id = " + l_n;
			rs = stmt.executeQuery(get);
			
			if(rs.next())
			{
				bd = rs.getString(1);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return bd;
	}
	
	void deleteTweet(int t_id) {
		try
		{
			String write = "delete from tweet where tweet_id = \"" + t_id + "\"";
			pstm = con.prepareStatement(write);
			pstm.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}