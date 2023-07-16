package com.isep.hpah.triovision;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pawn {

	private Coordonate coordonate;
	private PawnColor color;
	
	public enum PawnColor {
		RED, BLUE, YELLOW, GREEN;
	}
}
