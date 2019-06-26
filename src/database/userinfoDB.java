package database;

import java.sql.*;

public class userinfoDB {
	private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    public userinfoDB(){
    	;
    }
    
    public static void main(String args[]) {
	    String word;
	    userinfoDB conn=new userinfoDB();
	    word = conn.getName(4);
        System.out.println(word);
	 
    }
    
    public Connection getConnection(){
    	try{
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dic_database?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone = GMT", "root", "iloveoctober");
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return conn;
    }
    
    public void close(){
    	try{
    		if(rs != null)
    		rs.close();
    		if(ps != null)
    		ps.close();
    		if(conn != null)
    		conn.close();
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    public int Insert(String name,String password,int gid,int status){   //插入一条用户记录
    	int result = 0;
    	try{
    		String sql = "insert into user_info(name,password,gid,status) values(?,?,?,?)";
    		conn = getConnection();
    		ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    		ps.setString(1, name);
    		ps.setString(2, password);
    		ps.setInt(3, gid);
    		ps.setInt(4, status);
    		ps.executeUpdate();
    		rs = ps.getGeneratedKeys();
    		while(rs.next()){
    			result = rs.getInt(1);
    		}
    		conn.close();
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		result = 0;
    	}
    	return result;
    }
    
    public int getID(String name) {
    	int id = 0;
    	try{
    		String sql = "select id from user_info where name = ?";
    		conn = getConnection();
    		ps = conn.prepareStatement(sql);
    		ps.setString(1, name);
    		rs = ps.executeQuery();
    		if(rs.next()) {
    			id = rs.getInt(1);
    		}
    		conn.close();
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return id;
    }
    
    public String getName(int id){       //获取用户姓名
    	String name = null;
    	try{
    		String sql = "select name from user_info where id ="+id;
    		conn = getConnection();
    		ps = conn.prepareStatement(sql);
    		rs = ps.executeQuery();
    		if(rs.next()){
    			name = rs.getString("name");
    			conn.close();
    		}
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return name;
    }
    
    public int IsExist(String name) {    //根据用户名判断用户是否存在
    	int result = 0;
    	try {
    		String sql = "select count(*) from user_info where name = ?";
    		conn = getConnection();
    		ps = conn.prepareStatement(sql);
    		ps.setString(1, name);
    		rs = ps.executeQuery();
    		if(rs.next()) {
    			result = rs.getInt(1);
    		}
    		close();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return result;
    }
    
    public String getPassword1(int id){       //获取用户密码
    	String pw = null;
    	try{
    		String sql = "select password from user_info where id = ?";
    		conn = getConnection();
    		ps = conn.prepareStatement(sql);
    		ps.setInt(1, id);
    		rs = ps.executeQuery();
    		if(rs.next()){
    			pw = rs.getString("password");
    			conn.close();
    		}
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return pw;
    }
    
    public String getPassword2(String name){       //根据用户名获取用户密码
    	String pw = null;
    	try{
    		String sql = "select password from user_info where name = ?";
    		conn = getConnection();
    		ps = conn.prepareStatement(sql);
    		ps.setString(1, name);
    		rs = ps.executeQuery();
    		if(rs.next()){
    			pw = rs.getString("password");
    			conn.close();
    		}
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return pw;
    }
    
    public int getGid(int id){       //获取生词本ID
    	int gid = 0;
    	try{
    		String sql = "select gid from user_info where id = ?";
    		conn = getConnection();
    		ps = conn.prepareStatement(sql);
    		ps.setInt(1, id);
    		rs = ps.executeQuery();
    		if(rs.next()){
    			gid = rs.getInt("gid");
    			conn.close();
    		}
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return gid;
    }
    
    public int updatePassword(int id,String pw){    //更新用户密码
    	int result = 0;
    	try{
    		String sql = "update user_info set password = ? where id = ?";
    		conn = getConnection();
    		ps = conn.prepareStatement(sql);
    		ps.setString(1, pw);
    		ps.setInt(2, id);
    		result = ps.executeUpdate();
    		conn.close();
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return result;
    }
    
    public int updateStatus(int id,int status) {    //更新用户状态
    	int result = 0;
    	try{
    		String sql = "update user_info set status = ? where id = ?";
    		conn = getConnection();
    		ps = conn.prepareStatement(sql);
    		ps.setInt(1, status);
    		ps.setInt(2, id);
    		result = ps.executeUpdate();
    		conn.close();
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return result;
    }
    
    public int Delete(int id) {  //删除一条用户记录
   	    int result = 0;
   	    try {
   		    String sql = "delete from user_info where id = ?";
   		    conn = getConnection();
   		    ps = conn.prepareStatement(sql);
   		    ps.setInt(1, id);
   		    result = ps.executeUpdate();
       	    conn.close();
   	    }
   	    catch(Exception ex) {
   		    ex.printStackTrace();
   	    }
   	    return result;
    }
}
