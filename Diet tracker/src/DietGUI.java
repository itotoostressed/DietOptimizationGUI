import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class DietGUI extends JFrame {
    int budget;
    int weight;
    int numberOfFoods = 5;
    String[] Collumns = {"Foods", "Total Protein", "Total Calories", "Total Cost", "Amount of servings"};
    String[] foodNames = {"Chicken", "Beef", "Lamb", "Fries", "Rice"};
    double[] proteinPerServing = {25, 27, 26, 2, 3}; // protein for chicken, beef, lamb, fries, rice in order
    double[] costPerServing = {14, 15, 16.5, 5, 6};
    double[] caloriePerServing = {250, 270, 245, 400, 250};
    double[] maxFoods = {0, 0, 0, 0, 0};
    double[] amounts;

    LinearObjectiveFunction objectiveFunction = new LinearObjectiveFunction(proteinPerServing, 0); //maximize protein
    Collection<LinearConstraint> constraints = new ArrayList<>(); //setup arrayList for constraints

    public DietGUI() {

        Object[][] information = new Object[numberOfFoods][Collumns.length]; //Setting up 2d array to be put on JTable

        // initialize GUI
        setTitle("Diet Optimizer");
        setLayout(null);
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.CYAN);

        JLabel budgetLabel = new JLabel("Current budget: ");
        budgetLabel.setFont(new Font("Serif ", Font.BOLD, 15));
        budgetLabel.setBounds(340, 0, 200, 60);

        JTextField budgetField = new JTextField();
        budgetField.setBounds(225, 50, 350, 40);

        JLabel weightLabel = new JLabel("Current Body Weight:");
        weightLabel.setBounds(320, 90, 200, 60);
        weightLabel.setFont(new Font("Serif ", Font.BOLD, 15));

        JTextField weightField = new JTextField();
        weightField.setBounds(225, 140, 350, 40);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setVisible(true);
        calculateButton.setBounds(50, 230, 300, 75);

        JButton previousResult = new JButton("last result");
        previousResult.setVisible(true);
        previousResult.setBounds(450, 230, 300, 75);

        JTable resultArea = new JTable(information, Collumns); //initializing JTable
        resultArea.setFont(new Font("Serif", Font.BOLD, 16));
        resultArea.setVisible(true);
        resultArea.setBackground(Color.CYAN);

        JScrollPane resultPane = new JScrollPane(resultArea); //initializing JScrollPane
        resultPane.setBounds(0, 360, 800, 100);
        resultPane.setVisible(true);

        JFrame historyFrame = new JFrame();
        historyFrame.setSize(800, 300);
        historyFrame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Most recent result: ");
        title.setVisible(true);
        title.setFont(new Font("Serif", Font.BOLD, 16));
        title.setSize(600, 100);

        JButton goBack = new JButton("return");
        goBack.setSize(200, 75);
        goBack.setVisible(true);
        historyFrame.add(goBack, BorderLayout.SOUTH);

        add(budgetLabel); //adding rest of components
        add(budgetField);
        add(weightLabel);
        add(weightField);
        add(calculateButton);
        add(previousResult);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    soundPlayer.playSound("mixkit-cool-interface-click-tone-2568.wav"); //plays clicking sound
                    budget = Integer.parseInt(budgetField.getText()); //parseInt the text inputted in the field to get inputted number
                    weight = Integer.parseInt(weightField.getText());
                    if (budget < 0) {
                        JOptionPane.showMessageDialog(DietGUI.this, "please get more money.");
                    }
                    constraints.clear(); //reset previous constraints
                    for (int i = 0; i < numberOfFoods; i++) { //loop to add the constraint to every food
                        maxFoods[i] = 1;
                        constraints.add(new LinearConstraint(maxFoods, Relationship.LEQ, 3));
                        maxFoods [i] = 0;
                    }
                    constraints.add(new LinearConstraint(caloriePerServing, Relationship.GEQ, weight*5));
                    constraints.add(new LinearConstraint(caloriePerServing, Relationship.LEQ, weight*8));
                    constraints.add(new LinearConstraint(costPerServing, Relationship.LEQ, budget));

                    SimplexSolver solver = new SimplexSolver();  //the solver for the linear equation
                    PointValuePair solution = solver.optimize(
                            new LinearConstraintSet(constraints),  //adds the new set of constraints
                            objectiveFunction,
                            GoalType.MAXIMIZE,
                            new NonNegativeConstraint(true));  //ensures that none of the values go below zero
                    if (solution != null) { //if there is a solution, continue
                        amounts = solution.getPoint(); //gets information from the array
                        for (int l = 0; l<numberOfFoods; l++){ //for loop to add the calculated serving amounts into the JTable
                            information[l][0] = foodNames[l];
                            information[l][1] = proteinPerServing[l] * amounts[l];
                            information[l][2] = caloriePerServing[l] * amounts[l];
                            information[l][3] = costPerServing[l] * amounts[l];
                            information[l][4] = amounts[l];
                        }
                        try{ //try catch for printing the array/results into a file
                            FileOutputStream fos = new FileOutputStream("previousResultinfo.txt");
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                            oos.reset();
                            oos.writeObject(information);
                            oos.close();
                        }catch(Exception e3){
                            System.err.println("Error in file writing/reading.");
                        }
                    }
                    add(resultPane); //adds the new results
                    repaint(); //ensures the JFrame is updated after adding resultPane
                }
                catch (NumberFormatException e2) { //If user inputs a letter, then will print out error message
                    JOptionPane.showMessageDialog(DietGUI.this, "Please enter a number");
                }
                catch (NoFeasibleSolutionException e3) {
                    JOptionPane.showMessageDialog(DietGUI.this, "No feasible solution");
                }
            }
        }); //end of actionListener
        previousResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    soundPlayer.playSound("mixkit-cool-interface-click-tone-2568.wav");
                    FileInputStream fis = new FileInputStream("previousResultinfo.txt");
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    Object[][] previousInformation = (Object[][]) ois.readObject();

                    JTable historyTable = new JTable(previousInformation, Collumns);
                    historyTable.setFont(new Font("Serif", Font.BOLD, 16));
                    historyTable.setVisible(true);

                    JScrollPane historyScroll = new JScrollPane(historyTable);
                    historyScroll.setSize(800, 60);
                    historyScroll.setVisible(true);
                    historyTable.setBackground(Color.CYAN);

                    historyFrame.add(historyScroll, BorderLayout.CENTER);
                    historyFrame.add(title, BorderLayout.NORTH);
                    historyFrame.setVisible(true);
                    setVisible(false);
                }
                catch (Exception e4) {
                }
            }
        });
        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soundPlayer.playSound("mixkit-cool-interface-click-tone-2568.wav");
                historyFrame.setVisible(false);
                setVisible(true);
            }
        });
    } //end of DietGUI constructor
} //end of DietGUI class