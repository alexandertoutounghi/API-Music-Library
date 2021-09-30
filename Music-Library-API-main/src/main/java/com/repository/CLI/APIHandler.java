package com.repository.CLI;

import com.google.common.net.UrlEscapers;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Scanner;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class APIHandler {
  protected static final String BASE_URL = "http://localhost:8080/home";
  private final String dir;
  protected CloseableHttpClient client;

  public APIHandler(String dir) {
    this.dir = dir;
  }

  public String getResponse(CloseableHttpResponse response) throws IOException {
    if (response.getEntity() == null) System.out.println("transaction complete");
    Scanner scanner = new Scanner(response.getEntity().getContent());
    StringBuilder stringResponse = new StringBuilder();
    while (scanner.hasNext()) stringResponse.append(scanner.nextLine()).append("\n");
    response.close();
    return stringResponse.toString();
  }

  public <T> String Put(T value) throws IOException {
    client = HttpClients.createDefault();
    HttpPut httpPut = new HttpPut(String.format(BASE_URL + dir));
    StringEntity entity = new StringEntity(toJson(value));
    httpPut.setEntity(entity);
    httpPut.setHeader("Accept", "application/json");
    httpPut.setHeader("Content-type", "application/json");
    CloseableHttpResponse response = client.execute(httpPut);
    client.close();
    System.out.println(
      "PATH: " + String.format(BASE_URL + dir) + " AT: " + response.getEntity().toString()
    );
    return getResponse(response);
  }

  public <T> String Post(T value) throws IOException {
    client = HttpClients.createDefault();
    HttpPost httpPost = new HttpPost(String.format(BASE_URL + dir));
    StringEntity entity = new StringEntity(toJson(value));
    httpPost.setEntity(entity);
    httpPost.setHeader("Accept", "application/json");
    httpPost.setHeader("Content-type", "application/json");
    CloseableHttpResponse response = client.execute(httpPost);
    client.close();
    return getResponse(response);
  }

  public String Delete(String key) throws IOException {
    client = HttpClients.createDefault();
    HttpDelete httpDelete = new HttpDelete(
      String.format(BASE_URL + dir + "/%s", escapeURL(key))
    );
    CloseableHttpResponse response = client.execute(httpDelete);
    client.close();
    return getResponse(response);
  }

  public String getAll() throws IOException {
    client = HttpClients.createDefault();
    HttpGet httpGet = new HttpGet(String.format(BASE_URL + dir));
    CloseableHttpResponse response = client.execute(httpGet);
    client.close();
    return getResponse(response);
  }

  public String getDetails(String key) throws IOException {
    client = HttpClients.createDefault();
    HttpGet httpGet = new HttpGet(String.format(BASE_URL + dir + "/%s", escapeURL(key)));
    CloseableHttpResponse response = client.execute(httpGet);
    client.close();
    return getResponse(response);
  }

  public <T> String toJson(T object) {
    Gson gson = new Gson();
    return gson.toJson(object);
  }

  public String escapeURL(String url) {
    return UrlEscapers.urlFragmentEscaper().escape(url);
  }
}
