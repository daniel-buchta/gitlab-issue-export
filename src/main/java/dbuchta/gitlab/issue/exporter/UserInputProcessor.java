package dbuchta.gitlab.issue.exporter;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import dbuchta.gitlab.issue.exporter.model.Project;
import dbuchta.gitlab.issue.exporter.model.User;
import dbuchta.gitlab.issue.exporter.service.GitLabApiClient;
import dbuchta.gitlab.issue.exporter.service.IssueExporter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserInputProcessor  {

  private static final Splitter OPTION_INPUT_SPLITTER = Splitter.on(" ").trimResults();

  private static final ImmutableSet<String> QUIT_COMMANDS = ImmutableSet
      .of("2", "exit", "Exit", "Quit", "quite", "q");

  private static final Map<String, String> OPTIONS = ImmutableMap.<String, String>builder()
      .put("1", "Export issues for project")
      .put("2", "Exit")
      .build();

  private final IssueExporter issueExporter;

  private final GitLabApiClient apiClient;

  private final GitLabIssueExportConfigurationProperties config;

  private Scanner scanner;

  @PostConstruct
  public void initScanner() {
    this.scanner = new Scanner(System.in);
  }

  @PreDestroy
  public void closeScanner() {
    this.scanner.close();
  }

  void processUserInputs() {

    String privateToken = Optional.ofNullable(config.getPrivateToken()).map(Strings::emptyToNull)
        .orElseGet(this::readPrivateToken);

    User user = apiClient.getUser(privateToken);
    System.out.println("Hello " + user.getName() + ", #" + user.getId());

    List<String> splitInput = selectOption(scanner);

    while (!QUIT_COMMANDS.contains(splitInput.get(0))) {

      switch (splitInput.get(0)) {
        case "1":
          exportIssues(privateToken, splitInput.size() > 1 ? splitInput.get(1) : "");
          break;

        default:
          System.out.println("Invalid option: " + splitInput.get(0));
      }
      splitInput = selectOption(scanner);
    }
  }

  private String readPrivateToken() {
    String input;
    do {
      System.out.print("Enter your personal GitLab access token: ");
      input = scanner.nextLine().trim();
    } while (input.isEmpty());
    return input;
  }

  private void exportIssues(String privateToken, String projectId) {
    while (projectId.trim().isEmpty()) {
      try {
        List<Project> projects = apiClient.listUserProjects(privateToken);
        System.out.println("Found " + projects.size() + " projects:");

        projects
            .forEach(project -> System.out.println("[" + project.getId() + "] " + project.getName()));
      } catch (Exception e) {
        System.out.println("Error loading list of projects: " +  e.getMessage());
      }
      System.out.print("Enter project ID: ");
      projectId = scanner.nextLine();
    }
    try {
      issueExporter.exportIssues(privateToken, projectId);
    } catch (IOException | RuntimeException e) {
      System.out.println("Error exporting issues: " +  e.getMessage());
    }
  }

  private List<String> selectOption(Scanner scanner) {
    System.out.println("Select an option:");
    OPTIONS.forEach((code, text) -> System.out.println("[" + code + "] " + text));
    System.out.print(">>> ");

    return OPTION_INPUT_SPLITTER.splitToList(scanner.nextLine().trim());
  }

}
