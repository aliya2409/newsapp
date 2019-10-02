import com.javalab.newsportal.model.Comment;
import com.javalab.newsportal.model.News;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AssuredTest {

    @Test
    void givenValidNewsId_whenGet_thenCorrect() {
        given()
                .when().get("/news/112").then().statusCode(200).assertThat()
                .body("title", equalTo("www"))
                .body("id", equalTo(112))
                .body("brief", equalTo("www"))
                .body("creationDate", equalTo("2019-09-23T13:53:36"))
                .body("comments", hasSize(2));
    }

    @Test
    void givenInvalidNewsId_whenGet_thenNotFound() {
        String invalidId = "200";
        given()
                .when().get("/news/" + invalidId).then().statusCode(404).assertThat()
                .body("message", equalTo("Could not find news with id: " + invalidId));
    }

    @Test
    void givenUnsupportedUri_whenGet_thenErrorJson() {
        String uri = "/";
        given().when().get(uri).then().statusCode(404).assertThat()
                .body("message", equalTo("No handler found for GET " + uri));
    }

    @Test
    void whenGetAllNewsAndJsonHasRequiredKV_thenCorrect() {
        News[] newsArr = given()
                .when().get("/news/allnews").then().statusCode(200).extract()
                .as(News[].class);
        assertEquals(3, newsArr.length);
    }

    @Test
    void whenPostNewNews_thenResponseStatus200() {
        News news = new News();
        news.setTitle("BrandNew");
        news.setBrief("lalalala");
        news.setContent("opopopop");
        RequestSpecification reqSpec = given()
                .header("Content-Type", "application/json");
        given().with().spec(reqSpec).body(news)
                .when()
                .request("POST", "/news/saveNews")
                .then().log().ifStatusCodeMatches(greaterThan(200)).statusCode(200);
    }

    @Test
    void givenValidNewsIdGetDelete_thenResponseStatus200() {
        String newsId = "126";
        given()
                .when().get("/news/delete/" + newsId).then().statusCode(200);
    }

    @Test
    void givenNewsIdWhenSaveNewComment_thenOk() {
        String newsId = "125";
        Comment comment = new Comment();
        comment.setAuthor("test");
        comment.setContent("rrr");
        RequestSpecification reqSpec = given()
                .header("Content-Type", "application/json").header("newsId", newsId);
        given().with().spec(reqSpec).body(comment)
                .when()
                .request("POST", "/comments/save")
                .then().log().ifStatusCodeMatches(greaterThan(200)).statusCode(200);
    }

    @Test
    void givenNewsIdWhenEditComment_thenOk() {
        String newsId = "125";
        Comment comment = new Comment();
        comment.setId(132L);
        comment.setCreationDate(LocalDateTime.parse("2019-09-26T11:15:49"));
        comment.setAuthor("edited");
        comment.setContent("ssss");
        comment.setRating(10);
        RequestSpecification reqSpec = given()
                .header("Content-Type", "application/json").header("newsId", newsId);
        given().with().spec(reqSpec).body(comment)
                .when()
                .request("POST", "/comments/save")
                .then().log().ifStatusCodeMatches(greaterThan(200)).statusCode(200);
    }

    @Test
    void givenValidCommentIdWhenGetDelete_thenOk() {
        String commentId = "131";
        given()
                .when().get("/comments/delete/" + commentId).then().statusCode(200);
    }
}
