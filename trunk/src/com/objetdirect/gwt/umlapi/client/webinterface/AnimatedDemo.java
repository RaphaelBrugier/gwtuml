package com.objetdirect.gwt.umlapi.client.webinterface;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.objetdirect.gwt.umlapi.client.artifacts.UMLArtifact;
import com.objetdirect.gwt.umlapi.client.engine.Point;
import com.objetdirect.gwt.umlapi.client.engine.Scheduler;

/**
 * This class is an exemple of an animated contruction of an uml diagram
 * It shows how to use {@link Keyboard} and {@link Mouse}.
 *   
 * @author Florian Mounier (mounier-dot-florian.at.gmail'dot'com)
 * 
 */
public class AnimatedDemo extends AbsolutePanel {

    private static final int DELAY = 100;


    /**
     * Constructor of the animated demo, used to test the  
     *
     * @param canvas The {@link UMLCanvas} where to add the demo uml artifacts 
     */
    public AnimatedDemo(final UMLCanvas canvas) {
	Log.trace("Creating Animated demo");

	DeferredCommand.addCommand(new Command() {

	    @Override
	    public void execute() {
		HotKeyManager.setInputEnabled(false);
		Session.getActiveCanvas().setMouseEnabled(false);
		
		final Point classOrigin = new Point(50, 50);
		final Point classTarget = new Point(350, 150);
		
		new Scheduler.Task(this, this, DELAY) {@Override public void process() {
		    Mouse.move(classOrigin, NativeEvent.BUTTON_LEFT, false, false, false, false);
		}};
		new Scheduler.Task(this, this, DELAY) {@Override public void process() {
		    Keyboard.push('C');
		}};
		
		move(classOrigin, classTarget);			

		new Scheduler.Task(this, this, DELAY) {@Override public void process() {
		    Mouse.press(null, Point.getOrigin(), NativeEvent.BUTTON_LEFT, false, false, false, false);
		    Mouse.release(null, Point.getOrigin(), NativeEvent.BUTTON_LEFT, false, false, false, false);
		}};

		final Point objectOrigin = new Point(500, 500);
		final Point objectTarget = new Point(50, 400);
		final Point object = new Point(55, 410);
		
		new Scheduler.Task(this, this, DELAY) {@Override public void process() {
		    Mouse.move(objectOrigin, NativeEvent.BUTTON_LEFT, false, false, false, false);
		}};
		
		new Scheduler.Task(this, this, DELAY) {@Override public void process() {
		    Keyboard.push('O');
		}};

		move(objectOrigin, objectTarget);
		
		new Scheduler.Task(this, this, DELAY) {@Override public void process() {
		    Mouse.press(Session.getActiveCanvas().getArtifactAt(object), object, NativeEvent.BUTTON_LEFT, false, false, false, false);
		    Mouse.release(Session.getActiveCanvas().getArtifactAt(object), object, NativeEvent.BUTTON_LEFT, false, false, false, false);
		    Mouse.press(Session.getActiveCanvas().getArtifactAt(classTarget), classTarget, NativeEvent.BUTTON_LEFT, false, false, false, false);
		    Mouse.release(Session.getActiveCanvas().getArtifactAt(classTarget), classTarget, NativeEvent.BUTTON_LEFT, false, false, false, false);
		}};

		
		
		new Scheduler.Task(this, this, DELAY) {@Override public void process() {
		    Keyboard.push('I');
		}};
		
		move(classTarget, object);
		
		new Scheduler.Task(this, this, DELAY) {@Override public void process() {
		    Mouse.press(Session.getActiveCanvas().getArtifactAt(object), object, NativeEvent.BUTTON_LEFT, false, false, false, false);
		    Mouse.release(Session.getActiveCanvas().getArtifactAt(object), object, NativeEvent.BUTTON_LEFT, false, false, false, false);
		}};

		final Point selectBoxOrigin = new Point(40, 100);
		final Point selectBoxTarget = new Point(500, 500);
		new Scheduler.Task(this, this, DELAY) {@Override public void process() {
		    Mouse.press(null, selectBoxOrigin, NativeEvent.BUTTON_LEFT, false, false, false, false);
		}};
		
		move(selectBoxOrigin, selectBoxTarget);
		
		new Scheduler.Task(this, this, DELAY) {@Override public void process() {
		    Mouse.release(null, selectBoxTarget, NativeEvent.BUTTON_LEFT, false, false, false, false);
		}};

		final Point allTarget = new Point(500, 350);
		
		new Scheduler.Task(this, this, DELAY) {@Override public void process() {
		    Mouse.press(Session.getActiveCanvas().getArtifactAt(classTarget), classTarget, NativeEvent.BUTTON_LEFT, false, false, false, false);
		}};
		move(classTarget, allTarget);
		
		new Scheduler.Task(this, this, DELAY) {@Override public void process() {
		    Mouse.release(Session.getActiveCanvas().getArtifactAt(allTarget), allTarget, NativeEvent.BUTTON_LEFT, false, false, false, false);
		}};
		Session.getActiveCanvas().setMouseEnabled(true);
		HotKeyManager.setInputEnabled(true);
	    }	    
	});

    }

    private void move(final Point origin, final Point target) {
	final int step = 5;
	final int delay = 5;

	if(origin.getX() < target.getX()) {
	    for(int x = origin.getX() ; x < target.getX() ; x+=step) {
		final int xx = x;
		new Scheduler.Task(this, this, delay) {@Override public void process() {
		    Mouse.move(new Point(xx, origin.getY()), NativeEvent.BUTTON_LEFT, false, false, false, false);
		}};
	    }
	} else {
	    for(int x = origin.getX() ; x > target.getX() ; x-=step) {
		final int xx = x;
		new Scheduler.Task(this, this, delay) {@Override public void process() {
		    Mouse.move(new Point(xx, origin.getY()), NativeEvent.BUTTON_LEFT, false, false, false, false);
		}};
	    }
	}
	if(origin.getY() < target.getY()) {
	    for(int y = origin.getY() ; y < target.getY() ; y+=step) {
		final int yy = y;
		new Scheduler.Task(this, this, delay) {@Override public void process() {
		    Mouse.move(new Point(target.getX(), yy), NativeEvent.BUTTON_LEFT, false, false, false, false);
		}};
	    }
	} else {
	    for(int y = origin.getY() ; y > target.getY() ; y-=step) {
		final int yy = y;
		new Scheduler.Task(this, this, delay) {@Override public void process() {
		    Mouse.move(new Point(target.getX(), yy), NativeEvent.BUTTON_LEFT, false, false, false, false);
		}};
	    }
	}
    }
}

