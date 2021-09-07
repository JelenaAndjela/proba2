package CaseBasedReasoning;

import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

public class Model implements CaseComponent{


	private String name;
	private String likelihood;
	private String severity;
	private String skills;
	private String prerequisites;
	private String mitigations;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLikelihood() {
		return likelihood;
	}
	public void setLikelihood(String likelihood) {
		this.likelihood = likelihood;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public String getPrerequisites() {
		return prerequisites;
	}
	public void setPrerequisites(String prerequisites) {
		this.prerequisites = prerequisites;
	}
	public String getMitigations() {
		return mitigations;
	}
	public void setMitigations(String mitigations) {
		this.mitigations = mitigations;
	}
	public Model(String name, String likelihood, String severity, String skills,String prerequisites,
				 String mitigations) {
		super();
		this.name = name;
		this.likelihood = likelihood;
		this.severity = severity;
		this.skills = skills;
		this.prerequisites = prerequisites;
		this.mitigations = mitigations;
	}
	public Model() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Model [name=" + name + ", likelihood=" + likelihood + ", severity=" + severity +   ", skills=" + skills  + ", prerequisites=" + prerequisites + ", mitigations=" + mitigations
				+ "]";
	}
	@Override
	public Attribute getIdAttribute() {
		// TODO Auto-generated method stub
		return null;
	}




}