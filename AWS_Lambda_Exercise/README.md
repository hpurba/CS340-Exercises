**AWS Lambda Exercise**

Due Thursday by 11:59pm Points 2 Submitting a file upload File Types java
Complete the exercise in Java.

In this exercise you will:

Write an AWS Lambda function that sends email messages using the AWS Simple Email Service (SES)
Create a JAR file containing your compiled Lambda function
Create an AWS IAM role that provides your Lambda function the security permissions it requires.
Deploy your Lambda function using your JAR file and IAM role
Test your Lambda function using a test event
Assumptions
You have already configured your AWS credentials on your computer.  This should have been done when you installed the AWS CLI on your computer.

Intellij is installed on your computer.

Steps
By default, to send emails using the Simple Email Service (SES), you must first “verify” the email addresses you intend to send emails to and from (verifying an email address means proving that you actually own the addresses).
Login to the AWS console
Navigate to the Simple Email Service
Select “Email Addresses” on the left side of the SES screen
Verify each of the email addresses you want to send SES emails to and from.  Do this by clicking the “Verify a New Email Address” button.
When you deploy an AWS Lambda, you must assign it an IAM role that specifies what security permissions the Lambda should have when it runs.  For this purpose, create an IAM role that defines the security permissions your Lambda function should have when it executes.
Login to the AWS console
Navigate to the IAM service
On the left of the IAM service screen, select “Roles”
Click the “Create role” button
When asked to “Select type of trusted entity”, select “AWS Services”, and under "Choose the service that will use this role", select "Lambda" and click the Next button
On the “Attach permissions policies” screen, attach the AmazonSESFullAccess policy to your role.  This will allow your Lambda function to send emails using the SES service.  You can find the AmazonSESFullAccess role by entering “SES” in the search field. 
Also attach the CloudWatchLogsFullAccess policy to your role.  This will allow your Lambda function to log messages using the AWS CloudWatch service.  After doing that, click the Next button.
Skip the “Add tags” screen by clicking the Next button
On the “Review” screen, enter a name for your role (e.g., cs340lambda or whatever you like), and click the “Create role” button
Create a directory that will contain the code for this exercise
In the new directory, create a new Intellij project and select Maven on the left hand side.
Maven
Do not select an archetype and click "Next".
Name your project and select where it will save to.
Click the drop down labeled "Artifact Coordinates". Set the group id to whatever you want your package structure to be. For example "edu.byu.cs340" will tell Maven where your main package is.
Select "Finish".
In the new project, add dependencies on the AWS SDK Java library
Go to https://mvnrepository.com/ (Links to an external site.) and search for the following:
aws-lambda-java-core
aws-java-sdk-ses
Find the latest version of each dependency 
Copy the dependency for Maven.
Maven
Open pom.xml and add the following below the version tag:
<properties>
   <maven.compiler.source>1.11</maven.compiler.source>
   <maven.compiler.target>1.11</maven.compiler.target>
</properties>
Create a dependencies tag:
    <dependencies>
        <dependency>
            <groupId>com.amazonaws</groupId>
            <artifactId>aws-lambda-java-core</artifactId>
            <version>1.2.0</version>
        </dependency>

        <dependency>
            <groupId>com.amazonaws</groupId>
            <artifactId>aws-java-sdk-ses</artifactId>
            <version>1.11.720</version>
        </dependency>
    </dependencies>
In between the dependencies tag paste what you copied for each of the dependencies.
On the right hand side, click the Maven tab, and then click the refresh button. Maven will now download and import the dependencies and everything else it needs. NOTE: This will take some time, depending on your internet connection it can take 5-10 minutes.
In your project, create a Java package named “ses”.
In the “ses” package, create a class named “EmailRequest”.  In this class, create public properties for the different parts of an email message, including: “to”, “from”, “subject”, “textBody”, “htmlBody”. All of these should be strings.
In the “ses” package, create a class named “EmailResult”.  In this class, create a “message” property for returning the status of an email send operation, and a “timestamp” property for returning the date/time at which the email was sent.  Both of these should be strings.
In the “ses” package, create a class named EmailSender that will contain the “handler” for your Lambda function.  Add the following code to this file.  You might need to change the AWS region value if you’re using a region other than us-west-2.
 
package ses;

// these are the imports for SDK v1
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.simpleemail.*;
import com.amazonaws.services.simpleemail.model.*;
import com.amazonaws.regions.Regions;

