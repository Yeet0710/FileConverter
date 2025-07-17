package Graphic;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class is responsible for converting graphic files from one format to another.
 */
public class GraphicConverter {


    public GraphicConverter() {
    }

    /**
     * Converts a given graphic file from one format to another.
     * @param file
     */
    public static void convert(File file, GraphicFileType type) throws IOException {

        BufferedImage src = ImageIO.read(file);
        if (src == null) {
            System.out.println("Image didn't load!");
            return;
        }

        BufferedImage image = null;

        if (type == GraphicFileType.PGM) {
            image = new BufferedImage(
                    src.getWidth(), src.getHeight(),
                    BufferedImage.TYPE_BYTE_GRAY
            );
            image.getGraphics().drawImage(src, 0, 0, null);
            writeImage(file, type, image);
        } else if (type == GraphicFileType.PBM) {
            image = new BufferedImage(
                    src.getWidth(), src.getHeight(),
                    BufferedImage.TYPE_BYTE_BINARY
            );
            image.getGraphics().drawImage(src, 0, 0, null);
            writeImage(file, type, image);
        } else if (type == GraphicFileType.PDF) {
            createPDF(file, src);
        } else {
            image = new BufferedImage(
                    src.getWidth(), src.getHeight(),
                    BufferedImage.TYPE_INT_RGB
            );
            image.getGraphics().drawImage(src, 0, 0, null);
            writeImage(file, type, image);
        }

    }

    private static void createPDF(File file, BufferedImage src) throws IOException {
        // Create a new PDF-Document
        PDDocument document = new PDDocument();
        // Create a new page the same size as the image
        PDPage page = new PDPage(new PDRectangle(src.getWidth(), src.getHeight()));
        document.addPage(page);

        // Create a PDImageXObject from the BufferedImage
        PDImageXObject pdImage = PDImageXObject.createFromFileByContent(file, document);
        PDPageContentStream content = new PDPageContentStream(document, page);
        content.drawImage(pdImage, 0, 0, src.getWidth(), src.getHeight());
        content.close();

        // Safe the document to a file
        document.save("new.pdf");
        document.close();

        System.out.println("Export successful: new.pdf");
    }

    private static void writeImage(File file, GraphicFileType type, BufferedImage image) throws IOException {
        boolean write = ImageIO.write(image, type.getExtension(), new File("Umgewandelt." + type.getExtension()));
        System.out.println("Converting " + file.getName() + " to " + type.getExtension() + " was " + (write ? "successful" : "unsuccessful") + ".");
    }

}
