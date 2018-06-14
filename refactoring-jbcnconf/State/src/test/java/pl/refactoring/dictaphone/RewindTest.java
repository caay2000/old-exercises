package pl.refactoring.dictaphone;

import pl.refactoring.ex_dictaphone.Dictaphone;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/*
 * Invented by Wlodek Krakowski
 */
public class RewindTest {
    private Dictaphone recorder = new Dictaphone();

    @Before
    public void setUp() {
        recorder.power();
    }

    @After
    public void tearDown() {
        recorder = null;
    }

    @Test
    public void canStartRewindWhenTurnedOn() throws Exception {
        recorder.rewind();

        DictaphoneAssertions.assertThat(recorder).isRewinding();
    }

    @Test
    public void canStopRewind() throws Exception {
        recorder.fastForward();
        recorder.stop();

        DictaphoneAssertions.assertThat(recorder).isStopped();
    }

    @Test
    public void cannotRecWhenRewind() throws Exception {
        recorder.rewind();
        recorder.record();

        DictaphoneAssertions.assertThat(recorder).isRewinding();
    }

    @Test
    public void cannotPauseWhenRewind() throws Exception {
        recorder.rewind();
        recorder.pause();

        DictaphoneAssertions.assertThat(recorder).isRewinding();
    }

    @Test
    public void cannotFastForwardDirectlyWhenRewindIsOn() throws Exception {
        recorder.rewind();
        recorder.fastForward();

        DictaphoneAssertions.assertThat(recorder).isRewinding();
    }
}
