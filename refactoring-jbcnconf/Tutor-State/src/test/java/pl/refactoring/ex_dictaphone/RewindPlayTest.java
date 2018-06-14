package pl.refactoring.ex_dictaphone;

import org.junit.Before;
import org.junit.Test;

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

      DictaphoneAssertions.assertThat(recorder).isBackwardPlaying();
   }

   @Test
   public void doubledFastRewindPlayCanBeEnablesPlayingBack() throws Exception {
      recorder.play();
      recorder.rewind();
      recorder.rewind();

      DictaphoneAssertions.assertThat(recorder).isBackwardPlaying();
   }

   @Test
   public void fastRewindPlayCannotBePaused() throws Exception {
      recorder.play();
      recorder.rewind();
      recorder.pause();

      DictaphoneAssertions.assertThat(recorder).isBackwardPlaying();
   }

   @Test
   public void cannotRecordWhenFastRewindPlaying() throws Exception {
      recorder.play();
      recorder.rewind();
      recorder.record();

      DictaphoneAssertions.assertThat(recorder).isBackwardPlaying();
   }

   @Test
   public void fastRewindPlayCanBeStopped() throws Exception {
      recorder.play();
      recorder.rewind();
      recorder.stop();

      DictaphoneAssertions.assertThat(recorder).isStopped();
   }
}
