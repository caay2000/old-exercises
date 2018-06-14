package pl.refactoring.ex_dictaphone.states;

import pl.refactoring.ex_dictaphone.Dictaphone;

/*
 * Invented by Wlodek Krakowski
 */
public class OffState extends State {

   public void handlePower(Dictaphone dictaphone) {
      dictaphone.setState(STOPPED);
      dictaphone.getEngine().stopEngine();
   }
}
