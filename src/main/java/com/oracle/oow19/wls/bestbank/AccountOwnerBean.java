package com.oracle.oow19.wls.bestbank;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.github.javafaker.Faker;

@ManagedBean
@SessionScoped
public class AccountOwnerBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4417676256979648115L;

	private List<AccountOwner> accountOwnerList = new ArrayList<>();

	private AccountOwner selectedAccountOwner;

	private String msg;

	private static final Logger logger = Logger.getLogger(AccountOwnerBean.class.getName());

	@PostConstruct
    private void postConstruct () {
		Random random = new Random();
		Faker faker = new Faker();
		for (int i = 1; i < 5; i++) {
			AccountOwner owner = new AccountOwner();
			owner.setId(faker.finance().iban("DE"));
			owner.setName(faker.name().fullName());
			owner.setBirthDate(new SimpleDateFormat("yyyy-mm-dd").format(faker.date().birthday()));
			owner.setSsn(String.format("%s-%s-%s", random.nextInt((999 - 100) + 1) + 100, 
					random.nextInt((99 - 10) + 1) + 10, 
					random.nextInt((9999 - 1000) + 1) + 1000));
			accountOwnerList.add(owner);
		}
	}

	public List<AccountOwner> getAccountOwnerList() {
		return accountOwnerList;
	}

	public AccountOwner getSelectedAccountOwner() {
		return selectedAccountOwner;
	}

	public void setSelectedAccountOwner(AccountOwner selectedOwner) {
		if (selectedOwner != null) {
			this.msg = "Selected employee ID: " + selectedOwner.getId() + " - " + selectedOwner.getName();
			this.selectedAccountOwner = selectedOwner;
		}
	}

	public String getMsg() {
		return msg;
	}

}
