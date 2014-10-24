package com.cnc.httpclient;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;

import com.cnc.httpclient.RestClient;
import com.cnc.httpclient.request.Request;
import com.cnc.httpclient.request.RequestBuilder;
import com.cnc.httpclient.request.Request.VERB;
import com.cnc.httpclient.response.Response;

public class RestClientTest {
   @Test
   public void testGetMethod() {
      try {
         RestClient restClient = new RestClient();
         Request request = new RequestBuilder()
            .setUri( new URI("https://api.github.com/users/cnishina"))
            .setVerb(VERB.GET)
            .build()
            .logRequest();

         Response response = restClient.execute(request)
            .logResponse();

         System.out.println(response.getStatusLine());
      } catch (URISyntaxException e) {
         e.printStackTrace();
      }

   }

}
