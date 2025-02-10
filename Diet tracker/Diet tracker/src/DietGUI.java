import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class DietGUI extends JFrame {
    int budget;
    int weight;
    int numberOfFoods = 5;
    String[] Collumns = {"Foods", "Protein/Serving", "Calories/Serving", "Cost/Serving", "Amount of servings"};
    String[] foodNames = {"Chicken", "Beef", "Lamb", "Fries", "Rice"};
    double[] proteinPerServing = {25, 29, 26, 2, 1}; // protein for chicken, beef, lamb, fries, rice in order
    double[] costPerServing = {7, 5, 6, 3, 2};
    double[] caloriePerServing = {250, 294, 230, 500, 150};

    LinearObjectiveFunction objectiveFunction = new LinearObjectiveFunction(proteinPerServing, 0); //maximize protein
    Collection<LinearConstraint> constraints = new ArrayList<>();

    public DietGUI() {
        double[] nonNegative = {0, 0, 0, 0, 0};
        for (int i = 0; i < numberOfFoods; i++) {
            nonNegative[i] = 1; //sets non negative constraint for the current food
            constraints.add(new LinearConstraint(nonNegative, Relationship.GEQ, 0));
            nonNegative [i] = 0; //resets the constraints back to all zeros
        }
        setTitle("Diet Optimizer");
        setLayout(null);
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.CYAN);

        JLabel budgetLabel = new JLabel("Current budget: ");
        budgetLabel.setBounds(100, 30, 100, 60);
        JTextField budgetField = new JTextField();
        budgetField.setBounds(400, 40, 350, 40);

        JLabel weightLabel = new JLabel("Current Body Weight:");
        weightLabel.setBounds(100, 130, 200, 60);
        JTextField weightField = new JTextField();
        weightField.setBounds(400, 140, 350, 40);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setVisible(true);
        calculateButton.setBounds(250, 230, 300, 75);

        Object[][] information = new Object[numberOfFoods][Collumns.length]; //Setting up 2d array to be put on JTable
        for (int l = 0; l<numberOfFoods; l++){ //for loop to add the data of all the arrays excluding the amount of servings
            information[l][0] = foodNames[l];
            information[l][1] = proteinPerServing[l];
            information[l][2] = caloriePerServing[l];
            information[l][3] = costPerServing[l];
        }
        JTable resultArea = new JTable(information, Collumns); //initializing JTable
        JScrollPane resultPane = new JScrollPane(resultArea); //initializing JScrollPane
        resultPane.setBounds(0, 360, 800, 100);
        resultArea.setFont(new Font("Serif", Font.BOLD, 16));
        resultArea.setVisible(true);
        resultPane.setVisible(true);

        add(budgetLabel); //adding rest of components
        add(budgetField);
        add(weightLabel);
        add(weightField);
        add(calculateButton);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    budget = Integer.parseInt(budgetField.getText()); //parseInt the text inputted in the field to get inputted number
                    weight = Integer.parseInt(weightField.getText());
                    if (budget < 0) {
                        JOptionPane.showMessageDialog(DietGUI.this, "please get more money.");
                    }
                    constraints.add(new LinearConstraint(caloriePerServing, Relationship.LEQ, weight*10)); //adding constraints for calories
//                    constraints.add(new LinearConstraint(caloriePerServing, Relationship.GEQ, weight*7)); //adding constraints for calories
                    constraints.add(new LinearConstraint(costPerServing, Relationship.LEQ, budget)); //adding constraints for cost. GEQ = greater than or equal to

                    SimplexSolver solver = new SimplexSolver(); //the solver for the linear equation
                    PointValuePair solution = solver.optimize(
                            new LinearConstraintSet(constraints), //adds the new set of constraints
                            objectiveFunction,
                            GoalType.MAXIMIZE,
                            new NonNegativeConstraint(true)); //ensures that none of the values go below zero
                    if (solution != null) {
                        double[] amounts = solution.getPoint(); //gets information from the array
                        for (int l = 0; l<numberOfFoods; l++){ //for loop to add the calculated serving amounts into the JTable
                            information[l][4] = amounts[l];
                        }
                    }
                    System.out.println(solution);
                    add(resultPane);
                    repaint(); //ensures the JFrame is updated after adding resultPane

                }
                catch (NumberFormatException e2) { //If user inputs a letter, then will print out error message
                    JOptionPane.showMessageDialog(DietGUI.this, "Please enter a number");
                }
                catch (NoFeasibleSolutionException e3) {
                    JOptionPane.showMessageDialog(DietGUI.this, "No feasible solution");
                }
            }
        });
    }
}



/*
- Show checklist of options in food
- Show list of statistics for every food
 */
