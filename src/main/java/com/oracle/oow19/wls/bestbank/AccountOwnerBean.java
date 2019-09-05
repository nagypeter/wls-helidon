package com.oracle.oow19.wls.bestbank;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.github.javafaker.Faker;

@ManagedBean
@SessionScoped
public class AccountOwnerBean implements Serializable {

	private static final long serialVersionUID = 4417676256979648115L;
	
	private static final Logger logger = Logger.getLogger(AccountOwnerBean.class.getName());

	private List<AccountOwner> accountOwnerList = new ArrayList<>();

	private AccountOwner selectedAccountOwner;
	
	private String creditScoreUrl;

	@PostConstruct
    private void postConstruct () {
		Random random = new Random();
		Faker faker = new Faker();
		for (int i = 1; i < 15; i++) {
			AccountOwner owner = new AccountOwner();
			owner.setId(faker.finance().iban("DE"));
			owner.setFirstname(faker.name().firstName());
			owner.setLastname(faker.name().lastName());
			owner.setDateofbirth(new SimpleDateFormat("mm/dd/yyyy").format(faker.date().birthday()));
			owner.setSsn(String.format("%s-%s-%s", random.nextInt((999 - 100) + 1) + 100, 
					random.nextInt((99 - 10) + 1) + 10, 
					random.nextInt((9999 - 1000) + 1) + 1000));
			accountOwnerList.add(owner);
		}
		
		/* REMOVE THIS LINE TO READ CREDITSCORE ENDPOINT URL PROPERTY
		Properties props = new Properties();
		try {
			props.load(this.getClass().getResourceAsStream("/app.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.creditScoreUrl = props.getProperty("creditscore.url");
		REMOVE THIS LINE TO READ CREDITSCORE ENDPOINT URL PROPERTY */
	}

	public List<AccountOwner> getAccountOwnerList() {
		return accountOwnerList;
	}
	
	public void setSelectedAccountOwner(AccountOwner selectedOwner) {
		this.selectedAccountOwner = selectedOwner;
	}

	/* REMOVE THIS LINE TO ACTIVATE CREDITSCORE MICROSERVICES APPLICATION INVOCATION	
	public AccountOwner getSelectedAccountOwner() {
		if (this.selectedAccountOwner != null) {
			this.selectedAccountOwner = getCreditScore(this.selectedAccountOwner);
		}
		return this.selectedAccountOwner;
	}

	private AccountOwner getCreditScore (AccountOwner owner) {
		
		WebTarget webTarget = ClientBuilder.newClient().target(this.creditScoreUrl);

		Response response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(owner, MediaType.APPLICATION_JSON));
		
        if (response.getStatus() != 200) {
            logger.warning("Failed : HTTP error code : " + response.getStatus() + ", " + response.readEntity(String.class));
            return owner;
        }
 
        owner = response.readEntity(AccountOwner.class);
        
		response.close();
 
		return owner;
	}
	REMOVE THIS LINE TO ACTIVATE CREDITSCORE MICROSERVICES APPLICATION INVOCATION */

}
