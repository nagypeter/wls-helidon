package com.oracle.oow19.wls.trustbank;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@ManagedBean
@SessionScoped
public class EmployeeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4417676256979648115L;

	private List<Employee> employeeList = new ArrayList<>();

	private Employee selectedEmployee;

	private String msg;

	private static final Logger logger = Logger.getLogger(EmployeeBean.class.getName());

	@PostConstruct
    private void postConstruct () {
		for (int i = 1; i < 5; i++) {
			Employee employee = new Employee();
			employee.setId(Integer.toString(i));
			employee.setName("John Bravo");
			employee.setPhoneNumber(String.format("%s-%s-%s", "001", "12344", "4373477"));
			employee.setAddress("Main Street" + "," + "Budapest");
			employeeList.add(employee);
		}
	}

	public List<Employee> getEmployeeList() {
		logger.info("getEmployeeList:" + employeeList);
		return employeeList;
	}

	public Employee getSelectedEmployee() {
		logger.info("getSelectedEmployee:" + selectedEmployee);
		return selectedEmployee;
	}

	public void setSelectedEmployee(Employee selectedEmployee) {
		logger.info("setSelectedEmployee:" + selectedEmployee);
		if (selectedEmployee != null) {
			this.msg = "Selected employee ID: " + selectedEmployee.getId() + " - " + selectedEmployee.getName();
			logger.info("setSelectedEmployee MSG:" + this.msg);
			this.selectedEmployee = selectedEmployee;
		}
	}

	public String getMsg() {
		logger.info("getMsg:" + msg);
		return msg;
	}

}