public class EmailSender {
    public EmailResult handleRequest(EmailRequest request, Context context) {

        LambdaLogger logger = context.getLogger();
        logger.log("Entering send_email");

        try {
                AmazonSimpleEmailService client =
                AmazonSimpleEmailServiceClientBuilder.standard()

                // Replace US_WEST_2 with the AWS Region you're using for
                // Amazon SES.
                .withRegion(Regions.US_WEST_2).build();

                // TODO:
                // Use the AmazonSimpleEmailService object to send an email message
                // using the values in the EmailRequest parameter object

                logger.log("Email sent!");
             } catch (Exception ex) {
                logger.log("The email was not sent. Error message: " + ex.getMessage());
                throw new RuntimeException(ex);
            }
            finally {
                logger.log("Leaving send_email");
            }

            // TODO:
            // Return EmailResult
     }

}
Fill in the code that actually creates and sends an email message using the values contained in the EmailRequest parameter object.  Return an EmailResult containing a message indicating the result of the operation and the date/time at which the email was sent. The online documentation for the AWS Java SDK can be found here:
https://docs.aws.amazon.com/AWSJavaSDK/latest/javadoc/index.html (Links to an external site.)
 

Once your program compiles, add an “artifact” to your Intellij project that creates a JAR file containing your compiled code.
Select the File -> Project Structure menu
Select “Artifacts” on the left side of the “Project Structure” dialog
To add a new artifact, click the “plus” button at the top of the dialog
Select JAR -> From modules with dependencies
In the “Create JAR from Modules” dialog, keep the default options, and click OK
Check the “Include in project build” check box
Rebuild the project, which will result in a JAR file being created in the “out/artifacts/<DIR>/ folder. This JAR file can be used to deploy your Lambda function to AWS
 

***Note: AWS supports Java 11. You may be using a later versioned java compiler to build your jar. To change your compiler version in Intellij, go to Preferences -> Build, Execution, Deployment -> Java Compiler and then change “Project bytecode version” to 11.

  

Next, deploy your Lambda function.
Login to the AWS console
Navigate to the Lambda service
Select “Functions” on the left side of the Lambda screen
Click the “Create Function” button in the top-right corner
Give your function a name (e.g., send_email)
As your function’s Runtime select “Java 11”
Under Permissions, click “Choose or create an execution role”
In the “Execution role” drop-down, select “Use an existing role”
In the “Existing role” drop-down, select the name of the IAM role that you created in a previous step (e.g., cs340lambda)
Click the “Create function” button.  This will actually create the function, and take you to a screen that lets you further configure the function.
Scroll down to the “Runtime settings” section of the function configuration screen.
In the “Handler” field, specify the name of the method that contains your Lambda function handler (e.g., ses.EmailSender::handleRequest)
Scroll down to the "Function code" section of the function configuration screen.
Click the "Actions" dropdown.
Select the "Upload a .zip or .jar file"
Click the "Upload button and select the JAR file created in the previous step and click "Save".
Now, test your Lambda function.  This can be done as follows:
Scroll to the top and open the “Select a test event” drop-down
Select “Configure test events”.  A test event is simply a JSON object that you will send to your Lambda function to see if it works.
Specify a name for your test event (e.g., SendEmail)
Fill in the test JSON object.  Its attribute names should match the field names in the Java EmailRequest class created in a previous step.  The JSON object’s attributes should start with lower-case letters.  (AWS will automatically deserialize the JSON object into an EmailRequest Java object)
Click the “Create” button at the bottom. This will take you back to the Lambda function configuration screen.
Execute your test event.  Scroll to the top, select the name of your test event in the drop-down next to the “Test” button, and click the “Test” button.  This will execute your Lambda function with the test event JSON object.
The output of your function will be displayed in the console.  For more details, you can click the “logs” link to view the complete CloudWatch logs for your Lambda function
Submission

Submit the following .java files. DO NOT SUBMIT A .zip FILE.
File containing created "EmailRequest" class
File containing created "EmailResult" class
File containing created "EmailSender" class
Submit your Java code here.
Rubric
In Class Exercises Rubric
In Class Exercises Rubric
Criteria	Ratings	Pts
This criterion is linked to a Learning OutcomeExercise Correctness
This is a general reflection of how well you did on the exercise.
2 pts
Full Marks
Is mostly right, possibly some small errors.
1 pts
Some credit
May have bigger errors, or parts incomplete or missing.
0 pts
No Marks
Did not complete the assignment, did not follow instructions or had exceedingly many errors.
2 pts
Total Points: 2
****