package com.yonima.sopeduc.odt.reponse;

public enum Statut {
	
	OK("ok"), KO("ko");

	private String strStatut;

	public String getStrStatut() {
		return strStatut;
	}

	Statut(String strStatut) {
		this.strStatut = strStatut;
	}
	
}
