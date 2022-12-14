package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.dao.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.mapping.Join;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
    //  @After will run for success or failure (finally)
    @After("execution (* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint){

        //  print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>> Executing @After (finally) on method: " + method);
    }
    @AfterThrowing(pointcut = "execution (* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",throwing = "theExc")
    public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint,Throwable theExc){

        //  print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>> Executing @AfterThrowing on method: " + method);

        //  log the exception
        System.out.println("\n=====>> The exception is: " + theExc);
    }

    //  add a new advice for @AfterReturning on the findAccounts method
    @AfterReturning(pointcut = "execution (* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))", returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {

        //   print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>> Executing @AfterReturning on method: " + method);

        //   print out the results of the method call
        System.out.println("\n=====>> result is: " + result);

        //   let's post-process the data ... let's modify it

        //   convert the account names to uppercase
        convertAccountNamesToUppercase(result);
        System.out.println("\n=====>> result is: "+result);
    }

    private void convertAccountNamesToUppercase(List<Account> result) {

        // loop through accounts
        for (Account tempAccount : result) {

            //  get uppercase version of name
            String theUpperName = tempAccount.getName().toUpperCase();
            //  update the name on the account
            tempAccount.setName(theUpperName);
        }

    }

    @Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {

        System.out.println("\n=====>>> Executing @Before advice on method()");

        //  display the method signature
        MethodSignature methodSign = (MethodSignature) theJoinPoint.getSignature();

        System.out.println("Method: " + methodSign);
        //  display method arguments

        //  get args
        Object[] args = theJoinPoint.getArgs();

        // loop throw args
        for (Object tempArgs : args) {
            System.out.println(tempArgs);

            if (tempArgs instanceof Account) {

                //  downcast and print Account specific stuff
                Account theAccount = (Account) tempArgs;

                System.out.println("account name: " + theAccount.getName());
                System.out.println("account level: " + theAccount.getLevel());
            }
        }
    }

}

