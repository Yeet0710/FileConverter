package Graphic;

public enum GraphicFileType {

    JPG("jpg"),
    PNG("png"),
    BMP("bmp"),
    GIF("gif"),
    TIFF("tiff"),
    TGA("tga"),
    PPM("ppm"),
    PGM("pgm"),
    PBM("pbm"),
    PDF("pdf");

    private final String extension;

    GraphicFileType(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

    @Override
    public String toString() {
        return "GraphicFileType{" +
                "extension='" + extension + '\'' +
                '}';
    }

}
