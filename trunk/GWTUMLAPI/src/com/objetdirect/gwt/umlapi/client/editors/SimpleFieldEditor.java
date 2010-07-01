/*
 * This file is part of the GWTUML project and was written by Rapha�l Brugier (raphael-dot-brugier.at.gmail'dot'com) for Objet Direct
 * <http://wwww.objetdirect.com>
 * 
 * Copyright © 2010 Objet Direct Contact: gwtuml@googlegroups.com
 * 
 * GWTUML is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 * GWTUML is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with GWTUML. If not, see <http://www.gnu.org/licenses/>.
 */
package com.objetdirect.gwt.umlapi.client.editors;

import com.objetdirect.gwt.umlapi.client.artifacts.UMLArtifact;
import com.objetdirect.gwt.umlapi.client.umlCanvas.UMLCanvas;

/**
 * Simple field editor.
 * 
 * @author Rapha�l Brugier (raphael-dot-brugier.at.gmail'dot'com)
 */
public class SimpleFieldEditor extends FieldEditor {

	private final EditorPart editable;

	/**
	 * @param canvas
	 *            The canvas where to draw the editor
	 * @param artifact
	 *            The artifact owning the field to edit.
	 * @param editable
	 *            An instance of the EditorPart used to edit the field internally.
	 */
	public SimpleFieldEditor(final UMLCanvas canvas, final UMLArtifact artifact, EditorPart editable) {
		super(canvas, artifact);
		this.editable = editable;
	}

	@Override
	protected void next() {
		// No next part to edit
	}

	@Override
	protected boolean updateUMLArtifact(String newContent) {
		editable.setText(newContent);
		artifact.rebuildGfxObject();
		return false;
	}

}
