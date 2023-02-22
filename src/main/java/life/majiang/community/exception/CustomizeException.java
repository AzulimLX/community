package life.majiang.community.exception;



public class CustomizeException extends RuntimeException{
   private String message;
   private Integer code;

  public CustomizeException(String message)
  {
      this.message = message;
  }

  public CustomizeException(CEErrorCode errorCode)
  {
      this.code=errorCode.getCode();
      this.message=errorCode.getMessage();
  }

  public String getMessage()
  {
      return message;
  }

    public Integer getCode() {
        return code;
    }
}
