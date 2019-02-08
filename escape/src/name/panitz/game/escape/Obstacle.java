package name.panitz.game.escape;

import name.panitz.game.framework.ImageObject;
import name.panitz.game.framework.Vertex;

public class Obstacle<I> extends ImageObject<I> {

  public Obstacle(Vertex corner ) {
    super("fass.gif", corner, new Vertex(-1,0));
  }

}
