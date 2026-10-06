package org.example.firstspringbootproject.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "branch")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "branch_name", length = 100)
    private String branchName;

    @Column(name = "ifsc_code", length = 15, unique = true)
    private String ifscCode;

    @Column(name = "city", length = 60)
    private String city;

    @Column(name = "address", length = 200)
    private String address;


    public Branch() {
    }

    public Branch(String branchName, String ifscCode,
                  String city, String address) {
        this.branchName = branchName;
        this.ifscCode = ifscCode;
        this.city = city;
        this.address = address;
    }


    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @OneToMany(mappedBy = "branch")
    private List<Customer> customer;

}