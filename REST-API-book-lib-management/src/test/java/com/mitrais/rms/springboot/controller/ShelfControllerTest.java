package com.mitrais.rms.springboot.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mitrais.rms.springboot.model.Shelf;
import com.mitrais.rms.springboot.service.ShelfService;

@RunWith(SpringRunner.class)
@WebMvcTest(ShelfController.class)
public class ShelfControllerTest 
{
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean 
	private ShelfService shelfService;
	
	@Test
	public void createShelf_testExpectCREATEDandReturnValue() throws Exception 
	{		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/shelfs")
				.content("{\"maxCapacity\" : 5,\"currentCapacity\" : 5}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult res = mockMvc.perform(requestBuilder)
				.andExpect(status().isCreated())
				.andReturn();
		JSONAssert.assertEquals("{shelfId:0,maxCapacity:5,currentCapacity:5}",
				res.getResponse().getContentAsString(),true);
		
	}
	
	@Test
	public void getShelfById_testExpectOKandReturnValue() throws Exception 
	{
		
		Shelf shelf = new Shelf(1, 5, 5);
		when(shelfService.displayShelfById(shelf.getShelfId())).thenReturn(shelf );
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/shelfs/{shelfId}",shelf.getShelfId())
				.accept(MediaType.APPLICATION_JSON);
		MvcResult res = mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andReturn();
		JSONAssert.assertEquals("{shelfId:1,maxCapacity:5,currentCapacity:5}",
				res.getResponse().getContentAsString(),true);
	}
	
	@Test
	public void getShelfById_testExpectNO_CONTENTandReturnEmpty() throws Exception 
	{
		Shelf shelf = new Shelf(1, 5, 5);
		when(shelfService.displayShelfById(shelf.getShelfId())).thenReturn(shelf );
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/shelfs/{shelfId}",2)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult res = mockMvc.perform(requestBuilder)
				.andExpect(status().isNoContent())
				.andReturn();
		assertEquals("", res.getResponse().getContentAsString());
	}
	
}
