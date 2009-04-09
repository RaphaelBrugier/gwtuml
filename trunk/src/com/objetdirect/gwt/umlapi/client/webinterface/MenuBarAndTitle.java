package com.objetdirect.gwt.umlapi.client.webinterface;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.MenuBar;

public class MenuBarAndTitle {

    private String name;
    private final MenuBar subMenu;

    public MenuBarAndTitle() {
	subMenu = new MenuBar(true);
    }

    /**
     * Add an item to the Menu Bar
     * 
     * @param text
     *            the menu caption
     * @param cmd
     *            the command to run
     */
    public void addItem(final String text, final Command cmd) {
	subMenu.addItem(text, cmd);
    }

    /**
     * Add a sub menu
     * 
     * @param text
     *            the menu caption
     * @param popup
     *            the submenu
     */
    public void addItem(final String text, final MenuBar popup) {
	subMenu.addItem(text, popup);
    }

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @return the subMenu
     */
    public MenuBar getSubMenu() {
	return subMenu;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
	this.name = name;
    }

}
