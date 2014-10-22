package com.cnc.restclient.request;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;

public abstract class Upload {
   
   protected HttpEntity httpEntity;
   protected boolean isText;
   protected ContentType contentType;
   
   public boolean isUploadText() {
      return isText;
   }
   public abstract HttpEntity getHttpEntity();
   
   public ContentType getContentType() {
      return contentType;
   }
   public void setContentType(ContentType contentType) {
      this.contentType = contentType;
   }
}
