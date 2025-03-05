package com.microservices.book_service.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table (name = "book")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String author;
    
    @Column(name = "launch_date" , nullable = false)
    @Temporal(TemporalType.DATE)
    private Date launchDate;
    
    @Column(nullable = false)
    private BigDecimal price;
    
    @Column(nullable = false)
    private String title;
    
}
