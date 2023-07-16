package com.isep.hpah;

import java.util.ArrayList;
import java.util.List;

import com.isep.hpah.gui.TrivisionGrid;
import com.isep.hpah.triovision.Card;
import com.isep.hpah.triovision.Coordonate;
import com.isep.hpah.triovision.Pawn;
import com.isep.hpah.triovision.Pawn.PawnColor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Triovision extends Application {

	private List<Pawn> pawns;
	
	private List<Card> cards;
	
	@Override
	public void start(Stage stage) throws Exception {
		TrivisionGrid g = new TrivisionGrid();
		
		initPawns();
		
		stage.setScene(new Scene(g.createDisplay(pawns)));
	    stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public void initPawns() {
		pawns = new ArrayList<>();
		pawns.add(Pawn.builder().color(PawnColor.GREEN).coordonate(Coordonate.builder().x(1).y(0).build()).build());
		pawns.add(Pawn.builder().color(PawnColor.GREEN).coordonate(Coordonate.builder().x(2).y(0).build()).build());
		pawns.add(Pawn.builder().color(PawnColor.RED).coordonate(Coordonate.builder().x(0).y(1).build()).build());
		pawns.add(Pawn.builder().color(PawnColor.RED).coordonate(Coordonate.builder().x(0).y(2).build()).build());
		pawns.add(Pawn.builder().color(PawnColor.YELLOW).coordonate(Coordonate.builder().x(3).y(1).build()).build());
		pawns.add(Pawn.builder().color(PawnColor.YELLOW).coordonate(Coordonate.builder().x(3).y(2).build()).build());
		pawns.add(Pawn.builder().color(PawnColor.BLUE).coordonate(Coordonate.builder().x(1).y(3).build()).build());
		pawns.add(Pawn.builder().color(PawnColor.BLUE).coordonate(Coordonate.builder().x(2).y(3).build()).build());
	}
	
	public void initCards() {
		cards = new ArrayList<>();
		for (int i = 0; i < 12; i++) {
			cards.add(new Card());
		}
	}
}
