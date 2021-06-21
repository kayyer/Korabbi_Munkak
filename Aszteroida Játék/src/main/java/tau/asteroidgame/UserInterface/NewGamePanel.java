package tau.asteroidgame.UserInterface;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class NewGamePanel extends JPanel {

    /**
     * Lila keret a táblázatnak
     */
    private static Border purpleBorder = BorderFactory.createLineBorder(new Color( 	51, 0, 128),2);
    /**
     * A táblázat adatait megjelenítő modell
     */
    private TableModel tableModel = new TableModel();
    /**
     * A játékosok táblázata
     */
    private JTable playerTable;
    /**
     * A főképernyőre mutató referencia
     */
    private MainFrame mainFrame;
    /**
     * Az űrhajók iconjait tároló map
     */
    private Map<starships,ImageIcon> iconMap = new HashMap<>();
    /**
     * Játékosokat tároló lista
     */
    private List<Player> playerList = new ArrayList<>();
    public NewGamePanel(MainFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;

        iconMap.put(starships.BLUE,new ImageIcon(new ImageIcon(getClass().getResource("/starships/blue.png")).getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH)));
        iconMap.put(starships.GREY,new ImageIcon( new ImageIcon(getClass().getResource("/starships/grey.png")).getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH)));
        iconMap.put(starships.GREEN,new ImageIcon( new ImageIcon(getClass().getResource("/starships/green.png")).getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH)));
        iconMap.put(starships.ORANGE, new ImageIcon(new ImageIcon(getClass().getResource("/starships/orange.png")).getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH)));
        iconMap.put(starships.PINK, new ImageIcon(new ImageIcon(getClass().getResource("/starships/pink.png")).getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH)));

        setOpaque(false);

        JPanel popupPanel = new PopupPanel(mainFrame);
        popupPanel.setPreferredSize(new Dimension(700,500));

        JPanel titlePanel = new JPanel();
        GroupLayout groupLayout = new GroupLayout(titlePanel);
        titlePanel.setLayout(groupLayout);
        titlePanel.setAlignmentX(CENTER_ALIGNMENT);
        titlePanel.setOpaque(false);
        JLabel label = new JLabel("Player Selection",SwingConstants.CENTER);
        label.setFont(MainFrame.starGuardianFont.deriveFont(72.f));
        label.setForeground(Color.BLACK);
        titlePanel.add(label);

        JPanel startPanel = new JPanel();
        startPanel.setOpaque(false);
        startPanel.setAlignmentX(CENTER_ALIGNMENT);

        JButton startButton = new MenuButton(" Start Game ");

        startButton.setPreferredSize(new Dimension(245, 50));
        startButton.setActionCommand("startGame");
        startButton.addActionListener(InputController.getInstance());
        startPanel.add(startButton);
        titlePanel.add(startPanel);

        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup().addComponent(label))
                .addGroup(groupLayout.createParallelGroup().addComponent(startPanel))
        );
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(label).addComponent(startPanel));


        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BorderLayout());

        JLabel playersLabel = new JLabel("Players:");
        playersLabel.setForeground(Color.BLACK);
        playersLabel.setFont(MainFrame.starGuardianFont.deriveFont(32.f));
        centerPanel.add(playersLabel,BorderLayout.NORTH);

        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BorderLayout());
        playerPanel.setBackground(new Color(85,153,255));
        playerPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));

        playerTable = new JTable() {
            public Component prepareRenderer( TableCellRenderer renderer, int row, int column) {
                JComponent jc = (JComponent)super.prepareRenderer(renderer, row, column);
                jc.setBorder(purpleBorder);
                return jc;
            }
        };
        playerTable.setModel(tableModel);
        playerTable.getColumn("-").setCellRenderer(new JTableButtonRenderer());
        //playerTable.getColumn("Starship").setCellRenderer(new JTableComboBoxRenderer());
        JComboBox comboBox = new JComboBox(shipNames);
        comboBox.setRenderer(new StarshipRenderer());
        comboBox.setFont(MainFrame.starGuardianFont.deriveFont(26.f));
        playerTable.getColumn("Starship").setCellEditor(new CustomComboBoxEditor(comboBox));
        playerTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = playerTable.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
                int row    = e.getY()/playerTable.getRowHeight(); //get the row of the button

                /*Checking the row or column is valid or not*/
                if (row < playerTable.getRowCount() && row >= 0 && column < playerTable.getColumnCount() && column >= 0) {
                    Object value = playerTable.getValueAt(row, column);
                    if (value instanceof JButton) {
                        /*perform a click event*/
                        ((JButton)value).doClick();
                    }
                }
            }
        });
        playerTable.setRowHeight(35);
        playerTable.setFont(MainFrame.starGuardianFont.deriveFont(26.f));
        playerTable.getTableHeader().setFont(MainFrame.starGuardianFont.deriveFont(27.f));
        playerTable.getTableHeader().setDefaultRenderer(new PlayerTableRenderer());
        playerTable.setDefaultRenderer(Object.class,new PlayerTableRenderer());
        playerTable.setShowGrid(true);
        playerTable.setGridColor(new Color( 	51, 0, 128));
        playerTable.setBackground(bg);
        playerTable.setOpaque(true);



        JScrollPane scrollPane = new JScrollPane(playerTable);
        scrollPane.setOpaque(true);
        scrollPane.getViewport().setBackground(bg);
        scrollPane.setBorder( BorderFactory.createLineBorder(new Color( 	51, 0, 128),4));
        scrollPane.setFont(MainFrame.starGuardianFont.deriveFont(27.f));
        playerPanel.add(scrollPane,BorderLayout.CENTER);


        JPanel addPlayerPanel = new JPanel();
        addPlayerPanel.setAlignmentX(CENTER_ALIGNMENT);
        addPlayerPanel.setOpaque(false);

        JButton addPlayerButton = new MenuButton("Add Player");
        addPlayerPanel.add(addPlayerButton);
        addPlayerButton.setPreferredSize(new Dimension(245, 50));
        addPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(playerList.size() >= 5){
                    mainFrame.displayMessage("Too many players!");
                    return;
                }
                tableModel.addPlayer();
                playerTable.updateUI();
            }
        });

        centerPanel.add(playerPanel,BorderLayout.CENTER);
        playerPanel.add(addPlayerPanel,BorderLayout.SOUTH);
        popupPanel.add(titlePanel);
        popupPanel.add(centerPanel);

        add(popupPanel,BorderLayout.CENTER);
    }


    /**
     * Alap helyzetbe állítja a panelt
     */
    public void reset() {
        tableModel.reset();
    }

    /**
     * Visszaadja a játékosok listáját
     * @return A játékosok listája
     */
    public List<Player> getPlayerList() {
        return playerList;
    }

    /**
     * Az űrhajók színei
     */
    public enum starships {
        BLUE,
        GREEN,
        GREY,
        ORANGE,
        PINK;
    }

    /**
     * Az űrhajók színei String-ként
     */
    static String[] shipNames = { "Blue", "Green", "Grey", "Orange", "Pink" };
    /**
     * A táblázat mezőinek egyik háttere
     */
    private static Color bg = new Color( 153, 85, 255);
    /**
     * A táblázat mezőinek másik háttere
     */
    private static Color alternate_bg = new Color(179, 128, 255);

    /**
     * A játékosok adatait tároló osztály
     */
    public class Player {
        /**
         * A játékos űrhajójának színe
         */
        private starships starship;
        /**
         * A játékos neve
         */
        private String name;

        /**
         * A játékos egyetlen konstruktora
         * @param starship Az űrhajója színe
         * @param name A játékos neve
         */
        public Player(starships starship, String name) {
            this.starship = starship;
            this.name = name;
        }

        /**
         * Lekérdezi a játékos űrhajójának színét
         * @return Az űrhajó színe
         */
        public starships getStarship() {
            return starship;
        }

        /**
         * Lekérdezi a játékos nevét
         * @return a játékos neve
         */
        public String getName() {
            return name;
        }

        /**
         * Beállítja a játékos űrhajójának színét
         * @param starship Az űrhajó új színe
         */
        public void setStarship(starships starship) {
            this.starship = starship;
        }

        /**
         * Beállítja a játékos nevét
         * @param name A játékos új neve
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * Visszaadja a játékos űrhajójának ikonját
         * @return
         */
        public ImageIcon getIcon(){
            return iconMap.get(starship);
        }
    }

    /**
     * A Játékosok táblázatának modellje
     */
    private class TableModel extends AbstractTableModel {
        /**
         * A játékosok száma
         */
        private int playerCount = 0;




        @Override
        public int getRowCount() {
            return playerList.size();
        }

        @Override
        public int getColumnCount() {
            return 3;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex){
                case 0:
                    return playerList.get(rowIndex).getName();
                case 1:
                    return iconMap.get(playerList.get(rowIndex).getStarship());
                default:
                    JButton removeButton = new JButton("Remove");
                    removeButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            playerList.remove(rowIndex);
                            updateUI();
                        }
                    });
                    removeButton.setFont(MainFrame.starGuardianFont.deriveFont(26.f));
                    if(rowIndex % 2 == 1)
                        removeButton.setBackground(bg);
                    else
                        removeButton.setBackground(alternate_bg);
                    removeButton.setBorder(purpleBorder);
                    return removeButton;
            }
        }

        @Override
        public String getColumnName(int oszlop) {
            switch (oszlop) {
                case 0:
                    return "Player name";
                case 1:
                    return "Starship";
                default:
                    return "-";
            }
        }

        @Override
        public Class<?> getColumnClass(int oszlop) {
            switch (oszlop) {
                case 0:
                    return String.class;
                case 1:
                    return ImageIcon.class;
                case 2:
                default:
                    return JButton.class;
            }
        }

        /**
         * Hozzáad egy új játékos, ha van még hely.
         */
        public void addPlayer() {
            if(playerList.size() < 5)
            playerList.add(new Player(starships.BLUE,"Player" + (++playerCount)));
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex)
        {
            switch (columnIndex) {
                case 0:
                    playerList.get(rowIndex).setName((String) aValue);
                    break;
                default:
                    switch ((String) aValue) {
                        case "Blue":
                            playerList.get(rowIndex).setStarship(starships.BLUE);
                            break;
                        case "Grey":
                            playerList.get(rowIndex).setStarship(starships.GREY);
                            break;
                        case "Green":
                            playerList.get(rowIndex).setStarship(starships.GREEN);
                            break;
                        case "Orange":
                            playerList.get(rowIndex).setStarship(starships.ORANGE);
                            break;
                        case "Pink":
                            playerList.get(rowIndex).setStarship(starships.PINK);
                            break;
                    }

                    break;
            }
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex == 0 || columnIndex == 1;
        }

        /**
         * Alaphelyzetbe állítja a modellt
         */
        public void reset() {
            playerList.clear();
        }
    }

    /**
     * A Táblázatban lévő gombok megjelenítője
     */
    private static class JTableButtonRenderer implements TableCellRenderer {
        @Override public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JButton button = (JButton)value;
            return button;
        }
    }

    /**
     * Az űrhajó színének beállítására használt tábla cella szerkesztő
     */
    public class CustomComboBoxEditor extends DefaultCellEditor {

        // Declare a model that is used for adding the elements to the `Combo box`
        private DefaultComboBoxModel model;

        public CustomComboBoxEditor(JComboBox comboBox) {
            super(comboBox);
            this.model = (DefaultComboBoxModel)((JComboBox)getComponent()).getModel();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {


            //finally return the component.
            return super.getTableCellEditorComponent(table, value, isSelected, row, column);
        }

    }

    /**
     * Az űrhajók ikonjainak megjelenítő renderer
     */
    public class StarshipRenderer extends DefaultListCellRenderer {


        public StarshipRenderer() {

        }

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            String starshipColour = (String) value;
            this.setText(starshipColour);
            ImageIcon icon = null;
            switch (starshipColour) {
                case "Blue":
                    icon = iconMap.get(starships.BLUE);
                    break;
                case "Grey":
                    icon = iconMap.get(starships.GREY);
                    break;
                case "Green":
                    icon = iconMap.get(starships.GREEN);
                    break;
                case "Orange":
                    icon = iconMap.get(starships.ORANGE);
                    break;
                case "Pink":
                    icon = iconMap.get(starships.PINK);
                    break;
            }
            this.setIcon(icon);
            return this;
        }
    }

    /**
     * A játékosok celláinak megjelenítésére használt renderer.
     * Emiatt lesz alternáló hátterőek az egyes mezők
     */
    static class PlayerTableRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JComponent c = (JComponent) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if(row % 2 == 1)
                c.setBackground( bg);
            else
                c.setBackground( alternate_bg);
            c.setBorder(purpleBorder);
            return c;
        }
    }
    @Override
    public String getName() {
        return "New Game Panel";
    }
}
