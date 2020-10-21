package sequenties;

import java.awt.*;

public class RNA {
    protected Color color;
    public void setcolor(char a) {
        if (a == 'G' || a == 'C') {
            color = Color.RED;
        } else if (a =='A'){
            color = Color.YELLOW;
        }
        else {
            color = Color.blue;
        }
    }

    public Color getColor() {
        return color;
    }
}

