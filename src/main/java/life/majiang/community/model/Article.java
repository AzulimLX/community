package life.majiang.community.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("article")
public class Article {
    @TableField("id")
    Integer id;
    @TableField("title")
    String title;
    @TableField("description")
    String description;
    @TableField("gmt_create")
    Long gmtCreate;
    @TableField("gmt_modified")
    Long gmtModified;
    @TableField("creator")
    Integer creator;
    @TableField("comment_count")
    Long commentCount;
    @TableField("view_count")
    Long viewCount;
    @TableField("like_count")
    Long likeCount;
    @TableField("tag")
    String tag;

}
