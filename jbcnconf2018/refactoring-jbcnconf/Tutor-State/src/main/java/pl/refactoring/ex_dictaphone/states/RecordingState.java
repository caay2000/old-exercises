package pl.refactoring.ex_dictaphone.states;


import pl.refactoring.ex_dictaphone.Dictaphone;
import pl.refactoring.ex_dictaphone.Engine;
/*
 * Invented by Wlodek Krakowski
 */
public class RecordingState extends State {

   public void handlePause(Dictaphone dictaphone) {
      dictaphone.setState(new PausedState(dictaphone.getState()));
      dictaphone.getEngine().setTapeDirection(Engine.TAPE_STOPPED);
   }

   public void handleStop(Dictaphone dictaphone) {
      dictaphone.setState(STOPPED);
      dictaphone.getEngine().stopEngine();
   }
}
