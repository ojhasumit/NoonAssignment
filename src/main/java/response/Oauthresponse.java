package response;

public class Oauthresponse {
    private boolean success;

    private Data data;

    private String errorMessage;

    public void setSuccess(boolean success){
        this.success = success;
    }
    public boolean getSuccess(){
        return this.success;
    }
    public void setData(Data data){
        this.data = data;
    }
    public Data getData(){
        return this.data;
    }
    public void setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }
    public String getErrorMessage(){
        return this.errorMessage;
    }


    public static class Data
    {
        private String token;

        public void setToken(String token){
            this.token = token;
        }
        public String getToken(){
            return this.token;
        }
    }
}
