package name.panitz.game.escape;

import name.panitz.game.framework.ImageObject;
import name.panitz.game.framework.Vertex;

public class Floor<I> extends ImageObject<I> {

  public Floor(Vertex corner ) {
    super("wall.png", corner, new Vertex(0,0));
  }

}
