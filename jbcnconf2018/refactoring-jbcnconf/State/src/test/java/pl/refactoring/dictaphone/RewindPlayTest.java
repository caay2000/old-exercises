package pl.refactoring.dictaphone;

import pl.refactoring.ex_dictaphone.Dictaphone;
import org.junit.Before;
import org.junit.Test;

import static pl.refactoring.dictaphone.DictaphoneAssertions.assertThat;

/*
 * Invented by Wlodek Krakowski
 */
public class RewindPlayTest {
   private Dictaphone recorder = new Dictaphone();

   @Before
   public void setUp()
   {
      recorder.power();
   }

   @Test
   public void fastRewindPlayCanBeEnabledOnlyWhenPlaying() throws Exception {
      recorder.play();
      recorder.rewind();

      assertThat(recorder).isBackwardPlaying();
   }

   @Test
   public void doubledFastRewindPlayCanBeEnablesPlayingBack() throws Exception {
      recorder.play();
      recorder.rewind();
      recorder.rewind();

      assertThat(recorder).isBackwardPlaying();
   }

   @Test
   public void fastRewindPlayCannotBePaused() throws Exception {
      recorder.play();
      recorder.rewind();
      recorder.pause();

      assertThat(recorder).isBackwardPlaying();
   }

   @Test
   public void cannotRecordWhenFastRewindPlaying() throws Exception {
      recorder.play();
      recorder.rewind();
      recorder.record();

      assertThat(recorder).isBackwardPlaying();
   }

   @Test
   public void fastRewindPlayCanBeStopped() throws Exception {
      recorder.play();
      recorder.rewind();
      recorder.stop();

      assertThat(recorder).isStopped();
   }
}
