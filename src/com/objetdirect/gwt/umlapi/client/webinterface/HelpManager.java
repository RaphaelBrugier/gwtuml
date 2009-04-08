/**
 * 
 */
package com.objetdirect.gwt.umlapi.client.webinterface;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.gwt.mosaic.ui.client.MessageBox;

/**
 * @author florian
 * 
 */
public class HelpManager {

    private static LinkedHashMap<String, String> hotkeysHelp = new LinkedHashMap<String, String>();

    public static void addHotkeyHelp(final String key, final String description) {
	hotkeysHelp.put(key, description);
    }

    public static void bringHelpPopup() {
	final StringBuilder htmlContent = new StringBuilder();
	htmlContent.append("<table style='width: 100%'>");
	for (final Entry<String, String> entry : hotkeysHelp.entrySet()) {
	    htmlContent.append("<tr><td style='text-align: right'><b>["
		    + entry.getKey() + "]</b></td><td> - </td><td>"
		    + entry.getValue() + "</td></tr>");
	}
	htmlContent.append("</table>");
	MessageBox.info("Help", htmlContent.toString());
    }
}
