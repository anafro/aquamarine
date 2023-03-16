package ru.anafro.lush.utils.release;

public class Version {
    private final int major, minor, patch;

    public Version(int major, int minor, int patch) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public int getPatch() {
        return patch;
    }

    @Override
    public String toString() {
        return "%d.%d.%d".formatted(major, minor, patch);
    }
}
