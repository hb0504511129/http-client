package com.cnc.restclient.request;

import org.apache.http.HttpEntity;

public abstract class Payload {
   protected HttpEntity httpEntity;
   protected boolean multiPart;
   
   public abstract HttpEntity getHttpEntity();
   
   public abstract boolean isText();
   
   public boolean isMultiPart() {
      return multiPart;
   }
}
