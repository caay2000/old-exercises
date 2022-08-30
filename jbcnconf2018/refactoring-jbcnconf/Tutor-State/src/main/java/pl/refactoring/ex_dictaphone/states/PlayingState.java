package pl.refactoring.ex_dictaphone.states;

import pl.refactoring.ex_dictaphone.Dictaphone;
import pl.refactoring.ex_dictaphone.Engine;

/*
 * Invented by Wlodek Krakowski
 */
public class PlayingState extends State {
   public void handlePlay(Dictaphone dictaphone) {
      dictaphone.setState(FORWARD_PLAY_3x);
      dictaphone.getEngine().startForwardPlaying();
   }

   public void handlePause(Dictaphone dictaphone) {
      dictaphone.setState(new PausedState(dictaphone.getState()));
      dictaphone.getEngine().setTapeDirection(Engine.TAPE_STOPPED);
   }

   public void handleFastForward(Dictaphone dictaphone) {
      dictaphone.setState(FORWARD_PLAY_3x);
      dictaphone.getEngine().startForwardPlaying();
   }

   public void handleRewind(Dictaphone dictaphone) {
      dictaphone.setState(BACKWARD_PLAY_3x);
      dictaphone.getEngine().startBackwardPlaying();
   }

   public void handleStop(Dictaphone dictaphone) {
      dictaphone.setState(STOPPED);
      dictaphone.getEngine().stopEngine();
   }
}
