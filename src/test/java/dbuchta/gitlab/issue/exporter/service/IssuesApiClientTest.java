package dbuchta.gitlab.issue.exporter.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import com.google.common.io.ByteStreams;
import dbuchta.gitlab.issue.exporter.model.Issue;
import java.io.IOException;
import java.io.InputStream;
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
public class IssuesApiClientTest {

  @Autowired
  private IssuesApiClient issuesApiClient;

  @Autowired
  private MockRestServiceServer server;

  @Test
  public void listProjectIssues() throws IOException {
    // setup
    InputStream body = getClass().getClassLoader()
        .getResourceAsStream("json-samples/project-issues.json");

    server.expect(requestTo("/projects/42/issues"))
        .andExpect(method(HttpMethod.GET))
        .andExpect(header("PRIVATE-TOKEN", "token"))
        .andRespond(withSuccess(ByteStreams.toByteArray(body), MediaType.APPLICATION_JSON));

    // run
    List<Issue> result = issuesApiClient.listProjectIssues("token", "42");

    //verify
    assertThat(result).hasSize(1)
    .extracting(Issue::getId).isNotNull();

    Issue issue = result.get(0);
    assertThat(issue.getId()).isEqualTo("41");
    assertThat(issue.getWebUrl()).isNotBlank();
    assertThat(issue.getTitle()).isEqualTo("Ut commodi ullam eos dolores perferendis nihil sunt.");
    assertThat(issue.getState()).isEqualTo("closed");
    assertThat(issue.getCreatedAt()).isNotNull();
    assertThat(issue.getUpdatedAt()).isNotNull();
    assertThat(issue.getClosedAt()).isNotNull();
  }
}