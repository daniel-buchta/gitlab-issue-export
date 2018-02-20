package dbuchta.gitlab.issue.exporter.service;

import dbuchta.gitlab.issue.exporter.model.Issue;
import dbuchta.gitlab.issue.exporter.model.Project;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
@AllArgsConstructor
public class GitLabApiClient {

  private final RestTemplate restTemplate;

  /**
   * Calls API endpoint: List project issues
   * <p>
   * Reference: https://docs.gitlab.com/ee/api/issues.html#list-project-issues
   * </p>
   * Get a list of all visible projects across GitLab for the authenticated user. When accessed
   * without authentication, only public projects are returned.
   * <pre>
   * GET /projects/:id/issues
   * GET /projects/:id/issues?state=opened
   * GET /projects/:id/issues?state=closed
   * GET /projects/:id/issues?labels=foo
   * GET /projects/:id/issues?labels=foo,bar
   * GET /projects/:id/issues?labels=foo,bar&state=opened
   * GET /projects/:id/issues?milestone=1.0.0
   * GET /projects/:id/issues?milestone=1.0.0&state=opened
   * GET /projects/:id/issues?iids[]=42&iids[]=43
   * GET /projects/:id/issues?search=issue+title+or+description
   * GET /projects/:id/issues?author_id=5
   * GET /projects/:id/issues?assignee_id=5
   * GET /projects/:id/issues?my_reaction_emoji=star
   * </pre>
   *
   * curl --header "PRIVATE-TOKEN: 9koXpg98eAheJpvBs5tK" https://gitlab.example.com/api/v4/projects/4/issues
   * @param privateToken user's personal access token
   * @param projectId project ID
   * @return list of issues
   */
  public List<Issue> listProjectIssues(String privateToken, String projectId) {

    ResponseEntity<List<Issue>> response = restTemplate
        .exchange("/projects/" + projectId + "/issues", HttpMethod.GET,
            createRequestEntity(privateToken), new ParameterizedTypeReference<List<Issue>>() { });

    if (response.getStatusCode() != HttpStatus.OK) {
      log.error("Received HTTP response status", response.getStatusCode());
      throw new RuntimeException("Response status not OK: " + response.getStatusCode());
    }

    return response.getBody();
  }

  /**
   * Get a list of all visible projects across GitLab for the authenticated user. When accessed
   * without authentication, only public projects are returned.
   *
   * @param privateToken user's personal access token
   * @return list of projects
   */
  public List<Project> listAllProjects(String privateToken) {
    ResponseEntity<List<Project>> response = restTemplate
        .exchange("/projects", HttpMethod.GET, createRequestEntity(privateToken),
            new ParameterizedTypeReference<List<Project>>() { });

    if (response.getStatusCode() != HttpStatus.OK) {
      log.error("Received HTTP response status", response.getStatusCode());
      throw new RuntimeException("Response status not OK: " + response.getStatusCode());
    }

    return response.getBody();
  }

  private HttpEntity<?> createRequestEntity(String privateToken) {
    HttpHeaders headers = new HttpHeaders();
    headers.add("PRIVATE-TOKEN", privateToken);
    return new HttpEntity<>(headers);
  }

}
