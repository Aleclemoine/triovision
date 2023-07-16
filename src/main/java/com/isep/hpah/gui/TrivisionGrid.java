package com.isep.hpah.gui;

import java.util.List;

import com.isep.hpah.triovision.Card;
import com.isep.hpah.triovision.Pawn;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
	
	

	public Pane createDisplay(List<Pawn> pawns) {
		
		VBox p1Box = new VBox();
		p1Box.setPrefWidth(200);
		Label p1Label = new Label("Player 1");
		p1Label.setAlignment(Pos.TOP_CENTER);
		p1Label.setStyle("-fx-text-fill: black; -fx-font: normal 20px 'serif' ");
		p1Box.getChildren().add(p1Label);
		
		VBox gridWrapper = new VBox();
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(BUTTON_PADDING));
		grid.setHgap(BUTTON_PADDING);
		grid.setVgap(BUTTON_PADDING);
		grid.setBorder(Border.stroke(null));
		displayGrid(grid, pawns);
		gridWrapper.getChildren().add(grid);
		
		VBox p2Box = new VBox();
		p2Box.setPrefWidth(200);
		Label p2Label = new Label("Player 2");
		p2Label.setAlignment(Pos.TOP_CENTER);
		p2Label.setStyle("-fx-text-fill: black; -fx-font: normal 20px 'serif' ");
		p2Box.getChildren().add(p2Label);
		
		HBox box = new HBox(p1Box, gridWrapper, p2Box);
		
		return box;
	}
	
	public void displayGrid(GridPane grid, List<Pawn> pawns) {
//		for (int i = 0; i < 4; i++) {
//			for (int j = 0; j < 4; j++) {
//				grid.add(new Circle(50, Paint.valueOf("RED")), i, j);
//			}
//		}
		for (Pawn p : pawns) {
			grid.add(new Circle(50, Paint.valueOf(p.getColor().toString())), p.getCoordonate().getX(),
					p.getCoordonate().getY());
		}
	}
}
