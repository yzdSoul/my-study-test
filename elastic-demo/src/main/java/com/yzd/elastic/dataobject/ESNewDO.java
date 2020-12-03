package com.yzd.elastic.dataobject;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.ArrayList;

/**
 * Created by yzd on 2020/12/1
 */
@Document(indexName = "news", // 索引名
        shards = 1, // 默认索引分区数
        replicas = 0, // 每个分区的备份数
        refreshInterval = "-1" // 刷新间隔
)
public class ESNewDO {
    @Id
    private String id;

    @Field(value = "title",type = FieldType.Keyword)
    private String title;

    @Field(type = FieldType.Date,format = DateFormat.basic_date)
    private Long createtime;

    private ArrayList categoryIds;

    private String tag;

    private String newsType;

    private String content;

    private Integer ttl;

    private String origin;

    private String status;

    @Field(type = FieldType.Date,format = DateFormat.basic_date)
    private Long pubtime;

    @Field(type = FieldType.Date,format = DateFormat.basic_date)
    private Long gmtCreated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public ArrayList getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(ArrayList categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getPubtime() {
        return pubtime;
    }

    public void setPubtime(Long pubtime) {
        this.pubtime = pubtime;
    }

    public Long getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Long gmtCreated) {
        this.gmtCreated = gmtCreated;
    }



    @Override
    public String toString() {
        return "ESNewDO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", createtime=" + createtime +
                ", categoryIds=" + categoryIds +
                ", tag='" + tag + '\'' +
                ", newsType='" + newsType + '\'' +
                ", content='" + content + '\'' +
                ", ttl=" + ttl +
                ", origin='" + origin + '\'' +
                ", status='" + status + '\'' +
                ", pubtime=" + pubtime +
                ", gmtCreated=" + gmtCreated +
                '}';
    }
}
