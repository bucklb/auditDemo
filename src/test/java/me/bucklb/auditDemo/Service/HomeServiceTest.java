package me.bucklb.auditDemo.Service;

import me.bucklb.auditDemo.service.HomeService;
import org.junit.Test;

public class HomeServiceTest {

    HomeService homeService = new HomeService();

    // Not a lot to test ...
    @Test
    public void testGreeting() {

        String greeting = homeService.greeting();
        assert(greeting.contains("no place like it"));

    }

}
