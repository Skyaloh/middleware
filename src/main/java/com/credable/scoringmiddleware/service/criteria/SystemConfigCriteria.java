package com.credable.scoringmiddleware.service.criteria;

import com.credable.scoringmiddleware.service.helper.filter.LongFilter;
import com.credable.scoringmiddleware.service.helper.filter.StringFilter;

import java.io.Serializable;
import java.util.Objects;

public class SystemConfigCriteria implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private StringFilter name;

    public LongFilter getId() {
        return id;
    }


    public StringFilter getCode() {
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SystemConfigCriteria that = (SystemConfigCriteria) o;
        return Objects.equals(id, that.id) && Objects.equals(code, that.code) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name);
    }

    @Override
    public String toString() {
        return "SystemConfigCriteria{" +
                "id=" + id +
                ", code=" + code +
                ", name=" + name +
                '}';
    }
}
