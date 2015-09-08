package com.yonima.sopeduc.exception;

/**
 * Interface principale des Exceptions du Framework. Elle défini des constantes
 * qui seront accessible à partir de chaque Exception.
 */
public interface SOPErrors {
	  /**
	   * Constante qui défini le code d'erreur par défaut.
	   */
	  public static final int DEFAULT_ERROR_ID = -1;

	  /**
	   * Constante qui défini le code d'erreur non defini ou inconnu.
	   */
	  public static final int UNDEFINED_ERROR_ID = -999999;

	  /**
	   * Constante qui défini le code d'erreur concernant les exceptions fatales.
	   */
	  public static final int DEFAULT_FATAL_ERROR_ID = 100;
}
