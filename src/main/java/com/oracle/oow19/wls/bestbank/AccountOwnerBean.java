package com.oracle.oow19.wls.bestbank;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.github.javafaker.Faker;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

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
			/*owner.setBirthDate(String.format("%s.%s.%s", "19" + random.nextInt(9), 
					Integer.toString(random.nextInt((99 - 80) + 1) + 80), 
					String.format("%02d", random.nextInt(29))));
			*/
			Calendar cal = Calendar.getInstance();
			cal.set(1960, 1, 1);
			Date startDate = cal.getTime();
			cal.set(2001, 12, 31);
			Date endDate = cal.getTime();
			String birthDate = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(faker.date().between(startDate, endDate));   
			owner.setBirthDate(birthDate);
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
		logger.info("setSelectedAccountOwner:" + selectedOwner);
		if (selectedOwner != null) {
			this.msg = "Selected employee ID: " + selectedOwner.getId() + " - " + selectedOwner.getName();
			logger.info("setSelectedEmployee MSG:" + this.msg);
			this.selectedAccountOwner = selectedOwner;
		}
	}

	public String getMsg() {
		logger.info("getMsg:" + msg);
		return msg;
	}

}
