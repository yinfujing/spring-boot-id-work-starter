package rui.coder.idWork;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {IdWorkConfiguration.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
class IdWorkTest {

    @Autowired
    private IdWorker idWorker;

    @Test
    void nextId() {
        int size = 100000;
        Set<Long> set = new HashSet<>();
        for (int i = 0; i < size; i++) {
            set.add(idWorker.nextId());
        }
        assertEquals(size, set.size());
    }
}
