package dbuchta.gitlab.issue.exporter.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.google.common.collect.ImmutableList;
import dbuchta.gitlab.issue.exporter.MockitoExtension;
import dbuchta.gitlab.issue.exporter.model.Issue;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;


@ExtendWith(MockitoExtension.class)
class IssueExporterTest {

  @Mock
  private GitLabApiClient apiClient;

  @Mock
  private IssueExporter.Config config;

  @InjectMocks
  private IssueExporter issueExporter;

  @Test
  void exportIssues() throws IOException {
    // setup
    when(apiClient.listProjectIssues("token", "42"))
      .thenReturn(ImmutableList.of(
        Issue.builder().id("1").webUrl("").title("Title").state("open").createdAt("2018-02-18").updatedAt("2018-02-19").build(),
        Issue.builder().id("2").webUrl("").title("Title").state("closed").createdAt("2018-02-18").updatedAt("2018-02-19").closedAt("2018-02-19").build()));

    when(config.getExportOutputFolder()).thenReturn("exports");

    // run
    String file = issueExporter.exportIssues("token", "42");

    // verify
    assertThat(new File(file)).exists();
  }
}