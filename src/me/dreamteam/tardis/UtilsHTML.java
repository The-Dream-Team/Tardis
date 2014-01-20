package me.dreamteam.tardis;

public class UtilsHTML {

    // General

    static String startHTML = "<html>";
    static String endHTML = "</html>";

    static String boldHTML = "<b>";
    static String eboldHTML = "</b>";

    static String centerHTML = "<center>";
    static String ecenterHTML = "</center>";

    static String imageHTML = "<img src=\"";
    static String eimageHTML = "\"></img>";

    static String breakHTML = "<br>";

    // Character Selection Buttons
    static String bpcsStart = startHTML + imageHTML;
    static String bpcsMiddle = eimageHTML + breakHTML + centerHTML;
    static String bpcsEnd = ecenterHTML + endHTML;

    // Character Selection Dialog
    static String csDialog = startHTML + boldHTML + "&raquo; " + Utils.txtCS + eboldHTML + endHTML;

}