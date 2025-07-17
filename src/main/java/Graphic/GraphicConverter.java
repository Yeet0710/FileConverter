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
            System.out.println("Bild konnte nicht geladen werden!");
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
            PDDocument document = new PDDocument();
            PDPage page = new PDPage(new PDRectangle(src.getWidth(), src.getHeight()));
            document.addPage(page);

            PDImageXObject pdImage = PDImageXObject.createFromFileByContent(file, document);
            PDPageContentStream content = new PDPageContentStream(document, page);
            content.drawImage(pdImage, 0, 0, src.getWidth(), src.getHeight());
            content.close();

            // 5. PDF speichern
            document.save("output.pdf");
            document.close();

            System.out.println("Export abgeschlossen: output.pdf");
        } else {
            image = new BufferedImage(
                    src.getWidth(), src.getHeight(),
                    BufferedImage.TYPE_INT_RGB
            );
            image.getGraphics().drawImage(src, 0, 0, null);
            writeImage(file, type, image);
        }



    }

    private static void writeImage(File file, GraphicFileType type, BufferedImage image) throws IOException {
        boolean write = ImageIO.write(image, type.getExtension(), new File("Umgewandelt." + type.getExtension()));
        System.out.println("Converting " + file.getName() + " to " + type.getExtension() + " was " + (write ? "successful" : "unsuccessful") + ".");
    }

}
