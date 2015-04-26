package Model;

/**
 * Created by Thomas Murray on 16/04/2015.
 *
 * This class is used to discover what printers are available on the current system
 *
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
