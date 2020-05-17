package response;
import java.util.List;

public class TransactionHistoryResponse {

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
        private List<Txn> txn;

        public void setTxn(List<Txn> txn){
            this.txn = txn;
        }
        public List<Txn> getTxn(){
            return this.txn;
        }

        public static class Txn
        {
            private String txnId;



            private String txnDate;

            private int amount;

            public void setTxnId(String txnId){
                this.txnId = txnId;
            }
            public String getTxnId(){
                return this.txnId;
            }

            public void setAmount(int amount){
                this.amount = amount;
            }
            public int getAmount(){
                return this.amount;
            }

            public String getTxnDate() {
                return txnDate;
            }

            public void setTxnDate(String txnDate) {
                this.txnDate = txnDate;
            }

        }



    }

}
