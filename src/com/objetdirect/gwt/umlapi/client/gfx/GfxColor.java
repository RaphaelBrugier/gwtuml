package com.objetdirect.gwt.umlapi.client.gfx;

/**
 * @author Florian Mounier (mounier-dot-florian.at.gmail'dot'com)
 */
public class GfxColor {
    /**
     * The aqua color in RGB
     */
    static public final GfxColor AQUA = new GfxColor(0, 255, 255, 255);
    /**
     * The balck color in RGB
     */
    static public final GfxColor BLACK = new GfxColor(0, 0, 0, 255);
    /**
     * The blue color in RGB
     */
    static public final GfxColor BLUE = new GfxColor(0, 0, 255, 255);
    /**
     * The fuchsia color in RGB
     */
    static public final GfxColor FUCHSIA = new GfxColor(255, 0, 255, 255);
    /**
     * The gray color in RGB
     */
    static public final GfxColor GRAY = new GfxColor(128, 128, 128, 255);
    /**
     * The green color in RGB
     */
    static public final GfxColor GREEN = new GfxColor(0, 128, 0, 255);
    /**
     * The lime color in RGB
     */
    static public final GfxColor LIME = new GfxColor(0, 255, 0, 255);
    /**
     * The maroon color in RGB
     */
    static public final GfxColor MAROON = new GfxColor(128, 0, 0, 255);
    /**
     * The navy color in RGB
     */
    static public final GfxColor NAVY = new GfxColor(0, 0, 128, 255);
    /**
     * The olive color in RGB
     */
    static public final GfxColor OLIVE = new GfxColor(128, 128, 0, 255);
    /**
     * The purpble color in RGB
     */
    static public final GfxColor PURPLE = new GfxColor(128, 0, 128, 255);
    /**
     * The red color in RGB
     */
    static public final GfxColor RED = new GfxColor(255, 0, 0, 255);
    /**
     * The silver color inRGB
     */
    static public final GfxColor SILVER = new GfxColor(192, 192, 192, 255);
    /**
     * The teal color in RGB
     */
    static public final GfxColor TEAL = new GfxColor(0, 128, 128, 255);
    /**
     * The white color in RGB
     */
    static public final GfxColor WHITE = new GfxColor(255, 255, 255, 255);
    /**
     * The yellow color in RGB
     */
    static public final GfxColor YELLOW = new GfxColor(255, 255, 0, 255);
    int a;
    int b;
    int g;
    int r;

    /**
     * Constructor of a color
     *
     * @param r Red value (0-255)
     * @param g Green value (0-255)
     * @param b Blue value (0-255)
     */
    public GfxColor(final int r, final int g, final int b) {
	this(r, g, b, 255);
    }

    /**
     * Constructor of 
     *
     * @param r Red value (0-255)
     * @param g Green value (0-255)
     * @param b Blue value (0-255)
     * @param a Alpha (transparency) value (0-255)
     */
    public GfxColor(final int r, final int g, final int b, final int a) {
	super();
	this.r = r;
	this.g = g;
	this.b = b;
	this.a = a;
    }

    /**
     * Getter for the Alpha value 
     * 
     * @return The Alpha value 
     */
    public int getAlpha() {
	return this.a;
    }

    /**
     * Getter for the Blue value 
     * 
     * @return The Blue value 
     */
    public int getBlue() {
	return this.b;
    }
    
    /**
     * Getter for the Green value 
     * 
     * @return The Green value 
     */
    public int getGreen() {
	return this.g;
    }
    
    /**
     * Getter for the Red value 
     * 
     * @return The Red value 
     */
    public int getRed() {
	return this.r;
    }

    /**
     * Setter for the Alpha value
     * 
     * @param a The Alpha value
     */
    public void setAlpha(final int a) {
	this.a = a;
    }

    /**
     * Setter for the Blue value
     * 
     * @param b The Blue value
     */
    public void setBlue(final int b) {
	this.b = b;
    }

    /**
     * Setter for the Green value
     * 
     * @param g The Green value
     */
    public void setGreen(final int g) {
	this.g = g;
    }

    /**
     * Setter for the Red value
     * 
     * @param r The Red value
     */
    public void setRed(final int r) {
	this.r = r;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "#" + Integer.toHexString(this.r) + Integer.toHexString(this.g)
		+ Integer.toHexString(this.b);
    }
}
