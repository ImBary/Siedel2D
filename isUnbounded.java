import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class isUnbounded {
    public static boolean isStrict(String filename) throws IOException {
        // Read the number of constraints
        int m = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            m = Integer.parseInt(reader.readLine());

            // Read constraints and normalize
            double[][] constraints = new double[m][3];
            for (int i = 0; i < m; i++) {
                String[] line = reader.readLine().split(" ");
                constraints[i][0] = Double.parseDouble(line[0]);
                constraints[i][1] = Double.parseDouble(line[1]);
                constraints[i][2] = Double.parseDouble(line[2]);
            }

            // Normalize constraints to y <= cx + n
            double[][] normalizedConstraints = new double[m][2];
            for (int i = 0; i < m; i++) {
                double ai = constraints[i][0];
                double bi = constraints[i][1];
                double ci = constraints[i][2];

                if (bi != 0) {
                    double c = -ai / bi;
                    double n = ci / bi;
                    normalizedConstraints[i][0] = c;
                    normalizedConstraints[i][1] = n;
                }
            }

            // Calculate slopes
            double[] slopes = new double[m];
            for (int i = 0; i < m; i++) {
                slopes[i] = normalizedConstraints[i][0];
            }
            Arrays.sort(slopes);

            // Analyze angles
            double maxSlope = slopes[slopes.length - 1];

            // Check for unboundedness
            return maxSlope >= 0;
        }
    }
}

/*
    #dane
    m - liczba constrainsow 
    constrains - dwuwymiar tablica [ai bi ci]
    normalizedConstrains - dwuwymiar tablica ma w sobiie [c,n] bo przeksztalcamy y<= cx +n
    slopes - jedenwymiar wspolczynniki kierunkowe c dla znormalizowanych
    maxSlopes -- najwiekszy wpolczynnik kierunkowy
    
    #logika
    1) wczytujemmy m razy nierownosc [ai bi ci]
    2) normalizujemy nierownosci :
        * sprawdza czy b jest rozne od zera:
            tak) oblicza wspolczynnik kierunkowy c = -a/b i przesuniecie n = c/b 
    
    3) Wczytuje współczynniki kierunkowe (c) znormalizowanych nierówności do tablicy slopes sortujac rosnaco
    4) true jestli maxSlopes >= 0 
       

 */