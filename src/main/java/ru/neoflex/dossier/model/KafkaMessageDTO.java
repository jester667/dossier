package ru.neoflex.dossier.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * KafkaMessageDTO
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-12-22T13:15:26.021840+03:00[Europe/Moscow]")@lombok.AllArgsConstructor
@lombok.Builder
@lombok.NoArgsConstructor

public class KafkaMessageDTO   {
  @JsonProperty("address")
  private String address;

  /**
   * Gets or Sets theme
   */
  public enum ThemeEnum {
    FINISH_REGISTRATION("FINISH_REGISTRATION"),
    
    CREATE_DOCUMENT("CREATE_DOCUMENT"),
    
    SEND_DOCUMENT("SEND_DOCUMENT"),
    
    SEND_SES("SEND_SES"),
    
    CREDIT_ISSUED("CREDIT_ISSUED"),
    
    APPLICATION_DENIED("APPLICATION_DENIED");

    private String value;

    ThemeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ThemeEnum fromValue(String value) {
      for (ThemeEnum b : ThemeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("theme")
  private ThemeEnum theme;

  @JsonProperty("applicationId")
  private Long applicationId = null;

  public KafkaMessageDTO address(String address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   * @return address
  */
  @ApiModelProperty(value = "")


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public KafkaMessageDTO theme(ThemeEnum theme) {
    this.theme = theme;
    return this;
  }

  /**
   * Get theme
   * @return theme
  */
  @ApiModelProperty(value = "")


  public ThemeEnum getTheme() {
    return theme;
  }

  public void setTheme(ThemeEnum theme) {
    this.theme = theme;
  }

  public KafkaMessageDTO applicationId(Long applicationId) {
    this.applicationId = applicationId;
    return this;
  }

  /**
   * Get applicationId
   * @return applicationId
  */
  @ApiModelProperty(value = "")


  public Long getApplicationId() {
    return applicationId;
  }

  public void setApplicationId(Long applicationId) {
    this.applicationId = applicationId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    KafkaMessageDTO kafkaMessageDTO = (KafkaMessageDTO) o;
    return Objects.equals(this.address, kafkaMessageDTO.address) &&
        Objects.equals(this.theme, kafkaMessageDTO.theme) &&
        Objects.equals(this.applicationId, kafkaMessageDTO.applicationId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, theme, applicationId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class KafkaMessageDTO {\n");
    
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    theme: ").append(toIndentedString(theme)).append("\n");
    sb.append("    applicationId: ").append(toIndentedString(applicationId)).append("\n");
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

