package dbuchta.gitlab.issue.exporter;

import dbuchta.gitlab.issue.exporter.service.IssueExporter;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConfigurationProperties("gitlab-export")
@Data
@Slf4j
public class GitLabIssueExportConfigurationProperties implements IssueExporter.Config{

  /**
   * (Required) Root URI for the v4 API of the GitLab instance
   */
  private String baseApiUri;

  /**
   * (Optional) Personal access token to access the GitLab API
   */
  private String privateToken;

  /**
   * (Optional) Output folder for exports
   */
  private String exportOutputFolder;

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    log.info("GitLab API base URI: {}", baseApiUri);
    return builder.requestFactory(this::skipSslCertificateVerification).rootUri(baseApiUri).build();
  }

  private HttpComponentsClientHttpRequestFactory skipSslCertificateVerification() {

    SSLContext sslContext;
    try {
      sslContext = SSLContexts.custom()
          .loadTrustMaterial(null, (X509Certificate[] chain, String authType) -> true)
          .build();

    } catch (GeneralSecurityException e) {
      log.error("Can't create SSL context: {}", e.toString());
      throw new RuntimeException(e);
    }

    SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

    HttpComponentsClientHttpRequestFactory result = new HttpComponentsClientHttpRequestFactory();

    result.setHttpClient(HttpClients.custom().setSSLSocketFactory(csf).build());
    return result;
  }
}
