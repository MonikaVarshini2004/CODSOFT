import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApplication extends JFrame implements ActionListener {
    private static final int TIME_LIMIT = 10; // Time limit per question in seconds
    private static final Question[] QUESTIONS = {
        new Question("What is the capital of France?",
                new String[]{"1. Berlin", "2. Madrid", "3. Paris", "4. Rome"},
                3),
        new Question("Which planet is known as the Red Planet?",
                new String[]{"1. Earth", "2. Mars", "3. Jupiter", "4. Saturn"},
                2),
        new Question("What is the largest mammal?",
                new String[]{"1. Elephant", "2. Blue Whale", "3. Giraffe", "4. Hippopotamus"},
                2)
    };

    private int currentQuestionIndex = 0;
    private int score = 0;
    private int timeLeft = TIME_LIMIT;
    private Timer timer;

    private JLabel questionLabel;
    private JRadioButton[] options;
    private JButton submitButton;
    private JLabel timerLabel;
    private ButtonGroup optionsGroup;

    public QuizApplication() {
        setTitle("Quiz Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        questionLabel = new JLabel();
        timerLabel = new JLabel("Time left: " + TIME_LIMIT + " seconds");

        options = new JRadioButton[4];
        optionsGroup = new ButtonGroup();
        for (int i = 0; i < options.length; i++) {
            options[i] = new JRadioButton();
            optionsGroup.add(options[i]);
        }

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(questionLabel);
        for (JRadioButton option : options) {
            panel.add(option);
        }
        panel.add(timerLabel);
        panel.add(submitButton);

        add(panel);

        loadQuestion();
        startTimer();

        setVisible(true);
    }

    private void loadQuestion() {
        if (currentQuestionIndex < QUESTIONS.length) {
            Question question = QUESTIONS[currentQuestionIndex];
            questionLabel.setText(question.getQuestion());
            String[] optionsText = question.getOptions();
            for (int i = 0; i < options.length; i++) {
                options[i].setText(optionsText[i]);
            }
            optionsGroup.clearSelection();
        } else {
            showResults();
        }
    }

    private void startTimer() {
        timeLeft = TIME_LIMIT;
        timerLabel.setText("Time left: " + timeLeft + " seconds");

        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeLeft--;
                timerLabel.setText("Time left: " + timeLeft + " seconds");
                if (timeLeft <= 0) {
                    timer.cancel();
                    handleTimeout();
                }
            }
        }, 1000, 1000);
    }

    private void handleTimeout() {
        JOptionPane.showMessageDialog(this, "Time's up! Moving to the next question.");
        currentQuestionIndex++;
        loadQuestion();
        startTimer();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            for (int i = 0; i < options.length; i++) {
                if (options[i].isSelected()) {
                    if (i + 1 == QUESTIONS[currentQuestionIndex].getCorrectAnswer()) {
                        score++;
                    }
                    break;
                }
            }
            currentQuestionIndex++;
            loadQuestion();
            startTimer();
        }
    }

    private void showResults() {
        if (timer != null) {
            timer.cancel();
        }
        JOptionPane.showMessageDialog(this, "Quiz over! Your score: " + score + "/" + QUESTIONS.length);
        System.exit(0);
    }

    public static void main(String[] args) {
        new QuizApplication();
    }
}
