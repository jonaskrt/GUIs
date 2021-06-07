import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Gui extends JFrame {

    public static final long serialVersionUID = 1L;

    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();

    JButton b1 = new JButton("Delete");
    JButton b2 = new JButton("A");
    JButton b3 = new JButton("B");
    JButton b4 = new JButton("C");

    public Gui() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        p1.setLayout(new GridLayout(1, 2));
        p1.add(p2);
        p2.add(p3);

        p2.setLayout(new GridLayout(3, 1));
        p2.add(b2);
        p2.add(b3);
        p2.add(b4);

        p3.add(b1);

        DoItListener doIt = new DoItListener(null);
        DeleteItListener deleteIt = new DeleteItListener(this, doIt);

        b1.addActionListener(deleteIt);
        b2.addActionListener(deleteIt);
        b3.addActionListener(deleteIt);
        b4.addActionListener(deleteIt);

        add(p1);
        pack();
    }

    public void deleteButton(String name) {
        if (b2 != null && "A".equals(name)) {
            p2.remove(b2);
            b2 = null;
            p2.invalidate();
        }
        if (b3 != null && "B".equals(name)) {
            p2.remove(b3);
            b3 = null;
            p2.invalidate();
        }
        if (b4 != null && "A".equals(name)) {
            p2.remove(b4);
            b4 = null;
            p2.invalidate();
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gui().setVisible(true);
            }
        });
    }
}

class DoItListener implements ActionListener {

    private ActionListener delegate;

    public DoItListener(ActionListener next) {
        delegate = next;
    }

    public void actionPerformed(ActionEvent e) {
        if (!("Delete".equals(e.getActionCommand()))) {
            System.out.println("doing " + e.getActionCommand());
        } else if (delegate != null) {
            delegate.actionPerformed(e);
        }
    }
}

class DeleteItListener implements ActionListener {

    private Gui gui;

    private boolean deleteNext;

    private ActionListener delegate;

    public DeleteItListener(Gui container, ActionListener next) {
        gui = container;
        delegate = next;
        deleteNext = false;
    }

    public void actionPerformed(ActionEvent e) {
        if ("Delete".equals(e.getActionCommand())) {
            deleteNext = true;
        } else if (deleteNext) {
            gui.deleteButton(e.getActionCommand());
            deleteNext = false;
        } else if (delegate != null) {
            delegate.actionPerformed(e);
        }
    }
}