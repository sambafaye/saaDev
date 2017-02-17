package com.yonima.sopeduc.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class ConfigApplicationPrefix {

	  private static final String NOM_PROPERTIES = "applicationprefix";
	  private static final String NOM_PARAMETRE = "directory";
	  private static String applicationPrefix;

	  /**
	   * Methode retournant l'�ventuel pr�fixe pour les proprietes de l'application
	   * 
	   * Le fichier properties doit porter le nom applicationprefix.properties (integre � l'ear) Le nom de la propriete
	   * fixant le repertoire o� trouver les proprietes locales est: directory
	   * 
	   * Exemple de fichier applicationprefix.properties
	   * 
	   * directory=ccn
	   * 
	   * @return String le nom du repertoire o� rechercher les fichiers properties propre � l'instance
	   */
	  public static String getApplicationPrefix() {
	    if (applicationPrefix == null) {
	      ResourceBundle resourceBundle = null;

	      String localAppPrefix;
	      InputStream configStream = null;
	      try {
	        configStream = Config.class.getResourceAsStream("/" + NOM_PROPERTIES + ".properties");
	        resourceBundle = new PropertyResourceBundle(configStream);
	      } catch (Exception e) {
	        /* Do nothing */
	      } finally {
	        try {
	          if (configStream != null)
	            configStream.close();
	        } catch (IOException e) {
	          // Exception volontairement ignoree
	        }
	      }

	      if (resourceBundle != null) {
	        try {
	          String applicationPrefix = resourceBundle.getString(NOM_PARAMETRE);
	          if (applicationPrefix != null && !"".equals(applicationPrefix.trim())) {
	            localAppPrefix = applicationPrefix + "/";
	          } else {
	            localAppPrefix = "";
	          }
	        } catch (Exception e) {
	          localAppPrefix = "";
	        }

	        applicationPrefix = localAppPrefix;
	      } else {
	        applicationPrefix = "";
	      }
	    }

	    return applicationPrefix;
	  }
}