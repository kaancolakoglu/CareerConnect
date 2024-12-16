package com.springframework.CareerConnect.exceptions;

public class JobPostingNotFoundException extends RuntimeException {
  public JobPostingNotFoundException(String message) {
    super(message);
  }
}
