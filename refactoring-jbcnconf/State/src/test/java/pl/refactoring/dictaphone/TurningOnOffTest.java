package pl.refactoring.dictaphone;

import pl.refactoring.ex_dictaphone.Dictaphone;
import org.junit.Test;

/*
 * Invented by Wlodek Krakowski
 */
public class TurningOnOffTest {
    private Dictaphone recorder = new Dictaphone();

    @Test
    public void shouldBeTurnedOffWhenCreated() throws Exception {
        DictaphoneAssertions.assertThat(recorder).isTurnedOff();
    }

    @Test
    public void powerButtonTurnsItOn() throws Exception {
        recorder.power();

        DictaphoneAssertions.assertThat(recorder).isStopped();
    }

    @Test
    public void shouldTurnOnAndTurnOff() throws Exception {
        recorder.power();
        recorder.power();

        DictaphoneAssertions.assertThat(recorder).isTurnedOff();
    }

    @Test
    public void cannotFastForwardWhenTurnedOff() throws Exception {
        recorder.fastForward();

        DictaphoneAssertions.assertThat(recorder).isTurnedOff();
    }

    @Test
    public void cannotRewindWhenTurnedOff() throws Exception {
        recorder.fastForward();

        DictaphoneAssertions.assertThat(recorder).isTurnedOff();
    }
}
