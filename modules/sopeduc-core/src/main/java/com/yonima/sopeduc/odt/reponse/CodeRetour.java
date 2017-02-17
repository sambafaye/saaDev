package com.yonima.sopeduc.odt.reponse;

public class CodeRetour {
	
	private Statut statut;
	private int codeRetour;
	private String libCodeRetour;

	public CodeRetour() {}
	
	public CodeRetour(Statut statut, String libCodeRetour,int codeRetour) {
		this.statut = statut;
		this.libCodeRetour = libCodeRetour;
		this.codeRetour = codeRetour;
	}


	public Statut getStatut() {
		return statut;
	}
	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public int getCodeRetour() {
		return codeRetour;
	}

	public void setCodeRetour(int codeRetour) {
		this.codeRetour = codeRetour;
	}

	public String getLibCodeRetour() {
		return libCodeRetour;
	}

	public void setLibCodeRetour(String libCodeRetour) {
		this.libCodeRetour = libCodeRetour;
	}
	

}
