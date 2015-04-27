package Model;

/**
 * Created by David Kiernan on 22/04/2015.
 */
public class OpeningWordFile {
    private static String DIR = "Document\\Manual.docx"; // Document is the folder where the Manual is stored.
    // Manual is the file that is to be opened
    // Won't make a difference if static or not

    public OpeningWordFile(){

    }

    public static String getDIR() {
        return DIR;
    }
}
