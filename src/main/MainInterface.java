package main;

import java.io.IOException;
import java.util.Optional;

import org.apache.http.client.ClientProtocolException;

import com.sun.prism.paint.Color;

import database.glossaryDB;
import database.reciteDB;
import database.userinfoDB;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.stage.Stage;
import user.ModifiedPassword;
import query.QueryWord;
import recite.GlossaryInterface;
import recite.ReciteInterface;

@SuppressWarnings("unused")
public class MainInterface extends Application{
	
    private TabPane tp = new TabPane();//��ǩ
    private Tab tbQuery = new Tab();//����
    private Tab tbRecite = new Tab();//������
    private Tab tbUser = new Tab();//�û�����
    private BorderPane bp = new BorderPane();//�߿�
    private TextField tfQuery = new TextField();
    private Button btQuery = new Button("��ѯ");
    private TextArea taQuery = new TextArea();
    private Button btAdd = new Button("�������ʱ�");
    private HBox hb1 = new HBox();//������ť���ᣩ
    
    private BorderPane bpQuery = new BorderPane();
    private ImageView iv_query = new ImageView();
    private ImageView iv_recite = new ImageView();
    private ImageView iv_user = new ImageView();
    
    private Label lbl1 = new Label("�ʿ⣺�ļ�");
    private Label lbl2 = new Label("��������4653��");
    private Label lbl3 = new Label();
    private ToggleGroup tg = new ToggleGroup();//������ѡ��ť
    private RadioButton rbcet4 = new RadioButton("�ļ�");
    private RadioButton rbcet6 = new RadioButton("����");
    private Button btstart = new Button("��ʼ������");
    
    private Button btModified = new Button("�޸�����");
    private Button btGlossary = new Button("���ʱ�");
    private Button btlogoff = new Button("�˳���½");
    private Button btCancle = new Button("ע���˺�");
    
