package com.cnc.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.joda.time.DateTime;

import com.cnc.httpclient.request.Request;
import com.cnc.httpclient.response.Response;

public class HTTPClient {
   
   private static CloseableHttpClient httpClient;
   
   public static HttpClient getHttpClient() {
      if (httpClient == null)
         httpClient = HttpClients.createDefault();
      return httpClient;
   }
   
   public Response execute(Request request) {
      return doExecute(request.getHttpRequest());
   }
   
   public Response doExecute(HttpRequest httpRequest) {
      Response response = null;
      try {
         DateTime start = DateTime.now();
         HttpResponse httpResponse = getHttpClient()
               .execute((HttpUriRequest) httpRequest);
         DateTime end = DateTime.now();
         
         // get the interval
         response = new Response()
            .setInterval(start, end)
            .setHeaders(httpResponse.getAllHeaders())
            .setStatusLine(httpResponse.getStatusLine());
         
         // read out the body
         InputStream is = httpResponse.getEntity().getContent();
         BufferedReader br = new BufferedReader(new InputStreamReader(is));
         String line;
         String textBody = "";
         while ((line = br.readLine()) != null) {
            textBody += line + "\n";
         }
         response.setTextBody(textBody);
      } catch (ClientProtocolException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
      return response;
   }
   
   
   public static boolean isContentTypeJson(List<Header> headers) {
      return isContextType(headers, "json");
   }
   public static boolean isContentTypeText(List<Header> headers) {
      return isContextType(headers, "text");
   }
   
   private static boolean isContextType(List<Header> headers, String type) { 
      for (Header h : headers) {
         if (h.getName().equalsIgnoreCase("content-type") && 
             h.getValue().toLowerCase().contains(type))
               return true;
      }
      return false;
   }
}