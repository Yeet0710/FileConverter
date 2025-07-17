package Sound;

public enum SoundFileType {

    MP3("mp3"),
    WAV("wav"),
    OGG("ogg"),
    FLAC("flac");

    private final String extension;

    SoundFileType(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

    @Override
    public String toString() {
        return "SoundFileType{" +
                "extension='" + extension + '\'' +
                '}';
    }

}
