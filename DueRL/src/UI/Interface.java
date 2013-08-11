/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Color;

import World.Environment;

/**
 *
 * @author eniirane
 */
public class Interface implements Runnable {
    
    private JFrame frame;
    private Environment world;
    
    private Font playAreaFont = new Font("Courier", Font.BOLD,16);
    private Font infoAreaFont = new Font("Courier", Font.BOLD,12);
    public JTextArea playArea;
    public JTextArea combatLog;
    public CombatLog combatLogString;
    public StatDisplay statDisplay;
    public JTextArea infoArea;
    
    private String infoText = "WASD or arrow keys to move." + "\n" + "Space to target/attack." + "\n" + "Q to quit.";
    
    public Interface(Environment world) {
        this.world = world;
        this.combatLogString = world.getCombatLog();
        this.statDisplay = new StatDisplay(world);
    }
    
    @Override
    public void run() {
        frame = new JFrame("DueRL");
        frame.setPreferredSize(new Dimension(450, 365));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
        GridLayout layout = new GridLayout(1, 2);
        container.setLayout(layout);        
        
        this.playArea = new JTextArea("");
        playArea.setBackground(Color.BLACK);
        playArea.setForeground(Color.WHITE);
        playArea.setEditable(false);
        playArea.setFont(playAreaFont);
        container.add(playArea);
        
        container.add(sideBar());
        
        this.repaint();
               
        playArea.addKeyListener(new EventHandler(world, playArea, this));
    }

    public JPanel sideBar() {
        JPanel panel = new JPanel(new GridLayout(3,1));
        
        panel.setBackground(Color.BLACK);

        JTextArea keyBindings = new JTextArea(infoText);
        combatLog = new JTextArea("");
        infoArea = new JTextArea("");

        keyBindings.setBackground(Color.BLACK);
        keyBindings.setFont(infoAreaFont);
        keyBindings.setForeground(Color.DARK_GRAY);
        
        combatLog.setBackground(Color.BLACK);
        combatLog.setFont(infoAreaFont);
        combatLog.setForeground(Color.LIGHT_GRAY);
        
        infoArea.setBackground(Color.BLACK);
        infoArea.setFont(infoAreaFont);
        infoArea.setForeground(Color.DARK_GRAY);
        
        panel.add(keyBindings);
        panel.add(combatLog);
        panel.add(infoArea);
        
        return panel;
    }
    
    public void repaint() {
        playArea.setText(world.toString());
        updateCombatLog();
        updateInfoArea();
    }
    
    public void updateCombatLog() {
        combatLog.setText(combatLogString.toString());
    }
    
    public void updateInfoArea() {
        infoArea.setText(statDisplay.toString());
    }
    
    public void exit() {
        System.out.println("Bye!");
        frame.dispose();
    }
}
