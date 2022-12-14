package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.Account;
import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainDemoApp {

    public static void main(String[] args) {

        // read spring config java class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        // get the bean from spring container
        AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);

        //  get the bean from spring container
        MembershipDAO theMembershipDAO=context.getBean("membershipDAO",MembershipDAO.class);

        // call the business method again
        Account myAccount=new Account();
        myAccount.setName("John");
        myAccount.setLevel("Silver");
        theAccountDAO.addAccount(myAccount,true);
        theAccountDAO.doWork();

        //  call the accountdao getter/setter method
        theAccountDAO.setName("foobar");
        theAccountDAO.setServiceCode("silver");

        String name= theAccountDAO.getName();
        String code= theAccountDAO.getServiceCode();

        //  call the membershipDAO bussines method
        theMembershipDAO.addSillyMember();
        theMembershipDAO.goToSleep();

        // close the context
        context.close();
    }

}
