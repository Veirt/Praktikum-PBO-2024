package posttest6;

import de.vandermeer.asciitable.AsciiTable;

public interface Displayable {
    void display(int num);

    void displayRow(AsciiTable at, int num);
}
