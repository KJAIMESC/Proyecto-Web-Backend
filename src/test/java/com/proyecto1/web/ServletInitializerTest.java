package com.proyecto1.web;

import org.junit.jupiter.api.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;
import static org.mockito.Mockito.*;

public class ServletInitializerTest {

    @Test
    public void testConfigure() {
        // Mocking SpringApplicationBuilder
        SpringApplicationBuilder builderMock = mock(SpringApplicationBuilder.class);

        // Creating an instance of ServletInitializer
        ServletInitializer servletInitializer = new ServletInitializer();

        // Calling the configure method with the mocked builder
        servletInitializer.configure(builderMock);

        // Verifying that the sources method of SpringApplicationBuilder is called with WebApplication.class
        verify(builderMock, times(1)).sources(WebApplication.class);
    }
}
