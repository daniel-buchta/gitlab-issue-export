package dbuchta.gitlab.issue.exporter.service;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;
import static org.supercsv.prefs.CsvPreference.STANDARD_PREFERENCE;

import com.google.common.io.Files;
import dbuchta.gitlab.issue.exporter.model.Issue;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListWriter;

@Component
@Slf4j
@AllArgsConstructor
public class IssueExporter {

  private final GitLabApiClient apiClient;

  private final Config config;

  public String exportIssues(String privateToken, String projectId) throws IOException {
    String outputFileName = getExportOutputFolder() + "issues-" + projectId + "_"
        + LocalDateTime.now().format(ISO_LOCAL_DATE_TIME).replaceAll(":", "-") + ".csv";

    Files.createParentDirs(new File(outputFileName));

    List<Issue> issues = apiClient.listProjectIssues(privateToken, projectId);
    log.info("Found {} issues for project #{}", issues.size(), projectId);

    try (
      CsvListWriter csv = new CsvListWriter(new FileWriter(outputFileName), STANDARD_PREFERENCE)) {

      // write the header
      csv.writeHeader("ID", "URL", "Title", "State", "Created At", "Updated At", "Closed At");

      // write each issue
      issues.stream().map(this::issueToObjectList).forEach(i -> writeRow(csv, i));
    }

    log.info("Issues export completed: {}", outputFileName);
    return outputFileName;
  }

  private String getExportOutputFolder() {
    String result = config.getExportOutputFolder();

    if (isNullOrEmpty(result)) {
      result = ".";
    }

    if (!result.endsWith("/")){
      result += "/";
    }

    return result;
  }

  private void writeRow(ICsvListWriter csv, List<Object> row) {
    try {
      csv.write(row, processors);
    } catch (IOException e) {
      log.error("Error occurred when writing row '{}' to output CSV", row);
      throw new RuntimeException(e);
    }
  }

  private List<Object> issueToObjectList(Issue issue) {
    return ListBuilder.create()
        .add(issue.getId())
        .add(issue.getWebUrl())
        .add(issue.getTitle())
        .add(issue.getState())
        .add(issue.getCreatedAt())
        .add(issue.getUpdatedAt())
        .add(issue.getClosedAt())
        .build();
  }

  private final CellProcessor[] processors = {
      new UniqueHashCode(), // id (must be unique)
      new NotNull(), // web URL
      new NotNull(), // title
      new NotNull(), // state
      new NotNull(), // created at
      new NotNull(), // update at
      new Optional(), // closed at
  };

  // formatovany datum: new FmtDate("dd/MM/yyyy")

  public interface Config {

    String getExportOutputFolder();

  }

  private static class ListBuilder<T> {

    private final List<T> list = new ArrayList<>();

    static <T> ListBuilder<T> create() {
      return new ListBuilder<>();
    }

    ListBuilder<T> add(T element) {
      this.list.add(element);
      return this;
    }

    List<T> build() {
      List<T> result = new ArrayList<>(list);
      list.clear();
      return result;
    }
  }

}
