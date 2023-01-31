package life.majiang.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageDto {
    private List<ArticleDto> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private  int totalPage;

    public void setPagination(long totalCount,int page,int size)
    {
        //首先计算出它有多少页可以展示

        if (totalCount%size==0)
        {
            totalPage= (int)totalCount /size;
        }else {
            totalPage = (int)totalCount/size +1;
        }

        if (page<1)
            page=1;
        if (page>totalPage)
            page=totalPage;

        this.page=page;

            pages.add(page);
        //判断显示页码
        for (int i=1;i<=3;i++)
        {
            if (page-i>0)
                pages.add(0,page-i);//index：要插入元素的索引，element：要插入的元素
            if (page+i<=totalPage)
                pages.add(page+i);
        }


        //判断什么时候有返回上一页的标签
        if (page == 1)
        {
            showPrevious=false;
        }
        else showPrevious =true;

        //判断什么时候有进行下一页的标签
        if (page==totalPage)
        {
            showNext =false;
        }
        else showNext = true;

        //判断什么时候进行快速前进()
        if (pages.contains(1)) //如果pages显示的页码有第一页时 就不用展示快速前进
        {
                 showFirstPage =false;
        }
        else showFirstPage = true;


        //判断什么时候有快速后退（直接到最后一页）
         if (pages.contains(totalPage))
         {
             showEndPage =false;
         }
         else showEndPage = true;

    }

}
