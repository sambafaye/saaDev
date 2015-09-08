package com.yonima.sopeduc.odt.reponse;



public class OdtReponse extends ObjetDeTransport {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1552479154635676698L;
	private CodeRetour codeRetour;

	public CodeRetour getCodeRetour() {
		return codeRetour;
	}

	public void setCodeRetour(CodeRetour codeRetour) {
		this.codeRetour = codeRetour;
	}
	
	/**
	 * Fixe le code retour à OK, le code à 0 et le libelle à null
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public <T extends OdtReponse> T setRetourOK() {
		this.codeRetour = new CodeRetour(Statut.OK, null, 0);
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	public <T extends OdtReponse> T setRetourOK(int code, String libelle) {
		this.codeRetour = new CodeRetour(Statut.OK, libelle, code);
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	public <T extends OdtReponse> T setRetourKO(int code, String libelle) {
		this.codeRetour = new CodeRetour(Statut.KO, libelle, code);
		return (T) this;
	}

}
