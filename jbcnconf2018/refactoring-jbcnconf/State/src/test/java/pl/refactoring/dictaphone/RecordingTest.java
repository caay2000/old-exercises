package pl.refactoring.dictaphone;

import pl.refactoring.ex_dictaphone.Dictaphone;
import org.junit.Before;
import org.junit.Test;

import static pl.refactoring.dictaphone.DictaphoneAssertions.assertThat;


/*
 * Invented by Wlodek Krakowski
 */
public class RecordingTest {
    private Dictaphone recorder = new Dictaphone();

    @Before
    public void setUp() {
        recorder.power();
    }

    @Test
    public void recordingCanBeStarted() {
        recorder.record();

        assertThat(recorder).isRecording();
    }

    @Test
    public void recordingCanBeStopped() {
        recorder.record();
        recorder.stop();

        assertThat(recorder).isStopped();
    }

    @Test
    public void recordingCanBePaused() {
        recorder.record();
        recorder.pause();

        assertThat(recorder).isRecordingPaused();
    }

    @Test
    public void recordingCanBeUnPaused() {
        recorder.record();
        recorder.pause();
        recorder.pause();

        assertThat(recorder).isRecording();
    }

    @Test
    public void cannotFastForwardsWhenRecording() {
        recorder.record();
        recorder.fastForward();

        assertThat(recorder).isRecording();
    }

    @Test
    public void CannotRewindWhenRecording() {
        recorder.record();
        recorder.rewind();

        assertThat(recorder).isRecording();
    }

    @Test
    public void CannotTurnOffWhenRecording() {
        recorder.record();
        recorder.power();

        assertThat(recorder).isRecording();
    }
}
