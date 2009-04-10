package com.objetdirect.gwt.umlapi.client.gfx;

import com.allen_sauer.gwt.log.client.Log;

class Line extends IncubatorGfxObject {

    private final int h;
    private final int w;

    public Line(final int x1, final int y1, final int x2, final int y2) {
	this.x = x1;
	this.y = y1;
	this.w = x2 - x1;
	this.h = y2 - y1;
    }

    @Override
    public void draw() {
	if (!this.isVisible) {
	    Log.trace(this + " is not visible");
	    return;
	}
	if (this.canvas == null) {
	    Log.fatal("canvas is null for " + this);
	}

	Log.trace("Drawing " + this);
	this.canvas.saveContext();
	if (this.fillColor != null) {
	    this.canvas.setFillStyle(this.fillColor);
	}
	if (this.strokeColor != null) {
	    this.canvas.setStrokeStyle(this.strokeColor);
	}
	if (this.strokeWidth != 0) {
	    this.canvas.setLineWidth(this.strokeWidth);
	}
	this.canvas.beginPath();
	this.canvas.moveTo(getX(), getY());
	this.canvas.lineTo(getX() + this.w, getY() + this.h);
	this.canvas.closePath();
	this.canvas.stroke();
	this.canvas.restoreContext();
    }

    @Override
    public int getHeight() {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public int getWidth() {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public boolean isPointed(final int xp, final int yp) {
	return false;
    }
}
