package dbuchta.gitlab.issue.exporter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.annotation.Generated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Generated("com.robohorse.robopojogenerator")
public class Issue {

  @JsonProperty("closed_at")
  private String closedAt;

  @JsonProperty("discussion_locked")
  private boolean discussionLocked;

  @JsonProperty("iid")
  private int iid;

  @JsonProperty("author")
  private User author;

  @JsonProperty("due_date")
  private String dueDate;

  @JsonProperty("description")
  private String description;

  @JsonProperty("assignees")
  private List<AssigneesItem> assignees;

  @JsonProperty("created_at")
  private String createdAt;

  @JsonProperty("weight")
  private Object weight;

  @JsonProperty("title")
  private String title;

  @JsonProperty("labels")
  private List<Object> labels;

  @JsonProperty("milestone")
  private Milestone milestone;

  @JsonProperty("updated_at")
  private String updatedAt;

  @JsonProperty("web_url")
  private String webUrl;

  @JsonProperty("project_id")
  private int projectId;

  @JsonProperty("time_stats")
  private TimeStats timeStats;

  @JsonProperty("user_notes_count")
  private int userNotesCount;

  @JsonProperty("state")
  private String state;

  @JsonProperty("assignee")
  private User assignee;

  @JsonProperty("id")
  private String id;

  @JsonProperty("confidential")
  private boolean confidential;

}