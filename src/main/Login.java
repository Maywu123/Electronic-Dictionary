package main;

import database.userinfoDB;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Login extends Application{
	private Pane p = new Pane();
	private Label lbl1 = new Label("�û���");
	private Label lbl2 = new Label("����");
	private TextField tf1 = new TextField();
	private PasswordField tf2 = new PasswordField();
	private Button btlogin = new Button("��½");
	private Button btregister = new Button("ע��");
	private Label tip1 = new Label();
	private Label tip2 = new Label();
	private userinfoDB userdb = new userinfoDB();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Application.launch(args);
	}
    
	public void start(Stage primaryStage) throws Exception{
		lbl1.setLayoutX(100);
		lbl1.setLayoutY(80);
		lbl2.setLayoutX(100);
		lbl2.setLayoutY(140);
		tf1.setLayoutX(170);
		tf1.setLayoutY(80);
		tf2.setLayoutX(170);
		tf2.setLayoutY(140);
		btlogin.setLayoutX(170);
		btlogin.setLayoutY(200);
		btregister.setLayoutX(260);
		btregister.setLayoutY(200);
		tip1.setLayoutX(370);
		tip1.setLayoutY(80);
		tip2.setLayoutX(370);
		tip2.setLayoutY(140);
		
		p.getChildren().addAll(lbl1,lbl2,tf1,tf2,btlogin,btregister,tip1,tip2);
		p.setStyle("-fx-background-image: url(\"/images/bg3.jpg\");");
		
		btregister.setOnAction(e->registerHandle());
		btlogin.setOnAction(e->loginHandle(primaryStage));

		Scene scene = new Scene(p,500,400);
		primaryStage.setTitle("���Ӵʵ�");
    	primaryStage.setScene(scene);
    	primaryStage.show();
	}
	
	private void registerHandle(){
		Register open = new Register();
		try{
			open.start(new Stage());
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	private void loginHandle(Stage stage) {
		
		String uname = tf1.getText();
		String pa = tf2.getText();
		if(uname.isEmpty()) {
			tip1.setText("�������û���");
			tip1.setTextFill(Color.RED);
		}
		if(pa.isEmpty()) {
			tip2.setText("����������");
			tip2.setTextFill(Color.RED);
		}
		if(!uname.isEmpty() && !pa.isEmpty()) {
			if(userdb.IsExist(uname) == 0) {       //�û�������
				Alert alert = new Alert(Alert.AlertType.WARNING,"�û������ڣ�����ע�ᣡ");
				alert.show();
			}
			else if(!pa.equals(userdb.getPassword2(uname))){   //�������
				Alert alert = new Alert(Alert.AlertType.WARNING,"�������");
				alert.show();
			}
			else {
				int id = userdb.getID(uname);
				MainInterface open = new MainInterface(id);
				stage.close();
				open.start(new Stage());
			}
		}
	}
}
