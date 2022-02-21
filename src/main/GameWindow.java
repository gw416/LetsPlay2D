package main;

import javax.swing.JFrame;

public class GameWindow {
	
	private JFrame jframe;

	public GameWindow(GamePanel gamePanel) {

		jframe = new JFrame();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(gamePanel);
		jframe.setLocationRelativeTo(null); // bring up window in center of screen
		//jframe.setLocation(150, 100);
		jframe.setResizable(false);
		jframe.pack(); // using jpanel dimesions for singlecomponent
		jframe.setVisible(true); // keep at end to prevent bug

	}
	
}
