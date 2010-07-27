/*
 * This file is part of the GWTUML project and was written by Mounier Florian <mounier-dot-florian.at.gmail'dot'com> for Objet Direct
 * <http://wwww.objetdirect.com>
 * 
 * Copyright © 2009 Objet Direct Contact: gwtuml@googlegroups.com
 * 
 * GWTUML is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 * GWTUML is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with GWTUML. If not, see <http://www.gnu.org/licenses/>.
 */
package com.objetdirect.gwt.umlapi.client.artifacts;

import java.util.Collections;

import com.objetdirect.gwt.umlapi.client.engine.Direction;
import com.objetdirect.gwt.umlapi.client.engine.Point;
import com.objetdirect.gwt.umlapi.client.gfx.GfxObject;
import com.objetdirect.gwt.umlapi.client.umlCanvas.UMLCanvas;

/**
 * This abstract class specialize an {@link UMLArtifact} in a link type artifact
 * 
 * @author Florian Mounier (mounier-dot-florian.at.gmail'dot'com)
 */
public abstract class LinkArtifact extends UMLArtifact {

	/**
	 * /!\ Don't forget to increment the serialVersionUID if you change any of the fields above /!\
	 */
	private static final long serialVersionUID = 1L;

	private UMLArtifact leftUMLArtifact;

	private UMLArtifact rightUMLArtifact;

	protected Point leftPoint;

	protected Point rightPoint;
	protected int order;

	protected boolean isSelfLink;
	protected Direction leftDirection;
	protected Direction rightDirection;

	protected boolean isTheOneRebuilding;

	private boolean doesntHaveToBeComputed;
	
	private UMLArtifactPeer artifactsTargeted;

	/** Default constructor ONLY for GWT-RPC serialization. */
	@Deprecated
	protected LinkArtifact() {
	}

	/**
	 * Constructor of RelationLinkArtifact
	 * 
	 * @param canvas
	 *            Where the gfxObject are displayed
	 * @param id
	 *            The artifacts's id
	 * @param uMLArtifact1
	 *            First {@link UMLArtifact}
	 * @param uMLArtifact2
	 *            Second {@link UMLArtifact}
	 * 
	 */
	public LinkArtifact(UMLCanvas canvas, int id, final UMLArtifact uMLArtifact1, final UMLArtifact uMLArtifact2) {
		super(canvas, id);
		leftPoint = Point.getOrigin();
		rightPoint = Point.getOrigin();
		isSelfLink = false;
		leftDirection = Direction.UNKNOWN;
		rightDirection = Direction.UNKNOWN;
		isTheOneRebuilding = false;

		leftUMLArtifact = uMLArtifact1;
		rightUMLArtifact = uMLArtifact2;
		artifactsTargeted = new UMLArtifactPeer(uMLArtifact1, uMLArtifact2);
		order = Collections.frequency(this.canvas.getuMLArtifactRelations(), artifactsTargeted);
		this.canvas.getuMLArtifactRelations().add(artifactsTargeted);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.objetdirect.gwt.umlapi.client.artifacts.UMLArtifact#getCenter()
	 */
	@Override
	public Point getCenter() {
		return Point.getMiddleOf(leftPoint, rightPoint);
	}

	@Override
	public int getHeight() {
		return leftPoint.getY() < rightPoint.getY() ? rightPoint.getY() - leftPoint.getY() : leftPoint.getY() - rightPoint.getY();
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
		return leftPoint.getX() < rightPoint.getX() ? rightPoint.getX() - leftPoint.getX() : leftPoint.getX() - rightPoint.getX();
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
	 * @return the artifactsTargeted
	 */
	public UMLArtifactPeer getArtifactsTargeted() {
		return artifactsTargeted;
	}

	/**
	 * Getter for the leftUMLArtifact
	 * 
	 * @return the leftUMLArtifact
	 */
	public UMLArtifact getLeftUMLArtifact() {
		return leftUMLArtifact;
	}

	/**
	 * Getter for the rightUMLArtifact
	 * 
	 * @return the rightUMLArtifact
	 */
	public UMLArtifact getRightUMLArtifact() {
		return rightUMLArtifact;
	}

	/**
	 * This method add an extra dependency removal for link <br>
	 * to tell other artifact that they don't need to be still dependent on this line
	 */
	public abstract void removeCreatedDependency();

	void doesntHaveToBeComputed(final boolean state) {
		doesntHaveToBeComputed = state;
	}

	protected void computeDirectionsType() {
		if (doesntHaveToBeComputed) {
			return;
		}
		isTheOneRebuilding = true;

		final Direction oldLeftDirection = leftDirection;
		final Direction oldRightDirection = rightDirection;

		leftDirection = this.computeDirectionType(leftPoint, leftUMLArtifact);
		rightDirection = this.computeDirectionType(rightPoint, rightUMLArtifact);

		if (leftDirection != oldLeftDirection) {
			leftUMLArtifact.removeDirectionDependecy(oldLeftDirection, this);
			leftUMLArtifact.rebuildDirectionDependencies(oldLeftDirection);
			leftUMLArtifact.addDirectionDependecy(leftDirection, this);
			leftUMLArtifact.sortDirectionDependecy(leftDirection);
			leftUMLArtifact.rebuildDirectionDependencies(leftDirection);
		} else {
			if (canvas.isLinkArtifactsHaveAlreadyBeenSorted()) {
				canvas.setLinkArtifactsHaveAlreadyBeenSorted(true);
				leftUMLArtifact.sortDirectionDependecy(leftDirection);
				leftUMLArtifact.rebuildDirectionDependencies(leftDirection);
				canvas.setLinkArtifactsHaveAlreadyBeenSorted(false);
			}
		}
		if (rightDirection != oldRightDirection) {
			rightUMLArtifact.removeDirectionDependecy(rightDirection, this);
			rightUMLArtifact.rebuildDirectionDependencies(oldRightDirection);
			rightUMLArtifact.addDirectionDependecy(rightDirection, this);
			leftUMLArtifact.sortDirectionDependecy(rightDirection);
			rightUMLArtifact.rebuildDirectionDependencies(rightDirection);
		} else {
			if (canvas.isLinkArtifactsHaveAlreadyBeenSorted()) {
				canvas.setLinkArtifactsHaveAlreadyBeenSorted(true);
				leftUMLArtifact.sortDirectionDependecy(rightDirection);
				rightUMLArtifact.rebuildDirectionDependencies(rightDirection);
				canvas.setLinkArtifactsHaveAlreadyBeenSorted(false);
			}
		}
		isTheOneRebuilding = false;
	}

	private Direction computeDirectionType(final Point point, final UMLArtifact uMLArtifact) {
		if (point.getX() == uMLArtifact.getLocation().getX()) {
			return Direction.LEFT;
		} else if (point.getY() == uMLArtifact.getLocation().getY()) {
			return Direction.UP;
		} else if (point.getX() == uMLArtifact.getLocation().getX() + uMLArtifact.getWidth()) {
			return Direction.RIGHT;
		} else if (point.getY() == uMLArtifact.getLocation().getY() + uMLArtifact.getHeight()) {
			return Direction.DOWN;
		}
		return Direction.UNKNOWN;

	}

}
