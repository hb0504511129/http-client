package com.cnc.restclient.request;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

public class UploadText extends Upload {
   
   public UploadText(ContentType contentType) {
      this.isText = true;
      this.contentType = contentType;
   }

   private String text;

   public void setText(String text) {
      this.text = text;
   }
   public String getText() {
      return text;
   }
   public HttpEntity getHttpEntity() {
      httpEntity = new StringEntity(text, contentType); 
      return httpEntity;
   }
}
