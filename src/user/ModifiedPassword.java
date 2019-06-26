package user;

import javafx.stage.Stage;
import database.userinfoDB;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ModifiedPassword extends Application{
	
	private Pane p = new Pane();
	private Label lbl0 = new Label("修改密码");
	private Label lbl1 = new Label("原密码");
	private Label lbl2 = new Label("密码");
	private Label lbl3 = new Label("确认密码");
	private Label lbl4 = new Label("提示：密码为6-12位");
	private PasswordField tf1 = new PasswordField();
	private PasswordField tf2 = new PasswordField();
	private PasswordField tf3 = new PasswordField();
	private Button btok = new Button("确认");
	private Button btconcle = new Button("取消");
	private Label tip1 = new Label();
	private Label tip2 = new Label();
	private Label tip3 = new Label();
	private userinfoDB userdb = new userinfoDB();
	private int id;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Application.launch(args);
	}
    
	public ModifiedPassword(int id) {
		this.id = id;
	}
	
	public void start(Stage primaryStage) throws Exception{
		
		lbl0.setLayoutX(220);
		lbl0.setLayoutY(40);
		
		lbl1.setLayoutX(100);
		lbl1.setLayoutY(80);
		
		lbl2.setLayoutX(100);
		lbl2.setLayoutY(120);
		
		lbl3.setLayoutX(100);
		lbl3.setLayoutY(160);
		
		lbl4.setLayoutX(100);
		lbl4.setLayoutY(280);
		
		tf1.setLayoutX(170);
		tf1.setLayoutY(80);
		
		tf2.setLayoutX(170);
		tf2.setLayoutY(120);
		
		tf3.setLayoutX(170);
		tf3.setLayoutY(160);
		
		btok.setLayoutX(190);
		btok.setLayoutY(220);
		
		btconcle.setLayoutX(260);
		btconcle.setLayoutY(220);
		
		tip1.setLayoutX(370);
		tip1.setLayoutY(80);
		tip2.setLayoutX(370);
		tip2.setLayoutY(120);
		tip3.setLayoutX(370);
		tip3.setLayoutY(160);
		
		p.getChildren().addAll(lbl0,lbl1,lbl2,lbl3,tf1,tf2,tf3,btok,btconcle,tip1,tip2,tip3);
		p.setStyle("-fx-background-image: url(\"/images/bg3.jpg\");");
		
		btok.setOnAction(e->modifiedHandle(primaryStage));
		
		btconcle.setOnAction(e -> concleHandel(primaryStage));

		Scene scene = new Scene(p,500,400);
		primaryStage.setTitle("电子词典");
    	primaryStage.setScene(scene);
    	primaryStage.show();
	}
	
	public void concleHandel(Stage stage) {
        stage.close();
    }
    
	private void modifiedHandle(Stage stage) {
		int result = 0;
		String oldpa = tf1.getText();
		String newpa1 = tf2.getText();
		String newpa2 = tf3.getText();
		if(oldpa.isEmpty()) {
			tip1.setText("请输入原密码");
    		tip1.setTextFill(Color.RED);
		}
		if(newpa1.isEmpty()) {
			tip2.setText("请输入新密码");
    		tip2.setTextFill(Color.RED);
		}
		if(newpa2.isEmpty()) {
			tip3.setText("请确认新密码");
    		tip3.setTextFill(Color.RED);
		}
		if(newpa1.length() > 16) {
			tip2.setText("密码超出16位");
    		tip2.setTextFill(Color.RED);
		}
		if(!newpa1.equals(newpa2)) {
			tip3.setText("密码不一致");
    		tip3.setTextFill(Color.RED);
		}
		if(!oldpa.isEmpty() && !newpa1.isEmpty() && !newpa2.isEmpty() && newpa1.equals(newpa2) && (newpa1.length()<=16)) {
			tip1.setText("");
			tip2.setText("");
			tip3.setText("");
			result = userdb.updatePassword(id, newpa1);
			if(result != 0) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION,"修改成功！");
        		alert.show();
        		stage.close();
			}
		}
	}
}



