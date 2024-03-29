package vitorgsevero.io.justgifit;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.File;

@ConfigurationProperties(prefix = "com.justgifit")
public class JustGifItProperties {

    /**
     * The location of the animated gif
     */
    private File gifLocation;

    /**
     * Whether or not to optimize web filters
     */
    private Boolean optimize;

    public Boolean getOptimize() {
        return optimize;
    }

    public void setOptimize(Boolean optimize) {
        this.optimize = optimize;
    }

    public File getGifLocation() {
        return gifLocation;
    }

    public void setGifLocation(File gifLocation) {
        this.gifLocation = gifLocation;
    }
}
