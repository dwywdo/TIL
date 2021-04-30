package me.dwywdo.springvalidation.validation;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.lang.NonNull;

public class OutputResponse {
    @Min(1)
    @Max(10)
    private int numberBetweenOneAndTen;

    @NotEmpty
    private String notEmptyString;

    @Pattern(regexp = "^[0-9]{6}$")
    private String pinCode;

    public OutputResponse(int numberBetweenOneAndTen, @NonNull String notEmptyString, String pinCode) {
        this.numberBetweenOneAndTen = numberBetweenOneAndTen;
        this.notEmptyString = notEmptyString;
        this.pinCode = pinCode;
    }

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
