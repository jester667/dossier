package ru.neoflex.dossier.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * EmailMessageDTO
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-12-22T12:30:43.727894100+03:00[Europe/Moscow]")@lombok.AllArgsConstructor
@lombok.Builder
@lombok.NoArgsConstructor

public class EmailMessageDTO   {
  @JsonProperty("address")
  private String address;

  @JsonProperty("subject")
  private String subject;

  @JsonProperty("text")
  private String text;

  public EmailMessageDTO address(String address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   * @return address
  */
  @ApiModelProperty(example = "abc@gmail.com", value = "")


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public EmailMessageDTO subject(String subject) {
    this.subject = subject;
    return this;
  }

  /**
   * Get subject
   * @return subject
  */
  @ApiModelProperty(example = "test", value = "")


  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public EmailMessageDTO text(String text) {
    this.text = text;
    return this;
  }

  /**
   * Get text
   * @return text
  */
  @ApiModelProperty(example = "Hello mr. Anderson", value = "")


  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EmailMessageDTO emailMessageDTO = (EmailMessageDTO) o;
    return Objects.equals(this.address, emailMessageDTO.address) &&
        Objects.equals(this.subject, emailMessageDTO.subject) &&
        Objects.equals(this.text, emailMessageDTO.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, subject, text);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EmailMessageDTO {\n");
    
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    subject: ").append(toIndentedString(subject)).append("\n");
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

