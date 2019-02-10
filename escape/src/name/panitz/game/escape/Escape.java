package name.panitz.game.escape;

import java.util.ArrayList;
import java.util.List;

import name.panitz.game.framework.*;

public class Escape<I, S> extends AbstractGame<I, S> {
    List<Enemy<I>> enemies = new ArrayList<>();
    List<Obstacle<I>> obstacles = new ArrayList<>();
    List<Projectile<I>> projectiles = new ArrayList<>();
    List<Weapon<I>> weapons = new ArrayList<>();
    Player<I> dude;
    List<Floor<I>> floor = new ArrayList<>();
    TextObject<I> infoText
            = new TextObject<>(new Vertex(30, 35)
            , "Kleines Beispielspiel", "Helvetica", 28);

    SoundObject<S> outch = new SoundObject<>("outch.wav");

    public Escape() {
        super(new Player<>(new Vertex(0, 400), null, 100), 800, 600);

        dude = (Player<I>) getPlayer();

        floor.add(new Floor<>(new Vertex(0, 500)));


        projectiles.add(new Projectile<>(new Vertex(800, 450), new Vertex(3, 0), 10));
        getGOss().add(floor);
        getGOss().add(projectiles);
        getButtons().add(new Button("Pause", () -> pause()));
        getButtons().add(new Button("Start", () -> start()));
        getButtons().add(new Button("Exit", () -> System.exit(0)));
    }

    private void checkPlayerWallCollsions() {
        for (GameObject<I> floor : floor) {
            if (dude.touches(floor) && (dude.getVelocity().y > 0 || dude.getVelocity().y < 0)) {
                dude.stopUpwardsMovement();
                System.out.println("Touches floor");
                return;
            }
        }
    }

    private void checkPlayerProjectileCollision() {
        for (GameObject<I> p : projectiles) {
            if (dude.touches(p)) {
                dude.health = dude.health - 10;
                p.getPos().moveTo(new Vertex(-1000, p.getPos().y));
                System.out.println(dude.health);
                return;
            }
        }
    }

    @Override
    public void doChecks() {
        checkPlayerProjectileCollision();
        checkPlayerWallCollsions();
        System.out.println(player.getVelocity());
    }

  /*@Override
  public boolean isStopped() {
    return super.isStopped() || stiche>=10;
  }*/

    @Override
    public void keyPressedReaction(KeyCode keycode) {
        if (keycode != null)
            switch (keycode) {
                case RIGHT_ARROW:
                    if (player.getVelocity().x != 2) {
                        player.setVelocity(new Vertex(2, player.getVelocity().y));
                        System.out.println("right");
                    }
                    break;
                case LEFT_ARROW:
                    if (player.getVelocity().x != -2) {
                        player.setVelocity(new Vertex(-2, player.getVelocity().y));
                        System.out.println("left");
                    }
                    break;
                case UP_ARROW:
                    dude.jump();
                    break;
                case VK_SPACE:
                    dude.fire(projectiles);
                    break;
                default:
                    ;
            }
    }

  @Override
  public void keyReleasedReaction(KeyCode keycode) {
    if (keycode!=null)
      switch (keycode){
        case RIGHT_ARROW:
            if(player.getVelocity().x != -2) {
                player.setVelocity(new Vertex(0,player.getVelocity().y));
            }
          break;
        case LEFT_ARROW:
            if(player.getVelocity().x != 2) {
                player.setVelocity(new Vertex(0, player.getVelocity().y));
            }
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