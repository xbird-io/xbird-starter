/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.xbird.starter.swagger2.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

/**
 * Swagger2 Configuration Properties.
 * 
 * @author zhycn
 * @since 1.0.0 2018-01-30
 */
@ConfigurationProperties(prefix = "xbird.swagger2")
public class Swagger2Properties {
  
  // 设置API信息
  private String title = "API Documentation";
  private String description = "The Best APIs are Built with Swagger Tools.";
  private String termsOfServiceUrl;
  private ContactWrapper contact = new ContactWrapper();
  private String license;
  private String licenseUrl;
  private String version = "1.0";

  /**
   * 构建API信息
   */
  public ApiInfo build() {
    return new ApiInfoBuilder()
        .title(title)
        .description(description)
        .version(version)
        .termsOfServiceUrl(termsOfServiceUrl)
        .contact(new Contact(contact.getName(), contact.getUrl(), contact.getEmail()))
        .license(license)
        .licenseUrl(licenseUrl)
        .build();
  }

  public ContactWrapper getContact() {
    return contact;
  }

  public String getDescription() {
    return description;
  }

  public String getLicense() {
    return license;
  }

  public String getLicenseUrl() {
    return licenseUrl;
  }

  public String getTermsOfServiceUrl() {
    return termsOfServiceUrl;
  }

  public String getTitle() {
    return title;
  }

  public String getVersion() {
    return version;
  }

  public void setContact(ContactWrapper contact) {
    this.contact = contact;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setLicense(String license) {
    this.license = license;
  }

  public void setLicenseUrl(String licenseUrl) {
    this.licenseUrl = licenseUrl;
  }

  public void setTermsOfServiceUrl(String termsOfServiceUrl) {
    this.termsOfServiceUrl = termsOfServiceUrl;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public static class ContactWrapper {

    private String name;
    private String url;
    private String email;

    public String getName() {
      return name;
    }

    public String getUrl() {
      return url;
    }

    public String getEmail() {
      return email;
    }

    public void setName(String name) {
      this.name = name;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    public void setEmail(String email) {
      this.email = email;
    }

  }
  
}
