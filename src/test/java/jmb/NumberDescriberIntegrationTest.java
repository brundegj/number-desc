/*
 * James Brundege
 * Date: 2018-01-10
 * MIT license: https://opensource.org/licenses/MIT
 */
package jmb;

import jmb.numdesc.NumberDescriberApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.Map;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NumberDescriberApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"management.port=0"})
public class NumberDescriberIntegrationTest {

    @LocalServerPort
    int port;

    @Inject
    private TestRestTemplate testRestTemplate;

    @SuppressWarnings("unchecked")
    @Test
    public void shouldReturnWordDescriptionForValidRequest() {
        ResponseEntity<Map> entity = this.testRestTemplate
                .getForEntity("http://localhost:" + this.port + "/numdesc/123", Map.class);

        then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(entity.getBody().values()).contains("One hundred and twenty three");
    }

    @Test
    public void shouldReturn400ErrorIfNumberIsNotAnInteger() {
        ResponseEntity<Map> entity = this.testRestTemplate
                .getForEntity("http://localhost:" + this.port + "/numdesc/123.4", Map.class);

        then(entity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        then((String)entity.getBody().get("errorMessage")).contains("Invalid number").contains("123.4");
    }

}
