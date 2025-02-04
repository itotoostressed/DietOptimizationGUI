import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DietGUI extends JFrame {

    public DietGUI () {
        setTitle("Diet Planner");
        setLayout(new BorderLayout());
        setSize(1000, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        getContentPane().setBackground(Color.YELLOW);

        JPanel input = new JPanel();
        input.setLayout(new GridLayout(6, 2));
        input.setVisible(true);

        JLabel budgetLabel = new JLabel("Budget: ");
        JTextField budgetField = new JTextField();

        JLabel weightLabel = new JLabel("Current Body Weight: ");
        JTextField weightField = new JTextField();

        JLabel proteinLabel = new JLabel("Required Protein: ");
        JTextField proteinField = new JTextField();

        JButton calculateButton = new JButton("Calculate");
//        JLabel resultLabel = new JLabel("Optimal Diet Plan:");
//        JTextArea resultArea = new JTextArea(5, 20);
//        resultArea.setEditable(false);

        input.add(budgetLabel);
        input.add(budgetField);
        input.add(weightLabel);
        input.add(weightField);
        input.add(proteinLabel);
        input.add(proteinField);
        input.add(calculateButton);
//        input.add(resultLabel);
        input.setLocation(0,0);
        input.setVisible(true);

        add(input);
        
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}