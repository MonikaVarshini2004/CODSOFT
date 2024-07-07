public class Question {
    private String qus;
    private String[] opt;
    private int crtAns;

    public Question(String question, String[] options, int correctAnswer) {
        this.qus = question;
        this.opt = options;
        this.crtAns = correctAnswer;
    }

    public String getQuestion() {
        return qus;
    }

    public String[] getOptions() {
        return opt;
    }

    public int getCorrectAnswer() {
        return crtAns;
    }
}
