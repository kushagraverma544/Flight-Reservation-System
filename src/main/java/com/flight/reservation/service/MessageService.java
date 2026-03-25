package com.flight.reservation.service;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

  private final MessageSource messageSource;

  public MessageService(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  /**
   * Get message from properties file using message key
   *
   * @param messageKey - Message key from MessageKeys constants
   * @return - Message string from properties file
   */
  public String getMessage(String messageKey) {
    return messageSource.getMessage(
        messageKey,
        null,
        LocaleContextHolder.getLocale());
  }

  /**
   * Get message with parameters from properties file
   *
   * @param messageKey - Message key from MessageKeys constants
   * @param params     - Parameters to replace placeholders in message
   * @return - Message string with replaced parameters
   */
  public String getMessage(String messageKey, Object[] params) {
    return messageSource.getMessage(
        messageKey,
        params,
        LocaleContextHolder.getLocale());
  }

  /**
   * Get message with default value if key not found
   *
   * @param messageKey   - Message key from MessageKeys constants
   * @param defaultValue - Default value if key not found
   * @return - Message string or default value
   */
  public String getMessageWithDefault(String messageKey, String defaultValue) {
    return messageSource.getMessage(
        messageKey,
        null,
        defaultValue,
        LocaleContextHolder.getLocale());
  }

  /**
   * Get message with parameters and default value if key not found
   *
   * @param messageKey   - Message key from MessageKeys constants
   * @param params       - Parameters to replace placeholders in message
   * @param defaultValue - Default value if key not found
   * @return - Message string with replaced parameters or default value
   */
  public String getMessageWithDefault(String messageKey, Object[] params, String defaultValue) {
    return messageSource.getMessage(
        messageKey,
        params,
        defaultValue,
        LocaleContextHolder.getLocale());
  }
}
