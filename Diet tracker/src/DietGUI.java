import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DietGUI extends JFrame {

    public DietGUI () {
        setTitle("Diet Planner");
        setLayout(new BorderLayout());
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel input = new JPanel();
        input.setLayout(new GridLayout(4, 2));
        input.setVisible(true);

        JLabel budgetLabel = new JLabel("Budget: ");
        JTextField budgetField = new JTextField();

        JLabel weightLabel = new JLabel("Current Body Weight: ");
        JTextField weightField = new JTextField();

        JButton calculateButton = new JButton("Calculate");
        JLabel resultLabel = new JLabel("Optimal Diet Plan:");
        JTextArea resultArea = new JTextArea(5, 20);
        resultArea.setEditable(false);

        input.add(budgetLabel);
        input.add(budgetField);
        input.add(weightLabel);
        input.add(weightField);
        input.add(calculateButton);
        input.add(resultLabel);
        input.setLocation(0,0);
        input.setVisible(true);

        add(input);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int budget = Integer.parseInt(budgetField.getText());
                    int weight = Integer.parseInt(weightField.getText());
                    int deficitCalories = weight*10-600;
                    for (int x = 0; x<10; x++) {
                        for (int y = 0; y<10; y++) {
                            if (x+y < 10) {
                                int b = x+y*5;
                                if (b<budget) {

                                }
                            }
                        }
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null, "Please enter a numerical value", "Enter a number", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
