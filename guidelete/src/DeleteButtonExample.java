import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DeleteButtonExample extends JFrame
{
    private boolean deleteNow = false;
    private JButton deleteButton;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JButton[] buttons = new JButton[5];

    private ActionListener deleteAction = new ActionListener()
    {
        public void actionPerformed(ActionEvent ae)
        {
            JButton button = (JButton) ae.getSource();
            if (deleteNow)
            {
                leftPanel.remove(button);
                leftPanel.revalidate();
                leftPanel.repaint();
                deleteNow = false;
            }
            else
            {
                // Do your normal Event Handling here.
                System.out.println("My COMMAND IS : " + button.getActionCommand());
            }
        }
    };

    private void createAndDisplayGUI()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setLayout(new GridLayout(0, 2));

        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(0, 2));
        leftPanel.setBackground(Color.WHITE);
        for (int i = 0; i < 5; i++)
        {
            buttons[i] = new JButton("" + i);
            buttons[i].addActionListener(deleteAction);
            buttons[i].setActionCommand("" + i);
            leftPanel.add(buttons[i]);
        }

        rightPanel = new JPanel();
        rightPanel.setBackground(Color.BLUE);

        JButton deleteButton = new JButton("DELETE");
        deleteButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                JOptionPane.showMessageDialog(null, "Delete any Button from the Left Panel by clicking it."
                        , "INFO : ", JOptionPane.INFORMATION_MESSAGE);
                deleteNow = true;
            }
        });
        rightPanel.add(deleteButton);

        add(leftPanel);
        add(rightPanel);
        pack();
        setVisible(true);
    }

    public static void main(String... args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new DeleteButtonExample().createAndDisplayGUI();
            }
        });
    }
}