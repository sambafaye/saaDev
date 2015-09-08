package com.yonima.sopeduc.resources;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import com.yonima.sopeduc.exception.ResourceException;

/**
 * Classe d�finissant un manager de configuration donn�e : une seule instance
 * pour un fichier de configuration donn�. Instanci� par la classe statique
 * <code>ConfigManager</code>.
 */
public class Config {

  public static Config create(String nomConfig) throws ResourceException {
    if (nomConfig == null) {
      throw new ResourceException(
          "Could not instantiate Config, config name is null");
    }
    Config config = new Config();
    ResourceBundle resourceBundle = null;
    String uid = null;
    InputStream configStream = null;
    try {
      String applicationPrefix = ConfigApplicationPrefix.getApplicationPrefix();
      String configFileName;
      
      // Cherche le fichier de properties dans le classpath en utilisant le prefixe applicatif
      configFileName = "/" + applicationPrefix + nomConfig + ".properties";
      configStream = Config.class.getResourceAsStream(configFileName);
      
      if (configStream == null && applicationPrefix != null) {
    	  // Si le fichier de properties n'a pas ete trouve avec le prefix, le chercher sans le prefix
    	  configFileName = "/" + nomConfig + ".properties";
    	  configStream = Config.class.getResourceAsStream(configFileName);
      }

      resourceBundle = new PropertyResourceBundle(configStream);
      uid = Config.getUID(nomConfig);
    } catch (MissingResourceException e) {
      throw new ResourceException("Fichier de configuration " + nomConfig
          + ".properties manquant.", e);
    } catch (NullPointerException npe) {
      throw new ResourceException(
          "Probleme lecture : Fichier de configuration " + nomConfig
              + ".properties", npe);
    } catch (IOException ioe) {
      throw new ResourceException(
          "Probleme lecture : Fichier de configuration " + nomConfig
              + ".properties", ioe);
    } finally {
      try {
            if (configStream != null) configStream.close();
      } catch (IOException e) {
            // Exception volontairement ignoree
      }
    }
    config.setResourceBundle(resourceBundle);
    config.uid = uid;
    config.filename = nomConfig;
    return config;
  }
  private static String getUID(String filename) {
    String configFileName;
    URL url;
    
    String applicationPrefix = ConfigApplicationPrefix.getApplicationPrefix();
    configFileName = "/" + applicationPrefix + filename + ".properties";
    url = Config.class.getResource(configFileName);
    if (url == null && applicationPrefix != null) {
    	configFileName = "/" + filename + ".properties";
    	url = Config.class.getResource(configFileName);
    }
    
    File f = new File(url.getFile());
    return "" + f.lastModified();
  }
  /**
   * Fichier de propri�t�s associ� � l'instance de Config.
   */
  private ResourceBundle m_Config;

  private String uid;

  private String filename;

  /**
   * Constructeur de la classe Config. Lit le fichier de configuration pass� en
   * param�tre.
   * 
   * @param nomConfig Nom du fichier de propri�t�s (sans le ".properties").
   *          History 11 Dec 2003: GDL, modified constructor in order to manage
   *          refresh() feature on ConfigManager
   * @deprecated use Config.create(String nomConfig) instead
   */
  public Config(String nomConfig) throws ResourceException {
    Config.create(nomConfig);
  }

  /**
   * Constructeur de la classe Config. Lit le fichier de configuration pass� en
   * param�tre.
   * 
   * @param nomConfig Nom du fichier de propri�t�s (sans le ".properties").
   *          History 11 Dec 2003: GDL, modified constructor in order to manage
   *          refresh() feature on ConfigManager
   */
  protected Config() {
    super();
  }

  public String getFileName() {
    return filename;
  }

  /**
   * Retourne la liste des propri�t� d�tenues.
   * 
   * @return java.util.Enumeration La liste des clefs.
   */
  @SuppressWarnings({"rawtypes" })
  public Enumeration getKeys() throws ResourceException {
    return m_Config.getKeys();
  }

  /**
   * Retourne une propri�t� bool�nne.
   * 
   * @param name Entr�e recherch�.
   * @return boolean Retourne la valeur de l'entr�e.
   * @throws com.yonima.sopeduc.ResourceException Levée si l'entrée n'est
   *           pas trouvée.
   */
  public boolean getPropertyBoolean(String name) throws ResourceException {
    String val = getSafeProperty(name);
    return new Boolean(val.trim()).booleanValue();
  }

