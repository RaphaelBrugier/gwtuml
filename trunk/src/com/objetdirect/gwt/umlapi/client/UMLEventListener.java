/**
 * 
 */
package com.objetdirect.gwt.umlapi.client;

import com.objetdirect.gwt.umlapi.client.artifacts.UMLArtifact;
import com.objetdirect.gwt.umlapi.client.artifacts.links.LinkArtifact;

/**
 * @author florian
 * 
 */
public interface UMLEventListener {

    public boolean onNewLink(LinkArtifact linkArtifact);

    public boolean onNewUMLArtifact(UMLArtifact umlArtifact);

}
