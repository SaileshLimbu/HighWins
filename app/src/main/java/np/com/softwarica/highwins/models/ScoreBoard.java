package np.com.softwarica.highwins.models;

public class ScoreBoard {
    private int totalScore;
    private int correctAnswer;
    private int wrongAnswer;

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getWrongAnswer() {
        return wrongAnswer;
    }

    public void setWrongAnswer(int wrongAnswer) {
        this.wrongAnswer = wrongAnswer;
    }

    public void increaseTotalScore() {
        totalScore++;
    }

    public void increaseCorrectAnswer() {
        correctAnswer++;
    }

    public void increaseWrongAnswer() {
        wrongAnswer++;
    }

    public void decreaseTotalScore() {
        totalScore--;
    }

    public void resetScoreBoard() {
        totalScore = 0;
        correctAnswer = 0;
        wrongAnswer = 0;
    }
}
