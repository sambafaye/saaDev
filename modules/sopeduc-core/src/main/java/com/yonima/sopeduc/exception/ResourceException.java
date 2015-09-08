package com.yonima.sopeduc.exception;


/**
 * Exception levée lors d'un probléme avec l'utilisation du package
 * com.yonima.sopeduc.resources.*.
 */
public class ResourceException extends FatalException {
  /**
	 * 
	 */
	private static final long serialVersionUID = 4346415996936284452L;

/**
   * Constructeur prenant en paramétre une Exception.
   * 
   * @param e L'exception à encapsuler.
   */
  public ResourceException(Exception e) {
    super(e);
  }

  /**
   * Contructeur prenant en param�tre lengthmessage qui d�crit le probl�me
   * survenu.
   * 
   * @param msg Le message de l'exception.
   */
  public ResourceException(String msg) {
    super(msg);
  }

  /**
   * Constructeur prenant en pram�tre un message et une exception � encapsuler.
   * 
   * @param msg Le message d'erreur.
   * @param e L'exception � encapsuler.
   */
  public ResourceException(String msg, Exception e) {
    super(msg, e);
  }

  /**
   * Constructeur prenant en pram�tre un message et une exception � encapsuler.
   * 
   * @param msg Le message d'erreur.
   * @param t Le Throwable � encapsuler (sera cast� en Exception).
   */
  public ResourceException(String msg, Throwable t) {
    super(msg, t);
  }
}
