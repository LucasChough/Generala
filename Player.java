public class Player {
    // privae variables
    private int one;
    private int two;
    private int three;
    private int four;
    private int five;
    private int six;
    private int straight;
    private int full;
    private int poker;
    private int generala;
    private int doubleGenerala;

    public Player() { // constructor
        one = 0;
        two = 0;
        three = 0;
        four = 0;
        five = 0;
        six = 0;
        straight = 0;
        full = 0;
        poker = 0;
        generala = 0;
        doubleGenerala = 0;
    } // end constructor

    public void setOne(int score) {
        one = score;
    }

    public int getOne() {
        return one;
    }

    public void setTwo(int score) {
        two = score;
    }

    public int getTwo() {
        return two;
    }

    public void setThree(int score) {
        three = score;
    }

    public int getThree() {
        return three;
    }

    public void setFour(int score) {
        four = score;
    }

    public int getFour() {
        return four;
    }

    public void setFive(int score) {
        five = score;
    }

    public int getFive() {
        return five;
    }

    public void setSix(int score) {
        six = score;
    }

    public int getSix() {
        return six;
    }

    public void setStraight(int score) {
        straight = score;
    }

    public int getStraight() {
        return straight;
    }

    public void setFull(int score) {
        full = score;
    }

    public int getFull() {
        return full;
    }

    public void setPoker(int score) {
        poker = score;
    }

    public int getPoker() {
        return poker;
    }

    public void setGenerala(int score) {
        generala = score;
    }

    public int getGenerala() {
        return generala;
    }

    public void setDoubleGenerala(int score) {
        doubleGenerala = score;
    }

    public int getDoubleGenerala() {
        return doubleGenerala;
    }

    public int getTotalScore() {
        int total = 0;
        if (this.getDoubleGenerala() > 0) {
            total += this.getDoubleGenerala();
        }
        if (this.getGenerala() > 0) {
            total += this.getGenerala();
        }
        if (this.getPoker() > 0) {
            total += this.getPoker();
        }
        if (this.getFull() > 0) {
            total += this.getFull();
        }
        if (this.getStraight() > 0) {
            total += this.getStraight();
        }
        if (this.getSix() > 0) {
            total += this.getSix();
        }
        if (this.getFive() > 0) {
            total += this.getFive();
        }
        if (this.getFour() > 0) {
            total += this.getFour();
        }
        if (this.getThree() > 0) {
            total += this.getThree();
        }
        if (this.getTwo() > 0) {
            total += this.getTwo();
        }
        if (this.getOne() > 0) {
            total += this.getOne();
        } // end if's
        return total;
    }

    public String toString() {
        return ("One: " + one + "\n" +
                "Two: " + two + "\n" +
                "Three: " + three + "\n" +
                "Four: " + four + "\n" +
                "Five: " + five + "\n" +
                "Six: " + six + "\n" +
                "Straight: " + straight + "\n" +
                "Full House: " + full + "\n" +
                "Poker: " + poker + "\n" +
                "Generala: " + generala + "\n" +
                "Double Generala: " + doubleGenerala + "\n");
    }

} // end player
