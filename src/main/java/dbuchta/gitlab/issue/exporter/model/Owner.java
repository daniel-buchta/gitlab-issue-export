package dbuchta.gitlab.issue.exporter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;
import lombok.Data;


@Data
@Generated("com.robohorse.robopojogenerator")
public class Owner {

  @JsonProperty("name")
  private String name;

  @JsonProperty("created_at")
  private String createdAt;

  @JsonProperty("id")
  private int id;

}