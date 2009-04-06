/**
 * 
 */
package com.objetdirect.gwt.umlapi.client.artifacts.links.classlinks;

import java.util.LinkedHashMap;

import com.google.gwt.user.client.Command;
import com.objetdirect.gwt.umlapi.client.artifacts.classArtifactComponent.ClassArtifact;
import com.objetdirect.gwt.umlapi.client.artifacts.links.RelationArtifact;
import com.objetdirect.gwt.umlapi.client.umlcomponents.Relation;
import com.objetdirect.gwt.umlapi.client.umlcomponents.Relation.RelationKind;

/**
 * @author florian
 *
 */
public class AssociationLinkArtifact extends RelationArtifact {

    public AssociationLinkArtifact(ClassArtifact left, ClassArtifact right) {
        super(left, right);
        relation = new Relation(RelationKind.ASSOCIATION);
        relation.setLeftCardinality("0..*");
        relation.setRightCardinality("0..*");
        this.adornmentLeft = LinkAdornment.WIRE_ARROW;
        this.adornmentRight = LinkAdornment.NONE;
        this.style = LinkStyle.SOLID;
    }
    /* (non-Javadoc)
     * @see com.objetdirect.gwt.umlapi.client.artifacts.links.RelationshipLinkArtifact#getRightMenu()
     */
    @Override
    public LinkedHashMap<Command, String> getRightMenu() {
        LinkedHashMap<Command, String> rightMenu = new LinkedHashMap<Command, String>();
        Command doNothing = new Command() {
            public void execute() {
            }
        };
        rightMenu.put(null, "Association " + leftClassArtifact.getClassName() +
                " " + adornmentLeft.getShape().getIdiom() +  "-" + adornmentRight.getShape().getIdiom(true) + " "
                + rightClassArtifact.getClassName());
        return rightMenu;
    }
}
