/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package type;

/**
 *
 * @author WOLF
 */
public class Types {
    private int driverLicenseId;
    private String driverLicense;

    public Types() {
    }

    public Types(int driverLicenseId, String driverLicense) {
        this.driverLicenseId = driverLicenseId;
        this.driverLicense = driverLicense;
    }

    public int getDriverLicenseId() {
        return driverLicenseId;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicenseId(int driverLicenseId) {
        this.driverLicenseId = driverLicenseId;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    @Override
    public String toString() {
        return this.driverLicense;
    }
}
