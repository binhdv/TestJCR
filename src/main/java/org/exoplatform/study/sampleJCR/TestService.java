package org.exoplatform.study.sampleJCR;

import org.chromattic.api.ChromatticSession;
import org.exoplatform.commons.chromattic.ChromatticManager;

public class TestService {
	
	ChromatticManager chrom;
	
	public TestService(ChromatticManager chrom) {
	    this.chrom = chrom;
	  }

	  /**
	   * Get Chromattic manager object
	   * @return the cManager
	   */
	  public ChromatticManager getcManager() {
	    return chrom;
	  }
	  
	  /**
	   * Get Chromattic session
	   * @return chromattic session
	   */
	  public ChromatticSession getSession() {
	    return getcManager().getLifeCycle("lab").getContext().getSession();
	  }
	  
	  

}
