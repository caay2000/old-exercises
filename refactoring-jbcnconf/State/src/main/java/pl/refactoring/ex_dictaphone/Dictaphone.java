package pl.refactoring.ex_dictaphone;

/*
 * Invented by Wlodek Krakowski
 */
public class Dictaphone {

    public static final String OFF = "OFF";
    public static final String STOPPED = "STOPPED";
    public static final String PLAYING = "PLAYING";
    public static final String FORWARD_PLAY_3x = "FORWARD_PLAY_3x";
    public static final String BACKWARD_PLAY_3x = "BACKWARD_PLAY_3x";
    public static final String RECORDING = "RECORDING";
    public static final String PAUSED = "PAUSED";
    public static final String FAST_FORWARD = "FAST_FORWARD";
    public static final String REWIND = "REWIND";

    private String prevState;
    private String state;
    private Engine engine = new Engine();

    public Dictaphone() {
        this.state = OFF;
    }

    public String getState() {
        return state;
    }

    public Engine getEngine() {
        return engine;
    }

    public void play() {
        if (state == PLAYING) {
            state = FORWARD_PLAY_3x;

            engine.setHead(Engine.HeadState.READING);
            engine.setTapeDirection(Engine.TAPE_FORWARD_3x);
            engine.setMicrophoneOn(false);
            engine.setSpeakersOn(true);
        } else if (state == STOPPED) {
            state = PLAYING;

            engine.setHead(Engine.HeadState.READING);
            engine.setTapeDirection(Engine.TAPE_FORWARD);
            engine.setMicrophoneOn(false);
            engine.setSpeakersOn(true);
        } else if (state == FORWARD_PLAY_3x) {
            state = PLAYING;

            engine.setHead(Engine.HeadState.READING);
            engine.setTapeDirection(Engine.TAPE_FORWARD);
            engine.setMicrophoneOn(false);
            engine.setSpeakersOn(true);
        }
    }

    public void power() {

        if (state == OFF) {
            state = STOPPED;

            engine.setHead(Engine.HeadState.PUT_AWAY);
            engine.setTapeDirection(Engine.TAPE_STOPPED);
            engine.setMicrophoneOn(false);
            engine.setSpeakersOn(false);
        } else if (state == STOPPED) {
            state = OFF;

            engine.setHead(Engine.HeadState.PUT_AWAY);
            engine.setTapeDirection(Engine.TAPE_STOPPED);
            engine.setMicrophoneOn(false);
            engine.setSpeakersOn(false);
        }
    }

    public void record() {
        if (state == STOPPED) {
            state = RECORDING;

            engine.setHead(Engine.HeadState.WRITING);
            engine.setTapeDirection(Engine.TAPE_FORWARD);
            engine.setMicrophoneOn(true);
            engine.setSpeakersOn(false);
        }
    }

    public void pause() {
        if (state == PAUSED) {
            state = prevState;
            engine.setTapeDirection(Engine.TAPE_FORWARD);
        } else if (state == PLAYING || state == RECORDING) {
            prevState = state;
            state = PAUSED;
            engine.setTapeDirection(Engine.TAPE_STOPPED);
        }
    }

    public void fastForward() {
        if (state == STOPPED) {
            state = FAST_FORWARD;

            engine.setHead(Engine.HeadState.PUT_AWAY);
            engine.setTapeDirection(Engine.TAPE_FAST_FORWARD);
            engine.setMicrophoneOn(false);
            engine.setSpeakersOn(false);
        } else if (state == PLAYING) {
            state = FORWARD_PLAY_3x;

            engine.setHead(Engine.HeadState.READING);
            engine.setTapeDirection(Engine.TAPE_FORWARD_3x);
            engine.setMicrophoneOn(false);
            engine.setSpeakersOn(true);
        }
    }

    public void rewind() {
        if (state == STOPPED) {
            state = REWIND;

            engine.setHead(Engine.HeadState.PUT_AWAY);
            engine.setTapeDirection(Engine.TAPE_FAST_BACKWARD);
            engine.setMicrophoneOn(false);
            engine.setSpeakersOn(false);
        } else if (state == PLAYING) {
            state = BACKWARD_PLAY_3x;

            engine.setHead(Engine.HeadState.READING);
            engine.setTapeDirection(Engine.TAPE_BACKWARD_3x);
            engine.setMicrophoneOn(false);
            engine.setSpeakersOn(true);
        }
    }

    public void stop() {
        if (state != OFF) {
            state = STOPPED;

            engine.setHead(Engine.HeadState.PUT_AWAY);
            engine.setTapeDirection(Engine.TAPE_STOPPED);
            engine.setMicrophoneOn(false);
            engine.setSpeakersOn(false);
        }
    }
}
