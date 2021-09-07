public class Attack {
	public String name;
	public String likelihood_of_attack;
	public String typical_severity;
	public String skills_required;
	public String prerequisites;
	public String mitigations;
	

	public Attack(String name, String likelihood_of_attack,String mitigations,String prerequisites, String skills_required,String typical_severity ) {
		this.name = name;
		this.likelihood_of_attack = likelihood_of_attack;
		this.mitigations = mitigations;
		this.prerequisites = prerequisites;
		this.skills_required = skills_required;
		this.typical_severity = typical_severity;
	}

	public String getLikelihood_of_attack() {
		return likelihood_of_attack;
	}

	public void setLikelihood_of_attack(String likelihood_of_attack) {
		this.likelihood_of_attack = likelihood_of_attack;
	}

	public String getTypical_severity() {
		return typical_severity;
	}

	public void setTypical_severity(String typical_severity) {
		this.typical_severity = typical_severity;
	}

	public String getSkills_required() {
		return skills_required;
	}

	public void setSkills_required(String skills_required) {
		this.skills_required = skills_required;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public Attack() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    
}
