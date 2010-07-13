/*
 * This file is part of the Gwt-Generator project and was written by Rapha�l Brugier <raphael dot brugier at gmail dot com > for Objet Direct
 * <http://wwww.objetdirect.com>
 * 
 * Copyright � 2010 Objet Direct
 * 
 * Gwt-Generator is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 * Gwt-Generator is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with Gwt-Generator. If not, see <http://www.gnu.org/licenses/>.
 */
package com.objetdirect.gwt.umlapi.client.umlCanvas;

import static com.objetdirect.gwt.umlapi.client.helpers.CursorIconManager.PointerStyle.MOVE;
import static com.objetdirect.gwt.umlapi.client.umlCanvas.UMLCanvas.DragAndDropState.NONE;
import static com.objetdirect.gwt.umlapi.client.umlCanvas.UMLCanvas.DragAndDropState.TAKING;
import static com.objetdirect.gwt.umlapi.client.umlcomponents.umlrelation.UMLLink.LinkKind.INSTANTIATION;
import static com.objetdirect.gwt.umlapi.client.umlcomponents.umlrelation.UMLLink.LinkKind.OBJECT_RELATION;

import java.util.ArrayList;
import java.util.List;

import com.allen_sauer.gwt.log.client.Log;
import com.objetdirect.gwt.umlapi.client.artifacts.LinkArtifact;
import com.objetdirect.gwt.umlapi.client.artifacts.UMLArtifact;
import com.objetdirect.gwt.umlapi.client.artifacts.object.ClassSimplifiedArtifact;
import com.objetdirect.gwt.umlapi.client.artifacts.object.InstantiationRelationLinkArtifact;
import com.objetdirect.gwt.umlapi.client.artifacts.object.ObjectArtifact;
import com.objetdirect.gwt.umlapi.client.artifacts.object.ObjectRelationLinkArtifact;
import com.objetdirect.gwt.umlapi.client.contextMenu.ContextMenu;
import com.objetdirect.gwt.umlapi.client.contextMenu.MenuBarAndTitle;
import com.objetdirect.gwt.umlapi.client.engine.Point;
import com.objetdirect.gwt.umlapi.client.gfx.GfxObject;
import com.objetdirect.gwt.umlapi.client.umlcomponents.UMLClass;
import com.objetdirect.gwt.umlapi.client.umlcomponents.UMLObject;
import com.objetdirect.gwt.umlapi.client.umlcomponents.umlrelation.InstantiationRelation;
import com.objetdirect.gwt.umlapi.client.umlcomponents.umlrelation.ObjectRelation;
import com.objetdirect.gwt.umlapi.client.umlcomponents.umlrelation.UMLRelation;

/**
 * UMLCanvas concrete class for an object diagram.
 * 
 * @author Rapha�l Brugier <raphael dot brugier at gmail dot com>
 */
@SuppressWarnings("serial")
public class UMLCanvasObjectDiagram extends UMLCanvas implements ObjectDiagram {

	/**
	 * Classes defined in the instantiated class diagram.
	 */
	private List<UMLClass> domainClasses;

	/**
	 * Classes that are defined directly in the object diagram. This classes are a simplifed version of the UMLClass.
	 */
	private List<UMLClass> objectDiagramClasses;

	private List<UMLRelation> classRelations;

	/**
	 * Default constructor only for gwt-rpc serialization
	 */
	@SuppressWarnings("unused")
	private UMLCanvasObjectDiagram() {
	}

	protected UMLCanvasObjectDiagram(@SuppressWarnings("unused") boolean dummy) {
		super(true);

		objectDiagramClasses = new ArrayList<UMLClass>();
	}

	@Override
	public void addNewClass() {
		this.addNewClass(wrapper.getCurrentMousePosition());
	}

	public void addNewObject() {
		this.addNewObject(wrapper.getCurrentMousePosition());
	}

	@Override
	public void dropContextualMenu(GfxObject gfxObject, Point location) {
		doSelection(gfxObject, false, false);

		MenuBarAndTitle rightMenu;
		if (getUMLArtifact(gfxObject) == null) {
			rightMenu = null;
		} else {
			rightMenu = getUMLArtifact(gfxObject).getRightMenu();
		}

		ContextMenu contextMenu = ContextMenu.createObjectDiagramContextMenu(location, this, rightMenu);

		contextMenu.show();
	}

	private void addNewClass(final Point location) {
		if (dragAndDropState != NONE) {
			return;
		}
		final ClassSimplifiedArtifact newClass = new ClassSimplifiedArtifact(this, idCount, "ClassName");
		objectDiagramClasses.add(newClass.toUMLComponent());

		this.add(newClass);
		newClass.moveTo(Point.substract(location, getCanvasOffset()));
		for (final UMLArtifact selectedArtifact : selectedArtifacts.keySet()) {
			selectedArtifact.unselect();
		}
		selectedArtifacts.clear();
		this.doSelection(newClass.getGfxObject(), false, false);
		selectedArtifacts.put(newClass, new ArrayList<Point>());
		dragOffset = location;
		wrapper.setCursorIcon(MOVE);
		dragAndDropState = TAKING;
		mouseIsPressed = true;

		wrapper.setHelpText("Adding a new class", location.clonePoint());
	}

