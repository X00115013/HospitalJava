package Model;

/**
 * Created by Thomas Murray on 13/04/2015.
 */
    import java.io.ByteArrayInputStream;
    import java.io.FileInputStream;
    import java.io.FileNotFoundException;
    import java.io.InputStream;

    import javax.print.*;
    import javax.print.attribute.AttributeSet;
    import javax.print.attribute.HashAttributeSet;
    import javax.print.attribute.HashPrintRequestAttributeSet;
    import javax.print.attribute.PrintRequestAttributeSet;
    import javax.print.attribute.standard.PrinterName;
    import javax.print.event.PrintJobAdapter;
    import javax.print.event.PrintJobEvent;
    import javax.print.event.PrintJobListener;

    public class Printing {
        private String fileIn;

        public Printing(String fileIn)throws PrintException, FileNotFoundException {
             InputStream in = new FileInputStream("files/"+fileIn+".txt");
            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            AttributeSet attributeSet = new HashAttributeSet();
            attributeSet.add(new PrinterName("HP Deskjet F4200 series", null));
            PrintService service = PrintServiceLookup.lookupDefaultPrintService();
            DocPrintJob job = service.createPrintJob();
            Doc doc = new SimpleDoc(in, flavor, null);
            PrintJobWatcher watcher = new PrintJobWatcher(job);
            job.print(doc, null);
            watcher.waitForDone();
        }

static class PrintJobWatcher {
    // true iff it is safe to close the print job's input stream
    boolean done = false;

    PrintJobWatcher(DocPrintJob job) {
        // Add a listener to the print job
        job.addPrintJobListener(new PrintJobAdapter() {
            public void printJobCanceled(PrintJobEvent pje) {
                allDone();
            }
            public void printJobCompleted(PrintJobEvent pje) {
                allDone();
            }
            public void printJobFailed(PrintJobEvent pje) {
                allDone();
            }
            public void printJobNoMoreEvents(PrintJobEvent pje) {
                allDone();
            }
            void allDone() {
                synchronized (PrintJobWatcher.this) {
                    done = true;
                    PrintJobWatcher.this.notify();
                }
            }
        });
    }

    public synchronized void waitForDone() {
        try {
            while (!done) {
                wait();
            }
        } catch (InterruptedException e) {
        }
    }
}
}



