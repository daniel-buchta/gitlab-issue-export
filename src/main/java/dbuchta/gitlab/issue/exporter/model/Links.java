package dbuchta.gitlab.issue.exporter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;
import lombok.Data;


@Data
@Generated("com.robohorse.robopojogenerator")
public class Links {

  @JsonProperty("merge_requests")
  private String mergeRequests;

  @JsonProperty("members")
  private String members;

  @JsonProperty("self")
  private String self;

  @JsonProperty("repo_branches")
  private String repoBranches;

  @JsonProperty("issues")
  private String issues;

  @JsonProperty("events")
  private String events;

  @JsonProperty("labels")
  private String labels;

}