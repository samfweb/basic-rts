import org.newdawn.slick.Input;
/**
 * Provides user input handling
 */
public class InputHandler {
	
	/**
	 * Updates the world based on user input
	 *
	 * @param world the world
	 * @param input the input
	 */
	public void update(World world, Input input) {
		double targetX = world.getCamera().screenXToGlobalX(input.getMouseX());
		double targetY = world.getCamera().screenYToGlobalY(input.getMouseY());
		
		/*Handles selection on left click 
		 * (Key_Q in use due to eclipse not reading mouse buttons)
		 */
		if (input.isKeyPressed(Input.MOUSE_LEFT_BUTTON) 
				|| input.isKeyPressed(Input.KEY_Q)) {
			WorldObject target = Calculate.closestWorldObject(world, Calculate.SELECTABLE, 
																targetX, targetY);
			//Select the target
			world.select((SelectableObject)target);
		}
		
		//User wants to move camera with WASD keys
		if (input.isKeyPressed(Input.KEY_W) 
				|| input.isKeyPressed(Input.KEY_A)
				|| input.isKeyPressed(Input.KEY_S) 
				|| input.isKeyPressed(Input.KEY_D)) {
			world.getCamera().followPlayerInput();
		}
		//Actions possible if a unit is selected
		if (world.getSelection() != null) {
			
			/*Selected object does action right click 
			 * (Key_E in use due to eclipse not reading mouse buttons)
			 */
			if (input.isKeyPressed(Input.MOUSE_RIGHT_BUTTON) 
					|| input.isKeyPressed(Input.KEY_E)) {
				world.getSelection().actionRightClick(targetX, targetY);
			}	
			
			//Selected object does action 1
			if (input.isKeyPressed(Input.KEY_1)) {
				world.getSelection().action1();
			}
			//Selected object does action 2
			if (input.isKeyPressed(Input.KEY_2)) {
				world.getSelection().action2();
			}
			//Selected object does action 3
			if (input.isKeyPressed(Input.KEY_3)) {
				world.getSelection().action3();
			}
		}
	}
}
