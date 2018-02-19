package dbuchta.gitlab.issue.exporter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Author{

	@JsonProperty("web_url")
	private String webUrl;

	@JsonProperty("avatar_url")
	private Object avatarUrl;

	@JsonProperty("name")
	private String name;

	@JsonProperty("state")
	private String state;

	@JsonProperty("id")
	private int id;

	@JsonProperty("username")
	private String username;

	public void setWebUrl(String webUrl){
		this.webUrl = webUrl;
	}

	public String getWebUrl(){
		return webUrl;
	}

	public void setAvatarUrl(Object avatarUrl){
		this.avatarUrl = avatarUrl;
	}

	public Object getAvatarUrl(){
		return avatarUrl;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
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

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"Author{" + 
			"web_url = '" + webUrl + '\'' + 
			",avatar_url = '" + avatarUrl + '\'' + 
			",name = '" + name + '\'' + 
			",state = '" + state + '\'' + 
			",id = '" + id + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}