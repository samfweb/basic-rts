/**
 * Basic Slick2D game built using OOP.
 * Initial Slick2D skeleton provided by Eleanor McMurty
 */

import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;

/**
 * Main class for the game.
 * Handles initialization, input and rendering.
 */
public class App extends BasicGame {
    /** window width, in pixels */
    public static final int WINDOW_WIDTH = 1024;
    /** window height, in pixels */
    public static final int WINDOW_HEIGHT = 768;

    private World world;

    public App() {
        super("Shadow Build 2: The Buildening");
    }
    /** Initalises the game world
     * @param gc The slick game container object.
     */
    @Override
    public void init(GameContainer gc)
            throws SlickException {
        try {
			world = new World();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    /** Update the game state for a frame.
     * @param gc The Slick game container object.
     * @param delta Time passed since last frame (milliseconds).
     */
    @Override
    public void update(GameContainer gc, int delta)
            throws SlickException {
        // Get data about the current input (keyboard state).
        Input input = gc.getInput();
        world.update(input, delta);
    }

    /** Render the entire screen, so it reflects the current game state.
     * @param gc The Slick game container object.
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(GameContainer gc, Graphics g)
            throws SlickException {
        world.render(g);
    }

    /** Start-up method. Creates the game and runs it.
     * @param args Command-line arguments (ignored).
     */
    public static void main(String[] args)
            throws SlickException {
    	//Run game
      AppGameContainer app = new AppGameContainer(new App());
      app.setShowFPS(false);
      app.setDisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT, false);
      app.start();
    	
    }

}