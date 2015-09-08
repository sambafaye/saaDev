package com.yonima.sopeduc.resources;

import java.util.Locale;
import java.util.MissingResourceException;

import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

public class ReloadableConfig {

  private ReloadableResourceBundleMessageSource messageSource;

  public ReloadableConfig() {
    messageSource = new ReloadableResourceBundleMessageSource();
  }

  public void setBasename(String basename) {
    setBasenames(new String[]{basename});
  }

  public void setBasenames(String[] basenames) {
    messageSource.setBasenames(basenames);
  }

  public void setDefaultEncoding(String defaultEncoding) {
    messageSource.setDefaultEncoding(defaultEncoding);
  }

  public void setCacheSeconds(int cacheSeconds) {
    messageSource.setCacheSeconds(cacheSeconds);
  }

  public boolean getPropertyBoolean(String name) throws MissingResourceException {
    String val = getInternalMessage(name, null, null);
    return new Boolean(val.trim()).booleanValue();
  }

  public boolean getPropertyBoolean(String name, boolean defaut) {
    String buff = null;
    try {
      buff = getInternalMessage(name, null, null);
      return new Boolean(buff.trim()).booleanValue();
    } catch (MissingResourceException e) {
      return defaut;
    }
  }

  public int getPropertyInt(String name) throws MissingResourceException {
    String buff = getInternalMessage(name, null, null);
    return Integer.parseInt(buff);
  }

  public int getPropertyInt(String name, int defaut) {
    int ret;
    String buff = null;
    try {
      buff = getInternalMessage(name, null, null);
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

  public long getPropertyLong(String name) throws MissingResourceException {
    String buff = getInternalMessage(name, null, null);
    return Long.parseLong(buff);
  }

  public long getPropertyLong(String name, long defaut) {
    long ret;
    String buff = null;
    try {
      buff = getInternalMessage(name, null, null);
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

  public String getPropertyStr(String name) throws MissingResourceException {
    return getInternalMessage(name, null, null);
  }

  public String getPropertyStr(String name, Object[] args, Locale locale) throws MissingResourceException {
    return getInternalMessage(name, args, locale);
  }

  public String getPropertyStr(String name, String defaut) {
    String value;
    try {
      value = getInternalMessage(name, null, null);
      return value;
    } catch (MissingResourceException e) {
      return defaut;
    }
  }

  public String getPropertyStr(String name, String defaut, Object[] args, Locale locale) {
    String value;
    try {
      value = getInternalMessage(name, args, locale);
      return value;
    } catch (MissingResourceException e) {
      return defaut;
    }
  }

  private String getInternalMessage(String key, Object[] args, Locale locale) throws MissingResourceException {
    String ret;

    try {
      ret = messageSource.getMessage(key, args, locale);
    } catch (NoSuchMessageException e) {
      throw new MissingResourceException("Clef inexistante : " + key, ReloadableConfig.class.getName(), key);
    }

    return ret;
  }

}


