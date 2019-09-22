package http.view;

import http.model.HttpResponse;
import http.model.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FileResourceViewResolverTest {
    private FileResourceViewResolver fileResourceViewResolver;

    @BeforeEach
    void setUp() {
        fileResourceViewResolver = new FileResourceViewResolver();
    }

    @Test
    void 상태코드_OK_테스트() {
        ModelAndView modelAndView = new ModelAndView(new View("/index.html"));
        HttpResponse response = fileResourceViewResolver.resolve(modelAndView);

        assertThat(response.getHttpStatus()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty();
        assertThat(response.getHeaders()).isNotNull();
    }

    @Test
    void 상태코드_302_테스트() {
        ModelAndView modelAndView = new ModelAndView(new View("redirect:/index.html"));
        HttpResponse response = fileResourceViewResolver.resolve(modelAndView);

        assertThat(response.getHttpStatus()).isEqualTo(HttpStatus.FOUND);
        assertThat(response.getHeader("Location")).isNotEmpty();
    }
}