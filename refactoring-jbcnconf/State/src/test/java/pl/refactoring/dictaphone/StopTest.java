package pl.refactoring.dictaphone;

import pl.refactoring.ex_dictaphone.Dictaphone;
import org.junit.Test;

import static pl.refactoring.dictaphone.DictaphoneAssertions.assertThat;

/*
 * Invented by Wlodek Krakowski
 */
public class StopTest {
    private Dictaphone recorder = new Dictaphone();

    @Test
    public void cannotStopWhenTurnedOff() throws Exception {
        recorder.stop();
        assertThat(recorder).isTurnedOff();
    }
}
