package com.codegym.model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;

@Entity
@Component
@Table
public class User implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Integer age;
    private String password;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        String name = user.getName();
        String email = user.getEmail();
        String phone = user.getPhone();
        Integer age = user.getAge();
        String password = user.getPassword();

        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
        if (name.length() > 45 || name.length() < 5) {
            errors.rejectValue("name", "name.length");
        }
        ValidationUtils.rejectIfEmpty(errors, "email", "email.empty");
        if (!email.matches("^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$")) {
            errors.rejectValue("email", "email.matches");
        }
        ValidationUtils.rejectIfEmpty(errors, "password", "password.empty");
        if (password.length() > 30 || password.length() < 6) {
            errors.rejectValue("password", "password.length");
        }
        ValidationUtils.rejectIfEmpty(errors, "phone", "phone.empty");
        if (phone.length() > 13 || phone.length() < 10) {
            errors.rejectValue("phone", "phone.length");
        }
        if (!phone.startsWith("+84") || !phone.startsWith("0")) {
            errors.rejectValue("phone", "phone.startsWith");
        }
        if (!phone.matches("^$|[0-9]*$")) {
            errors.rejectValue("phone", "phone.matches", "Phone invalidddddd");
        }
        ValidationUtils.rejectIfEmpty(errors, "age", "age.empty");
        if (age < 18) {
            errors.rejectValue("age", "age.length");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
