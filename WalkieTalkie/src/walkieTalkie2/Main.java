package walkieTalkie2;

import walkietalkie.WalkieTalkie;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            new WalkieTalkie(6001).iniciar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
