package dbuchta.gitlab.issue.exporter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;
import lombok.Data;


@Data
@Generated("com.robohorse.robopojogenerator")
public class Author {

  @JsonProperty("web_url")
  private String webUrl;

  @JsonProperty("avatar_url")
  private Object avatarUrl;

  @JsonProperty("name")
  private String name;

  @JsonProperty("state")
  private String state;

  @JsonProperty("id")
  private int id;

  @JsonProperty("username")
  private String username;


}