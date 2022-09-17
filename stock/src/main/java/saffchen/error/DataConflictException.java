package saffchen.error;

/**
 * @author alex_jd on 8/28/22
 * @project JRM-Java-Stock-System
 */
public class DataConflictException extends RuntimeException {
    public DataConflictException(String msg) {
        super(msg);
    }
}
