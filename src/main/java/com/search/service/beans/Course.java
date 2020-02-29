package com.search.service.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tbl_course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("courseName")
    private String courseName;
    @JsonProperty("courseCd")
    private String courseCd;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id) &&
                Objects.equals(courseName, course.courseName) &&
                Objects.equals(courseCd, course.courseCd);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", courseCd='" + courseCd + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseName, courseCd);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCd() {
        return courseCd;
    }

    public void setCourseCd(String courseCd) {
        this.courseCd = courseCd;
    }
}
