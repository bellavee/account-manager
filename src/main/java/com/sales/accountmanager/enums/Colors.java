package com.sales.accountmanager.enums;


public enum Colors {

    RED("#ffe2dd"),
    ORANGE("#fadec9"),
    YELLOW("#fdecc8"),
    GREEN("#dbeddb"),
    BLUE("#d3e5ef"),
    PURPLE("#e8deee"),
    PINK("#f4e0e9");

    private final String colorHex;

    Colors(String colorHex) {
        this.colorHex = colorHex;
    }

    public String getColorHex() {
        return colorHex;
    }

}
