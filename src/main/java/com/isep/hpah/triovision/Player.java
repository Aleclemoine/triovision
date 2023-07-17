package com.isep.hpah.triovision;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {

	private String name;
	private List<Card> cards;
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return name.equals(((Player) obj).getName());
	}
}
