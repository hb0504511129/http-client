package com.cnc.restclient.request;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.entity.ContentType;
import org.apache.log4j.Logger;
import org.json.JSONObject;

public class Request {
   final static Logger log = Logger.getLogger(Request.class);
   public static enum VERB { GET, POST, PUT, HEAD, TRACE, OPTIONS, DELETE }
   protected URI uri;
   protected List<Header> headers = new ArrayList<Header>();
   protected VERB verb;
   protected HttpRequest httpRequest;
   protected Payload payload;
   
   public Request logRequest(int logOption) {
      logCurl(logOption);
      logHeaders();
      return this;
   }
   
   public Request logRequest() {
      logCurl();
      logHeaders();
      return this;
   }
   
   public Request logCurl() {
      log.debug( getCurl(1));
      return this;
   }
   
   public Request logCurl(int logOption) {
      log.debug( getCurl(logOption) );
      return this;
   }

   public String getCurl(Integer logOption) {
      // curl -v -X VERB
      String curl = "curl -v -X " + verb.name() + " ";

      // add headers
      for (Header h : headers)
         curl += "-H \"" + h.getName() + ":" + h.getValue() + "\" ";

      // POST and PUT
      // http://superuser.com/questions/149329/what-is-the-curl-command-line-syntax-to-do-a-post-request
      if (verb == VERB.PUT || verb == VERB.POST) {
         PayloadSingle ps = (PayloadSingle) payload;
         if (payload.isText()) {
            curl += "--data ";
            UploadText ut = (UploadText) ps.getUpload();
            
            // content-type: application/json
            if (ut.getContentType() == ContentType.APPLICATION_JSON) {
               JSONObject json = new JSONObject(ut.getText());
               curl += json.toString(logOption);
            }
            // content-type: text
            else {
               curl += ut.getText();
            }
         }
         else {
            // content-type: file
            curl += "--data-binary ";
            UploadFile uf = (UploadFile) ps.getUpload();
            curl += "@" + uf.getFile().getName();
         }
      }

      // add the URL
      curl += uri.toString();
      return curl;
   }
   
   public Request logHeaders() {
      for (Header h : headers) {
         log.debug("> " + h.getName() + ": " + h.getValue());
      }
      return this;
   }

   public HttpRequest getHttpRequest() {
      return httpRequest;
   }
   public URI getUri() {
      return uri;
   }
   public List<Header> getHeaders() {
      return headers;
   }
   public VERB getVerb() {
      return verb;
   }
   public void setPayload(Payload payload) {
      this.payload = payload;
   }
}
