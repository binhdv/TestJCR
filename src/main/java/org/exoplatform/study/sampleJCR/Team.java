package org.exoplatform.study.sampleJCR;

import java.util.Map;

import org.chromattic.api.annotations.Create;
import org.chromattic.api.annotations.OneToMany;
import org.chromattic.api.annotations.PrimaryType;
import org.chromattic.api.annotations.Property;

@PrimaryType(name="lab:team")
public abstract class Team {

	@Property(name = "lab:teamName")
	public abstract String getTeamName();  

	 
	public abstract void setTeamName(String name);
	
	@OneToMany
	public abstract Map<String,Member> getMembers();
	
	public Member getMember(String id) {
		return getMembers().get(id);
	}
		  	 
	public void addBook(Member m) {
		getMembers().put(m.getName(), m);
	}
	@Create
	public abstract Member createMember();
}
