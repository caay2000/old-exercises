package pl.refactoring.ex_dictaphone;

/*
 * Invented by Wlodek Krakowski
 */
public class Engine {

   public static final int TAPE_STOPPED = 0;
   public static final int TAPE_FORWARD = 1;

   public static final int TAPE_FORWARD_3x = 3;
   public static final int TAPE_BACKWARD_3x = -3;

   public static final int TAPE_FAST_FORWARD = 10;
   public static final int TAPE_FAST_BACKWARD = -10;

   public enum HeadState {
      PUT_AWAY, READING, WRITING
   }

   private boolean microphoneOn;
   private boolean speakersOn;
   private int tapeDirection;
   private HeadState head;

   Engine()
   {
      tapeDirection = 0;
      head = HeadState.PUT_AWAY;
      speakersOn = false;
      microphoneOn = false;
   }

   public boolean isMicrophoneOn() {
      return microphoneOn;
   }

   public void setMicrophoneOn(boolean microphoneOn) {
      this.microphoneOn = microphoneOn;
   }

   public boolean isSpeakersOn() {
      return speakersOn;
   }

   public void setSpeakersOn(boolean speakersOn) {
      this.speakersOn = speakersOn;
   }

   public int getTapeDirection() {
      return tapeDirection;
   }

   public void setTapeDirection(int tapeDirection) {
      this.tapeDirection = tapeDirection;
   }

   public HeadState getHead() {
      return head;
   }

   public void setHead(HeadState head) {
      this.head = head;
   }
}
