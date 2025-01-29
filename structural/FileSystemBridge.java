interface OperatingSystem {
    void openFile(String filename);
    void saveFile(String filename);
}

class Windows implements OperatingSystem {
    public void openFile(String filename) { System.out.println("Opening " + filename + " in Windows"); }
    public void saveFile(String filename) { System.out.println("Saving " + filename + " in Windows"); }
}

class Linux implements OperatingSystem {
    public void openFile(String filename) { System.out.println("Opening " + filename + " in Linux"); }
    public void saveFile(String filename) { System.out.println("Saving " + filename + " in Linux"); }
}

// Abstraction
abstract class FileType {
    protected OperatingSystem os;

    public FileType(OperatingSystem os) {
        this.os = os;
    }

    public abstract void open();
    public abstract void save();
}

// Refined Abstraction
class TextFile extends FileType {
    private String filename;

    public TextFile(String filename, OperatingSystem os) {
        super(os);
        this.filename = filename;
    }

    public void open() {
        os.openFile(filename);
    }

    public void save() {
        os.saveFile(filename);
    }
}

class PDFFile extends FileType {
    private String filename;

    public PDFFile(String filename, OperatingSystem os) {
        super(os);
        this.filename = filename;
    }

    public void open() {
        os.openFile(filename);
    }

    public void save() {
        os.saveFile(filename);
    }
}

// Usage
public class FileSystemBridge {
    public static void main(String[] args) {
        FileType textFile = new TextFile("document.txt", new Windows());
        textFile.open();
        textFile.save();

        FileType pdfFile = new PDFFile("report.pdf", new Linux());
        pdfFile.open();
        pdfFile.save();
    }
}
