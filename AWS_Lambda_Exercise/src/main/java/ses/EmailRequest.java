package ses;

/**
 * This is the EmailRequest and its public properties for the different parts of an email message.
 * The parts include: “to”, “from”, “subject”, “textBody”, “htmlBody”. All of these should be strings.
 */
public class EmailRequest {
  public String TO;
  public String FROM;
  public String SUBJECT;
  public String TEXTBODY;
  public String HTMLBODY;
}