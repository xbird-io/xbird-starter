package com.github.zhycn.swagger2.autoconfigure;

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
  
  // 扫描基础包
  private String basePackage;
  
  // 设置API信息
  private String title = "Api Documentation";
  private String description = "Api Documentation";
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

  public String getBasePackage() {
    return basePackage;
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

  public void setBasePackage(String basePackage) {
    this.basePackage = basePackage;
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
