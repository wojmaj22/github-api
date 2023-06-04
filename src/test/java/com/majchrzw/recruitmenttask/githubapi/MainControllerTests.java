package com.majchrzw.recruitmenttask.githubapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.majchrzw.recruitmenttask.githubapi.DTO.Branch;
import com.majchrzw.recruitmenttask.githubapi.DTO.Commit;
import com.majchrzw.recruitmenttask.githubapi.DTO.Repository;
import com.majchrzw.recruitmenttask.githubapi.Service.ApiService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTests {
	
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	ApiService apiService;
	
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void shouldWorkWithBasicRequest() throws Exception{
		// given
		ArrayList<Branch> branches = new ArrayList<Branch>();
		branches.add(new Branch("branch1", new Commit("1234")));
		
		ArrayList<Repository> repos = new ArrayList<Repository>();
		repos.add(new Repository("testUser","testRepo", branches));
		// when
		Mockito.when(apiService.getListOfRepos("test")).thenReturn(repos);
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(repos);
		// then
		mockMvc.perform(MockMvcRequestBuilders.get("/api/test").accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().string(json));
	}
	@Test
	public void shouldShow404ErrorWhenUserDoesNotExist() throws Exception{
		// when
		Mockito.when(apiService.getListOfRepos("test")).thenThrow(WebClientResponseException.class);
		// then
		mockMvc.perform(MockMvcRequestBuilders.get("/api/test").accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldShow406ErrorWhenWrongHeaderIsProvided() throws Exception{
		// when
		Mockito.when(apiService.getListOfRepos("test")).thenReturn(null);
		// then
		mockMvc.perform(MockMvcRequestBuilders.get("/api/test").accept(MediaType.APPLICATION_XML_VALUE))
				.andExpect(status().isNotAcceptable());
	}
	
	
	
}
