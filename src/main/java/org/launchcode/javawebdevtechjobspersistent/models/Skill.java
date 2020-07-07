package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Skill extends AbstractEntity {

    @Size(min = 1, max = 100)
    @NotBlank
    private String verboseSkill;

    public String getVerboseSkill() {
        return verboseSkill;
    }

    public void setVerboseSkill(String verboseSkill) {
        this.verboseSkill = verboseSkill;
    }

    //no-arg constructor for hibernate
    public Skill() {}
}