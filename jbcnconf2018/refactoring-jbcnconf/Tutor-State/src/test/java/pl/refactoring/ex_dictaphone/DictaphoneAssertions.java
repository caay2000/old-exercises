package pl.refactoring.ex_dictaphone;

import pl.refactoring.ex_dictaphone.states.PausedState;
import pl.refactoring.ex_dictaphone.states.State;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

/*
 * Invented by Wlodek Krakowski
 */
public class DictaphoneAssertions extends AbstractAssert<DictaphoneAssertions, Dictaphone> {

    protected DictaphoneAssertions(Dictaphone actual) {
        super(actual, DictaphoneAssertions.class);
    }

    public static DictaphoneAssertions assertThat(Dictaphone dictaphone) {
        return new DictaphoneAssertions(dictaphone);
    }

    private void isInState(State state) {
        Assertions.assertThat(actual.getState())
                .overridingErrorMessage("Expected states is <%s> but was <%s>", state, actual.getState())
                .isEqualTo(state);
    }

    private void isHeadInState(Engine.HeadState headState) {
        Assertions.assertThat(actual.getEngine().getHead())
                .overridingErrorMessage("Expected head states is <%s> but was <%s>", headState, actual.getEngine().getHead())
                .isEqualTo(headState);
    }

    private void isTapeMoving(int tapeDirection) {
        Assertions.assertThat(actual.getEngine().getTapeDirection())
                .overridingErrorMessage("Expected tape movement is <%d> but was <%d>", tapeDirection, actual.getEngine().getTapeDirection())
                .isEqualTo(tapeDirection);
    }

    private void isMicrophoneEnabled(boolean on) {
        Assertions.assertThat(actual.getEngine().isMicrophoneOn())
                .overridingErrorMessage("Expected microphone-activity is <%s> but was <%s>", on, actual.getEngine().isMicrophoneOn())
                .isEqualTo(on);
    }

    private void areSpeakersEnabled(boolean on) {
        Assertions.assertThat(actual.getEngine().isSpeakersOn())
                .overridingErrorMessage("Expected speakers-activity is <%s> but was <%s>", on, actual.getEngine().isSpeakersOn())
                .isEqualTo(on);
    }

    public DictaphoneAssertions isTurnedOff() {
        isInState(State.OFF);

        isHeadInState(Engine.HeadState.PUT_AWAY);
        isMicrophoneEnabled(false);
        areSpeakersEnabled(false);
        isTapeMoving(Engine.TAPE_STOPPED);

        return this;
    }

    public DictaphoneAssertions isStopped() {
        isInState(State.STOPPED);

        isHeadInState(Engine.HeadState.PUT_AWAY);
        isMicrophoneEnabled(false);
        areSpeakersEnabled(false);
        isTapeMoving(Engine.TAPE_STOPPED);

        return this;
    }

    public DictaphoneAssertions isPlaying() {
        isInState(State.PLAYING);

        isHeadInState(Engine.HeadState.READING);
        isMicrophoneEnabled(false);
        areSpeakersEnabled(true);
        isTapeMoving(Engine.TAPE_FORWARD);

        return this;
    }

    public DictaphoneAssertions isPlayingPaused() {
        isInState(new PausedState(State.PLAYING));

        isHeadInState(Engine.HeadState.READING);
        isMicrophoneEnabled(false);
        areSpeakersEnabled(true);
        isTapeMoving(Engine.TAPE_STOPPED);

        return this;
    }

    public DictaphoneAssertions isRecordingPaused() {
        isInState(new PausedState(State.RECORDING));

        isHeadInState(Engine.HeadState.WRITING);
        isMicrophoneEnabled(true);
        areSpeakersEnabled(false);
        isTapeMoving(Engine.TAPE_STOPPED);

        return this;
    }

    public DictaphoneAssertions isRecording() {
        isInState(State.RECORDING);

        isHeadInState(Engine.HeadState.WRITING);
        isMicrophoneEnabled(true);
        areSpeakersEnabled(false);
        isTapeMoving(Engine.TAPE_FORWARD);

        return this;
    }

    public DictaphoneAssertions isFastForwarding() {
        isInState(State.FAST_FORWARD);

        isHeadInState(Engine.HeadState.PUT_AWAY);
        isMicrophoneEnabled(false);
        areSpeakersEnabled(false);
        isTapeMoving(Engine.TAPE_FAST_FORWARD);

        return this;
    }

    public DictaphoneAssertions isRewinding() {
        isInState(State.REWIND);

        isHeadInState(Engine.HeadState.PUT_AWAY);
        isMicrophoneEnabled(false);
        areSpeakersEnabled(false);
        isTapeMoving(Engine.TAPE_FAST_BACKWARD);

        return this;
    }

    public DictaphoneAssertions isForwardPaying() {
        isInState(State.FORWARD_PLAY_3x);

        isHeadInState(Engine.HeadState.READING);
        isMicrophoneEnabled(false);
        areSpeakersEnabled(true);
        isTapeMoving(Engine.TAPE_FORWARD_3x);

        return this;
    }

    public DictaphoneAssertions isBackwardPlaying() {
        isInState(State.BACKWARD_PLAY_3x);

        isHeadInState(Engine.HeadState.READING);
        isMicrophoneEnabled(false);
        areSpeakersEnabled(true);
        isTapeMoving(Engine.TAPE_BACKWARD_3x);

        return this;
    }
}