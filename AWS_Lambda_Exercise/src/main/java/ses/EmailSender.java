package ses;

// these are the imports for SDK v1
import com.amazonaws.regions.Region;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.simpleemail.*;
import com.amazonaws.services.simpleemail.model.*;
import com.amazonaws.regions.Regions;

/**
 * This is the EmailSender. It contains the “handler” for the Lambda function.
 */
public class EmailSender {
  public SendEmailResult handleRequest(EmailRequest request, Context context) {
    SendEmailResult emailResult;
    LambdaLogger logger = context.getLogger();
    logger.log("Entering send_email\n");
    Regions regions = Regions.US_EAST_2;

    try {
      AmazonSimpleEmailService client =
        AmazonSimpleEmailServiceClientBuilder.standard()
          .withRegion(regions).build();

      SendEmailRequest emailRequest = new SendEmailRequest()
        .withDestination(
          new Destination().withToAddresses(request.TO))
        .withMessage(new Message()
          .withBody(new Body()
            .withHtml(new Content()
              .withCharset("UTF-8").withData(request.HTMLBODY))
            .withText(new Content()
              .withCharset("UTF-8").withData(request.TEXTBODY)))
          .withSubject(new Content()
            .withCharset("UTF-8").withData(request.SUBJECT)))
        .withSource(request.FROM);

      emailResult = client.sendEmail(emailRequest);
      logger.log("Email sent!\n");
    } catch (Exception ex) {
      logger.log("The email was not sent. Error message: " + ex.getMessage() + "\n");
      throw new RuntimeException(ex);
    }
    finally {
      logger.log("Leaving send_email\n");
    }
    return emailResult;
  }
}
