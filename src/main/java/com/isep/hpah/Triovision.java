package com.isep.hpah;

import java.util.ArrayList;
import java.util.List;

import com.isep.hpah.gui.TrivisionGrid;
import com.isep.hpah.triovision.Card;
import com.isep.hpah.triovision.Coordonate;
import com.isep.hpah.triovision.Pawn;
import com.isep.hpah.triovision.Pawn.PawnColor;
import com.isep.hpah.triovision.Player;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Triovision extends Application {

	private List<Pawn> pawns;
	
	private List<Player> players;
	
	@Override
	public void start(Stage stage) throws Exception {
		TrivisionGrid g = new TrivisionGrid();
		
		initPlayers();
		initPawns();
		
		stage.setScene(new Scene(g.createDisplay(players, pawns)));
	    stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	private void initPawns() {
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
	
	private void initPlayers() {
		players = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			Player p = new Player();
			p.setName("Player " + (i+1));
			initCards(p);
			players.add(p);
		}
	}
	
	
	private void initCards(Player p) {
		List<Card> cards = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			cards.add(new Card(true));
		}
		p.setCards(cards);
	}
}
