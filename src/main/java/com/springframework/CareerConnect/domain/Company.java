//package com.springframework.CareerConnect.domain;
//
//import jakarta.persistence.*;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.Set;
//
//@Entity
//@NoArgsConstructor
//@Getter
//@Setter
//@Builder
//public class Company{
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long companyId;
//
//    private String companyName;
//    private String companyDescription;
//
//    @ManyToMany
//    @JoinTable(name = "company_address",
//            joinColumns = @JoinColumn(name = "company_id"),
//            inverseJoinColumns = @JoinColumn(name = "address_id"))
//    private Set<Address> address;
//
//    //private Set<JobPosting> jobPostings;
//
//
//    public Company(Long companyId, String companyName, String companyDescription) {
//        this.companyId = companyId;
//        this.companyName = companyName;
//        this.companyDescription = companyDescription;
//    }
//}
