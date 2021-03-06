package com.activity.four.controller;

import capital.scalable.restdocs.AutoDocumentation;
import capital.scalable.restdocs.jackson.JacksonResultHandlers;
import capital.scalable.restdocs.response.ResponseModifyingPreprocessors;
import com.activity.four.model.Employee;
import com.activity.four.model.Ticket;
import com.activity.four.response.Response;
import com.activity.four.service.TicketService;
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
class TicketControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private TicketService ticketServiceTest;
    private TicketController ticketControllerTest;

    private Ticket ticket;
    private Employee employee;

    @BeforeEach
    void setUp(RestDocumentationContextProvider restDocumentation) {
        ticketControllerTest = new TicketController(ticketServiceTest);
        mockMvc = MockMvcBuilders.standaloneSetup(ticketControllerTest)
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

        ticket = new Ticket(1L, "Ticket 1", "Test 1", "Major", "New");
        employee = new Employee(1L, "Alice", "Harrington", "Rocca", "HR");
    }

    @Test
    void getTickets() throws Exception {
        List<Ticket> tickets = Arrays.asList(
                new Ticket(1L, "Ticket 1", "Test 1", "Major", "New"),
                new Ticket(2L, "Ticket 2", "Test 2", "Critical", "New"),
                new Ticket(3L, "Ticket 3", "Test 3", "Normal", "New")
        );
        when(ticketServiceTest.getTickets()).thenReturn(tickets);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/tickets"))
                .andExpect(status().isOk());
    }

    @Test
    void getTicket() throws Exception {
        Ticket ticket = new Ticket(1L, "Ticket 1", "Test 1", "Major", "New");
        when(ticketServiceTest.getTicket(any())).thenReturn(ticket);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/tickets/1"))
                .andExpect(status().isOk());
    }

    @Test
    void listTicket() throws Exception {
        List<Ticket> tickets = Arrays.asList(
                new Ticket(1L, "Ticket 1", "Test 1", "Major", "New"),
                new Ticket(2L, "Ticket 2", "Test 2", "Critical", "New")
        );
        when(ticketServiceTest.listTickets(employee.getId())).thenReturn(tickets);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/tickets/list-ticket/1"))
                .andExpect(status().isOk());
    }

    @Test
    void addTicket() throws Exception {
        String content = objectWriter.writeValueAsString(ticket);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/tickets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        Response response = objectMapper.readValue(resultContent, Response.class);
        assertEquals(response.isSuccess(), Boolean.TRUE);
    }

    @Test
    void deleteTicket() throws Exception {
        String content = objectWriter.writeValueAsString(ticket);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/tickets/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        Response response = objectMapper.readValue(resultContent, Response.class);
        assertEquals(response.isSuccess(), Boolean.TRUE);
    }

    @Test
    void updateTicket() throws Exception {
        String content = objectWriter.writeValueAsString(ticket);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/tickets/1?severity=Closed")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        Response response = objectMapper.readValue(resultContent, Response.class);
        assertEquals(response.isSuccess(), Boolean.TRUE);
    }

    @Test
    void assignTicket() throws Exception {
        String content = objectWriter.writeValueAsString(ticket);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/tickets/1/add-assignee/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        Response response = objectMapper.readValue(resultContent, Response.class);
        assertEquals(response.isSuccess(), Boolean.TRUE);
    }

    @Test
    void addWatcher() throws Exception {
        String content = objectWriter.writeValueAsString(ticket);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/tickets/1/add-watcher/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        Response response = objectMapper.readValue(resultContent, Response.class);
        assertEquals(response.isSuccess(), Boolean.TRUE);
    }
}