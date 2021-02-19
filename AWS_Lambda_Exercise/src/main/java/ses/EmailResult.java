package ses;

/**
 * This is the EmailResult and its public properties for the different parts of an email returning status message.
 * Contains a “message” property for returning the status of an email send operation,
 * and a “timestamp” property for returning the date/time at which the email was sent.
 * Both strings.
 */
public class EmailResult {
  public String message;
  public String timestamp;
}