package com.objetdirect.gwt.umlapi.client.webinterface;

import com.objetdirect.gwt.umlapi.client.gfx.GfxFont;

/**
 * This class allows to set and access configuration values
 * 
 * @author fmounier
 */
public class OptionsManager {

    public enum QualityLevel {
	VERY_HIGH("Very High", "Slow", 40),
	HIGH("High", "for good pc and browser", 30),	
	NORMAL("Normal", "Recommended for real browser", 20),
	LOW("Low", "for very old pc and IE users", 10);

	public static boolean compare(final QualityLevel q1,
		final QualityLevel q2) {
	    return q1.index >= q2.index;
	}

	public static QualityLevel getQualityFromName(final String qualityName) {
	    for (final QualityLevel qlvl : QualityLevel.values()) {
		if (qlvl.toString().equalsIgnoreCase(qualityName)) {
		    return qlvl;
		}
	    }
	    return QualityLevel.HIGH;
	}

	private final String description;

	private final int index;

	private final String name;

	private QualityLevel(final String name, final String description,
		final int index) {
	    this.name = name;
	    this.description = description;
	    this.index = index;

	}

	/**
	 * @return the description
	 */
	public String getDescription() {
	    return this.description;
	}

	/**
	 * @return the name
	 */
	public String getName() {
	    return this.name;
	}

	@Override
	public String toString() {
	    return this.name + " (" + this.description + ")";
	}
    }

    private static final int ARROW_LENGTH = 25;
    private static final int ARROW_WIDTH = 15;
    private static final int BOTTOM_RECT_PADDING = 6;
    private static final int BOTTOM_TEXT_PADDING = 2;
    private static final int DIAMOND_LENGTH = 20;
    private static final int DIAMOND_WIDTH = 15;
    private static GfxFont font = new GfxFont("monospace", 10, GfxFont.NORMAL,
	    GfxFont.NORMAL, GfxFont.NORMAL);
    private static final int LEFT_RECT_PADDING = 4;
    private static final int LEFT_TEXT_PADDING = 1;
    private static final int MOVING_STEP = 20;
    private static QualityLevel qualityLevel;
    private static final int REFLEXIVE_PATH_X_GAP = 25;
    private static final int REFLEXIVE_PATH_Y_GAP = 50;
    private static final int RIGHT_RECT_PADDING = 4;
    private static final int RIGHT_TEXT_PADDING = 1;
    private static GfxFont smallCapsFont = new GfxFont("monospace", 10,
	    GfxFont.NORMAL, GfxFont.SMALL_CAPS, GfxFont.NORMAL);

    private static final int SOLID_ARROW_LENGTH = 30;

    private static final int SOLID_ARROW_WIDTH = 20;

    private static final int TOP_RECT_PADDING = 4;

    private static final int TOP_TEXT_PADDING = 1;

    /**
     * @return the aRROW_LENGTH
     */
    public static int getArrowLenght() {
	return ARROW_LENGTH;
    }

    /**
     * @return the aRROW_WIDTH
     */
    public static int getArrowWidth() {
	return ARROW_WIDTH;
    }

    /**
     * @return the dIAMOND_LENGTH
     */
    public static int getDiamondLength() {
	return DIAMOND_LENGTH;
    }

    /**
     * @return the dIAMOND_WIDTH
     */
    public static int getDiamondWidth() {
	return DIAMOND_WIDTH;
    }

    public static GfxFont getFont() {
	return font;
    }

    public static int getMovingStep() {
	return MOVING_STEP;
    }

    /**
     * @return the qualityLevel
     */
    public static QualityLevel getQualityLevel() {
	return qualityLevel;
    }

    public static int getRectangleBottomPadding() {
	return BOTTOM_RECT_PADDING;
    }

    public static int getRectangleLeftPadding() {
	return LEFT_RECT_PADDING;
    }

    public static int getRectangleRightPadding() {
	return RIGHT_RECT_PADDING;
    }

    public static int getRectangleTopPadding() {
	return TOP_RECT_PADDING;
    }

    public static int getRectangleXTotalPadding() {
	return RIGHT_RECT_PADDING + LEFT_RECT_PADDING;
    }

    public static int getRectangleYTotalPadding() {
	return TOP_RECT_PADDING + BOTTOM_RECT_PADDING;
    }

    /**
     * @return the rEFLEXIVE_PATH_X_GAP
     */
    public static int getReflexivePathXGap() {
	return REFLEXIVE_PATH_X_GAP;
    }

    /**
     * @return the rEFLEXIVE_PATH_Y_GAP
     */
    public static int getReflexivePathYGap() {
	return REFLEXIVE_PATH_Y_GAP;
    }

    public static GfxFont getSmallCapsFont() {
	return smallCapsFont;
    }

    /**
     * @return the fILLED_ARROW_LENGTH
     */
    public static int getSolidArrowLenght() {
	return SOLID_ARROW_LENGTH;
    }

    /**
     * @return the fILLED_ARROW_WIDTH
     */
    public static int getSolidArrowWidth() {
	return SOLID_ARROW_WIDTH;
    }

    public static int getTextBottomPadding() {
	return BOTTOM_TEXT_PADDING;
    }

    public static int getTextLeftPadding() {
	return LEFT_TEXT_PADDING;
    }

    public static int getTextRightPadding() {
	return RIGHT_TEXT_PADDING;
    }

    public static int getTextTopPadding() {
	return TOP_TEXT_PADDING;
    }

    public static int getTextXTotalPadding() {
	return RIGHT_TEXT_PADDING + LEFT_TEXT_PADDING;
    }

    public static int getTextYTotalPadding() {
	return TOP_TEXT_PADDING + BOTTOM_TEXT_PADDING;
    }

    /**
     * @param level
     * @return the isAnimated
     */
    public static boolean qualityLevelIsAlmost(final QualityLevel level) {
	return QualityLevel.compare(qualityLevel, level);
    }

    /**
     * @param qualityLevel
     *            the qualityLevel to set
     */
    public static void setQualityLevel(final QualityLevel qualityLevel) {
	OptionsManager.qualityLevel = qualityLevel;
    }
}
