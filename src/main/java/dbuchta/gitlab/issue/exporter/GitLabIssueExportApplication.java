package dbuchta.gitlab.issue.exporter;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@AllArgsConstructor
public class GitLabIssueExportApplication {

  public static void main(String[] args) {
    SpringApplication.run(GitLabIssueExportApplication.class, args);
  }

  private final UserInputProcessor userInputProcessor;

  @Bean
  @Profile("!test") // don't register command line runner in tests
  public CommandLineRunner commandLineRunner() {
    return args -> userInputProcessor.processUserInputs();
  }

}
