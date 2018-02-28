package dbuchta.gitlab.issue.exporter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;
import lombok.Data;


@Data
@Generated("com.robohorse.robopojogenerator")
public class TimeStats {

  @JsonProperty("time_estimate")
  private int timeEstimate;

  @JsonProperty("total_time_spent")
  private int totalTimeSpent;

  @JsonProperty("human_time_estimate")
  private Object humanTimeEstimate;

  @JsonProperty("human_total_time_spent")
  private Object humanTotalTimeSpent;

}