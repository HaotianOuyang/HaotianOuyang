import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FramesAndPanels extends JFrame implements ActionListener, Runnable {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;
    private static final int PnlNum = 5;
    private JPanel panels[];
    private JMenu menu;
    private JMenuBar Mb;
    private JLabel MazeTitle;//include name, date, time, last edited time.
    private JButton generateMaze;
    private JMenuItem _new, generateNewMaze, save, save_as, Export;
    private JToggleButton solution;
    public FramesAndPanels(String title) throws HeadlessException {
        super(title);
    }

    private void createGUI(){
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        panels = new JPanel[PnlNum];

        Color colors[] = {Color.LIGHT_GRAY,Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.WHITE};
        String layoutLocaitons[] = {BorderLayout.NORTH, BorderLayout.SOUTH,
                BorderLayout.EAST,BorderLayout.WEST,BorderLayout.CENTER};
        for(int i = 0; i < PnlNum; i++){
            panels[i] = createPanel(colors[i]);
            getContentPane().add(panels[i], layoutLocaitons[i]);
        }

        generateMaze = createButton("Generate");
        solution = createToggle("Solution");
        Menus();
        LayoutButtons();
        LayoutLabels();
        repaint();
        setVisible(true);
    }
    private void Menus(){
        Mb = createMenuBar();
        setJMenuBar(Mb);
        menu = createMenu("File");
        Mb.add(menu);
        MazeTitle = createLabel("TemporaryTitle");

        _new = createMenuItem("New");
        generateNewMaze = createMenuItem("Generate a new Maze");
        save = createMenuItem("Save");
        save_as = createMenuItem("Save as");
        Export = createMenuItem("Export");
        menu.add(_new);
        menu.add(generateNewMaze);
        menu.add(save);
        menu.add(save_as);
        menu.add(Export);
    }
    private JPanel createPanel(Color c){
        JPanel panel = new JPanel();
        panel.setBackground(c);
        return panel;
    }

    private JMenuBar createMenuBar(){
        JMenuBar Mb = new JMenuBar();
        return Mb;
    }

    private JMenu createMenu(String str) {
        JMenu menu = new JMenu();
        menu.setText(str);
        return menu;

    }

    private JMenuItem createMenuItem(String str){
        JMenuItem menuItem = new JMenuItem();
        menuItem.setText(str);
        return menuItem;
    }
    private JLabel createLabel(String str){
        JLabel Label = new JLabel();
        Label.setText("Maze Name:" + str);
        return Label;
    }

    private JButton createButton(String str){
        JButton btn = new JButton();
        btn.setText(str);
        return btn;
    }

    private JToggleButton createToggle(String str){
        JToggleButton toggleButton = new JToggleButton();
        toggleButton.setText(str);
        return toggleButton;
    }
    private void LayoutLabels(){
        GridBagLayout layout = new GridBagLayout();
        JPanel TopPanels = panels[0];
        TopPanels.setLayout(layout);
        //add components to grid
        GridBagConstraints constraints = new GridBagConstraints();


        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.weightx = 100;
        constraints.weighty = 100;
        addToPanel(TopPanels, MazeTitle,constraints, 0,0,3,0);

    }
    private void LayoutButtons(){

        JPanel LeftPanels = panels[3];
        LeftPanels.setLayout(new BoxLayout(LeftPanels,BoxLayout.Y_AXIS));
        /*LeftPanels.setLayout(layout);
        //add components to grid
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.weightx = 100;
        constraints.weighty = 100;

        addToPanel(LeftPanels, generateMaze, constraints,0,0,2,2);
        addToPanel(LeftPanels, solution,constraints,0,2,2,2);*/

        LeftPanels.add(generateMaze);
        LeftPanels.add(solution);
    }
    private void addToPanel(JPanel jp,Component c) {
        jp.add(c);
    }

    private void addToPanel(JPanel jp,Component c, GridBagConstraints
            constraints,int x, int y, int w, int h) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = w;
        constraints.gridheight = h;
        jp.add(c, constraints);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void run() {
        createGUI();
    }
    public static void main(String[] args){
        SwingUtilities.invokeLater(new FramesAndPanels("MazeEditor"));
    }
}
