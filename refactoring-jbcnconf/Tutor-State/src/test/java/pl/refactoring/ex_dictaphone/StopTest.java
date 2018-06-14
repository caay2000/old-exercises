package pl.refactoring.ex_dictaphone;

import org.junit.Test;

/*
 * Invented by Wlodek Krakowski
 */
public class StopTest {
    private Dictaphone recorder = new Dictaphone();

    @Test
    public void cannotStopWhenTurnedOff() throws Exception {
        recorder.stop();
        DictaphoneAssertions.assertThat(recorder).isTurnedOff();
    }
}
