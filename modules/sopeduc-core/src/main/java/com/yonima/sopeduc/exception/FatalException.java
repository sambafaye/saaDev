package com.yonima.sopeduc.exception;

/**
 * Classe m�re de toutes les exceptions de type Fatal. Une classe de type fatal
 * indique normalement un probl�me insurmontable. Etend
 * <code>RuntimeException</code> Permet l'encapsulation d'une Exception.
 */
public abstract class FatalException extends RuntimeException {

  private static final long serialVersionUID = 6241558758036780518L;

  /** L'Exception encapsul�e. */
  protected Exception exception = null;

  /**
   * Le num�ro identifiant l'exception dans son contexte d'ex�cution
   */
  protected int errorId = SOPErrors.DEFAULT_FATAL_ERROR_ID;

  /**
   * Constructeur prenant en param�tre une Exception.
   * 
   * @param exception L'exception � encapsuler.
   */
  public FatalException(Exception exception) {
    this("", exception);
  }

  /**
   * Constructeur prenant un param�tre le message d'erreur.
   * 
   * @param message Le message d'erreur.
   */
  public FatalException(String message) {
    this(message, (Exception) null);
  }

  /**
   * Constructeur prenant un param�tre le message d'erreur.
   * 
   * @param message Le message d'erreur.
   */
  public FatalException(String message, int errorId) {
    this(message, (Exception) null, errorId);
  }

  /**
   * Constructeur prenant en param�tre un message et une exception � encapsuler.
   * 
   * @param message Le message d'erreur.
   * @param throwable Le Throwable � encapsuler (sera cast� en Exception).
   */
  public FatalException(String message, Throwable throwable) {
    this(message, throwable, SOPErrors.DEFAULT_FATAL_ERROR_ID);
  }

  /**
   * Constructeur prenant en param�tre un code d'erreur, un message et une
   * exception � encapsuler.
   * 
   * @param message Le message d'erreur.
   * @param throwable Le Throwable � encapsuler (sera cast� en Exception).
   * @param errorId Le code d'erreur
   */
  public FatalException(String message, Throwable throwable, int errorId) {
    super(message);
    this.errorId = errorId;
    if (throwable == null) {
      exception = null;
    } else {
      if (throwable instanceof Exception) {
        exception = (Exception) throwable;
      } else {
        exception = new Exception("Erreur : " + throwable.toString());
      }
    }
  }

  /**
   * Constructeur sans argument.
   */
  protected FatalException() {
    this("", (Exception) null);
  }

  public int getErrorId() {
    return errorId;
  }

  /**
   * M�thode getWrappedException, retourne l'exception encapsul�.
   * 
   * @return java.lang.Exception L'exception encapsul�. ATTENTION, retourne
   *         <code>null</code> si aucune exception n'est encapsul�. Utilis� la
   *         m�thode <code>hasWrappedException()</code> afin de le determiner.
   */
  public final Exception getWrappedException() {
    return this.getException();
  }

  /**
   * M�thode hasWrappedException, teste l'existence d'une exception encapsul�.
   * 
   * @return boolean true si il y a une exception encapsul�, false sinon.
   */
  public final boolean hasWrappedException() {
    if (exception != null) {
      return true;
    }
    return false;
  }

  /**
   * 
   * @return java.lang.Exception L'exception encapsul�.
   */
  protected Exception getException() {
    return exception;
  }

}