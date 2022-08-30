package pl.refactoring.ex_dictaphone.states;


import pl.refactoring.ex_dictaphone.Dictaphone;

/*
 * Invented by Wlodek Krakowski
 */
public class StoppedState extends State {
   public void handlePlay(Dictaphone dictaphone) {
      dictaphone.setState(PLAYING);
      dictaphone.getEngine().startPlaying();
   }

   public void handlePower(Dictaphone dictaphone) {
      dictaphone.setState(OFF);
      dictaphone.getEngine().stopEngine();
   }

   public void handleRecord(Dictaphone dictaphone) {
      dictaphone.setState(RECORDING);
      dictaphone.getEngine().startRecording();
   }

   public void handleFastForward(Dictaphone dictaphone) {
      dictaphone.setState(FAST_FORWARD);
      dictaphone.getEngine().startForwarding();
   }

   public void handleRewind(Dictaphone dictaphone) {
      dictaphone.setState(REWIND);
      dictaphone.getEngine().startRewind();
   }

   public void handleStop(Dictaphone dictaphone) {
      dictaphone.setState(STOPPED);
      dictaphone.getEngine().stopEngine();
   }
}
