import java.util.Random;


public class ScrollArtZigZag{  
    static final int WIDTH = getTerminalWidth() - 1; 
    static final int HEIGHT = 20; 
    static final int num_patterns = 6; 
    static final Random rand = new Random();
    static final String[] list_symbols ={"*", "+","&","^","%"};
    
    static int[]xCols = new int[num_patterns];
    static int[] yRows = new int[num_patterns];
    static int[] dirs = new int[num_patterns];
    static String[] symbols = new String[num_patterns];

    public static void main(String[] args) throws InterruptedException{
      
        for(int i = 0; i < num_patterns; i++) {
        xCols[i] = rand.nextInt(WIDTH);
        yRows[i] = rand.nextInt(HEIGHT);
        dirs[i] = 1;
        symbols[i] = list_symbols[rand.nextInt(list_symbols.length)];
        
      }
      while(true){
        printFrame();
        movePatterns();
        Thread.sleep(60);
      }

    }

    public static void movePatterns(){ 
        for (int i = 0; i < num_patterns; i++){
            xCols[i] += dirs [i];
            yRows[i] += 1;

            if(xCols[i] <= 0){ 
                dirs[i] = 1;
            }
            if(xCols[i] >= WIDTH - 1){
                dirs[i] = -1;

            }
            if(yRows[i] >= HEIGHT) {
                yRows[i] = 0;
                symbols[i] = list_symbols[rand.nextInt(list_symbols.length)];
            }
        }
    }
public static void printFrame() {
    String[] row = new String[WIDTH];
    for(int i = 0; i < WIDTH; i++){
        row[i] = " ";
    }
        for (int i = 0; i < num_patterns; i++){
            if(xCols[i] >= 0 && xCols[i] < WIDTH){
                row[xCols[i]] = symbols[i];
            }
        }
        System.out.println(String.join("",row));
    }

    
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
            if (columns != null && !columns.isEmpty()) return Integer.parseInt(columns);
            ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", "stty size </dev/tty");
            pb.redirectErrorStream(true);
            Process process = pb.start();
            java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(process.getInputStream()));
            String output = reader.readLine();
            if (output != null && !output.isEmpty()) {
                return Integer.parseInt(output.trim().split(" ")[1]);
            }
        } catch (Exception ignored) {}
        return 80;
    }
}

