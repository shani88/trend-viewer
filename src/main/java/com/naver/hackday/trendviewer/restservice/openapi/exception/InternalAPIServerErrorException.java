package com.naver.hackday.trendviewer.restservice.openapi.exception;

public class InternalAPIServerErrorException extends RuntimeException {


  public InternalAPIServerErrorException(String msg) {
    super(msg);
  }
}
