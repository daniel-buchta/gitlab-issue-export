package dbuchta.gitlab.issue.exporter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class TimeStats{

	@JsonProperty("time_estimate")
	private int timeEstimate;

	@JsonProperty("total_time_spent")
	private int totalTimeSpent;

	@JsonProperty("human_time_estimate")
	private Object humanTimeEstimate;

	@JsonProperty("human_total_time_spent")
	private Object humanTotalTimeSpent;

	public void setTimeEstimate(int timeEstimate){
		this.timeEstimate = timeEstimate;
	}

	public int getTimeEstimate(){
		return timeEstimate;
	}

	public void setTotalTimeSpent(int totalTimeSpent){
		this.totalTimeSpent = totalTimeSpent;
	}

	public int getTotalTimeSpent(){
		return totalTimeSpent;
	}

	public void setHumanTimeEstimate(Object humanTimeEstimate){
		this.humanTimeEstimate = humanTimeEstimate;
	}

	public Object getHumanTimeEstimate(){
		return humanTimeEstimate;
	}

	public void setHumanTotalTimeSpent(Object humanTotalTimeSpent){
		this.humanTotalTimeSpent = humanTotalTimeSpent;
	}

	public Object getHumanTotalTimeSpent(){
		return humanTotalTimeSpent;
	}

	@Override
 	public String toString(){
		return 
			"TimeStats{" + 
			"time_estimate = '" + timeEstimate + '\'' + 
			",total_time_spent = '" + totalTimeSpent + '\'' + 
			",human_time_estimate = '" + humanTimeEstimate + '\'' + 
			",human_total_time_spent = '" + humanTotalTimeSpent + '\'' + 
			"}";
		}
}