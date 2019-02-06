package Files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author herth
 */
public class Files {

    private static final String FILENAME_PART_1 = "C:\\Users\\herth\\OneDrive\\Έγγραφα\\";
    private static final String FILENAME_PART_2 = ".txt";
    private static String fileName = null;
    private static PrintWriter LogFile = null;

    public Files() {
    }

    public static void setFileName(String username) {
        fileName = Files.FILENAME_PART_1 + username + "_"
                + LocalDateTime.now().getDayOfMonth() + "_"
                + LocalDateTime.now().getMonthValue() + "_"
                + LocalDateTime.now().getYear() + Files.FILENAME_PART_2;
        openFile();
    }

    private static boolean openFile() {
        boolean result = false;

        try {
            File f = new File(fileName);
            if (f.exists() && !f.isDirectory()) {
                LogFile = new PrintWriter(new FileOutputStream(new File(fileName), true));
                result = true;
            } else {
                LogFile = new PrintWriter(fileName, "UTF-8");
            }
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
            return result;
        }
        return result;
    }

    public static void logToFile(String s) {
        LogFile.write(s);
        LogFile.write("\n");
    }

    public static void closeFile() {
        LogFile.close();
    }

}
