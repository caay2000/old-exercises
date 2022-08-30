package pl.refactoring.dictaphone;

import pl.refactoring.ex_dictaphone.Dictaphone;
import org.junit.Before;
import org.junit.Test;

import static pl.refactoring.dictaphone.DictaphoneAssertions.assertThat;

/*
 * Invented by Wlodek Krakowski
 */
public class PlayingTest {
    private Dictaphone recorder = new Dictaphone();

    @Before
    public void setUp() {
        recorder.power();
    }

    @Test
    public void shouldPlayWhenPlayPressed() throws Exception {
        recorder.play();

        assertThat(recorder).isPlaying();
    }

    @Test
    public void shouldFwdPlay2xWhenPlayDoublePressed() throws Exception {
        recorder.play();
        recorder.play();

        assertThat(recorder).isForwardPaying();
    }

    @Test
    public void shouldPlayBackWhenPlayPressed() throws Exception {
        recorder.play();
        recorder.play();
        recorder.play();

        assertThat(recorder).isPlaying();
    }

    @Test
    public void playingCanBeStopped() throws Exception {
        recorder.play();
        recorder.stop();

        assertThat(recorder).isStopped();
    }

    @Test
    public void playingCanBePaused() throws Exception {
        recorder.play();
        recorder.pause();

        assertThat(recorder).isPlayingPaused();
    }

    @Test
    public void playingCanBeUnPaused() throws Exception {
        recorder.play();
        recorder.pause();
        recorder.pause();

        assertThat(recorder).isPlaying();
    }

}
