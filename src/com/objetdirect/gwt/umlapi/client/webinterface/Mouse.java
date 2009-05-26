/**
 * 
 */
/*
 *    This file is part of the GWTUML project
 *    and was written by Mounier Florian <mounier-dot-florian.at.gmail'dot'com> 
 *    for Objet Direct <http://wwww.objetdirect.com>
 *    
 *    Copyright © 2009 Objet Direct
 *    Contact: gwtuml@googlegroups.com
 *    
 *    GWTUML is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    GWTUML is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with GWTUML. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.objetdirect.gwt.umlapi.client.webinterface;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.dom.client.NativeEvent;
import com.objetdirect.gwt.umlapi.client.engine.Point;
import com.objetdirect.gwt.umlapi.client.gfx.GfxObject;

/**
 * @author Florian Mounier (mounier-dot-florian.at.gmail'dot'com)
 *
 */
public class Mouse {

    private static boolean imEnabled = true;

    /**
     * This method represent a double click with the mouse. <br />
     * It's automatically called on double click but can also be called manually for testing purpose
     *  
     * @param gfxObject The object on which this event has occurred
     * @param location The location of the event
     * @param triggerButton A number representing which button has triggered the event
     * @param isCtrlDown True if ctrl key was down during the event
     * @param isAltDown True if alt key was down during the event
     * @param isShiftDown True if shift key was down during the event
     * @param isMetaKey True if meta key (Win key / Home key) was down during the event
     */
    public static void doubleClick(GfxObject gfxObject, Point location,
	    int triggerButton, boolean isCtrlDown, boolean isAltDown, boolean isShiftDown,
	    boolean isMetaKey) {
	if(imEnabled) {
	    Log.trace("Mouse double clicked on " + gfxObject + " at " + location + " with button " + triggerButton + " ctrl " + isCtrlDown + " alt " + isAltDown + " shift " + isShiftDown);
	    Session.getActiveCanvas().mouseDoubleClicked(gfxObject, location);
	}
    }
    /**
     * This method represent a movement with the mouse. <br />
     * It's automatically called on mouse move but can also be called manually for testing purpose
     * 
     * @param location The location of the event
     * @param triggerButton A number representing which button has triggered the event
     * @param isCtrlDown True if ctrl key was down during the event
     * @param isAltDown True if alt key was down during the event
     * @param isShiftDown True if shift key was down during the event
     * @param isMetaKey True if meta key (Win key / Home key) was down during the event
     */
    public static void move(Point location,
	    int triggerButton, boolean isCtrlDown, boolean isAltDown, boolean isShiftDown,
	    boolean isMetaKey) {
	if(imEnabled) {
	//    Log.trace("Mouse moved to " + location + " with button " + triggerButton + " ctrl " + isCtrlDown + " alt " + isAltDown + " shift " + isShiftDown);
	    Session.getActiveCanvas().mouseMoved(location, isCtrlDown, isShiftDown);

	}
    }
    /**
     * This method represent a mouse press with the mouse. <br />
     * It's automatically called on mouse press but can also be called manually for testing purpose
     *  
     * @param gfxObject The object on which this event has occurred
     * @param location The location of the event
     * @param triggerButton A number representing which button has triggered the event
     * @param isCtrlDown True if ctrl key was down during the event
     * @param isAltDown True if alt key was down during the event
     * @param isShiftDown True if shift key was down during the event
     * @param isMetaKey True if meta key (Win key / Home key) was down during the event
     */
    public static void press(GfxObject gfxObject, Point location,
	    int triggerButton, boolean isCtrlDown, boolean isAltDown, boolean isShiftDown,
	    boolean isMetaKey) {
	if(imEnabled) {
	    Log.trace("Mouse pressed on " + gfxObject + " at " + location + " with button " + triggerButton + " ctrl " + isCtrlDown + " alt " + isAltDown + " shift " + isShiftDown);
	    if(triggerButton == NativeEvent.BUTTON_LEFT) {
		Session.getActiveCanvas().mouseLeftPressed(gfxObject, location, isCtrlDown, isShiftDown);
	    } else if (triggerButton == NativeEvent.BUTTON_RIGHT) {
		Session.getActiveCanvas().mouseRightPressed(gfxObject, location);
	    }

	}
    }
    /**
     * This method represent a mouse release with the mouse. <br />
     * It's automatically called on release but can also be called manually for testing purpose
     *  
     * @param gfxObject The object on which this event has occurred
     * @param location The location of the event
     * @param triggerButton A number representing which button has triggered the event
     * @param isCtrlDown True if ctrl key was down during the event
     * @param isAltDown True if alt key was down during the event
     * @param isShiftDown True if shift key was down during the event
     * @param isMetaKey True if meta key (Win key / Home key) was down during the event
     */
    public static void release(GfxObject gfxObject, Point location,
	    int triggerButton, boolean isCtrlDown, boolean isAltDown, boolean isShiftDown,
	    boolean isMetaKey) {
	if(imEnabled) {
	    Log.trace("Mouse released on " + gfxObject + " at " + location + " with button " + triggerButton + " ctrl " + isCtrlDown + " alt " + isAltDown + " shift " + isShiftDown);
	    if(triggerButton == NativeEvent.BUTTON_LEFT) {
		Session.getActiveCanvas().mouseReleased(gfxObject, location, isCtrlDown, isShiftDown);
	    }
	}
    }
    /**
     * Getter to the current state of {@link Mouse}
     * 
     * @return true if it is enabled false otherwise
     */
    public static boolean isEnabled() {
	return imEnabled;
    }

    /**
     * Set the {@link Mouse} state. This is used to disable {@link Mouse} while editing for instance
     * 
     * @param isEnabled The status : True to activate False to disable 
     */
    public static void setEnabled(final boolean isEnabled) {
	Mouse.imEnabled = isEnabled;
    }
}
