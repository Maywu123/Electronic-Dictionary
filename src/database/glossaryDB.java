package database;

import java.sql.*;
import java.lang.StringBuilder;

import org.apache.commons.lang3.ArrayUtils;


public class glossaryDB {
	private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    public glossaryDB(){
    	;
    }
    
    public static void main(String args[]) {
    	int result;
	    //String word = new String();
	    glossaryDB conn=new glossaryDB();
	    //result = conn.Insert("");
	    result = conn.DeleteWord(1, "back");
        System.out.println(result);
	    
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
    
    public String getWordList(int gid){     //???????????????????
    	String wl = "";
    	try{
    		String sql = "select wordlist from glossary where id = ?";
    		conn = getConnection();
    		ps = conn.prepareStatement(sql);
    		ps.setInt(1, gid);
    		rs = ps.executeQuery();
    		if(rs.next()){
    			wl = rs.getString("wordlist");
    		}
    		close();
    		/*if(wl.isEmpty()){
    			wordlist = null;     //?????????????????????null
    		}
    		else{
    			wordlist = wl.split(",");
    		}*/
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return wl;
    }
    
    public int AddWord(int gid,String w){      //???????????????????,????
    	int result = 0;
    	String wl = "";
    	try{
    		String sql = "select wordlist from glossary where id = ?";
    		conn = getConnection();
    		ps = conn.prepareStatement(sql);
    		ps.setInt(1, gid);
    		rs = ps.executeQuery();
    		if(rs.next()){
    			wl = rs.getString("wordlist");
    		}
    		close();
    		if(wl.isEmpty()){
    			wl += w;
    		}
    		else{
    			wl += ",";
    			wl += w;
    		}
    		result = updateWordList(gid,wl);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return result;
    }
    
    public int updateWordList(int gid,String wl){     //??????????????
    	int result = 0;
    	try{
    		String sql = "update glossary set wordlist = ? where id = ?";
    		conn = getConnection();
    		ps = conn.prepareStatement(sql);
    		ps.setString(1, wl);
    		ps.setInt(2, gid);
    		result = ps.executeUpdate();
    		conn.close();
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return result;
    }
    
    public int DeleteWord(int gid,String w){        //???????????????????,????
    	int result = 0;
    	int k = -1;
    	String wl = "";
    	String[] wordlist = new String[100];
    	StringBuilder str = new StringBuilder();
    	try{
    		wl = getWordList(gid);
    		if(!wl.isEmpty()){
    			wordlist = wl.split(",");
    			for(int i = 0; i < wordlist.length; i++){
        			if(wordlist[i].equals(w)){
        				k = i;
        				break;
        			}
        		}
        		if( k != -1){
        			wordlist = (String[]) ArrayUtils.remove(wordlist, k);
        			for(int j = 0; j < wordlist.length; j++){
        				str.append(wordlist[j]);
        			}
        			updateWordList(gid,str.toString());
        			result = 1;
        		}
        		else{
        			result = 0;
        		}
    		}
    		else{
    			result = 0;
    		}
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return result;
    }
    
    public int Insert(String wl){          //??????????,?????????ID??????
    	int result = 0;
    	StringBuilder str = new StringBuilder();
    	try{
    		String sql = "insert into glossary(wordlist) values(?)";
    		conn = getConnection();
    		ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    		ps.setString(1, wl);
    		ps.executeUpdate();
    		rs = ps.getGeneratedKeys();
    		if(rs.next()){
    			result = rs.getInt(1);
    		}
    		conn.close();
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return result;
    }
    
    public int clearWordList(int gid){      //????????
    	int result = 0;
    	String wl = "";
    	try{
    		result = updateWordList(gid,wl);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return result;
    }
}
