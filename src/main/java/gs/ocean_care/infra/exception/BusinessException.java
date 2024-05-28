package gs.ocean_care.infra.exception;

public class BusinessException extends  RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}