package me.dwywdo.springboottest.sample;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SampleControllerTest2 {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    SampleService mockSampleService;

    @Test
    public void hello() throws Exception {
        when(mockSampleService.getName()).thenReturn("whiteship");
        // String.class? a body type I want
        final String result = testRestTemplate.getForObject("/hello", String.class);
        assertThat(result).isEqualTo("hello whiteship");
    }

    @Test
    public void helloWithWebClient() throws Exception {
        when(mockSampleService.getName()).thenReturn("whiteship");

        webTestClient.get().uri("/hello").exchange()
                     .expectStatus().isOk()
                     .expectBody(String.class).isEqualTo("hello whiteship");
    }

}
