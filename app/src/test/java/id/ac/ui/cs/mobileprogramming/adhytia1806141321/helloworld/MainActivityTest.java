package id.ac.ui.cs.mobileprogramming.adhytia1806141321.helloworld;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainActivityTest {
    private SimplePassword pass;

    @Test
    public void onClick() {
        pass = new SimplePassword();
        assertEquals(pass.getPassword(), "aboutme");
    }
}