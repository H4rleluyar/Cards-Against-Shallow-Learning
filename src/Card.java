public class Card {
    private static final int MAX_LINE_CHAR_NUM = 100;
    private String line;
    private boolean hidden = false;
    private boolean used = false;

    public Card() { line = ""; }
    public Card(String line) { this.line = line; }

    //accessor
    public String line() { return line; }
    public boolean flipped() { return hidden; }
    public boolean used() { return used; }

    //mutator
    public boolean setLine(String newLine) {
        if(newLine.length() > MAX_LINE_CHAR_NUM)
            return false;

        line = newLine;
        return true;
    }

    public void flipCard() { hidden = !hidden; }
    public void setUsed(boolean used) { this.used = used; }
}
