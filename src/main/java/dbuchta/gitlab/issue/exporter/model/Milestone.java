package dbuchta.gitlab.issue.exporter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Milestone{

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

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setProjectId(int projectId){
		this.projectId = projectId;
	}

	public int getProjectId(){
		return projectId;
	}

	public void setIid(int iid){
		this.iid = iid;
	}

	public int getIid(){
		return iid;
	}

	public void setDueDate(Object dueDate){
		this.dueDate = dueDate;
	}

	public Object getDueDate(){
		return dueDate;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return state;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	@Override
 	public String toString(){
		return 
			"Milestone{" + 
			"updated_at = '" + updatedAt + '\'' + 
			",project_id = '" + projectId + '\'' + 
			",iid = '" + iid + '\'' + 
			",due_date = '" + dueDate + '\'' + 
			",description = '" + description + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",state = '" + state + '\'' + 
			",id = '" + id + '\'' + 
			",title = '" + title + '\'' + 
			"}";
		}
}