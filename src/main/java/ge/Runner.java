package ge;

import ge.app.Sh2Application;

public class Runner {

    public static void main(String[] args) {
        try {
            Sh2Application.run(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
