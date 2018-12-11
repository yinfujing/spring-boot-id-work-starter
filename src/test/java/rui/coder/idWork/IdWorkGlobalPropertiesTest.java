package rui.coder.idWork;

import org.junit.jupiter.api.Test;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class IdWorkGlobalPropertiesTest {

    @Test
    void bit() {
        assertEquals(16,1<<4);
        assertEquals(15,~(-1<<4));
        System.out.println(-1<<4);
        System.out.println(~16);
        System.out.println(Integer.toBinaryString(-16));
        System.out.println(Integer.toBinaryString(16));
    }


    @Test
    void newTime() {
        System.out.println(System.currentTimeMillis());
    }

    @Test
    void timeParse() {
        long time=1544515710817L;
        Date date=new Date(time);

        Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        System.out.println(format.format(date));
    }
}