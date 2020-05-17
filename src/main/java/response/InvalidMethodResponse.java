package response;

public class InvalidMethodResponse {
    private Long timestamp;

    private int status;

    private String error;

    private String exception;

    private String message;

    private String path;

    public void setTimestamp(Long timestamp){
        this.timestamp = timestamp;
    }
    public Long getTimestamp(){
        return this.timestamp;
    }
    public void setStatus(int status){
        this.status = status;
    }
    public int getStatus(){
        return this.status;
    }
    public void setError(String error){
        this.error = error;
    }
    public String getError(){
        return this.error;
    }
    public void setException(String exception){
        this.exception = exception;
    }
    public String getException(){
        return this.exception;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
    public void setPath(String path){
        this.path = path;
    }
    public String getPath(){
        return this.path;
    }

}
