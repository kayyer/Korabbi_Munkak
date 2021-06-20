package tau.asteroidgame.UserInterface;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainMenuPanel extends JPanel {
    Border blackBorder = BorderFactory.createLineBorder(Color.BLACK, 4);
    Color orangeBackgroundColor = new Color(255, 102, 0);
    MainFrame mainFrame = null;

    public MainMenuPanel(MainFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;

        setAlignmentX(Component.CENTER_ALIGNMENT);
        setAlignmentY(Component.CENTER_ALIGNMENT);
        setOpaque(false);

        JPanel panel = new JPanel();
        panel.setOpaque(false);

        GroupLayout groupLayout = new GroupLayout(panel);
        panel.setLayout(groupLayout);


        JLabel title = new JLabel("Asteroid Game",SwingConstants.CENTER);
        title.setFont(MainFrame.starGuardianFont.deriveFont(72.f));
        title.setBorder(BorderFactory.createEmptyBorder(100, 0, 100, 0));
        title.setBackground(Color.BLUE);
        title.setForeground(Color.YELLOW);
        panel.add(title);

        JPanel panel1 = new JPanel();
        panel1.setOpaque(false);
        panel1.setAlignmentX(CENTER_ALIGNMENT);
        JButton newGameButton =  new MenuButton("New Game");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mainFrame.switchPanel(MainFrame.Panels.NEWGAME);
                updateUI();
            }
        });
        newGameButton.setPreferredSize(new Dimension(245, 50));
        panel1.add(newGameButton);

        JPanel panel2 = new JPanel();
        panel2.setOpaque(false);
        panel2.setAlignmentX(CENTER_ALIGNMENT);

        JButton quitButton =  new MenuButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        quitButton.setPreferredSize(new Dimension(245, 50));

        panel2.add(quitButton);

        panel.add(panel1);
        panel.add(panel2);

        groupLayout.setAutoCreateContainerGaps(true);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(title)
                .addComponent(panel1)
                .addComponent(panel2)
        );
        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup().addComponent(title))
                .addGroup(groupLayout.createParallelGroup().addComponent(panel1))
                .addGroup(groupLayout.createParallelGroup().addComponent(panel2)));

        add(panel);
    }

    @Override
    public String getName() {
        return "Main Menu Panel";
    }
}
