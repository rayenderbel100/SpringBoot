package tn.esprit.arctic.championnat.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    // Pointcut pour toutes les méthodes dans le package Services
    @Pointcut("execution(* tn.esprit.arctic.championnat.Services.*.*(..))")
    public void serviceMethods() {
    }

    // Exécuté avant l'appel de la méthode
    @Before("serviceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Avant l'exécution de la méthode : " + joinPoint.getSignature().getName());
    }

    // Exécuté après l'appel de la méthode (qu'elle réussisse ou échoue)
    @After("serviceMethods()")
    public void logAfter(JoinPoint joinPoint) {
        log.info("Après l'exécution de la méthode : " + joinPoint.getSignature().getName());
    }

    // Exécuté après un retour réussi de la méthode
    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("Méthode " + joinPoint.getSignature().getName() + " exécutée avec succès.");
        if (result != null) {
            log.info("Résultat retourné : " + result.toString());
        }
    }

    // Exécuté si la méthode lève une exception
    @AfterThrowing(pointcut = "serviceMethods()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        log.error("Exception dans la méthode : " + joinPoint.getSignature().getName() + " - Message : " + exception.getMessage());
    }

    // Exécuté autour de la méthode (permet de contrôler son exécution, mesurer le temps, etc.)
    @Around("serviceMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        
        log.info("Début de l'exécution (Around) : " + joinPoint.getSignature().getName());
        
        // Exécuter la méthode cible
        Object result = joinPoint.proceed();
        
        long elapsedTime = System.currentTimeMillis() - start;
        log.info("Fin de l'exécution (Around) : " + joinPoint.getSignature().getName() + " - Temps d'exécution : " + elapsedTime + " ms");
        
        return result;
    }
}
