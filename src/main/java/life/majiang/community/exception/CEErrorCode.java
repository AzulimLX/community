package life.majiang.community.exception;

public enum CEErrorCode implements CErrorCode{
    QUESTION_NOT_FOUND(2001,"你找的问题不存在捏,要不要换一个试试"),
    TARGET_PARAM_NOT_FOUND(2002,"未选择任何问题或评论进行回复(可能被删除了)"),
    NO_LOGIN(2003,"未登录不能进行评论，请先登录"),
    SYSTEM_ERROR(2004,"服务器冒烟了，要不您稍后重试？？？"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"回复的评论已经不存在了，要不换一个试试？");


    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
    private Integer code;
    private String message;


    CEErrorCode(Integer code,String message)

    {   this.code=code;
        this.message=message;
    }
}
