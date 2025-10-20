import java.util.Random;

public class IceCreamScroll {
    static final int width = getTerminalWidth();

  

        
        img[0][3] = '0';
        img[1][1] = '(';
        img[1][6] = ')';
        img[2][0] = '(';
        img[2][2] = '_';
        img[2][3] = '_';
        img[2][4] = '_';
        img[2][5] = '_';
        img[2][7] = ')';
        img[3][1] = '\\';
        img[3][6] = '/';
        img[4][2] = '\\';
        img[4][5] = '/';
        img[5][3] = '\\';
        img[5][4] = '/';

    

    public static int getTerminalWidth() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            return getUnixTerminalWidth();
        } else {
            return 80;
        }
    }

    private static int getUnixTerminalWidth() {
        try {
            String columns = System.getenv("COLUMNS");
            if (columns != null && !columns.isEmpty()) {
                return Integer.parseInt(columns);
            }

            ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", "stty size </dev/tty");
            pb.redirectErrorStream(true);
            Process process = pb.start();
            java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(process.getInputStream()));
            String output = reader.readLine();
            if (output != null && !output.isEmpty()) {
                return Integer.parseInt(output.trim().split(" ")[1]);
            }
        } catch (Exception ignored) {
        }
        return 80;
    }
}