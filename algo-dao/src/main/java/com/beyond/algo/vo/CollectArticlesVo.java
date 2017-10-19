package com.beyond.algo.vo;

import lombok.Data;

/**
 * @author ：zhangchuanzhi
 * @Description:收藏文献Vo
 * @date ：11:10 2017/10/19
 */
@Data
public class CollectArticlesVo {
    // 文章名字
    private String modName;
    // 文章来源
    private String papertext;
    // 算法语言
    private String lanName;
    // 作者收藏时间
    private String collectDate;
    private String usrSn;
    private int page;

    private int rows;

    private int total;
}
