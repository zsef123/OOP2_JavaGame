package controller;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.*;

/**
 * @author Nathan Sweet <misc@n4te.com>
 * http://slick.ninjacave.com/forum/viewtopic.php?t=1041
 */
public class CrossFadeTransition implements Transition {
   private GameState firstState;
   private GameState secondState;
   private FadeOutTransition fade;

   public CrossFadeTransition (int fadeMillis) {
      fade = new FadeOutTransition(Color.black, fadeMillis);
   }

   @Override
   public void init (GameState firstState, GameState secondState) {
      this.firstState = firstState;
      this.secondState = secondState;
   }

   @Override
   public void preRender (StateBasedGame game, GameContainer container, Graphics g) throws SlickException {
   }

   @Override
   public void postRender (StateBasedGame game, GameContainer container, Graphics g) throws SlickException {
      firstState.render(container, game, g);
      g.clearAlphaMap();
      g.setDrawMode(Graphics.MODE_ALPHA_MAP);
      fade.postRender(game, container, g);
      g.setDrawMode(Graphics.MODE_ALPHA_BLEND);
      secondState.render(container, game, g);
      g.setDrawMode(Graphics.MODE_NORMAL);
   }

   @Override
   public void update (StateBasedGame game, GameContainer container, int delta) throws SlickException {
      fade.update(game, container, delta);
   }

   @Override
   public boolean isComplete () {
      return fade.isComplete();
   }

}