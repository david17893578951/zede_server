package zede.utilities;

import java.util.HashMap;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class HeaderContent {

  public HttpHeaders defaultHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Location", "http://ejemplos-ejemplo12.193b.starter-ca-central-1.openshiftapps.com");
    headers.add("Content-Type", "application/json");
    return headers;
  }

  public HttpHeaders customHeaders(HashMap<String, String> headersMap) {
    HttpHeaders headers = new HttpHeaders();
    for (HashMap.Entry<String, String> entry : headersMap.entrySet()) {
      headers.add(entry.getKey(), entry.getValue());
    }
    return headers;
  }

  public HttpHeaders messagesResponseHeaders(String messages) {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Access-Control-Expose-Headers", "Message");
    headers.add("Location", "http://ejemplos-ejemplo12.193b.starter-ca-central-1.openshiftapps.com");
    headers.add("Message", messages);
    return headers;
  }
}
