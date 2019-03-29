package np.com.softwarica.highwins.hwo;

public class HwOperation {
    private int firstNumber;
    private int secondNumber;

    public int getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(int secondNumber) {
        this.secondNumber = secondNumber;
    }


    private int getRandNum() {
        return (int) ((Math.random() * ((100 - 1) + 1)) + 1);
    }

    public void shuffleNumber() {
        firstNumber = getRandNum();
        secondNumber = getRandNum();

        if (firstNumber == secondNumber) firstNumber++;
    }
}
