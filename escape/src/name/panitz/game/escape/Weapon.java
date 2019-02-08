package name.panitz.game.escape;

import name.panitz.game.framework.ImageObject;
import name.panitz.game.framework.Vertex;

public class Weapon<I> extends ImageObject<I> {
  Projectile<I> projectile;
  public Weapon(Vertex corner, Projectile<I> projectile) {
    super("weapon.png", corner, new Vertex(0,0));
    this.projectile = projectile;
  }

}
