package io.simplon.toomanychoco;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test void appGetInstanceReturnInstanceOfApp() {
        App classUnderTest = App.getInstance();
        assertInstanceOf(App.class, classUnderTest, "app instance should be an instance of the class App");
    }
}
