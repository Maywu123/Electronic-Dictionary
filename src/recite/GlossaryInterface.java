package recite;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import main.MainInterface;

import java.util.ArrayList;

import database.glossaryDB;
import database.userinfoDB;

public class GlossaryInterface extends Application {
    private Button Delete=new Button("Delete");
    private Button Clear=new Button("Clear");
    private TabPane mainIn=new TabPane();
    private Tab Glossary=new Tab();
    private ArrayList<String> wordList=new ArrayList<>();
    private ObservableList<String> words = FXCollections.observableArrayList();
    private ListView<String> wordView=new ListView<>(words);
    private glossaryDB gdb = new glossaryDB();
    private userinfoDB userdb = new userinfoDB();
    private int id;

    public GlossaryInterface(int id) {
        this.id = id;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GlossaryInterface.launch(args);
	}

    @Override
    public void start(Stage primaryStage) {
    	
        Delete.setOnAction(e->deleteHandle());
        Clear.setOnAction(e->clearHandle());

        Glossary.setText("生词本");
        Glossary.setContent(GlossaryIn());
        Glossary.setClosable(false);

        mainIn.getTabs().add(Glossary);

        Scene scene=new Scene(mainIn,400,500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dictionary");
        primaryStage.show();
    }

    private Pane GlossaryIn(){
        BorderPane GI = new BorderPane();
        GI.setPadding(new Insets(20, 0, 20, 20));

        GI.setLeft(ListV());
        GI.setRight(createButtonCol());

        return GI;
    }


    private ListView ListV(){
    	int gid = userdb.getGid(id);
        String wl = gdb.getWordList(gid);
        String[] temp = new String[100];
        if(!wl.isEmpty()) {
        	temp = wl.split(",");
        	for(int i = 0; i < temp.length; i++) {
        		words.add(temp[i]);
        	}
        	wordView.setItems(words);
        }
        else {
        	wordView.setItems(null);
        }
        //wordView.setCellFactory(CheckBoxListCell.forListView());
        wordView.setMaxHeight(Control.USE_PREF_SIZE);
        wordView.setPrefWidth(200);
        return wordView;
    }

    private VBox createButtonCol(){
        Delete.setMaxWidth(Double.MAX_VALUE);
        Clear.setMaxWidth(Double.MAX_VALUE);

        VBox vbButtons = new VBox();
        vbButtons.setSpacing(10);
        vbButtons.setPadding(new Insets(0, 20, 10, 20));
        vbButtons.getChildren().addAll(Delete, Clear);
        return vbButtons;
    }
    private void deleteHandle() {
    	String word = wordView.getSelectionModel().getSelectedItem();
    	gdb.DeleteWord(id, word);
    	words.remove(word);
    	wordView.setItems(null);
    	wordView.setItems(words);
    }
    private void clearHandle() {
    	gdb.clearWordList(id);
    	wordView.setItems(null);
    }
}
