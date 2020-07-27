package pool;

public class ConnectionPoolBusyException extends RuntimeException{

    public ConnectionPoolBusyException(){}

    public ConnectionPoolBusyException(String message){
        super(message);
    }

}
