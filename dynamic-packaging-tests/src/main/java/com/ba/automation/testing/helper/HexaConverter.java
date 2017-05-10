package com.ba.automation.testing.helper;

import java.awt.Color;
import java.util.StringTokenizer;

/**
 * used to convert colour code to hexa format
 * Created by n459280 on 06/03/2017.
 */
public class HexaConverter {
    private static String hex ="";
    public static String hexConvert(String color) {
        String s1 = color.substring(5);
        StringTokenizer st = new StringTokenizer(s1);
        int r = Integer.parseInt(st.nextToken(",").trim());
        int g = Integer.parseInt(st.nextToken(",").trim());
        int b = Integer.parseInt(st.nextToken(",").trim());
        Color c = new Color(r, g, b);
        hex = "#"+Integer.toHexString(c.getRGB()).substring(2);
        return hex;

    }
}
