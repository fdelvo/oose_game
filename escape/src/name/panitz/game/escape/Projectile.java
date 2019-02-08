package name.panitz.game.escape;

import name.panitz.game.framework.ImageObject;
import name.panitz.game.framework.Vertex;

public class Projectile<I> extends ImageObject<I> {
  int damage;
  public Projectile(Vertex corner, int damage) {
    super("projectile.png", corner, new Vertex(0,0));
    this.damage = damage;
  }

}
