package pl.refactoring.ex_dictaphone;

import org.junit.Before;
import org.junit.Test;


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

        DictaphoneAssertions.assertThat(recorder).isRecording();
    }

    @Test
    public void recordingCanBeStopped() {
        recorder.record();
        recorder.stop();

        DictaphoneAssertions.assertThat(recorder).isStopped();
    }

    @Test
    public void recordingCanBePaused() {
        recorder.record();
        recorder.pause();

        DictaphoneAssertions.assertThat(recorder).isRecordingPaused();
    }

    @Test
    public void recordingCanBeUnPaused() {
        recorder.record();
        recorder.pause();
        recorder.pause();

        DictaphoneAssertions.assertThat(recorder).isRecording();
    }

    @Test
    public void cannotFastForwardsWhenRecording() {
        recorder.record();
        recorder.fastForward();

        DictaphoneAssertions.assertThat(recorder).isRecording();
    }

    @Test
    public void CannotRewindWhenRecording() {
        recorder.record();
        recorder.rewind();

        DictaphoneAssertions.assertThat(recorder).isRecording();
    }

    @Test
    public void CannotTurnOffWhenRecording() {
        recorder.record();
        recorder.power();

        DictaphoneAssertions.assertThat(recorder).isRecording();
    }
}
