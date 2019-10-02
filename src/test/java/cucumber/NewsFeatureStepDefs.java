package cucumber;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.javalab.newsportal.model.News;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class NewsFeatureStepDefs {

    protected MockMvc mockMvc;

    private ResultActions response;
    private String json;
    private final String ENDPOINT_GET_ALL_NEWS = "/news/allnews";
    private final String ENDPOINT_GET_NEWS_BY_ID = "/news/{id}";
    private final String ENDPOINT_SAVE_NEWS = "/news/saveNews";
    private final String ENDPOINT_DELETE_NEWS_BY_ID = "/news/delete/{id}";

    public NewsFeatureStepDefs(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @When("^a user calls GET /news/allnews$")
    public void userCallsAllNews() throws Exception {
        response = mockMvc.perform(get(ENDPOINT_GET_ALL_NEWS));
    }

    @Then("^the status code is 200$")
    public void varifyStatusCode() throws Exception {
        response
                .andExpect(status().isOk());
    }

    @And("^response should includes a list of news$")
    public void varifyListSize() throws Exception {
        response.andExpect(jsonPath("$").isArray());
    }


    @When("^a user makes GET request to /news/2$")
    public void userCallsGetNewsById() throws Exception {
        response = mockMvc
                .perform(get(ENDPOINT_GET_NEWS_BY_ID, 2));
    }

    @Then("^response is a forward to /comments$")
    public void verifyForward() throws Exception {
        response.andExpect(forwardedUrl("/comments"));
    }

    @Given("^news with filled in title, brief and content$")
    public void fillForm() throws JsonProcessingException {
        News news = new News();
        news.setTitle("Kiwi");
        news.setBrief("Tequila");
        news.setContent("Sunrise");
        ObjectMapper objectMapper = new ObjectMapper();
        json = objectMapper.writeValueAsString(news);
    }

    @When("^a user makes POST request to /news/saveNews$")
    public void postNewNews() throws Exception {
        response = mockMvc.perform(post(ENDPOINT_SAVE_NEWS)
                .contentType(APPLICATION_JSON_UTF8).content(json));
    }

    @Given("^news with filled in id, creation date, title, brief and content$")
    public void fillUpdateForm() throws JsonProcessingException {
        News news = new News();
        news.setId(2L);
        news.setCreationDate(LocalDateTime.parse("2019-10-02T20:30:00"));
        news.setTitle("Lemon");
        news.setBrief("Whiskey");
        news.setContent("On the beach!");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.registerModule(new JavaTimeModule());
        json = objectMapper.writeValueAsString(news);
    }

    @When("^a user makes GET request to /news/delete/2$")
    public void makeDeleteRequest() throws Exception {
        response = mockMvc
                .perform(get(ENDPOINT_DELETE_NEWS_BY_ID, 2));
    }

    @When("^a user makes GET request to /news/3$")
    public void getDeletedNews() throws Exception {
        response = mockMvc.perform(get(ENDPOINT_GET_NEWS_BY_ID, 3));
    }

    @Then("^the status code is 404$")
    public void verifyStatus404() throws Exception {
        response.andExpect(status().isNotFound());
    }

    @And("^the response contains a json$")
    public void verifyResponseTypeJson() throws Exception {
        response.andExpect(content().contentType("application/json;charset=UTF-8"));
    }

    @And("^the json's 'message' property includes 'Could not find news with id: 3'$")
    public void jsonContainsMessage() throws Exception {
        response.andExpect(jsonPath("$.message").value("Could not find news with id: 3"));
    }
}
