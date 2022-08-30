package pl.refactoring.ex_dictaphone.states;

import pl.refactoring.ex_dictaphone.Dictaphone;
import pl.refactoring.ex_dictaphone.Engine;

/*
 * Invented by Wlodek Krakowski
 */
public class PausedState extends State {
   private State prevState;

   public PausedState(State prevState) {
      this.prevState = prevState;
   }

   public void handlePause(Dictaphone dictaphone) {
         dictaphone.setState(prevState);
         dictaphone.getEngine().setTapeDirection(Engine.TAPE_FORWARD);
   }

   public void handleStop(Dictaphone dictaphone) {
         dictaphone.setState(STOPPED);
         dictaphone.getEngine().stopEngine();
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof PausedState)) return false;

      PausedState that = (PausedState) o;

      if (prevState != null ? !prevState.equals(that.prevState) : that.prevState != null) return false;

      return true;
   }

   @Override
   public int hashCode() {
      return prevState != null ? prevState.hashCode() : 0;
   }
}
