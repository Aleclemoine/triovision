package com.isep.hpah.gui;

import java.util.List;

import com.isep.hpah.triovision.Card;
import com.isep.hpah.triovision.Coordonate;
import com.isep.hpah.triovision.Pawn;
import com.isep.hpah.triovision.Player;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import lombok.Data;

@Data
public class TrivisionGrid {

	private static final double BUTTON_PADDING = 5;

	private List<Pawn> pawns;
	
	private double orgSceneX, orgSceneY;
	
	public Pane createDisplay(List<Player> players, List<Pawn> pawns) {
		
		VBox p1Box = new VBox();
		p1Box.setPadding(new Insets(BUTTON_PADDING));
		p1Box.setPrefWidth(200);
		Label p1Label = new Label("Player 1");
		p1Label.setAlignment(Pos.TOP_CENTER);
		p1Label.setStyle("-fx-text-fill: black; -fx-font: normal 20px 'serif' ");
		p1Box.getChildren().add(p1Label);
		
		GridPane gridCardPlayer1 = new GridPane();
		gridCardPlayer1.setPadding(new Insets(BUTTON_PADDING));
		gridCardPlayer1.setHgap(BUTTON_PADDING);
		gridCardPlayer1.setVgap(BUTTON_PADDING);
		gridCardPlayer1.setBorder(Border.stroke(null));
		displayCard(gridCardPlayer1, players.get(0).getCards());
		p1Box.getChildren().add(gridCardPlayer1);
		
		
		VBox gridWrapper = new VBox();
		gridWrapper.setPrefSize(425, 425);
		GridPane grid = new GridPane();
		grid.setPrefSize(425, 425);
		grid.setPadding(new Insets(BUTTON_PADDING));
		grid.setHgap(BUTTON_PADDING);
		grid.setVgap(BUTTON_PADDING);
		grid.setBorder(Border.stroke(null));
		displayGrid(grid, pawns);
		gridWrapper.getChildren().add(grid);
		
		VBox p2Box = new VBox();
		p2Box.setPadding(new Insets(BUTTON_PADDING));
		p2Box.setPrefWidth(200);
		Label p2Label = new Label("Player 2");
		p2Label.setAlignment(Pos.TOP_CENTER);
		p2Label.setStyle("-fx-text-fill: black; -fx-font: normal 20px 'serif' ");
		p2Box.getChildren().add(p2Label);
		
		GridPane gridCardPlayer2 = new GridPane();
		gridCardPlayer2.setPadding(new Insets(BUTTON_PADDING));
		gridCardPlayer2.setHgap(BUTTON_PADDING);
		gridCardPlayer2.setVgap(BUTTON_PADDING);
		gridCardPlayer2.setBorder(Border.stroke(null));
		displayCard(gridCardPlayer2, players.get(1).getCards());
		p2Box.getChildren().add(gridCardPlayer2);
		
		HBox box = new HBox(p1Box, gridWrapper, p2Box);
		
		return box;
	}
	
	public void displayCard(GridPane grid, List<Card> cards) {
		for (Pawn p : cards.get(0).getPawns()) {
			Circle circle = new Circle(25, Paint.valueOf(p.getColor().toString()));
			grid.add(circle, p.getCoordonate().getX(), p.getCoordonate().getY());
		}
	}
	
	public void displayGrid(GridPane grid, List<Pawn> pawns) {
		for (Pawn p : pawns) {
			Circle circle = new Circle(50, Paint.valueOf(p.getColor().toString()));
			circle.setCursor(Cursor.HAND);

			circle.setOnMousePressed((t) -> {
				orgSceneX = t.getSceneX();
				orgSceneY = t.getSceneY();

				Circle c = (Circle) (t.getSource());
				c.toFront();
			});
			circle.setOnMouseReleased((t) -> {
				double offsetX = t.getSceneX() - orgSceneX;
				double offsetY = t.getSceneY() - orgSceneY;

				System.out.println("offsetX : " + Math.round(offsetX/100));
				System.out.println("offsetY : " + Math.round(offsetY/100));
				
				Circle c = (Circle) (t.getSource());

				int newX = (int) (p.getCoordonate().getX()+ Math.round(offsetX/100));
				int newY = (int) (p.getCoordonate().getY()+ Math.round(offsetY/100));
				Coordonate newCoordonate = Coordonate.builder().x(newX).y(newY).build();
				boolean isPawnAtTheSamePlace = false;
				for (Pawn p2 : pawns) {
					if (p2.isAtTheSamePlace(newCoordonate)) {
						isPawnAtTheSamePlace = true;
						break;
					}
				}
				if (!isPawnAtTheSamePlace) {
					p.getCoordonate().setX(newX);
					p.getCoordonate().setY(newY);
					
					grid.getChildren().remove(c);
					grid.add(c, p.getCoordonate().getX(),p.getCoordonate().getY());
					// TODO: check if anyOne win
				} else {
					// TODO: Message "You must do a valid move !"
				}
			});
			grid.add(circle, p.getCoordonate().getX(), p.getCoordonate().getY());
		}
	}
}
