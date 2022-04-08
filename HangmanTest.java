import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
class HangmanTest {

    @Test
    void letter_count_test() {
            var s = new Hangman();
        ArrayList<Character> d = new ArrayList<>();
        assertEquals(2,s.letter_count('e',"prejudice",d));
        assertEquals(0,s.letter_count('j',"finally",d));
        System.out.println(d);
    }
    @Test
    void process_guess_test(){
        var s = new Hangman();
        ArrayList<Character> guess_list = new ArrayList<>();
        for(char i='a';i<='z';i++){
            guess_list.add(i);
        }
        guess_list.remove(0);
        guess_list.remove(0);
        guess_list.remove(0);
        guess_list.remove(0);
        System.out.println(guess_list);
        assertFalse(s.process_guess('a',guess_list,guess_list));
        assertTrue(s.process_guess('k',guess_list,guess_list));
    }
}