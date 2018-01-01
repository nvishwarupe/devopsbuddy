package com.devopsbuddy.exceptions;

/**
 * Created by nvishwarupe
 */


public class S3Exception extends RuntimeException {

    public S3Exception (Throwable e) {
        super(e);
    }

    public S3Exception(String message) {
        super(message);
    }
}
