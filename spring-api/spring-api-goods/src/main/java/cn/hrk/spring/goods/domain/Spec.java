package cn.hrk.spring.goods.domain;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Id;
import java.io.Serializable;

@Table(name="tb_spec")
public class Spec implements Serializable {
    @Id
    private Integer id;

    private String name;

    private String options;

    private Integer seq;

    private Integer templateId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options == null ? null : options.trim();
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }
}