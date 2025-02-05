package Zheng.Kobe;

import org.w3c.dom.events.Event;

import com.sun.prism.paint.Color;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
* Purpose: Holds and runs the GUI for the game
* @author Kobe Zheng
* @date Dec 20, 2024
*/
public class GuiDriver extends Application {
	int TR = 0;
	int total = 0;
	Die d1 = new Die();
	int rndNum = 0;

	int roll2 = 0;
	boolean rolled = false;
	int pScore = 0;
	int tScore = 0;
	String pt = "";
	int p2 = 0;
	int p1 = 0;
	
	
	/**
	 * Purpose: renders the stage
	 * @param the stage
	 * @param none
	 */
	@Override
	public void start(Stage stage) throws Exception {
		VBox vbox = new VBox(10);

		// Create and display the title
		Label title = new Label("Shut The Box");
		Label turn = new Label("Player 1 is playing");
		vbox.getChildren().addAll(title, turn);

		HBox tileBox = new HBox(10);
		HBox menu = new HBox(10);
		Button[] tileBtns = new Button[9];

		Tile[] tiles = new Tile[9];

		for (int i = 0; i < tileBtns.length; i++) {
			tileBtns[i] = new Button(String.valueOf(i + 1));
			tileBtns[i].setStyle("-fx-background-color: gray;");
			tiles[i] = new Tile(i + 1);
			tileBox.getChildren().add(tileBtns[i]);
		}
		tileBox.setAlignment(Pos.CENTER);
		vbox.getChildren().add(tileBox);

		// Creates buttons
		Button btnRoll = new Button("ROLL DICE/DIE");
		Button reset = new Button("Reset");
		Button submit = new Button("Submit");
		Button endRnd = new Button("End Round");

		Label result = new Label("Result");
		Label lblValue = new Label(); // output of results
		Label rounds = new Label("Round(s): 0");
		Label p1Score = new Label("P1: ");
		Label p2Score = new Label("P2: ");

		VBox score = new VBox(2);
		score.getChildren().addAll(p1Score, p2Score);
		score.setAlignment(Pos.CENTER_LEFT);

		vbox.getChildren().addAll(menu, rounds, submit, result, lblValue, score);
		vbox.setAlignment(Pos.CENTER);
		menu.getChildren().addAll(btnRoll, reset, endRnd);
		menu.setAlignment(Pos.CENTER);

		// event handler for when the roll button is clicked
		btnRoll.setOnAction(e -> {
			// rolls the dice
			int roll = d1.roll();
			int roll2 = d1.roll();
			// checks if the 7,8,9 tiles are up
			if (tiles[6].isUp() == false && tiles[7].isUp() == false && tiles[8].isUp() == false) {
				roll2 = 0;
			}

			// adds the total roll
			TR = roll + roll2;
			total = roll + roll2;
			btnRoll.setDisable(true);
			btnRoll.disableProperty();
			rolled = true;

			for (int i = 0; i < tileBtns.length; i++) {
				int index = i;
				// checks if any of the tile buttons have been clicked
				tileBtns[i].setOnAction(f -> {
					System.out.println(tiles[index].isUp());
					// changes background color if the tile is clicked and is up
					if (tiles[index].isUp() && TR - index >= 1) {
						tileBtns[index].setStyle("-fx-background-color: #00FF00;");
						TR -= index + 1;
						tiles[index].select();

					}

				});
			}

			lblValue.setText(String.valueOf(roll + roll2));
		});

		reset.setOnAction(a -> {
			// changes back to the default color and the tile resets
			for (int i = 0; i < tileBtns.length; i++) {
				if (tiles[i].isUp()) {
					tileBtns[i].setStyle("-fx-background-color: gray;");
					tiles[i].deselect();
				}

				TR = total;
			}
		});

		submit.setOnAction(g -> {

			if (TR == 0) {

				btnRoll.setDisable(false);
				rounds.setText(String.valueOf("Round(s): " + rndNum));

				// changes to red once they have submitted their answer
				for (int i = 0; i < tileBtns.length; i++) {
					if (tiles[i].selected()) {
						tileBtns[i].setStyle("-fx-background-color: red;");
						tiles[i].putDown();

					}
				}
			}

		});

		endRnd.setOnAction(j ->

		{

			if (rolled) {
				rolled = false;
				rndNum++;
				btnRoll.setDisable(false);
				rounds.setText(String.valueOf("Round(s): " + rndNum));

				for (int i = 0; i < tileBtns.length; i++) {
					if (tiles[i].isUp()) {
						pScore = (pScore + tiles[i].getValue());

					}

				}
				
				tScore = tScore + pScore;
				pScore = 0;

				for (int i = 0; i < tileBtns.length; i++) {

					tileBtns[i].setStyle("-fx-background-color: gray;");
					tiles[i].deselect();
					tiles[i].putUp();

				}

				System.out.println(tScore);

				if (rndNum == 5) {
					p1 = tScore;
					turn.setText("Player 2 is playing");
					System.out.println("Works");

					p1Score.setText("P1: " + tScore);
					tScore = 0;
				}

				if (rndNum == 10) {
					p2 = tScore;
					p2Score.setText("P2: " + tScore);
					submit.setDisable(true);
					btnRoll.setDisable(true);
					reset.setDisable(true);
					endRnd.setDisable(true);
					for (int i=0;i<tileBtns.length;i++) {
						tileBtns[i].setDisable(true);
					}
					if (p1 < p2) {
						turn.setText("Player One Has Won!");
					}
					else if(p2<p1) {
						turn.setText("Player Two Has Won!");
					}

				}

			}

		});

		Scene scene = new Scene(vbox, 500, 300);
		stage.setScene(scene);

		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}