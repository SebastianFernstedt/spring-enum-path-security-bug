package com.example.enumpathbugdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class EnumPathBugDemoApplicationTests {

    @Autowired
    private MockMvc client;

    @Test
    @WithMockUser(username = "admin", authorities = { "ADMIN" })
    void validUserAndValidEnumReturnsOk() throws Exception {
        client.perform(MockMvcRequestBuilders.get("/TYPE_1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "admin", authorities = { "ADMIN" })
    void validUserAndInvalidEnumThrows400BadRequest() throws Exception {
        client.perform(MockMvcRequestBuilders.get("/NOT_AN_ENUM_VALUE"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "user", authorities = { "USER" })
    void invalidUserAndValidEnumThrows403Forbidden() throws Exception {
        client.perform(MockMvcRequestBuilders.get("/TYPE_1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isForbidden())
                .andReturn();
    }

    /**
     * This test fails, as it returns 400 BAD REQUEST instead.
     */
    @Test
    @WithMockUser(username = "user", authorities = { "USER" })
    void invalidUserAndInvalidEnumThrows403Forbidden() throws Exception {
        client.perform(MockMvcRequestBuilders.get("/NOT_AN_ENUM_VALUE"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isForbidden())
                .andReturn();
    }
}
