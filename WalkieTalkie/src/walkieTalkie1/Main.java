package walkieTalkie1;

import walkietalkie.WalkieTalkie;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            new WalkieTalkie(6000).iniciar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