    private VBox vbRecite = new VBox();
    private VBox vbUser = new VBox();
    private HBox hb2 = new HBox();
    private userinfoDB userdb = new userinfoDB();
    private glossaryDB gdb = new glossaryDB();
    private reciteDB rdb = new reciteDB();
    private int id;
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainInterface.launch(args);
	}
	public MainInterface(){
		;
	}
	public MainInterface(int id) {
		this.id = id;
	}
    public void start(Stage primaryStage){
    	lbl3.setText("�ѱ����ʸ�����"+String.valueOf(rdb.getCountforCet4(id)));
    	iv_query.setImage(new Image("/images/query.png"));
    	iv_query.setFitWidth(50);
    	iv_query.setFitHeight(40);
    	
    	iv_recite.setImage(new Image("/images/recite.png"));
    	iv_recite.setFitWidth(50);
    	iv_recite.setFitHeight(40);
    	
    	iv_user.setImage(new Image("/images/user.png"));
    	iv_user.setFitWidth(50);
    	iv_user.setFitHeight(40);
    	
    	
    	tbQuery.setGraphic(iv_query);
    	tbQuery.setClosable(false);
    	tbRecite.setGraphic(iv_recite);
    	tbUser.setClosable(false);
    	tbUser.setGraphic(iv_user);
    	tbRecite.setClosable(false);
    	
    	taQuery.setPrefHeight(540);//�б���ͼ�ؼ���
    	taQuery.setPrefWidth(380);
    	taQuery.setEditable(false);
    	
    	tfQuery.setPrefWidth(255);//������

    	hb1.getChildren().add(tfQuery);
    	hb1.getChildren().add(btQuery);//��ť
    	hb1.getChildren().add(btAdd);
    	
    	bpQuery.setTop(hb1);//������λ�ã��ϣ�
    	bpQuery.setCenter(taQuery);
    	bp.setCenter(tp);
    	tbQuery.setContent(bpQuery);//���ÿ���ΪtbQuery������
    	
    	rbcet4.setToggleGroup(tg);//ѡ��������
    	rbcet4.setSelected(true);
    	rbcet6.setToggleGroup(tg);
    	
    	vbRecite.getChildren().add(lbl1);//��һ��������ӵ�recite��
    	vbRecite.getChildren().add(lbl2);
    	
    	hb2.getChildren().add(rbcet4);//��
    	hb2.getChildren().add(rbcet6);
    	
    	vbRecite.getChildren().add(hb2);
    	vbRecite.getChildren().add(lbl3);
    	vbRecite.getChildren().add(btstart);
    	
    	vbRecite.setSpacing(40);
    	vbRecite.setPrefWidth(400);
    	vbRecite.setPrefHeight(600);
    	vbRecite.setPadding(new Insets(20,20,20,20));
    	vbRecite.setStyle("-fx-background-image: url(\"/images/bg2.jpg\");");
    	tbRecite.setContent(vbRecite);
    	
        vbUser.getChildren().addAll(btModified,btGlossary,btlogoff,btCancle);
    	
    	vbUser.setSpacing(40);
    	vbUser.setPrefWidth(400);
    	vbUser.setPrefHeight(600);
    	vbUser.setPadding(new Insets(20,20,20,20));
    	vbUser.setStyle("-fx-background-image: url(\"/images/bg2.jpg\");");
    	tbUser.setContent(vbUser);
    	
    	
    	//btUser.setOnAction(e->showInfo());
    	btModified.setOnAction(e->ModifiedHandle());
        btQuery.setOnAction(e->{
			try {
				queryHandle();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
        rbcet4.setOnAction(e->{
        	lbl1.setText("�ʿ⣺�ļ�");
        	lbl2.setText("��������4653��");
        	lbl3.setText("�ѱ����ʸ�����"+rdb.getCountforCet4(id));
        });
        rbcet6.setOnAction(e->{
        	lbl1.setText("�ʿ⣺����");
        	lbl2.setText("��������1286��");
        	lbl3.setText("�ѱ����ʸ�����"+rdb.getCountforCet6(id));
        });
        btstart.setOnAction(e->startHandle(id));
        btlogoff.setOnAction(e->logoffHandle(primaryStage));
        btGlossary.setOnAction(e->glossaryHandle(id));
        btCancle.setOnAction(e->cancleHandle(primaryStage));
    	btAdd.setOnAction(e->addHandle());
    	
    	tp.getTabs().add(tbQuery);
    	tp.getTabs().add(tbRecite);
    	tp.getTabs().add(tbUser);
    	/*tp.getSelectionModel().selectedItemProperty().addListener((ov,oldTab,newTab)->{
    		tbQuery.setStyle("-fx-background-color:#fff");
    		tbRecite.setStyle("-fx-background-color:#fff");
    		tbUser.setStyle("-fx-background-color:#fff");
    		Tab temp = tp.getSelectionModel().getSelectedItem();
    		temp.setStyle("-fx-background-color:#1a83e1");
    	});*/
    	//tp.setSide(Side.LEFT);
    	Scene scene = new Scene(new Group(),400,600);
    	Group sceneroot = (Group)scene.getRoot();
    	sceneroot.getChildren().add(bp);
    	//scene.setUserAgentStylesheet(getClass().getResource("maininterface.css").toExternalForm());
    	primaryStage.setTitle("���Ӵʵ�");
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }
    
    private void queryHandle() throws ClientProtocolException, IOException{
    	String word = tfQuery.getText();
    	String meaning = new String();
    	if(word.isEmpty()) {
    		Alert alert = new Alert(Alert.AlertType.INFORMATION,"�����뵥�ʣ�");
    		alert.show();
    	}
    	else {
    		QueryWord qw = new QueryWord();
    		meaning = qw.Query(word);
    		taQuery.setText(meaning);
    	}
    }
    
    private void ModifiedHandle(){           //�޸�����
    	
    	ModifiedPassword m=new ModifiedPassword(id);			
		try	{
			
			m.start(new Stage());							
		} 
		catch (Exception e1){				
				e1.printStackTrace();			
		}			
    }
    private void startHandle(int id) {      //��ʼ������
    	int level = 0;
    	if(rbcet4.isSelected()) {
    		level = 4;
    	}
    	else {
    		level = 6;
    	}
    	ReciteInterface open = new ReciteInterface(id,level);
    	try {
    		open.start(new Stage());
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    private void logoffHandle(Stage stage) { //�˳���½
    	userdb.updateStatus(id, 0);
    	stage.close();
    }
    
    private void cancleHandle(Stage stage) {  //ע���˺�
    	Alert alert = new Alert(AlertType.CONFIRMATION,"ȷ��Ҫע���˺���",new ButtonType("ȡ��",ButtonBar.ButtonData.NO),
    			new ButtonType("ȷ��",ButtonBar.ButtonData.YES));
    	//showAndWait()���ڶԻ�����ʧ��ǰ����ִ��֮��Ĵ���
    	Optional<ButtonType> buttonType = alert.showAndWait();
    	if(buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)){
    		userdb.Delete(id);
    		stage.close();
        }
    	else {
    		alert.close();
    	}
    }
    private void glossaryHandle(int id) {
    	GlossaryInterface open = new GlossaryInterface(id);
    	try {
    		open.start(new Stage());
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    private void addHandle() {
    	int gid = userdb.getGid(id);
    	gdb.AddWord(gid, tfQuery.getText());
    }
}
