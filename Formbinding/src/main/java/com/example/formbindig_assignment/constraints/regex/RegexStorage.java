package com.example.formbindig_assignment.constraints.regex;

public class RegexStorage {
    public static final String EMAIL_REGEX = "^(\\D)+(\\w)*((\\.(\\w)+)?)+@(\\D)+(\\w)*((\\.(\\D)+(\\w)*)+)?(\\.)[a-z]{2,}$";
    public static final String ZIP_REGEX = "^[0-9 ]{0,6}$";
    public static final String HOME_PHONE_REGEX = "^[0-9- ]{0,10}$";
    public static final String CELL_PHONE_REGEX = "^[0-9- ]{0,11}$";

}
