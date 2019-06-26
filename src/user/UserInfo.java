package user;

public class UserInfo {

	private int id;
	private String uname;
	private String password;
	private int status;
	private Glossary glossary;
	
	public UserInfo(int i,String un){
		id=i;
		uname=un;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int i){
		id=i;
	}
	
	public String getUname(){
		return uname;
	}
	
	public void setUname(String u){
		uname=u;
	}
	
	public void setStatus(int s){
		status=s;
	}
	
	public String getPassword(){
		return password;
	}
	
	public int getStatus(){
		return status;
	}
	
	public Glossary getGlossary(){
		return glossary;
	}
}

