package name.panitz.game.klaus;


import name.panitz.game.escape.Escape;
import name.panitz.game.example.simple.SimpleGame;
import name.panitz.game.framework.fx.GameApplication;

public class PlayFX extends GameApplication {
  public PlayFX(){
    super(new Escape<>());
  }
  public static void main(String[] args) {
    PlayFX.launch();
  }
}
