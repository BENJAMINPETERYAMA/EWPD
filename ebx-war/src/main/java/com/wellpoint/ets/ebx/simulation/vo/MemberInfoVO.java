/*
 * Created on Nov 26, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.ebx.simulation.vo;

/**
 * @author U23665
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */

public class MemberInfoVO {

    private String            memberId;

    private String            firstName;

    private String            lastName;

    private String            dateOfBirth;

    private String            alphaPrefix;

    private MemberDetailsInfoVO memberDetailsInfo;

    private boolean           isDependentInformation;

    /**
     * @return the alphaPrefix
     */
    public String getAlphaPrefix() {
        return alphaPrefix;
    }

    /**
     * @param alphaPrefix
     *          the alphaPrefix to set
     */
    public void setAlphaPrefix(String alphaPrefix) {
        this.alphaPrefix = alphaPrefix;
    }

    /**
     * @return the dateOfBirth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth
     *          the dateOfBirth to set
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     *          the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     *          the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the memberId
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * @param memberId
     *          the memberId to set
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    /**
     * @return the memberDetailsInfo
     */
    public MemberDetailsInfoVO getMemberDetailsInfo() {
        return memberDetailsInfo;
    }

    /**
     * @param memberDetailsInfo
     *          the memberDetailsInfo to set
     */
    public void setMemberDetailsInfo(MemberDetailsInfoVO memberDetailsInfo) {
        this.memberDetailsInfo = memberDetailsInfo;
    }

    /**
     * @return the isDependentInformation
     */
    public boolean isDependentInformation() {
        return isDependentInformation;
    }

    /**
     * @param isDependentInformation
     *          the isDependentInformation to set
     */
    public void setDependentInformation(boolean isDependentInformation) {
        this.isDependentInformation = isDependentInformation;
    }

}
