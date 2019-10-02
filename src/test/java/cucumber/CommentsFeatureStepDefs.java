package cucumber;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.javalab.newsportal.config.AppConfig;
import com.javalab.newsportal.config.WebAppInitializer;
import com.javalab.newsportal.model.Comment;
import com.javalab.newsportal.model.News;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class, WebAppInitializer.class, TestConfig.class})
@WebAppConfiguration()
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class CommentsFeatureStepDefs {

    @Autowired
    protected WebApplicationContext webApplicationContext;
    protected MockMvc mockMvc;
    private ResultActions response;
    private String json;
    private News news;
    private final String ENDPOINT_GET_COMMENTS = "/comments";
    private final String ENDPOINT_SAVE_COMMENT = "/comments/save";
    private final String ENDPOINT_DELETE_COMMENT_BY_ID = "/comments/delete/{id}";

    public CommentsFeatureStepDefs(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Given("^news as a request attribute$")
    public void givenExistingNews() {
        news = new News();
        news.setId(1L);
        news.setTitle("Apple");
        news.setBrief("Martini");
        news.setContent("Appletini");
        news.setCreationDate(LocalDateTime.parse("2019-10-01T20:30:00"));
    }

    @When("^server calls GET /comments$")
    public void getComments() throws Exception {
        response = mockMvc
                .perform(get(ENDPOINT_GET_COMMENTS).requestAttr("news", news));
    }

    @Then("^the json contains news with a comments array$")
    public void verifyComments() throws Exception {
        response.andExpect(jsonPath("$.comments").isArray())
                .andExpect(jsonPath("$.comments").isNotEmpty());
    }

    @Given("^comment with filled in author and content$")
    public void fillNewCommentForm() throws JsonProcessingException {
        Comment comment = new Comment();
        comment.setAuthor("badass");
        comment.setContent("real men drink beer");
        ObjectMapper objectMapper = new ObjectMapper();
        json = objectMapper.writeValueAsString(comment);
    }

    @When("^a user makes POST request to /comments/save with newsId as a header$")
    public void postComment() throws Exception {
        response = mockMvc.perform(post(ENDPOINT_SAVE_COMMENT)
                .contentType(APPLICATION_JSON_UTF8).content(json)
                .header("newsId", "1"));
    }

    @When("^comment with filled in id, creation date, author and content$")
    public void fillUpdateCommentForm() throws JsonProcessingException {
        Comment comment = new Comment();
        comment.setAuthor("AppletiniLover");
        comment.setContent("dreamers drink appletini");
        comment.setId(4L);
        comment.setCreationDate(LocalDateTime.parse("2019-10-01T20:30:01"));
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.registerModule(new JavaTimeModule());
        json = objectMapper.writeValueAsString(comment);
    }

    @When(("^a user makes GET request to /comments/delete/4$"))
    public void deleteComment() throws Exception {
        response = mockMvc
                .perform(get(ENDPOINT_DELETE_COMMENT_BY_ID, 4));
    }

    @Then("^the status code is OK$")
    public void varifyStatusCode() throws Exception {
        response
                .andExpect(status().isOk());
    }

    @And("^the response contains a JSON$")
    public void verifyResponseTypeJson() throws Exception {
        response.andExpect(content().contentType("application/json;charset=UTF-8"));
    }
}
