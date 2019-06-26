package main;

import database.glossaryDB;
import database.reciteDB;
import database.userinfoDB;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Register extends Application{
	private Pane pane = new Pane();
    private Label lbl1 = new Label("用户名：");
    private Label lbl2 = new Label("密码：");
    private Label lbl3 = new Label("确认密码：");
    private TextField tf = new TextField();
    private PasswordField pf1 = new PasswordField();
    private PasswordField pf2 = new PasswordField();
    private Button btregister = new Button("注册");
    private Button btcancle = new Button("取消");
    private Label tip1 = new Label();
	private Label tip2 = new Label();
	private Label tip3 = new Label();
	private userinfoDB userdb = new userinfoDB();
	private glossaryDB gdb = new glossaryDB();
	private reciteDB rdb = new reciteDB();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Register.launch(args);
	}
    public void start(Stage primaryStage){
    	lbl1.setLayoutX(100);
    	lbl1.setLayoutY(80);
    	lbl2.setLayoutX(100);
    	lbl2.setLayoutY(140);
    	lbl3.setLayoutX(100);
    	lbl3.setLayoutY(200);
    	tf.setLayoutX(175);
    	tf.setLayoutY(80);
    	pf1.setLayoutX(175);
    	pf1.setLayoutY(140);
    	pf2.setLayoutX(175);
    	pf2.setLayoutY(200);
    	btregister.setLayoutX(175);
    	btregister.setLayoutY(280);
    	btcancle.setLayoutX(260);
    	btcancle.setLayoutY(280);
    	tip1.setLayoutX(370);
		tip1.setLayoutY(80);
		tip2.setLayoutX(370);
		tip2.setLayoutY(140);
		tip3.setLayoutX(370);
		tip3.setLayoutY(200);
    	
    	pane.getChildren().addAll(lbl1,lbl2,lbl3,tf,pf1,pf2,btregister,btcancle,tip1,tip2,tip3);
    	pane.setStyle("-fx-background-image: url(\"/images/007.jpg\");");
    	
    	btregister.setOnAction(e->registerHandle(primaryStage));
    	btcancle.setOnAction(e->cancleHandle(primaryStage));
    	
    	Scene scene = new Scene(pane,500,400);
		primaryStage.setTitle("电子词典");
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }
    
    private void cancleHandle(Stage stage) {
    	stage.close();
    }
    
    private void registerHandle(Stage stage) {
    	String uname = tf.getText();
    	String pa1 = pf1.getText();
    	String pa2 = pf2.getText();
    	if(uname.length() == 0) {
    		tip1.setText("请输入用户名");
    		tip1.setTextFill(Color.RED);
    	}
    	if(pa1.length() == 0) {
    		tip2.setText("请输入密码");
    		tip2.setTextFill(Color.RED);
    	}
    	if(pa2.length() == 0) {
    		tip3.setText("请确认密码");
    		tip3.setTextFill(Color.RED);
    	}
    	if(!pa1.equals(pa2)) {
    		tip3.setText("密码不一致");
    		tip3.setTextFill(Color.RED);
    	}
    	if(uname.length()>12||uname.length()<1){
    		tip1.setText("用户名长度为1-12字符长度，请重新输入");
    		tip1.setTextFill(Color.RED);
    	}
    	if(pa1.length()>12||pa1.length()<6){
    		tip2.setText("密码长度为6-12字符长度，请重新输入");
    		tip2.setTextFill(Color.RED);
    	}
    	if(pa2.length()>12||pa2.length()<6){
    		tip2.setText("确认密码长度为6-12字符长度，请重新输入");
    		tip2.setTextFill(Color.RED);
    	}
    	if(!uname.isEmpty() && !pa1.isEmpty() && !pa2.isEmpty() && pa1.equals(pa2)) {
    		int id = 0;
    		int gid = 0;
    		gid = gdb.Insert("");
    		id = userdb.Insert(uname,pa1,gid,0);
    		rdb.Insert(id, 0, 0);
    		if(id != 0) {
    			Alert alert = new Alert(Alert.AlertType.INFORMATION,"注册成功！");
        		alert.show();
    		}
    	}
    }
}
