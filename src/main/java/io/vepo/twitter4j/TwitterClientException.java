package io.vepo.twitter4j;

public class TwitterClientException extends RuntimeException {

    private static final long serialVersionUID = -7952732815144961066L;

    public enum CauseType {
        IO_EXCEPTION, INVALID_RESPONSE, INVALID_CREDENTIALS, SERVER_ERROR, SERIALIZATION_ERROR
    }

    private final CauseType causeType;;

    public TwitterClientException(CauseType causeType, Throwable cause) {
        super(cause);
        this.causeType = causeType;
    }

    public TwitterClientException(CauseType causeType) {
        this(causeType, null);
    }

    public CauseType getCauseType() {
        return causeType;
    }

}
