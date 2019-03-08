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
    List<GameObject<I>> background = new ArrayList<>();
    int score = 0;

    TextObject<I> scoreText
            = new TextObject<>(new Vertex(30, 35)
            , "Score:" + score, "Helvetica", 28);

    TextObject<I> healthText
            = new TextObject<>(new Vertex(30, 60)
            , "Health:", "Helvetica", 28);

    SoundObject<S> outch = new SoundObject<>("outch.wav");

    public Escape() {
        super(new Player<>(new Vertex(10, 450), null, 100), 800, 600);

        dude = (Player<I>) getPlayer();
        healthText.text = "Health: " + dude.health;
        background.add(scoreText);
        background.add(healthText);
        floor.add(new Floor<>(new Vertex(0, 500)));

        enemies.add(new Enemy<>(new Vertex(getWidth(),400), 100, null));

        projectiles.add(new Projectile<>(new Vertex(800, 401), new Vertex(3, 0), 10));
        getGOss().add(floor);
        getGOss().add(projectiles);
        getGOss().add(enemies);
        getGOss().add(background);
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
            if(dude.getPos().x <= 0.0 || dude.getPos().x >= this.getWidth() - dude.getWidth()) {
                dude.stopSidewaysMovement();
                System.out.println("Stop");
            }
        }
    }

    private void checkEnemyWallCollsions() {
        for (Enemy<I> g : enemies) {
            if (g.getPos().x+g.getWidth()<0) {
                g.getPos().x = getWidth();
            }
            if (dude.touches(g)){
                g.getPos().moveTo(new Vertex(getWidth()+10,g.getPos().y));
                dude.health = dude.health - 10;
                healthText.text = "Health: " + dude.health;
                System.out.println("Hit");
            }
            for (GameObject<I> floor : floor) {
                if (g.touches(floor) && (g.getVelocity().y > 0 || g.getVelocity().y <0)) {
                    g.getVelocity().y = 0.0;
                }
            }
            for (Projectile<I> p: projectiles) {
                if (p.touches(g)) {
                    p.getPos().x = 0-p.getWidth();
                    p.getVelocity().x = -1;
                    g.health = g.health - p.damage;
                    if (g.health == 0) {
                        score++;
                        scoreText.text = "Score: " + score;
                        g.getPos().x = getWidth();
                        g.health = 100;
                    }
                }
            }
        }
    }

    private void checkPlayerProjectileCollision() {
        for (Projectile<I> p : projectiles) {
            if (dude.touches(p)) {
                dude.health = dude.health - p.damage;
                healthText.text = "Health: " + dude.health;
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
        checkEnemyWallCollsions();
        isStopped();
        if (dude.health == 0) {
            background.add(new TextObject<>(new Vertex(getWidth()/2, getHeight()/2)
                    , "Game Over", "Helvetica", 28));
        }
    }


  public boolean isStopped() {
    return super.isStopped() || dude.health==0;
  }

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