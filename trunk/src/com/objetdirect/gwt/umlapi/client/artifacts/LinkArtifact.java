package com.objetdirect.gwt.umlapi.client.artifacts;

import com.objetdirect.gwt.umlapi.client.engine.Point;
import com.objetdirect.gwt.umlapi.client.gfx.GfxObject;
import com.objetdirect.gwt.umlapi.client.gfx.GfxStyle;

/**
 * This abstract class specialize an {@link UMLArtifact} in a link type artifact
 * 
 * @author Florian Mounier (mounier-dot-florian.at.gmail'dot'com)
 *
 */
public abstract class LinkArtifact extends UMLArtifact {

    /**
     * This enumeration list all the adornments that a relation could have
     * 
     * @author Florian Mounier (mounier-dot-florian.at.gmail'dot'com)
     */
    public enum LinkAdornment {
	
	/**
	 * No adornment -
	 */
	NONE(Shape.UNSHAPED, false),
	
	/**
	 * A wire arrow : -&gt;
	 */
	WIRE_ARROW(Shape.ARROW, false),
	
	/**
	 * A simple filled arrow : -|&gt;
	 */
	SOLID_ARROW(Shape.ARROW, true),
	
	/**
	 * A filled diamond : -&lt;&gt;
	 */
	SOLID_DIAMOND(Shape.DIAMOND, true),
	
	/**
	 * A filled diamond with foreground color : -&lt;@&gt;
	 */
	INVERTED_SOLID_DIAMOND(Shape.DIAMOND, true, true);
	
	

	/**
	 * This sub enumeration specify the global shape of the adornment
	 *  
	 * @author Florian Mounier (mounier-dot-florian.at.gmail'dot'com)
	 *
	 */
	public enum Shape {
	    /**
	     * Arrow type
	     */
	    ARROW("<"),
	    /**
	     * Cross type
	     */
	    CROSS("x"),
	    /**
	     * Diamond type
	     */
	    DIAMOND("<>"),
	    /**
	     * No shape
	     */
	    UNSHAPED("");
	    
	    private final String idiom;

	    private Shape(final String idiom) {
		this.idiom = idiom;
	    }

	    /**
	     * Getter for the idiom
	     * 
	     * @return a string that represent the shape textually : for arrow  &lt; 
	     */
	    public String getIdiom() {
		return getIdiom(false);
	    }

	    /**
	     * Specific Getter for the idiom which change shape orientation between left &lt; and right &gt; 
	     * 
	     * @param isRight : if the shape is oriented to the right
	     * @return a string that represent the shape textually in the right orientation : for right arrow  &gt; 
	     */
	    public String getIdiom(final boolean isRight) {
		if (this.idiom.equals("<") && isRight) {
		    return ">";
		}
		return this.idiom;
	    }
	}

	private boolean isCrossed;
	private final boolean isInverted;
	private final boolean isSolid;
	private final Shape shape;

	private LinkAdornment(final Shape shape, final boolean isSolid) {
	    this(shape, isSolid, false);
	}

	private LinkAdornment(final Shape shape, final boolean isSolid,
		final boolean isInverted) {
	    this.shape = shape;
	    this.isSolid = isSolid;
	    this.isInverted = isInverted;
	}

	/** Getter for the shape
	 * @return the shape
	 */
	public Shape getShape() {
	    return this.shape;
	}

	/** 
	 * Determine if the shape is crossed or not
	 * 
	 * @return <ul>
	 *         <li><b>True</b> if it is crossed</li>
	 *         <li><b>False</b> otherwise</li>
	 *         </ul>
	 */
	public boolean isCrossed() {
	    return this.isCrossed;
	}
	
	/** 
	 * Determine if the shape is inverted or not (ie : the fill color is the foreground color)
	 * 
	 * @return <ul>
	 *         <li><b>True</b> if it is inverted</li>
	 *         <li><b>False</b> otherwise</li>
	 *         </ul>
	 */
	public boolean isInverted() {
	    return this.isInverted;
	}

	/** 
	 * Determine if the shape is filled or not 
	 * 
	 * @return <ul>
	 *         <li><b>True</b> if it is filled</li>
	 *         <li><b>False</b> otherwise</li>
	 *         </ul>
	 */
	public boolean isSolid() {
	    return this.isSolid;
	}

	/**
	 * Setter for the crossed state of the shape 
	 * @param isCrossed
	 *            <ul>
	 *         <li><b>True</b> to make the shape crossed</li>
	 *         <li><b>False</b> to make the shape normal</li>
	 *         </ul>
	 */
	public void setCrossed(final boolean isCrossed) {
	    this.isCrossed = isCrossed;
	}

    }
    /**
     * This enumeration list all the style that a link could have
     * 
     * @author Florian Mounier (mounier-dot-florian.at.gmail'dot'com)
     */
    public enum LinkStyle {
	
	/**
	 * Dash style : - - - - - 
	 */
	DASHED(GfxStyle.DASH),
	/**
	 * Long dash style : -- -- -- -- --
	 */
	LONG_DASHED(GfxStyle.LONGDASH),
	/**
	 * Solid style : ------------ 
	 */
	SOLID(GfxStyle.NONE);

	private final GfxStyle style;

	private LinkStyle(final GfxStyle style) {
	    this.style = style;
	}

	/**
	 * Getter for the {@link GfxStyle}
	 * 
	 * @return the {@link GfxStyle} to set to a line
	 */
	public GfxStyle getGfxStyle() {
	    return this.style;
	}
    }

    protected LinkAdornment adornmentLeft;
    protected LinkAdornment adornmentRight;
    protected Point leftPoint = Point.getOrigin();
    protected Point rightPoint = Point.getOrigin();
    protected LinkStyle style;

    @Override
    public int getHeight() {
	return this.leftPoint.getY() < this.rightPoint.getY() ? this.rightPoint.getY()
		- this.leftPoint.getY() : this.leftPoint.getY() - this.rightPoint.getY();
    }

    @Override
    public Point getLocation() {
	return Point.min(this.leftPoint, this.rightPoint);
    }

    @Override
    public int[] getOpaque() {
	return null;
    }

    @Override
    public GfxObject getOutline() {
	return null;
    }

    @Override
    public int getWidth() {
	return this.leftPoint.getX() < this.rightPoint.getX() ? this.rightPoint.getX()
		- this.leftPoint.getX() : this.leftPoint.getX() - this.rightPoint.getX();
    }

    @Override
    public boolean isALink() {
	return true;
    }

    @Override
    public boolean isDraggable() {
	return false;
    }

   /**
    * This method add an extra dependency removal for link <br> 
    * to tell other artifact that they don't need to be still dependent on this line
    */
    public abstract void removeCreatedDependency();
}
