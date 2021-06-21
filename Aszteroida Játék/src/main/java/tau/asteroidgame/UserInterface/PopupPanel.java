package tau.asteroidgame.UserInterface;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopupPanel extends JPanel {
    /**
     * A panel belsejében elhejezett panel.
     * Az méret beállítására használatos.
     */
    private JPanel innerPanel;
    /**
     * A panel háttérszíne
     */
    private Color panelBg = new Color(128, 179, 255);
    /**
     * A panel kerete
     */
    private Border blueBorder = BorderFactory.createLineBorder(new Color(4, 88, 213), 4);
    /**
     * A főképernyőre mutató referencia
     */
    protected MainFrame mainFrame;
    public PopupPanel(MainFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;

        setOpaque(false);
        setAlignmentY(CENTER_ALIGNMENT);
        JPanel myPanel = new JPanel();
        myPanel.setAlignmentX(CENTER_ALIGNMENT);
        myPanel.setAlignmentY(CENTER_ALIGNMENT);
        myPanel.setOpaque(false);
        myPanel.setLayout(new BorderLayout(0,0));

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new BorderLayout());
        JButton close = new MenuButton("X");
        close.setPreferredSize(new Dimension(50,50));
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mainFrame.switchToPreviousPanel();
            }
        });


        topPanel.add(close,BorderLayout.EAST);
        myPanel.add(topPanel,BorderLayout.NORTH);

        innerPanel = new JPanel();
        innerPanel.setBackground(panelBg);
        innerPanel.setLayout(new BoxLayout(innerPanel,BoxLayout.Y_AXIS));
        innerPanel.setAlignmentX(CENTER_ALIGNMENT);
        innerPanel.setBorder(blueBorder);


        myPanel.add(innerPanel,BorderLayout.CENTER);

        super.add(myPanel);
    }

    @Override
    public Component add(Component comp) {
        return innerPanel.add(comp);
    }

    @Override
    public void setPreferredSize(Dimension preferredSize) {
        innerPanel.setPreferredSize(preferredSize);
    }
}
