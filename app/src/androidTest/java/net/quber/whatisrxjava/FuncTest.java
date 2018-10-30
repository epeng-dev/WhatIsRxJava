package net.quber.whatisrxjava;

import org.junit.Test;

import io.reactivex.Observable;

import static junit.framework.Assert.assertTrue;


/**
 * Created by quber on 18. 9. 14.
 */
public class FuncTest {
    @Test
    public void test99_Imperative()
    {
        int dan = 3;
        for (int row = 1; row < 10; row++) {
            System.out.println(dan + '*' + row + '=' + (dan * row));
        }

        assertTrue(true);
    }
}
