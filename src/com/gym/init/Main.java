package com.gym.init;
 
import com.gym.mvc.controllers.loggin.ControllerLoggin;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
 
public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				e.printStackTrace();
			}
 
			new ControllerLoggin().init();
		});
	}
}
 