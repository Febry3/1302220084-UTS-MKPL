package com.mycompany.taxcalculator_1302220084;

///**
// * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
// * 
// * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
// * 
// * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
// * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
// * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
// * 
// */
public class TaxFunction {

    private final static int MAX_CHILD_NOT_TAXED = 3;
    private final static int MAX_MONTH_IN_YEAR = 12;
    private final static double TAX_RATE = 12;
    private final static int ADDITION_PER_CHILD = 1500000;
    private final static int NON_TAXABLE_INCOME = 54000000;
    private final static int NON_TAXABLE_ADDITION_FOR_NON_MARRIED = 54000000;

    public static int calculateTax(Employee employee) {
        int tax = 0;
        int numberOfChildren = employee.getChildren().size() > 3 ? employee.getChildren().size() : MAX_CHILD_NOT_TAXED;

        if (employee.getMonthWorkingInYear() > MAX_MONTH_IN_YEAR) {
            System.err.println("More than 12 month working per year");
        }

        if (employee.getSpouse() != null) {
            tax = (int) Math.round(TAX_RATE * (((employee.getMonthlySalary() + employee.getOtherMonthlyIncome()) * employee.getMonthWorkingInYear()) - employee.getAnnualDeductible() - (NON_TAXABLE_INCOME + NON_TAXABLE_ADDITION_FOR_NON_MARRIED + (numberOfChildren * ADDITION_PER_CHILD))));
        } else {
            tax = (int) Math.round(TAX_RATE * (((employee.getMonthlySalary() + employee.getOtherMonthlyIncome()) * employee.getMonthWorkingInYear()) - employee.getAnnualDeductible() - NON_TAXABLE_INCOME));
        }

        if (tax < 0) return 0;
        return tax;
    }
}
