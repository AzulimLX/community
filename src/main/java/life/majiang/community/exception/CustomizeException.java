package life.majiang.community.exception;



public class CustomizeException extends RuntimeException{
   private String message;

  public CustomizeException(String message)
  {
      this.message = message;
  }

  public CustomizeException(CEErrorCode errorCode)
  {
      this.message=errorCode.getMessage();
  }

  public String getMessage()
  {
      return message;
  }



}
