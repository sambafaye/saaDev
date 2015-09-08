package com.yonima.sopeduc.listener;

import java.util.Enumeration;
import java.util.MissingResourceException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


import com.yonima.sopeduc.exception.ResourceException;
import com.yonima.sopeduc.resources.Config;
import com.yonima.sopeduc.resources.ConfigApplicationPrefix;
import com.yonima.sopeduc.resources.ConfigManager;

public class AppServletContextListener implements ServletContextListener {

  public void contextDestroyed(ServletContextEvent sce) {
  }

  public void contextInitialized(ServletContextEvent sce) {
    tracerNumeroVersion();
    initialiserApplicationPrefixPourSpring(sce);
    tracerFichiersProperties(sce);
  }

  private void tracerFichiersProperties(ServletContextEvent sce) {
    String strFichiersPropertiesATracer = sce.getServletContext().getInitParameter("fichiersPropertiesATracer");
    if (strFichiersPropertiesATracer != null) {
      try {
        String[] fichiersPropertiesATracer = strFichiersPropertiesATracer.split(",");
        for (String fichier : fichiersPropertiesATracer) {
          tracerFichierDeProperties(fichier);
        }
      } catch (Exception e) {
//        Logger.getInstance().trace(
//            new RuntimeException("Impossible de tracer les fichier de properties <"+ strFichiersPropertiesATracer + "> : " + e.getMessage(), e));
      }
    }
  }

  private void initialiserApplicationPrefixPourSpring(ServletContextEvent sce) {
    String resourcesPrefix = sce.getServletContext().getInitParameter("resourcesPrefix");

    if (resourcesPrefix == null || "".equals(resourcesPrefix.trim())) {
//    	Logger.getInstance().trace(
//          new RuntimeException(
//              "Impossible de fixer le prefixe d'acces aux resources Spring car le nom de la variable d'environnement à utiliser est vide ou absente du fichier web.xml"));
    } else {
      // recuperer le prefixe de l'application
      try {
        String directory = ConfigApplicationPrefix.getApplicationPrefix();

        if (directory != null && !directory.isEmpty()) {
//          Logger.getInstance().trace(
//              new TraceInfoEvent("Prefixe d'acces aux resources Spring : <" + resourcesPrefix + ">=<" + directory + ">"));
          System.setProperty(resourcesPrefix, directory);
        }
      } catch (ResourceException e) {
        //TraceLogger.getInstance().trace(e);
      }
    }
  }

  private void tracerNumeroVersion() {
    String nomDuFichierDeVersion = "version";
    Config config = ConfigManager.getInstance().getConfig(nomDuFichierDeVersion);
    String version = config.getPropertyStr("version");

    if (version == null || version.isEmpty()) {
      version = "INCONNUE";
    }
    //TODO a rajouter
    //TraceLogger.getInstance().trace(new TraceEntreeSortieEvent("version: [" + version + "]"));
  }

  @SuppressWarnings("unchecked")
  private void tracerFichierDeProperties(String nomDuFichierDansClasspath) {
    try {
      Config config = ConfigManager.getInstance().getConfig(nomDuFichierDansClasspath);

      Enumeration<String> keys = config.getKeys();
      if (keys != null) {
        while (keys.hasMoreElements()) {
          String key = (String) keys.nextElement();
          //TODO a rajouter dans les logs
//          TraceLogger.getInstance().trace(
//              new TraceInfoEvent("Fichier <" + nomDuFichierDansClasspath + "> - [" + key + "]=[" + config.getPropertyStr(key)
//                  + "]"));
        }
      }
    } catch (MissingResourceException e) {
    	//TODO à rajouter dans le logs
//      TraceLogger.getInstance().trace(
//          new FMKApplicationException("Fichier de properties <" + nomDuFichierDansClasspath
//              + ".properties> absent, impossible de tracer son contenu : " + e.getMessage(), e));
    }
  }

}
