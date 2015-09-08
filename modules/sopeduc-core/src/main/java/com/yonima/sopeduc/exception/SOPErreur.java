package com.yonima.sopeduc.exception;

public enum SOPErreur implements Erreur {

	  ERR_INCONNUE(1, "Erreur inconnue"), ERR_CTRL_SURFACE(2, "Erreur lors du contr�le des param�tres");

	  private int code;
	  private String description;

	  private SOPErreur(int code, String description) {
	    this.code = code;
	    this.description = description;
	  }

	  public int getCode() {
	    return code;
	  }

	  public String getDescription() {
	    return description;
	  }
}
