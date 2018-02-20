package dbuchta.gitlab.issue.exporter.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;
import lombok.Data;

@Data
@Generated("com.robohorse.robopojogenerator")
public class Project {

  @JsonProperty("ssh_url_to_repo")
  private String sshUrlToRepo;

  @JsonProperty("issues_enabled")
  private boolean issuesEnabled;

  @JsonProperty("only_allow_merge_if_all_discussions_are_resolved")
  private boolean onlyAllowMergeIfAllDiscussionsAreResolved;

  @JsonProperty("request_access_enabled")
  private boolean requestAccessEnabled;

  @JsonProperty("_links")
  private Links links;

  @JsonProperty("open_issues_count")
  private int openIssuesCount;

  @JsonProperty("snippets_enabled")
  private boolean snippetsEnabled;

  @JsonProperty("description")
  private Object description;

  @JsonProperty("created_at")
  private String createdAt;

  @JsonProperty("import_status")
  private String importStatus;

  @JsonProperty("path")
  private String path;

  @JsonProperty("archived")
  private boolean archived;

  @JsonProperty("tag_list")
  private List<String> tagList;

  @JsonProperty("last_activity_at")
  private String lastActivityAt;

  @JsonProperty("shared_runners_enabled")
  private boolean sharedRunnersEnabled;

  @JsonProperty("id")
  private int id;

  @JsonProperty("container_registry_enabled")
  private boolean containerRegistryEnabled;

  @JsonProperty("owner")
  private Owner owner;

  @JsonProperty("visibility")
  private String visibility;

  @JsonProperty("path_with_namespace")
  private String pathWithNamespace;

  @JsonProperty("resolve_outdated_diff_discussions")
  private boolean resolveOutdatedDiffDiscussions;

  @JsonProperty("merge_requests_enabled")
  private boolean mergeRequestsEnabled;

  @JsonProperty("jobs_enabled")
  private boolean jobsEnabled;

  @JsonProperty("shared_with_groups")
  private List<Object> sharedWithGroups;

  @JsonProperty("http_url_to_repo")
  private String httpUrlToRepo;

  @JsonProperty("only_allow_merge_if_pipeline_succeeds")
  private boolean onlyAllowMergeIfPipelineSucceeds;

  @JsonProperty("web_url")
  private String webUrl;

  @JsonProperty("avatar_url")
  private String avatarUrl;

  @JsonProperty("wiki_enabled")
  private boolean wikiEnabled;

  @JsonProperty("public_jobs")
  private boolean publicJobs;

  @JsonProperty("name")
  private String name;

  @JsonProperty("creator_id")
  private int creatorId;

  @JsonProperty("namespace")
  private Namespace namespace;

  @JsonProperty("default_branch")
  private String defaultBranch;

  @JsonProperty("name_with_namespace")
  private String nameWithNamespace;

  @JsonProperty("star_count")
  private int starCount;

  @JsonProperty("forks_count")
  private int forksCount;

  @JsonProperty("runners_token")
  private String runnersToken;

  @JsonProperty("statistics")
  private Statistics statistics;

}