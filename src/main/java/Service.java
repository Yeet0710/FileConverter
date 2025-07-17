import Graphic.GraphicConverter;
import Graphic.GraphicFileType;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Service {

    public static void main(String[] args) throws IOException {

        System.out.println("Starting graphic file conversion...");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter image file Source: ");

        String url = scanner.nextLine();

        System.out.println("Enter file Type: ");
        System.out.println("1) JPG");
        System.out.println("2) PNG");
        System.out.println("3) BMP");
        System.out.println("4) GIF");
        System.out.println("5) TIFF");
        System.out.println("6) TGA");
        System.out.println("7) PPM");
        System.out.println("8) PGM");
        System.out.println("9) PBM");
        System.out.println("10) PDF");

        int type = Integer.parseInt(scanner.nextLine());
        GraphicFileType graphicFileType;
        switch (type) {
            case 1:
                graphicFileType = GraphicFileType.JPG;
                break;
            case 2:
                graphicFileType = GraphicFileType.PNG;
                break;
            case 3:
                graphicFileType = GraphicFileType.BMP;
                break;
            case 4:
                graphicFileType = GraphicFileType.GIF;
                break;
            case 5:
                graphicFileType = GraphicFileType.TIFF;
                break;
            case 6:
                graphicFileType = GraphicFileType.TGA;
                break;
            case 7:
                graphicFileType = GraphicFileType.PPM;
                break;
            case 8:
                graphicFileType = GraphicFileType.PGM;
                break;
            case 9:
                graphicFileType = GraphicFileType.PBM;
                break;
            case 10:
                graphicFileType = GraphicFileType.PDF;
                break;
            default:
                System.out.println("Invalid file type. Defaulting to PNG.");
                graphicFileType = GraphicFileType.PNG;
        }

        GraphicConverter graphicConverter = new GraphicConverter();
        GraphicConverter.convert(new File(url), graphicFileType);

        System.out.println("Converting to " + graphicFileType.getExtension());

    }

}
