package pl.refactoring.dictaphone;

import pl.refactoring.ex_dictaphone.Dictaphone;
import org.junit.Before;
import org.junit.Test;

import static pl.refactoring.dictaphone.DictaphoneAssertions.assertThat;


/*
 * Invented by Wlodek Krakowski
 */
public class FastForwardPlayTest {
    private Dictaphone recorder = new Dictaphone();

    @Before
    public void setUp() {
        recorder.power();
    }

    @Test
    public void fastForwardPlayCanBeEnabledOnlyWhenPlaying() throws Exception {
        recorder.play();
        recorder.fastForward();

        assertThat(recorder).isForwardPaying();
    }

    @Test
    public void doubledFastForwardPlayCannotEnablePlayingBack() throws Exception {
        recorder.play();
        recorder.fastForward();
        recorder.fastForward();

        assertThat(recorder).isForwardPaying();
    }

    @Test
    public void fastForwardPlayCannotBePaused() throws Exception {
        recorder.play();
        recorder.fastForward();
        recorder.pause();

        assertThat(recorder).isForwardPaying();
    }

    @Test
    public void cannotRecordWhenFastForwardPlaying() throws Exception {
        recorder.play();
        recorder.fastForward();
        recorder.record();

        assertThat(recorder).isForwardPaying();
    }

    @Test
    public void fastForwardPlayCanBeStopped() throws Exception {
        recorder.play();
        recorder.fastForward();
        recorder.stop();

        assertThat(recorder).isStopped();
    }
}
