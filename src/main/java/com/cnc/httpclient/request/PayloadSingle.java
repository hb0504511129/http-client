package com.cnc.httpclient.request;

import org.apache.http.HttpEntity;

public class PayloadSingle extends Payload {
   private Upload upload;

   public PayloadSingle() {
      multiPart = false;
   }
   public Upload getUpload() {
      return this.upload;
   }
   public void setUpload(Upload upload) {
      this.upload = upload;
   }

   public HttpEntity getHttpEntity() {
      return upload.getHttpEntity();
   }

   public boolean isText() {
      return upload.isUploadText();
   }
}
