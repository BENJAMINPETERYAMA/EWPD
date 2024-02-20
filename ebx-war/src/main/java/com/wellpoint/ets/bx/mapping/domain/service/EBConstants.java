/******************************************************************************
 * Program Name                : EBConstants
 * Description                 : This class is used to create the constants for the values for the EB03 Segment
 * 
 * Project Name                :
 * Creation Date               : 28 Dec 2010
 * Author                      : UST Global.
 *
 * Copyright Notice
 * This file contains proprietary information of US Technology.
 * Copying or reproduction without prior written approval is prohibited.
 *
 ********************************Change History*******************************
 * Sl No.   Modified Date           Modified By       Change Description
 *
 *-------------------------------------------------------------------------------
 * 1.0     28 Dec 2010              UST Global         Initial Draft
 *
 *------------------------------------------------------ -------------------------
 ********************************************************************************/
package com.wellpoint.ets.bx.mapping.domain.service;

import java.util.HashMap;

public class EBConstants {
    public final static HashMap EB01map = new HashMap();
    public final static HashMap EB02map = new HashMap();
    public final static HashMap EB03map = new HashMap();
    public final static HashMap EB04map = new HashMap();
    public final static HashMap EB06map = new HashMap();
    public final static HashMap EB09map = new HashMap();
    public final static HashMap EB12map = new HashMap();//BXNI-November
    public final static HashMap HSD01map = new HashMap();
    public final static HashMap HSD03map = new HashMap();
    public final static HashMap HSD05map = new HashMap();//BXNI-November
    public final static HashMap HSD07map = new HashMap();//BXNI-November
    public final static HashMap HSD08map = new HashMap();
    public final static HashMap III02map = new HashMap();
   
    
    static {
        EB01map.put("1", "Active Coverage");
        EB01map.put("2", "Active - Full Risk Capitation");
        EB01map.put("3", "Active - Services Capitated");
        EB01map.put("4", "Active - Services Capitated to Primary Care Physician");
        EB01map.put("5", "Active - Pending Investigation");
        EB01map.put("6", "Inactive Coverage");
        EB01map.put("7", "Inactive - Pending Eligibility Update");
        EB01map.put("8", "Inactive - Pending Investigation");
        EB01map.put("A", "Co-Insurance");
        EB01map.put("B", "Co-Payment");
        EB01map.put("C", "Deductible");
        EB01map.put("CB", "Coverage Basis");
        EB01map.put("D", "Benefit Description");
        EB01map.put("E", "Exclusions");
        EB01map.put("F", "Limitations");
        EB01map.put("G", "Out of Pocket (Stop Loss)");
        EB01map.put("H", "Unlimited");
        EB01map.put("I", "Non-Covered");
        EB01map.put("J", "Cost Containment");
        EB01map.put("K", "Reserve");
        EB01map.put("L", "Primary Care Provider");
        EB01map.put("M", "Pre-existing Condition");
        EB01map.put("MC", "Managed Care Coordinator");
        EB01map.put("N", "Services Restricted to Following Provider");
        EB01map.put("O", "Not Deemed a Medical Necessity");
        EB01map.put("P", "Benefit Disclaimer");
        EB01map.put("Q", "Second Surgical Opinion Required");
        EB01map.put("R", "Other or Additional Payor");
        EB01map.put("S", "Prior Year(s) History");
        EB01map.put("T", "Card(s) Reported Lost/Stolen");
        EB01map.put("U", "Contact Following Entity for Eligibility or Benefit Information");
        EB01map.put("V", "Cannot Process");
        EB01map.put("W", "Other Source of Data");
        EB01map.put("X", "Health Care Facility");
        EB01map.put("Y", "Spend Down");

        EB02map.put("CHD", "Children Only");
        EB02map.put("DEP", "Dependents Only");
        EB02map.put("ECH", "Employee and Children");
        EB02map.put("EMP", "Employee Only");
        EB02map.put("ESP", "Employee and Spouse");
        EB02map.put("FAM", "Family");
        EB02map.put("IND", "Individual");
        EB02map.put("SPC", "Spouse and Children");
        EB02map.put("SPO", "Spouse Only");

        EB03map.put("1", "Medical Care");
        EB03map.put("2", "Surgical");
        EB03map.put("3", "Consultation");
        EB03map.put("4", "Diagnostic X-Ray");
        EB03map.put("5", "Diagnostic Lab");
        EB03map.put("6", "Radiation Therapy");
        EB03map.put("7", "Anesthesia");
        EB03map.put("8", "Surgical Assistance");
        EB03map.put("9", "Other Medical");
        EB03map.put("10", "Blood Charges");
        EB03map.put("11", "Used Durable Medical Equipment");
        EB03map.put("12", "Durable Medical Equipment Purchase");
        EB03map.put("13", "Ambulatory Service Center Facility");
        EB03map.put("14", "Renal Supplies in the Home");
        EB03map.put("15", "Alternate Method Dialysis");
        EB03map.put("16", "Chronic Renal Disease (CRD) Equipment");
        EB03map.put("17", "Pre-Admission Testing");
        EB03map.put("18", "Durable Medical Equipment Rental");
        EB03map.put("19", "Pneumonia Vaccine");
        EB03map.put("20", "Second Surgical Opinion");
        EB03map.put("21", "Third Surgical Opinion");
        EB03map.put("22", "Social Work");
        EB03map.put("23", "Diagnostic Dental");
        EB03map.put("24", "Periodontics");
        EB03map.put("25", "Restorative");
        EB03map.put("26", "Endodontics");
        EB03map.put("27", "Maxillofacial Prosthetics");
        EB03map.put("28", "Adjunctive Dental Services");
        EB03map.put("30", "Health Benefit Plan Coverage");
        EB03map.put("32", "Plan Waiting Period");
        EB03map.put("33", "Chiropractic");
        EB03map.put("34", "Chiropractic Office Visits");
        EB03map.put("35", "Dental Care");
        EB03map.put("36", "Dental Crowns");
        EB03map.put("37", "Dental Accident");
        EB03map.put("38", "Orthodontics");
        EB03map.put("39", "Prosthodontics");
        EB03map.put("40", "Oral Surgery");
        EB03map.put("41", "Routine (Preventive) Dental");
        EB03map.put("42", "Home Health Care");
        EB03map.put("43", "Home Health Prescriptions");
        EB03map.put("44", "Home Health Visits");
        EB03map.put("45", "Hospice");
        EB03map.put("46", "Respite Care");
        EB03map.put("47", "Hospital");
        EB03map.put("48", "Hospital - Inpatient");
        EB03map.put("49", "Hospital - Room and Board");
        EB03map.put("50", "Hospital - Outpatient");
        EB03map.put("51", "Hospital - Emergency Accident");
        EB03map.put("52", "Hospital - Emergency Medical");
        EB03map.put("53", "Hospital - Ambulatory Surgical");
        EB03map.put("54", "Long Term Care");
        EB03map.put("55", "Major Medical");
        EB03map.put("56", "Medically Related Transportation");
        EB03map.put("57", "Air Transportation");
        EB03map.put("58", "Cabulance");
        EB03map.put("59", "Licensed Ambulance");
        EB03map.put("60", "General Benefits");
        EB03map.put("61", "In-vitro Fertilization");
        EB03map.put("62", "MRI/CAT Scan");
        EB03map.put("63", "Donor Procedures");
        EB03map.put("64", "Acupuncture");
        EB03map.put("65", "Newborn Care");
        EB03map.put("66", "Pathology");
        EB03map.put("67", "Smoking Cessation");
        EB03map.put("68", "Well Baby Care");
        EB03map.put("69", "Maternity");
        EB03map.put("70", "Transplants");
        EB03map.put("71", "Audiology Exam");
        EB03map.put("72", "Inhalation Therapy");
        EB03map.put("73", "Diagnostic Medical");
        EB03map.put("74", "Private Duty Nursing");
        EB03map.put("75", "Prosthetic Device");
        EB03map.put("76", "Dialysis");
        EB03map.put("77", "Otological Exam");
        EB03map.put("78", "Chemotherapy");
        EB03map.put("79", "Allergy Testing");
        EB03map.put("80", "Immunizations");
        EB03map.put("81", "Routine Physical");
        EB03map.put("82", "Family Planning");
        EB03map.put("83", "Infertility");
        EB03map.put("84", "Abortion");
        EB03map.put("85", "AIDS");
        EB03map.put("86", "Emergency Services");
        EB03map.put("87", "Cancer");
        EB03map.put("88", "Pharmacy");
        EB03map.put("89", "Free Standing Prescription Drug");
        EB03map.put("90", "Mail Order Prescription Drug");
        EB03map.put("91", "Brand Name Prescription Drug");
        EB03map.put("92", "Generic Prescription Drug");
        EB03map.put("93", "Podiatry");
        EB03map.put("94", "Podiatry - Office Visits");
        EB03map.put("95", "Podiatry - Nursing Home Visits");
        EB03map.put("96", "Professional (Physician)");
        EB03map.put("97", "Anesthesiologist");
        EB03map.put("98", "Professional (Physician) Visit - Office");
        EB03map.put("99", "Professional (Physician) Visit - Inpatient");
        EB03map.put("A0", "Professional (Physician) Visit - Outpatient");
        EB03map.put("A1", "Professional (Physician) Visit - Nursing Home");
        EB03map.put("A2", "Professional (Physician) Visit - Skilled Nursing Facility");
        EB03map.put("A3", "Professional (Physician) Visit - Home");
        EB03map.put("A4", "Psychiatric");
        EB03map.put("A5", "Psychiatric - Room and Board");
        EB03map.put("A6", "Psychotherapy");
        EB03map.put("A7", "Psychiatric - Inpatient");
        EB03map.put("A8", "Psychiatric - Outpatient");
        EB03map.put("A9", "Rehabilitation");
        EB03map.put("AA", "Rehabilitation - Room and Board");
        EB03map.put("AB", "Rehabilitation - Inpatient");
        EB03map.put("AC", "Rehabilitation - Outpatient");
        EB03map.put("AD", "Occupational Therapy");
        EB03map.put("AE", "Physical Medicine");
        EB03map.put("AF", "Speech Therapy");
        EB03map.put("AG", "Skilled Nursing Care");
        EB03map.put("AH", "Skilled Nursing Care - Room and Board");
        EB03map.put("AI", "Substance Abuse");
        EB03map.put("AJ", "Alcoholism");
        EB03map.put("AK", "Drug Addiction");
        EB03map.put("AL", "Vision (Optometry)");
        EB03map.put("AM", "Frames");
        EB03map.put("AN", "Routine Exam");
        EB03map.put("AO", "Lenses");
        EB03map.put("AQ", "Nonmedically Necessary Physical");
        EB03map.put("AR", "Experimental Drug Therapy");
        EB03map.put("BA", "Independent Medical Evaluation");
        EB03map.put("BB", "Partial Hospitalization (Psychiatric)");
        EB03map.put("BC", "Day Care (Psychiatric)");
        EB03map.put("BD", "Cognitive Therapy");
        EB03map.put("BE", "Massage Therapy");
        EB03map.put("BF", "Pulmonary Rehabilitation");
        EB03map.put("BG", "Cardiac Rehabilitation");
        EB03map.put("BH", "Pediatric");
        EB03map.put("BI", "Nursery");
        EB03map.put("BJ", "Skin");
        EB03map.put("BK", "Orthopedic");
        EB03map.put("BL", "Cardiac");
        EB03map.put("BM", "Lymphatic");
        EB03map.put("BN", "Gastrointestinal");
        EB03map.put("BP", "Endocrine");
        EB03map.put("BQ", "Neurology");
        EB03map.put("BR", "Eye");
        EB03map.put("BS", "Invasive Procedures");
        EB03map.put("UC", "Urgent");

        EB04map.put("12", "Medicare Secondary Working Aged Beneficiary or Spouse with Employer Group Health Plan");
        EB04map.put("13", "Medicare Secondary End-Stage Renal Disease Beneficiary in the 12 month coordination period with an employer’s group health plan");
        EB04map.put("14", "Medicare Secondary, No-fault Insurance including Auto is Primary");
        EB04map.put("15", "Medicare Secondary Worker’s Compensation");
        EB04map.put("16", "Medicare Secondary Public Health Service (PHS)or Other Federal Agency");
        EB04map.put("41", "Medicare Secondary Black Lung");
        EB04map.put("42", "Medicare Secondary Veteran’s Administration");
        EB04map.put("43", "Medicare Secondary Disabled Beneficiary Under Age 65 with Large Group Health Plan (LGHP)");
        EB04map.put("47", "Medicare Secondary, Other Liability Insurance is Primary");
        EB04map.put("AP", "Auto Insurance Policy");
        EB04map.put("C1", "Commercial");
        EB04map.put("CO", "Consolidated Omnibus Budget Reconciliation Act (COBRA)");
        EB04map.put("CP", "Medicare Conditionally Primary");
        EB04map.put("D", "Disability");
        EB04map.put("DB", "Disability Benefits");
        EB04map.put("EP", "Exclusive Provider Organization");
        EB04map.put("FF", "Family or Friends");
        EB04map.put("GP", "Group Policy");
        EB04map.put("HM", "Health Maintenance Organization (HMO)");
        EB04map.put("HN", "Health Maintenance Organization (HMO) - Medicare Risk");
        EB04map.put("HS", "Special Low Income Medicare Beneficiary");
        EB04map.put("IN", "Indemnity");
        EB04map.put("IP", "Individual Policy");
        EB04map.put("LC", "Long Term Care");
        EB04map.put("LD", "Long Term Policy");
        EB04map.put("LI", "Life Insurance");
        EB04map.put("LT", "Litigation");
        EB04map.put("MA", "Medicare Part A");
        EB04map.put("MB", "Medicare Part B");
        EB04map.put("MC", "Medicaid");
        EB04map.put("MH", "Medigap Part A");
        EB04map.put("MI", "Medigap Part B");
        EB04map.put("MP", "Medicare Primary");
        EB04map.put("OT", "Other");
        EB04map.put("PE", "Property Insurance - Personal");
        EB04map.put("PL", "Personal");
        EB04map.put("PP", "Personal Payment (Cash - No Insurance)");
        EB04map.put("PR", "Preferred Provider Organization (PPO)");
        EB04map.put("PS", "Point of Service (POS)");
        EB04map.put("QM", "Qualified Medicare Beneficiary");
        EB04map.put("RP", "Property Insurance - Real");
        EB04map.put("SP", "Supplemental Policy");
        EB04map.put("TF", "Tax Equity Fiscal Responsibility Act (TEFRA)");
        EB04map.put("WC", "Workers Compensation");
        EB04map.put("WU", "Wrap Up Policy");

        EB06map.put("6", "Hour");
        EB06map.put("7", "Day");
        EB06map.put("13", "24 Hours");
        EB06map.put("21", "Years");
        EB06map.put("22", "Service Year");
        EB06map.put("23", "Calendar Year");
        EB06map.put("24", "Year to Date");
        EB06map.put("25", "Contract");
        EB06map.put("26", "Episode");
        EB06map.put("27", "Visit");
        EB06map.put("28", "Outlier");
        EB06map.put("29", "Remaining");
        EB06map.put("30", "Exceeded");
        EB06map.put("31", "Not Exceeded");
        EB06map.put("32", "Lifetime");
        EB06map.put("33", "Lifetime Remaining");
        EB06map.put("34", "Month");
        EB06map.put("35", "Week");
        EB06map.put("36", "Admisson");
        
        HSD01map.put("DY", "Days");
        HSD01map.put("FL", "Units");
        HSD01map.put("HS", "Hours");
        HSD01map.put("MN", "Month");
        HSD01map.put("VS", "Visit");

        
        HSD03map.put("DA", "Days");
        HSD03map.put("MO", "Month");
        HSD03map.put("VS", "Visit");
        HSD03map.put("WK", "Week");
        HSD03map.put("YR", "Year");
        
        HSD05map.put("6", "Hour");
        HSD05map.put("7", "Day");
        HSD05map.put("21", "Year");
        HSD05map.put("22", "Service Year");
        HSD05map.put("23", "Calendar Year");
        HSD05map.put("24", "Year to Date");
        HSD05map.put("25", "Contract");
        HSD05map.put("26", "Episode");
        HSD05map.put("27", "Visit");
        HSD05map.put("28", "Outlier");
        HSD05map.put("29", "Remaining");
        HSD05map.put("30", "Exceeded");
        HSD05map.put("31", "Not Exceeded");
        
        
        HSD07map.put("5", "5th Week of the Month");
        HSD07map.put("6", "1st & 3rd Weeks of the Month");
        HSD07map.put("7", "2nd & 4th Weeks of the Month");
        HSD07map.put("8", "1st Working Day of Period");
        HSD07map.put("9", "Last Working Day of Period");
        HSD07map.put("A", "Monday through Friday");
        HSD07map.put("B", "Monday through Saturday");
        HSD07map.put("C", "Monday through Sunday");
        HSD07map.put("E", "Tuesday");
        HSD07map.put("F", "Wednesday");
        HSD07map.put("G", "Thursday");
        HSD07map.put("H", "Friday");
        HSD07map.put("J", "Saturday");
        HSD07map.put("K", "Sunday");
        HSD07map.put("L", "Monday through Thursday");
        HSD07map.put("M", "Immediately");
        HSD07map.put("N", "As Directed");
        HSD07map.put("O", "Daily Mon. through Fri");
        HSD07map.put("P", "1/2 Mon. & 1/2 Thurs.");
        HSD07map.put("Q", "1/2 Tues. & 1/2 Thurs.");
        HSD07map.put("R", "1/2 Wed. & 1/2 Fri");
        HSD07map.put("S", "Once Anytime Mon. through Fri");
        HSD07map.put("SG", "Tuesday through Friday");
        HSD07map.put("SL", "Monday, Tuesday and Thursday");
        HSD07map.put("SP", "Monday, Tuesday and Friday");
        HSD07map.put("SX", "Wednesday and Thursday");
        HSD07map.put("SY", "Monday, Wednesday and Thursday");
        HSD07map.put("SZ", "Tuesday, Thursday and Friday");
        HSD07map.put("T", "1/2 Tue. & 1/2 Fri.");
        HSD07map.put("U", "1/2 Mon. & 1/2 Wed.");
        HSD07map.put("V", "1/3 Mon., 1/3 Wed., 1/3 Fri.");
        HSD07map.put("W", "Whenever Necessary");
        HSD07map.put("X", "1/2 By Wed., Bal. By Fri.");
        HSD07map.put("Y", "None");
        
        
        HSD08map.put("A", "1st Shift");
        HSD08map.put("B", "2nd Shift");
        HSD08map.put("C", "3rd Shift");
        HSD08map.put("D", "A.M.");
        HSD08map.put("E", "P.M.");
        HSD08map.put("F", "As Directed");
        HSD08map.put("G", "Any Shift");
        HSD08map.put("Y", "None");
        
        
        
        
        
        

        III02map.put("11", "Office");
        III02map.put("12", "Home");
        III02map.put("21", "Inpatient Hospital");
        III02map.put("22", "Outpatient Hospital");
        III02map.put("23", "Emergency Room - Hospital");
        III02map.put("24", "Ambulatory Surgical Center");
        III02map.put("25", "Birthing Center");
        III02map.put("26", "Military Treatment Facility");
        III02map.put("31", "Skilled Nursing Facility");
        III02map.put("32", "Nursing Facility");
        III02map.put("33", "Custodial Care Facility");
        III02map.put("34", "Hospice");
        III02map.put("41", "Ambulance - Land");
        III02map.put("42", "Ambulance - Air or Water");
        III02map.put("50", "Federally Qualified Health Center");
        III02map.put("51", "Inpatient Psychiatric Facility");
        III02map.put("52", "Psychiatric Facility Partial Hospitalization");
        III02map.put("53", "Community Mental Health Center");
        III02map.put("54", "Intermediate Care Facility/Mentally Retarded");
        III02map.put("55", "Residential Substance Abuse Treatment Facility");
        III02map.put("56", "Psychiatric Residential Treatment Center");
        III02map.put("60", "Mass Immunization Center");
        III02map.put("61", "Comprehensive Inpatient Rehabilitation Facility");
        III02map.put("62", "Comprehensive Outpatient Rehabilitation Facility");
        III02map.put("65", "End-Stage Renal Disease Treatment Facility");
        III02map.put("71", "State or Local Public Health Clinic");
        III02map.put("72", "Rural Health Clinic");
        III02map.put("81", "Independent Laboratory");
        III02map.put("99", "Other Unlisted Facility");
        
        EB09map.put("99", "Quantity Used");
        EB09map.put("CA", "Covered - Actual");
        EB09map.put("CE", "Covered - Estimated");
        EB09map.put("DB", "Deductible Blood Units");
        EB09map.put("DY", "Days");
        EB09map.put("HS", "Hours");
        EB09map.put("LA", "Life-time Reserve - Actual");
        EB09map.put("LE", "Life-time Reserve - Estimated MN Month");
        EB09map.put("P6", "Number of Services or Procedures");
        EB09map.put("QA", "Quantity Approved");
        EB09map.put("S7", "Age, High Value");
        EB09map.put("1235", "Use this code when a benefit is based on a maximum age for the patient.");
        EB09map.put("S8", "Age, Low Value");
        EB09map.put("1236", "Use this code when a benefit is based on a minimum age for the patient.");
        EB09map.put("VS", "Visits");
        EB09map.put("YY", "Years");
        
        EB12map.put("Y", "In-Network");
        EB12map.put("N", "Out of Network");
        EB12map.put("W", "Not Applicable");
    }


}
