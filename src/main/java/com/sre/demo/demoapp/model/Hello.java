package com.sre.demo.demoapp.model;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter 
@NoArgsConstructor
@ToString
public class Hello {
    Date date;
    String message;
}