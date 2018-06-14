package pl.refactoring.ex_dictaphone.states;

import pl.refactoring.ex_dictaphone.Dictaphone;

/*
 * Invented by Wlodek Krakowski
 */
public abstract class State {
   public static final State OFF = new OffState();
   public static final State STOPPED = new StoppedState();
   public static final State PLAYING = new PlayingState();
   public static final State FORWARD_PLAY_3x = new ForwardPlayingState();
   public static final State BACKWARD_PLAY_3x = new BackwardPlayingState();
   public static final State RECORDING = new RecordingState();
   public static final State FAST_FORWARD = new FastForwardState();
   public static final State REWIND = new RewindState();

   protected State() {
      //To change body of created methods use File | Settings | File Templates.
   }

   public void handlePlay(Dictaphone dictaphone){};

   public void handlePower(Dictaphone dictaphone){};

   public void handleRecord(Dictaphone dictaphone){};

   public void handlePause(Dictaphone dictaphone){};

   public void handleFastForward(Dictaphone dictaphone){};

   public void handleRewind(Dictaphone dictaphone){};

   public void handleStop(Dictaphone dictaphone){};
}
