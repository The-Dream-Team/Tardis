package me.dreamteam.tardis;

import me.dreamteam.tardis.Utils;

public class UtilsHTML {
	
	// General
	
	static String startHTML = "<html>";
	static String endHTML = "</html>";
	
	static String boldHTML = "<b>";
	static String eboldHTML = "</b>";
	
	static String centerHTML = "<center>";
	static String ecenterHTML = "</center>";

	// Character Selection Buttons
	static String bpcsStart = "<html><img src=\"";
	static String bpcsMiddle = "\"></img><br><center>";
	static String bpcsEnd = "</center></html>";
	
	// Chracter Selection Dialog
	static String csDialog = startHTML + boldHTML + "&raquo; " + Utils.txtCS + eboldHTML + endHTML;
	
}
