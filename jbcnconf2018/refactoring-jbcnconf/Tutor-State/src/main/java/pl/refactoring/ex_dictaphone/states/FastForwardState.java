package pl.refactoring.ex_dictaphone.states;

import pl.refactoring.ex_dictaphone.Dictaphone;


/*
 * Invented by Wlodek Krakowski
 */
public class FastForwardState extends State {

   public void handleStop(Dictaphone dictaphone) {
      dictaphone.setState(STOPPED);
      dictaphone.getEngine().stopEngine();
   }
}
