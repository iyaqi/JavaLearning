package org.example;

import org.example.producer.MyProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Autowired
    MyProducer producer;

    @Test
    public void shouldAnswerWithTrue() {

        producer.send();
    }
}
