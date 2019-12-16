package top.pdcasystem.pdcasystem.Aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class AlphaTestAspect {

    @Pointcut("execution(* top.pdcasystem.pdcasystem.Service.*.*(..))")
    public void pointcut(){

    }

    @Before("pointcut()")
    public void beforerunning(){
        System.out.println("before");
    }

    @After("pointcut()")
    public void afterrunning(){
        System.out.println("after");
    }

    @AfterReturning("pointcut()")
    public void afterreturn(){
        System.out.println("afterreturn");
    }

    @AfterThrowing("pointcut()")
    public void afterthrow(){
        System.out.println("afterthrow");
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws  Throwable {
        System.out.println("aroundbefore");
        Object object = joinPoint.proceed();
        System.out.println("aroundafter");
        return object;
    }
}
