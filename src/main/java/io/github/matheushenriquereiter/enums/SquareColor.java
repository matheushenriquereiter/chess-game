package io.github.matheushenriquereiter.enums;

public enum SquareColor {
    LIGHT(0xE8EDF9), LIGHT_SELECTED(0xB1A7FC), BLACK(0xB7C0D8), BLACK_SELECTED(0x9990EC);

    private final int hexCode;

    SquareColor(int hexCode) {
        this.hexCode = hexCode;
    }

    public int getColor() {
        return hexCode;
    }
}
