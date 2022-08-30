package pl.refactoring.ex_dictaphone.states;

import pl.refactoring.ex_dictaphone.Dictaphone;

/*
 * Invented by Wlodek Krakowski
 */
public class ForwardPlayingState extends State {
   public void handlePlay(Dictaphone dictaphone) {
      dictaphone.setState(PLAYING);
      dictaphone.getEngine().startPlaying();
   }

   public void handleStop(Dictaphone dictaphone) {
      dictaphone.setState(STOPPED);
      dictaphone.getEngine().stopEngine();
   }
}
