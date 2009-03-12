package com.objetdirect.gwt.umlapi.client.webinterface;

import com.objetdirect.gwt.umlapi.client.gfx.GfxFont;

/**
 * This class allows to set and access configuration values 
 * @author fmounier
 *
 */
public class OptionsManager {

	private static final int RIGHT_TEXT_PADDING = 1;
	private static final int LEFT_TEXT_PADDING = 1;
	private static final int TOP_TEXT_PADDING = 1;
	private static final int BOTTOM_TEXT_PADDING = 2;
	
	private static final int RIGHT_RECT_PADDING = 4;
	private static final int LEFT_RECT_PADDING = 4;
	private static final int TOP_RECT_PADDING = 4;
	private static final int BOTTOM_RECT_PADDING = 6;
	
	
	private static GfxFont font = new GfxFont("monospace", 10, GfxFont.NORMAL, GfxFont.NORMAL, GfxFont.NORMAL);
	private static GfxFont smallCapsFont = new GfxFont("monospace", 10, GfxFont.NORMAL, GfxFont.SMALL_CAPS, GfxFont.NORMAL);
	
	
	public static int getTextRightPadding() {
		return RIGHT_TEXT_PADDING;
	}
	public static int getTextLeftPadding() {
		return LEFT_TEXT_PADDING;
	}
	public static int getTextTopPadding() {
		return TOP_TEXT_PADDING;
	}
	public static int getTextBottomPadding() {
		return BOTTOM_TEXT_PADDING;
	}
	public static int getTextXTotalPadding() {
		return RIGHT_TEXT_PADDING + LEFT_TEXT_PADDING;
	}
	public static int getTextYTotalPadding() {
		return TOP_TEXT_PADDING + BOTTOM_TEXT_PADDING;
	}
	public static int getRectangleRightPadding() {
		return RIGHT_RECT_PADDING;
	}
	public static int getRectangleLeftPadding() {
		return LEFT_RECT_PADDING;
	}
	public static int getRectangleTopPadding() {
		return TOP_RECT_PADDING;
	}
	public static int getRectangleBottomPadding() {
		return BOTTOM_RECT_PADDING;
	}	
	public static int getRectangleXTotalPadding() {
		return RIGHT_RECT_PADDING + LEFT_RECT_PADDING;
	}
	public static int getRectangleYTotalPadding() {
		return TOP_RECT_PADDING + BOTTOM_RECT_PADDING;
	}
	
	public static GfxFont getFont() {
		return font;
	}
	public static GfxFont getSmallCapsFont() {
		return smallCapsFont;
	}
}
