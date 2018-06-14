package pl.refactoring.ex_dictaphone;


import pl.refactoring.ex_dictaphone.states.State;

/*
 * Invented by Wlodek Krakowski
 */
public class Dictaphone {
   private State state;
   private Engine engine = new Engine();

   public Dictaphone() {
      this.setState(State.OFF);
   }

   public State getState() {
      return state;
   }

   public Engine getEngine() {
      return engine;
   }

   public void play() {
      state.handlePlay(this);
   }

   public void power() {
      state.handlePower(this);
   }

   public void record() {
      state.handleRecord(this);
   }

   public void pause() {
      state.handlePause(this);
   }

   public void fastForward() {
      state.handleFastForward(this);
   }

   public void rewind() {
      state.handleRewind(this);
   }

   public void stop() {
      state.handleStop(this);
   }

   public void setState(State state) {
      this.state = state;
   }
}
