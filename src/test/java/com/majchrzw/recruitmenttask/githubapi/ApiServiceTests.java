package com.majchrzw.recruitmenttask.githubapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.majchrzw.recruitmenttask.githubapi.DTO.Branch;
import com.majchrzw.recruitmenttask.githubapi.DTO.Commit;
import com.majchrzw.recruitmenttask.githubapi.DTO.Repository;
import com.majchrzw.recruitmenttask.githubapi.Service.ApiService;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ApiServiceTests {
	
	private final MockWebServer webServer = new MockWebServer();
	
	private ApiService apiService;
	
	@BeforeEach
	public void setUp(){
		apiService = new ApiService(WebClient.builder().baseUrl(webServer.url("/api/test").toString()).build());
	}
	
	@AfterEach
	void tearDown() throws IOException {
		webServer.shutdown();
	}
	
	@Test
	void shouldProperlyGetDataFromMockWebServer() throws Exception{
		webServer.enqueue(new MockResponse()
				.setResponseCode(200)
				.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.setBody("""
						[
						 {
						 "id": 609511706,
						 "node_id": "R_kgDOJFRpGg",
						 "name": "Electrical-blinds",
						 "full_name": "wojmaj22/Electrical-blinds",
						 "private": false,
						 "owner": {
						 "login": "wojmaj22",
						 "id": 95643176,
						 "type": "User",
						 "site_admin": false
						 },
						 "fork": false,
						 "created_at": "2023-03-04T12:01:31Z",
						 "updated_at": "2023-03-07T22:40:06Z",
						 "pushed_at": "2023-03-07T22:40:20Z",
						 "git_url": "git://github.com/wojmaj22/Electrical-blinds.git",
						 "ssh_url": "git@github.com:wojmaj22/Electrical-blinds.git",
						 "homepage": null,
						 "size": 6,
						 "default_branch": "main"
						 }
						 ]"""));
		webServer.enqueue(new MockResponse()
				.setResponseCode(200)
				.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.setBody("""
						[
						{
						"name": "main",
						"commit": {
						"sha": "836615d3f99c2d34352a56e345b65ba8706ee42b",
						"url": "https://api.github.com/repos/wojmaj22/Electrical-blinds/commits/836615d3f99c2d34352a56e345b65ba8706ee42b"
						},
						"protected": false
						}
						]"""));
		ArrayList<Branch> branches = new ArrayList<Branch>();
		branches.add(new Branch("main", new Commit("836615d3f99c2d34352a56e345b65ba8706ee42b")));
		ArrayList<Repository> properRepos = new ArrayList<Repository>();
		properRepos.add(new Repository("wojmaj22","Electrical-blinds", branches));
		
		ArrayList<Repository> repos = apiService.getListOfRepos("wojmaj22");
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(repos);
		assertEquals(properRepos.get(0), repos.get(0));
	}
	
	@Test
	void shouldThrow404ErrorWhenUserDoesNotExist(){
		webServer.enqueue(new MockResponse().setResponseCode(HttpStatus.NOT_FOUND.value()));
		
		assertThrows(WebClientResponseException.class, () -> {
			apiService.getListOfRepos("test");
		});
	}
	
}
