package name.panitz.game.escape;

import java.util.ArrayList;
import java.util.List;

import name.panitz.game.framework.*;

public class Escape<I,S> extends AbstractGame<I,S>{
  List<Enemy<I>> enemies = new ArrayList<>();
  List<Obstacle<I>> obstacles = new ArrayList<>();
  List<Projectile<I>> projectiles = new ArrayList<>();
  List<Weapon<I>> weapons = new ArrayList<>();
  Player<I> dude;
  List<Floor<I>> floor = new ArrayList<>();
  TextObject<I> infoText
    = new TextObject<>(new Vertex(30,35)
    ,"Kleines Beispielspiel","Helvetica",28);

  SoundObject<S> outch = new SoundObject<>("outch.wav");

  public Escape() {
    super(new Player<>(new Vertex(0,400), null, 100),800,600);

    dude = (Player<I>) getPlayer();

    floor.add(new Floor<>(new Vertex(0,500)));


    obstacles.add(new Obstacle<>(new Vertex(800, 10)));
    getGOss().add(floor);
    getButtons().add(new Button("Pause", ()-> pause()));
    getButtons().add(new Button("Start", ()-> start()));
    getButtons().add(new Button("Exit", ()-> System.exit(0)));
  }

  private void checkPlayerWallCollsions() {
    for (GameObject<I> floor : floor) {
      if (dude.touches(floor) && (dude.getVelocity().y > 0 || dude.getVelocity().y <0)) {
        dude.stopUpwardsMovement();
        System.out.println("Touches floor");
        return;
      }
    }
  }

  @Override
  public void doChecks() {
    checkPlayerWallCollsions();
  }

  /*@Override
  public boolean isStopped() {
    return super.isStopped() || stiche>=10;
  }*/

  @Override
  public void keyPressedReaction(KeyCode keycode) {
    if (keycode!=null)
      switch (keycode){
        case RIGHT_ARROW:
          player.setVelocity(new Vertex(2,player.getVelocity().y));
          break;
        case LEFT_ARROW:
          player.setVelocity(new Vertex(-2,player.getVelocity().y));
          break;
        case VK_SPACE:
          dude.jump();
          break;
        default: ;
      }
  }

  @Override
  public void keyReleasedReaction(KeyCode keycode) {
    if (keycode!=null)
      switch (keycode){
        case RIGHT_ARROW:
          player.setVelocity(new Vertex(0,player.getVelocity().y));
          break;
        case LEFT_ARROW:
          player.setVelocity(new Vertex(0,player.getVelocity().y));
          break;
        case DOWN_ARROW:
          player.setVelocity(new Vertex(player.getVelocity().x, 0));
          break;
        case UP_ARROW:
          player.setVelocity(new Vertex(player.getVelocity().x, 0));
          break;
        default: ;
      }
  }
}