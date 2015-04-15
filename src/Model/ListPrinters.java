package Model;

/**
 * Created by Roland on 16/04/2015.
 */



import javax.print.PrintService;
import javax.print.PrintServiceLookup;

public class ListPrinters {

    public static void main(String[] args) {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        System.out.println("Number of printers configured: " + printServices.length);

        for (PrintService printer : printServices)
            System.out.println("Printer: " + printer.getName());
    }
}
