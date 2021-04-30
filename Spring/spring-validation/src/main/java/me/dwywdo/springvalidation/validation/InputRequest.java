package me.dwywdo.springvalidation.validation;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class InputRequest {
    @Min(1)
    @Max(10)
    private int numberBetweenOneAndTen;

    @NotEmpty
    private String notEmptyString;

    @Pattern(regexp = "^[0-9]{6}$")
    private String pinCode;

    public int getNumberBetweenOneAndTen() {
        return numberBetweenOneAndTen;
    }

    public void setNumberBetweenOneAndTen(int numberBetweenOneAndTen) {
        this.numberBetweenOneAndTen = numberBetweenOneAndTen;
    }

    public String getNotEmptyString() {
        return notEmptyString;
    }

    public void setNotEmptyString(String notEmptyString) {
        this.notEmptyString = notEmptyString;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }
}
