package com.cnc.restclient.request;

import java.io.File;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;

public class UploadFile extends Upload {

   public UploadFile() {
      isText = false;
      contentType = ContentType.DEFAULT_BINARY;
   }
   private File file;

   public void setFile(File file) {
      this.file = file;
      httpEntity = new FileEntity(file);
   }
   public File getFile() {
      return this.file;
   }

   public HttpEntity getHttpEntity() {
      httpEntity = new FileEntity(file, contentType);
      return httpEntity;
   }
}
