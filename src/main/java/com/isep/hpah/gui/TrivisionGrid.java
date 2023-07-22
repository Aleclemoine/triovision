package com.isep.hpah.gui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.isep.hpah.triovision.Card;
import com.isep.hpah.triovision.Coordonate;
import com.isep.hpah.triovision.Pawn;
import com.isep.hpah.triovision.Pawn.PawnColor;
import com.isep.hpah.triovision.Player;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
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
	
	private static final String FORMAT_REMAINING_CARDS = "%s remaining cards";
	

	private List<Pawn> pawns;
	private Map<Player, VBox> playersTable;

	private double orgSceneX, orgSceneY;

	public Pane createDisplay(List<Player> playersArg, List<Pawn> pawnsArg) {

		pawns = pawnsArg;
		playersTable = new HashMap<>();

		VBox p1Box = new VBox();
		p1Box.setPadding(new Insets(BUTTON_PADDING));
		p1Box.setPrefWidth(200);
		Label p1Label = new Label(playersArg.get(0).getName());
		p1Label.setAlignment(Pos.TOP_CENTER);
		p1Label.setStyle("-fx-text-fill: black; -fx-font: normal 20px 'serif' ");
		p1Box.getChildren().add(p1Label);

		GridPane gridCardPlayer1 = new GridPane();
		gridCardPlayer1.setPadding(new Insets(BUTTON_PADDING));
		gridCardPlayer1.setHgap(BUTTON_PADDING);
		gridCardPlayer1.setVgap(BUTTON_PADDING);
		gridCardPlayer1.setBorder(Border.stroke(null));
		displayCard(gridCardPlayer1, playersArg.get(0).getCards());
		p1Box.getChildren().add(gridCardPlayer1);
		Label p1remainingCards = new Label(String.format(FORMAT_REMAINING_CARDS, playersArg.get(0).getCards().size()));
		p1Box.getChildren().add(p1remainingCards);

		playersTable.put(playersArg.get(0), p1Box);
		
		VBox gridWrapper = new VBox();
		gridWrapper.setPrefSize(425, 425);
		GridPane grid = new GridPane();
		grid.setPrefSize(425, 425);
		grid.setPadding(new Insets(BUTTON_PADDING));
		grid.setHgap(BUTTON_PADDING);
		grid.setVgap(BUTTON_PADDING);
		grid.setBorder(Border.stroke(null));
		displayGrid(grid);
		gridWrapper.getChildren().add(grid);

		VBox p2Box = new VBox();
		p2Box.setPadding(new Insets(BUTTON_PADDING));
		p2Box.setPrefWidth(200);
		Label p2Label = new Label(playersArg.get(1).getName());
		p2Label.setAlignment(Pos.TOP_CENTER);
		p2Label.setStyle("-fx-text-fill: black; -fx-font: normal 20px 'serif' ");
		p2Box.getChildren().add(p2Label);

		GridPane gridCardPlayer2 = new GridPane();
		gridCardPlayer2.setPadding(new Insets(BUTTON_PADDING));
		gridCardPlayer2.setHgap(BUTTON_PADDING);
		gridCardPlayer2.setVgap(BUTTON_PADDING);
		gridCardPlayer2.setBorder(Border.stroke(null));
		displayCard(gridCardPlayer2, playersArg.get(1).getCards());
		p2Box.getChildren().add(gridCardPlayer2);
		Label p2remainingCards = new Label(String.format(FORMAT_REMAINING_CARDS, playersArg.get(1).getCards().size()));
		p2Box.getChildren().add(p2remainingCards);
		
		playersTable.put(playersArg.get(1), p2Box);

		HBox box = new HBox(p1Box, gridWrapper, p2Box);

		return box;
	}

	private void displayCard(GridPane grid, List<Card> cards) {
		for (Pawn p : cards.get(0).getPawns()) {
			Circle circle = new Circle(25, Paint.valueOf(p.getColor().toString()));
			grid.add(circle, p.getCoordonate().getX(), p.getCoordonate().getY());
		}
	}

	private void displayGrid(GridPane pawnGrid) {
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				Circle circle = new Circle(50, Paint.valueOf("WHITE"));
				pawnGrid.add(circle, x, y);
			}
		}
		for (Pawn pawn : pawns) {
			Circle circle = new Circle(50, Paint.valueOf(pawn.getColor().toString()));
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

				Circle c = (Circle) (t.getSource());

				// I minus 100 because the circle is 50 radius and with a round, it s working well !
				int newX = (int) (pawn.getCoordonate().getX() + Math.round(offsetX / 100));
				int newY = (int) (pawn.getCoordonate().getY() + Math.round(offsetY / 100));
//				System.out.println("newX : " + newX);
//				System.out.println("newY : " + newY);
				if (newX < 0 || newX >= 4 || newY < 0 || newY >= 4) {
					// TODO: Message "You must do a valid move !" ?
				} else {
					Coordonate newCoordonate = Coordonate.builder().x(newX).y(newY).build();
					boolean isPawnAtTheSamePlace = false;
					for (Pawn pawn2 : pawns) {
						if (pawn2.isAtTheSamePlace(newCoordonate)) {
							isPawnAtTheSamePlace = true;
							break;
						}
					}
					if (!isPawnAtTheSamePlace) {
						
						pawn.getCoordonate().setX(newX);
						pawn.getCoordonate().setY(newY);
	
						pawnGrid.getChildren().remove(c);
						
						pawnGrid.add(c, pawn.getCoordonate().getX(), pawn.getCoordonate().getY());
	
						// check if anyOne win
						for (Entry<Player, VBox> entry : playersTable.entrySet()) {
							Player pl = entry.getKey();
							if (isMatchingCard(pl)) {
								pl.getCards().remove(0);
								VBox vPlayerBox = entry.getValue();
								if (CollectionUtils.isEmpty(pl.getCards())) {
									Label remainingCards = (Label) vPlayerBox.getChildren().get(2);
									remainingCards.setText(String.format(FORMAT_REMAINING_CARDS, pl.getCards().size()));
									Dialog<String> dialog = new Dialog<String>();
									dialog.setTitle("Congrats");
								    dialog.setHeaderText(String.format("The winner is %s", pl.getName()));
								    dialog.setContentText("Thank you for playing");
								    ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
								    dialog.getDialogPane().getButtonTypes().add(type);
								    dialog.setOnHidden(e -> Platform.exit());
								    dialog.showAndWait();
								} else {
									GridPane gridPane = (GridPane) vPlayerBox.getChildren().get(1);
									ObservableList<Node> children = gridPane.getChildren();
									gridPane.getChildren().removeAll(children);
									displayCard(gridPane, pl.getCards());
									Label remainingCards = (Label) vPlayerBox.getChildren().get(2);
									remainingCards.setText(String.format(FORMAT_REMAINING_CARDS, pl.getCards().size()));
								}
							}
						}
					} else {
						// TODO: Message "You must do a valid move !" ?
					}
				}
			});
			pawnGrid.add(circle, pawn.getCoordonate().getX(), pawn.getCoordonate().getY());
		}
	}

	private boolean isMatchingCard(Player pl) {

		PawnColor firstPlayerColor = pl.getCards().get(0).getPawns().get(0).getColor();

		List<Pawn> collectFirsPawnOfGrid = pawns.stream().filter(p -> p.getColor() == firstPlayerColor)
				.collect(Collectors.toList());

		for (Pawn candidate : collectFirsPawnOfGrid) {
			// check the second pawn of the possibility (2 colors of each)
			Pawn secondPlayerPawn = pl.getCards().get(0).getPawns().get(1);
			Pawn thirdPlayerPawn = pl.getCards().get(0).getPawns().get(2);
			for (Pawn pGrid : pawns) {
				if (pGrid.getColor() == secondPlayerPawn.getColor()
						&& pGrid.getCoordonate().getX() == candidate.getCoordonate().getX()
						&& pGrid.getCoordonate().getY() == candidate.getCoordonate().getY() + 1) {
					// we found the 2nd pawn => we ll try to match the third
					for (Pawn pGrid2 : pawns) {
						int absToAdd = thirdPlayerPawn.getCoordonate().getX() == 0 ? -1 : 1;
						if (pGrid2.getColor() == thirdPlayerPawn.getColor()
								&& pGrid2.getCoordonate().getX() == (candidate.getCoordonate().getX() + absToAdd)
								&& pGrid2.getCoordonate().getY() == candidate.getCoordonate().getY() + 2) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}	
}
