/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnbp.Account;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class AccountCreatError implements Serializable {

    private String emailLengthError;
    private String emailFormErr;
    private String passwordLengthError;
    private String confirmPasswordError;
    private String emailExistError;
    private String nameLengthError;
    private String phoneError;
    private String addressLengthError;

    public AccountCreatError() {
    }

    public AccountCreatError(String emailLengthError, String emailFormErr, String passwordLengthError, String confirmPasswordError, String emailExistError, String nameLengthError, String phoneError, String addressLengthError) {
        this.emailLengthError = emailLengthError;
        this.emailFormErr = emailFormErr;
        this.passwordLengthError = passwordLengthError;
        this.confirmPasswordError = confirmPasswordError;
        this.emailExistError = emailExistError;
        this.nameLengthError = nameLengthError;
        this.phoneError = phoneError;
        this.addressLengthError = addressLengthError;
    }

   

    /**
     * @return the emailLengthError
     */
    public String getEmailLengthError() {
        return emailLengthError;
    }

    /**
     * @param emailLengthError the emailLengthError to set
     */
    public void setEmailLengthError(String emailLengthError) {
        this.emailLengthError = emailLengthError;
    }

    /**
     * @return the emailFormErr
     */
    public String getEmailFormErr() {
        return emailFormErr;
    }

    /**
     * @param emailFormErr the emailFormErr to set
     */
    public void setEmailFormErr(String emailFormErr) {
        this.emailFormErr = emailFormErr;
    }

    /**
     * @return the passwordLengthError
     */
    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    /**
     * @param passwordLengthError the passwordLengthError to set
     */
    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }

    /**
     * @return the confirmPasswordError
     */
    public String getConfirmPasswordError() {
        return confirmPasswordError;
    }

    /**
     * @param confirmPasswordError the confirmPasswordError to set
     */
    public void setConfirmPasswordError(String confirmPasswordError) {
        this.confirmPasswordError = confirmPasswordError;
    }

    /**
     * @return the emailExistError
     */
    public String getEmailExistError() {
        return emailExistError;
    }

    /**
     * @param emailExistError the emailExistError to set
     */
    public void setEmailExistError(String emailExistError) {
        this.emailExistError = emailExistError;
    }

    /**
     * @return the nameLengthError
     */
    public String getNameLengthError() {
        return nameLengthError;
    }

    /**
     * @param nameLengthError the nameLengthError to set
     */
    public void setNameLengthError(String nameLengthError) {
        this.nameLengthError = nameLengthError;
    }

    /**
     * @return the phoneError
     */
    public String getPhoneError() {
        return phoneError;
    }

    /**
     * @param phoneError the phoneError to set
     */
    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

    /**
     * @return the addressLengthError
     */
    public String getAddressLengthError() {
        return addressLengthError;
    }

    /**
     * @param addressLengthError the addressLengthError to set
     */
    public void setAddressLengthError(String addressLengthError) {
        this.addressLengthError = addressLengthError;
    }

    /**
     * @return the phone
     */
   

}
