/*
 * WPDResponseFactory.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.framework.response;

//import com.wellpoint.wpd.common.adminmethod.response.RetrieveAdminMethodsResponse;
import com.wellpoint.wpd.common.accumulator.response.AccumulatorFilterResponse;
import com.wellpoint.wpd.common.adminmethod.response.AdminMethodOverrideResponse;
import com.wellpoint.wpd.common.adminmethod.response.AdminMethodSPSRetrieveResponse;
import com.wellpoint.wpd.common.adminmethod.response.AdminMethodSPSValidationResponse;
import com.wellpoint.wpd.common.adminmethod.response.AdminMethodValidationStatusResponse;
import com.wellpoint.wpd.common.adminmethod.response.AdminmethodViewAllResponse;
import com.wellpoint.wpd.common.adminmethod.response.GeneralBenefitAdminMethodValidationResponse;
import com.wellpoint.wpd.common.adminmethod.response.OverrideAdminMethodResponse;
import com.wellpoint.wpd.common.adminmethod.response.RetrieveAdminMethodsResponse;
import com.wellpoint.wpd.common.adminmethod.response.SaveAdminMethodResponse;
import com.wellpoint.wpd.common.adminmethodmaintain.response.AdminMethodCreateResponse;
import com.wellpoint.wpd.common.adminmethodmaintain.response.AdminMethodDeleteResponse;
import com.wellpoint.wpd.common.adminmethodmaintain.response.AdminMethodEditResponse;
import com.wellpoint.wpd.common.adminmethodmaintain.response.AdminMethodSearchResponse;
import com.wellpoint.wpd.common.adminmethodmaintain.response.AdminMethodViewResponse;
import com.wellpoint.wpd.common.adminmethodmapping.Response.AdminMethodMappingCreateResponse;
import com.wellpoint.wpd.common.adminmethodmapping.Response.AdminMethodMappingDeleteResponse;
import com.wellpoint.wpd.common.adminmethodmapping.Response.AdminMethodMappingEditResponse;
import com.wellpoint.wpd.common.adminmethodmapping.Response.AdminMethodMappingSearchResponse;
import com.wellpoint.wpd.common.adminmethodmapping.Response.AdminMethodMappingViewResponse;
import com.wellpoint.wpd.common.adminmethodmapping.Response.QuestionAnswerLookupResponse;
import com.wellpoint.wpd.common.adminoption.response.AddRootQuestionResponse;
import com.wellpoint.wpd.common.adminoption.response.AdminOptionUnlockResponse;
import com.wellpoint.wpd.common.adminoption.response.AdminOptionViewResponse;
import com.wellpoint.wpd.common.adminoption.response.ApproveAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.CheckInAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.CheckOutAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.CreateAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.DeleteAdminOptionQuestionResponse;
import com.wellpoint.wpd.common.adminoption.response.DeleteAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.DeleteAllAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.LocateRootQuestionResponse;
import com.wellpoint.wpd.common.adminoption.response.PersistChildQuestionnaireResponse;
import com.wellpoint.wpd.common.adminoption.response.PublishAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.RejectAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.RetrieveAdminOptionQuestionResponse;
import com.wellpoint.wpd.common.adminoption.response.RetrieveAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.RetrieveChildQuestionnaireResponse;
import com.wellpoint.wpd.common.adminoption.response.RetrieveRootQuestionnaireResponse;
import com.wellpoint.wpd.common.adminoption.response.SaveAdminOptionQuestionResponse;
import com.wellpoint.wpd.common.adminoption.response.SaveAdminOptionQuestionnaireResponse;
import com.wellpoint.wpd.common.adminoption.response.SaveAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.ScheduleForTestAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.SearchAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.TestFailAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.TestPassAdminOptionResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentApproveResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentAttachNotesResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentCheckInResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentCheckOutResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentCopyResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentDeleteResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentPublishResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentRejectResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentRetrieveResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentRetrieveResponseForEdit;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentSaveResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentScheduleForTestResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentTestFailResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentTestPassResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentUnlockResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentViewResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitHierarchyResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitHierarchyUpdateResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitRetrieveResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.DeleteBenefitComponentNotesResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.LocateBenefitComponentNotesResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.LocateComponentsBenefitAdministrationResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.LocateComponentsBenefitDefinitionResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.LocateNonAdjBenefitMandateResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.UpdateBenefitLinesResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.UpdateComponentsBenefitAdministrationResponse;
import com.wellpoint.wpd.common.benefitlevel.response.BenefitLevelResponse;
import com.wellpoint.wpd.common.benefitlevel.response.BenefitLineNotesAttachmentResponse;
import com.wellpoint.wpd.common.benefitlevel.response.DataTypeRetrieveResponse;
import com.wellpoint.wpd.common.benefitlevel.response.DeleteBenefitLevelResponse;
import com.wellpoint.wpd.common.benefitlevel.response.DeleteBenefitLineResponse;
import com.wellpoint.wpd.common.benefitlevel.response.NotesAttachmentResponseForBnftLine;
import com.wellpoint.wpd.common.benefitlevel.response.SaveBenefitLevelResponse;
import com.wellpoint.wpd.common.benefitlevel.response.SearchBenefitLevelResponse;
import com.wellpoint.wpd.common.benefitlevel.response.SearchBenefitLevelTermResponse;
import com.wellpoint.wpd.common.blueexchange.response.ContractVariableMappingResponse;
import com.wellpoint.wpd.common.blueexchange.response.CustomMessageRetrieveResponse;
import com.wellpoint.wpd.common.blueexchange.response.CustomMessageTextCreateResponse;
import com.wellpoint.wpd.common.blueexchange.response.CustomMessageTextUpdateResponse;
import com.wellpoint.wpd.common.blueexchange.response.DeleteCustomMessageResponse;
import com.wellpoint.wpd.common.blueexchange.response.DeleteSpsMappingResponse;
import com.wellpoint.wpd.common.blueexchange.response.RetrieveServiceTypeMappingResponse;
import com.wellpoint.wpd.common.blueexchange.response.SPSMappingCreateResponse;
import com.wellpoint.wpd.common.blueexchange.response.SPSMappingUpdateResponse;
import com.wellpoint.wpd.common.blueexchange.response.SPSMappingViewResponse;
import com.wellpoint.wpd.common.blueexchange.response.SaveServiceTypeMappingResponse;
import com.wellpoint.wpd.common.blueexchange.response.SearchCustomMessageResponse;
import com.wellpoint.wpd.common.blueexchange.response.SearchSPSMaintainResponse;
import com.wellpoint.wpd.common.blueexchange.response.ServiceTypeMappingDeleteResponse;
import com.wellpoint.wpd.common.blueexchange.response.ServiceTypeMappingSearchResponse;
import com.wellpoint.wpd.common.blueexchange.response.ViewServiceTypeCodeMappingResponse;
import com.wellpoint.wpd.common.catalog.response.CatalogDeleteResponse;
import com.wellpoint.wpd.common.catalog.response.CatalogRetrieveResponse;
import com.wellpoint.wpd.common.catalog.response.EditCatalogResponse;
import com.wellpoint.wpd.common.catalog.response.SaveCatalogResponse;
import com.wellpoint.wpd.common.catalog.response.SearchCatalogResponse;
import com.wellpoint.wpd.common.catalog.response.SrdaCatalogRetrieveResponse;
import com.wellpoint.wpd.common.contract.response.CheckCopyLegacyNoteResponse;
import com.wellpoint.wpd.common.contract.response.ContractAttachNotesResponse;
import com.wellpoint.wpd.common.contract.response.ContractBenefitComponentAttachNotesResponse;
import com.wellpoint.wpd.common.contract.response.ContractNoteAttachmentResponse;
import com.wellpoint.wpd.common.contract.response.ContractNotesDeleteResponse;
import com.wellpoint.wpd.common.contract.response.ContractQuestionNoteResponse;
import com.wellpoint.wpd.common.contract.response.ContractQuestionNotesPopUpResponse;
import com.wellpoint.wpd.common.contract.response.ContractRuleValidationResponse;
import com.wellpoint.wpd.common.contract.response.ContractRulesetResponse;
import com.wellpoint.wpd.common.contract.response.ContractSearchResponse;
import com.wellpoint.wpd.common.contract.response.ContractTransferLogResponse;
import com.wellpoint.wpd.common.contract.response.ContractUncodedNotesResponse;
import com.wellpoint.wpd.common.contract.response.CreateDateSegmentResponse;
import com.wellpoint.wpd.common.contract.response.DatafeedResponse;
import com.wellpoint.wpd.common.contract.response.DateSegmentCheckoutResponse;
import com.wellpoint.wpd.common.contract.response.DeleteContractComponentNoteResponse;
import com.wellpoint.wpd.common.contract.response.DeleteContractProductAdminResponse;
import com.wellpoint.wpd.common.contract.response.DeleteContractResponse;
import com.wellpoint.wpd.common.contract.response.DeletePricingInfoResponse;
import com.wellpoint.wpd.common.contract.response.LoadContractBenefitNoteResponse;
import com.wellpoint.wpd.common.contract.response.LoadContractComponentNoteResponse;
import com.wellpoint.wpd.common.contract.response.LocatePricingInformationResponse;
import com.wellpoint.wpd.common.contract.response.LocateProductResponse;
import com.wellpoint.wpd.common.contract.response.MembershipInfoResponse;
import com.wellpoint.wpd.common.contract.response.ReservedContractSearchResponse;
import com.wellpoint.wpd.common.contract.response.RetreiveBenefitsContractResponse;
import com.wellpoint.wpd.common.contract.response.RetreiveContractRuleTypeResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveAllPossibleAnswerResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveBasicInfoResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveBenefitGeneralInfoResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveBenefitHeadingsResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveBenefitLinesResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveCodedNonCodedBenefitHeadingsResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveContractBenefitAdministrationResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveContractBenefitComponentResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveContractBenefitDefinitionResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveContractBenefitMandateResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveContractNoteResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveContractPricingInfoResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveContractProductAdminOptionOverrideResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveContractProductAdminResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveContractProductResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveContractSpecificInfoResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveContractStandardBenefitResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveDateSegmentCommentResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveDateSegmentsResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveDeletedDSResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveReservedContractResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveRulesResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveSelectedCommentResponse;
import com.wellpoint.wpd.common.contract.response.SaveContractAdaptedInfoResponse;
import com.wellpoint.wpd.common.contract.response.SaveContractAdministrationResponse;
import com.wellpoint.wpd.common.contract.response.SaveContractBasicInfoResponse;
import com.wellpoint.wpd.common.contract.response.SaveContractBenefitAdministrationResponse;
import com.wellpoint.wpd.common.contract.response.SaveContractBenefitDefinitionResponse;
import com.wellpoint.wpd.common.contract.response.SaveContractBenefitNoteResponse;
import com.wellpoint.wpd.common.contract.response.SaveContractHeadingsResponse;
import com.wellpoint.wpd.common.contract.response.SaveContractProductAdminResponse;
import com.wellpoint.wpd.common.contract.response.SaveContractSpecificInfoResponse;
import com.wellpoint.wpd.common.contract.response.SaveDateSegmentCommentResponse;
import com.wellpoint.wpd.common.contract.response.SavePricingInfoResponse;
import com.wellpoint.wpd.common.contract.response.SaveReservedContractResponse;
import com.wellpoint.wpd.common.contract.response.SaveRuleInformationResponse;
import com.wellpoint.wpd.common.indicativemapping.response.ConfirmImportIndicativeMappingResponse;
import com.wellpoint.wpd.common.indicativemapping.response.CreateIndicativeMappingResponse;
import com.wellpoint.wpd.common.indicativemapping.response.DeleteIndicativeMappingResponse;
import com.wellpoint.wpd.common.indicativemapping.response.EditIndicativeMappingResponse;
import com.wellpoint.wpd.common.indicativemapping.response.IndicativeDetailResponse;
import com.wellpoint.wpd.common.indicativemapping.response.RetrieveIndicativeMappingResponse;
import com.wellpoint.wpd.common.indicativemapping.response.SearchIndicativeMappingResponse;
import com.wellpoint.wpd.common.item.response.CatalogLookUpResponse;
import com.wellpoint.wpd.common.item.response.ItemSoftDeleteResponse;
import com.wellpoint.wpd.common.item.response.RetrieveItemResponse;
import com.wellpoint.wpd.common.item.response.SaveItemResponse;
import com.wellpoint.wpd.common.item.response.SearchItemResponse;
import com.wellpoint.wpd.common.legacycontract.response.RetrieveHeadingsResponse;
import com.wellpoint.wpd.common.legacycontract.response.RetrieveLegacyContractNotesResponse;
import com.wellpoint.wpd.common.legacycontract.response.RetrieveLegacyContractResponse;
import com.wellpoint.wpd.common.legacycontract.response.RetrieveLegacyLookUpResponse;
import com.wellpoint.wpd.common.mandate.response.CheckOutMandateResponse;
import com.wellpoint.wpd.common.mandate.response.CopyMandateResponse;
import com.wellpoint.wpd.common.mandate.response.CreateMandateResponse;
import com.wellpoint.wpd.common.mandate.response.DeleteMandateResponse;
import com.wellpoint.wpd.common.mandate.response.LocateMandateResponse;
import com.wellpoint.wpd.common.mandate.response.MandateCheckInResponse;
import com.wellpoint.wpd.common.mandate.response.RetrieveMandateResponse;
import com.wellpoint.wpd.common.migration.response.CancelMigrationResponse;
import com.wellpoint.wpd.common.migration.response.ConfirmMigrationContractResponse;
import com.wellpoint.wpd.common.migration.response.MigrationContractRulesResponse;
import com.wellpoint.wpd.common.migration.response.MigrationGenerateReportResponse;
import com.wellpoint.wpd.common.migration.response.MigrationPricingInfoResponse;
import com.wellpoint.wpd.common.migration.response.MigrationProductInfoResponse;
import com.wellpoint.wpd.common.migration.response.RetrieveBenefitDetailsResponse;
import com.wellpoint.wpd.common.migration.response.RetrieveMigGeneralInfoResponse;
import com.wellpoint.wpd.common.migration.response.RetrieveMigVariableMappingResponse;
import com.wellpoint.wpd.common.migration.response.SaveLastAccessPageInfoResponse;
import com.wellpoint.wpd.common.migration.response.SaveLegacyContractResponse;
import com.wellpoint.wpd.common.migration.response.SaveMigGeneralInfoResponse;
import com.wellpoint.wpd.common.migration.response.SaveMigVariableMappingResponse;
import com.wellpoint.wpd.common.migration.response.SaveMigrationNotesResponse;
import com.wellpoint.wpd.common.notes.response.BenefitLineNotesResponse;
import com.wellpoint.wpd.common.notes.response.BnftCompNotesToQuestionAttachmentResponse;
import com.wellpoint.wpd.common.notes.response.ContractAONotesToQuestionAttachmentResponse;
import com.wellpoint.wpd.common.notes.response.ContractNotesToQuestionAttachmentResponse;
import com.wellpoint.wpd.common.notes.response.ContractTeiredNotesToQuestionAttachmentResponse;
import com.wellpoint.wpd.common.notes.response.CreateNotesDataDomainResponse;
import com.wellpoint.wpd.common.notes.response.CreateNotesResponse;
import com.wellpoint.wpd.common.notes.response.DeleteNotesResponse;
import com.wellpoint.wpd.common.notes.response.LocateTargetSystemsForNotesResponse;
import com.wellpoint.wpd.common.notes.response.NoteDomainResponse;
import com.wellpoint.wpd.common.notes.response.NotesAttachmentResponse;
import com.wellpoint.wpd.common.notes.response.NotesCheckInResponse;
import com.wellpoint.wpd.common.notes.response.NotesCheckOutResponse;
import com.wellpoint.wpd.common.notes.response.NotesCopyResponse;
import com.wellpoint.wpd.common.notes.response.NotesSearchResponse;
import com.wellpoint.wpd.common.notes.response.ProductAONotesToQuestionAttachmentResponse;
import com.wellpoint.wpd.common.notes.response.QuestionNotesPopUpResponse;
import com.wellpoint.wpd.common.notes.response.RetrieveNotesResponse;
import com.wellpoint.wpd.common.notes.response.ViewAllVersionsNotesResponse;
import com.wellpoint.wpd.common.popup.response.PopupResponse;
import com.wellpoint.wpd.common.product.response.DeleteProductAdminResponse;
import com.wellpoint.wpd.common.product.response.DeleteProductBenefitNoteResponse;
import com.wellpoint.wpd.common.product.response.DeleteProductComponentNoteResponse;
import com.wellpoint.wpd.common.product.response.DeleteProductNoteResponse;
import com.wellpoint.wpd.common.product.response.DeleteProductResponse;
import com.wellpoint.wpd.common.product.response.DeleteProductRuleResponse;
import com.wellpoint.wpd.common.product.response.HideProductAdminOptionResponse;
import com.wellpoint.wpd.common.product.response.LoadProductBenefitNoteResponse;
import com.wellpoint.wpd.common.product.response.LoadProductComponentNoteResponse;
import com.wellpoint.wpd.common.product.response.ProductAdminDeleteResponse;
import com.wellpoint.wpd.common.product.response.ProductAdminResponse;
import com.wellpoint.wpd.common.product.response.ProductBenefitComponentDeleteResponse;
import com.wellpoint.wpd.common.product.response.ProductBenefitComponentResponse;
import com.wellpoint.wpd.common.product.response.ProductBenefitMndateRetrieveResponse;
import com.wellpoint.wpd.common.product.response.ProductBenefitTierValidationResponse;
import com.wellpoint.wpd.common.product.response.ProductLineOverrideNotesResponse;
import com.wellpoint.wpd.common.product.response.ProductQuestionNoteResponse;
import com.wellpoint.wpd.common.product.response.ProductQuestionNotesPopUpResponse;
import com.wellpoint.wpd.common.product.response.ProductRuleRefDataResponse;
import com.wellpoint.wpd.common.product.response.QuestionDeleteResponse;
import com.wellpoint.wpd.common.product.response.RetreiveProductRuleTypeResponse;
import com.wellpoint.wpd.common.product.response.RetrieveBenefitTierDefnResponse;
import com.wellpoint.wpd.common.product.response.RetrieveProductAdminOptionResponse;
import com.wellpoint.wpd.common.product.response.RetrieveProductBenefitAdminResponse;
import com.wellpoint.wpd.common.product.response.RetrieveProductBenefitDefinitionResponse;
import com.wellpoint.wpd.common.product.response.RetrieveProductBenefitResponse;
import com.wellpoint.wpd.common.product.response.RetrieveProductComponentHierarchyResponse;
import com.wellpoint.wpd.common.product.response.RetrieveProductNoteResponse;
import com.wellpoint.wpd.common.product.response.RetrieveProductQuestionareResponse;
import com.wellpoint.wpd.common.product.response.RetrieveProductResponse;
import com.wellpoint.wpd.common.product.response.RetrieveProductTierDefnResponse;
import com.wellpoint.wpd.common.product.response.RetrieveValidProductStructuresResponse;
import com.wellpoint.wpd.common.product.response.SaveProductAdminResponse;
import com.wellpoint.wpd.common.product.response.SaveProductBenefitAdminResponse;
import com.wellpoint.wpd.common.product.response.SaveProductBenefitAdministrationResponse;
import com.wellpoint.wpd.common.product.response.SaveProductBenefitDefinitionResponse;
import com.wellpoint.wpd.common.product.response.SaveProductBenefitNoteResponse;
import com.wellpoint.wpd.common.product.response.SaveProductComponentNoteResponse;
import com.wellpoint.wpd.common.product.response.SaveProductComponentResponse;
import com.wellpoint.wpd.common.product.response.SaveProductComponentRuleInformationResponse;
import com.wellpoint.wpd.common.product.response.SaveProductNoteResponse;
import com.wellpoint.wpd.common.product.response.SaveProductResponse;
import com.wellpoint.wpd.common.product.response.SaveProductRulesResponse;
import com.wellpoint.wpd.common.product.response.SearchProductResponse;
import com.wellpoint.wpd.common.product.response.UpdateProductBenefitGeneralInformationResponse;
import com.wellpoint.wpd.common.product.response.UpdateProductQuestionareResponse;
import com.wellpoint.wpd.common.productstructure.response.AttachNotesToQuestionResponseForPS;
import com.wellpoint.wpd.common.productstructure.response.DeleteProductStructureResponse;
import com.wellpoint.wpd.common.productstructure.response.ProductStructureLifeCycleResponse;
import com.wellpoint.wpd.common.productstructure.response.ProductStructureNotesResponse;
import com.wellpoint.wpd.common.productstructure.response.RetrieveBenefitComponentResponse;
import com.wellpoint.wpd.common.productstructure.response.RetrieveBenefitDefenitionResponse;
import com.wellpoint.wpd.common.productstructure.response.RetrieveBenefitMandatesResponse;
import com.wellpoint.wpd.common.productstructure.response.RetrieveComponentFromTreeResponse;
import com.wellpoint.wpd.common.productstructure.response.RetrieveProdStructureComponentHierarchyResponse;
import com.wellpoint.wpd.common.productstructure.response.RetrieveProductStructureBenefitAdministrationResponse;
import com.wellpoint.wpd.common.productstructure.response.RetrieveProductStructureResponse;
import com.wellpoint.wpd.common.productstructure.response.RetrieveProductStructureVersionsResponse;
import com.wellpoint.wpd.common.productstructure.response.SaveAdminOptionResponseForPS;
import com.wellpoint.wpd.common.productstructure.response.SaveBenefitAdministrationResponse;
import com.wellpoint.wpd.common.productstructure.response.SaveProductStructureBenefitDefinitionResponse;
import com.wellpoint.wpd.common.productstructure.response.SaveProductStructureResponse;
import com.wellpoint.wpd.common.productstructure.response.SearchProductStructureResponse;
import com.wellpoint.wpd.common.question.response.CheckOutQuestionResponse;
import com.wellpoint.wpd.common.question.response.DeleteQuestionResponse;
import com.wellpoint.wpd.common.question.response.PublishQuestionResponse;
import com.wellpoint.wpd.common.question.response.QuestionViewResponse;
import com.wellpoint.wpd.common.question.response.RetrieveQuestionResponse;
import com.wellpoint.wpd.common.question.response.SaveQuestionResponse;
import com.wellpoint.wpd.common.question.response.ScheduleForTestQuestionResponse;
import com.wellpoint.wpd.common.question.response.SearchQuestionResponse;
import com.wellpoint.wpd.common.question.response.TestFailQuestionResponse;
import com.wellpoint.wpd.common.question.response.TestPassQuestionResponse;
import com.wellpoint.wpd.common.question.response.UnlockQuestionResponse;
import com.wellpoint.wpd.common.refdata.response.RefDataDomainValidationResponse;
import com.wellpoint.wpd.common.refdata.response.ReferenceDataLookupResponse;
import com.wellpoint.wpd.common.referencemapping.response.CreateReferenceMappingResponse;
import com.wellpoint.wpd.common.referencemapping.response.DeleteReferenceMappingResponse;
import com.wellpoint.wpd.common.referencemapping.response.EditReferenceMappingResponse;
import com.wellpoint.wpd.common.referencemapping.response.RetrieveReferenceMappingResponse;
import com.wellpoint.wpd.common.referencemapping.response.SearchReferenceMappingResponse;
import com.wellpoint.wpd.common.report.response.ContractReportResponse;
import com.wellpoint.wpd.common.security.response.DeleteModuleTaskAssociationResponse;
import com.wellpoint.wpd.common.security.response.DeleteTaskAssociationResponse;
import com.wellpoint.wpd.common.security.response.LocateSubTaskResponse;
import com.wellpoint.wpd.common.security.response.ModuleDeleteResponse;
import com.wellpoint.wpd.common.security.response.ModuleSearchResponse;
import com.wellpoint.wpd.common.security.response.RetrieveModuleAssociationResponse;
import com.wellpoint.wpd.common.security.response.RetrieveRoleAssociationResponse;
import com.wellpoint.wpd.common.security.response.RetrieveRoleModuleAssociationResponse;
import com.wellpoint.wpd.common.security.response.RetrieveRoleResponse;
import com.wellpoint.wpd.common.security.response.RetrieveTaskResponse;
import com.wellpoint.wpd.common.security.response.RoleDeleteResponse;
import com.wellpoint.wpd.common.security.response.RoleModuleLookUpResponse;
import com.wellpoint.wpd.common.security.response.SaveModuleAssociationResponse;
import com.wellpoint.wpd.common.security.response.SaveModuleResponse;
import com.wellpoint.wpd.common.security.response.SaveRoleAssociationResponse;
import com.wellpoint.wpd.common.security.response.SaveRoleModuleAssociationResponse;
import com.wellpoint.wpd.common.security.response.SaveRoleResponse;
import com.wellpoint.wpd.common.security.response.SaveSubTaskAssociationResponse;
import com.wellpoint.wpd.common.security.response.SaveSubTaskResponse;
import com.wellpoint.wpd.common.security.response.SaveTaskAssociationResponse;
import com.wellpoint.wpd.common.security.response.SaveTaskResponse;
import com.wellpoint.wpd.common.security.response.SearchRoleResponse;
import com.wellpoint.wpd.common.security.response.SubTaskLookUpResponse;
import com.wellpoint.wpd.common.security.response.TaskLookUpResponse;
import com.wellpoint.wpd.common.standardbenefit.response.AdminLevelResponse;
import com.wellpoint.wpd.common.standardbenefit.response.AdministrationOptionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.ApproveStandardBenefitResponse;
import com.wellpoint.wpd.common.standardbenefit.response.BenefitDefinitionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.BenefitQuestionNoteResponse;
import com.wellpoint.wpd.common.standardbenefit.response.CreateBenefitAdministrationResponse;
import com.wellpoint.wpd.common.standardbenefit.response.CreateBenefitTierDefinitionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.DeleteAdministrationOptionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.DeleteBenefitAdministrationResponse;
import com.wellpoint.wpd.common.standardbenefit.response.DeleteBenefitDefinitionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.DeleteBenefitMandateResponse;
import com.wellpoint.wpd.common.standardbenefit.response.DeleteStandardBenefitNotesResponse;
import com.wellpoint.wpd.common.standardbenefit.response.GetBenefitTierDefinitionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.GroupRuleResponse;
import com.wellpoint.wpd.common.standardbenefit.response.HideAdminOptionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.HideQuestionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.LocateAdministrationOptionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.LocateBenefitAdministrationResponse;
import com.wellpoint.wpd.common.standardbenefit.response.LocateBenefitDefinitionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.LocateBenefitLevelListResponse;
import com.wellpoint.wpd.common.standardbenefit.response.LocateBenefitMandateResponse;
import com.wellpoint.wpd.common.standardbenefit.response.LocateMandateListResponse;
import com.wellpoint.wpd.common.standardbenefit.response.LocateSelectedQuestionListResponse;
import com.wellpoint.wpd.common.standardbenefit.response.LocateStandardBenefitNotesResponse;
import com.wellpoint.wpd.common.standardbenefit.response.LookupAdminOptionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.NonAdjBenefitMandateResponse;
import com.wellpoint.wpd.common.standardbenefit.response.PossibleAnswersResponse;
import com.wellpoint.wpd.common.standardbenefit.response.PublishStandardBenefitResponse;
import com.wellpoint.wpd.common.standardbenefit.response.RejectStandardBenefitResponse;
import com.wellpoint.wpd.common.standardbenefit.response.RetrieveAdministrationOptionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.RetrieveBenefitAdministrationResponse;
import com.wellpoint.wpd.common.standardbenefit.response.RetrieveBenefitDefinitionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.RetrieveNonAdjMandateResponse;
import com.wellpoint.wpd.common.standardbenefit.response.RetrieveQuestionaireResponse;
import com.wellpoint.wpd.common.standardbenefit.response.RuleResponse;
import com.wellpoint.wpd.common.standardbenefit.response.ScheduleForTestSBResponse;
import com.wellpoint.wpd.common.standardbenefit.response.StandardBenefitCheckInResponse;
import com.wellpoint.wpd.common.standardbenefit.response.StandardBenefitCheckOutResponse;
import com.wellpoint.wpd.common.standardbenefit.response.StandardBenefitCopyResponse;
import com.wellpoint.wpd.common.standardbenefit.response.StandardBenefitDeleteResponse;
import com.wellpoint.wpd.common.standardbenefit.response.StandardBenefitNoteAttachmentResponse;
import com.wellpoint.wpd.common.standardbenefit.response.StandardBenefitResponse;
import com.wellpoint.wpd.common.standardbenefit.response.StandardBenefitSearchResponse;
import com.wellpoint.wpd.common.standardbenefit.response.StandardBenefitUnLockResponse;
import com.wellpoint.wpd.common.standardbenefit.response.StandardBenefitVersionsLifeCycleResponse;
import com.wellpoint.wpd.common.standardbenefit.response.TestFailStandardBenefitResponse;
import com.wellpoint.wpd.common.standardbenefit.response.TestPassStandardBenefitResponse;
import com.wellpoint.wpd.common.standardbenefit.response.TierLookUpResponse;
import com.wellpoint.wpd.common.standardbenefit.response.UpdateBenefitAdministrationResponse;
import com.wellpoint.wpd.common.standardbenefit.response.UpdateQuestionnaireResponse;
import com.wellpoint.wpd.common.subcatalog.response.ItemAssociationDeleteResponse;
import com.wellpoint.wpd.common.subcatalog.response.ItemLookUpResponse;
import com.wellpoint.wpd.common.subcatalog.response.SaveItemAssociationResponse;
import com.wellpoint.wpd.common.subcatalog.response.SubCatalogDeleteResponse;
import com.wellpoint.wpd.common.subcatalog.response.SubCatalogSaveResponse;
import com.wellpoint.wpd.common.subcatalog.response.SubCatalogSearchResponse;
import com.wellpoint.wpd.common.task.response.TaskDeleteResponse;
import com.wellpoint.wpd.common.task.response.TaskSearchResponse;
import com.wellpoint.wpd.common.tierdefinition.response.ContractTierDefSaveResponse;
import com.wellpoint.wpd.common.tierdefinition.response.ContractTierDeleteResponse;
import com.wellpoint.wpd.common.tierdefinition.response.DeleteLevelFromTierResponse;
import com.wellpoint.wpd.common.tierdefinition.response.ProductTierDefSaveResponse;
import com.wellpoint.wpd.common.tierdefinition.response.ProductTierDeleteResponse;
import com.wellpoint.wpd.common.tierdefinition.response.TierDefinitionRetrieveResponse;

/**
 * Factory class for creating Responses.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: WPDResponseFactory.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public class WPDResponseFactory {

	public static final int SEARCH_ADMIN_METHOD_RESPONSE = 1113;
	public static final int DELETE_ADMIN_METHOD_RESPONSE = 1119;
	public static final int SEARCH_ADMIN_METHOD_MAPPING_RESPONSE = 1120;
	public static final int VIEW_ADMIN_METHOD_MAPPING_RESPONSE = 1150;
	public static final int EDIT_ADMIN_METHOD_RESPONSE = 1118;
	public static final int VIEW_ADMIN_METHOD_RESPONSE = 1114;
	public static final int ADMINMETHOD_VIEWALL_RESPONSE = 1115;
	public static final int SAVE_SERVICE_MAPPING_RESPONSE = 1111;
	
	 public static final int ACCUMULATOR_FILTER_RESPONSE = 416; 
	
	public static final int ADMIN_METHOD_SPS_VALID_RESPONSE=5555;
	
	public static final int RETRIEVE_SERVICE_MAPPING_RESPONSE = 1112;
	
	public static final int BNFT_COMP_NOTES_TO_QUESTION_ATTACHMENT_RESPONSE = 60000;
	
	public static final int CNTRCT_NOTES_TO_QUESTION_ATTACHMENT_RESPONSE = 800000;
	
	public static final int CNTRCT_AO_NOTES_TO_QUESTION_ATTACHMENT_RESPONSE = 900000;
	
	public static final int CNTRCT_TEIRED_AO_NOTES_TO_QUESTION_ATTACHMENT_RESPONSE = 600000;
	
	public static final int PROD_AO_NOTES_TO_QUESTION_ATTACHMENT_RESPONSE = 1000000;
	
	// For AdminMethods
	public static final int ADMIN_METHODS_VALIDATION_STATUS_RESPONSE = 1000;
	
    //migration waizard
    public static final int RETRIEVE_LEGACY_CONTRACT_RESPONSE = 611;

    public static final int SAVE_LEGACY_CONTRACT_RESPONSE = 612;
    
    public static final int SAVE_MIGRATED_NOTES_RESPONSE = 613;
    
    public static final int RETRIEVE_BENEFIT_DETAILS_RESPONSE = 614;

    //for contract module

    public static final int CONTRACT_TRANSFER_LOG_RESPONSE = 987;
    
    public static final int RULES_SEARCH_RESPONSE = 980;

    public static final int SAVE_RESERVED_RESPONSE = 994;

    public static final int RETRIEVE_RESERVED_RESPONSE = 995;

    public static final int DATE_SEGMENTS_SEARCH_RESPONSE = 990;
    
    public static final int BENEFIT_SEARCH_RESPONSE = 1982;

    public static final int DATE_SEGMENTS_CREATE_RESPONSE = 991;

    public static final int SAVE_CONTRACT_SPECIFICINFO_RESPONSE = 120;
    
    public static final int SAVE_CONTRACT_ADAPTEDINFO_RESPONSE = 456;

    public static final int RETRIEVE_CONTRACT_BASICINFO_RESPONSE = 244;

    public static final int SAVE_CONTRACT_BASIC_INFO_RESPONSE = 130;

    public static final int SAVE_PRODUCT_BENEFIT_ADMIN_RESPONSE = 118;

    public static final int STANDARD_BENEFIT_RESPONSE = 1;

    public static final int BENEFIT_DEFINITION_RESPONSE = 2;

    public static final int SAVE_CATALOG_RESPONSE = 75;

    public static final int EDIT_CATALOG_RESPONSE = 76;

    public static final int DELETE_CATALOG_RESPONSE = 78;

    public static final int RETRIEVE_CATALOG_RESPONSE = 77;
    
    public static final int RETRIEVE_SRDA_CATALOG_RESPONSE = 81;

    public static final int SEARCH_CATALOG_RESPONSE = 1022;

    public static final int SAVE_SUB_CATALOG_RESPONSE = 79;

    public static final int DELETE_SUB_CATALOG_RESPONSE = 80;

    public static final int SEARCH_SUB_CATALOG_RESPONSE = 1023;

    public static final int SEARCH_ITEM_LOOKUP_REPONSE = 80;

    public static final int REFERENCE_DATA_LOOKUP_RESPONSE = 1021;
    
    public static final int QUES_ANSWER_LOOKUP_RESPONSE = 1138821;    
    
    public static final int REFERENCE_DOMAIN_VALIDATION_RESPONSE = 10111;

    public static final int SEARCH_ITEM_RESPONSE = 1024;

    public static final int SEARCH_SUB_CATALOG_ITEM_RESPONSE = 1025;

    public static final int DELETE_ITEM_ASSOCIATION_RESPONSE = 890;

    public static final int SAVE_TASK_RESPONSE = 891;

    public static final int SAVE_SUB_TASK_RESPONSE = 222;

    public static final int SEARCH_ROLE_RESPONSE = 897;

    public static final int DELETE_TASK_ASSOCIATION_RESPONSE = 567;

    public static final int RETRIEVE_ROLE_ASSOCIATION_RESPONSE = 823;

    public static final int RETRIEVE_ROLE_RESPONSE = 303;

    public static final int RETRIEVE_TASK_RESPONSE = 892;

    public static final int SUB_TASK_LOOKUP_RESPONSE = 893;

    public static final int SUB_TASK_LOCATE_RESPONSE = 894;

    public static final int SAVE_SUBTASK_ASSOCIATION_RESPONSE = 895;

    public static final int SAVE_TASK_ASSOCIATION_RESPONSE = 887;

    public static final int DELETE_SUBTASK_ASSOCIATION_RESPONSE = 896;

    public static final int MODULE_TASK_LOOKUP_RESPONSE = 898;

    public static final int ROLE_TASK_LOOKUP_RESPONSE = 888;

    public static final int MODULE_ASSOCIATION_RESPONSE = 899;

    public static final int SAVE_MODULE_ASSOCIATION_RESPONSE = 741;

    public static final int DELETE_MODULE_ASSOCIATION_RESPONSE = 742;

    public static final int RETRIEVE_ROLE_MODULE_RESPONSE = 743;

    public static final int ROLE_MODULE_LOOKUP_RESPONSE = 744;

    public static final int SAVE_ROLE_MODULE_ASSOCIATION_RESPONSE = 745;

    public static final int CREATE_ITEM_RESPONSE = 168;

    public static final int UPDATE_ITEM_RESPONSE = 169;

    public static final int RETRIEVE_ITEM_REPONSE = 170;
    
    public static final int CATALOG_LOOKUP_RESPONSE = 1782;

    public static final int DELETE_STATUS_EDIT_RESPONSE = 171;

    public static final int SEARCH_ITEM_LOOKUP_RESPONSE = 878;

    public static final int SAVE_ITEM_ASSOCIATION_RESPONSE = 879;

    public static final int SAVE_ADMIN_OPTION_FOR_CONTRACT = 881;

    public static final int RETRIEVE_CONTRACT_ADMIN_OPTION_FOR_CONTRACT = 883;
    
    public static final int MEMBERSHIP_INFO_RESPONSE =974;

    //ADMINISTRATION SECURITY MODULE

    public static final int SEARCH_MODULE_RESPONSE = 578;

    public static final int SAVE_ROLE_RESPONSE = 575;

    public static final int TASK_SEARCH_RESPONSE = 1019;

    public static final int DELETE_TASK_RESPONSE = 1018;

    public static final int DELETE_MODULE_RESPONSE = 1027;

    public static final int SAVE_MODULE_RESPONSE = 880;

    public static final int DELETE_ROLE_RESPONSE = 1017;

    public static final int RETRIEVE_BENEFIT_DEFINITION_RESPONSE = 3;

    public static final int LOCATE_BENEFIT_DEFINITION_RESPONSE = 4;

    public static final int RETRIEVE_PRODUCT_BENEFIT_ADMIN_RESPONSE = 117;

    public static final int RETRIEVE_CONTRACT_BENEFIT_DEFINITION_RESPONSE = 208;

    public static final int CONTRACT_SEARCH_RESPONSE = 934;

    public static final int RETRIEVE_CONTRACT_SPECIFICINFO_RESPONSE = 121;

    public static final int RETRIEVE_CONTRACT_SB_RESPONSE = 122;

    public static final int RETRIEVE_CONTRACT_BENEFIT_MANDATE_RESPONSE = 123;

    public static final int SAVE_CONTRACT_COMMENT_RESPONSE = 124;

    public static final int RETIEVE_DATESEGMENT_COMMENT_RESPONSE = 125;

    public static final int RETIEVE_SELECTED_COMMENT_RESPONSE = 126;

    public static final int RETRIEVE_CONTRACT_BENEFIT_ADMIN_RESPONSE = 127;
    
    public static final int RETRIEVE_ALL_POSSIBLE_ANSWER_ADMIN_OPTION_RESPONSE = 1028;
    
    public static final int SAVE_BENEFIT_ADMINISTRATION_FOR_CONTRACT = 128;

    public static final int RETRIEVE_CONTRACT_BENEFIT_GENERALINFO_RESPONSE = 129;

    public static final int CONTRACT_NOTES_ATTACH_RESPONSE = 750;

    public static final int CONTRACT_NOTES_DELETE_RESPONSE = 350;

    public static final int CONTRACT_BENEFIT_COMPONENT_NOTES_ATTACH_RESPONSE = 131;

    public static final int LOAD_CONTRACT_COMPONENT_NOTE_RESPONSE = 132;

    public static final int DELETE_CONTRACT_COMPONENT_NOTE_RESPONSE = 133;

    public static final int RETRIEVE_CONTRACT_NOTE_RESPONSE = 989;

    public static final int CONTRACT_BENEFIT_NOTE_RESPONSE = 331;

    public static final int LOAD_CONTRACT_BENEFIT_NOTE_RESPONSE = 161;

    public static final int RESERVED_CONTRACT_SEARCH_RESPONSE = 134;

    public static final int ROLE_ASSOCIATION_RESPONSE = 135;

    public static final int SAVE_ROLE_ASSOCIATION_RESPONSE = 136;

    // for mandate list response
    public static final int LOCATE_MANDATE_LIST_RESPONSE = 21;

    public static final int RETRIEVE_NON_ADJ_MANDATE_RESPONSE = 22;

    public static final int DELETE_BENEFIT_DEFINITION_RESPONSE = 5;

    public static final int SAVE_PRODUCT_STRUCTURE_RESPONSE = 6;

    public static final int NON_ADJ_BENEFIT_MANDATE_RESPONSE = 9;

    public static final int SB_SEARCH_RESPONSE = 10;

    public static final int LOCATE_BENEFIT_MANDATE_RESPONSE = 11;

    public static final int DELETE_BENEFIT_MANDATE_RESPONSE = 32;

    public static final int CREATE_BENEFIT_ADMINISTRATION_RESPONSE = 12;

    public static final int LOCATE_BENEFIT_ADMINISTRATION_RESPONSE = 19;

    public static final int LOOK_UP_ADMIN_OPTION_RESPONSE = 26;

    public static final int SAVE_QUESTION_RESPONSE = 13;

    public static final int RETRIEVE_QUESTION_RESPONSE = 14;

    public static final int SEARCH_QUESTION_RESPONSE = 15;

    public static final int DELETE_QUESTION_RESPONSE = 16;

    public static final int UPDATE_BENEFIT_ADMINISTRATION_RESPONSE = 17;

    public static final int RETRIEVE_BENEFIT_ADMINISTRATION_RESPONSE = 29;

    public static final int DELETE_BENEFIT_ADMINISTRATION_RESPONSE = 30;

    public static final int SEARCH_PRODUCT_RESPONSE = 18;

    public static final int SEARCH_PRODUCT_STRUCTURE_RESPONSE = 20;

    public static final int SAVE_PRODUCT_STRUCTURE_BENEFIT_DEFINITION_RESPONSE = 205;

    public static final int RETRIEVE_BENEFIT_DEFENITION_RESPONSE = 27;

    public static final int RETRIEVE_PRODUCT_STRUCTURE_RESPONSE = 23;

    public static final int RETRIEVE_PRODUCT_STRUCTURE_VERSIONS_RESPONSE = 31;

    public static final int SAVE_PRODUCT_RESPONSE = 101;

    public static final int RETRIEVE_PRODUCT_RESPONSE = 102;

    public static final int RETRIEVE_VALID_PRD_STR_RESPONSE = 103;

    public static final int SAVE_PROD_COMPONENT_RESPONSE = 104;

    public static final int PROD_SEARCH_RESPONSE = 105;

    public static final int DELETE_PRODUCT_STRUCTURE_RESPONSE = 106;

    public static final int RETRIEVE_MANDATES_RESPONSE = 114;

    public static final int RETRIEVE_COMPONENT_FROM_TREE_RESPONSE = 107;

    public static final int RETRIEVE_BENEFIT_COMPONENT_RESPONSE = 25;

    public static final int CREATE_BENEFIT_ADMINOPTION_RESPONSE = 40;

    public static final int LOCATE_BENEFIT_ADMINOPTION_RESPONSE = 70;

    public static final int RETRIEVE_BENEFIT_ADMINOPTION_RESPONSE = 71;

    public static final int LOCATE_BENEFITLEVEL_LIST_RESPONSE = 901;

    public static final int RETRIEVE_PRODUCT_BENEFIT_RESPONSE = 111;

    public static final int UPDATE_QUESTION_RESPONSE = 902;

    public static final int DELETE_PRODUCT_STUCTURE_RESPONSE = 200;

    public static final int STANDARD_BENEFIT_DELETE_RESPONSE = 201;

    public static final int STANDARD_BENEFIT_VERSIONS_LIFECYCLE_RESPONSE = 204;

    public static final int STANDARD_BENEFIT_CHECKOUT_RESPONSE = 198;
    
    public static final int SEARCH_SERVICE_TYPE_MAPPING_RESPONSE = 6538;

    // for selectedQuestionListResponse
    public static final int SELECTED_QUESTION_LIST = 24;

    // for hideQuestionResponse
    public static final int HIDE_QUESTION_RESPONSE = 34;

    public static final int DELETE_BENEFIT_ADMINOPTION_RESPONSE = 72;

    // for possibleAnswersResponse
    public static final int POSSIBLE_ANSWERS_RESPONSE = 44;

    //	 for DELETE product
    public static final int DELETE_PRODUCT_RESPONSE = 108;

    public static final int DELETE_PRODUCT_BENEFIT_RESPONSE = 112;

    public static final int RETRIEVE_PRODUCT_BENEFIT_COMPONENT_RESPONSE = 113;

    //START : BENEFITCOMPONENT
    public static final int RETRIEVE_BENEFIT_COMPONENT_VIEW_RESPONSE = 1005;
    
    public static final int ADD_ROOT_QUESTION_RESPONSE = 420;
    
    public static final int LOCATE_ROOT_QUESTION_RESPONSE = 421;

    public static final int LOCATE_NON_ADJ_BENEFIT_MANDATE_RESPONSE = 28;

    public static final int SAVE_BENEFIT_COMPONENT_RESPONSE = 60;

    public static final int DELETE_BENEFIT_COMPONENT_RESPONSE = 1006;

    public static final int CHECKOUT_BENEFIT_COMPONENT_RESPONSE = 61;

    public static final int PUBLISH_BENEFIT_COMPONENT_RESPONSE = 62;
    
    public static final int SEARCH_VARIABLE_MAPPING_RESPONSE = 665;
    
    public static final int QUESTION_DELETE_RESPONSE = 332;
    public static final int SEARCH_SPS_MAINTAIN_RESPONSE = 666;
    
    /****IND RESPONSES***/
    public static final int SEARCH_IND_MAINTAIN_RESPONSE = 6000;
    public static final int DELETE_IND_MAINTAIN_RESPONSE = 6001;
    public static final int RETRIEVE_IND_MAINTAIN_RESPONSE = 6002;
    public static final int CREATE_IND_MAINTAIN_RESPONSE = 2727;
    
    /*** Ref Mapping */
    public static final int CREATE_REF_MAPPING_RESPONSE = 272730133;
    public static final int DELETE_REF_MAPPING_RESPONSE = 272710113;
    public static final int RETRIEVE_REF_MAPPING_RESPONSE = 292010110;
    
    
    public static final int EDIT_REF_MAPPING_RESPONSE = 9272173;
    public static final int SEARCH_REF_MAPPING_RESPONSE = 9071173;
    public static final int EDIT_IND_MAINTAIN_RESPONSE = 3737;

    public static final int HIDE_PRODUCT_ADMIN_OPTION_RESPONSE = 669;	
    
    public static final int DELETE_SPS_MAPPING_RESPONSE = 7777;
 
    public static final int ADMIN_MTHD_SPS_VALD_RESPONSE=2050;
    
    public static final int CHECKOUT_DATESEGMENT_RESPONSE =2051;
    
    /* admin method maintain */
    public static final int CREATE_ADMINMETHOD_RESPONSE = 204123930;
    public static final int CREATE_ADMINMETHODMAPPING_RESPONSE = 2941930; 
    public static final int EDIT_ADMINMETHODMAPPING_RESPONSE = 2148930; 
    public static final int DELETE_ADMINMETHODMAPPING_RESPONSE = 2118000; 
    
    
    
    

    
        
    	/**
		 * @return
		 */
		private static WPDResponse getMigrationGeneralInfoResponse() {
			RetrieveMigGeneralInfoResponse migrationGeneralInfoResponse= new RetrieveMigGeneralInfoResponse();
			return migrationGeneralInfoResponse;
		}

		/**
		 * @return
		 */
		private static WPDResponse getProductStructureNotesResponse() {
			ProductStructureNotesResponse productStructureNotesResponse= new ProductStructureNotesResponse();
			return productStructureNotesResponse;
		}
		
		/**
		 * @return
		 */
		private static WPDResponse saveMigrationGeneralInfoResponse() {
			SaveMigGeneralInfoResponse saveGeneralInfoResponse= new SaveMigGeneralInfoResponse();
			return saveGeneralInfoResponse;
		}
		
	
        
    public static final int SCHEDULE_TEST_BENEFIT_COMPONENT_RESPONSE = 63;

    public static final int TEST_PASS_BENEFIT_COMPONENT_RESPONSE = 64;

    public static final int TEST_FAIL_BENEFIT_COMPONENT_RESPONSE = 65;

    //END : BENEFITCOMPONENT

    public static final int RETRIEVE_PRODUCT_STRUCTURE_BENEFIT_ADMINISTATION_RESPONSE = 202;

    public static final int SAVE_BENEFIT_ADMINISTRATION_FOR_PRODUCT_STRUCTURE = 203;

    public static final int QUESTION_VIEW_RESPONSE = 300;

    public static final int RETRIEVE_BENEFIT__RESPONSE = 301;

    public static final int STANDARD_BENEFIT_CHECKIN_RESPONSE = 1100;

    public static final int ATTACH_BENEFIT_COMPONENT_NOTES_RESPONSE = 11001;

    public static final int LOCATE_BENEFIT_COMPONENT_NOTES_RESPONSE = 11002;

    public static final int DELETE_BENEFIT_COMPONENT_NOTES_RESPONSE = 11003;

    // benefitComponentRetrieveForEdit
    public static final int BENEFIT_COMPONENT_RETRIEVE_FOR_EDIT = 400;

    public static final int RETRIEVE_PRODUCT_BENEFIT_DEFINITION_RESPONSE = 115;

    public static final int BENEFIT_COMPONENT_CHECKIN_RESPONSE = 335;

    //	 benefitComponentRetrieveFor View
    public static final int RETRIEVE_BENEFIT_VIEW_RESPONSE = 401;

    public static final int SAVE_PRODUCT_BENEFIT_DEFINITION_RESPONSE = 116;

    public static final int SAVE_CONTRACT_BENEFIT_DEFINITION_RESPONSE = 1161;

    //Responses for Admin Option module
    public static final int SAVE_ADMIN_OPTION_RESPONSE = 150;

    public static final int RETRIEVE_ADMIN_OPTION_RESPONSE = 151;

    public static final int DELETE_ADMIN_OPTION_RESPONSE = 152;

    public static final int SEARCH_ADMIN_OPTION_RESPONSE = 153;

    public static final int CHECKIN_ADMIN_OPTION_RESPONSE = 154;

    public static final int CHECKOUT_ADMIN_OPTION_RESPONSE = 155;

    public static final int SAVE_ADMIN_OPTION_QUESTION_RESPONSE = 156;

    public static final int RETRIEVE_ADMIN_OPTION_QUESTION_RESPONSE = 157;

    public static final int DELETE_ADMIN_OPTION_QUESTION_RESPONSE = 158;
    
    public static final int DELETE_ALL_ADMIN_OPTION_RESPONSE = 159;

    
    //  for locateComponentsBenefitDefinition
    public static final int LOCATE_COMPONENTS_BENEFIT_DEFINITION_RESPONSE = 402;

    // for updateBenefitLinesResponse
    public static final int UPDATE_BENEFIT_LINES_RESPONSE = 403;

    public static final int STANDARD_BENEFIT_COPY_RESPONSE = 1101;

    public static final int TEST_PASS_STD_BEN_RESPONSE = 1102;

    public static final int TEST_FAIL_STD_BEN_RESPONSE = 1103;

    public static final int COPY_MANDATE_RESPONSE = 406;

    public static final int CREATE_MANDATE_RESPONSE = 407;

    public static final int DELETE_MANDATE_RESPONSE = 408;

    public static final int CHECKOUT_MANDATE_RESPONSE = 409;

    public static final int CHECKIN_MANDATE_RESPONSE = 410;

    public static final int RETRIEVE_MANDATE_RESPONSE = 411;

    public static final int LOCATE_MANDATE_RESPONSE = 412;

    public static final int CREATE_BENEFIT_LEVEL_RESPONSE = 500;

    public static final int SAVE_BENEFIT_LEVEL_RESPONSE = 501;

    public static final int DELETE_BENEFIT_LEVEL_RESPONSE = 502;

    public static final int SEARCH_BENEFIT_LEVEL_RESPONSE = 503;
    
    public static final int SEARCH_BENEFIT_LEVEL_TERM_RESPONSE = 799;
    
    public static final int RULE_RESPONSE = 550;
    
    public static final int GROUP_RULE_RESPONSE = 696;

    public static final int DATA_TYPE_RETRIEVE_RESPONSE = 504;

    public static final int CHECKOUT_QUESTION_RESPONSE = 551;

    public static final int ADMIN_OPTION_VIEW_RESPONSE = 600;

    public static final int PUBLISH_STD_BENEFIT_RESPONSE = 166;
    
    public static final int UNLOCK_STD_BENEFIT_RESPONSE = 160;

    public static final int SCHEDULEFORTEST_QUESTION_RESPONSE = 552;

    public static final int PUBLISH_QUESTION_RESPONSE = 553;
    
    public static final int UNLOCK_QUESTION_RESPONSE = 221;

    public static final int COPY_BENEFIT_COMPONENT_RESPONSE = 601;

    //  for locateComponentsBenefitAdministrations
    public static final int LOCATE_COMPONENTS_BENEFIT_ADMIN_RESPONSE = 800;

    public static final int UPDATE_COMPONENTS_BENEFIT_ADMIN_RESPONSE = 801;

    public static final int SCHEDULE_FOR_TEST_STD_BEN_RESPONSE = 679;

    public static final int ADMIN_LEVEL_RESPONSE = 167;

    public static final int SCHEDULEFORTEST_ADMINOPTION_RESPONSE = 999;

    public static final int PUBLISH_ADMIN_OPTION_RESPONSE = 998;

    public static final int TEST_PASS_ADMIN_OPTION_RESPONSE = 997;

    public static final int TEST_PASS_QUESTION_RESPONSE = 996;

    public static final int TEST_FAIL_QUESTION_RESPONSE = 972;

    public static final int TEST_FAIL_ADMIN_OPTION_RESPONSE = 973;

    public static final int PRODUCT_STRUCTURE_LIFE_CYCLE_RESPONSE = 701;

    public static final int BENEFIT_HIERARCHY_UPDATE_RESPONSE = 777;

    public static final int BENEFIT_HIERARCHY_RESPONSE = 992;

    public static final int DELETE_BENEFIT_LINE_RESPONSE = 1001;

    public static final int CREATE_NOTES_RESPONSE = 1002;

    public static final int NOTE_DOMAIN_RESPONSE = 1003;

    public static final int RETRIEVE_NOTES_RESPONSE = 1004;

    public static final int PRICING_INFO_RESPONSE = 1015;

    public static final int LOCATE_PRICING_INFO_RESPOSE = 1020;

    public static final int DELETE_PRICING_INFO_RESPOSE = 1025;

    public static final int RETRIEVE_CONTRACT_PRODUCT = 1040;

    public static final int NOTES_SEARCH_RESPONSE = 1007;

    public static final int CREATE_NOTES_DATA_DOMAIN_RESPONSE = 1010;

    public static final int SAVE_PROD_NOTE_RESPONSE = 555;

    public static final int RETRIEVE_PRODUCT_NOTE_RESPONSE = 556;

    public static final int DELETE_PRODUCT_NOTE_RESPONSE = 557;

    public static final int LOAD_PRODUCT_COMPONENT_NOTE_RESPONSE = 558;

    public static final int SAVE_PRODUCT_COMPONENT_NOTE_RESPONSE = 559;

    public static final int DELETE_PRODUCT_COMPONENT_NOTE_RESPONSE = 560;

    public static final int LOAD_PRODUCT_BENEFIT_NOTE_RESPONSE = 561;

    public static final int SAVE_PRODUCT_BENEFIT_NOTE_RESPONSE = 562;

    public static final int DELETE_PRODUCT_BENEFIT_NOTE_RESPONSE = 563;

    public static final int DELETE_PRODUCT_ADMIN_RESPONSE = 564;

    public static final int PRODUCT_LINE_OVERRIDE_NOTES_RESPONSE = 565;

    public static final int NOTES_CHECKIN_RESPONSE = 1011;

    public static final int NOTES_ALLVERSIONS_RESPONSE = 1012;

    public static final int RETRIEVE_CONTRACT_PRICINGINFO_RESPONSE = 1030;

    public static final int RETRIEVE_CONTRACT_BENEFIT_COMPONENT_VIEW_RESPONSE = 1035;

    public static final int NOTES_ATTACHMENT_RESPONSE = 1036;

    public static final int BENEFIT_LINE_NOTES_RESPONSE = 1037;

    public static final int LOCATE_TARGET_SYSTEM_FOR_NOTES_RESPONSE = 1050;

    public static final int NOTES_CHECKOUT_RESPONSE = 1008;

    public static final int DELETE_NOTES_RESPONSE = 1052;

    public static final int NOTES_COPY_RESPONSE = 1009;

    public static final int LOCATE_PRODUCT_RESPONSE = 1085;

    public static final int SAVE_RULEID_CONTRACTBEN_RESPONSE = 1087;

    public static final int RETRIEVE_PRODUCT_ADMIN_OPTION_RESPONSE = 1089;
    
    public static final int CUSTOM_MESSAGE_RETRIEVE_RESPONSE = 4003;
    
    

    public static final int SAVE_BENEFIT_ADMINISTRATION_FOR_PRODUCT = 1090;

    //		Standard Benefit Notes Attachment
    public static final int ATTACH_STANDARD_BENEFIT_NOTES_RESPONSE = 90;

    public static final int LOCATE_STANDARD_BENEFIT_NOTES_RESPONSE = 89;

    public static final int DELETE_STANDARD_BENEFIT_NOTES_RESPONSE = 95;

    //		admin options
    public static final int SAVE_PROD_ADMIN_RESPONSE = 753;

    public static final int RETRIEVE_PROD_ADMIN_RESPONSE = 754;

    public static final int DELETE_PROD_ADMIN_RESPONSE = 756;

    public static final int RETRIEVE_BENEFIT_HEADINGS_RESPONSE = 1048;

    public static final int DELETE_CONTRACT_RESPONSE = 1110;
    
    
    public static final int DELETE_SERVICETYPE_RESPONSE = 2000;

    //		for locateBenefitlinenotes
    public static final int LOCATE_NOTES_FOR_BENEFIT_LINE = 900;

    public static final int ATTACH_BENEFIT_LINES_NOTES_RESPONSE = 911;

    //			for contract product admin option
    public static final int RETRIEVE_CONTRACT_PROD_ADMIN_RESPONSE = 1121;

    public static final int DELETE_CONTRACT_PRODUCT_ADMIN_RESPONSE = 1122;

    public static final int SAVE_CONTRACT_PROD_ADMIN_RESPONSE = 1123;

    public static final int APPROVE_ADMIN_OPTION_RESPONSE = 2777;
    
    public static final int UNLOCK_ADMIN_OPTION_RESPONSE = 43;

    public static final int REJECT_ADMIN_OPTION_RESPONSE = 2778;

    public static final int APPROVE_STD_BEN_RESPONSE = 2779;

    public static final int REJECT_STD_BEN_RESPONSE = 2780;

    public static final int APPROVE_BENEFIT_COMPONENT_RESPONSE = 2781;

    public static final int REJECT_BENEFIT_COMPONENT_RESPONSE = 2782;

    //	Contract BenefitLevelNotes Override
    public static final int OVERRIDE_BENFITLINE_NOTES_RESPONSE = 11011;

    // For DataFeed
    public static final int DATAFEED_REQUEST_CNTRCT_RETRIEVE = 1124;
    
    public static final int DATAFEED_REQUEST_DEL_DS_RETRIEVE = 10007;

    //Migration Wizard

    public static final int RETRIEVE_LEGACY_LOOKUP_RESPONSE = 2001;

    public static final int RETRIEVE_MIG_VARIABLE_MAPPING_RESPONSE = 2002;

    public static final int SAVE_MIG_VARIABLE_MAPPING_RESPONSE = 2003;

    public static final int MIGRATION_PRICING_INFO_RESPOSE = 2004;
    
    public static final int MIGRATION_PRODUCT_INFO_RESPOSE = 2005;

	 public static final int RETRIEVE_MIG_GENINFO_RESPONSE = 2006;
	 
	 public static final int SAVE_MIG_GENINFO_RESPONSE = 2008;
	 
	 public static final int CONFIRM_MIG_CONTRACT_RESPONSE = 2009;

	 public static final int LAST_ACCESS_PAGE_MIG_CONTRACT_RESPONSE = 2011;
	 
	 public static final int MIGRATION_GENERATE_REPORT_RESPONSE = 2010;
	 
	 public static final int SAVE_MIGRATION_NOTES_RESPONSE = 2020;
    //			Product Denial and Exclusion
    public static final int PRODUCT_RULE_REF_DATA_RESPONSE = 12001;

    public static final int SAVE_PRODUCT_RULES_RESPONSE = 12002;

    public static final int DELETE_PRODUCT_RULE_RESPONSE = 12003;

    public static final int RETRIEVE_BENEFIT_LINES_RESPONSE = 1680;

    public static final int PRODUCT_BENEFIT_MANDATE_RETRIEVE_RESPONSE = 1681;

    public static final int SAVE_CONTRACT_HEADINGS_RESPONSE = 1682;

    public static final int LEGACY_HEADING_RESPONSE= 1683;
    
    public static final int CANCEL_MIGRATION_RESPONSE = 2007;
    
    public static final int ADMIN_METHOD_POPUP_RESPONSE= 1687;
    
    public static final int ADMIN_METHOD_RESPONSE = 1688;
    
    public static final int SAVE_ADMIN_METHOD_RESPONSE = 1689;
    
    public static final int OVERRIDE_ADMIN_METHOD_RESPONSE = 1690;
    
        public static final int ADMIN_METHOD_OVERRIDE_RESPONSE = 1670;
        
        public static final int CONTRACT_MANDATE_INFO_RESPONSE = 2015;
        
    public static final int RETRIEVE_CODED_NONCODED_BENEFIT_HEADINGS_RESPONSE = 1496;    
    
    public static final int ADMIN_OPTION_RESPONSE = 1117;
    
    public static final int ADMIN_OPTION_RESPONSE_FOR_PS = 1116;
    
    
