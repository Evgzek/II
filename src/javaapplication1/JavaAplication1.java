package javaapplication1;

public class JavaAplication1 {

    final static double T = 0.5;
    double out;
    double[] enters;
    double[] synapses;

    double[][] patters = {
            {0,0},
            {0,1},
            {1,0},
            {1,1},
    };

    double[] answers = {0,0,0,1};
    JavaAplication1(){
        enters = new double[patters[0].length];
        synapses = new double[enters.length];
        int steps = study();
        System.out.println(steps);
        for (int p = 0; p < patters.length; p++) {
            for (int i = 0; i< enters.length; i++)
                enters[i] = patters[p][i];

            countOut();
            System.out.println(out);
        }
    }

    void countOut(){
        out = 0;
        for (int i = 0; i < enters.length; i++)
            out += enters[i] * synapses[i];
        if (out > T) out = 1;
        else out = 0;
    }

    int study(){
        int steps = 0;
        for (int i = 0; i < enters.length; i++)
            synapses[i] = Math.random() * 0.2 + 0.1;

        double gErr = 0;
        do {steps++;
            gErr = 0;
            for (int p = 0; p < patters.length; p++) {
                for (int i = 0; i< enters.length; i++)
                    enters[i] = patters[p][i];

                countOut();

                double err = answers[p] - out;
                gErr += Math.abs(err);

                for (int i = 0; i < synapses.length; i++)
                    synapses [i] += 0.1 * err * enters[i];
            }
        }while (gErr!=0);
        return steps;
    }

    public static void main(String[] args) {
        new JavaAplication1();
    }
}
