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
		int color1 = rn.nextInt(PawnColor.values().length) + 1;
		int index = 1;
		for (PawnColor pc : PawnColor.values()) {
			if (index++ == color1) {
				pawns.add(Pawn.builder().color(pc).coordonate(Coordonate.builder().x(1).y(0).build()).build());
			}
		}
		
		int color2 = rn.nextInt(PawnColor.values().length) + 1;
		index = 1;
		for (PawnColor pc : PawnColor.values()) {
			if (index++ == color2) {
				pawns.add(Pawn.builder().color(pc).coordonate(Coordonate.builder().x(1).y(1).build()).build());
			}
		}
		
		
		int color3 = rn.nextInt(PawnColor.values().length) + 1;
		if (color1 == color2 && color1 == color3) {
			color3 = ((color3+1)%4)+1;
		}
		int abs = rn.nextInt(2)+1;
		index = 1;
		for (PawnColor pc : PawnColor.values()) {
			if (index++ == color3) {
				pawns.add(Pawn.builder().color(pc).coordonate(Coordonate.builder().x(abs == 1 ? 0 : 2).y(2).build()).build());
			}
		}
	}
}