  /**
   * Retourne une propri�t� bool�nne.
   * 
   * @param name Entr�e recherch�.
   * @param defaut Valeur par d�faut si entr�e non trouv�e.
   * @return boolean Retourne la valeur de l'entr�e si elle existe sinon la
   *         valeur par d�faut.
   */
  public boolean getPropertyBoolean(String name, boolean defaut) {
    String buff = null;
    try {
      buff = m_Config.getString(name);
      return new Boolean(buff.trim()).booleanValue();
    } catch (MissingResourceException e) {
      return defaut;
    }
  }

  /**
   * Retourne la valeur du param�tre name.
   * 
   * @param name Entrée recherché.
   * @return int Retourne la valeur de l'entr�e.
   * @throws com.yonima.sopeduc.exception.resources.ResourceException Levée si l'entrée n'est
   *           pas trouvée.
   * @throws java.lang.NumberFormatException Levée si la valeur n'est pas un
   *           entier.
   */
  public int getPropertyInt(String name) throws NumberFormatException,
      ResourceException {
    String buff = getSafeProperty(name);
    return Integer.parseInt(buff);
  }

  /**
   * Retourne la valeur enti�re du param�tre name.
   * 
   * @param name Nom du param�tre.
   * @param defaut Valeur par d�faut.
   * @return int Retourne la valeur de l'entr�e si l'entr�e existe sinon la
   *         valeur par d�faut.
   */
  public int getPropertyInt(String name, int defaut) {
    int ret;
    String buff = null;
    try {
      buff = m_Config.getString(name);
    } catch (MissingResourceException e) {
      return defaut;
    }
    try {
      ret = Integer.parseInt(buff);
    } catch (NumberFormatException e) {
      ret = defaut;
    }
    return ret;
  }

  public long getPropertyLong(String name) throws NumberFormatException,
      ResourceException {
    String buff = getSafeProperty(name);
    return Long.parseLong(buff);
  }

  /**
   * Retourne la valeur enti�re du param�tre name.
   * 
   * @param name Nom du param�tre.
   * @param defaut Valeur par d�faut.
   * @return int Retourne la valeur de l'entr�e si l'entr�e existe sinon la
   *         valeur par d�faut.
   */
  public long getPropertyLong(String name, long defaut) {
    long ret;
    String buff = null;
    try {
      buff = m_Config.getString(name);
    } catch (MissingResourceException e) {
      return defaut;
    }
    try {
      ret = Long.parseLong(buff);
    } catch (NumberFormatException e) {
      ret = defaut;
    }
    return ret;
  }

  /**
   * Retourne la valeur du paramétre name.
   * 
   * @param name Nom du paramétre.
   * @return java.lang.String Retourne la valeur de l'entrée.
   * @throws com.yonima.resources ResourceException levée si l'entrée
   *           n'existe pas.
   */
  public String getPropertyStr(String name) throws ResourceException {
    return getSafeProperty(name);
  }

  /**
   * Retourne la valeur du param�tre name.
   * 
   * @param name Nom du paramètre.
   * @param defaut Valeur par défaut.
   * @return java.lang.String Retourne la valeur de l'entr�e si elle existe
   *         sinon la valeur par défaut.
   */
  public String getPropertyStr(String name, String defaut) {
    String value;
    try {
      value = m_Config.getString(name);
      return value;
    } catch (MissingResourceException e) {
      return defaut;
    }
  }

  public String getUid() {
    return uid;
  }

  public boolean hasChanged() {
    String uid = Config.getUID(filename);
    if (!this.uid.equals(uid)) {
      this.uid = uid;
      return true;
    }
    return false;
  }

  public void setFileName(String filename) {
    this.filename = filename;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  /**
   * Retourne la valeur du param�tre d�sign� par key.
   * 
   * @param key La clé qui identifie la propriété.
   * @return java.lang.String La valeur de la clé correspondante.
   * @throws com.yonima.sopeduc.exception.ResourceException Levée si la clef
   *           n'existe pas.
   */
  private String getSafeProperty(String key) throws ResourceException {
    String ret;
    try {
      ret = m_Config.getString(key);
      return ret;
    } catch (MissingResourceException mre) {
      throw new ResourceException("Clef inexistante : " + key);
    }
  }

  private void setResourceBundle(ResourceBundle resourceBundle)
      throws ResourceException {
    if (resourceBundle == null) {
      throw new ResourceException("ResourceBundle is null");
    } else {
      this.m_Config = resourceBundle;
    }
  }
}