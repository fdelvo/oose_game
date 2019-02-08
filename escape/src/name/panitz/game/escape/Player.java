package name.panitz.game.escape;

import name.panitz.game.framework.FallingImage;
import name.panitz.game.framework.Vertex;

public class Player<I> extends FallingImage<I> {
  Weapon<I> weapon;
  int health;
  public Player(Vertex corner, Weapon<I> weapon, int health) {
    super("player.png", corner, new Vertex(0,1));
    this.weapon = weapon;
    this.health = health;
  }

}