//	===================================== Migration Contract Step 1-9 start =============================================
 	public static final int CONTRACT_RULES_RESPONSE = 13001;
 
//===================================== Migration Contract Step 1-9 end =============================================		 	

 	public static final int PRODUCT_STRUCTURE_NOTES_RESPONSE = 1671;
 	
 	public static final int HIDE_ADMIN_OPTION_RESPONSE = 7676;
 	
 	public static final int UNLOCK_BENEFIT_COMPONENT_RESPONSE = 2717;
 	
 	public static final int LEGACY_CONTRACT_NOTES_RESPONSE = 3456;
 	
    public static final int SPS_MAPPING_CREATE_RESPONSE = 4000;
    
    public static final int SPS_MAPPING_UPDATE_RESPONSE = 4001;
    
    public static final int SPS_MAPPING_VIEW_RESPONSE = 4002;
    
    public static final int CUSTOM_MESSAGE_SEARCH_RESPONSE = 8080;    

	public static final int CUSTOM_MESSAGE_DELETE_RESPONSE = 9090;
    
    public static final int CUSTOM_MESSAGE_CREATE_RESPONSE = 8081;
    
    public static final int CUSTOM_MESSAGE_UPDATE_RESPONSE = 8085;
    
    public static final int VIEW_SERVICE_TYPE_CODE_RESPONSE = 4004;
    
    public static final int RETRIEVE_QUESTIONNAIRE_RESPONSE = 7070;
    
    public static final int UPDATE_QUESTIONNAIRE_RESPONSE = 6060;
    
    public static final int RETRIEVE_PRODUCT_QUESTIONNAIRE_RESPONSE = 6006;
    
    public static final int UPDATE_PRODUCT_QUESTIONNAIRE_RESPONSE = 6007;
    
    public static final int RETRIEVE_ROOT_QUESTIONNAIRE_RESPONSE = 9999;
    
    public static final int RETRIEVE_CHILD_QUESTIONNAIRE_RESPONSE = 8888;
    
    public static final int PERSIST_CHILD_QUESTIONNAIRE_RESPONSE = 6666;
    
    public static final int PROD_STRUCTURE_HIERARCHY_RESPONSE = 321;
    public static final int RETRIEVE_PRODUCT_COMPONENT_HIERARCHY_RESPONSE = 6667;
    
    public static final int POPUP_RESPONSE = 6668;
    
    public static final int QUESTIONNAIRE_PR_RESPONSE = 1977;
    
    public static final int SPS_REF_RESPONSE = 19000;
    
    public static final int RETRIEVE_PRODUCT_COMPONENT_RULE_RESPONSE = 6669;
   
    public static final int QUESTION_NOTES_POPUP_RESPONSE = 3333;
    
    public static final int BENEFIT_QUESTION_NOTES__RESPONSE = 3334;
    
    public static final int PRODUCT_QUESTION_NOTES_POPUP_RESPONSE = 3335;
    
    public static final int PRODUCT_QUESTION_NOTES__RESPONSE = 3336;
    
    public static final int CONTRACT_QUESTION_NOTES__RESPONSE = 3337;
  
    public static final int CONTRACT_PROCESS_QUESTION_NOTES__RESPONSE = 3338;
    
    public static final int NOTES_QUESTION_RESPONSE = 1345;
    public static final int ATTACH_NOTESTOQUESTION_PS = 1347; 
   
    public static final int CONTRACT_UNCODED_NOTES_RESPONSE = 1727; 
    
    
    public static final int CONTRACT_RULESET_RESPONSE = 1967; 
    
     public static final int CONTRACT_RULE_VALID_RESPONSE = 1968; 
     
     public static final int TIER_LOOKUP_RESPONSE =1214;
     
     public static final int BENEFIT_TIER_DEFN_ASSN_RESPONSE =1215;
     
     public static final int BENEFIT_TIER_DEFN_RESPONSE =1216;     
     
     public static final int DELETE_PRODUCT_TIER_RESPONSE = 7411;

     
   
     public static final int DELETE_CONTRACT_TIER_RESPONSE = 7412;
     
     public static final int DELETE_LEVEL_FROM_TIER_RESPONSE = 7413;
     
     public static final int BENEFIT_TIER_DEFINITION_RESPONSE = 1979; 
     
     public static final int PRODUCT_TIER_DEFN_RESPONSE =1217;  
     
     public static final int PRODUCT_BNFTTIER_DEFN_RESPONSE =1218;  
     
     public static final int UPDATE_PRODUCT_BNFT_GENINFO_RESPONSE =1219; 
     
     public static final int PRODUCT_BNFT_TIER_VALIDN_RESPONSE =1220; 
         
     public static final int PRODUCT_BENEFIT_TIER_DEF_SAVE_RESPONSE = 1985;
     
     public static final int CONTRACT_BENEFIT_TIER_DEF_SAVE_RESPONSE = 1947;
    
     public static final int RETREIVE_RULE_TYPE_PRODUCT_RESPONSE = 1212;
     
     public static final int RETREIVE_RULE_TYPE_CONTRACT_RESPONSE = 1213;
     
     public static final int CHECK_COPY_LEGACY_NOTE_RESPONSE = 3666;
     
     public static final int CONTRACT_REPORT_RESPONSE = 4200;
     
     
     // Added for Indicative Long Term Solution     
     public static final int INDICATIVE_DETAIL_RESPONSE = 4201;   
     public static final int CONFIRM_IMPORT_RESPONSE = 4202;
     // End Indicative Long Term Solutin
     	
    public WPDResponseFactory() {
        super();
    }


    /**
     * The interface method to be used to create and retrieve a WPDResponse
     * object.
     * 
     * @param type
     * @return the requested type of WPDResponse object.s
     */

    public static WPDResponse getResponse(int type) {
        WPDResponse response = null;
        switch (type) {
        case CONTRACT_REPORT_RESPONSE:
        	return response = getContractReportResponse();
        case CONTRACT_RULESET_RESPONSE:
        	return response = getContractRuleSetResponse();
        case CONTRACT_RULE_VALID_RESPONSE:
        	return response = getContractRuleValidationResponse ();     
        case PRODUCT_QUESTION_NOTES_POPUP_RESPONSE:
        	return response = getProductQuestionNotesPopUpResponse();
        case PRODUCT_QUESTION_NOTES__RESPONSE:
        	return response = getProductQuestionNoteResponse ();   
        case CONTRACT_QUESTION_NOTES__RESPONSE:
        	return response = getContractQuestionNotesPopUpResponse ();	
        case CONTRACT_PROCESS_QUESTION_NOTES__RESPONSE:
        	return response = getContractQuestionNoteResponse ();	
        case ADMIN_MTHD_SPS_VALD_RESPONSE :
        	return response = getAdminMethodSPSValidationResponse();
        case RETRIEVE_SERVICE_MAPPING_RESPONSE :
        	return response = getRetrieveServiceMappingResponse();
        case BNFT_COMP_NOTES_TO_QUESTION_ATTACHMENT_RESPONSE :
        	return response = getBnftComptNotesToQstnAttchmtResponse();	
        case CNTRCT_NOTES_TO_QUESTION_ATTACHMENT_RESPONSE :
        	return response = getContractNotesToQstnAttchmtResponse();	
        case CNTRCT_AO_NOTES_TO_QUESTION_ATTACHMENT_RESPONSE :
        	return response = getContractAONotesToQstnAttchmtResponse();
        case CNTRCT_TEIRED_AO_NOTES_TO_QUESTION_ATTACHMENT_RESPONSE :
        	return response = getContractTeiredAONotesToQstnAttchmtResponse();	
        case PROD_AO_NOTES_TO_QUESTION_ATTACHMENT_RESPONSE :
        	return response = getProductAONotesToQstnAttchmtResponse();		
        case ADMIN_METHOD_SPS_VALID_RESPONSE :
        	return response = getRetrieveAmSPSValidResponse();	
        case SPS_MAPPING_UPDATE_RESPONSE:
        	 return response=getSpsMappingUpdateResponse();
        case SPS_MAPPING_CREATE_RESPONSE:
        	return response = getSpsmappingResponse();
        case ADMIN_METHODS_VALIDATION_STATUS_RESPONSE:
        	return response = getAdminMethodValidationStatusResponse();
        case PRODUCT_STRUCTURE_NOTES_RESPONSE:
            return response = getProductStructureNotesResponse();
        case RETRIEVE_MIG_GENINFO_RESPONSE:
            return response = getMigrationGeneralInfoResponse();
        case SAVE_MIG_GENINFO_RESPONSE:
           return response = saveMigrationGeneralInfoResponse();
        case SAVE_LEGACY_CONTRACT_RESPONSE:
            return response = getSaveLegacyContractResponse();
        case RETRIEVE_LEGACY_CONTRACT_RESPONSE:
            return response = getRetrieveLegacyContractResponse();
        case MODULE_TASK_LOOKUP_RESPONSE:
            return response = getTaskLookUpResponse(); 
        case PRODUCT_BENEFIT_MANDATE_RETRIEVE_RESPONSE:
            return response = getProductBenefitMndateRetrieveResponse();
        case DELETE_TASK_ASSOCIATION_RESPONSE:
            return response = getDeleteTaskAssociationResponse();
        case RETRIEVE_ROLE_ASSOCIATION_RESPONSE:
            return response = getRetrieveRoleAssociationResponse();
        case BENEFIT_LINE_NOTES_RESPONSE:
            return response = getBenefitLineNotesResponse();
        case RETRIEVE_ROLE_RESPONSE:
            return response = getRetrieveRoleResponse();
        case SAVE_ROLE_RESPONSE:
            return response = getSaveRoleResponse();
        case SEARCH_MODULE_RESPONSE:
            return response = getModuleSearchResponse();
        case DELETE_MODULE_RESPONSE:
            return response = getModuleDeleteResponse();
        case TASK_SEARCH_RESPONSE:
            return response = getTaskSearchResponse();
        case DELETE_TASK_RESPONSE:
            return response = getTaskDeleteResponse();
        case PRODUCT_LINE_OVERRIDE_NOTES_RESPONSE:
            return response = getProductBenefitLineNotesOverrideResponse();
        case DELETE_PRODUCT_ADMIN_RESPONSE:
            return response = getProductAdminDeleteResponse();
        case SEARCH_SUB_CATALOG_RESPONSE:
            return response = getSearchSubCatalogResponse();
        case SAVE_CATALOG_RESPONSE:
            return response = getSaveCatalogResponse();
        case EDIT_CATALOG_RESPONSE:
            return response = getEditCatalogResponse();
        case DELETE_CATALOG_RESPONSE:
            return response = getDeleteCatalogResponse();
        case DELETE_STATUS_EDIT_RESPONSE:
            return response = getSoftDeleteResponse();
        case DELETE_SUB_CATALOG_RESPONSE:
            return response = getSubCatalogDeleteResponse();
        case RETRIEVE_CATALOG_RESPONSE:
            return response = getRetrieveCatalogResponse();
        case RETRIEVE_SRDA_CATALOG_RESPONSE:
            return response = getSrdaRetrieveCatalogResponse();
        case SEARCH_CATALOG_RESPONSE:
            return response = getCatalogSearchResponse();
        case SAVE_SUB_CATALOG_RESPONSE:
            return response = getSubCatalogSaveResponse();
        case SAVE_ITEM_ASSOCIATION_RESPONSE:
            return response = getSaveItemAssociationResponse();
        case REFERENCE_DATA_LOOKUP_RESPONSE:
            return response = getSaveReferenceDataLookupResponse();
        case QUES_ANSWER_LOOKUP_RESPONSE:
            return response = getQuesAnswerlookupResponse();
            
        case CREATE_ADMINMETHOD_RESPONSE:
        	return response = getAdminMethodMappingCreateResponse();
        	
        case CREATE_ADMINMETHODMAPPING_RESPONSE:
        	return response = getAdminMethodMappingCreateResponse1();
        	
        case EDIT_ADMINMETHODMAPPING_RESPONSE:
        	return response = getAdminMethodMappingEditResponse();
        	
        case DELETE_ADMINMETHODMAPPING_RESPONSE:
        	return response = getAdminMethodMappingDeleteResponse();
        	
        	

        case REFERENCE_DOMAIN_VALIDATION_RESPONSE:
            return response =getReferenceDataDomainValidationResponse() ;
        case SAVE_MODULE_RESPONSE:
            return response = getSaveModuleResponse();
        case SAVE_TASK_RESPONSE:
            return response = getSaveTaskResponse();
        case SAVE_SUB_TASK_RESPONSE:
            return response = getSaveSubTaskResponse();
        case SEARCH_ROLE_RESPONSE:
            return response = getSearchRoleResponse();

        case RETRIEVE_TASK_RESPONSE:
            return response = getRetrieveTaskResponse();
        case SUB_TASK_LOOKUP_RESPONSE:
            return response = getSubTaskLookupResponse();
        case MODULE_ASSOCIATION_RESPONSE:
            return response = getRetrieveModuleAssociationResponse();
        case SAVE_MODULE_ASSOCIATION_RESPONSE:
            return response = getSaveModuleAssociationResponse();
        case DELETE_MODULE_ASSOCIATION_RESPONSE:
            return response = getDeleteModuleAssociationResponse();
        case RETRIEVE_ROLE_MODULE_RESPONSE:
            return response = getRetrieveRoleModuleAssociationResponse();
        case ROLE_MODULE_LOOKUP_RESPONSE:
            return response = getRoleModuleLookUpResponse();
        case SAVE_ROLE_MODULE_ASSOCIATION_RESPONSE:
            return response = getSaveRoleModuleAsociationResponse();
        case SUB_TASK_LOCATE_RESPONSE:
            return response = getLocateSubTaskResponse();
        case ROLE_TASK_LOOKUP_RESPONSE:
            return response = getRoleTasklookUpResponse();
        case SAVE_SUBTASK_ASSOCIATION_RESPONSE:
            return response = getSaveSubTaskAssociationResponse();
        case SAVE_TASK_ASSOCIATION_RESPONSE:
            return response = getSaveTaskAssociationResponse();
        case DELETE_SUBTASK_ASSOCIATION_RESPONSE:
            return response = getDeleteTaskAssociationresponse();
        case DELETE_ITEM_ASSOCIATION_RESPONSE:
            return response = getItemDeleteAssociationRequest();
        case DELETE_PRODUCT_BENEFIT_NOTE_RESPONSE:
            return response = getDeleteProductBenefitNoteResponse();
        case SAVE_PRODUCT_BENEFIT_NOTE_RESPONSE:
            return response = getSaveProductBenefitNoteResponse();
        case LOAD_PRODUCT_BENEFIT_NOTE_RESPONSE:
            return response = getLoadProductBenefitNoteResponse();
        case DELETE_PRODUCT_COMPONENT_NOTE_RESPONSE:
            return response = getDeleteProductComponentNoteResponse();
        case SAVE_PRODUCT_COMPONENT_NOTE_RESPONSE:
            return response = getSaveProductComponentNoteResponse();
        case LOAD_PRODUCT_COMPONENT_NOTE_RESPONSE:
            return response = getLoadProductComponentNoteResponse();
        case DELETE_PRODUCT_NOTE_RESPONSE:
            return response = getDeleteProductNoteResponse();
        case RETRIEVE_PRODUCT_NOTE_RESPONSE:
            return response = getRetrieveProductNoteResponse();
        case SAVE_PROD_NOTE_RESPONSE:
            return response = getSaveProductNoteResponse();
        case STANDARD_BENEFIT_RESPONSE:
            return response = getStandardBenefitResponse();
        case STANDARD_BENEFIT_COPY_RESPONSE:
            return response = getStandardBenefitCopyResponse();
        case BENEFIT_DEFINITION_RESPONSE:
            return response = getBenefitDefinitionResponse();
        case RETRIEVE_BENEFIT_DEFINITION_RESPONSE:
            return response = getRetrieveBenefitDefinitionResponse();
        case SEARCH_ITEM_LOOKUP_RESPONSE:
            return response = getItemLookUpResponse();
        case LOCATE_BENEFIT_DEFINITION_RESPONSE:
            return response = getLocateBenefitDefinitionResponse();
        // for mandate list response
        case LOCATE_MANDATE_LIST_RESPONSE:
            return response = getMandateListResponse();
        case LOCATE_NON_ADJ_BENEFIT_MANDATE_RESPONSE:
            return response = getNonAdjBenefitMandateListResponse();
        // for nonAdjMandateRetrieve
        case RETRIEVE_NON_ADJ_MANDATE_RESPONSE:
            return response = retrieveNonAdjMandateDetails();
        case LOCATE_BENEFITLEVEL_LIST_RESPONSE:
            return response = getBenefitLevelListResponse();
        case LOCATE_BENEFIT_ADMINISTRATION_RESPONSE:
            return response = getLocateBenefitAdministrationResponse();
        case DELETE_BENEFIT_DEFINITION_RESPONSE:
            return response = getDeleteBenefitDefinitionResponse();
        case SAVE_PRODUCT_STRUCTURE_RESPONSE:
            return response = getSaveProductStructureResponse();
        case NON_ADJ_BENEFIT_MANDATE_RESPONSE:
            return response = getBenefitMandateResponse();
        case SB_SEARCH_RESPONSE:
            return response = getStandardBenefitSearchResponse();
        case LOCATE_BENEFIT_MANDATE_RESPONSE:
            return response = getLocateBenefitMandateResponse();
        case DELETE_BENEFIT_MANDATE_RESPONSE:
            return response = getDeleteBenefitMandateResponse();
        case CREATE_BENEFIT_ADMINISTRATION_RESPONSE:
            return response = getBenefitAdministrationResponse();
        case SAVE_QUESTION_RESPONSE:
            return response = getSaveQuestionResponse();
        case RETRIEVE_QUESTION_RESPONSE:
            return response = getRetrieveQuestionResponse();
        case SEARCH_QUESTION_RESPONSE:
            return response = getSearchQuestionResponse();
        case DELETE_QUESTION_RESPONSE:
            return response = getDeleteQuestionResponse();
        case UPDATE_BENEFIT_ADMINISTRATION_RESPONSE:
            return response = getUpdatedBenefitAdministrationResponse();
        case DELETE_BENEFIT_ADMINISTRATION_RESPONSE:
            return response = getDeletedBenefitAdministrationResponse();
        case RETRIEVE_BENEFIT_ADMINISTRATION_RESPONSE:
            return response = getRetrieveBenefitAdministrationResponse();
        case SEARCH_PRODUCT_RESPONSE:
            return response = getLocateSearchProductResponse();
        case SEARCH_PRODUCT_STRUCTURE_RESPONSE:
            return response = getLocateProductStructureResponse();
        case RETRIEVE_PRODUCT_STRUCTURE_RESPONSE:
            return response = getRetrieveProductStructureResponse();
        case SAVE_PRODUCT_RESPONSE:
            return response = getSaveProductResponse();
        case RETRIEVE_PRODUCT_RESPONSE:
            return response = getRetrieveProductResponse();
        case RETRIEVE_VALID_PRD_STR_RESPONSE:
            return response = getRetrieveValidProductStructuresResponse();
        case SAVE_PROD_COMPONENT_RESPONSE:
            return response = getSaveProductComponentResponse();
        case PROD_SEARCH_RESPONSE:
            return response = getSearchProductResponse();
        case DELETE_PRODUCT_STRUCTURE_RESPONSE:
            return response = getDeleteProductStructureResponse();
        case RETRIEVE_BENEFIT_COMPONENT_RESPONSE:
            return response = getBenefitComponentResponse();
        case DELETE_BENEFIT_COMPONENT_RESPONSE:
            return response = getBenefitComponentDeleteResponse();
        // for selectedQuestionListResponse
        case SELECTED_QUESTION_LIST:
            return response = getSelectedQuestionLsitResponse();
        case LOOK_UP_ADMIN_OPTION_RESPONSE:
            return response = getLookupAdminOptionResponse();
        //	        	for benefit adminoption response
        case CREATE_BENEFIT_ADMINOPTION_RESPONSE:
            return response = getCreateBenefitAdministrationResponse();
        case LOCATE_BENEFIT_ADMINOPTION_RESPONSE:
            return response = getLocateAdministrationOptionResponse();
        case RETRIEVE_BENEFIT_ADMINOPTION_RESPONSE:
            return response = getRetrieveAdministrationOptionResponse();
        case UPDATE_QUESTION_RESPONSE:
            return response = getUpdateQuestionResponse();
        // for hideQuestionResponse
        case HIDE_QUESTION_RESPONSE:
            return response = getHideQuestionResponse();
        case DELETE_BENEFIT_ADMINOPTION_RESPONSE:
            return response = getDeleteAdministrationOptionResponse();
        // for possible Answers response
        case POSSIBLE_ANSWERS_RESPONSE:
            return response = getPossibleAnswersResponse();

        case RETRIEVE_PRODUCT_BENEFIT_RESPONSE:
            return response = getRetrieveProductBenefitResponse();
        case RETRIEVE_BENEFIT_DEFENITION_RESPONSE:
            return response = getRetrieveBenefitDefenitionResponse();

        case DELETE_PRODUCT_RESPONSE:
            return response = getDeleteProductResponse();

        case RETRIEVE_COMPONENT_FROM_TREE_RESPONSE:
            return response = getBenefitComponentFromTreeResponse();

        case DELETE_PRODUCT_STUCTURE_RESPONSE:
            return response = getDeleteProductStructureResponse();

        case DELETE_ROLE_RESPONSE:
            return response = getDeleteRoleResponse();

        case DELETE_PRODUCT_BENEFIT_RESPONSE:
            return response = getDeleteProductBenefitResponse();

        case RETRIEVE_PRODUCT_BENEFIT_COMPONENT_RESPONSE:
            return response = getRetrieveProductBenefitComponentResponse();
        case RETRIEVE_BENEFIT_COMPONENT_VIEW_RESPONSE:
            return response = getRetrieveBenefitComponentViewResponse();

        case STANDARD_BENEFIT_DELETE_RESPONSE:
            return response = getDeleteStandardBenefitResponse();

        case QUESTION_VIEW_RESPONSE:
            return response = getQuestionViewResponse();

        case RETRIEVE_MANDATES_RESPONSE:
            return response = getRetrieveBenefitMandatesResponse();
        case RETRIEVE_BENEFIT__RESPONSE:
            return response = getRetrieveBenefitResponse();
        case RETRIEVE_PRODUCT_STRUCTURE_BENEFIT_ADMINISTATION_RESPONSE:
            return response = getRetrieveProductStructureBenefitAdministrationResponse();

        case SAVE_BENEFIT_ADMINISTRATION_FOR_PRODUCT_STRUCTURE:
            return response = getSaveProductStructureBenefitAdministrationResponse();

        case SAVE_BENEFIT_COMPONENT_RESPONSE:
            return response = getSaveBenefitComponentResponse();

        case CHECKOUT_BENEFIT_COMPONENT_RESPONSE:
            return response = getCheckOutBenefitComponentResponse();

        case PUBLISH_BENEFIT_COMPONENT_RESPONSE:
            return response = getPublishBenefitComponentResponse();

        case SCHEDULE_TEST_BENEFIT_COMPONENT_RESPONSE:
            return response = getScheduleForTestBenefitComponentResponse();

        case TEST_PASS_BENEFIT_COMPONENT_RESPONSE:
            return response = getTestPassBenefitComponentResponse();

        case TEST_FAIL_BENEFIT_COMPONENT_RESPONSE:
            return response = getTestFailBenefitComponentResponse();

        case BENEFIT_COMPONENT_CHECKIN_RESPONSE:
            return response = getBenefitComponentCheckInResponse();

        case LOCATE_BENEFIT_COMPONENT_NOTES_RESPONSE:
            return response = getBenefitComponentLocateNotesResponse();

        case DELETE_BENEFIT_COMPONENT_NOTES_RESPONSE:
            return response = getBenefitComponentDeleteNotesResponse();
        //for benefitComponentRetrieveForEdit
        case BENEFIT_COMPONENT_RETRIEVE_FOR_EDIT:
            return response = getBenefitComponentRetrieveResponseForEdit();

        case RETRIEVE_PRODUCT_BENEFIT_DEFINITION_RESPONSE:
            return response = getRetrieveProductBenefitDefinitionResponse();

        case RETRIEVE_BENEFIT_VIEW_RESPONSE:
            return response = getRetrieveBenefitViewResponse();

        case STANDARD_BENEFIT_CHECKIN_RESPONSE:
            return response = getStandardBenefitCheckinResponse();

        case ATTACH_BENEFIT_COMPONENT_NOTES_RESPONSE:
            return response = getBenefitComponentAttachNotesResponse();

        case STANDARD_BENEFIT_CHECKOUT_RESPONSE:
            return response = getStandardBenefitCheckOutResponse();

        case RETRIEVE_PRODUCT_BENEFIT_ADMIN_RESPONSE:
            return response = getRetrieveProductBenefitAdminResponse();

        case SAVE_PRODUCT_BENEFIT_DEFINITION_RESPONSE:
            return response = getSaveProductBenefitDefinitionResponse();

        case SAVE_CONTRACT_BENEFIT_DEFINITION_RESPONSE:
            return response = getSaveContractBenefitDefinitionResponse();

        case SAVE_ADMIN_OPTION_RESPONSE:
            return response = getCreateAdminOptionResponse();

        case RETRIEVE_ADMIN_OPTION_RESPONSE:
            return response = getRetrieveAdminOptionResponse();

        case DELETE_ADMIN_OPTION_RESPONSE:
            return response = getDeleteAdminOptionResponse();

        case DELETE_ALL_ADMIN_OPTION_RESPONSE:
            return response = getDeleteAllAdminOptionResponse();

        case SEARCH_ADMIN_OPTION_RESPONSE:
            return response = getSearchAdminOptionResponse();

        case CHECKIN_ADMIN_OPTION_RESPONSE:
            return response = getCheckInAdminOptionResponse();

        case CHECKOUT_ADMIN_OPTION_RESPONSE:
            return response = getCheckOutAdminOptionResponse();

        case SAVE_ADMIN_OPTION_QUESTION_RESPONSE:
            return response = getSaveAdminOptionQuestionResponse();

        case RETRIEVE_ADMIN_OPTION_QUESTION_RESPONSE:
            return response = getRetreiveAdminOptionQuestionResponse();

        case DELETE_ADMIN_OPTION_QUESTION_RESPONSE:
            return response = getDeleteAdminOptionQuestionResponse();

        // for locateComponentsBenefitDefinitionResponse
        case LOCATE_COMPONENTS_BENEFIT_DEFINITION_RESPONSE:
            return response = getLocateComponentsBenefitDefinitionResponse();

        // for upadateBenefitLinesResponse
        case UPDATE_BENEFIT_LINES_RESPONSE:
            return response = getUpdateBenefitLinesResponse();

        case RETRIEVE_PRODUCT_STRUCTURE_VERSIONS_RESPONSE:
            return response = getProductStructureVersionsResponse();

        case COPY_MANDATE_RESPONSE:
            return response = getCopyMandateResponse();

        case CREATE_MANDATE_RESPONSE:
            return response = getCreateMandateResponse();

        case DELETE_MANDATE_RESPONSE:
            return response = getDeleteMandateResponse();

        case CHECKOUT_MANDATE_RESPONSE:
            return response = getCheckOutMandateResponse();

        case RETRIEVE_MANDATE_RESPONSE:
            return response = getRetrieveMandateResponse();

        case LOCATE_MANDATE_RESPONSE:
            return response = getLocateMandateResponse();

        case CHECKIN_MANDATE_RESPONSE:
            return response = getCheckInMandateResponse();
            
        case PRODUCT_RULE_REF_DATA_RESPONSE:
            return response = getProductRuleRefDataResponse();
            
        case SAVE_PRODUCT_BENEFIT_ADMIN_RESPONSE:
            return response = getSaveProductBenefitAdminResponse();

        case CREATE_BENEFIT_LEVEL_RESPONSE:
            return response = getCreateBenefitLevelResponse();

        case SAVE_BENEFIT_LEVEL_RESPONSE:
            return response = getSaveBenefitLevelResponse();
            
        case SEARCH_SERVICE_TYPE_MAPPING_RESPONSE:
        	return response = getServiceTypeSearchResponse();

        case DELETE_BENEFIT_LEVEL_RESPONSE:
            return response = getDeleteBenefitLevelResponse();

        case SEARCH_BENEFIT_LEVEL_RESPONSE:
            return response = getSearchBenefitLevelResponse();

        case SEARCH_BENEFIT_LEVEL_TERM_RESPONSE:
            return response = getSearchBenefitLevelTermResponse();
            
        case DATA_TYPE_RETRIEVE_RESPONSE:
            return response = getDataTypeRetrieveResponse();

        case SAVE_PRODUCT_STRUCTURE_BENEFIT_DEFINITION_RESPONSE:
            return response = getSaveProductStructureBenefitDefinitionResponse();

        case CHECKOUT_QUESTION_RESPONSE:
            return response = getCheckOutQuestionResponse();

        case SCHEDULEFORTEST_QUESTION_RESPONSE:
            return response = getScheduleForTestQuestionResponse();

        case PUBLISH_QUESTION_RESPONSE:
            return response = getPublishQuestionResponse();
            
        case UNLOCK_QUESTION_RESPONSE:
            return response = getUnlockQuestionResponse();

        case ADMIN_OPTION_VIEW_RESPONSE:
            return response = getadminOptionViewResponse();
        case PUBLISH_STD_BENEFIT_RESPONSE:
            return response = getPublishStdBenefitResponse();
        case UNLOCK_STD_BENEFIT_RESPONSE:
            return response = getStandardBenefitUnLockResponse();
        case COPY_BENEFIT_COMPONENT_RESPONSE:
            return response = getCopyBenefitComponentResponse();

        case ROLE_ASSOCIATION_RESPONSE:
            return response = getRoleAssociationResponse();
        case SAVE_ROLE_ASSOCIATION_RESPONSE:
            return response = getSaveRoleAssociationResponse();
        case LOCATE_COMPONENTS_BENEFIT_ADMIN_RESPONSE:
            return response = getLocateComponentsBenefitAdministrationResponse();

        case UPDATE_COMPONENTS_BENEFIT_ADMIN_RESPONSE:
            return response = getUpdateComponentsBenefitAdministrationResponse();
        case TEST_PASS_STD_BEN_RESPONSE:
            return response = getTestPassStdBenResponse();
        case TEST_FAIL_STD_BEN_RESPONSE:
            return response = getTestFailStdBenResponse();
        case STANDARD_BENEFIT_VERSIONS_LIFECYCLE_RESPONSE:
            return response = getBenefitVersionsLifeCycleResponse();
        case SCHEDULE_FOR_TEST_STD_BEN_RESPONSE:
            return response = getScheduleForTestStdBenResponse();
        case ADMIN_LEVEL_RESPONSE:
            return response = getAdminLevelResponse();
        case SCHEDULEFORTEST_ADMINOPTION_RESPONSE:
            return response = getScheduleForTestAdminOptionResponse();
        case PUBLISH_ADMIN_OPTION_RESPONSE:
            return response = getPublishAdminOptionResponse();
        case TEST_PASS_ADMIN_OPTION_RESPONSE:
            return response = getTestPassAdminOptionResponse();
        case TEST_PASS_QUESTION_RESPONSE:
            return response = getTestPassQuestionResponse();
        case PRODUCT_STRUCTURE_LIFE_CYCLE_RESPONSE:
            return response = getScheduleForTestProductStructureResponse();
        case TEST_FAIL_QUESTION_RESPONSE:
            return response = getTestFailQuestionResponse();
        case TEST_FAIL_ADMIN_OPTION_RESPONSE:
            return response = getTestFailAdminOptionResponse();
        case BENEFIT_HIERARCHY_UPDATE_RESPONSE:
            return response = getBenefitHierarchyUpdateResponse();
        case BENEFIT_HIERARCHY_RESPONSE:
            return response = getBenefitHierarchyResponse();
        case DELETE_BENEFIT_LINE_RESPONSE:
            return response = getDeleteBenefitLineResponse();
        case SAVE_CONTRACT_SPECIFICINFO_RESPONSE:
            return response = getContractSpecificInfoResponse();
        case SAVE_CONTRACT_ADAPTEDINFO_RESPONSE:
            return response = getContractAdaptedInfoResponse();            
        case CREATE_NOTES_RESPONSE:
            return response = getCreateNotesResponse();
        case RETRIEVE_CONTRACT_BENEFIT_DEFINITION_RESPONSE:
            return response = getRetrieveContractBenefitDefinitionResponse();
        case CONTRACT_SEARCH_RESPONSE:
            return response = getContractSearchResponse();
        case NOTE_DOMAIN_RESPONSE:
            return response = getNoteDomainResponse();
        case RETRIEVE_NOTES_RESPONSE:
            return response = getRetrieveNotesResponse();
        case PRICING_INFO_RESPONSE:
            return response = getPricingInfoResponse();
        case LOCATE_PRICING_INFO_RESPOSE:
            return response = getLocatePricingInfoResponse();
        case DELETE_PRICING_INFO_RESPOSE:
            return response = getDeletePricingInfoResponse();
        case RETRIEVE_CONTRACT_PRODUCT:
            return response = getRetrieveContractProduct();
        case NOTES_SEARCH_RESPONSE:
            return response = getNotesSearchResponse();
        case CREATE_NOTES_DATA_DOMAIN_RESPONSE:
            return response = getCreateNotesDataDomainResponse();
        case SAVE_CONTRACT_BASIC_INFO_RESPONSE:
            return response = getSaveContractBasicInfoResponse();
        case RETRIEVE_CONTRACT_PRICINGINFO_RESPONSE:
            return response = getRetrievePricingInfoResponse();
        case RETRIEVE_CONTRACT_BENEFIT_COMPONENT_VIEW_RESPONSE:
            return response = getRetrieveContractBenefitComponentResponse();
        case NOTES_ATTACHMENT_RESPONSE:
            return response = getNotesAtachmentResponse();
        case RETRIEVE_CONTRACT_SB_RESPONSE:
            return response = getContractSBResponse();
        case RETRIEVE_CONTRACT_BENEFIT_MANDATE_RESPONSE:
            return response = getContractBenefitMandateResponse();
        case NOTES_CHECKIN_RESPONSE:
            return response = getNotesCheckInResponse();
        case NOTES_ALLVERSIONS_RESPONSE:
            return response = getNotesAllVersionsResponse();

        case LOCATE_TARGET_SYSTEM_FOR_NOTES_RESPONSE:
            return response = getTargetSystemForNotesResponse();
        case NOTES_CHECKOUT_RESPONSE:
            return response = getNotesCheckoutResponse();

        case NOTES_COPY_RESPONSE:
            return response = getNotesCopyResponse();
        case DELETE_NOTES_RESPONSE:
            return response = getNotesDeleteResponse();
        case SAVE_CONTRACT_COMMENT_RESPONSE:
            return response = getContractCommentResponse();
        case RETIEVE_DATESEGMENT_COMMENT_RESPONSE:
            return response = getRetrieveDateSegmentCommentResponse();
        case SEARCH_ITEM_RESPONSE:
            return response = getItemSearchResponse();
        case CREATE_ITEM_RESPONSE:
            return response = getCreateItemResponse();
        case RETRIEVE_ITEM_REPONSE:
            return response = getLocateItemResponse();
        case CATALOG_LOOKUP_RESPONSE:
            return response = getCatalogLookUpResponse();
        case RETRIEVE_CONTRACT_SPECIFICINFO_RESPONSE:
            return response = getRetrieveContractSpecificInfoResponse();

        case DATE_SEGMENTS_SEARCH_RESPONSE:
            return response = getRetrieveDateSegmentResponse();

        case DATE_SEGMENTS_CREATE_RESPONSE:
            return response = getCreateDateSegmentResponse();

        case RETRIEVE_CONTRACT_BASICINFO_RESPONSE:
            return response = getRetrieveBasicInfoRequest();
        case RETIEVE_SELECTED_COMMENT_RESPONSE:
            return response = getRetrieveSelectedCommentResponse();
        case LOCATE_PRODUCT_RESPONSE:
            return response = getLocateProductResponse();
        case ATTACH_STANDARD_BENEFIT_NOTES_RESPONSE:
            return response = getStandardBenefitAttachNotesResponse();

        case LOCATE_STANDARD_BENEFIT_NOTES_RESPONSE:
            return response = getStandardBenefitAttachNotesLocateResponse();

        case DELETE_STANDARD_BENEFIT_NOTES_RESPONSE:
            return response = getDeleteStandardBenefitNotesResponse();
        case RETRIEVE_CONTRACT_BENEFIT_ADMIN_RESPONSE:
            return response = getRetrieveContractBenefitAdminResponse();
        case RETRIEVE_ALL_POSSIBLE_ANSWER_ADMIN_OPTION_RESPONSE:
        	return response = getRetrieveAllPossibleAnswerResponse();
        case SAVE_BENEFIT_ADMINISTRATION_FOR_CONTRACT:
            return response = getSaveContractBenefitAdminResponse();
        case RETRIEVE_CONTRACT_BENEFIT_GENERALINFO_RESPONSE:
            return response = getContractBenefitGeneralInfoResponse();
        case CONTRACT_NOTES_ATTACH_RESPONSE:
            return response = getContractNoteAttachmentResponse();
        case CONTRACT_NOTES_DELETE_RESPONSE:
            return response = getContractNotesDeleteResponse();
        case CONTRACT_BENEFIT_COMPONENT_NOTES_ATTACH_RESPONSE:
            return response = getContractBenefitComponentNoteAttachmentResponse();
        case LOAD_CONTRACT_COMPONENT_NOTE_RESPONSE:
            return response = getContractLoadBenefitComponentNoteAttachmentResponse();
        case DELETE_CONTRACT_COMPONENT_NOTE_RESPONSE:
            return response = getDeleteContractComponentNoteAttachmentResponse();
        case RETRIEVE_CONTRACT_NOTE_RESPONSE:
            return response = getRetrieveContractNoteResponse();
        //                	admin
        case SAVE_PROD_ADMIN_RESPONSE:
            return response = getSaveProductAdminResponse();
        case RETRIEVE_PROD_ADMIN_RESPONSE:
            return response = getProductAdminResponse();

        case DELETE_PROD_ADMIN_RESPONSE:
            return response = getDeleteProductAdminResponse();
        case BENEFIT_SEARCH_RESPONSE:
        	return response = getRetreiveBenefitsContractResponse();

        case RETRIEVE_BENEFIT_HEADINGS_RESPONSE:
            return response = getBenefitHeadingsResponse();

        case DELETE_CONTRACT_RESPONSE:
            return response = getDeleteContractResponse();
        case LOCATE_NOTES_FOR_BENEFIT_LINE:

            return response = getNotesAttachmentForBnftLineRespone();
        case ATTACH_BENEFIT_LINES_NOTES_RESPONSE:
            return response = getBenefitLineNotesAttachmentResponse();

        case OVERRIDE_BENFITLINE_NOTES_RESPONSE:
            return response = getBenefitLineNotesOverrideResponse();

        case CONTRACT_BENEFIT_NOTE_RESPONSE:
            return response = getContractBenefitNoteResponse();
        case LOAD_CONTRACT_BENEFIT_NOTE_RESPONSE:
            return response = getContractLoadBenefitNoteAttachmentResponse();

        case RETRIEVE_RESERVED_RESPONSE:
            return response = getReservedContractResponse();
            
        case MEMBERSHIP_INFO_RESPONSE:
        	return response = getMembershipResponse();

        case SAVE_RESERVED_RESPONSE:
            return response = saveReservedContractResponse();

        case RESERVED_CONTRACT_SEARCH_RESPONSE:
            return response = getReservedContractSearchResponse();

        case RETRIEVE_CONTRACT_PROD_ADMIN_RESPONSE:
            return response = getRetrieveContractProductAdminResponse();
        case DELETE_CONTRACT_PRODUCT_ADMIN_RESPONSE:
            return response = getDeleteContractProductAdminResponse();
        case SAVE_CONTRACT_PROD_ADMIN_RESPONSE:
            return response = getSaveContractProductAdminResponse();
        case SAVE_RULEID_CONTRACTBEN_RESPONSE:
            return response = getSaveRuleInfoContractBenefitResponse();

        case RETRIEVE_PRODUCT_ADMIN_OPTION_RESPONSE:
            return response = getProductRetrieveAdminOptionResponse();
        //    	        case SAVE_BENEFIT_ADMINISTRATION_FOR_PRODUCT:
        //    	            return response = getSaveProductBenefitAdministrationResponse();
        case SAVE_ADMIN_OPTION_FOR_CONTRACT:
            return response = getSaveContractAdminiOptionResponse();
        case RETRIEVE_CONTRACT_ADMIN_OPTION_FOR_CONTRACT:
            return response = getContractRetrieveAdminOptionResponse();
        case DATAFEED_REQUEST_CNTRCT_RETRIEVE:
            return response = getContractForDataFeed();

        case SAVE_PRODUCT_RULES_RESPONSE:
            return response = getSaveProductRulesResponse();
        case DELETE_PRODUCT_RULE_RESPONSE:
            return response = getDeleteProductRuleResponse();
        
         case CONTRACT_TRANSFER_LOG_RESPONSE:
            return response = getContractTransferLogResponse();
         
        case RULES_SEARCH_RESPONSE:
            return response = getRulesResponse();
        case APPROVE_ADMIN_OPTION_RESPONSE:
            return response = getApproveAdminOptionResponse();
        case UNLOCK_ADMIN_OPTION_RESPONSE:
            return response = getUnlockAdminOptionResponse();
        case REJECT_ADMIN_OPTION_RESPONSE:
            return response = getRejectAdminOptionResponse();
        case APPROVE_STD_BEN_RESPONSE:
            return response = getApproveStdBenefitResponse();
        case REJECT_STD_BEN_RESPONSE:
            return response = getRejectStdBenefitResponse();
        case APPROVE_BENEFIT_COMPONENT_RESPONSE:
            return response = getApproveBCResponse();
        case REJECT_BENEFIT_COMPONENT_RESPONSE:
            return response = getRejectBCResponse();
        case RETRIEVE_LEGACY_LOOKUP_RESPONSE:
            return response = getRetrieveLegacyLookUpResponse();
        case RETRIEVE_MIG_VARIABLE_MAPPING_RESPONSE:
            return response = getRetrieveMigVariableMappingResponse();
        case SAVE_MIG_VARIABLE_MAPPING_RESPONSE:
            return response = getSaveMigVariableMappingResponse();
        case CONFIRM_MIG_CONTRACT_RESPONSE:
        	return response = getConfirmMigrationContractResponse();
        case MIGRATION_GENERATE_REPORT_RESPONSE:
        	return response = getMigrationGenerateReportResponse();
        case LAST_ACCESS_PAGE_MIG_CONTRACT_RESPONSE:
        	return response = getSaveLastAccessPageInfoResponse();
        
        case SAVE_MIGRATION_NOTES_RESPONSE :
        	return response = getSaveMigrationNotesResponse();

        case RETRIEVE_BENEFIT_LINES_RESPONSE:
            return response = getBenefitLinesResponse();
        case SAVE_CONTRACT_HEADINGS_RESPONSE:
            return response = getSaveContractHeadingsResponse();
        case MIGRATION_PRICING_INFO_RESPOSE:
            return response = getMigrationPricingInfoResponse(); 
        case MIGRATION_PRODUCT_INFO_RESPOSE:
            return response = getMigrationProductInfoResponse();  
        case LEGACY_HEADING_RESPONSE:
	             return response = getlegacyHeadingResponse(); 
	             
        case SAVE_BENEFIT_ADMINISTRATION_FOR_PRODUCT:
            return response = getSaveProductBenefitAdministrationResponse(); 
            
        case ADMIN_METHOD_POPUP_RESPONSE:
            return response = getAdminMethodPopupResponse(); 
        case ADMIN_METHOD_RESPONSE:
            return response = getAdminMethodResponse(); 
        case SAVE_ADMIN_METHOD_RESPONSE:
            return response = getSaveAdminMethodResponse(); 
        case OVERRIDE_ADMIN_METHOD_RESPONSE:
            return response = getOverrideAdminMethodResponse();
                case ADMIN_METHOD_OVERRIDE_RESPONSE:
        	return response = getAdminMethodOverrideResponse();
             

        case RULE_RESPONSE:
            return response = getRuleResponse();  
        case GROUP_RULE_RESPONSE:
            response = new GroupRuleResponse();
            break;
//	  		===================================== Migration Contract Step 1-9 start =============================================
        case CONTRACT_RULES_RESPONSE:
            return response = getMigrationContractRulesResponse();	     
        case CANCEL_MIGRATION_RESPONSE:
            return response = getCancelMigrationResponse();
// 		===================================== Migration Contract Step 1-9 end =============================================
               
        case CONTRACT_MANDATE_INFO_RESPONSE:
            return response = getContractMandateInfo();
            
        case RETRIEVE_CODED_NONCODED_BENEFIT_HEADINGS_RESPONSE:
            return response = getCodedNonCodedBenefitHeadingsResponse();    
        
        case QUESTION_DELETE_RESPONSE:
            return response = getQuestionDeleteResponse();           
           
        case HIDE_PRODUCT_ADMIN_OPTION_RESPONSE:
            return response = getProductAdminOptionResponse();
            
        case ADMIN_OPTION_RESPONSE:
            return response = getAdminOptionhideResponse();    
            
        case ADMIN_OPTION_RESPONSE_FOR_PS:
            return response = getAdminOptionResponseForPs();  
            
        case HIDE_ADMIN_OPTION_RESPONSE:
            return response = getHideAdminOptionResponse();    
                 
        case UNLOCK_BENEFIT_COMPONENT_RESPONSE:
            return response = getBenefitComponentUnlockResponse();   
        	
        case LEGACY_CONTRACT_NOTES_RESPONSE:
        	
        	return response = getLegacyContractNotesResponse();

        case RETRIEVE_BENEFIT_DETAILS_RESPONSE:
        	return response = getBenefitDetailsResponse(); 
        case SEARCH_SPS_MAINTAIN_RESPONSE:
        	return response = getSearchSPSMaintainResponse();
        case DELETE_SPS_MAPPING_RESPONSE:
        	return response = getDeleteSpsMappingResponse();
        	
        case SAVE_SERVICE_MAPPING_RESPONSE :
        	return response = getSaveServiceMappingResponse();
        case DELETE_SERVICETYPE_RESPONSE:
        	return response = getServiceTypeMappingResponse();
        
        case SPS_MAPPING_VIEW_RESPONSE:
        	return response = getSpsmappingViewResponse(); 	
        case CUSTOM_MESSAGE_SEARCH_RESPONSE:
        	return response = getCustomMessageSearchResponse();
        case CUSTOM_MESSAGE_RETRIEVE_RESPONSE:
        	return response = getCustomMessageRetrieveResponse();
        case CUSTOM_MESSAGE_DELETE_RESPONSE:
        	return response = getCustomMessageDeleteResponse();
        case CUSTOM_MESSAGE_CREATE_RESPONSE :
        	return  getCustomMessageTextCreateResponse();     
        case CUSTOM_MESSAGE_UPDATE_RESPONSE :
        	return getCustomMessageTextUpdateResponse();
        case VIEW_SERVICE_TYPE_CODE_RESPONSE:
        	return response = getViewServiceTypeCodeMappingResponse();
        case RETRIEVE_QUESTIONNAIRE_RESPONSE:
        	return response = getRetriveQuestionnaireResponse();
        case UPDATE_QUESTIONNAIRE_RESPONSE:
        	return response = getUpdateQuestionnaireResponse();
        case RETRIEVE_PRODUCT_QUESTIONNAIRE_RESPONSE:
        	return response = getRetrieveProductQuestionareResponse();	
        case UPDATE_PRODUCT_QUESTIONNAIRE_RESPONSE:
        	return response = getUpdateProductQuestionareResponse();
        		
        case RETRIEVE_ROOT_QUESTIONNAIRE_RESPONSE:
        	return response = getRetrieveRootQuestionnaireResponse();
        	
        case RETRIEVE_CHILD_QUESTIONNAIRE_RESPONSE:
        	return response = getRetrieveChildQuestionnaireResponse();   
        	
        case PERSIST_CHILD_QUESTIONNAIRE_RESPONSE:
        	return response = getPersistChildQuestionnaireResponse();  
        	
        case ADD_ROOT_QUESTION_RESPONSE:
        	return response = getAddRootQuestionResponse();
        	
        case LOCATE_ROOT_QUESTION_RESPONSE:
        	return response = getLocateRootQuestionResponse();
        	
        case PROD_STRUCTURE_HIERARCHY_RESPONSE:
        	return response = getRetrieveProdStructureComponentHierarchyResponse();
        	
        case RETRIEVE_PRODUCT_COMPONENT_HIERARCHY_RESPONSE:
        	return response = getProductComponentHierarchyResponse();
        case POPUP_RESPONSE:
        	return response = getPopupResponse();
        case QUESTIONNAIRE_PR_RESPONSE:
        	return response = getSaveAdminOptionQuestionnaireResponse();
        case RETRIEVE_PRODUCT_COMPONENT_RULE_RESPONSE:
        	return response = getProductComponentRuleResponse();
        case CHECKOUT_DATESEGMENT_RESPONSE:
        	return response = getCheckoutDateSegmentResponse();
        	
         case SEARCH_IND_MAINTAIN_RESPONSE:	
        	return response=getSearchIndicativeMappingResponse();
        case DELETE_IND_MAINTAIN_RESPONSE:	
        	return response=getDeleteIndicativeMappingResponse();
        case RETRIEVE_IND_MAINTAIN_RESPONSE:	
        	return response=getRetrieveIndicativeMappingResponse();
        case QUESTION_NOTES_POPUP_RESPONSE:
        	return response = getQuestionNotesPopUpResponse();
        case BENEFIT_QUESTION_NOTES__RESPONSE:
        	return response = getBenefitQuestionNoteResponse();
        case CREATE_IND_MAINTAIN_RESPONSE:
        	return response = getCreateIndicativeMappingResponse();
        case EDIT_IND_MAINTAIN_RESPONSE:
        	return response = getEditIndicativeMappingResponse();
        case ACCUMULATOR_FILTER_RESPONSE:
        	return response = getAccumulatorFilterResponse();
        case NOTES_QUESTION_RESPONSE:
        	return response = getQuestionNotesResponse();
        case CREATE_REF_MAPPING_RESPONSE:
        	return response = getCreateReferenceMappingResponse();
        case EDIT_REF_MAPPING_RESPONSE:
        	return response = getEditReferenceMappingResponse();
        case RETRIEVE_REF_MAPPING_RESPONSE:
        	return response = getRetrieveReferenceMappingResponse();
        	
        	
        case DELETE_REF_MAPPING_RESPONSE:
        	return response = getDeleteReferenceMappingResponse();
        case SEARCH_REF_MAPPING_RESPONSE:
        	return response = getSearchReferenceMappingResponse();
        	
        case ATTACH_NOTESTOQUESTION_PS:
        	return response = getAttachNotesToQuestionResponseForPS();
        	
        case TIER_LOOKUP_RESPONSE:
        	return response = getTierLookUpResponse();
        	
        case BENEFIT_TIER_DEFN_ASSN_RESPONSE:
        	return response = getBenefitTierDefnAssnResponse();
        	
        case BENEFIT_TIER_DEFN_RESPONSE:
        	return response = getBenefitTierDefnResponse();
        	
        case CONTRACT_UNCODED_NOTES_RESPONSE:
        	return response = getContractUncodedNotesResponse();
        	
        case DELETE_PRODUCT_TIER_RESPONSE:
        	return response = getDeleteBenefitTierResponse();
    	
        case DELETE_CONTRACT_TIER_RESPONSE:
        	return response = getDeleteContractBenTierResponse();
        	
        case PRODUCT_TIER_DEFN_RESPONSE:
        	return response = getProductTierDefnResponse();
        	
        case PRODUCT_BNFTTIER_DEFN_RESPONSE:
        	return response = getProductBnftTierDefnResponse();
        	
        case UPDATE_PRODUCT_BNFT_GENINFO_RESPONSE:
        	return response = getUpdateProductBnftGenInfoResponse();
        
        case DELETE_LEVEL_FROM_TIER_RESPONSE:
        	return response = getDeleteLevelFromTierResponse();
        
        case PRODUCT_BNFT_TIER_VALIDN_RESPONSE:
        	return response = getProductBenefitTierValidnResponse();
        
        case BENEFIT_TIER_DEFINITION_RESPONSE:
        	return response = getBenefitTierDefinitionResponse();
        	
        case PRODUCT_BENEFIT_TIER_DEF_SAVE_RESPONSE:
        	return response = getProductTierDefSaveResponse();
        
        case CONTRACT_BENEFIT_TIER_DEF_SAVE_RESPONSE:
        	return response = getContractTierDefSaveResponse();
        case SEARCH_ADMIN_METHOD_RESPONSE:
        	return response = getSearchAdminMethodResponse();
        case DELETE_ADMIN_METHOD_RESPONSE:
        	return response = getDeleteAdminMethodResponse();	
        case VIEW_ADMIN_METHOD_RESPONSE:
            	return response = getViewAdminMethodResponse();
        case ADMINMETHOD_VIEWALL_RESPONSE:
        	return response = getViewAllAdminMethodResponse();
        case EDIT_ADMIN_METHOD_RESPONSE:
        	return response = getEditAdminMethodResponse();
        case RETREIVE_RULE_TYPE_PRODUCT_RESPONSE:
        	return response = getRetreiveProductRuleTypeResponse();
        case RETREIVE_RULE_TYPE_CONTRACT_RESPONSE:
        	return response = getRetreiveContractRuleTypeResponse();
        case SEARCH_ADMIN_METHOD_MAPPING_RESPONSE:
        	return response = getAdminMethodMappingSearchResponse();
        case VIEW_ADMIN_METHOD_MAPPING_RESPONSE:
        	return response = getAdminMethodMappingViewResponse();
        case CHECK_COPY_LEGACY_NOTE_RESPONSE:
        	return response = getCheckCopyLegacyNoteResponse();
        case DATAFEED_REQUEST_DEL_DS_RETRIEVE:
        	return response = getDeletedDateSegmentsResponse();
        case SEARCH_VARIABLE_MAPPING_RESPONSE:
        	return response = getVariableMappingResponse();
        case INDICATIVE_DETAIL_RESPONSE:
        	return new IndicativeDetailResponse();   
        case CONFIRM_IMPORT_RESPONSE:
        	return new ConfirmImportIndicativeMappingResponse();       	
        default:
        }
        return response;
    }
    
    private static ContractReportResponse getContractReportResponse() {
    	return new ContractReportResponse();
    }
    
    /**
     * 
     * @return
     */
    private static WPDResponse getDeletedDateSegmentsResponse(){
    	RetrieveDeletedDSResponse response = new RetrieveDeletedDSResponse();
    	return response;
    }
    /**
	 * @return
	 */
	private static WPDResponse getCheckCopyLegacyNoteResponse() {
		CheckCopyLegacyNoteResponse response = new CheckCopyLegacyNoteResponse();
		return response;
	}

	/**
	 * @return
	 */
	private static WPDResponse getAdminMethodMappingViewResponse() {
		AdminMethodMappingViewResponse response = new AdminMethodMappingViewResponse();
		return response;
	}

	/**
	 * @return
	 */
	private static WPDResponse getAdminMethodMappingSearchResponse() {
		AdminMethodMappingSearchResponse response = new AdminMethodMappingSearchResponse();
		return response;
		
	}

	/**
	 * @return
	 */
	private static WPDResponse getRetreiveContractRuleTypeResponse() {
		RetreiveContractRuleTypeResponse response = new RetreiveContractRuleTypeResponse();
		return response;
	}
	
    /**
	 * @return
	 */
	private static WPDResponse getRetreiveProductRuleTypeResponse() {
		RetreiveProductRuleTypeResponse response = new RetreiveProductRuleTypeResponse();
		return response;
	}
	
    /**
	 * @return
	 */
	private static WPDResponse getEditAdminMethodResponse() {
		AdminMethodEditResponse adminMethodEditResponse=new AdminMethodEditResponse();
		return adminMethodEditResponse;
	}
	
    	
    /**
	 * @return
	 */
	private static WPDResponse getViewAdminMethodResponse() {
		AdminMethodViewResponse adminMethodViewResponse=new AdminMethodViewResponse();
		return adminMethodViewResponse;
	}
	
	
	/**
	 * @return
	 */
	private static WPDResponse getViewAllAdminMethodResponse() {
		AdminmethodViewAllResponse adminmethodViewAllResponse=new AdminmethodViewAllResponse();
		return adminmethodViewAllResponse;
	}

	/**
	 * @return
	 */
	private static WPDResponse getSearchAdminMethodResponse() {
		AdminMethodSearchResponse adminMethodSearchResponse=new AdminMethodSearchResponse();
		return adminMethodSearchResponse;
	}
	
	/**
	 * 
	 * @return
	 */
	private static WPDResponse getDeleteAdminMethodResponse() {
		AdminMethodDeleteResponse adminMethodDeleteResponse=new AdminMethodDeleteResponse();
		return adminMethodDeleteResponse;
	}
	
	private static WPDResponse getProductTierDefSaveResponse() {
		ProductTierDefSaveResponse productTierSaveResponse = new ProductTierDefSaveResponse();
		return productTierSaveResponse;
	}
	 /**
	 * @return
	 */
	private static WPDResponse getContractTierDefSaveResponse() {
		ContractTierDefSaveResponse contractTierSaveResponse = new ContractTierDefSaveResponse(); 
		return contractTierSaveResponse;
	}
    /**
	 * @return
	 */
    
    /**
	 * @return
	 */

	private static WPDResponse getDeleteLevelFromTierResponse() {
		DeleteLevelFromTierResponse deleteLevelFromTierResponse = new DeleteLevelFromTierResponse();
		return deleteLevelFromTierResponse;
	}

	/**
	 * @return
	 */

	private static WPDResponse getBenefitTierDefinitionResponse() {
		TierDefinitionRetrieveResponse tierDefinitionResponse = new TierDefinitionRetrieveResponse(); 
		return tierDefinitionResponse;
	}
    /**
	 * @return
	 */

	private static WPDResponse getContractUncodedNotesResponse() {
		ContractUncodedNotesResponse contractUncodedNotesResponse= new ContractUncodedNotesResponse();
		return contractUncodedNotesResponse;
	}
    /**
	 * @return
	 */
	private static WPDResponse getEditIndicativeMappingResponse() {
		EditIndicativeMappingResponse editIndicativeMappingResponse= new EditIndicativeMappingResponse();
		return editIndicativeMappingResponse;
	}
    /**
	 * @return
	 */
	private static WPDResponse getCreateIndicativeMappingResponse() {
		CreateIndicativeMappingResponse createIndicativeMappingResponse= new CreateIndicativeMappingResponse();
		return createIndicativeMappingResponse;
	}
	
	private static WPDResponse getCreateReferenceMappingResponse() {
		CreateReferenceMappingResponse createReferenceMappingResponse= new CreateReferenceMappingResponse();
		return createReferenceMappingResponse;
	}
	
	private static WPDResponse getEditReferenceMappingResponse() {
		EditReferenceMappingResponse editReferenceMappingResponse= new EditReferenceMappingResponse();
		return editReferenceMappingResponse;
	}
	
	private static WPDResponse getRetrieveReferenceMappingResponse() {
		RetrieveReferenceMappingResponse retrieveReferenceMappingResponse= new RetrieveReferenceMappingResponse();
		return retrieveReferenceMappingResponse;
	}
	
	private static WPDResponse getDeleteReferenceMappingResponse() {
		DeleteReferenceMappingResponse deleteReferenceMappingResponse= new DeleteReferenceMappingResponse();
		return deleteReferenceMappingResponse;
	}
	
	private static WPDResponse getDeleteAdminMethodMappingResponse() {
		AdminMethodMappingDeleteResponse adminMethodMappingDeleteResponse= new AdminMethodMappingDeleteResponse();
		return adminMethodMappingDeleteResponse;
	}
	
	
	private static WPDResponse getSearchReferenceMappingResponse() {
		SearchReferenceMappingResponse SearchReferenceMappingResponse= new SearchReferenceMappingResponse();
		return SearchReferenceMappingResponse;
	}
	
	
    /**
	 * @return
	 */
	private static WPDResponse getProductComponentHierarchyResponse() {
		RetrieveProductComponentHierarchyResponse hierarchyResponse = new RetrieveProductComponentHierarchyResponse();
		return hierarchyResponse;
	}
	
	private static WPDResponse getSaveAdminOptionQuestionnaireResponse() {
		SaveAdminOptionQuestionnaireResponse saveAdminOptionQuestionnaireResponse = new SaveAdminOptionQuestionnaireResponse();
		return saveAdminOptionQuestionnaireResponse;
	}
	private static WPDResponse getRetrieveAmSPSValidResponse(){
		AdminMethodSPSRetrieveResponse adminMethodSPSRetrieveResponse= new AdminMethodSPSRetrieveResponse();
		return adminMethodSPSRetrieveResponse;
	}
    private static WPDResponse getPersistChildQuestionnaireResponse(){
    	return new PersistChildQuestionnaireResponse();
    }
    
    private static WPDResponse getRetrieveChildQuestionnaireResponse(){
    	return new RetrieveChildQuestionnaireResponse();
    }
    
    private static WPDResponse getRetrieveRootQuestionnaireResponse(){
    	return new RetrieveRootQuestionnaireResponse();
    }
    
    private static WPDResponse getUpdateProductQuestionareResponse() {
		return new UpdateProductQuestionareResponse();
	}
    private static WPDResponse getRetrieveProductQuestionareResponse() {
		return new RetrieveProductQuestionareResponse();
	}
    	
	private static WPDResponse getRetrieveServiceMappingResponse() {
		return new RetrieveServiceTypeMappingResponse();
	}
	private static WPDResponse getBnftComptNotesToQstnAttchmtResponse() {
		return new BnftCompNotesToQuestionAttachmentResponse();
	}
	private static WPDResponse getContractNotesToQstnAttchmtResponse() {
		return new ContractNotesToQuestionAttachmentResponse();
	}
	private static WPDResponse getContractAONotesToQstnAttchmtResponse() {
		return new ContractAONotesToQuestionAttachmentResponse();
	}
	private static WPDResponse getContractTeiredAONotesToQstnAttchmtResponse() {
		return new ContractTeiredNotesToQuestionAttachmentResponse();
	}
	private static WPDResponse getProductAONotesToQstnAttchmtResponse() {
		return new ProductAONotesToQuestionAttachmentResponse();
	}
	private static WPDResponse getSpsmappingViewResponse(){
		SPSMappingViewResponse mappingViewResponse = new SPSMappingViewResponse();
		return mappingViewResponse;
	}
	
	private static WPDResponse getCustomMessageSearchResponse(){
		SearchCustomMessageResponse searchCustomMessageResponse = new SearchCustomMessageResponse();
		return searchCustomMessageResponse;
	}
	private static WPDResponse getCustomMessageRetrieveResponse(){
		CustomMessageRetrieveResponse customMessageRetrieveResponse = new CustomMessageRetrieveResponse();
		return customMessageRetrieveResponse;
	}
	private static WPDResponse getSaveServiceMappingResponse() {
		SaveServiceTypeMappingResponse response = new SaveServiceTypeMappingResponse();
		return response;
	}   
   
    private static WPDResponse getSpsMappingUpdateResponse(){
    	return new SPSMappingUpdateResponse();
    }
	private static WPDResponse getSpsmappingResponse() {
		SPSMappingCreateResponse response = new SPSMappingCreateResponse();
		return response;
	}
	/**
	 * @return
	 */
	private static WPDResponse getAdminMethodValidationStatusResponse() {
		AdminMethodValidationStatusResponse response = new AdminMethodValidationStatusResponse();
		return response;
	}

	private static WPDResponse  getBenefitComponentUnlockResponse(){
    	BenefitComponentUnlockResponse benefitComponentUnlockResponse= new BenefitComponentUnlockResponse();
		return benefitComponentUnlockResponse;
    }
    
	private static WPDResponse  getAdminOptionResponseForPs(){
    	SaveAdminOptionResponseForPS saveAdminOptionResponse= new SaveAdminOptionResponseForPS();
		return saveAdminOptionResponse;
    }
    
    private static WPDResponse  getAdminOptionhideResponse(){
    	SaveAdminOptionResponse saveAdminOptionResponse= new SaveAdminOptionResponse();
		return saveAdminOptionResponse;
    }
    
    private static WPDResponse  getProductAdminOptionResponse(){
    	HideProductAdminOptionResponse hideProductAdminOptionResponse= new HideProductAdminOptionResponse();
		return hideProductAdminOptionResponse;
    }
    /**
	 * @return
	 */
	private static WPDResponse getQuestionDeleteResponse() {
	    QuestionDeleteResponse deleteQuestionResponse= new QuestionDeleteResponse();
		return deleteQuestionResponse;
	}
	
    /**
	 * @return
	 */
	private static WPDResponse getContractMandateInfo() {
	    RetrieveBenefitMandatesResponse retrieveBenefitMandatesResponse= new RetrieveBenefitMandatesResponse();
		return retrieveBenefitMandatesResponse;
	}
	 /**
	 * @return
	 */
	private static WPDResponse getRetreiveBenefitsContractResponse(){
		RetreiveBenefitsContractResponse retreiveBenefitsContractResponse = new RetreiveBenefitsContractResponse();
		return retreiveBenefitsContractResponse;
	}
    /**
	 * @return
	 */
	private static WPDResponse getConfirmMigrationContractResponse() {
		ConfirmMigrationContractResponse confirmMigrationContractResponse= new ConfirmMigrationContractResponse();
		return confirmMigrationContractResponse;
	}
    /**
	 * @return
	 */
	private static WPDResponse getSaveLastAccessPageInfoResponse() {
		SaveLastAccessPageInfoResponse saveLastAccessPageInfoResponse= new SaveLastAccessPageInfoResponse();
		return saveLastAccessPageInfoResponse;
	}	
	/**
	 * @return
	 */
	private static WPDResponse getMigrationGenerateReportResponse() {
		MigrationGenerateReportResponse migrationGenerateReportResponse= new MigrationGenerateReportResponse();
		return migrationGenerateReportResponse;
	}


	/**
     * @return
     */
    private static WPDResponse getSaveLegacyContractResponse() {
        SaveLegacyContractResponse saveLegacyContractResponse = new SaveLegacyContractResponse();
        return saveLegacyContractResponse;
    }

    /**
     * @return
     */
    private static WPDResponse getMigrationContractRulesResponse() {
    	MigrationContractRulesResponse response = new MigrationContractRulesResponse();
        return response;
    }

    
    
    /**
     * @return
     */
    private static WPDResponse getCancelMigrationResponse() {
    	CancelMigrationResponse response = new CancelMigrationResponse();
        return response;
    }
    
    /**
     * @return
     */
    private static WPDResponse getRetrieveLegacyContractResponse() {
        RetrieveLegacyContractResponse retrieveLegacyContractResponse = new RetrieveLegacyContractResponse();
        return retrieveLegacyContractResponse;
    }


    /**
     * @return
     */
    private static WPDResponse getTaskLookUpResponse() {
        TaskLookUpResponse response = new TaskLookUpResponse();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getRetrieveMigVariableMappingResponse() {
        RetrieveMigVariableMappingResponse retrieveMigVariableMappingResponse = new RetrieveMigVariableMappingResponse();
        return retrieveMigVariableMappingResponse;
    }


    /**
     * @return
     */
    private static WPDResponse getRetrieveLegacyLookUpResponse() {
        RetrieveLegacyLookUpResponse retrieveLegacyLookUpResponse = new RetrieveLegacyLookUpResponse();
        return retrieveLegacyLookUpResponse;
    }


    private static WPDResponse getSaveMigVariableMappingResponse() {
        SaveMigVariableMappingResponse saveMigVariableMappingResponse = new SaveMigVariableMappingResponse();
        return saveMigVariableMappingResponse;

    }
    private static WPDResponse getSaveMigrationNotesResponse() {
    	SaveMigrationNotesResponse saveMigrationNotesResponse = new SaveMigrationNotesResponse();
        return saveMigrationNotesResponse;

    }

    

    /**
     * @return
     */
    private static WPDResponse getMigrationPricingInfoResponse() {
        MigrationPricingInfoResponse migrationPricingInfoResponse = new MigrationPricingInfoResponse();
        return migrationPricingInfoResponse;
    }

    /**
     * @return
     */
    private static WPDResponse getMigrationProductInfoResponse() {
        MigrationProductInfoResponse migrationProductInfoResponse = new MigrationProductInfoResponse();
        return migrationProductInfoResponse;
    }
    /**
     * @return
     */
    private static WPDResponse getDeleteTaskAssociationResponse() {
        DeleteTaskAssociationResponse response = new DeleteTaskAssociationResponse();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getProductBenefitMndateRetrieveResponse() {
        ProductBenefitMndateRetrieveResponse response = new ProductBenefitMndateRetrieveResponse();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getRetrieveRoleAssociationResponse() {
        RetrieveRoleAssociationResponse response = new RetrieveRoleAssociationResponse();
        return response;
    }


    public static WPDResponse getReferenceDataDomainValidationResponse() {
        RefDataDomainValidationResponse referenceDataDomainValidationResponse = new RefDataDomainValidationResponse();
        return referenceDataDomainValidationResponse;
    }


    private static WPDResponse getSaveProductRulesResponse() {
        SaveProductRulesResponse response = new SaveProductRulesResponse();
        return response;
    }


    private static WPDResponse getDeleteProductRuleResponse() {
        DeleteProductRuleResponse response = new DeleteProductRuleResponse();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getContractRetrieveAdminOptionResponse() {
        RetrieveContractProductAdminOptionOverrideResponse response = new RetrieveContractProductAdminOptionOverrideResponse();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getSaveContractAdminiOptionResponse() {
        SaveContractAdministrationResponse response = new SaveContractAdministrationResponse();
        return response;
    }


    /**
     * @return
     */

    /**
     * @return
     */
    private static WPDResponse getProductRetrieveAdminOptionResponse() {
        RetrieveProductAdminOptionResponse response = new RetrieveProductAdminOptionResponse();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getSaveSubTaskResponse() {
        SaveSubTaskResponse response = new SaveSubTaskResponse();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getSaveProductBenefitAdministrationResponse() {
        SaveProductBenefitAdministrationResponse response = new SaveProductBenefitAdministrationResponse();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getRetrieveRoleResponse() {
        RetrieveRoleResponse response = new RetrieveRoleResponse();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getRetrieveContractProductAdminResponse() {
        RetrieveContractProductAdminResponse response = new RetrieveContractProductAdminResponse();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getSaveRoleResponse() {
        SaveRoleResponse response = new SaveRoleResponse();
        return response;
    }


    /**
     * @return BenefitLineNotesResponse
     */
    private static WPDResponse getBenefitLineNotesResponse() {
        BenefitLineNotesResponse response = new BenefitLineNotesResponse();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getDeleteContractProductAdminResponse() {
        DeleteContractProductAdminResponse response = new DeleteContractProductAdminResponse();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getSaveContractProductAdminResponse() {
        SaveContractProductAdminResponse response = new SaveContractProductAdminResponse();
        return response;
    }


    private static WPDResponse getSoftDeleteResponse() {
        ItemSoftDeleteResponse response = new ItemSoftDeleteResponse();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getReservedContractSearchResponse() {
        ReservedContractSearchResponse response = new ReservedContractSearchResponse();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getContractLoadBenefitNoteAttachmentResponse() {
        LoadContractBenefitNoteResponse response = new LoadContractBenefitNoteResponse();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getContractBenefitNoteResponse() {
        SaveContractBenefitNoteResponse response = new SaveContractBenefitNoteResponse();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getBenefitLineNotesAttachmentResponse() {
        BenefitLineNotesAttachmentResponse response = new BenefitLineNotesAttachmentResponse();
        return response;
    }


    private static WPDResponse getBenefitLineNotesOverrideResponse() {
        ContractAttachNotesResponse response = new ContractAttachNotesResponse();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getNotesAttachmentForBnftLineRespone() {
        NotesAttachmentResponseForBnftLine response = new NotesAttachmentResponseForBnftLine();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getDeleteContractResponse() {
        DeleteContractResponse response = new DeleteContractResponse();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getSearchSubCatalogResponse() {
        // Create an instance of SubCatalog Search Response
        SubCatalogSearchResponse response = new SubCatalogSearchResponse();

        return response;
    }


    /**
     * 
     * @return
     */
    private static WPDResponse getSaveTaskResponse() {
        SaveTaskResponse response = new SaveTaskResponse();
        return response;
    }


    /**
     * 
     * @return
     */

    private static WPDResponse getSearchRoleResponse() {
        SearchRoleResponse response = new SearchRoleResponse();
        return response;
    }
    private static WPDResponse getAccumulatorFilterResponse(){
		AccumulatorFilterResponse  response = new AccumulatorFilterResponse();
		return response;
	}

    /**
     * 
     * @return
     */
    private static WPDResponse getRetrieveTaskResponse() {
        RetrieveTaskResponse response = new RetrieveTaskResponse();
        return response;
    }


    /**
     * 
     * @return
     */
    private static WPDResponse getSubTaskLookupResponse() {
        SubTaskLookUpResponse response = new SubTaskLookUpResponse();
        return response;
    }


    private static WPDResponse getRetrieveModuleAssociationResponse() {
        RetrieveModuleAssociationResponse response = new RetrieveModuleAssociationResponse();
        return response;
    }


    /**
     * 
     * @return
     */
    private static WPDResponse getLocateSubTaskResponse() {
        LocateSubTaskResponse response = new LocateSubTaskResponse();
        return response;
    }


    /**
     * 
     * @return
     */
    private static WPDResponse getSaveSubTaskAssociationResponse() {
        SaveSubTaskAssociationResponse response = new SaveSubTaskAssociationResponse();
        return response;
    }


    /**
     * 
     * @return
     */
    private static WPDResponse getSaveTaskAssociationResponse() {
        SaveTaskAssociationResponse response = new SaveTaskAssociationResponse();
        return response;
    }


    private static WPDResponse getSaveModuleAssociationResponse() {
        SaveModuleAssociationResponse response = new SaveModuleAssociationResponse();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getDeleteTaskAssociationresponse() {
        DeleteTaskAssociationResponse response = new DeleteTaskAssociationResponse();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getProductAdminDeleteResponse() {
        // Create an instance of SubCatalog Search Response
        ProductAdminDeleteResponse productAdminDeleteResponse = new ProductAdminDeleteResponse();
        return productAdminDeleteResponse;
    }


    /**
     * 
     * @return
     */
    private static WPDResponse getSaveModuleResponse() {
        SaveModuleResponse response = new SaveModuleResponse();
        return response;
    }


    /**
     * 
     * @return
     */
    private static WPDResponse getDeleteModuleAssociationResponse() {
        DeleteModuleTaskAssociationResponse response = new DeleteModuleTaskAssociationResponse();
        return response;
    }


    /**
     * 
     * @return
     */
    private static WPDResponse getRetrieveRoleModuleAssociationResponse() {
        RetrieveRoleModuleAssociationResponse response = new RetrieveRoleModuleAssociationResponse();
        return response;
    }


    /**
     * 
     * @return
     */
    private static WPDResponse getRoleModuleLookUpResponse() {
        RoleModuleLookUpResponse response = new RoleModuleLookUpResponse();
        return response;
    }


    /**
     * 
     * @return
     */
    private static WPDResponse getSaveRoleModuleAsociationResponse() {
        SaveRoleModuleAssociationResponse response = new SaveRoleModuleAssociationResponse();
        return response;
    }


    /**
     * 
     * @return
     */
    private static WPDResponse getRoleTasklookUpResponse() {
        TaskLookUpResponse response = new TaskLookUpResponse();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getRetrieveContractNoteResponse() {
        RetrieveContractNoteResponse response = new RetrieveContractNoteResponse();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getDeleteContractComponentNoteAttachmentResponse() {
        DeleteContractComponentNoteResponse response = new DeleteContractComponentNoteResponse();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getContractLoadBenefitComponentNoteAttachmentResponse() {
        LoadContractComponentNoteResponse response = new LoadContractComponentNoteResponse();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getContractBenefitComponentNoteAttachmentResponse() {
        ContractBenefitComponentAttachNotesResponse response = new ContractBenefitComponentAttachNotesResponse();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getContractNoteAttachmentResponse() {
        ContractNoteAttachmentResponse response = new ContractNoteAttachmentResponse();
        return response;
    }


    /**
     * 
     * /**
     * 
     * @return
     */
    private static WPDResponse getContractNotesDeleteResponse() {
        ContractNotesDeleteResponse response = new ContractNotesDeleteResponse();
        return response;
    }


    /**
     * 
     * /**
     * 
     * @return
     */
    private static WPDResponse getContractBenefitGeneralInfoResponse() {
        RetrieveBenefitGeneralInfoResponse response = new RetrieveBenefitGeneralInfoResponse();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getSaveContractBenefitAdminResponse() {
        SaveContractBenefitAdministrationResponse response = new SaveContractBenefitAdministrationResponse();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getRetrieveContractBenefitAdminResponse() {
        RetrieveContractBenefitAdministrationResponse retrieveContractBenefitAdministrationResponse = new RetrieveContractBenefitAdministrationResponse();
        return retrieveContractBenefitAdministrationResponse;
    }

    /**
     * @return
     */
    private static WPDResponse getRetrieveAllPossibleAnswerResponse() {
        RetrieveAllPossibleAnswerResponse retrieveAllPossibleAnswerResponse = new RetrieveAllPossibleAnswerResponse();
        return retrieveAllPossibleAnswerResponse;
    }
    /**
     * @return
     */
    private static WPDResponse getRetrieveSelectedCommentResponse() {
        RetrieveSelectedCommentResponse retrieveSelectedCommentResponse = new RetrieveSelectedCommentResponse();
        return retrieveSelectedCommentResponse;
    }


    /**
     * @return
     */
    private static WPDResponse getCreateItemResponse() {
        SaveItemResponse itemResponse = new SaveItemResponse();
        return itemResponse;
    }


    /**
     * @return
     */
    private static WPDResponse getDeleteRoleResponse() {
        RoleDeleteResponse roleResponse = new RoleDeleteResponse();
        return roleResponse;
    }


    /**
     * @return
     */
    private static WPDResponse getLocateItemResponse() {
        RetrieveItemResponse itemResponse = new RetrieveItemResponse();
        return itemResponse;
    }
    /**
     * 
     * @return
     */
    private static WPDResponse getCatalogLookUpResponse(){
        CatalogLookUpResponse response = new CatalogLookUpResponse();
        return response;
    }

    /**
     * @return
     */
    private static WPDResponse getItemSearchResponse() {
        SearchItemResponse itemResponse = new SearchItemResponse();
        return itemResponse;
    }


    /**
     * for retrieving Contract Comment
     * 
     * @return
     */
    public static RetrieveDateSegmentCommentResponse getRetrieveDateSegmentCommentResponse() {
        RetrieveDateSegmentCommentResponse retrieveDateSegmentCommentResponse = new RetrieveDateSegmentCommentResponse();
        return retrieveDateSegmentCommentResponse;
    }


    /**
     * for saving Contract Comment
     * 
     * @return
     */
    public static SaveDateSegmentCommentResponse getContractCommentResponse() {
        SaveDateSegmentCommentResponse saveDateSegmentCommentResponse = new SaveDateSegmentCommentResponse();
        return saveDateSegmentCommentResponse;
    }


    /**
     * for deleting the product notes
     * 
     * @return
     */
    public static SaveProductNoteResponse getSaveProductNoteResponse() {
        SaveProductNoteResponse saveProductNoteResponse = new SaveProductNoteResponse();
        return saveProductNoteResponse;
    }


    /**
     * for loading the product notes
     * 
     * @return
     */
    public static RetrieveProductNoteResponse getRetrieveProductNoteResponse() {
        RetrieveProductNoteResponse retrieveProductNoteResponse = new RetrieveProductNoteResponse();
        return retrieveProductNoteResponse;
    }


    /**
     * for saving the product notes
     * 
     * @return
     */
    public static DeleteProductNoteResponse getDeleteProductNoteResponse() {
        DeleteProductNoteResponse deleteProductNoteResponse = new DeleteProductNoteResponse();
        return deleteProductNoteResponse;
    }


    /**
     * for loading the notes associated to a benefit component
     * 
     * @return
     */
    public static LoadProductComponentNoteResponse getLoadProductComponentNoteResponse() {
        LoadProductComponentNoteResponse loadProductComponentNoteResponse = new LoadProductComponentNoteResponse();
        return loadProductComponentNoteResponse;
    }


    /**
     * for attaching the notes associated to a benefit component
     * 
     * @return
     */
    public static SaveProductComponentNoteResponse getSaveProductComponentNoteResponse() {
        SaveProductComponentNoteResponse saveProductComponentNoteResponse = new SaveProductComponentNoteResponse();
        return saveProductComponentNoteResponse;
    }


    /**
     * for unattaching the notes associated to a benefit component
     * 
     * @return
     */
    public static DeleteProductComponentNoteResponse getDeleteProductComponentNoteResponse() {
        DeleteProductComponentNoteResponse deleteProductComponentNoteResponse = new DeleteProductComponentNoteResponse();
        return deleteProductComponentNoteResponse;
    }


    /**
     * for unattaching the notes associated to a benefit component
     * 
     * @return
     */
    public static DeleteProductBenefitNoteResponse getDeleteProductBenefitNoteResponse() {
        DeleteProductBenefitNoteResponse deleteProductBenefitNoteResponse = new DeleteProductBenefitNoteResponse();
        return deleteProductBenefitNoteResponse;
    }


    /**
     * for loading the notes associated to a benefit component
     * 
     * @return
     */
    public static LoadProductBenefitNoteResponse getLoadProductBenefitNoteResponse() {
        LoadProductBenefitNoteResponse loadProductBenefitNoteResponse = new LoadProductBenefitNoteResponse();
        return loadProductBenefitNoteResponse;
    }


    /**
     * for attaching the notes associated to a benefit
     * 
     * @return
     */
    public static SaveProductBenefitNoteResponse getSaveProductBenefitNoteResponse() {
        SaveProductBenefitNoteResponse saveProductBenefitNoteResponse = new SaveProductBenefitNoteResponse();
        return saveProductBenefitNoteResponse;
    }


    /**
     * for deleting the notes
     * 
     * @return
     */
    private static WPDResponse getNotesDeleteResponse() {
        DeleteNotesResponse deleteNotesResponse = new DeleteNotesResponse();
        return deleteNotesResponse;
    }


    /**
     * for override benefit line notes to product
     * 
     * @return
     */
    private static WPDResponse getProductBenefitLineNotesOverrideResponse() {
        ProductLineOverrideNotesResponse response = new ProductLineOverrideNotesResponse();
        return response;
    }


    /**
     * for locatingTargerSystems for notes
     * 
     * @return
     */
    private static WPDResponse getTargetSystemForNotesResponse() {
        LocateTargetSystemsForNotesResponse forNotesResponse = new LocateTargetSystemsForNotesResponse();
        return forNotesResponse;
    }


    /**
     * @return
     */

    private static WPDResponse getNotesAtachmentResponse() {
        // Create an instance of Response
        NotesAttachmentResponse attachmentResponse = new NotesAttachmentResponse();
        return attachmentResponse;
    }


    public static SaveCatalogResponse getSaveCatalogResponse() {
        SaveCatalogResponse saveCatalogResponse = new SaveCatalogResponse();
        return saveCatalogResponse;
    }


    public static EditCatalogResponse getEditCatalogResponse() {
        EditCatalogResponse editCatalogResponse = new EditCatalogResponse();
        return editCatalogResponse;
    }


    public static CatalogDeleteResponse getDeleteCatalogResponse() {
        CatalogDeleteResponse catalogDeleteResponse = new CatalogDeleteResponse();
        return catalogDeleteResponse;
    }


    public static CatalogRetrieveResponse getRetrieveCatalogResponse() {
        CatalogRetrieveResponse catalogRetrieveResponse = new CatalogRetrieveResponse();
        return catalogRetrieveResponse;
    }

    public static SrdaCatalogRetrieveResponse getSrdaRetrieveCatalogResponse() {
        SrdaCatalogRetrieveResponse srdaCatalogRetrieveResponse = new SrdaCatalogRetrieveResponse();
        return srdaCatalogRetrieveResponse;
    }



    public static WPDResponse getCatalogSearchResponse() {
        SearchCatalogResponse catalogResponse = new SearchCatalogResponse();
        return catalogResponse;
    }


    public static WPDResponse getTaskSearchResponse() {
        TaskSearchResponse taskSearchResponse = new TaskSearchResponse();
        return taskSearchResponse;
    }


    public static WPDResponse getTaskDeleteResponse() {
        TaskDeleteResponse taskDeleteResponse = new TaskDeleteResponse();
        return taskDeleteResponse;
    }


    public static WPDResponse getModuleDeleteResponse() {
        ModuleDeleteResponse moduleDeleteResponse = new ModuleDeleteResponse();
        return moduleDeleteResponse;
    }


    public static WPDResponse getModuleSearchResponse() {
        ModuleSearchResponse moduleSearchResponse = new ModuleSearchResponse();
        return moduleSearchResponse;
    }


    public static WPDResponse getSubCatalogSaveResponse() {
        SubCatalogSaveResponse subCatalogSaveResponse = new SubCatalogSaveResponse();
        return subCatalogSaveResponse;
    }


    public static WPDResponse getSubCatalogDeleteResponse() {
        SubCatalogDeleteResponse subCatalogDeleteResponse = new SubCatalogDeleteResponse();
        return subCatalogDeleteResponse;
    }


    /**
     * @return
     */

    private static WPDResponse getNotesCheckInResponse() {
        NotesCheckInResponse notesCheckInResponse = new NotesCheckInResponse();
        return notesCheckInResponse;
    }


    /**
     * @return
     */

    private static WPDResponse getRetrieveContractBenefitComponentResponse() {
        RetrieveContractBenefitComponentResponse retrieveContractBenefitComponentResponse = new RetrieveContractBenefitComponentResponse();
        return retrieveContractBenefitComponentResponse;
    }


    private static WPDResponse getRetrievePricingInfoResponse() {
        RetrieveContractPricingInfoResponse retrieveContractPricingInfoResponse = new RetrieveContractPricingInfoResponse();
        return retrieveContractPricingInfoResponse;
    }


    private static WPDResponse getSaveContractBasicInfoResponse() {
        return new SaveContractBasicInfoResponse();
    }


    /**
     * @return
     */
    private static WPDResponse getCreateNotesDataDomainResponse() {
        CreateNotesDataDomainResponse createNotesDataDomainResponse = new CreateNotesDataDomainResponse();
        return createNotesDataDomainResponse;
    }


    /**
     * @return
     */
    private static WPDResponse getRetrieveNotesResponse() {
        RetrieveNotesResponse retrieveNotesResponse = new RetrieveNotesResponse();
        return retrieveNotesResponse;
    }


    /**
     * @return
     */

    private static WPDResponse getDeletePricingInfoResponse() {
        DeletePricingInfoResponse deletePricingInfoResponse = new DeletePricingInfoResponse();
        return deletePricingInfoResponse;
    }


    /**
     * @return
     */
    private static WPDResponse getLocatePricingInfoResponse() {
        LocatePricingInformationResponse locatePricingInformationResponse = new LocatePricingInformationResponse();
        return locatePricingInformationResponse;
    }


    /**
     * @return
     */
    private static WPDResponse getRetrieveContractProduct() {
        RetrieveContractProductResponse retrieveContractProductResponse = new RetrieveContractProductResponse();
        return retrieveContractProductResponse;
    }


    /**
     * @return
     */
    private static WPDResponse getPricingInfoResponse() {
        SavePricingInfoResponse savePricingInfoResponse = new SavePricingInfoResponse();
        return savePricingInfoResponse;
    }


    /**
     * @return
     */
    private static WPDResponse getNoteDomainResponse() {
        NoteDomainResponse noteDomainResponse = new NoteDomainResponse();
        return noteDomainResponse;
    }


    /**
     * @return
     */
    private static WPDResponse getCreateNotesResponse() {
        CreateNotesResponse createNotesResponse = new CreateNotesResponse();
        return createNotesResponse;
    }


    /**
     * @return
     */
    private static WPDResponse getDeleteBenefitLineResponse() {
        DeleteBenefitLineResponse deleteBenefitLineResponse = new DeleteBenefitLineResponse();
        return deleteBenefitLineResponse;
    }


    /**
     * @return
     */
    private static WPDResponse getNotesSearchResponse() {
        NotesSearchResponse notesSearchResponse = new NotesSearchResponse();
        return notesSearchResponse;
    }


    /**
     * @return
     */
    private static WPDResponse getBenefitHierarchyResponse() {
        BenefitHierarchyResponse benefitHierarchyResponse = new BenefitHierarchyResponse();
        return benefitHierarchyResponse;
    }


    /**
     * @return
     */
    private static WPDResponse getBenefitHierarchyUpdateResponse() {
        BenefitHierarchyUpdateResponse benefitHierarchyUpdateResponse = new BenefitHierarchyUpdateResponse();
        return benefitHierarchyUpdateResponse;
    }


    /**
     * For UpdateComponentsBenefitAdministrationResponse
     * 
     * @return
     */
    private static WPDResponse getTestPassQuestionResponse() {
        TestPassQuestionResponse response = new TestPassQuestionResponse();
        return response;
    }


    /**
     * For UpdateComponentsBenefitAdministrationResponse
     * 
     * @return
     */
    private static WPDResponse getTestPassAdminOptionResponse() {
        TestPassAdminOptionResponse response = new TestPassAdminOptionResponse();
        return response;
    }


    /**
     * For UpdateComponentsBenefitAdministrationResponse
     * 
     * @return
     */
    private static WPDResponse getTestFailAdminOptionResponse() {
        TestFailAdminOptionResponse response = new TestFailAdminOptionResponse();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getStandardBenefitCheckOutResponse() {
        StandardBenefitCheckOutResponse standardBenefitCheckOutResponse = new StandardBenefitCheckOutResponse();
        return standardBenefitCheckOutResponse;
    }


    /**
     * For UpdateComponentsBenefitAdministrationResponse
     * 
     * @return
     */
    private static WPDResponse getPublishAdminOptionResponse() {
        PublishAdminOptionResponse response = new PublishAdminOptionResponse();
        return response;
    }


    /**
     * For UpdateComponentsBenefitAdministrationResponse
     * 
     * @return
     */
    private static WPDResponse getScheduleForTestAdminOptionResponse() {
        ScheduleForTestAdminOptionResponse response = new ScheduleForTestAdminOptionResponse();
        return response;
    }


    /**
     * For UpdateComponentsBenefitAdministrationResponse
     * 
     * @return
     */
    private static WPDResponse getUpdateComponentsBenefitAdministrationResponse() {
        UpdateComponentsBenefitAdministrationResponse response = new UpdateComponentsBenefitAdministrationResponse();
        return response;
    }


    /**
     * For LocateComponentsBenefitAdministrationResponse
     * 
     * @return
     */
    private static WPDResponse getLocateComponentsBenefitAdministrationResponse() {
        LocateComponentsBenefitAdministrationResponse response = new LocateComponentsBenefitAdministrationResponse();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getPublishQuestionResponse() {
        PublishQuestionResponse publishQuestionResponse = new PublishQuestionResponse();
        return publishQuestionResponse;
    }
    
    /**
     * @return
     */
    private static WPDResponse getUnlockQuestionResponse() {
        UnlockQuestionResponse unlockQuestionResponse = new UnlockQuestionResponse();
        return unlockQuestionResponse;
    }


    /**
     * @return
     */
    private static WPDResponse getCheckOutQuestionResponse() {
        CheckOutQuestionResponse checkOutQuestionResponse = new CheckOutQuestionResponse();
        return checkOutQuestionResponse;
    }


    /**
     * @return
     */
    private static WPDResponse getScheduleForTestQuestionResponse() {
        ScheduleForTestQuestionResponse scheduleForTestQuestionResponse = new ScheduleForTestQuestionResponse();
        return scheduleForTestQuestionResponse;
    }


    /**
     * @return
     */
    private static WPDResponse getCopyBenefitComponentResponse() {
        BenefitComponentCopyResponse benefitComponentCopyResponse = new BenefitComponentCopyResponse();
        return benefitComponentCopyResponse;
    }


    /**
     * @return
     */
    private static WPDResponse getRoleAssociationResponse() {
        RetrieveRoleAssociationResponse retrieveRoleAssociationResponse = new RetrieveRoleAssociationResponse();
        return retrieveRoleAssociationResponse;
    }


    /**
     * @return
     */
    private static WPDResponse getSaveRoleAssociationResponse() {
        SaveRoleAssociationResponse saveRoleAssociationResponse = new SaveRoleAssociationResponse();
        return saveRoleAssociationResponse;
    }


    /**
     * @return
     */
    private static WPDResponse getProductRuleRefDataResponse() {
        ProductRuleRefDataResponse response = new ProductRuleRefDataResponse();
        return response;
    }
    /**
     * @return
     */
    private static WPDResponse getadminOptionViewResponse() {
        AdminOptionViewResponse response = new AdminOptionViewResponse();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getSaveProductStructureBenefitDefinitionResponse() {
        // Create an instance of the response object
        SaveProductStructureBenefitDefinitionResponse benefitDefinitionResponse = new SaveProductStructureBenefitDefinitionResponse();

        return benefitDefinitionResponse;
    }


    /**
     * @return
     */
    private static WPDResponse getCreateBenefitLevelResponse() {
        BenefitLevelResponse benefitLevelResponse = new BenefitLevelResponse();
        return benefitLevelResponse;
    }


    /**
     * @return
     */
    private static WPDResponse getSaveBenefitLevelResponse() {
        SaveBenefitLevelResponse saveBenefitLevelResponse = new SaveBenefitLevelResponse();
        return saveBenefitLevelResponse;
    }


    /**
     * @return
     */
    private static WPDResponse getDeleteBenefitLevelResponse() {
        DeleteBenefitLevelResponse deleteBenefitLevelResponse = new DeleteBenefitLevelResponse();
        return deleteBenefitLevelResponse;
    }


    /**
     * @return
     */
    private static WPDResponse getSearchBenefitLevelResponse() {
        SearchBenefitLevelResponse searchBenefitLevelResponse = new SearchBenefitLevelResponse();
        return searchBenefitLevelResponse;
    }

    private static WPDResponse getSearchBenefitLevelTermResponse() {
        SearchBenefitLevelTermResponse searchBenefitLevelTermResponse = new SearchBenefitLevelTermResponse();
        return searchBenefitLevelTermResponse;
    }

    /**
     * @return
     */
    private static WPDResponse getDataTypeRetrieveResponse() {
        DataTypeRetrieveResponse dataTypeRetrieveResponse = new DataTypeRetrieveResponse();
        return dataTypeRetrieveResponse;
    }


    /**
     * @return
     */
    private static WPDResponse getCheckInMandateResponse() {
        MandateCheckInResponse response = new MandateCheckInResponse();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getLocateMandateResponse() {
        LocateMandateResponse response = new LocateMandateResponse();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getRetrieveMandateResponse() {
        RetrieveMandateResponse response = new RetrieveMandateResponse();
        return response;

    }


    /**
     * @return
     */
    private static WPDResponse getCheckOutMandateResponse() {
        CheckOutMandateResponse response = new CheckOutMandateResponse();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getDeleteMandateResponse() {
        DeleteMandateResponse response = new DeleteMandateResponse();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getCreateMandateResponse() {
        CreateMandateResponse response = new CreateMandateResponse();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getCopyMandateResponse() {
        CopyMandateResponse response = new CopyMandateResponse();
        return response;
    }


    /**
     * for UpdateBenefitLinesResponse
     * 
     * @return
     */
    private static WPDResponse getUpdateBenefitLinesResponse() {
        UpdateBenefitLinesResponse response = new UpdateBenefitLinesResponse();
        return response;
    }


    /**
     * For LocateComponentsBenefitDefinitionResponse
     * 
     * @return
     */
    private static WPDResponse getLocateComponentsBenefitDefinitionResponse() {
        LocateComponentsBenefitDefinitionResponse response = new LocateComponentsBenefitDefinitionResponse();
        return response;
    }


    /**
     * For LocateComponentsBenefitDefinitionResponse
     * 
     * @return
     */
    private static WPDResponse getBenefitComponentCheckInResponse() {
        BenefitComponentCheckInResponse response = new BenefitComponentCheckInResponse();
        return response;
    }


    /**
     * For LocateBenefitComponentNotesResponse
     * 
     * @return
     */
    private static WPDResponse getBenefitComponentLocateNotesResponse() {
        LocateBenefitComponentNotesResponse response = new LocateBenefitComponentNotesResponse();
        return response;
    }


    /**
     * For DeleteBenefitComponentNotesResponse
     * 
     * @return
     */
    private static WPDResponse getBenefitComponentDeleteNotesResponse() {
        DeleteBenefitComponentNotesResponse response = new DeleteBenefitComponentNotesResponse();
        return response;
    }


    /**
     * For BenefitComponentRetrieveResponseForEdit
     * 
     * @return
     */
    private static WPDResponse getBenefitComponentRetrieveResponseForEdit() {
        BenefitComponentRetrieveResponseForEdit response = new BenefitComponentRetrieveResponseForEdit();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getRetrieveBenefitResponse() {
        BenefitRetrieveResponse benefitRetrieveResponse = new BenefitRetrieveResponse();
        return benefitRetrieveResponse;
    }


    /**
     * @return
     */
    private static WPDResponse getSaveProductStructureBenefitAdministrationResponse() {
        // Create an instance of the response
        SaveBenefitAdministrationResponse administrationResponse = new SaveBenefitAdministrationResponse();

        // Return the response
        return administrationResponse;
    }


    /**
     * @return
     */
    private static WPDResponse getRetrieveProductStructureBenefitAdministrationResponse() {
        // Create an instance of the response
        RetrieveProductStructureBenefitAdministrationResponse administrationResponse = new RetrieveProductStructureBenefitAdministrationResponse();

        // Return the response
        return administrationResponse;
    }


    public static WPDResponse getDeleteProductResponse() {
        DeleteProductResponse deleteProductResponse = new DeleteProductResponse();
        return deleteProductResponse;
    }


    public static WPDResponse getDeleteProductBenefitResponse() {
        ProductBenefitComponentDeleteResponse productBenefitComponentDeleteResponse = new ProductBenefitComponentDeleteResponse();
        return productBenefitComponentDeleteResponse;
    }


    public static WPDResponse getRetrieveProductBenefitComponentResponse() {
        ProductBenefitComponentResponse productBenefitComponentResponse = new ProductBenefitComponentResponse();
        return productBenefitComponentResponse;
    }


    // for possible answers response
    private static WPDResponse getPossibleAnswersResponse() {
        PossibleAnswersResponse possibleAnswersResponse = new PossibleAnswersResponse();
        return possibleAnswersResponse;
    }


    // for hide Question Response
    private static WPDResponse getHideQuestionResponse() {
        HideQuestionResponse hideQuestionResponse = new HideQuestionResponse();
        return hideQuestionResponse;
    }


    // for selectedQuestionListResponse
    private static WPDResponse getSelectedQuestionLsitResponse() {
        LocateSelectedQuestionListResponse response = new LocateSelectedQuestionListResponse();
        return response;
    }


    /** ******* Product responses ****************** */
    //	public static final int SAVE_PRODUCT_RESPONSE = 101;
    //	public static final int RETRIEVE_PRODUCT_RESPONSE = 102;
    //	public static final int RETRIEVE_VALID_PRD_STR_RESPONSE = 103;
    //	public static final int SAVE_PROD_COMPONENT_RESPONSE = 104;
    //	public static final int SAVE_PROD_SEARCH_RESPONSE = 105;
    private static WPDResponse getSaveProductResponse() {
        SaveProductResponse response = new SaveProductResponse();
        return response;
    }


    private static WPDResponse getRetrieveProductResponse() {
        RetrieveProductResponse response = new RetrieveProductResponse();
        return response;
    }


    private static WPDResponse getRetrieveValidProductStructuresResponse() {
        RetrieveValidProductStructuresResponse response = new RetrieveValidProductStructuresResponse();
        return response;
    }


    private static WPDResponse getSaveProductComponentResponse() {
        SaveProductComponentResponse response = new SaveProductComponentResponse();
        return response;
    }


    private static WPDResponse getSearchProductResponse() {
        SearchProductResponse response = new SearchProductResponse();
        return response;
    }


    /** ******* End of Product responses *********** */

    private static WPDResponse getDeleteQuestionResponse() {
        DeleteQuestionResponse response = new DeleteQuestionResponse();
        return response;
    }


    private static WPDResponse getSearchQuestionResponse() {
        SearchQuestionResponse response = new SearchQuestionResponse();
        return response;
    }


    private static WPDResponse getRetrieveQuestionResponse() {
        RetrieveQuestionResponse response = new RetrieveQuestionResponse();
        return response;
    }


    private static WPDResponse getSaveQuestionResponse() {
        SaveQuestionResponse response = new SaveQuestionResponse();
        return response;
    }


    /**
     * @return
     */
    private static WPDResponse getSaveProductStructureResponse() {
        SaveProductStructureResponse saveProductStructureResponse = new SaveProductStructureResponse();
        return saveProductStructureResponse;
    }


    /**
     * This method is to create the RetrieveNonAdjMandateResponse object
     * 
     * @return
     */
    private static RetrieveNonAdjMandateResponse retrieveNonAdjMandateDetails() {
        // create the response object
        RetrieveNonAdjMandateResponse nonAdjMandateResponse = new RetrieveNonAdjMandateResponse();
        // return the response object
        return nonAdjMandateResponse;
    }


    /**
     * This method is to create the locateMandateListResponse object
     * 
     * @return locateMandateListResponse LocateMandateListResponse
     */
    private static LocateMandateListResponse getMandateListResponse() {
        // create the respone object
        LocateMandateListResponse locateMandateListResponse = new LocateMandateListResponse();
        // return the response object
        return locateMandateListResponse;
    }


    private static LocateBenefitLevelListResponse getBenefitLevelListResponse() {
        LocateBenefitLevelListResponse locateBenefitLevelListResponse = new LocateBenefitLevelListResponse();
        return locateBenefitLevelListResponse;
    }


    private static WPDResponse getRetrieveBenefitAdministrationResponse() {
        RetrieveBenefitAdministrationResponse retrieveBenefitAdministrationResponse = new RetrieveBenefitAdministrationResponse();
        return retrieveBenefitAdministrationResponse;
    }


    public static StandardBenefitResponse getStandardBenefitResponse() {
        StandardBenefitResponse standardBenefitResponse = new StandardBenefitResponse();
        return standardBenefitResponse;
    }


    public static StandardBenefitCopyResponse getStandardBenefitCopyResponse() {
        StandardBenefitCopyResponse standardBenefitCopyResponse = new StandardBenefitCopyResponse();
        return standardBenefitCopyResponse;
    }


    public static BenefitDefinitionResponse getBenefitDefinitionResponse() {
        BenefitDefinitionResponse benefitDefinitionResponse = new BenefitDefinitionResponse();
        return benefitDefinitionResponse;
    }


    public static RetrieveBenefitDefinitionResponse getRetrieveBenefitDefinitionResponse() {
        RetrieveBenefitDefinitionResponse retrieveBenefitDefinitionResponse = new RetrieveBenefitDefinitionResponse();
        return retrieveBenefitDefinitionResponse;
    }


    public static LocateBenefitDefinitionResponse getLocateBenefitDefinitionResponse() {
        LocateBenefitDefinitionResponse locateBenefitDefinitionResponse = new LocateBenefitDefinitionResponse();
        return locateBenefitDefinitionResponse;
    }


    public static LocateBenefitAdministrationResponse getLocateBenefitAdministrationResponse() {
        LocateBenefitAdministrationResponse locateBenefitAdministrationResponse = new LocateBenefitAdministrationResponse();
        return locateBenefitAdministrationResponse;
    }


    public static DeleteBenefitDefinitionResponse getDeleteBenefitDefinitionResponse() {
        DeleteBenefitDefinitionResponse deleteBenefitDefinitionResponse = new DeleteBenefitDefinitionResponse();
        return deleteBenefitDefinitionResponse;
    }


    public static NonAdjBenefitMandateResponse getBenefitMandateResponse() {
        NonAdjBenefitMandateResponse nonAdjBenefitMandateResponse = new NonAdjBenefitMandateResponse();
        return nonAdjBenefitMandateResponse;
    }


    public static StandardBenefitSearchResponse getStandardBenefitSearchResponse() {
        StandardBenefitSearchResponse standardBenefitSearchResponse = new StandardBenefitSearchResponse();
        return standardBenefitSearchResponse;
    }


    public static LocateBenefitMandateResponse getLocateBenefitMandateResponse() {
        LocateBenefitMandateResponse locateBenefitMandateResponse = new LocateBenefitMandateResponse();
        return locateBenefitMandateResponse;
    }


    public static DeleteBenefitMandateResponse getDeleteBenefitMandateResponse() {
        DeleteBenefitMandateResponse deleteBenefitMandateResponse = new DeleteBenefitMandateResponse();
        return deleteBenefitMandateResponse;
    }


    public static CreateBenefitAdministrationResponse getBenefitAdministrationResponse() {
        CreateBenefitAdministrationResponse createBenefitAdministrationResponse = new CreateBenefitAdministrationResponse();
        return createBenefitAdministrationResponse;
    }


    public static RetrieveProductBenefitResponse getRetrieveProductBenefitResponse() {
        RetrieveProductBenefitResponse retrieveProductBenefitResponse = new RetrieveProductBenefitResponse();
        return retrieveProductBenefitResponse;
    }


    /**
     * @return
     */
    private static UpdateBenefitAdministrationResponse getUpdatedBenefitAdministrationResponse() {
        UpdateBenefitAdministrationResponse updateBenefitAdministrationResponse = new UpdateBenefitAdministrationResponse();
        return updateBenefitAdministrationResponse;
    }


    /**
     * @return
     */
    private static DeleteBenefitAdministrationResponse getDeletedBenefitAdministrationResponse() {
        DeleteBenefitAdministrationResponse deleteBenefitAdministrationResponse = new DeleteBenefitAdministrationResponse();
        return deleteBenefitAdministrationResponse;
    }


    public static SearchProductResponse getLocateSearchProductResponse() {

        SearchProductResponse searchProductResponse = new SearchProductResponse();
        return searchProductResponse;

    }


    public static SearchProductStructureResponse getLocateProductStructureResponse() {
        SearchProductStructureResponse searchProductStructureResponse = new SearchProductStructureResponse();
        return searchProductStructureResponse;
    }


    public static WPDResponse getRetrieveProductStructureResponse() {

        RetrieveProductStructureResponse retrieveProductStructureResponse = new RetrieveProductStructureResponse();
        return retrieveProductStructureResponse;
    }


    public static WPDResponse getDeleteProductStructureResponse() {
        DeleteProductStructureResponse deleteProductStructureResponse = new DeleteProductStructureResponse();
        return deleteProductStructureResponse;
    }


    public static WPDResponse getBenefitComponentResponse() {

        RetrieveBenefitComponentResponse retrieveBenefitComponentResponse = new RetrieveBenefitComponentResponse();
        return retrieveBenefitComponentResponse;
    }


    private static LookupAdminOptionResponse getLookupAdminOptionResponse() {
        LookupAdminOptionResponse lookupAdminOptionResponse = new LookupAdminOptionResponse();
        return lookupAdminOptionResponse;
    }


    /**
     * 
     * @return
     */
    public static AdministrationOptionResponse getCreateBenefitAdministrationResponse() {
        AdministrationOptionResponse administrationOptionResponse = new AdministrationOptionResponse();
        return administrationOptionResponse;
    }
    
    private static WPDResponse getAdminMethodMappingCreateResponse() {
		AdminMethodCreateResponse adminMethodCreateResponse= new AdminMethodCreateResponse();
		return adminMethodCreateResponse;
	}
	
    
    private static WPDResponse getAdminMethodMappingCreateResponse1() {
		AdminMethodMappingCreateResponse adminMethodMappingCreateResponse= new AdminMethodMappingCreateResponse();
		return adminMethodMappingCreateResponse;
	}
	
    private static WPDResponse getAdminMethodMappingEditResponse() {
		AdminMethodMappingEditResponse adminMethodMappingEditResponse= new AdminMethodMappingEditResponse();
		return adminMethodMappingEditResponse;
	}

    private static WPDResponse getAdminMethodMappingDeleteResponse() {
    	AdminMethodMappingDeleteResponse adminMethodMappingDeleteResponse= new AdminMethodMappingDeleteResponse();
		return adminMethodMappingDeleteResponse;
	}
    
    
    /**
     * 
     * @return
     */
    public static LocateAdministrationOptionResponse getLocateAdministrationOptionResponse() {
        LocateAdministrationOptionResponse response = new LocateAdministrationOptionResponse();
        return response;
    }


    /**
     * 
     * @return
     */
    public static RetrieveAdministrationOptionResponse getRetrieveAdministrationOptionResponse() {
        RetrieveAdministrationOptionResponse response = new RetrieveAdministrationOptionResponse();
        return response;
    }


    /**
     * 
     * @return
     */
    public static com.wellpoint.wpd.common.standardbenefit.response.SaveQuestionResponse getUpdateQuestionResponse() {
        com.wellpoint.wpd.common.standardbenefit.response.SaveQuestionResponse saveQuestionResponse = new com.wellpoint.wpd.common.standardbenefit.response.SaveQuestionResponse();
        return saveQuestionResponse;
    }


    /**
     * 
     * @return
     */
    public static DeleteAdministrationOptionResponse getDeleteAdministrationOptionResponse() {
        DeleteAdministrationOptionResponse response = new DeleteAdministrationOptionResponse();
        return response;
    }


    /**
     * 
     * @return
     */
    public static WPDResponse getBenefitComponentFromTreeResponse() {
        RetrieveComponentFromTreeResponse retrieveComponentFromTreeResponse = new RetrieveComponentFromTreeResponse();
        return retrieveComponentFromTreeResponse;
    }


    /**
     * 
     * @return
     */
    public static RetrieveBenefitDefenitionResponse getRetrieveBenefitDefenitionResponse() {
        RetrieveBenefitDefenitionResponse retrieveBenefitDefenitionResponse = new RetrieveBenefitDefenitionResponse();
        return retrieveBenefitDefenitionResponse;
    }


    public static BenefitComponentRetrieveResponse getRetrieveBenefitComponentViewResponse() {
        BenefitComponentRetrieveResponse benefitComponentRetrieveResponse = new BenefitComponentRetrieveResponse();
        return benefitComponentRetrieveResponse;
    }


    /**
     * @return
     */
    private static WPDResponse getDeleteStandardBenefitResponse() {
        StandardBenefitDeleteResponse standardBenefitDeleteResponse = new StandardBenefitDeleteResponse();
        return standardBenefitDeleteResponse;
    }


    private static WPDResponse getBenefitComponentDeleteResponse() {
        BenefitComponentDeleteResponse benefitComponentDeleteResponse = new BenefitComponentDeleteResponse();
        return benefitComponentDeleteResponse;
    }


    /**
     * 
     * @return questionViewResponse
     */
    public static QuestionViewResponse getQuestionViewResponse() {
        QuestionViewResponse questionViewResponse = new QuestionViewResponse();
        return questionViewResponse;
    }


    private static LocateNonAdjBenefitMandateResponse getNonAdjBenefitMandateListResponse() {
        LocateNonAdjBenefitMandateResponse locateNonAdjBenefitMandateResponse = new LocateNonAdjBenefitMandateResponse();
        return locateNonAdjBenefitMandateResponse;
    }


    public static RetrieveBenefitMandatesResponse getRetrieveBenefitMandatesResponse() {
        RetrieveBenefitMandatesResponse retrieveBenefitMandatesResponse = new RetrieveBenefitMandatesResponse();
        return retrieveBenefitMandatesResponse;
    }


    public static BenefitComponentSaveResponse getSaveBenefitComponentResponse() {
        BenefitComponentSaveResponse benefitComponentSaveResponse = new BenefitComponentSaveResponse();
        return benefitComponentSaveResponse;
    }


    /**
     * Creating the BenefitComponentCheckOutResponse object
     * 
     * @return benefitComponentCheckOutResponse
     */
    public static BenefitComponentCheckOutResponse getCheckOutBenefitComponentResponse() {
        BenefitComponentCheckOutResponse benefitComponentCheckOutResponse = new BenefitComponentCheckOutResponse();
        return benefitComponentCheckOutResponse;
    }


    /**
     * Creating the BenefitComponentTestPassResponse object
     * 
     * @return benefitComponentTestPassResponse
     */
    public static BenefitComponentTestPassResponse getTestPassBenefitComponentResponse() {
        BenefitComponentTestPassResponse benefitComponentTestPassResponse = new BenefitComponentTestPassResponse();
        return benefitComponentTestPassResponse;
    }


    /**
     * Creating the BenefitComponentTestFailResponse object
     * 
     * @return benefitComponentTestFailResponse
     */
    public static BenefitComponentTestFailResponse getTestFailBenefitComponentResponse() {
        BenefitComponentTestFailResponse benefitComponentTestFailResponse = new BenefitComponentTestFailResponse();
        return benefitComponentTestFailResponse;
    }


    /**
     * Creating the BenefitComponentPublishResponse object
     * 
     * @return benefitComponentPublishResponse
     */
    public static BenefitComponentPublishResponse getPublishBenefitComponentResponse() {
        BenefitComponentPublishResponse benefitComponentPublishResponse = new BenefitComponentPublishResponse();
        return benefitComponentPublishResponse;
    }


    /**
     * Creating the BenefitComponentScheduleForTestResponse object
     * 
     * @return benefitComponentScheduleForTestResponse
     */
    public static BenefitComponentScheduleForTestResponse getScheduleForTestBenefitComponentResponse() {
        BenefitComponentScheduleForTestResponse benefitComponentScheduleForTestResponse = new BenefitComponentScheduleForTestResponse();
        return benefitComponentScheduleForTestResponse;
    }


    public static WPDResponse getRetrieveProductBenefitDefinitionResponse() {
        RetrieveProductBenefitDefinitionResponse productBenefitDefinitionResponse = new RetrieveProductBenefitDefinitionResponse();
        return productBenefitDefinitionResponse;
    }


    /**
     * 
     * @return BenefitComponentViewResponse
     */
    public static BenefitComponentViewResponse getRetrieveBenefitViewResponse() {
        BenefitComponentViewResponse retrieveViewBenefitResponse = new BenefitComponentViewResponse();
        return retrieveViewBenefitResponse;
    }


    public static StandardBenefitCheckInResponse getStandardBenefitCheckinResponse() {
        StandardBenefitCheckInResponse standardBenefitCheckInResponse = new StandardBenefitCheckInResponse();
        return standardBenefitCheckInResponse;
    }


    public static BenefitComponentAttachNotesResponse getBenefitComponentAttachNotesResponse() {
        BenefitComponentAttachNotesResponse benefitComponentAttachNotesResponse = new BenefitComponentAttachNotesResponse();
        return benefitComponentAttachNotesResponse;
    }


    /**
     * For LocateComponentsBenefitDefinitionResponse
     * 
     * @return
     */
    //	    private static WPDResponse getBenefitComponentNotesAttachResponse(){
    //	    	BenefitComponentAttachNotesResponse response =
    //	    					new BenefitComponentAttachNotesResponse();
    //	    	return response;
    //	    }
    public static WPDResponse getRetrieveProductBenefitAdminResponse() {
        RetrieveProductBenefitAdminResponse retrieveProductBenefitAdminResponse = new RetrieveProductBenefitAdminResponse();
        return retrieveProductBenefitAdminResponse;
    }


    public static WPDResponse getSaveProductBenefitDefinitionResponse() {
        SaveProductBenefitDefinitionResponse saveProductBenefitDefinitionResponse = new SaveProductBenefitDefinitionResponse();
        return saveProductBenefitDefinitionResponse;
    }


    public static WPDResponse getSaveContractBenefitDefinitionResponse() {
        SaveContractBenefitDefinitionResponse saveContractBenefitDefinitionResponse = new SaveContractBenefitDefinitionResponse();
        return saveContractBenefitDefinitionResponse;
    }


    /**
     * Creating new CreateAdminOptionResponse object.
     * 
     * @return createAdminOptionResponse
     */
    public static WPDResponse getCreateAdminOptionResponse() {
        CreateAdminOptionResponse createAdminOptionResponse = new CreateAdminOptionResponse();
        return createAdminOptionResponse;
    }


    /**
     * Creating new RetrieveAdminOptionResponse object
     * 
     * @return retrieveAdminOptionResponse
     */
    public static WPDResponse getRetrieveAdminOptionResponse() {
        RetrieveAdminOptionResponse retrieveAdminOptionResponse = new RetrieveAdminOptionResponse();
        return retrieveAdminOptionResponse;
    }


    /**
     * Creating new DeleteAdminOptionResponse object
     * 
     * @return deleteAdminOptionResponse
     */
    public static WPDResponse getDeleteAdminOptionResponse() {
        DeleteAdminOptionResponse deleteAdminOptionResponse = new DeleteAdminOptionResponse();
        return deleteAdminOptionResponse;
    }


    /**
     * Creating new DeleteAllAdminOptionResponse object
     * 
     * @return deleteAllAdminOptionResponse
     */
    public static WPDResponse getDeleteAllAdminOptionResponse() {
        DeleteAllAdminOptionResponse deleteAllAdminOptionResponse = new DeleteAllAdminOptionResponse();
        return deleteAllAdminOptionResponse;
    }
    
    
    /**
     * Creating new SearchAdminOptionResponse object
     * 
     * @return searchAdminOptionResponse
     */
    public static WPDResponse getSearchAdminOptionResponse() {
        SearchAdminOptionResponse searchAdminOptionResponse = new SearchAdminOptionResponse();
        return searchAdminOptionResponse;
    }


    /**
     * Creating new CheckInAdminOptionResponse object
     * 
     * @return checkInAdminOptionResponse
     */
    public static WPDResponse getCheckInAdminOptionResponse() {
        CheckInAdminOptionResponse checkInAdminOptionResponse = new CheckInAdminOptionResponse();
        return checkInAdminOptionResponse;
    }


    /**
     * Creating new CheckOutAdminOptionResponse object
     * 
     * @return checkOutAdminOptionResponse
     */
    public static WPDResponse getCheckOutAdminOptionResponse() {
        CheckOutAdminOptionResponse checkOutAdminOptionResponse = new CheckOutAdminOptionResponse();
        return checkOutAdminOptionResponse;
    }


    /**
     * Creating new SaveAdminOptionQuestionResponse object
     * 
     * @return saveAdminOptionQuestionResponse
     */
    public static WPDResponse getSaveAdminOptionQuestionResponse() {
        SaveAdminOptionQuestionResponse saveAdminOptionQuestionResponse = new SaveAdminOptionQuestionResponse();
        return saveAdminOptionQuestionResponse;
    }


    /**
     * Creating new RetrieveAdminOptionQuestionResponse object
     * 
     * @return retrieveAdminOptionQuestionResponse
     */
    public static WPDResponse getRetreiveAdminOptionQuestionResponse() {
        RetrieveAdminOptionQuestionResponse retrieveAdminOptionQuestionResponse = new RetrieveAdminOptionQuestionResponse();
        return retrieveAdminOptionQuestionResponse;
    }


    /**
     * Creating new DeleteAdminOptionQuestionResponse object
     * 
     * @return deleteAdminOptionQuestionResponse
     */
    public static WPDResponse getDeleteAdminOptionQuestionResponse() {
        DeleteAdminOptionQuestionResponse deleteAdminOptionQuestionResponse = new DeleteAdminOptionQuestionResponse();
        return deleteAdminOptionQuestionResponse;
    }


    public static WPDResponse getProductStructureVersionsResponse() {
        RetrieveProductStructureVersionsResponse retrieveProductStructureVersionsResponse = new RetrieveProductStructureVersionsResponse();
        return retrieveProductStructureVersionsResponse;
    }


    public static WPDResponse getSaveProductBenefitAdminResponse() {
        SaveProductBenefitAdminResponse saveProductBenefitAdminResponse = new SaveProductBenefitAdminResponse();
        return saveProductBenefitAdminResponse;
    }


    /**
     * @return
     */
    private static WPDResponse getPublishStdBenefitResponse() {
        PublishStandardBenefitResponse response = new PublishStandardBenefitResponse();
        return response;
    }
    
    private static WPDResponse getStandardBenefitUnLockResponse() {
    	StandardBenefitUnLockResponse response = new StandardBenefitUnLockResponse();
        return response;
    }


    public static WPDResponse getScheduleForTestStdBenResponse() {
        ScheduleForTestSBResponse scheduleForTestSBResponse = new ScheduleForTestSBResponse();
        return scheduleForTestSBResponse;
    }


    public static WPDResponse getBenefitVersionsLifeCycleResponse() {
        StandardBenefitVersionsLifeCycleResponse standardBenefitVersionsLifeCycleResponse = new StandardBenefitVersionsLifeCycleResponse();
        return standardBenefitVersionsLifeCycleResponse;
    }


    public static WPDResponse getTestPassStdBenResponse() {
        TestPassStandardBenefitResponse testPassStandardBenefitResponse = new TestPassStandardBenefitResponse();
        return testPassStandardBenefitResponse;
    }


    public static WPDResponse getTestFailStdBenResponse() {
        TestFailStandardBenefitResponse testFailStandardBenefitResponse = new TestFailStandardBenefitResponse();
        return testFailStandardBenefitResponse;
    }


    public static WPDResponse getAdminLevelResponse() {
        AdminLevelResponse response = new AdminLevelResponse();
        return response;
    }


    public static WPDResponse getScheduleForTestProductStructureResponse() {
        ProductStructureLifeCycleResponse scheduleForTestProductStructureResponse = new ProductStructureLifeCycleResponse();
        return scheduleForTestProductStructureResponse;
    }


    public static WPDResponse getTestFailQuestionResponse() {
        TestFailQuestionResponse testFailQuestionResponse = new TestFailQuestionResponse();
        return testFailQuestionResponse;
    }


    public static WPDResponse getSaveItemAssociationResponse() {
        SaveItemAssociationResponse saveItemAssociationResponse = new SaveItemAssociationResponse();
        return saveItemAssociationResponse;
    }

    
	
    public static WPDResponse getQuesAnswerlookupResponse() {
    	QuestionAnswerLookupResponse qestionAnswerLookupResponse = new QuestionAnswerLookupResponse();
        return qestionAnswerLookupResponse;
    }


    public static WPDResponse getSaveReferenceDataLookupResponse() {
        ReferenceDataLookupResponse referenceDataLookupResponse = new ReferenceDataLookupResponse();
        return referenceDataLookupResponse;
    }

       
    public static WPDResponse getItemDeleteAssociationRequest() {
        ItemAssociationDeleteResponse response = new ItemAssociationDeleteResponse();
        return response;
    }


    public static WPDResponse getContractSpecificInfoResponse() {
        SaveContractSpecificInfoResponse saveContractSpecificInfoResponse = new SaveContractSpecificInfoResponse();
        return saveContractSpecificInfoResponse;
    }
    
    public static WPDResponse getContractAdaptedInfoResponse() {
        SaveContractAdaptedInfoResponse saveContractAdaptedInfoResponse = new SaveContractAdaptedInfoResponse();
        return saveContractAdaptedInfoResponse;
    }


    public static WPDResponse getRetrieveContractBenefitDefinitionResponse() {
        RetrieveContractBenefitDefinitionResponse contractBenefitDefinitionResponse = new RetrieveContractBenefitDefinitionResponse();
        return contractBenefitDefinitionResponse;
    }


    public static WPDResponse getRetrieveContractSpecificInfoResponse() {
        RetrieveContractSpecificInfoResponse retrieveContractSpecificInfoResponse = new RetrieveContractSpecificInfoResponse();
        return retrieveContractSpecificInfoResponse;
    }


    public static WPDResponse getContractSearchResponse() {
        ContractSearchResponse contractSearchResponse = new ContractSearchResponse();
        return contractSearchResponse;
    }


    public static WPDResponse getContractSBResponse() {
        RetrieveContractStandardBenefitResponse retrieveContractStandardBenefitResponse = new RetrieveContractStandardBenefitResponse();
        return retrieveContractStandardBenefitResponse;
    }


    public static WPDResponse getContractBenefitMandateResponse() {
        RetrieveContractBenefitMandateResponse retrieveContractBenefitMandateResponse = new RetrieveContractBenefitMandateResponse();
        return retrieveContractBenefitMandateResponse;
    }


    public static WPDResponse getNotesCheckoutResponse() {
        NotesCheckOutResponse notesCheckOutResponse = new NotesCheckOutResponse();
        return notesCheckOutResponse;
    }


    public static WPDResponse getNotesCopyResponse() {
        NotesCopyResponse notesCopyResponse = new NotesCopyResponse();
        return notesCopyResponse;
    }


    public static WPDResponse getNotesAllVersionsResponse() {
        ViewAllVersionsNotesResponse viewAllVersionsNotesResponse = new ViewAllVersionsNotesResponse();
        return viewAllVersionsNotesResponse;
    }


    public static WPDResponse getRetrieveDateSegmentResponse() {
        RetrieveDateSegmentsResponse response = new RetrieveDateSegmentsResponse();
        return response;
    }


    public static WPDResponse getCreateDateSegmentResponse() {
        CreateDateSegmentResponse response = new CreateDateSegmentResponse();
        return response;
    }


    public static WPDResponse getRetrieveBasicInfoRequest() {
        RetrieveBasicInfoResponse retrieveBasicInfoResponse = new RetrieveBasicInfoResponse();
        return retrieveBasicInfoResponse;
    }


    public static WPDResponse getLocateProductResponse() {
        LocateProductResponse locateProductResponse = new LocateProductResponse();
        return locateProductResponse;

    }


    /**
     * Creating new StandardBenefitNoteAttachmentResponse object
     * 
     * @return standardBenefitNoteAttachmentResponse
     */
    public static WPDResponse getStandardBenefitAttachNotesResponse() {
        StandardBenefitNoteAttachmentResponse standardBenefitNoteAttachmentResponse = new StandardBenefitNoteAttachmentResponse();
        return standardBenefitNoteAttachmentResponse;
    }


    public static WPDResponse getStandardBenefitAttachNotesLocateResponse() {
        LocateStandardBenefitNotesResponse locateStandardBenefitNotesResponse = new LocateStandardBenefitNotesResponse();
        return locateStandardBenefitNotesResponse;
    }


    public static WPDResponse getDeleteStandardBenefitNotesResponse() {
        DeleteStandardBenefitNotesResponse deleteStandardBenefitNotesResponse = new DeleteStandardBenefitNotesResponse();
        return deleteStandardBenefitNotesResponse;
    }


    /**
     * for deleting the product admin
     * 
     * @return
     */
    public static SaveProductAdminResponse getSaveProductAdminResponse() {
        SaveProductAdminResponse saveProductAdminResponse = new SaveProductAdminResponse();
        return saveProductAdminResponse;
    }


    /**
     * for loading the product admin
     * 
     * @return
     */
    public static ProductAdminResponse getProductAdminResponse() {
        ProductAdminResponse ProductAdminResponse = new ProductAdminResponse();
        return ProductAdminResponse;
    }


    /**
     * for saving the product admin
     * 
     * @return
     */
    public static DeleteProductAdminResponse getDeleteProductAdminResponse() {
        DeleteProductAdminResponse deleteProductAdminResponse = new DeleteProductAdminResponse();
        return deleteProductAdminResponse;
    }


    /**
     * @return
     */
    private static WPDResponse getBenefitHeadingsResponse() {
        // Create an instance of SubCatalog Search Response
        RetrieveBenefitHeadingsResponse response = new RetrieveBenefitHeadingsResponse();

        return response;
    }


    public static ItemLookUpResponse getItemLookUpResponse() {
        ItemLookUpResponse itemLookUpResponse = new ItemLookUpResponse();
        return itemLookUpResponse;
    }


    public static RetrieveReservedContractResponse getReservedContractResponse() {
        RetrieveReservedContractResponse retrieveReserved = new RetrieveReservedContractResponse();
        return retrieveReserved;
    }
    
    public static MembershipInfoResponse getMembershipResponse(){
    	MembershipInfoResponse membershipInfoResponse = new MembershipInfoResponse();
    	return membershipInfoResponse;
    }


    public static SaveReservedContractResponse saveReservedContractResponse() {
        SaveReservedContractResponse saveReserved = new SaveReservedContractResponse();
        return saveReserved;
    }


    public static SaveRuleInformationResponse getSaveRuleInfoContractBenefitResponse() {
        SaveRuleInformationResponse saveRuleInformationResponse = new SaveRuleInformationResponse();
        return saveRuleInformationResponse;
    }


    private static ApproveAdminOptionResponse getApproveAdminOptionResponse() {
        ApproveAdminOptionResponse adminOptionResponse = new ApproveAdminOptionResponse();
        return adminOptionResponse;
    }
    
    private static AdminOptionUnlockResponse getUnlockAdminOptionResponse() {
    	AdminOptionUnlockResponse adminOptionResponse = new AdminOptionUnlockResponse();
        return adminOptionResponse;
    }


    private static RejectAdminOptionResponse getRejectAdminOptionResponse() {
        RejectAdminOptionResponse rejectOptionResponse = new RejectAdminOptionResponse();
        return rejectOptionResponse;
    }


    private static ApproveStandardBenefitResponse getApproveStdBenefitResponse() {
        ApproveStandardBenefitResponse approveStandardBenefitResponse = new ApproveStandardBenefitResponse();
        return approveStandardBenefitResponse;
    }


    /**
     * @return DatafeedResponse
     */
    private static WPDResponse getContractForDataFeed() {
        DatafeedResponse datafeedResponse = new DatafeedResponse();
        return datafeedResponse;
    }


    public static RetrieveRulesResponse getRulesResponse() {
        RetrieveRulesResponse retrieveRulesResponse = new RetrieveRulesResponse();
        return retrieveRulesResponse;
    }
    public static ContractTransferLogResponse getContractTransferLogResponse() {
        ContractTransferLogResponse contractTransferLogResponse = new ContractTransferLogResponse();
        return contractTransferLogResponse;
    }

    private static RejectStandardBenefitResponse getRejectStdBenefitResponse() {
        RejectStandardBenefitResponse response = new RejectStandardBenefitResponse();
        return response;
    }


    private static BenefitComponentApproveResponse getApproveBCResponse() {
        BenefitComponentApproveResponse response = new BenefitComponentApproveResponse();
        return response;
    }


    private static BenefitComponentRejectResponse getRejectBCResponse() {
        BenefitComponentRejectResponse response = new BenefitComponentRejectResponse();
        return response;
    }


    private static WPDResponse getBenefitLinesResponse() {
        // Create an instance of Benefit Line Response
        RetrieveBenefitLinesResponse response = new RetrieveBenefitLinesResponse();

        return response;
    }


    private static WPDResponse getSaveContractHeadingsResponse() {
        SaveContractHeadingsResponse response = new SaveContractHeadingsResponse();
        return response;

    }


	 private static RetrieveHeadingsResponse getlegacyHeadingResponse(){
	 	RetrieveHeadingsResponse response = new RetrieveHeadingsResponse();
	  	return response;
	 }
	 
	 private static RuleResponse getRuleResponse(){
	 	RuleResponse response = new RuleResponse();
	 	return response; 
	 }
	 private static RetrieveAdminMethodsResponse getAdminMethodPopupResponse(){
		 RetrieveAdminMethodsResponse response = new RetrieveAdminMethodsResponse();
		  	return response;
		 }
	 
	 private static GeneralBenefitAdminMethodValidationResponse getAdminMethodResponse(){
		 GeneralBenefitAdminMethodValidationResponse response = new GeneralBenefitAdminMethodValidationResponse();
	  		return response;
	 }
	 private static SaveAdminMethodResponse getSaveAdminMethodResponse(){
	 	SaveAdminMethodResponse response = new SaveAdminMethodResponse();
	  		return response;
	 }
	 private static OverrideAdminMethodResponse getOverrideAdminMethodResponse(){
	 	OverrideAdminMethodResponse response = new OverrideAdminMethodResponse();
	  		return response;
	 }
	 	 private static AdminMethodOverrideResponse getAdminMethodOverrideResponse(){
	 	AdminMethodOverrideResponse response = new AdminMethodOverrideResponse();
	  		return response;
	 }
	 	 
	   private static WPDResponse getCodedNonCodedBenefitHeadingsResponse() {
	         
	      RetrieveCodedNonCodedBenefitHeadingsResponse response = new RetrieveCodedNonCodedBenefitHeadingsResponse();
	      return response;
	     
	   }
	   private static WPDResponse  getHideAdminOptionResponse(){
	    HideAdminOptionResponse response = new  HideAdminOptionResponse();
	  	return response;
	 }
	   
	  private static  WPDResponse getLegacyContractNotesResponse(){
	  	
	  	RetrieveLegacyContractNotesResponse response = new RetrieveLegacyContractNotesResponse();
	  	return response;
	  }
	  
	  
	  private static  WPDResponse getServiceTypeSearchResponse(){
	  	
	  	ServiceTypeMappingSearchResponse response = new ServiceTypeMappingSearchResponse();
	  	return response;
	  }

	   /**
		 * @return
		 */
	private static WPDResponse getBenefitDetailsResponse() {
		RetrieveBenefitDetailsResponse benefitDetailsResponse = new  RetrieveBenefitDetailsResponse();
		return benefitDetailsResponse;
	}
	
	private static WPDResponse getSearchSPSMaintainResponse() {
		SearchSPSMaintainResponse searchSPSMaintainResponse = new  SearchSPSMaintainResponse();
		return searchSPSMaintainResponse;
	}
	private static WPDResponse getDeleteSpsMappingResponse() {
		DeleteSpsMappingResponse deleteSpsMappingResponse = new  DeleteSpsMappingResponse();
		return deleteSpsMappingResponse;
	}
	
	private static WPDResponse getServiceTypeMappingResponse() {
		ServiceTypeMappingDeleteResponse serviceTypeMappingDeleteResponse = new  ServiceTypeMappingDeleteResponse();
		return serviceTypeMappingDeleteResponse;
	}
	private static WPDResponse getViewServiceTypeCodeMappingResponse() {
		ViewServiceTypeCodeMappingResponse codeMappingResponse = new  ViewServiceTypeCodeMappingResponse();
		return codeMappingResponse;
	}
	private static WPDResponse getCustomMessageDeleteResponse(){
		DeleteCustomMessageResponse deleteCustomMessageResponse = new DeleteCustomMessageResponse();
		return deleteCustomMessageResponse;
	}
	
	private static WPDResponse getCustomMessageTextCreateResponse() {
		return new CustomMessageTextCreateResponse();
	}
	
	private static WPDResponse getCustomMessageTextUpdateResponse(){
		return new CustomMessageTextUpdateResponse();
	}
	private static WPDResponse getRetriveQuestionnaireResponse(){
		RetrieveQuestionaireResponse retrieveQuestionaireResponse = new RetrieveQuestionaireResponse();
		return retrieveQuestionaireResponse;
	}
	private static WPDResponse getUpdateQuestionnaireResponse(){
		UpdateQuestionnaireResponse updateQuestionnaireResponse = new UpdateQuestionnaireResponse();
		return updateQuestionnaireResponse;
	}
	private static WPDResponse getAddRootQuestionResponse(){
		AddRootQuestionResponse addRootQuestionResponse = new AddRootQuestionResponse();
		return addRootQuestionResponse;
	}
	private static WPDResponse getLocateRootQuestionResponse(){
		LocateRootQuestionResponse locateRootQuestionResponse = new LocateRootQuestionResponse();
		return locateRootQuestionResponse;
	}
	 public static WPDResponse getRetrieveProdStructureComponentHierarchyResponse(){
    	RetrieveProdStructureComponentHierarchyResponse hierarchyResponse = new RetrieveProdStructureComponentHierarchyResponse();
    	return hierarchyResponse;
    }
	 public static WPDResponse getPopupResponse(){
	 	PopupResponse popupResponse = new PopupResponse();
    	return popupResponse;
    }
	 public static WPDResponse getAdminMethodSPSValidationResponse(){
	 	AdminMethodSPSValidationResponse adminMethodSPSValidationResponse = new AdminMethodSPSValidationResponse();
    	return adminMethodSPSValidationResponse;
    } 
	 public static WPDResponse getProductComponentRuleResponse(){
	 	SaveProductComponentRuleInformationResponse response = new SaveProductComponentRuleInformationResponse();
    	return response;
    }
	 
	 public static WPDResponse getSearchIndicativeMappingResponse(){
	 	SearchIndicativeMappingResponse response = new SearchIndicativeMappingResponse();
    	return response;
    } 
	 
	 public static WPDResponse getCheckoutDateSegmentResponse(){
	 	DateSegmentCheckoutResponse response = new DateSegmentCheckoutResponse();
	 	return response;
	 }
	 
	  public static WPDResponse getDeleteIndicativeMappingResponse(){
	 	DeleteIndicativeMappingResponse response = new DeleteIndicativeMappingResponse();
    	return response;
    } 
	 public static WPDResponse getRetrieveIndicativeMappingResponse(){
	 	RetrieveIndicativeMappingResponse response = new RetrieveIndicativeMappingResponse();
    	return response;
    } 

	 public static WPDResponse getQuestionNotesPopUpResponse(){
	 	QuestionNotesPopUpResponse response = new QuestionNotesPopUpResponse();
    	return response;
    } 
	 public static WPDResponse getBenefitQuestionNoteResponse(){
	 	BenefitQuestionNoteResponse response = new BenefitQuestionNoteResponse();
    	return response;
    } 

		 public static WPDResponse getProductQuestionNotesPopUpResponse(){
	 	ProductQuestionNotesPopUpResponse response = new ProductQuestionNotesPopUpResponse();
    	return response;
    } 
	 public static WPDResponse getProductQuestionNoteResponse(){
	 	ProductQuestionNoteResponse response = new ProductQuestionNoteResponse();
    	return response;
    } 
	 public static WPDResponse getContractQuestionNotesPopUpResponse(){
	 	ContractQuestionNotesPopUpResponse response = new ContractQuestionNotesPopUpResponse();
    	return response;
    } 
	 public static WPDResponse getContractQuestionNoteResponse(){
	 	ContractQuestionNoteResponse response = new ContractQuestionNoteResponse();
    	return response;
    } 
	 
	 public static WPDResponse getQuestionNotesResponse(){
	     QuestionNotesPopUpResponse response = new QuestionNotesPopUpResponse();
	     return response;
	 }   
	 public static WPDResponse getAttachNotesToQuestionResponseForPS(){
	     AttachNotesToQuestionResponseForPS response = new AttachNotesToQuestionResponseForPS();
	     return response;
	 }  
	     
	 public static WPDResponse getContractRuleSetResponse(){
	     ContractRulesetResponse contractRulesetResponse=new ContractRulesetResponse();
	     return contractRulesetResponse;
	 }   
	 public static WPDResponse getContractRuleValidationResponse(){
	     ContractRuleValidationResponse contractRuleValidationResponse=new ContractRuleValidationResponse();
	     return contractRuleValidationResponse;
	 }  
	 public static WPDResponse getTierLookUpResponse(){
	     TierLookUpResponse tierLookUpResponse=new TierLookUpResponse();
	     return tierLookUpResponse;
	 } 
	 public static WPDResponse getBenefitTierDefnAssnResponse(){
	     CreateBenefitTierDefinitionResponse createBenefitTierDefinitionResponse 
	     = new CreateBenefitTierDefinitionResponse();
	     return createBenefitTierDefinitionResponse;
	 } 
	 
	 public static WPDResponse getBenefitTierDefnResponse(){
	     GetBenefitTierDefinitionResponse benefitTierDefinitionResponse 
	     = new GetBenefitTierDefinitionResponse();
	     return benefitTierDefinitionResponse;
	 } 
	 public static WPDResponse getDeleteBenefitTierResponse(){
	     ProductTierDeleteResponse benefitTierDeleteResponse 
	     = new ProductTierDeleteResponse();
	     return benefitTierDeleteResponse;
	 } 
	 public static WPDResponse getProductTierDefnResponse(){
	     RetrieveProductTierDefnResponse retrieveProductTierDefnResponse 
	     = new RetrieveProductTierDefnResponse();
	     return retrieveProductTierDefnResponse;
	 } 
	 
	 public static WPDResponse getProductBnftTierDefnResponse(){
	     RetrieveBenefitTierDefnResponse retrieveBenefitTierDefnResponse 
	     = new RetrieveBenefitTierDefnResponse();
	     return retrieveBenefitTierDefnResponse;
	 } 
	 public static WPDResponse getDeleteContractBenTierResponse(){
	     ContractTierDeleteResponse contractTierDeleteResponse 
	     		= new ContractTierDeleteResponse();
	     return contractTierDeleteResponse;
	 } 
	 public static WPDResponse getUpdateProductBnftGenInfoResponse(){
	     UpdateProductBenefitGeneralInformationResponse updateProductBnftGenInfoResponse 
	     = new UpdateProductBenefitGeneralInformationResponse();
	     return updateProductBnftGenInfoResponse;
	 }
	 public static WPDResponse getProductBenefitTierValidnResponse(){
	     ProductBenefitTierValidationResponse productBenefitTierValidnResponse 
	     = new ProductBenefitTierValidationResponse();
	     return productBenefitTierValidnResponse;
	 }
	 public static WPDResponse getVariableMappingResponse(){
	 	ContractVariableMappingResponse contractVariableMappingResponse 
	     = new ContractVariableMappingResponse();
	     return contractVariableMappingResponse;
	 }
}