package com.cnc.restclient.request;

import java.net.URI;
import java.util.ArrayList;

import org.apache.http.Header;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.message.BasicHeader;

public class RequestBuilder extends Request {
   
   public RequestBuilder setUri(URI uri) {
      this.uri = uri;
      return this;
   }
   
   public RequestBuilder addHeader(String key, String value) {
      if (headers == null)
         headers = new ArrayList<Header>();
      headers.add( new BasicHeader(key, value));
      return this;
   }
   
   public <T extends Upload> RequestBuilder addUpload(T upload) {
      return this;
   }
   
   public RequestBuilder setVerb(VERB verb) {
      this.verb = verb;
      return this;
   }
   
   public Request build() {
      Request request = new Request();
      request.uri = uri;
      request.headers = headers;
      request.verb = verb;
      
      String url = uri.toString();
      switch (verb) {
      case GET:
         httpRequest = new HttpGet(url);
         break;
      case POST:
         httpRequest = new HttpPost(url);
         ((HttpPost) httpRequest).setEntity(payload.getHttpEntity());
         break;
      case PUT:
         httpRequest = new HttpPut(url);
         ((HttpPut) httpRequest).setEntity(payload.getHttpEntity());
         break;
      case HEAD:
         httpRequest = new HttpHead(url);
         break;
      case TRACE:
         httpRequest = new HttpTrace(url);
         break;
      case OPTIONS:
         httpRequest = new HttpOptions(url);
         break;
      case DELETE:
         httpRequest = new HttpDelete(url);
         break;
      }
      // set headers
      if (headers != null) {
         httpRequest.setHeaders( headers.toArray(new Header[headers.size()]) );
      }
            
      request.httpRequest = httpRequest;
      return request;
   }
}
