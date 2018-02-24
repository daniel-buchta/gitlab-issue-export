package dbuchta.gitlab.issue.exporter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.annotation.Generated;
import lombok.Data;

@Data
@Generated("com.robohorse.robopojogenerator")
public class User {

  @JsonProperty("can_create_project")
  private boolean canCreateProject;

  @JsonProperty("theme_id")
  private int themeId;

  @JsonProperty("created_at")
  private String createdAt;

  @JsonProperty("bio")
  private Object bio;

  @JsonProperty("projects_limit")
  private int projectsLimit;

  @JsonProperty("linkedin")
  private String linkedin;

  @JsonProperty("last_activity_on")
  private String lastActivityOn;

  @JsonProperty("can_create_group")
  private boolean canCreateGroup;

  @JsonProperty("skype")
  private String skype;

  @JsonProperty("twitter")
  private String twitter;

  @JsonProperty("identities")
  private List<IdentitiesItem> identities;

  @JsonProperty("last_sign_in_at")
  private String lastSignInAt;

  @JsonProperty("color_scheme_id")
  private int colorSchemeId;

  @JsonProperty("id")
  private String id;

  @JsonProperty("state")
  private String state;

  @JsonProperty("confirmed_at")
  private String confirmedAt;

  @JsonProperty("email")
  private String email;

  @JsonProperty("current_sign_in_at")
  private String currentSignInAt;

  @JsonProperty("two_factor_enabled")
  private boolean twoFactorEnabled;

  @JsonProperty("external")
  private boolean external;

  @JsonProperty("avatar_url")
  private String avatarUrl;

  @JsonProperty("web_url")
  private String webUrl;

  @JsonProperty("website_url")
  private String websiteUrl;

  @JsonProperty("organization")
  private String organization;

  @JsonProperty("name")
  private String name;

  @JsonProperty("location")
  private Object location;

  @JsonProperty("username")
  private String username;
}