package com.cnc.httpclient.response;

import java.util.Arrays;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.StatusLine;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.json.JSONObject;

import com.cnc.httpclient.HTTPClient;

public class Response {
   final static Logger log = Logger.getLogger(Response.class);
   private Interval interval;
   private List<Header> headers;
   private StatusLine statusLine;
   private String textBody;
   
   public Response logResponse() {
      log.debug("< " + statusLine.toString());
      for (Header h : headers) {
         log.debug("< " + h.getName() + ": " + h.getValue());
      }
      
      // output the text body if there is
      if (HTTPClient.isContentTypeText(headers))
         log.debug(textBody);
      if (HTTPClient.isContentTypeJson(headers)) {
         JSONObject json = new JSONObject(textBody);
         log.debug(json.toString(2));
      }
      return this;
   }
   
   public Response setInterval(DateTime start, DateTime end) {
      interval = new Interval(start, end);
      return this;
   }
   public Interval getInterval() {
      return interval;
   }
   public Response setInterval(Interval interval) {
      this.interval = interval;
      return this;
   }
   public List<Header> getHeaders() {
      return headers;
   }
   public Response setHeaders(Header[] headers) {
      this.headers = Arrays.asList(headers);
      return this;
   }
   public StatusLine getStatusLine() {
      return statusLine;
   }
   public Response setStatusLine(StatusLine statusLine) {
      this.statusLine = statusLine;
      return this;
   }
   public String getTextBody() {
      return textBody;
   }
   public void setTextBody(String textBody) {
      this.textBody = textBody;
   }
}
