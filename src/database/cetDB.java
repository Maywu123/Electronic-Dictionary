package database;

import java.sql.*;

public class cetDB {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    
    public cetDB(){
    	;
    }
    
    /*public static void main(String args[]) {
    	String word;
   	    cetDB conn=new cetDB();
   	    word = conn.getMeaningfromCet4(2);
        System.out.println(word);
   	 
    }*/
    
    public Connection getConnection() {      //�������ݿ�
        try {
       	    Class.forName("com.mysql.cj.jdbc.Driver");
       	    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dic_database?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone = GMT",
                 "root", "iloveoctober");
        }
            catch(Exception ex) {
       	    ex.printStackTrace();
        }
        return conn;
    }
    
    public void close() {         //�ر����ݿ�����
   	    try {
   		 if(rs!=null)
   		     rs.close();
   		 if(stmt!=null)
   		    stmt.close();
   		 if(conn!=null)
   		    conn.close();
   	    }
   	    catch(Exception ex) {
   		    ex.printStackTrace();
   	    }
    }
    
    public String getWordfromCet4(int number) {   //�����ļ�����
   	    String word = "";
   	    try {
   		    conn = getConnection();
   		    stmt = conn.createStatement();
       	    String sql = "select word from cet4 where id = '"+number+"'";
       	    rs = stmt.executeQuery(sql);
   		 if(rs.next()){
       	    word = rs.getString("word");
       	    conn.close();
   		 }
   	    }
   	    catch(SQLException ex) {
   		    ex.printStackTrace();
   		    word = "";
   	    }
   	    return word;
    }
    
    public String getWordfromCet6(int number) {      //������������
   	    String word = "";
   	    try {
   		    conn = getConnection();
   		    stmt = conn.createStatement();
       	    String sql = "select word from cet6 where id = '"+number+"'";
       	    rs = stmt.executeQuery(sql);
       	    if(rs.next()){
       	        word = rs.getString("word");
       	        conn.close();
   		 }
   	    }
   	    catch(SQLException ex) {
   		    ex.printStackTrace();
   		    word = "";
   	    }
   	    return word;
    }
    
    public String getMeaningfromCet4(int number) {  //�����ļ���������
   	    String meaning = "";
   	    try {
   		     conn = getConnection();
   		     stmt = conn.createStatement();
       	     String sql = "select meaning from cet4 where id = '"+number+"'";
       	     rs = stmt.executeQuery(sql);
   		     if(rs.next()){
       	         meaning = rs.getString("meaning");
       	        conn.close();
   		 }
   	    }
   	    catch(SQLException ex) {
   		     ex.printStackTrace();
   		     meaning = "";
   	    }
   	    return meaning;
    }
    
    public String getMeaningfromCet6(int number) {  //����������������
   	    String meaning = "";
   	    try {
   		     conn = getConnection();
   		    stmt = conn.createStatement();
       	    String sql = "select meaning from cet6 where id = '"+number+"'";
       	    rs = stmt.executeQuery(sql);
   		    if(rs.next()){
   		        meaning = rs.getString("meaning");
       	        conn.close();
   		 }
   	    }
   	    catch(SQLException ex) {
   		    ex.printStackTrace();
   		    meaning = "";
   	    }
   	    return meaning;
    }
}
