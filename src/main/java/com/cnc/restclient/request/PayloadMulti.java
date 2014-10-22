package com.cnc.restclient.request;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

public class PayloadMulti extends Payload {
   private Map<String,Upload> multiUploads;
   
   public PayloadMulti() {
      multiPart = true;
   }

   public Map<String,Upload> getUploads() {
      return this.multiUploads;
   }
   public void addUpload(String form, Upload upload) {
      if (multiUploads == null)
         multiUploads = new HashMap<String,Upload>();
      multiUploads.put(form, upload);
   }

   public HttpEntity getHttpEntity() {
      // it's multi-part upload. inspiration from:
      // http://stackoverflow.com/questions/18964288/upload-a-file-through-an-http-form-via-multipartentitybuilder-with-a-progress
      // http://hc.apache.org/httpcomponents-client-ga/httpmime/examples/org/apache/http/examples/entity/mime/ClientMultipartFormPost.java
      MultipartEntityBuilder builder = MultipartEntityBuilder.create();
      builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

      Set<String> formNames = multiUploads.keySet();
      for (String formName : formNames) {
         Upload upload = multiUploads.get(formName);
         // it is text
         if (upload.isUploadText()) {
            UploadText textUpload = (UploadText) upload;
            StringBody sb = new StringBody(textUpload.getText(), upload.getContentType());
            builder.addPart(formName, sb);
         }
         // else it is a file
         else {
            UploadFile fileUpload = (UploadFile) upload;
            FileBody fb = new FileBody(fileUpload.getFile());
            builder.addPart(formName, fb);
         }
      }
      httpEntity = builder.build();
      return httpEntity;
   }

   public boolean isText() {
      String[] keys = (String[]) multiUploads.keySet().toArray();
      Upload firstUpload = multiUploads.get(keys[0]);
      return firstUpload.isUploadText();
   }
}
