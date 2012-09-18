package org.exoplatform.study.test;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import junit.framework.TestCase;

import org.chromattic.api.Chromattic;
import org.chromattic.api.ChromatticSession;
import org.exoplatform.commons.chromattic.ChromatticManager;
import org.exoplatform.container.StandaloneContainer;
import org.exoplatform.container.component.RequestLifeCycle;
import org.exoplatform.services.jcr.RepositoryService;
import org.exoplatform.services.jcr.ext.app.SessionProviderService;
import org.exoplatform.services.jcr.ext.common.SessionProvider;
import org.exoplatform.study.sampleJCR.Member;
import org.exoplatform.study.sampleJCR.Team;

public class TestJCR extends TestCase {
   
 /**
 * 
 */
protected static StandaloneContainer  container;
  
  static {
    initContainer();
  }
  
 /**
 * Initialize the container
 */
private static void initContainer() {
    try {
      String containerConf = Thread.currentThread()
                                   .getContextClassLoader()
                                   .getResource("conf/standalone/configuration.xml")
                                   .toString();
      StandaloneContainer.addConfigurationURL(containerConf);
      container = StandaloneContainer.getInstance();
      
    } catch (Exception e) {
    	throw new RuntimeException("Failed to initialize standalone container: " + e.getMessage(), e);
    }
  }

/**
 * Start current container
 */
protected void setUp() throws Exception {
	RequestLifeCycle.begin(container); 
	
}
  

/**
 * Clear current container
 */
protected void tearDown() throws Exception {
  RequestLifeCycle.end();
}

 /**
 * Test add new team and new member
 * @throws RepositoryException
 */
public void testAddTeamMember() throws RepositoryException{
	    
	  RepositoryService repositoryService = 
			  (RepositoryService) container.getComponentInstanceOfType(RepositoryService.class);
      SessionProviderService sessionProviderService = 
    		  (SessionProviderService) container.getComponentInstanceOfType(SessionProviderService.class);
      SessionProvider sessionProvider = 
    		  sessionProviderService.getSystemSessionProvider(null);
      Session session = 
    		  sessionProvider.getSession("binhdv", repositoryService.getCurrentRepository());
      Node root = session.getRootNode();
      Node team = root.addNode("SOC","lab:team");
      team.setProperty("lab:teamName", "SOCIAL");
      Node binh = team.addNode("binh", "lab:member");
      binh.setProperty("lab:name", "binhdv");
      binh.setProperty("lab:age", 29);
      binh.setProperty("lab:team", team.getName());
      session.save();
      System.out.println("=============="+team.getName()+"/"+binh.getPath()+"/"+binh.getProperty("lab:age").getLong());
      assertNotNull(team);
      session.logout();
      }

/**
 * Test add new team and new member using Chromattic
 * @throws RepositoryException
 */
public void testAddTeamMemberChromattic() throws RepositoryException{  
	assertNotNull(1);	
	ChromatticManager chromatticManager = (ChromatticManager)container.getComponentInstanceOfType(ChromatticManager.class);
	Chromattic chromattic = chromatticManager.getLifeCycle("binhdv").getChromattic();
	assertNotNull(chromatticManager);
	ChromatticSession chrome = chromattic.openSession();
	Team exo = chrome.findByPath(Team.class, "Exo");
	if (exo == null){
		exo=chrome.create(Team.class, "Exo");
		chrome.save();
	}
	assertNotNull(exo);
	Member m = exo.createMember();
	m.setMemberName("binh");
	m.setMemberAge(29);
	assertEquals(m.getName(), "binh");
	chrome.save();
	chrome.close();
}
}
