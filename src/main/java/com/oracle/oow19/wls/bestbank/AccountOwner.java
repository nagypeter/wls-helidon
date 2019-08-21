package com.oracle.oow19.wls.bestbank;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AccountOwner {
    private String id;
    private String firstname;
    private String lastname;
    private String dateofbirth;
    private String ssn;
    private String score;

    public String getId () {
        return id;
    }

    public void setId (String id) {
        this.id = id;
    }

    public String getFirstname () {
        return firstname;
    }

    public void setFirstname (String firstName) {
        this.firstname = firstName;
    }
    
    public String getLastname () {
        return lastname;
    }

    public void setLastname (String lastName) {
        this.lastname = lastName;
    }
    
    public String getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(String birthDate) {
		this.dateofbirth = birthDate;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	

	public String getScore() {
		return score;
	}

	public void setScore(String creditScore) {
		this.score = creditScore;
	}

	@Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AccountOwner other = (AccountOwner) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }
}
