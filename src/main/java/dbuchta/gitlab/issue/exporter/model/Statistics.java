package dbuchta.gitlab.issue.exporter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;
import lombok.Data;


@Data
@Generated("com.robohorse.robopojogenerator")
public class Statistics {

  @JsonProperty("commit_count")
  private int commitCount;

  @JsonProperty("lfs_objects_size")
  private int lfsObjectsSize;

  @JsonProperty("job_artifacts_size")
  private int jobArtifactsSize;

  @JsonProperty("repository_size")
  private int repositorySize;

  @JsonProperty("storage_size")
  private int storageSize;

}