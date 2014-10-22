package com.cnc.restclient;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;

import com.cnc.restclient.request.Request;
import com.cnc.restclient.request.RequestBuilder;
import com.cnc.restclient.request.Request.VERB;
import com.cnc.restclient.response.Response;

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
