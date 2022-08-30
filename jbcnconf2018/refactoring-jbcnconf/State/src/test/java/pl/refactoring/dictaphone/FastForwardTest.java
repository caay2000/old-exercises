package pl.refactoring.dictaphone;

import pl.refactoring.ex_dictaphone.Dictaphone;
import org.junit.Before;
import org.junit.Test;

/*
 * Invented by Wlodek Krakowski
 */
public class FastForwardTest {
    private Dictaphone recorder = new Dictaphone();

    @Before
    public void setUp() {
        recorder.power();
    }

    @Test
    public void canStartFastForwardWhenTurnedOn() throws Exception {
        recorder.fastForward();

        DictaphoneAssertions.assertThat(recorder).isFastForwarding();
    }

    @Test
    public void canStopFastForward() throws Exception {
        recorder.fastForward();
        recorder.stop();

        DictaphoneAssertions.assertThat(recorder).isStopped();
    }

    @Test
    public void cannotRecWhenFastForward() throws Exception {
        recorder.fastForward();
        recorder.record();

        DictaphoneAssertions.assertThat(recorder).isFastForwarding();
    }

    @Test
    public void cannotPauseWhenFastForward() throws Exception {
        recorder.fastForward();
        recorder.pause();

        DictaphoneAssertions.assertThat(recorder).isFastForwarding();
    }

    @Test
    public void cannotRewindDirectlyWhenFastForwardIsOn() throws Exception {
        recorder.fastForward();
        recorder.rewind();

        DictaphoneAssertions.assertThat(recorder).isFastForwarding();
    }
}
