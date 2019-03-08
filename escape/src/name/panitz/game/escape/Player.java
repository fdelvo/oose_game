package name.panitz.game.escape;

import name.panitz.game.framework.FallingImage;
import name.panitz.game.framework.Vertex;

import java.util.List;

public class Player<I> extends FallingImage<I> {
  Weapon<I> weapon;
  int health;
  public Player(Vertex corner, Weapon<I> weapon, int health) {
    super("player.png", corner, new Vertex(0,1));
    this.weapon = weapon;
    this.health = health;
  }

  public void fire(List<Projectile<I>> projectile) {
    projectile.add(new Projectile<>(new Vertex(this.getPos().x + this.getWidth() + 5, this.getPos().y), new Vertex(3,0), 10));
    System.out.println("Added");
  }
}
