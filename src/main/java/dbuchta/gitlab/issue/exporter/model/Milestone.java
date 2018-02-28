package dbuchta.gitlab.issue.exporter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Milestone {

  @JsonProperty("updated_at")
  private String updatedAt;

  @JsonProperty("project_id")
  private int projectId;

  @JsonProperty("iid")
  private int iid;

  @JsonProperty("due_date")
  private Object dueDate;

  @JsonProperty("description")
  private String description;

  @JsonProperty("created_at")
  private String createdAt;

  @JsonProperty("state")
  private String state;

  @JsonProperty("id")
  private int id;

  @JsonProperty("title")
  private String title;

}