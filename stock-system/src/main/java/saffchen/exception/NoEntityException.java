package saffchen.exception;

/**
 * @author saffchen created on 07.07.2022
 * @project JRM-Java-Stock-System
 */
public class NoEntityException extends Exception{
    public NoEntityException(Long id) {
        System.out.printf("Object {%s} is not found", id);
    }
}
