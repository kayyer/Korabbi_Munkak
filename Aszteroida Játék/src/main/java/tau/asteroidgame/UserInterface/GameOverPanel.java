package tau.asteroidgame.UserInterface;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverPanel extends JPanel {
	
    Border blackBorder = BorderFactory.createLineBorder(Color.BLACK, 4);
    Color orangeBackgroundColor = new Color(255, 102, 0);
    MainFrame mainFrame = null;
	
    public GameOverPanel(MainFrame mainframe) {
        super();
        this.mainFrame = mainFrame;

        setAlignmentX(Component.CENTER_ALIGNMENT);
        setAlignmentY(Component.CENTER_ALIGNMENT);
        setOpaque(false);

        JPanel panel = new JPanel();
        panel.setOpaque(false);

        GroupLayout groupLayout = new GroupLayout(panel);
        panel.setLayout(groupLayout);


        JLabel title = new JLabel("Game Over",SwingConstants.CENTER);
        title.setFont(MainFrame.starGuardianFont.deriveFont(72.f));
        title.setBorder(BorderFactory.createEmptyBorder(100, 0, 100, 0));
        title.setBackground(Color.BLUE);
        title.setForeground(Color.YELLOW);
        panel.add(title);

        JPanel panel1 = new JPanel();
        panel1.setOpaque(false);
        panel1.setAlignmentX(CENTER_ALIGNMENT);
        JButton menuButton =  new MenuButton("Return to Menu");
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mainFrame.switchPanel(MainFrame.Panels.MAINMENU);
                updateUI();
            }
        });
        menuButton.setPreferredSize(new Dimension(245, 50));
        panel1.add(menuButton);
        panel.add(panel1);
        
        groupLayout.setAutoCreateContainerGaps(true);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(title)
                .addComponent(panel1)
        );
        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup().addComponent(title))
                .addGroup(groupLayout.createParallelGroup().addComponent(panel1))
        );

        add(panel);
    }
    
    @Override
    public String getName() {
        return "Game Over Panel";
    }
}
