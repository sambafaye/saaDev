package com.yonima.sopeduc.resources;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import com.yonima.sopeduc.exception.ResourceException;

/**
 * Classe gérant les fichiers de propriétés (les objets Config).
 */
public class ConfigManager implements Runnable {

  private static ConfigManager m_ConfigManager;
  static private Hashtable m_HConfig = new Hashtable();
  public static ConfigManager getInstance() {
    if (m_ConfigManager == null) {
      synchronized (ConfigManager.class) {
        if (m_ConfigManager == null)
          m_ConfigManager = new ConfigManager();
      }
    }
    return m_ConfigManager;
  }

  private Thread thread;

  protected ConfigManager() {
    super();
    String start = System.getProperty("com.socgen.fmk.resources.startRefreshThread");
    if ("true".equals(start)) {
      thread = new Thread(this);
      thread.start();
    }
  }

  @SuppressWarnings("rawtypes")
public String dumpConfigs() throws ResourceException {
    String sz = "", szkey = null;
    Enumeration e = m_HConfig.elements();
    Enumeration ks = null;
    if (e == null)
      return sz;
    while (e.hasMoreElements()) {
      Config cfg = (Config) e.nextElement();
      ks = cfg.getKeys();
      while (ks.hasMoreElements()) {
        szkey = (String) ks.nextElement();
        sz += szkey;
        sz += ":";
        sz += cfg.getPropertyStr(szkey, "");
        sz += "\n";
      }
      sz += "\n\n";
    }
    return sz;
  }

  public String dumpHTable() throws ResourceException {
    return m_HConfig.toString();
  }

@SuppressWarnings("unchecked")
public Config getConfig(String nomConfig) throws ResourceException {
    if (m_HConfig.get(nomConfig) == null) {
      Config newConfig = Config.create(nomConfig);
      m_HConfig.put(nomConfig, newConfig);
    }
    return (Config) m_HConfig.get(nomConfig);
  }

 @SuppressWarnings("rawtypes")
public Collection getConfigs() {
    return m_HConfig.values();
  }

  public void refresh(String nomConfig) throws ResourceException {
    synchronized (m_HConfig) {
      reloadConfig(nomConfig);
    }
  }

  @SuppressWarnings("unchecked")
public void reloadConfig(String nomConfig) throws ResourceException {
    Config config = Config.create(nomConfig);
    m_HConfig.put(nomConfig, config);
  }

@SuppressWarnings("rawtypes")
public void run() {
    while (true) {
      try {
        Thread.sleep(60000);
      } catch (InterruptedException e) {
        // nop
      }
      for (Iterator i = m_HConfig.values().iterator(); i.hasNext();) {
        Config config = (Config) i.next();
        if (config.hasChanged()) {
          try {
            ConfigManager.getInstance().reloadConfig(config.getFileName());
          } catch (ResourceException e1) {
            // nop
          }
        }
      }
    }
  }
}