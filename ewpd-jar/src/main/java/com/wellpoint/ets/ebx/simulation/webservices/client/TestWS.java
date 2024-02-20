package com.wellpoint.ets.ebx.simulation.webservices.client;

import java.util.List;

//import com.wellpoint.wpd.common.util.WebServiceExcelGenerator;

public class TestWS {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ContractWebServiceVO contract = new ContractWebServiceVO();
		/*contract.setSystem("EWPD"); 
		contract.setContractId("CB51");
		contract.setEffectiveDate("09/01/2010"); 
		contract.setVersion(5);
		*/
		contract.setSystem("EWPD"); 
		contract.setContractId("BQ31");
		contract.setEffectiveDate("09/01/2010"); 
		contract.setVersion(13);
		 
		/*contract.setSystem("ISG");
		contract.setContractId("00C0");
		contract.setEffectiveDate("01/01/2011");*/

		List<ContractWebServiceVO> contractList = new SimulationWebServiceImplPortProxy()
				.getContractInfo(contract, "Production", false);

		System.out.println("contractList-->" + contract);
		if (contractList != null) {
			/*
			 * System.out.println("contractList.get(0).getMajorHeadings()-->" +
			 * contractList.get(0).getMajorHeadings());
			 * System.out.println("contractList.ErrorCodesList-->" +
			 * contractList.get(0).getErrorCodesList());
			 */
		}
		if (contractList.get(0).getMajorHeadings() != null) {

			for (int i = 0; i < contractList.get(0).getMajorHeadings()
					.getEntry().size(); i++) {
				System.out.println("MajorHeadings DescriptionText-->"
						+ contractList.get(0).getMajorHeadings().getEntry()
								.get(i).getValue().getDescriptionText());
				for (int j = 0; j < contractList.get(0).getMajorHeadings()
						.getEntry().get(i).getValue().getMinorHeadings()
						.getEntry().size(); j++) {
					if (contractList.get(0).getMajorHeadings().getEntry()
							.get(i).getValue().getMinorHeadings().getEntry()
							.get(j).getValue().getErrorCodesList() != null) {
						System.out.println("MinorHeadings ErrorCodesList-->"
								+ contractList.get(0).getMajorHeadings()
										.getEntry().get(i).getValue()
										.getMinorHeadings().getEntry().get(j)
										.getValue().getErrorCodesList());
						for (int k = 0; k < contractList.get(0)
								.getMajorHeadings().getEntry().get(i)
								.getValue().getMinorHeadings().getEntry()
								.get(j).getValue().getMappings().getEntry()
								.size(); k++) {
							if (contractList.get(0).getMajorHeadings()
									.getEntry().get(i).getValue()
									.getMinorHeadings().getEntry().get(j)
									.getValue().getMappings().getEntry().get(k)
									.getValue().getContractMapping()
									.getErrorCodesList() != null) {
								System.out
										.println("ContractMapping ErrorCodesList -->"
												+ contractList.get(0)
														.getMajorHeadings()
														.getEntry().get(i)
														.getValue()
														.getMinorHeadings()
														.getEntry().get(j)
														.getValue()
														.getMappings()
														.getEntry().get(k)
														.getValue()
														.getContractMapping()
														.getErrorCodesList());
							}
						}
					}
				}
			}
		}
		//WebServiceExcelGenerator builder = new WebServiceExcelGenerator();
		//builder.buildExcelDocument(contract, "Production");

	}
}
