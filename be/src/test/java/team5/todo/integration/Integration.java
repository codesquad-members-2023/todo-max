package team5.todo.integration;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import team5.todo.annotation.IntegrationTest;

@IntegrationTest
public class Integration {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper objectMapper;

	@BeforeEach
	@Sql(scripts = {"classpath:schema/schema.sql", "classpath:schema/data.sql"})
	void setUp() {
	}
}