	/**
	 * Add a new object with default values to this canvas at the specified location
	 * 
	 * @param location
	 *            The initial object location
	 */
	private void addNewObject(final Point location) {
		if (dragAndDropState != NONE) {
			return;
		}

		UMLClass clazz = new UMLClass("Object");
		if (domainClasses.size() != 0) {
			clazz = domainClasses.get(0);
		} else if (objectDiagramClasses.size() != 0) {
			clazz = objectDiagramClasses.get(0);
		}

		final ObjectArtifact newObject = new ObjectArtifact(this, idCount, clazz);

		this.add(newObject);
		newObject.moveTo(Point.substract(location, getCanvasOffset()));
		for (final UMLArtifact selectedArtifact : selectedArtifacts.keySet()) {
			selectedArtifact.unselect();
		}
		selectedArtifacts.clear();
		this.doSelection(newObject.getGfxObject(), false, false);
		selectedArtifacts.put(newObject, new ArrayList<Point>());
		dragOffset = location;
		wrapper.setCursorIcon(MOVE);
		dragAndDropState = TAKING;
		mouseIsPressed = true;

		wrapper.setHelpText("Adding a new object", location.clonePoint());
	}

	@Override
	protected LinkArtifact makeLinkBetween(UMLArtifact uMLArtifact, UMLArtifact uMLArtifactNew) {
		try {
			if (activeLinking == OBJECT_RELATION && uMLArtifactNew instanceof ObjectArtifact && uMLArtifact instanceof ObjectArtifact) {
				return makeObjectRelationLink((ObjectArtifact) uMLArtifact, (ObjectArtifact) uMLArtifactNew);
			} else if ((activeLinking == INSTANTIATION)) {
				ClassSimplifiedArtifact classArtifact = null;
				ObjectArtifact objectArtifact = null;

				// Dirty dirty dirty :(
				if (uMLArtifactNew instanceof ClassSimplifiedArtifact && uMLArtifact instanceof ObjectArtifact) {
					classArtifact = (ClassSimplifiedArtifact) uMLArtifactNew;
					objectArtifact = (ObjectArtifact) uMLArtifact;
				} else if (uMLArtifact instanceof ClassSimplifiedArtifact && uMLArtifactNew instanceof ObjectArtifact) {
					classArtifact = (ClassSimplifiedArtifact) uMLArtifact;
					objectArtifact = (ObjectArtifact) uMLArtifactNew;
				} else {
					return null;
				}
				return new InstantiationRelationLinkArtifact(this, idCount, classArtifact, objectArtifact);
			}
		} catch (IllegalArgumentException e) {
			return null;
		}
		return null;
	}

	/**
	 * @param uMLArtifact
	 * @param uMLArtifactNew
	 * @return
	 */
	private LinkArtifact makeObjectRelationLink(ObjectArtifact left, ObjectArtifact right) throws IllegalArgumentException {
		// If no classes relations have been setup, we allow all the objects relations.
		if (classRelations == null) {
			Log.debug("UMLCanvasObject::makeObjectRelationLink  --  classRelations == null");
			return new ObjectRelationLinkArtifact(this, idCount, left, right);
		}

		for (UMLRelation relation : classRelations) {
			if (relation.getLeftTarget().getName().equals(left.toUMLComponent().getClassName())
					&& relation.getRightTarget().getName().equals(right.toUMLComponent().getClassName())) {
				ObjectRelationLinkArtifact objectRelation = new ObjectRelationLinkArtifact(this, idCount, left, right);
				objectRelation.setRightRole(relation.getRightRole());
				return objectRelation;
			}
		}

		return null;
	}


	@Override
	public List<UMLObject> getObjects() {
		ArrayList<UMLObject> umlObjects = new ArrayList<UMLObject>();

		for (final UMLArtifact umlArtifact : getArtifactById().values()) {
			if (umlArtifact instanceof ObjectArtifact) {
				ObjectArtifact objectArtifact = (ObjectArtifact) umlArtifact;
				umlObjects.add(objectArtifact.toUMLComponent());
			}
		}
		return umlObjects;
	}

	@Override
	public List<InstantiationRelation> getInstantiationRelations() {
		List<InstantiationRelation> instantiations = new ArrayList<InstantiationRelation>();

		for (final UMLArtifact umlArtifact : getArtifactById().values()) {
			if (umlArtifact instanceof InstantiationRelationLinkArtifact) {
				InstantiationRelationLinkArtifact instantiationArtifact = (InstantiationRelationLinkArtifact) umlArtifact;
				instantiations.add(instantiationArtifact.toUMLComponent());
			}
		}

		return instantiations;
	}

	@Override
	public List<ObjectRelation> getObjectRelations() {
		List<ObjectRelation> relations = new ArrayList<ObjectRelation>();

		for (final UMLArtifact umlArtifact : getArtifactById().values()) {
			if (umlArtifact instanceof ObjectRelationLinkArtifact) {
				ObjectRelationLinkArtifact relationArtifact = (ObjectRelationLinkArtifact) umlArtifact;
				relations.add(relationArtifact.toUMLComponent());
			}
		}

		return relations;
	}

	@Override
	public List<UMLClass> getClasses() {
		// If there is at least one class defined in the domain classes or in the object diagram
		// return the concatenation of the two lists.
		if (domainClasses.size() != 0 || objectDiagramClasses.size() != 0) {
			List<UMLClass> classes = new ArrayList<UMLClass>(domainClasses);
			classes.addAll(objectDiagramClasses);
			return classes;
		}

		// Else return a list with a default UmlClass.
		List<UMLClass> classes = new ArrayList<UMLClass>();
		classes.add(new UMLClass("Object"));
		return classes;
	}

	@Override
	public void setClasses(List<UMLClass> classes) {
		domainClasses = classes;
	}

	@Override
	public void setClassRelations(List<UMLRelation> classRelations) {
		this.classRelations = classRelations;
	}
}
