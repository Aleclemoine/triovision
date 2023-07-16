package com.isep.hpah.triovision;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.isep.hpah.triovision.Pawn.PawnColor;

import lombok.Data;

@Data
public class Card {

	private List<Pawn> pawns;
	
	public Card() {
		pawns = new ArrayList<>();
		
		Random rn = new Random();
		int choice = rn.nextInt(PawnColor.values().length) + 1;
		int index = 1;
		for (PawnColor pc : PawnColor.values()) {
			if (index++ == choice) {
				pawns.add(Pawn.builder().color(pc).coordonate(Coordonate.builder().x(0).y(0).build()).build());
			}
		}
		
		choice = rn.nextInt(PawnColor.values().length) + 1;
		index = 1;
		for (PawnColor pc : PawnColor.values()) {
			if (index++ == choice) {
				pawns.add(Pawn.builder().color(pc).coordonate(Coordonate.builder().x(0).y(-1).build()).build());
			}
		}
		
		choice = rn.nextInt(PawnColor.values().length) + 1;
		index = 1;
		for (PawnColor pc : PawnColor.values()) {
			if (index++ == choice) {
				pawns.add(Pawn.builder().color(pc).coordonate(Coordonate.builder().x(1).y(-2).build()).build());
			}
		}
	}
}
