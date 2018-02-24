package dbuchta.gitlab.issue.exporter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class IdentitiesItem{

	@JsonProperty("provider")
	private String provider;

	@JsonProperty("extern_uid")
	private String externUid;
}