package com.yonima.sopeduc.exception;


@javax.ejb.ApplicationException(rollback = true)
public class ApplicationException extends RuntimeException {

	  private static final long serialVersionUID = -4602930635925494841L;

	  private int code = SOPErreur.ERR_INCONNUE.getCode();

	  public ApplicationException() {
	    super(SOPErreur.ERR_INCONNUE.getDescription());
	  }

	  public ApplicationException(Erreur erreur, Throwable cause) {
	    super(erreur.getDescription(), cause);
	    this.code = erreur.getCode();
	  }

	  public ApplicationException(Erreur erreur) {
	    super(erreur.getDescription());
	    this.code = erreur.getCode();
	  }

	  public ApplicationException(String message) {
	    super(message);
	  }

	  public ApplicationException(String message, Throwable cause) {
	    super(message, cause);
	  }

	  public ApplicationException(Throwable cause) {
	    super(SOPErreur.ERR_INCONNUE.getDescription(), cause);
	  }

	  public int getCode() {
	    return code;
	  }
}
