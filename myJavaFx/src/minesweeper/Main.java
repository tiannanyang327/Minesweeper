package minesweeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {	
	private Scene scene;
	private HBox topbar; 
	private HBox bottombar;
	private static final int BOX_SIZE = 40; 
	private static final int W = 800;
	private static final int H = 600; 
	
	private static final int X_BOXES = W / BOX_SIZE;  
	private static final int Y_BOXES = H / BOX_SIZE;
	private Box[][] grid = new Box [X_BOXES][Y_BOXES]; 
	
//	ObservableList<String> levelBoxList = FXCollections.observableArrayList("Easy","Medium","Hard");
//	@FXML
//	private ChoiceBox<String> levelBox; 

	
	private Parent createContent() {
		VBox root = new VBox();
		Pane gamePane = new Pane();
		gamePane.setPrefSize(W, H);
		
		
		for (int y = 0; y < Y_BOXES; y++) {
			for (int x = 0; x< X_BOXES; x++ ) {
				Box box = new Box(x, y, Math.random()< 0.2);
				grid[x][y]=box;
				gamePane.getChildren().add(box);
			}
		}
		
		root.getChildren().addAll(this.topbar,gamePane,this.bottombar);
		return root;
		
	}
	
	private class Box extends StackPane {
		private boolean isOpen = false;
		
		private Rectangle boxborder = new Rectangle(BOX_SIZE, BOX_SIZE);
		private Text text = new Text();
		
		public Box(int x, int y, boolean hasBomb) {
			boxborder.setStroke(Color.LIGHTGRAY);
			
			text.setText("!");
			text.setFont(Font.font(18));
			text.setVisible(false);
			
			getChildren().addAll(boxborder, text); 
			
			setTranslateX (x * BOX_SIZE); 
			setTranslateY (y * BOX_SIZE); 
			
			setOnMouseClicked(e->open());
		}
		
		public void open() {
			if(isOpen)
				return; 
			
			isOpen = true; 
			text.setVisible(true);
			boxborder.setFill(null);
			
		}
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		stage.show();
		stage.setTitle("Minesweeper Game");
		showTopBar();
		showBottomBar();
		scene = new Scene(createContent());
		stage.setScene(scene);
	}
	
//	@FXML
//	private void initialize() {
//		levelBox.setItems(levelBoxList);
//		levelBox.setValue("Easy");
//	}
	
	private void showTopBar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/TopBar.fxml"));
		topbar = loader.load();
	}
	private void showBottomBar() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/BottomBar.fxml"));
		bottombar = loader.load();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

}

// The above code is taking reference from this tutorial video https://www.youtube.com/watch?v=JwcyxuKko_M. 
