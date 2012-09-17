package org.exoplatform.study.sampleJCR;

import org.chromattic.api.annotations.ManyToOne;
import org.chromattic.api.annotations.Name;
import org.chromattic.api.annotations.PrimaryType;
import org.chromattic.api.annotations.Property;

@PrimaryType(name = "lab:member")
public abstract class Member {

	@Name
	public abstract String getName();
	
	@Property(name = "lab:name")
	public abstract String getMemberName();  

	 
	public abstract void setMemberName(String name);
	
	@Property(name = "lab:age")
	public abstract String getMemberAge();  

	 
	public abstract void setMemberAge(int age);
	
	@ManyToOne
	public abstract Team getTeam();
}
