package name.panitz.game.escape;

import name.panitz.game.framework.FallingImage;
import name.panitz.game.framework.ImageObject;
import name.panitz.game.framework.Vertex;

public class Enemy<I> extends FallingImage<I> {
  int health;
  Weapon<I> weapon;
  public Enemy(Vertex corner, int health, Weapon<I> weapon) {
    super("heart.png", corner, new Vertex(-1,1));
    this.weapon = weapon;
    this.health = health;
  }
}
