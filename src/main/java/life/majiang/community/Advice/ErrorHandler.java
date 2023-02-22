package life.majiang.community.Advice;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import life.majiang.community.dto.ResultDto;
import life.majiang.community.exception.CEErrorCode;
import life.majiang.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    Object handle(HttpServletRequest request, Throwable ex, Model model)
    {
        String contentType = request.getContentType();
        if ("application/json".equals(contentType))
        {//返回JSON
            if (ex instanceof CustomizeException)
            {
               return ResultDto.errorOf((CustomizeException) ex);
            }
            else
            {
               return ResultDto.errorOf(CEErrorCode.SYSTEM_ERROR);
            }
        }
        else
        {
            //返回错误页面
            if (ex instanceof CustomizeException)
            {
                model.addAttribute("message",ex.getMessage());
            }
            else
            {
                model.addAttribute("message","服务器冒烟了，请稍后试试! ! !");
            }

            return new ModelAndView("error");
        }

    }


}
