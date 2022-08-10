package co.develhope.angelo.testController;


import co.develhope.angelo.testController.controllers.HomeController;
import co.develhope.angelo.testController.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TestControllerApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private HomeController controller;

	@Autowired
	TestRestTemplate restTemplate;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	void restTemplateHomeTest(){
		String output = this.restTemplate.getForObject("http://localhost:" + port + "/", String.class);
		assertThat(output).contains("Hello World");
	}

	@Test
	void restTemplateUserTest(){
		User user = this.restTemplate.getForObject("http://localhost:" + port + "/user", User.class);
		assertThat(user.getName()).contains("Angelo");
		assertThat(user.getSurname()).contains("Zammataro");
	}

	@Test
	public void mockMVCHomeTest() throws  Exception {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello World")));
	}

	@Test
	public void mockMVCUserTest() throws  Exception {
		this.mockMvc.perform(get("/user")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Angelo"))
				.andExpect(jsonPath("$.surname").value("Zammataro"))
				;
	}
}
