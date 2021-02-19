package edu.byu.cs340;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;

/**
 * Usage:
 *  Program arguments: test1.txt hikarus3bucket1
 */
public class S3Copy {
    public static void main(String[] args) {
        // Create AmazonS3 object for doing S3 operations
        AmazonS3 s3 = AmazonS3ClientBuilder
                .standard()
                .withRegion("us-west-2")
                .build();

        // Write code to do the following:
        // 1. get name of file to be copied from the command line
        String fileName = args[0];      // test1.txt

        // 2. get name of S3 bucket from the command line
        String bucketName = args[1];    // hikarus3bucket1

        String file_path = "/Users/hikar/OneDrive/Desktop/WINTER_2021/CS_340-Exercises/AWS_S3-Exercise/test1.txt";

        // 3. upload file to the specified S3 bucket using the file name as the S3 key
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, new File(file_path));
        try{
            s3.putObject(putObjectRequest); // upload file
        }
        catch(Exception exception){
            System.out.println(exception);
        }
    }
}