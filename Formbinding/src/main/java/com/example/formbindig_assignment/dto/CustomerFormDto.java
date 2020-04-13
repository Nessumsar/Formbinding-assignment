package com.example.formbindig_assignment.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.example.formbindig_assignment.constraints.messages.ValidationMessages.*;
import static com.example.formbindig_assignment.constraints.regex.RegexStorage.*;

public class CustomerFormDto {

    @NotBlank(message = "Email is required")
    @Email(regexp = EMAIL_REGEX, flags = Pattern.Flag.CASE_INSENSITIVE, message = EMAIL_FORMAT_MESSAGE)
    private String email;

    @Size(max = 100, message = STREET_SIZE_ERROR)
    private String street;

    @Pattern(regexp = ZIP_REGEX, flags = Pattern.Flag.CASE_INSENSITIVE, message = ZIP_SIZE_ERROR)
    private String zipCode;

    @Size(max = 100, message = CITY_SIZE_ERROR)
    private String city;

    @Pattern(regexp = HOME_PHONE_REGEX, flags = Pattern.Flag.CASE_INSENSITIVE, message = HOME_PHONE_SIZE_ERROR)
    private String homePhone;

    @Pattern(regexp = CELL_PHONE_REGEX, flags = Pattern.Flag.CASE_INSENSITIVE, message = CELL_PHONE_SIZE_ERROR)
    private String cellPhone;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }
}
