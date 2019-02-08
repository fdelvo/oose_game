package name.panitz.game.escape;

import name.panitz.game.framework.ImageObject;
import name.panitz.game.framework.Vertex;

public class Enemy<I> extends ImageObject<I> {
  int health;
  Weapon<I> weapon;
  public Enemy(Vertex corner, int health, Weapon<I> weapon) {
    super("enemy.png", corner, new Vertex(0,0));
    this.weapon = weapon;
    this.health = health;
  }
}
