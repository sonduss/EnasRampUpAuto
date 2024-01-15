
package seleautomation.com.RampUpAuto;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import seleautomation.models.Post;
import seleautomation.models.User;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

public class APITests {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void testUsersAPI() {
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "/users");

        Assert.assertEquals(response.statusCode(), 200);

        JsonPath jsonPath = response.jsonPath();
        // I created post class to support auto completion feature
        List<User> users = jsonPath.getList(".", User.class);

        
        List<Integer> numberOfIds =  jsonPath.getList("id");

        // remove all the null values from the list because
        // jsonPath.getList() function will return null for each row that not
        // contains id'
        numberOfIds.removeIf(id -> id == null);

        System.out.println("number of user ids:" + numberOfIds.size());
        // check if the response has ids
        Assert.assertNotEquals(numberOfIds.size(), 0);

        for (User user : users) {
            System.out.println("Id: " + user.id );
            System.out.println("name: " + user.name );
        }
    }

    @Test
    public void testPostsAPI() {

        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "/posts");

        Assert.assertEquals(response.statusCode(), 200);

        JsonPath jsonPath = response.jsonPath();

        // I created post class to support auto completion feature
        List<Post> posts = jsonPath.getList(".", Post.class);

        // get the post with id 17
        Optional<Post> postWithId17 = posts.stream().filter(post -> post.id == 17).findFirst();

        // the test will fail if theere is no post with id 17
        Post post = postWithId17.get();
        Assert.assertEquals(post.title, "fugit voluptas sed molestias voluptatem provident");

        // check In each object , we have the keys (userid, id body, title) with non
        // empty values

        boolean allPostsHaveKeys = posts.stream()
                .allMatch(p -> !p.userId.toString().isEmpty() && !p.id.toString().isEmpty()
                        && !p.body.toString().isEmpty() && !p.title.toString().isEmpty());

        Assert.assertTrue(allPostsHaveKeys);
    }
}
