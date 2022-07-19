package com.activity.four.controller;

import capital.scalable.restdocs.AutoDocumentation;
import capital.scalable.restdocs.jackson.JacksonResultHandlers;
import capital.scalable.restdocs.response.ResponseModifyingPreprocessors;
import com.activity.four.model.Employee;
import com.activity.four.model.Ticket;
import com.activity.four.response.Response;
import com.activity.four.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.cli.CliDocumentation;
import org.springframework.restdocs.http.HttpDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({MockitoExtension.class, RestDocumentationExtension.class})
@AutoConfigureRestDocs(outputDir = "target/generated-docs")
class EmployeeControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private EmployeeService employeeServiceTest;
    private EmployeeController employeeControllerTest;


    private Employee employee;
    private Ticket ticket;

    @BeforeEach
    void setUp(RestDocumentationContextProvider restDocumentation) {
        employeeControllerTest = new EmployeeController(employeeServiceTest);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeControllerTest)
                .apply(documentationConfiguration(restDocumentation))
                .alwaysDo(JacksonResultHandlers.prepareJackson(objectMapper))
                .alwaysDo(MockMvcRestDocumentation.document("{method-name}",
                        Preprocessors.preprocessRequest(),
                        Preprocessors.preprocessResponse(
                                ResponseModifyingPreprocessors.replaceBinaryContent(),
                                ResponseModifyingPreprocessors.limitJsonArrayLength(objectMapper),
                                Preprocessors.prettyPrint())))
                .apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentation)
                        .uris()
                        .withScheme("http")
                        .withHost("localhost")
                        .withPort(8080)
                        .and().snippets()
                        .withDefaults(CliDocumentation.curlRequest(),
                                HttpDocumentation.httpRequest(),
                                HttpDocumentation.httpResponse(),
                                AutoDocumentation.requestFields(),
                                AutoDocumentation.responseFields(),
                                AutoDocumentation.pathParameters(),
                                AutoDocumentation.requestParameters(),
                                AutoDocumentation.description(),
                                AutoDocumentation.methodAndPath(),
                                AutoDocumentation.section()))
                .build();

        employee = new Employee(1L, "Alice", "Harrington", "Rocca", "HR");
        ticket = new Ticket(1L, "Ticket 1", "Test 1", "Major", "New");
    }

    @Test
    @WithMockUser(username = "vince", roles = "ADMIN")
    void getEmployees() throws Exception {
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "Winston", "Little", "Scott", "IT"),
                new Employee(2L, "Alice", "Harrington", "Rocca", "HR"),
                new Employee(3L, "Jovani", "Sheppard", "Kent", "SALES")
        );
        when(employeeServiceTest.getEmployees()).thenReturn(employees);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees"))
                .andExpect(status().isOk());
    }

    @Test
    void getEmployee() throws Exception {
        Employee employee = new Employee(1L, "Winston", "Little", "Scott", "IT");
        when(employeeServiceTest.getEmployee(any())).thenReturn(employee);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/employees/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", password = "abc", roles = "USER")
    void addEmployee() throws Exception {
        String content = objectWriter.writeValueAsString(employee);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        Response response = objectMapper.readValue(resultContent, Response.class);
        assertEquals(response.isSuccess(), Boolean.TRUE);
    }

    @Test
    void deleteEmployee() throws Exception {
        String content = objectWriter.writeValueAsString(employee);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        Response response = objectMapper.readValue(resultContent, Response.class);
        assertEquals(response.isSuccess(), Boolean.TRUE);
    }

    @Test
    void updateEmployee() throws Exception {
        String content = objectWriter.writeValueAsString(employee);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/employees/1?firstName=Andrew&middleName=Soriano&lastName=Legarte")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        Response response = objectMapper.readValue(resultContent, Response.class);
        assertEquals(response.isSuccess(), Boolean.TRUE);
    }

    @Test
    void removeTicket() throws Exception {
        employee.setAssigned(ticket);
        String content = objectWriter.writeValueAsString(employee);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/employees/1/remove-ticket/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        Response response = objectMapper.readValue(resultContent, Response.class);
        assertEquals(response.isSuccess(), Boolean.TRUE);
    }
}