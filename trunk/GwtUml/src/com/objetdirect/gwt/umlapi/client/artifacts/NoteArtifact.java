package com.objetdirect.gwt.umlapi.client.artifacts;

import java.util.ArrayList;
import java.util.List;

import com.objetdirect.gwt.umlapi.client.ThemeManager;
import com.objetdirect.gwt.umlapi.client.gfx.GfxManager;
import com.objetdirect.gwt.umlapi.client.gfx.GfxObject;
import com.objetdirect.gwt.umlapi.client.gfx.GfxPlatform;


public class NoteArtifact extends BoxArtifact {

	protected static int TEXT_OFFSET = 25;
	protected static int TEXT_MARGIN = 8;
	
	public NoteArtifact() {
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void addDependency(NoteLinkArtifact dependency) {
		dependencies.add(dependency);
	}
	
	public float[] getOpaque() {
		float[] opaque = new float[] {
				getX()+TEXT_OFFSET-TEXT_MARGIN, getY(),
				getX(), getY()+TEXT_OFFSET-TEXT_MARGIN,
				getX(), getY()+getHeight(),
				getX()+getWidth(), getY()+getHeight(),
				getX()+getWidth(), getY()
		};
		return opaque;
	}
	
	protected GfxObject buildGfxObject() {
		GfxPlatform gPlatform = GfxManager.getInstance();
		GfxObject vg = gPlatform.buildVirtualGroup();
		
		contentText = gPlatform.buildText(content);
		gPlatform.translate(contentText, TEXT_OFFSET, TEXT_OFFSET);
		gPlatform.setFillColor(contentText, ThemeManager.getForegroundColor());
		borderPath = getBorderPath();
		cornerPath = getCornerPath();
		gPlatform.addToVirtualGroup(vg, borderPath);
		gPlatform.addToVirtualGroup(vg, contentText);
		gPlatform.addToVirtualGroup(vg, cornerPath);
		return vg;
	}

	protected GfxObject getBorderPath() {
		GfxPlatform gPlatform = GfxManager.getInstance();
		GfxObject path = gPlatform.buildPath();
		
		gPlatform.moveTo(path, TEXT_OFFSET-TEXT_MARGIN, 0);
		gPlatform.lineTo(path, getWidth(), 0);
		gPlatform.lineTo(path, getWidth(), getHeight());
		gPlatform.lineTo(path, 0, getHeight());
		gPlatform.lineTo(path, 0, TEXT_OFFSET-TEXT_MARGIN);
		gPlatform.lineTo(path, TEXT_OFFSET-TEXT_MARGIN, 0);
		gPlatform.setFillColor(path, ThemeManager.getBackgroundColor());
		gPlatform.setStroke(path, ThemeManager.getForegroundColor(), 1);
		return path;
	}
	
	protected GfxObject getCornerPath() {
		GfxPlatform gPlatform = GfxManager.getInstance();
		GfxObject path = gPlatform.buildPath();
		
		gPlatform.moveTo(path, TEXT_OFFSET-TEXT_MARGIN, 0);
		gPlatform.lineTo(path, TEXT_OFFSET-TEXT_MARGIN, TEXT_OFFSET-TEXT_MARGIN);
		gPlatform.lineTo(path, 0, TEXT_OFFSET-TEXT_MARGIN);
		gPlatform.lineTo(path, TEXT_OFFSET-TEXT_MARGIN, 0);
		gPlatform.setFillColor(path, ThemeManager.getBackgroundColor());
		gPlatform.setStroke(path, ThemeManager.getForegroundColor(), 1);
		return path;
	}

	public int getWidth() {
		return TEXT_OFFSET+(int)GfxManager.getInstance().getWidthFor(contentText)/4*3+TEXT_MARGIN;
	}

	public int getHeight() {
		return TEXT_OFFSET+(int)GfxManager.getInstance().getHeightFor(contentText)+TEXT_MARGIN;
	}

	public List<GfxObject> getComponents() {
		List<GfxObject> comps = new ArrayList<GfxObject>();
		comps.add(contentText);
		comps.add(borderPath);
		comps.add(cornerPath);
		return comps;
	}
	
	public void select() {
		GfxManager.getInstance().setStroke(borderPath, ThemeManager.getHighlightedForegroundColor(), 2);
		GfxManager.getInstance().setStroke(cornerPath, ThemeManager.getHighlightedForegroundColor(), 2);
	}

	public void unselect() {
		GfxManager.getInstance().setStroke(borderPath, ThemeManager.getForegroundColor(), 1);
		GfxManager.getInstance().setStroke(cornerPath, ThemeManager.getForegroundColor(), 1);
	}
	
	public void adjusted() {
		super.adjusted();
		for (int i=0; i<dependencies.size(); i++) {
			NoteLinkArtifact elem = dependencies.get(i);
			elem.adjust();
		}
	}
	
	public Object getSubPart(GfxObject o) {
		// TODO Auto-generated method stub
		return null;
	}
	
	String content="";
	GfxObject contentText = null;
	GfxObject borderPath = null;
	GfxObject cornerPath = null;
	List<NoteLinkArtifact> dependencies = new ArrayList<NoteLinkArtifact>();
	GfxObject vg;
}
