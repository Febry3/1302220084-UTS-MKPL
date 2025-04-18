package com.mycompany.taxcalculator_1302220084;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Employee {

    private String employeeId;
    private PersonalInfo personalInfo;

    private LocalDate joinedDate;
    private int monthWorkingInYear;

    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;

    private Spouse spouse;
    private List<Child> children;

    public Employee(String employeeId, PersonalInfo personalInfo, LocalDate joinedDate) {
        this.employeeId = employeeId;
        this.personalInfo = personalInfo;
        this.joinedDate = joinedDate;
        this.children = new LinkedList<>();
    }

//	/**
//	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
//	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
//	 */
    public void setMonthlySalary(int grade) {
        int initialSalary = 0;
        switch (grade) {
            case 1:
                initialSalary = 3000000;
                break;
            case 2:
                initialSalary = 5000000;
                break;
            case 3:
                initialSalary = 7000000;
                break;
            default:
                break;
        }
        
        this.monthlySalary = personalInfo.isForeigner() ? (int)(initialSalary * 1.5) : initialSalary ;
        if (personalInfo.isForeigner()) {

        }
    }

    public void setAnnualDeductible(int deductible) {
        this.annualDeductible = deductible;
    }

    public void setAdditionalIncome(int income) {
        this.otherMonthlyIncome = income;
    }

    public void setSpouse(Spouse spouse) {
        this.spouse = spouse;
    }

    public void addChild(Child child) {
        children.add(child);
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public LocalDate getJoinedDate() {
        return joinedDate;
    }

    public int getMonthWorkingInYear() {
        return monthWorkingInYear;
    }

    public int getMonthlySalary() {
        return monthlySalary;
    }

    public int getOtherMonthlyIncome() {
        return otherMonthlyIncome;
    }

    public int getAnnualDeductible() {
        return annualDeductible;
    }

    public Spouse getSpouse() {
        return spouse;
    }

    public List<Child> getChildren() {
        return children;
    }

    public int getAnnualIncomeTax() {

        //Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
        LocalDate date = LocalDate.now();

        if (date.getYear() == this.joinedDate.getYear()) {
            monthWorkingInYear = date.getMonthValue() - this.joinedDate.getMonthValue();
        } else {
            monthWorkingInYear = 12;
        }

        return TaxFunction.calculateTax(this);
    }
}
