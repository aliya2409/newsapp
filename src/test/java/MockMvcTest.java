import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.javalab.newsportal.config.AppConfig;
import com.javalab.newsportal.config.WebAppInitializer;
import com.javalab.newsportal.model.Comment;
import com.javalab.newsportal.model.News;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class, WebAppInitializer.class})
@WebAppConfiguration()
public class MockMvcTest {

    @Autowired
    protected WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void givenNewsURIWithIdPathVariable_whenMockMVC_thenForward() throws Exception {
        mockMvc
                .perform(get("/news/{id}", 112)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/comments"));
    }

    @Test
    void givenExistingNews_whenGetComments_thenOk() throws Exception {
        News news = new News();
        news.setTitle("www");
        news.setContent("www");
        news.setBrief("www");
        news.setCreationDate(LocalDateTime.parse("2019-09-23T13:53:36"));
        news.setId(112L);
        mockMvc
                .perform(get("/comments").requestAttr("news", news))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.comments").isArray());
    }

    @Test
    void givenAllNewsURI_whenGet_thenOk() throws Exception {
        mockMvc
                .perform(get("/news/allnews"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));
    }

    @Test
    void givenWac_whenServletContext_thenItProvidesNewsController() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(webApplicationContext.getBean("newsController"));
    }

    @Test
    void givenNewNews_whenSave_thenOk() throws Exception {
        News news = new News();
        news.setTitle("mockMvc");
        news.setBrief("lalalala");
        news.setContent("opopopop");
        ObjectMapper objectMapper = new ObjectMapper();
        String newsJson = objectMapper.writeValueAsString(news);
        mockMvc.perform(post("/news/saveNews")
                .contentType(APPLICATION_JSON_UTF8).content(newsJson))
                .andExpect(status().isOk());
    }

    @Test
    void givenEditedNews_whenSave_thenOk() throws Exception {
        News news = new News();
        news.setId(133L);
        news.setTitle("mockMvc edited");
        news.setBrief("gggg");
        news.setContent("opopopop");
        news.setCreationDate(LocalDateTime.parse("2019-09-26T14:09:04"));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.registerModule(new JavaTimeModule());
        String newsJson = objectMapper.writeValueAsString(news);

        mockMvc.perform(post("/news/saveNews")
                .contentType(APPLICATION_JSON_UTF8).content(newsJson))
                .andExpect(status().isOk());
    }

    @Test
    void givenNewsId_whenDelete_thenOk() throws Exception {
        String newsId = "137";
        mockMvc
                .perform(get("/news/delete/{id}", newsId))
                .andExpect(status().isOk());
    }

    @Test
    void givenNewsId_whenSaveNewComment_thenOk() throws Exception {
        String newsId = "112";
        Comment comment = new Comment();
        comment.setAuthor("mockMvc");
        comment.setContent("new");
        ObjectMapper objectMapper = new ObjectMapper();
        String commentJson = objectMapper.writeValueAsString(comment);
        mockMvc.perform(post("/comments/save")
                .contentType(APPLICATION_JSON_UTF8).content(commentJson)
                .header("newsId", newsId))
                .andExpect(status().isOk());
    }

    @Test
    void givenNewsId_whenSaveEditedComment_thenOk() throws Exception {
        String newsId = "112";
        Comment comment = new Comment();
        comment.setId(135L);
        comment.setAuthor("mockMvc");
        comment.setContent("gggfggf");
        comment.setRating(5);
        comment.setCreationDate(LocalDateTime.parse("2019-09-26T14:44:55"));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.registerModule(new JavaTimeModule());
        String commentJson = objectMapper.writeValueAsString(comment);

        mockMvc.perform(post("/comments/save")
                .contentType(APPLICATION_JSON_UTF8).content(commentJson)
                .header("newsId", newsId))
                .andExpect(status().isOk());
    }

    @Test
    void givenCommentId_whenDelete_thenOk() throws Exception {
        String commentId = "136";
        mockMvc
                .perform(get("/comments/delete/{id}", commentId))
                .andExpect(status().isOk());
    }
}
