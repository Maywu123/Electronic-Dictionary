package recite;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import database.cetDB;
import database.glossaryDB;
import database.reciteDB;
import database.userinfoDB;

public class ReciteInterface extends Application {
    private Button Next =new Button("下一个");
    private Button Meaning=new Button("释义");
    private Button addtoG=new Button("加入生词本");
    private Map<String,String> Dic=new HashMap<>();
    private VBox vb = new VBox();
    private Text En=new Text();
    private Text Ch=new Text();
    private TabPane mainIn=new TabPane();
    private Tab Recite=new Tab();
    private reciteDB rdb = new reciteDB();
    private cetDB cetdb = new cetDB();
    private glossaryDB gdb = new glossaryDB();
    private userinfoDB userdb = new userinfoDB();
    private int level;
    private int id;
    private int count;

    public static void main(String[] args) {
        Application.launch(ReciteInterface.class, args);
    }
    
    public ReciteInterface(int id,int level) {
    	this.id = id;
    	this.level = level;
    }

    @Override
    public void start(Stage primaryStage) {
        Recite.setText("Recite");
        Recite.setContent(ReciteIn());
        Recite.setClosable(false);

        mainIn.getTabs().add(Recite);
        
        Next.setOnAction(e->nextHandle());
        Meaning.setOnAction(e->meaningHandle());
        addtoG.setOnAction(e->addHandle());

        Scene scene=new Scene(mainIn,300,400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dictionary");
        primaryStage.show();
    }

    private Pane ReciteIn(){
        BorderPane RI=new BorderPane();
        RI.setPadding(new Insets(20,0,20,20));

        RI.setLeft(WordField());
        RI.setRight(ButtonCol());
        return RI;
    }

    private VBox WordField(){
    	String word = new String();
        vb.setSpacing(10);
        if(level == 4) {
        	count = rdb.getCountforCet4(id);
        	if(count == 0) {
        		word = cetdb.getWordfromCet4(1);
        	}
        	else {
        		word = cetdb.getWordfromCet4(count);
        	}
        	En.setText(word);
        }
        else {
        	count = rdb.getCountforCet6(id);
        	if(count == 0) {
        		word = cetdb.getWordfromCet6(1);
        	}
        	else {
        		word = cetdb.getWordfromCet6(count);
        	}
        	En.setText(word);
        }
        Ch.setText("");
        vb.getChildren().addAll(En,Ch);
        return vb;
    }

    private VBox ButtonCol(){
        Next.setMaxWidth(Double.MAX_VALUE);
        Meaning.setMaxWidth(Double.MAX_VALUE);
        addtoG.setMaxWidth(Double.MAX_VALUE);

        VBox vbButtons = new VBox();
        vbButtons.setSpacing(10);
        vbButtons.setPadding(new Insets(0, 20, 10, 20));
        vbButtons.getChildren().addAll(Next,Meaning, addtoG);
        return vbButtons;
    }
    
    private void nextHandle() {        //获取下一个单词
    	String word = new String();
    	if(level == 4) {
    		count++;
    		word = cetdb.getWordfromCet4(count);
    		En.setText(word);
    		Ch.setText("");
    		rdb.updateCountforCet4(id, count);
    	}
    	else {
    		count++;
    		word = cetdb.getWordfromCet6(count);
    		En.setText(word);
    		Ch.setText("");
    		rdb.updateCountforCet6(id, count);
    	}
    }
    
    private void meaningHandle() {     //获取单词释义
    	String meaning = new String();
    	if(level == 4) {
    		meaning = cetdb.getMeaningfromCet4(count);
    		Ch.setText(meaning);
    	}
    	else {
    		meaning = cetdb.getMeaningfromCet6(count);
    		Ch.setText(meaning);
    	}
    }
    
    private void addHandle() {       //把单词加入生词本
    	int gid = userdb.getGid(id);
    	gdb.AddWord(gid, En.getText());
    }
}
