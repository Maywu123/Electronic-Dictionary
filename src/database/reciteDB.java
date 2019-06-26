package database;

import java.sql.*;

public class reciteDB {
	private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    public reciteDB() {
    	;
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
    
    public int Insert(int uid,int c4,int c6) { //插入一条记录
    	int result = 0;
    	try{
    		String sql = "insert into recite_info(uid,countforcet4,countforcet6) values(?,?,?)";
    		conn = getConnection();
    		ps = conn.prepareStatement(sql);
    		ps.setInt(1, uid);
    		ps.setInt(2, c4);
    		ps.setInt(3, c6);
    		result = ps.executeUpdate();
    		conn.close();
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return result;
    }
    
    public int getCountforCet4(int uid) {
    	int count = 0;
    	try{
    		String sql = "select countforcet4 from recite_info where uid = ?";
    		conn = getConnection();
    		ps = conn.prepareStatement(sql);
    		ps.setInt(1, uid);
    		rs = ps.executeQuery();
    		if(rs.next()) {
    			count = rs.getInt(1);
    		}
    		close();
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return count;
    }
    
    public int getCountforCet6(int uid) {
    	int count = 0;
    	try{
    		String sql = "select countforcet6 from recite_info where uid = ?";
    		conn = getConnection();
    		ps = conn.prepareStatement(sql);
    		ps.setInt(1, uid);
    		rs = ps.executeQuery();
    		if(rs.next()) {
    			count = rs.getInt(1);
    		}
    		close();
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return count;
    }
    
    public int updateCountforCet4(int uid,int c4) {   //更新已背四级单词数目
    	int result = 0;
    	try{
    		String sql = "update recite_info set countforcet4 = ? where uid = ?";
    		conn = getConnection();
    		ps = conn.prepareStatement(sql);
    		ps.setInt(1, c4);
    		ps.setInt(2, uid);
    		result = ps.executeUpdate();
    		close();
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return result;
    }
    
    public int updateCountforCet6(int uid,int c6) {   //更新已背六级单词数目
    	int result = 0;
    	try{
    		String sql = "update recite_info set countforcet6 = ? where uid = ?";
    		conn = getConnection();
    		ps = conn.prepareStatement(sql);
    		ps.setInt(1, c6);
    		ps.setInt(2, uid);
    		result = ps.executeUpdate();
    		close();
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return result;
    }
    
    public int Delete(int uid) {    //删除一条记录
    	int result = 0;
    	try{
    		String sql = "delete from recite_info where uid = ?";
    		conn = getConnection();
    		ps = conn.prepareStatement(sql);
    		ps.setInt(1, uid);
    		result = ps.executeUpdate();
    		close();
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return result;
    }
}
