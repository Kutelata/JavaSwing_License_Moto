/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

/**
 *
 * @author Wolf
 */
public class User {

    private int id;
    private String account;
    private String pass;
    private float mark;
    private String identificationCard;
    private String name;
    private String image;
    private int gender;
    private String birthday;
    private String address;
    private int driverLicenseId;
    private String driverLicense;
    private int roleId;
    private String role;
    private String testTime;
    private int result;

    public User() {
    }

    public User(int id, String account, String pass, float mark, String identificationCard, String name, String image, int gender, String birthday, String address, String driverLicense, String role, String testTime, int result, int roleId, int driverLicenseId) {
        this.id = id;
        this.account = account;
        this.pass = pass;
        this.mark = mark;
        this.identificationCard = identificationCard;
        this.name = name;
        this.image = image;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
        this.driverLicense = driverLicense;
        this.role = role;
        this.testTime = testTime;
        this.result = result;
        this.roleId = roleId;
        this.driverLicenseId = driverLicenseId;
    }

    public User(String account, String pass, String identificationCard, String name, String image, int gender, String birthday, String address, int driverLicenseId, int roleId) {
        this.account = account;
        this.pass = pass;
        this.identificationCard = identificationCard;
        this.name = name;
        this.image = image;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
        this.driverLicenseId = driverLicenseId;
        this.roleId = roleId;
    }

    public User(int id, String account, String pass, String identificationCard, String name, String image, int gender, String birthday, String address, int driverLicenseId, int roleId) {
        this.id = id;
        this.account = account;
        this.pass = pass;
        this.identificationCard = identificationCard;
        this.name = name;
        this.image = image;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
        this.driverLicenseId = driverLicenseId;
        this.roleId = roleId;
    }

    public int getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public String getPass() {
        return pass;
    }

    public float getMark() {
        return mark;
    }

    public String getIdentificationCard() {
        return identificationCard;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public int getGender() {
        return gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getAddress() {
        return address;
    }

    public int getDriverLicenseId() {
        return driverLicenseId;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getRole() {
        return role;
    }

    public String getTestTime() {
        return testTime;
    }
    
    public int getResult(){
        return result;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    public void setIdentificationCard(String identificationCard) {
        this.identificationCard = identificationCard;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setTestTime(String testTime) {
        this.testTime = testTime;
    }
    
    public void setResult(int result){
        this.result = result;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    public void setDriverLicenseId(int driverLicenseId) {
        this.driverLicenseId = driverLicenseId;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
