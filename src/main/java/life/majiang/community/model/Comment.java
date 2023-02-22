package life.majiang.community.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("comment")
public class Comment {
      @TableField("id")
      private long id;
      @TableField("parent_id")
      private Integer parentId;
      @TableField("type")
      private Integer type;
      @TableField("commentator")
      private int commentator;
      @TableField("gmt_create")
      private long gmtCreate;
      @TableField("gmt_modified")
      private long gmtModified;
      @TableField("like_count")
      private long likeCount;
      @TableField("content")
      private String content;
}
