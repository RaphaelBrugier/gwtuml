package com.objetdirect.gwt.umlapi.client.webinterface;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.objetdirect.gwt.umlapi.client.artifacts.ClassArtifact;
import com.objetdirect.gwt.umlapi.client.engine.ClassDiagramAnimator;
import com.objetdirect.gwt.umlapi.client.engine.StandardClassEditor;

public class DrawerPanel extends HorizontalPanel{

	public DrawerPanel() {
		Log.info("Creating drawer");


		this.setWidth("100%");

		final UMLCanvas gc = new UMLCanvas(800, 600);
		this.add(gc);
		HotKeyManager.setActiveCanvas(gc);

		ClassArtifact defaultclass = new ClassArtifact("Class1");
		defaultclass.setLocation(400, 300);
		gc.add(defaultclass);

		DOM.setStyleAttribute(gc.getElement(), "border", "2px solid black");

		gc.addUMLElementListener(new ClassDiagramAnimator().
				setClassEditor(new StandardClassEditor())
		);
		Log.info("Init class end\nBegin sidebar");
		VerticalPanel sidePanel = new VerticalPanel();
		sidePanel.setSpacing(5);

		Button addClass = new Button("Add Class (C)");
		Button addNote = new Button("Add Note (N)");
		Button makeLink = new Button("Make Link (L)");

		addClass.setPixelSize(128, 64);
		addNote.setPixelSize(128, 64);
		makeLink.setPixelSize(128, 64);

		addClass.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				gc.addNewClass();	        	
			}
		});

		addNote.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				gc.addNewNote();
			}
		});

		makeLink.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				gc.addNewLink();
			}
		});

		sidePanel.add(addClass);
		sidePanel.add(addNote);
		sidePanel.add(makeLink);

		this.add(sidePanel);

		Log.info("Init end");
	}

}
