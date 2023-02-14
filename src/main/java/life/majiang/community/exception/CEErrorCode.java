package life.majiang.community.exception;

public enum CEErrorCode implements CErrorCode{


    QUESTION_NOT_FOUND("你找的问题不存在捏,要不要换一个试试");

    @Override
    public String getMessage() {
        return message;
    }

    private String message;

    CEErrorCode(String message)
    {
        this.message=message;
    }
}
