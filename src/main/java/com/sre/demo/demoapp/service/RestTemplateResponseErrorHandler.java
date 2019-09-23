package com.sre.demo.demoapp.service;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.server.ResponseStatusException;

public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        if (httpResponse.getStatusCode()
        .series() == HttpStatus.Series.SERVER_ERROR) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
      } else if (httpResponse.getStatusCode()
        .series() == HttpStatus.Series.CLIENT_ERROR) {
          // handle CLIENT_ERROR
          if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
              throw new ResponseStatusException(HttpStatus.NOT_FOUND);
          }
      }
    }

    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return (
          httpResponse.getStatusCode().series() == Series.CLIENT_ERROR 
          || httpResponse.getStatusCode().series() == Series.SERVER_ERROR);
    }
    
}