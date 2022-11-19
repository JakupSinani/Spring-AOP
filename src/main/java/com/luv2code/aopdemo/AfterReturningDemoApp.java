package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.Account;
import com.luv2code.aopdemo.dao.AccountDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AfterReturningDemoApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(DemoConfig.class);

        //  get the bean from spring container
        AccountDAO theAccountDAO=context.getBean("accountDAO",AccountDAO.class);

        //  call method to find the accounts
        List<Account> theAccounts=theAccountDAO.findAccounts();

        //  diplay the accounts
        System.out.println("\n\nMain Program: AfterReturningDemoApp");
        System.out.println("----");

        System.out.println(theAccounts);

        System.out.println("\n ");

        //  close the context
        context.close();
    }
}
