package com.cnc.httpclient;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;

import com.cnc.httpclient.HTTPClient;
import com.cnc.httpclient.request.Request;
import com.cnc.httpclient.request.RequestBuilder;
import com.cnc.httpclient.request.Request.VERB;
import com.cnc.httpclient.response.Response;

public class HTTPClientTest {
   @Test
   public void testGetMethod() {
      try {
         HTTPClient httpClient = new HTTPClient();
         Request request = new RequestBuilder()
            .setUri( new URI("https://api.github.com/users/cnishina"))
            .setVerb(VERB.GET)
            .build()
            .logRequest();

         Response response = httpClient.execute(request)
            .logResponse();

         System.out.println(response.getStatusLine());
      } catch (URISyntaxException e) {
         e.printStackTrace();
      }

   }

}
