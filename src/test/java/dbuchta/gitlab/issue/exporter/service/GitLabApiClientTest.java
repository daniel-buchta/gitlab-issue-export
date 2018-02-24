package dbuchta.gitlab.issue.exporter.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import com.google.common.io.ByteStreams;
import dbuchta.gitlab.issue.exporter.model.Issue;
import dbuchta.gitlab.issue.exporter.model.Project;
import dbuchta.gitlab.issue.exporter.model.User;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebClient
@AutoConfigureMockRestServiceServer
public class GitLabApiClientTest {

  @Autowired
  private GitLabApiClient gitLabApiClient;

  @Autowired
  private MockRestServiceServer server;

  @Test
  public void listProjectIssues() throws IOException {
    // setup
    server.expect(requestTo("/projects/42/issues"))
        .andExpect(method(HttpMethod.GET))
        .andExpect(header("PRIVATE-TOKEN", "token"))
        .andRespond(withSuccess(bodyFromFile("json-samples/project-issues.json"), MediaType.APPLICATION_JSON));

    // run
    List<Issue> result = gitLabApiClient.listProjectIssues("token", "42");

    //verify
    assertThat(result).hasSize(1).extracting(Issue::getId).isNotNull();

    Issue issue = result.get(0);
    assertThat(issue.getId()).isEqualTo("41");
    assertThat(issue.getWebUrl()).isNotBlank();
    assertThat(issue.getTitle()).isEqualTo("Ut commodi ullam eos dolores perferendis nihil sunt.");
    assertThat(issue.getState()).isEqualTo("closed");
    assertThat(issue.getCreatedAt()).isNotNull();
    assertThat(issue.getUpdatedAt()).isNotNull();
    assertThat(issue.getClosedAt()).isNotNull();
  }

  @Test
  public void listUserProjects() throws IOException {
    // setup
    server.expect(requestTo("/projects?membership=true"))
        .andExpect(method(HttpMethod.GET))
        .andExpect(header("PRIVATE-TOKEN", "token"))
        .andRespond(withSuccess(bodyFromFile("json-samples/projects.json"), MediaType.APPLICATION_JSON));

    // run
    List<Project> result = gitLabApiClient.listUserProjects("token");

    //verify
    assertThat(result).hasSize(2).extracting(Project::getId).isNotNull();

    Project project0 = result.get(0);
    assertThat(project0.getId()).isEqualTo(4);
    assertThat(project0.getName()).isEqualTo("Diaspora Client");
    assertThat(project0.getWebUrl()).isNotBlank();
    assertThat(project0.getCreatedAt()).isNotNull();

    Project project1 = result.get(1);
    assertThat(project1.getId()).isEqualTo(6);
    assertThat(project1.getName()).isEqualTo("Puppet");
    assertThat(project1.getWebUrl()).isNotBlank();
    assertThat(project1.getCreatedAt()).isNotNull();
  }

  @Test
  public void getUser() throws IOException {
    // setup
    server.expect(requestTo("/user"))
        .andExpect(method(HttpMethod.GET))
        .andExpect(header("PRIVATE-TOKEN", "token"))
        .andRespond(withSuccess(bodyFromFile("json-samples/user.json"), MediaType.APPLICATION_JSON));

    // run
    User result = gitLabApiClient.getUser("token");

    //verify
    assertThat(result.getId()).isEqualTo("1");
    assertThat(result.getName()).isEqualTo("John Smith");
    assertThat(result.getWebUrl()).isNotBlank();
    assertThat(result.getCreatedAt()).isNotNull();
  }

  private byte[] bodyFromFile(String name) throws IOException {
    return ByteStreams.toByteArray(getClass().getClassLoader().getResourceAsStream(name));
  }
}