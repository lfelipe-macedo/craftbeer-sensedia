package com.beerhouse;

import com.beerhouse.controller.BeerController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackageClasses = Application.class)
public class ApplicationTests {

    @Autowired
    private BeerController controller;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

}