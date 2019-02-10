package name.panitz.game.escape;

import name.panitz.game.framework.ImageObject;
import name.panitz.game.framework.Vertex;

public class Projectile<I> extends ImageObject<I> {
  int damage;
  public Projectile(Vertex corner, Vertex speed, int damage) {
    super("fass.gif", corner, speed);
    this.damage = damage;
  }

}
