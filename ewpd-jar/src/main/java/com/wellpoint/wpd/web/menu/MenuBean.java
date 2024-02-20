/*
 * MenuBean.java 
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary 
 * information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose 
 * or use Confidential Information without the
 * express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.myfaces.renderkit.html.util.AddResource;
import org.apache.myfaces.renderkit.html.util.AddResourceFactory;

import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.security.SecurityConstants;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.search.util.SearchConstants;
import com.wellpoint.wpd.common.util.SessionCleanUp;
import com.wellpoint.wpd.web.adminmethodmaintain.AdminMethodMaintainBackingBean;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.menu.WPDNavigationMenuItem;
import com.wellpoint.wpd.web.search.BasicSearchBackingBean;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * The MenuBean is a BackingBean for the MenuBar in the Application.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MenuBean extends WPDBackingBean {
    
	private Map sessionScope;
	private List validationMessages = null;
    private static final String CREATE_NAVIGATION = "create";

    private static final String CREATE_BENEFIT_COMPONENT_NAVIGATION = "createBenefitComponent";

    private static final String MAINTAIN_BENEFIT_COMPONENT_NAVIGATION = "maintainBenefitComponent";

    private static final String SEARCH_NAVIGATION = "search";

    private static final String PRODUCT_STRUCTURE_CREATE_ACTION = "#{menuBean.actionProductStructureCreate}";

    private static final String PRODUCT_STRUCTURE_MAINTAIN_ACTION = "#{menuBean.actionProductStructureMaintain}";

    private static final String PRODUCT_CREATE_ACTION = "#{menuBean.actionProductCreate}";

    private static final String PRODUCT_MAINTAIN_ACTION = "#{menuBean.actionProductMaintain}";

    private static final String BASIC_SEARCH_ACTION = "#{menuBean.actionBasicSearch}";

    private static final String ADVANCED_SEARCH_ACTION = "#{menuBean.actionAdvancedSearch}";
    
    private static final String MASS_UPDATE_SEARCH_ACTION = "#{menuBean.actionMassUpdateSearch}";

    private static final String VARIABLE_CREATE_NAVIGATION = "variableMenuAction";

    private static final String VARIABLE_SEARCH_NAVIGATION = "variableSearchMenuAction";

    private static final String STRUCTURE_SEARCH_NAVIGATION = "searchStructureMenuAction";

    private static final String STRUCTURE_CREATE_NAVIGATION = "createStructureMenuAction";

    private static final String CREATE_PRODUCT_STRUCTURE_NAVIGATION = "createProductStructure";

    private static final String MAINTAIN_PRODUCT_STRUCTURE_NAVIGATION = "maintainProductStructure";

    private static final String CREATE_PRODUCT_NAVIGATION = "createProduct";

    private static final String MAINTAIN_PRODUCT_NAVIGATION = "maintainProduct";

    private static final String CREATE_MENU_STD_BENEFIT_NAVIGATION = "createStandardBenefit";

    private static final String MAINTAIN_MENU_STD_BENEFIT_NAVIGATION = "searchStandardBenefit";


    private static final String CREATE_INDICATIVE_MAPPING_NAVIGATION = "createIndicativeMapping";

    private static final String MAINTAIN_INDICATIVE_MAPPING_NAVIGATION = "maintainIndicativeMapping";
    
    /* Added For Indicative Long Term Solution */
    private static final String CREATE_INDICATIVE_LAYOUT_NAVIGATION = "createIndicativeLayout";
    
    private static final String CREATE_ADMIN_METHOD_NAVIGATION = "createAdminMethod";

    private static final String MAINTAIN_ADMIN_METHOD_NAVIGATION = "maintainAdminMethod";
    private static final String CREATE_ADMIN_METHOD_MAPPING_NAVIGATION = "createAdminMethodMapping";
    private static final String  CREATE_ACCUM_METHOD_MAPPING_NAVIGATION="createStandardAccumMenuAction";
    private static final String  MAINTAIN_ACCUM_METHOD_MAPPING_NAVIGATION="maintainStandardAccumMenuAction";
    private static final String MAINTAIN_ADMIN_METHOD_MAPPING_NAVIGATION = "maintainAdminMethodMapping";
    
    
    
    private static final String CREATE_SPS_MAPPING_NAVIGATION = "createspsIDMapping";

    private static final String MAINTAIN_SPS_MAPPING_NAVIGATION = "maintainspsIDMapping";


    private static final String CREATE_MENU_QUESTIONS_NAVIGATION = "createQuestions";

    private static final String MAINTAIN_MENU_QUESTIONS_NAVIGATION = "searchQuestions";

    private static final String CREATE_MENU_ADMIN_OPTIONS_NAVIGATION = "createAdminOptions";

    private static final String MAINTAIN_MENU_ADMIN_OPTIONS_NAVIGATION = "searchAdminOptions";

    private static final String CREATE_MENU_MANDATE_NAVIGATION = "mandateCreatePage";

    private static final String MAINTAIN_MENU_MANDATE_NAVIGATION = "mandateSearchPage";

    private static final String CONTRACT_CREATE_NAVIGATION = "createContractMenuAction";

    private static final String CONTRACT_MAINTAIN_NAVIGATION = "maintainContractMenuAction";
    
    private static final String CONTRACT_VALIDATE_NAVIGATION = "validateContractMenuAction";
    
    private static final String CONTRACT_DATA_EXTRACT = "contractDataExtractMenuAction";

    private static final String NOTES_CREATE_ACTION = "#{menuBean.actionNotesCreate}";

    private static final String NOTES_MAINTAIN_ACTION = "#{menuBean.actionNotesMaintain}";

    private static final String CREATE_NOTES_NAVIGATION = "createNotesPage";

    private static final String MAINTAIN_NOTES_NAVIGATION = "maintainNotesPage";

    private static final String CATALOG_CREATE_ACTION = "#{menuBean.actionCatalogCreate}";

    private static final String SUB_CATALOG_CREATE_ACTION = "#{menuBean.actionSubCatalogCreate}";

    private static final String SUB_CATALOG_MAINTAIN_ACTION = "#{menuBean.actionSubCatalogMaintain}";

    private static final String ITEM_CREATE_ACTION = "#{menuBean.actionItemCreate}";

    private static final String ITEM_MAINTAIN_ACTION = "#{menuBean.actionItemMaintain}";

    private static final String CREATE_CATALOG_NAVIGATION = "createCatalogPage";

    private static final String MAINTAIN_CATALOG_NAVIGATION = "maintainCatalogPage";

    private static final String CREATE_SUB_CATALOG_NAVIGATION = "createSubCatalogPage";

    private static final String MAINTAIN_SUB_CATALOG_NAVIGATION = "maintainSubCatalogPage";

    private static final String CREATE_ITEM_NAVIGATION = "createItemPage";

    private static final String MAINTAIN_ITEM_NAVIGATION = "maintainItemPage";

    private static final String ROLE_CREATE_ACTION = "#{menuBean.actionRoleCreate}";

    private static final String ROLE_MAINTAIN_ACTION = "#{menuBean.actionRoleMaintain}";

    private static final String MODULE_CREATE_ACTION = "#{menuBean.actionModuleCreate}";

    private static final String MODULE_MAINTAIN_ACTION = "#{menuBean.actionModuleMaintain}";

    private static final String TASK_CREATE_ACTION = "#{menuBean.actionTaskCreate}";

    private static final String TASK_MAINTAIN_ACTION = "#{menuBean.actionTaskMaintain}";

    private static final String CREATE_ROLE_NAVIGATION = "roleCreatePage";

    private static final String MAINTAIN_ROLE_NAVIGATION = "roleMaintainPage";

    private static final String MODULE_CREATE_NAVIGATION = "moduleCreatePage";

    private static final String MODULE_MAINTAIN_NAVIGATION = "moduleMaintainPage";

    private static final String TASK_CREATE_NAVIGATION = "taskCreatePage";

    private static final String TASK_MAINTAIN_NAVIGATION = "taskMaintainPage";

    private static final String RESERVE_CONTRACT_ACTION = "#{menuBean.reserveContractCreate}";
    
    private static final String RELEASE_CONTRACT_ACTION = "#{menuBean.releaseReservedContract}";

    private static final String MAINTAIN_CONTRACT_ACTION = "#{menuBean.reserveContractMaintain}";
    
    private static final String RESERVE_CONTRACT_NAVIGATION = "createReserveContract";

    private static final String MAINTAIN_RESERVE_CONTRACT_NAVIGATION = "maintainReserveContract";
    
    private static final String RELEASE_RESERVE_CONTRACT_NAVIGATION = "releaseReservedContract";

    private static final String MIGRATION_WIZARD_ACTION = "step1";
    
    
    // Create Test Case and Test Suite for Benefit selection
    
    private static final String RMA_NAV_ACTION = "#{menuBean.navigateToRMA}";
    
    private static final String CREATE_MENU_TESTSUITE_NAVIGATION = "createTestSuite";    
    private static final String TESTSUITE_CREATE_ACTION = "#{menuBean.createTestSuite}";
    
    private static final String MAINTAIN_MENU_TESTSUITE_NAVIGATION = "maintainTestSuite";
    private static final String TESTSUITE_MAINTAIN_ACTION = "#{menuBean.maintainTestSuite}";
    
    private static final String CREATE_MENU_TESTCASE_NAVIGATION = "createTestCase";
    private static final String MAINTAIN_MENU_TESTCASE_NAVIGATION = "maintainTestCase";
    
    private static final String TESTCASE_CREATE_ACTION = "#{menuBean.createTestCase}";
    private static final String TESTCASE_MAINTAIN_ACTION = "#{menuBean.maintainTestCase}";
    
    /*For Dictionary Manager */     
    private static final String ADD_WORD_ACTION = "#{menuBean.actionAddWord}";
    
    private static final String ADD_WORD_TO_DICTIONARY = "addWordToDictionary";
    /*For Dictionary Manager */ 
    private static final String SPS_MAPPING_CREATE_ACTION = "#{menuBean.spsMappingCreate}";
    private static final String SPS_MAPPING_MAINTAIN_ACTION = "#{menuBean.spsMappingMaintain}";
    private static final String IND_MAPPING_CREATE_ACTION = "#{menuBean.indMappingCreate}";
    private static final String IND_MAPPING_MAINTAIN_ACTION = "#{menuBean.indMappingMaintain}";
    private static final String SERVICE_TYPE_MAPPING_CREATE_ACTION = "#{menuBean.serviceTypeMappingCreate}";
    private static final String SERVICE_TYPE_MAPPING_MAINTAIN_ACTION = "#{menuBean.serviceTypeMappingMaintain}";
    private static final String CUSTOM_MESSAGE_TEXT_CREATE_ACTION = "#{menuBean.customMessageTextCreate}";
    private static final String CUSTOM_MESSAGE_TEXT_MAINTAIN_ACTION = "#{menuBean.customMessageTextMaintain}";
    private static final String VIEW_UNMAPPED_HEADER_RULES_PAGE = "viewUnmappedHeaderRulesPage";
    private static final String VIEW_UNMAPPED_HEADER_RULES_ACTION = "#{menuBean.viewUnmappedHeaderRulesAction}";
    
    private static final String SPS_MAPPING_CREATE = "spsMappingCreate";
    private static final String SPS_MAPPING_MAINTAIN = "spsMappingMaintain";
    private static final String IND_MAPPING_CREATE = "indMappingCreate";
    private static final String IND_MAPPING_MAINTAIN = "indMappingMaintain";
    private static final String SERVICE_TYPE_MAPPING_CREATE = "serviceTypeMappingCreate";
    private static final String SERVICE_TYPE_MAPPING_MAINTAIN = "serviceTypeMappingMaintain";
    private static final String CUSTOM_MESSAGE_TEXT_CREATE = "customMessageTextCreate";
    private static final String CUSTOM_MESSAGE_TEXT_MAINTAIN = "customMessageTextMaintain";
    private static final String IND_MAP_CREATE = "indMapCreate";
    private static final String IND_MAP_MAINTAIN = "indMapMaintain";
    
    private static final String RESOURCE_FILE = "rma";
	private static final String RESOURCE_FILE_BX = "blueexchange";
	private static final String RESOURCE_FILE_UPDATE = "massUpdate";
	private static  String RMA_APP_LINK = "CAT_URL";
	private static  String BX_APP_LINK = "BX_URL";
	//private static  String UPDATE_APP_LINK = "MASS_UPDATE_URL";
	private static final String MASS_UPDATE = "rma";
	    
    private User user;

    /**
     * Method for dynamic creation of the menu items
     * 
     * @return menu
     */
    public List getMenu() {

        List maintainItems = new ArrayList();

        try {
            user = getUser();
        } catch (SevereException e) {
        	Logger.logError(e);
        }
        /* Product */
        WPDNavigationMenuItem product = new WPDNavigationMenuItem("Product",
                "", user, WebConstants.PRODUCT_MODULE, null);
        WPDNavigationMenuItem productCreate = new WPDNavigationMenuItem(
                "Create", PRODUCT_CREATE_ACTION, user,
                WebConstants.PRODUCT_MODULE, WebConstants.TASK_CREATE);
        WPDNavigationMenuItem productMaintain = new WPDNavigationMenuItem(
                "Maintain", PRODUCT_MAINTAIN_ACTION, user,
                WebConstants.PRODUCT_MODULE, WebConstants.MAINTAIN_TASK);

        List productMenus = new ArrayList();
        productMenus.add(productCreate);
        productMenus.add(productMaintain);
        product.setNavigationMenuItems(productMenus);
        maintainItems.add(product);

        /* Product Structure */
        WPDNavigationMenuItem productStructure = new WPDNavigationMenuItem(
                "Product Structure", "", user,
                WebConstants.PRODUCT_STRUCTURES_MODULE, null);
        WPDNavigationMenuItem productStructureCreate = new WPDNavigationMenuItem(
                "Create", PRODUCT_STRUCTURE_CREATE_ACTION, user,
                WebConstants.PRODUCT_STRUCTURES_MODULE,
                WebConstants.TASK_CREATE);
        WPDNavigationMenuItem productStructureMaintain = new WPDNavigationMenuItem(
                "Maintain", PRODUCT_STRUCTURE_MAINTAIN_ACTION, user,
                WebConstants.PRODUCT_STRUCTURES_MODULE,
                WebConstants.MAINTAIN_TASK);
        List productStructureMenus = new ArrayList();
        productStructureMenus.add(productStructureCreate);
        productStructureMenus.add(productStructureMaintain);
        productStructure.setNavigationMenuItems(productStructureMenus);
        maintainItems.add(productStructure);

        /* Benefit Component */
        WPDNavigationMenuItem benefitComponent = new WPDNavigationMenuItem(
                "Benefit Component", "", user,
                WebConstants.BENEFIT_COMPONENTS_MODULE, null);
        WPDNavigationMenuItem benefitComponentCreate = new WPDNavigationMenuItem(
                "Create", "#{menuBean.createBenefitComponentAction}", user,
                WebConstants.BENEFIT_COMPONENTS_MODULE,
                WebConstants.TASK_CREATE);
        WPDNavigationMenuItem benefitComponentMaintain = new WPDNavigationMenuItem(
                "Maintain", "#{menuBean.maintainBenefitComponentAction}", user,
                WebConstants.BENEFIT_COMPONENTS_MODULE,
                WebConstants.MAINTAIN_TASK);
        List benefitComponentMenus = new ArrayList();
        benefitComponentMenus.add(benefitComponentCreate);
        benefitComponentMenus.add(benefitComponentMaintain);
        benefitComponent.setNavigationMenuItems(benefitComponentMenus);
        maintainItems.add(benefitComponent);

        /* Standard Benefit */
        WPDNavigationMenuItem standardBenefit = new WPDNavigationMenuItem(
                "Benefit", "", user, WebConstants.BENEFIT_MODULE, null);
        WPDNavigationMenuItem standardBenefitCreate = new WPDNavigationMenuItem(
                "Create", "#{menuBean.createStandardBenefitAction}", user,
                WebConstants.BENEFIT_MODULE, WebConstants.TASK_CREATE);
        WPDNavigationMenuItem standardBenefitMaintain = new WPDNavigationMenuItem(
                "Maintain", "#{menuBean.searchStandardBenefitAction}", user,
                WebConstants.BENEFIT_MODULE, WebConstants.MAINTAIN_TASK);
        List standardBenefitMenus = new ArrayList();
        standardBenefitMenus.add(standardBenefitCreate);
        standardBenefitMenus.add(standardBenefitMaintain);
        standardBenefit.setNavigationMenuItems(standardBenefitMenus);
        maintainItems.add(standardBenefit);

        /* Admin Options */
        WPDNavigationMenuItem adminOptions = new WPDNavigationMenuItem(
                "Admin Options", "", user, WebConstants.ADMIN_OPTION_MODULE,
                null);
        WPDNavigationMenuItem adminOptionsCreate = new WPDNavigationMenuItem(
                "Create", "#{menuBean.createAdminOptionsAction}", user,
                WebConstants.ADMIN_OPTION_MODULE,
                WebConstants.TASK_CREATE);
        WPDNavigationMenuItem adminOptionsMaintain = new WPDNavigationMenuItem(
                "Maintain", "#{menuBean.searchAdminOptionsAction}", user,
                WebConstants.ADMIN_OPTION_MODULE,
                WebConstants.MAINTAIN_TASK);
        List adminOptionsMenus = new ArrayList();
        adminOptionsMenus.add(adminOptionsCreate);
        adminOptionsMenus.add(adminOptionsMaintain);
        adminOptions.setNavigationMenuItems(adminOptionsMenus);
       // maintainItems.add(adminOptions);

        /* SPS Reference Mapping Code for Sep 2--9  start */        
        WPDNavigationMenuItem spsReferenceMapping = new WPDNavigationMenuItem("Reference Mapping", "",
                user, WebConstants.SPS_REFERENCE_MAPPING_MODULE, null);        
        WPDNavigationMenuItem spsReferenceMappingCreate = new WPDNavigationMenuItem(
                "Create", "#{menuBean.createSpsReferenceMappingAction}", user,
                WebConstants.SPS_REFERENCE_MAPPING_MODULE, WebConstants.TASK_CREATE);
        WPDNavigationMenuItem spsReferenceMappingMaintain = new WPDNavigationMenuItem(
                "Maintain", "#{menuBean.searchSpsReferenceMappingAction}", user,
                WebConstants.SPS_REFERENCE_MAPPING_MODULE, WebConstants.MAINTAIN_TASK);
        List spsReferenceMappingMenus = new ArrayList();
        spsReferenceMappingMenus.add(spsReferenceMappingCreate);
        spsReferenceMappingMenus.add(spsReferenceMappingMaintain);
        spsReferenceMapping.setNavigationMenuItems(spsReferenceMappingMenus);        
        /* SPS Reference Mapping Code for Sep 2--9  end */
                
        
        /* Indicative Mapping */
        WPDNavigationMenuItem indicativeMapping = new WPDNavigationMenuItem("Indicative Mapping", "",
                user, WebConstants.INDICATIVE_MAPPING_MODULE, null);
        WPDNavigationMenuItem indicativeMappingCreate = new WPDNavigationMenuItem(
                "Create", "#{menuBean.createIndicativeMappingAction}", user,
                WebConstants.INDICATIVE_MAPPING_MODULE, WebConstants.TASK_CREATE);
        WPDNavigationMenuItem indicativeMappingMaintain = new WPDNavigationMenuItem(
                "Maintain", "#{menuBean.searchIndicativeMappingAction}", user,
                WebConstants.INDICATIVE_MAPPING_MODULE, WebConstants.MAINTAIN_TASK);
        
        List indicativeMappingMenus = new ArrayList();
        indicativeMappingMenus.add(indicativeMappingCreate);
        indicativeMappingMenus.add(indicativeMappingMaintain);
        indicativeMapping.setNavigationMenuItems(indicativeMappingMenus);
      
        /* Added For Indicative Long Term Solution */     
        WPDNavigationMenuItem indicativeLayout = new WPDNavigationMenuItem("Indicative Layout", "",
                user, WebConstants.INDICATIVE_LAYOUT_MODULE, null);
        
        WPDNavigationMenuItem indicativeLayoutCreate = new WPDNavigationMenuItem(
                "Maintain", "#{menuBean.createIndicativeLayoutAction}", user,
                WebConstants.INDICATIVE_LAYOUT_MODULE, WebConstants.TASK_CREATE);
        List indicativeLayoutMenus = new ArrayList();
        indicativeLayoutMenus.add(indicativeLayoutCreate);
        indicativeLayout.setNavigationMenuItems(indicativeLayoutMenus);       
        
        /* Admin Method Mapping */
     
        WPDNavigationMenuItem adminMethod = new WPDNavigationMenuItem("Admin Method", "",
                user, WebConstants.ADMIN_METHOD_MODULE, null);
        
        WPDNavigationMenuItem adminMethodCreate = new WPDNavigationMenuItem(
                "Create", "#{menuBean.createAdminMethodAction}", user,
                WebConstants.ADMIN_METHOD_MODULE, WebConstants.ADMIN_METHOD_TASK_ADD);
        
        WPDNavigationMenuItem adminMethodMaintain = new WPDNavigationMenuItem(
                "Maintain", "#{menuBean.searchAdminMethodAction}", user,
                WebConstants.ADMIN_METHOD_MODULE, WebConstants.ADMIN_METHOD_MAINTAIN_TASK);
        
        WPDNavigationMenuItem adminMethodMappingCreate = new WPDNavigationMenuItem(
                "Mapping Create", "#{menuBean.createAdminMethodMappingAction}", user,
                WebConstants.ADMIN_METHOD_MODULE, WebConstants.ADMIN_METHOD_MAPPING_TASK_ADD);
        WPDNavigationMenuItem adminMethodMappingMaintain = new WPDNavigationMenuItem(
                "Mapping Maintain", "#{menuBean.searchAdminMethodMappingAction}", user,
                WebConstants.ADMIN_METHOD_MODULE, WebConstants.ADMIN_MAINTAIN_MAPPING_TASK_MAINTAIN);
        
        List adminMethodMenus = new ArrayList();
        adminMethodMenus.add(adminMethodCreate);
        adminMethodMenus.add(adminMethodMaintain);
        adminMethodMenus.add(adminMethodMappingCreate);
        adminMethodMenus.add(adminMethodMappingMaintain);
        adminMethod.setNavigationMenuItems(adminMethodMenus);
    
        
        
        /* Admin Method Mapping */
        
        WPDNavigationMenuItem stdAccumMethod = new WPDNavigationMenuItem("Standard Accumulator", "",
                user, WebConstants.STDACCUM_METHOD_MODULE, null);
        
        WPDNavigationMenuItem stdAccumMethodCreate = new WPDNavigationMenuItem(
                "Create", "#{menuBean.newCreateStdAccumulator}", user,
                WebConstants.STDACCUM_METHOD_MODULE, WebConstants.STDACCUM_METHOD_TASK_ADD);
        
        WPDNavigationMenuItem stdAccumMethodMaintain = new WPDNavigationMenuItem(
                "Maintain", "#{menuBean.newMaintainStdAccumulator}", user,
                WebConstants.STDACCUM_METHOD_MODULE, WebConstants.STDACCUM_METHOD_MAINTAIN_TASK);
        
        List stdAccumMethodMenus = new ArrayList();
        stdAccumMethodMenus.add(stdAccumMethodCreate);
       stdAccumMethodMenus.add(stdAccumMethodMaintain);
       
        stdAccumMethod.setNavigationMenuItems(stdAccumMethodMenus);
        /* RMA */
        
        userId = getRequest().getHeader(SecurityConstants.SM_USER);

		ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_FILE,Locale.getDefault());
		String path = resourceBundle.getString(RMA_APP_LINK);

        WPDNavigationMenuItem rmaMenu = new WPDNavigationMenuItem(
                "RMA", path+"?userName="+user.getUserId().toLowerCase(), user,
                WebConstants.RMA_WIZARD_MODULE,WebConstants.CREATE_RULES);
        


        /* Questions */
        WPDNavigationMenuItem questions = new WPDNavigationMenuItem(
                "Questions", "", user, WebConstants.QUESTION_MODULE, null);
        WPDNavigationMenuItem questionsCreate = new WPDNavigationMenuItem(
                "Create", "#{menuBean.createQuestionsAction}", user,
                WebConstants.QUESTION_MODULE, WebConstants.TASK_CREATE);
        WPDNavigationMenuItem questionsMaintain = new WPDNavigationMenuItem(
                "Maintain", "#{menuBean.searchQuestionsAction}", user,
                WebConstants.QUESTION_MODULE, WebConstants.MAINTAIN_TASK);
        List questionsMenus = new ArrayList();
        questionsMenus.add(questionsCreate);
        questionsMenus.add(questionsMaintain);
        questions.setNavigationMenuItems(questionsMenus);
       // maintainItems.add(questions);

        WPDNavigationMenuItem maintenanceMenu = new WPDNavigationMenuItem(
                "Product Configuration", "", user, null, null);
        maintenanceMenu.setNavigationMenuItems(maintainItems);

        /* Contract */
        WPDNavigationMenuItem contractMenu = new WPDNavigationMenuItem(
                "Contract Development", "", user,
                WebConstants.CONTRACT_DEVELOPMENT_MODULE, null);
        WPDNavigationMenuItem searchMenuContractCreate = new WPDNavigationMenuItem(
                "Create", "#{menuBean.actionContractCreate}", user,
                WebConstants.CONTRACT_DEVELOPMENT_MODULE,
                WebConstants.TASK_CREATE);
        WPDNavigationMenuItem searchMenuContractMaintain = new WPDNavigationMenuItem(
                "Maintain", "#{menuBean.actionContractMaintain}", user,
                WebConstants.CONTRACT_DEVELOPMENT_MODULE,
                WebConstants.MAINTAIN_TASK);
        WPDNavigationMenuItem searchMenuContractValidate = new WPDNavigationMenuItem(
                "Validate", "#{menuBean.actionContractValidate}", user,
                WebConstants.CONTRACT_DEVELOPMENT_MODULE,
                WebConstants.VALIDATE_TASK);
       WPDNavigationMenuItem contractDataExtractMenuItem = new WPDNavigationMenuItem(
                "Contract Extract", "#{ContractReportBackingBean.initializePage}", user,
                WebConstants.CONTRACT_DEVELOPMENT_MODULE,
                WebConstants.DATA_EXTRACT_TASK);        
        List searchItems = new ArrayList();
        searchItems.add(searchMenuContractCreate);
        searchItems.add(searchMenuContractMaintain);
        /*
   		 * Adiós RMA!
         * The following line have been commented as part of sunsetting RMA Application[Blaze Rules]
         * This have been done as part of WAS 7 upgrade project. 
         */
        //searchItems.add(searchMenuContractValidate);
        searchItems.add(contractDataExtractMenuItem);
        contractMenu.setNavigationMenuItems(searchItems);

        /* Notes */
        WPDNavigationMenuItem notesMenu = new WPDNavigationMenuItem(
                "Notes Library", "", user, WebConstants.NOTES_MODULE, null);
        WPDNavigationMenuItem notesCreate = new WPDNavigationMenuItem("Create",
                NOTES_CREATE_ACTION, user, WebConstants.NOTES_MODULE,
                WebConstants.TASK_CREATE);
        WPDNavigationMenuItem notesMaintain = new WPDNavigationMenuItem(
                "Maintain", NOTES_MAINTAIN_ACTION, user,
                WebConstants.NOTES_MODULE, WebConstants.MAINTAIN_TASK);
        List notesItems = new ArrayList();
        notesItems.add(notesCreate);
        notesItems.add(notesMaintain);
        notesMenu.setNavigationMenuItems(notesItems);

        /* Administation Start */
        WPDNavigationMenuItem adminMenu = new WPDNavigationMenuItem(
                "Administration", "", user, null, null);
        WPDNavigationMenuItem referenceData = new WPDNavigationMenuItem(
                "Reference Data", "", user,
                WebConstants.REFERENCE_DATA_MODULE, null);
        WPDNavigationMenuItem security = new WPDNavigationMenuItem("Security",
                "", user, WebConstants.SECURITY_MODULE, null);
        WPDNavigationMenuItem contractID = new WPDNavigationMenuItem(
                "Contract Id", "", user, WebConstants.CONTRACT_ID_MODULE,
                null);
        WPDNavigationMenuItem system = new WPDNavigationMenuItem("System", "",
                user, WebConstants.SYSTEM_MODULE, null);
        WPDNavigationMenuItem dictionaryManager = new WPDNavigationMenuItem("Dictionary", "",
                user, WebConstants.DICTIONARY_MANAGER_MODULE, null);

        WPDNavigationMenuItem migrationMenu = new WPDNavigationMenuItem(
                "Contract Migration Wizard", "#{menuBean.actionMigrationWizard}", user,
                WebConstants.MIGRATION_WIZARD_MODULE,null);
        WPDNavigationMenuItem blueExchange = new WPDNavigationMenuItem("Blue Exchange", "",
                user, WebConstants.BLUE_EXCHANGE_MODULE, null);
                
        List adminMenuList = new ArrayList();
        adminMenuList.add(referenceData);
        adminMenuList.add(security);
        adminMenuList.add(system);
        adminMenuList.add(contractID);
        adminMenuList.add(migrationMenu);
        adminMenuList.add(dictionaryManager);
        adminMenuList.add(blueExchange);
        adminMenuList.add(adminOptions);
        adminMenuList.add(questions);
        adminMenuList.add(spsReferenceMapping);
        adminMenuList.add(indicativeMapping);
        /* Added For Indicative Long Term Solution */
        adminMenuList.add(indicativeLayout);
        /*
   		 * Adiós RMA!
         * The following line have been commented as part of sunsetting RMA Application[Blaze Rules]
         * This have been done as part of WAS 7 upgrade project. 
         */
        //adminMenuList.add(rmaMenu);
       adminMenuList.add(adminMethod);
       adminMenuList.add(stdAccumMethod);
        adminMenu.setNavigationMenuItems(adminMenuList);
        /*Mass update*/ /* removing mass update 
        /*ResourceBundle resourceBundleUpdate = ResourceBundle.getBundle(RESOURCE_FILE_UPDATE,Locale.getDefault());
        String updatePath = resourceBundleUpdate.getString(UPDATE_APP_LINK);
        
        WPDNavigationMenuItem massUpdateMenu = new WPDNavigationMenuItem(
                "Mass Update", "", user, WebConstants.MASS_UPDATE_MODULE, WebConstants.TASK_MASS_UPDATE);
        List updateMenuList = new ArrayList();
        WPDNavigationMenuItem massUpdateMenuItem = new WPDNavigationMenuItem("Mass Update",
        		updatePath + "/massupdate/loadSearch.html?userName="+user.getUserId().toLowerCase()+"&from=ewpd", user, WebConstants.MASS_UPDATE_MODULE,
                WebConstants.TASK_MASS_UPDATE);
        updateMenuList.add(massUpdateMenuItem);
        massUpdateMenu.setNavigationMenuItems(updateMenuList);*/
        /*Mass update end*/

        WPDNavigationMenuItem catalogCreateMenu = new WPDNavigationMenuItem(
                "Catalog Create", CATALOG_CREATE_ACTION, user,
                WebConstants.REFERENCE_DATA_MODULE,
                WebConstants.CATALOG_CREATE_TASK);
        WPDNavigationMenuItem catalogMaintainMenu = new WPDNavigationMenuItem(
                "Catalog Maintain", MAINTAIN_CATALOG_NAVIGATION, user,
                WebConstants.REFERENCE_DATA_MODULE,
                WebConstants.CATALOG_MAINTAIN_TASK);
        WPDNavigationMenuItem itemCreateMenu = new WPDNavigationMenuItem(
                "Item Create", ITEM_CREATE_ACTION, user,
                WebConstants.REFERENCE_DATA_MODULE,
                WebConstants.ITEM_CREATE_TASK);
        WPDNavigationMenuItem itemMaintainMenu = new WPDNavigationMenuItem(
                "Item Maintain", ITEM_MAINTAIN_ACTION, user,
                WebConstants.REFERENCE_DATA_MODULE,
                WebConstants.ITEM_MAINTAIN_TASK);
        WPDNavigationMenuItem subCatalogCreateMenu = new WPDNavigationMenuItem(
                "Sub-Catalog Create", SUB_CATALOG_CREATE_ACTION, user,
                WebConstants.REFERENCE_DATA_MODULE,
                WebConstants.SUBCATALOG_CREATE_TASK);
        WPDNavigationMenuItem subCatalogMaintainMenu = new WPDNavigationMenuItem(
                "Sub-Catalog Maintain", SUB_CATALOG_MAINTAIN_ACTION, user,
                WebConstants.REFERENCE_DATA_MODULE,
                WebConstants.SUBCATALOG_MAINTAIN_TASK);

        List refDataMenuList = new ArrayList();

        refDataMenuList.add(catalogCreateMenu);
        refDataMenuList.add(catalogMaintainMenu);
        refDataMenuList.add(subCatalogCreateMenu);
        refDataMenuList.add(subCatalogMaintainMenu);
        refDataMenuList.add(itemCreateMenu);
        refDataMenuList.add(itemMaintainMenu);
        referenceData.setNavigationMenuItems(refDataMenuList);

        WPDNavigationMenuItem roleCreateMenu = new WPDNavigationMenuItem(
                "Role Create", ROLE_CREATE_ACTION, user,
                WebConstants.SECURITY_MODULE,
                WebConstants.ROLE_CREATE_TASK);
        WPDNavigationMenuItem roleMaintainMenu = new WPDNavigationMenuItem(
                "Role Maintain", ROLE_MAINTAIN_ACTION, user,
                WebConstants.SECURITY_MODULE,
                WebConstants.ROLE_MAINTAIN_TASK);
        WPDNavigationMenuItem moduleCreateMenu = new WPDNavigationMenuItem(
                "Module Create", MODULE_CREATE_ACTION, user,
                WebConstants.SECURITY_MODULE,
                WebConstants.MODULE_CREATE_TASK);
        WPDNavigationMenuItem moduleMaintainMenu = new WPDNavigationMenuItem(
                "Module Maintain", MODULE_MAINTAIN_ACTION, user,
                WebConstants.SECURITY_MODULE,
                WebConstants.MODULE_MAINTAIN_TASK);
        WPDNavigationMenuItem taskCreateMenu = new WPDNavigationMenuItem(
                "Task Create", TASK_CREATE_ACTION, user,
                WebConstants.SECURITY_MODULE,
                WebConstants.TASK_CREATE_TASK);
        WPDNavigationMenuItem taskMaintainMenu = new WPDNavigationMenuItem(
                "Task Maintain", TASK_MAINTAIN_ACTION, user,
                WebConstants.SECURITY_MODULE,
                WebConstants.TASK_MAINTAIN_TASK);

        List securityMenuList = new ArrayList();

        securityMenuList.add(roleCreateMenu);
        securityMenuList.add(roleMaintainMenu);
        securityMenuList.add(moduleCreateMenu);
        securityMenuList.add(moduleMaintainMenu);
        securityMenuList.add(taskCreateMenu);
        securityMenuList.add(taskMaintainMenu);

        security.setNavigationMenuItems(securityMenuList);

        WPDNavigationMenuItem reserveContractMenu = new WPDNavigationMenuItem(
                "Reserve", RESERVE_CONTRACT_ACTION, user,
                WebConstants.CONTRACT_ID_MODULE, WebConstants.TASK_CREATE);
        WPDNavigationMenuItem releaseContractMenu = new WPDNavigationMenuItem(
                "Release", RELEASE_CONTRACT_ACTION, user,
                WebConstants.CONTRACT_ID_MODULE,
                WebConstants.RELEASE_TASK);
        WPDNavigationMenuItem maintainContractMenu = new WPDNavigationMenuItem(
                "Maintain", MAINTAIN_CONTRACT_ACTION, user,
                WebConstants.CONTRACT_ID_MODULE,
                WebConstants.MAINTAIN_TASK);
        List contractIDList = new ArrayList();

        contractIDList.add(reserveContractMenu);
        contractIDList.add(releaseContractMenu);
        contractIDList.add(maintainContractMenu);
        contractID.setNavigationMenuItems(contractIDList);

        List systemItems = new ArrayList();
        WPDNavigationMenuItem uac = new WPDNavigationMenuItem(
                "Application Access Control", "applicationAccessControl", user,
                WebConstants.SYSTEM_MODULE,
                WebConstants.APPLICATION_ACCESS_CONTROL_TASK);
        systemItems.add(uac);
        WPDNavigationMenuItem lv = new WPDNavigationMenuItem("Log Viewer",
                "logViewer", user, WebConstants.SYSTEM_MODULE,
                WebConstants.LOG_VIEWER_TASK);
        systemItems.add(lv);
        system.setNavigationMenuItems(systemItems);
        /* Administation End */
        /* For Blue Exchange Start */
        WPDNavigationMenuItem spsMappingCreate = new WPDNavigationMenuItem(
                "SPS Mapping Create", SPS_MAPPING_CREATE_ACTION, user,
                WebConstants.BLUE_EXCHANGE_MODULE,
                WebConstants.SPS_MAPPING_CREATE_TASK);
        WPDNavigationMenuItem spsMappingMaintain = new WPDNavigationMenuItem(
                "SPS Mapping Maintain", SPS_MAPPING_MAINTAIN_ACTION, user,
                WebConstants.BLUE_EXCHANGE_MODULE,
                WebConstants.SPS_MAPPING_MAINTAIN_TASK);
        WPDNavigationMenuItem serviceTypeMappingCreate = new WPDNavigationMenuItem(
                "Service Type Mapping Create", SERVICE_TYPE_MAPPING_CREATE_ACTION, user,
                WebConstants.BLUE_EXCHANGE_MODULE,
                WebConstants.SERVICE_TYPE_MAPPING_CREATE_TASK);
        WPDNavigationMenuItem serviceTypeMappingMaintain = new WPDNavigationMenuItem(
                "Service Type Mapping Maintain", SERVICE_TYPE_MAPPING_MAINTAIN_ACTION, user,
                WebConstants.BLUE_EXCHANGE_MODULE,
                WebConstants.SERVICE_TYPE_MAPPING_MAINTAIN_TASK);
             WPDNavigationMenuItem unMappedHeaderRules = new WPDNavigationMenuItem(
                "UnMapped Header Rules", VIEW_UNMAPPED_HEADER_RULES_ACTION, user,
                WebConstants.BLUE_EXCHANGE_MODULE,
                WebConstants.VIEW_SERVICE_TYPE_CODE_MAPPING_TASK);        
        WPDNavigationMenuItem customMessageTextCreate = new WPDNavigationMenuItem(
                "Custom Message Text Create", CUSTOM_MESSAGE_TEXT_CREATE_ACTION, user,
                WebConstants.BLUE_EXCHANGE_MODULE,
                WebConstants.CUSTOM_MESSAGE_TEXT_CREATE_TASK);
        WPDNavigationMenuItem customMessageTextMaintain = new WPDNavigationMenuItem(
                "Custom Message Text Maintain", CUSTOM_MESSAGE_TEXT_MAINTAIN_ACTION, user,
                WebConstants.BLUE_EXCHANGE_MODULE,
                WebConstants.CUSTOM_MESSAGE_TEXT_MAINTAIN_TASK);
        
        ResourceBundle resourceBundleBx = ResourceBundle.getBundle(RESOURCE_FILE_BX,Locale.getDefault());
        String bxPath = resourceBundleBx.getString(BX_APP_LINK);
        
        WPDNavigationMenuItem contractVariableMapping = new WPDNavigationMenuItem(
                 "WPD BX Mapping", bxPath+"/wpdBXindex.jsp?userName="+user.getUserId().toLowerCase(), user,
                 WebConstants.BLUE_EXCHANGE_MAPPING_MODULE,WebConstants.BLUE_EXCHANGE_MAPPING_TASK);
         
        WPDNavigationMenuItem ewpdBXMapping = new WPDNavigationMenuItem(
                "eWPD BX Mapping", bxPath+"/ewpdBXindex.jsp?userName="+user.getUserId().toLowerCase(), user,
                WebConstants.EBX_BLUE_EXCHANGE_MAPPING_MODULE,WebConstants.EBX_BLUE_EXCHANGE_MAPPING_TASK);
        
        //Menu item for the MTM Simulation module.
        WPDNavigationMenuItem simulationMapping = new WPDNavigationMenuItem(
                "Simulation Tool", bxPath+"/simulationindex.jsp?userName="+user.getUserId().toLowerCase(), user,
                WebConstants.SIMULATION_MAPPING_MODULE,WebConstants.SIMULATION_MAPPING_TASK);
        
        WPDNavigationMenuItem spiderMapping = new WPDNavigationMenuItem(
                "SPIDER BX UM Mapping", bxPath+"/spiderIndex.jsp?userName="+user.getUserId().toLowerCase(), user,
                WebConstants.SPIDER_MAPPING_MODULE,WebConstants.SPIDER_MAPPING_TASK);
        
        List blueExchangeList = new ArrayList();
        blueExchangeList.add(spsMappingCreate);
        blueExchangeList.add(spsMappingMaintain);
        blueExchangeList.add(serviceTypeMappingCreate);
        blueExchangeList.add(serviceTypeMappingMaintain);
        blueExchangeList.add(unMappedHeaderRules);
        /* Commented For Custom Message Start*/
        
       blueExchangeList.add(customMessageTextCreate);
       blueExchangeList.add(customMessageTextMaintain);
       blueExchangeList.add(contractVariableMapping);
       blueExchangeList.add(ewpdBXMapping);
       blueExchangeList.add(simulationMapping);
       blueExchangeList.add(spiderMapping); // Added for spider mapping menu
        /* Commented For Custom Message End*/
       
       
       
       /* Administation End */
       /* For Blue Exchange Start */
       WPDNavigationMenuItem indMappingCreate = new WPDNavigationMenuItem(
               "SPS Mapping Create", IND_MAPPING_CREATE_ACTION, user,
               WebConstants.BLUE_EXCHANGE_MODULE,
               WebConstants.SPS_MAPPING_CREATE_TASK);
       WPDNavigationMenuItem indMappingMaintain = new WPDNavigationMenuItem(
               "SPS Mapping Maintain", IND_MAPPING_CREATE_ACTION, user,
               WebConstants.BLUE_EXCHANGE_MODULE,
               WebConstants.SPS_MAPPING_MAINTAIN_TASK);
        
        blueExchange.setNavigationMenuItems(blueExchangeList);
        /* For Blue Exchange End */
        /*For Dictionary Manager Start*/        
        WPDNavigationMenuItem addWordMenu = new WPDNavigationMenuItem(
                "Add", ADD_WORD_ACTION, user,
                WebConstants.DICTIONARY_MANAGER,
                WebConstants.ADD_WORD_TASK);
        List dictionaryManagerList = new ArrayList();
        dictionaryManagerList.add(addWordMenu);
        dictionaryManager.setNavigationMenuItems(dictionaryManagerList);
        /*For Dictionary Manager End*/  
        
        contractMenu.setNavigationMenuItems(searchItems);
        WPDNavigationMenuItem searchBasicMenu = new WPDNavigationMenuItem(
                "Search", WebConstants.SEARCH_ITEM_NAVIGATION, user,
                WebConstants.SEARCH_ENGINE_MODULE, null);

        WPDNavigationMenuItem basicSearch = new WPDNavigationMenuItem("Basic",
                BASIC_SEARCH_ACTION, user,
                WebConstants.SEARCH_ENGINE_MODULE,
                WebConstants.BASIC_SEARCH_TASK);
        WPDNavigationMenuItem advancedSearch = new WPDNavigationMenuItem(
                "Advanced", ADVANCED_SEARCH_ACTION, user,
                WebConstants.SEARCH_ENGINE_MODULE,
                WebConstants.ADVANCED_SEARCH_TASK);
        List searchList = new ArrayList();
        searchList.add(basicSearch);
        searchList.add(advancedSearch);
        searchBasicMenu.setNavigationMenuItems(searchList);

        /* Benefit Selection > 
         *  				Test Rule
         * 						> Create
         * 						> Maintain
         * 					Test Suite 
         * 						> Create
         * 						> Maintain
         * 					
         */
        WPDNavigationMenuItem benefitSelectionMenu = new WPDNavigationMenuItem(
                "Benefit Selection", "", user, null, null);
        
        WPDNavigationMenuItem rmappl =  new WPDNavigationMenuItem(
                "RMA",  RMA_NAV_ACTION, user, WebConstants.ADJUD_RMA_MODULE, null);
        
        WPDNavigationMenuItem testRule = new WPDNavigationMenuItem(
                "Test Case", "", user,
                WebConstants.TESTCASE_MODULE, null);
        
        
   
        WPDNavigationMenuItem testSuite = new WPDNavigationMenuItem("Test Suite",
                "", user, WebConstants.TESTSUITE_MODULE, null);
        
        List benefitSelectionSubMenuList = new ArrayList();
        benefitSelectionSubMenuList.add(rmappl);
        benefitSelectionSubMenuList.add(testRule);
        benefitSelectionSubMenuList.add(testSuite);
        benefitSelectionMenu.setNavigationMenuItems(benefitSelectionSubMenuList);
                        
        WPDNavigationMenuItem createTestCase = new WPDNavigationMenuItem(
                "Create",  TESTCASE_CREATE_ACTION, user, WebConstants.TESTCASE_MODULE, WebConstants.TASK_TESTCASE_CREATE);
        WPDNavigationMenuItem maintainTestCase = new WPDNavigationMenuItem(
                "Maintain",  TESTCASE_MAINTAIN_ACTION, user, WebConstants.TESTCASE_MODULE, WebConstants.TASK_TESTCASE_MAINTAIN);
      
        List testRuleMenuList = new ArrayList();
        testRuleMenuList.add(createTestCase);
        testRuleMenuList.add(maintainTestCase);
        testRule.setNavigationMenuItems(testRuleMenuList);
     
   		WPDNavigationMenuItem createTestSuite = new WPDNavigationMenuItem(
        		"Create",  TESTSUITE_CREATE_ACTION, user, WebConstants.TESTSUITE_MODULE, WebConstants.TASK_TESTSUITE_CREATE);
   		WPDNavigationMenuItem maintainTestSuite = new WPDNavigationMenuItem(
        		"Maintain",  TESTSUITE_MAINTAIN_ACTION, user, WebConstants.TESTSUITE_MODULE, WebConstants.TASK_TESTSUITE_MAINTAIN);
        
        List testSuiteMenuList = new ArrayList();
   		testSuiteMenuList.add(createTestSuite);
   		testSuiteMenuList.add(maintainTestSuite);
   		testSuite.setNavigationMenuItems(testSuiteMenuList);
        
   		/*
   		 * Adiós RMA!
         * The following line have been commented as part of sunsetting RMA Application[Blaze Rules]
         * This have been done as part of WAS 7 upgrade project. 
         */
        /*WPDNavigationMenuItem[] items = new WPDNavigationMenuItem[] {
                searchBasicMenu, contractMenu, maintenanceMenu, notesMenu,
                adminMenu, benefitSelectionMenu };
   		*/
   		
   		WPDNavigationMenuItem[] items = new WPDNavigationMenuItem[] {
                searchBasicMenu, contractMenu, maintenanceMenu, notesMenu,
                adminMenu};
        
        List menu = new ArrayList();
        //adding only menu items which are authorized.
        for (int i = 0; i < items.length; i++) {
            if (items[i].isAuthorized()) {
                menu.add(items[i]);
            }
        }

        return menu;
    }

    /**
     * Method returning String for Navigation
     * 
     * @return String BASIC_SEARCH_CRITERIA
     */
    public String actionSearchAction() {
        SessionCleanUp.cleanUp();
        return SearchConstants.BASIC_SEARCH_CRITERIA;
    }

    /**
     * Method returning String for Navigation
     * 
     * @return String CONTRACT_CREATE_NAVIGATION
     */
    public String actionContractCreate() {
        SessionCleanUp.cleanUp();
        return CONTRACT_CREATE_NAVIGATION;
    }

    /**
     * Method returning String for Navigation
     * 
     * @return String CONTRACT_MAINTAIN_NAVIGATION
     */
    public String actionContractMaintain() {
        SessionCleanUp.cleanUp();
        return CONTRACT_MAINTAIN_NAVIGATION;
    }
    
    /**
     * Method returning String for Navigation
     * 
     * @return String CONTRACT_MAINTAIN_NAVIGATION
     */
    public String actionContractValidate() {
        SessionCleanUp.cleanUp();
        return CONTRACT_VALIDATE_NAVIGATION;
    }

    /**
     * Method returning String for Navigation
     * 
     * @return String CREATE_NAVIGATION
     */
    public String actionMandateCreate() {
        return CREATE_NAVIGATION;
    }

    /**
     * Method returning String for Navigation
     * 
     * @return String MIGRATION_WIZARD_ACTION
     */
    public String actionMigrationWizard() {
        SessionCleanUp.cleanUp();
        this.getSession().removeAttribute("legacyContractBackingBean");
        this.setBreadCrumbText("Administration >> Contract Migration Wizard >> Step1");
        return MIGRATION_WIZARD_ACTION;

    }

    /**
     * Method returning String for Navigation
     * 
     * @return String SEARCH_NAVIGATION
     */
    public String actionMandateSearch() {
        return SEARCH_NAVIGATION;
    }
    /**
     * Method returning String for Navigation
     * 
     * @return String VARIABLE_CREATE_NAVIGATION
     */
    public String actionVariableCreate() {
        return VARIABLE_CREATE_NAVIGATION;
    }

    /**
     * Method returning String for Navigation
     * 
     * @return String STRUCTURE_SEARCH_NAVIGATION
     */
    public String actionStructureSearch() {
        return STRUCTURE_SEARCH_NAVIGATION;
    }

    /**
     * Method returning String for Navigation
     * 
     * @return String STRUCTURE_CREATE_NAVIGATION
     */
    public String actionStructureCreate() {
        return STRUCTURE_CREATE_NAVIGATION;
    }

    /**
     * Method returning String for Navigation
     * 
     * @return String VARIABLE_SEARCH_NAVIGATION
     */
    public String actionVariableSearch() {
        return VARIABLE_SEARCH_NAVIGATION;
    }

    /**
     * Method returning String for Navigation
     * 
     * @return String CREATE_BENEFIT_COMPONENT_NAVIGATION
     */
    public String createBenefitComponentAction() {
        SessionCleanUp.cleanUp();
        return CREATE_BENEFIT_COMPONENT_NAVIGATION;
    }

    /**
     * Method returning String for Navigation
     * 
     * @return String MAINTAIN_BENEFIT_COMPONENT_NAVIGATION
     */
    public String maintainBenefitComponentAction() {
        SessionCleanUp.cleanUp();
        return MAINTAIN_BENEFIT_COMPONENT_NAVIGATION;
    }

    /**
     * Method returning String for Navigation
     * 
     * @return String CREATE_PRODUCT_STRUCTURE_NAVIGATION
     */
    public String actionProductStructureCreate() {
        SessionCleanUp.cleanUp();
        return CREATE_PRODUCT_STRUCTURE_NAVIGATION;
    }

    /**
     * Method returning String for Navigation
     * 
     * @return String MAINTAIN_PRODUCT_STRUCTURE_NAVIGATION
     */
    public String actionProductStructureMaintain() {
        SessionCleanUp.cleanUp();
        return MAINTAIN_PRODUCT_STRUCTURE_NAVIGATION;

    }

    /**
     * Method returning String for Navigation
     * 
     * @return String CREATE_PRODUCT_NAVIGATION
     */
    public String actionProductCreate() {
        SessionCleanUp.cleanUp();
        return CREATE_PRODUCT_NAVIGATION;
    }

    /**
     * Method returning String for Navigation
     * 
     * @return String MAINTAIN_PRODUCT_NAVIGATION
     */
    public String actionProductMaintain() {
        SessionCleanUp.cleanUp();
        return MAINTAIN_PRODUCT_NAVIGATION;

    }

    /**
     * Method returning String for Navigation
     * 
     * @return String CREATE_NOTES_NAVIGATION
     */
    public String actionNotesCreate() {
        SessionCleanUp.cleanUp();
        return CREATE_NOTES_NAVIGATION;
    }

    /**
     * Method returning String for Navigation
     * 
     * @return String MAINTAIN_NOTES_NAVIGATION
     */
    public String actionNotesMaintain() {
        SessionCleanUp.cleanUp();
        return MAINTAIN_NOTES_NAVIGATION;

    }

    /**
     * Method returning String for Navigation
     * 
     * @return String CREATE_CATALOG_NAVIGATION
     */
    public String actionCatalogCreate() {
        SessionCleanUp.cleanUp();
        return CREATE_CATALOG_NAVIGATION;
    }

    /**
     * Method returning String for Navigation
     * 
     * @return String MAINTAIN_CATALOG_NAVIGATION
     */
    public String actionCatalogMaintain() {
        SessionCleanUp.cleanUp();
        return MAINTAIN_CATALOG_NAVIGATION;

    }

    /**
     * Method returning String for Navigation
     * 
     * @return String CREATE_SUB_CATALOG_NAVIGATION
     */
    public String actionSubCatalogCreate() {
        SessionCleanUp.cleanUp();
        return CREATE_SUB_CATALOG_NAVIGATION;
    }

    /**
     * Method returning String for Navigation
     * 
     * @return String MAINTAIN_SUB_CATALOG_NAVIGATION
     */
    public String actionSubCatalogMaintain() {
        SessionCleanUp.cleanUp();
        return MAINTAIN_SUB_CATALOG_NAVIGATION;

    }

    /**
     * Method returning String for Navigation
     * 
     * @return String CREATE_ITEM_NAVIGATION
     */
    public String actionItemCreate() {
        SessionCleanUp.cleanUp();
        return CREATE_ITEM_NAVIGATION;
    }

    /**
     * Method returning String for Navigation
     * 
     * @return String MAINTAIN_ITEM_NAVIGATION
     */
    public String actionItemMaintain() {
        SessionCleanUp.cleanUp();
        return MAINTAIN_ITEM_NAVIGATION;

    }

    /**
     * Method returning String for Navigation
     * 
     * @return String CREATE_MENU_STD_BENEFIT_NAVIGATION
     */
    public String createStandardBenefitAction() {

        SessionCleanUp.cleanUp();
        return CREATE_MENU_STD_BENEFIT_NAVIGATION;

    }

    /**
     * Method returning String for Navigation
     * 
     * @return String MAINTAIN_MENU_STD_BENEFIT_NAVIGATION
     */
    public String searchStandardBenefitAction() {
        SessionCleanUp.cleanUp();
        return MAINTAIN_MENU_STD_BENEFIT_NAVIGATION;

    }

    
    /**
     * Method returning String for Navigation
     * 
     * @return String CREATE_MENU_QUESTIONS_NAVIGATION
     */
    public String createIndicativeMappingAction() {
        SessionCleanUp.cleanUp();
		setBreadCrumbText("Administration >> Indicative Mapping >> Create");
        return CREATE_INDICATIVE_MAPPING_NAVIGATION;
    }

    /**
     * Method returning String for Navigation
     * 
     * @return String MAINTAIN_MENU_QUESTIONS_NAVIGATION
     */
    public String searchIndicativeMappingAction() {
        SessionCleanUp.cleanUp();
		setBreadCrumbText("Administration >> Indicative Mapping >> Locate");
        return MAINTAIN_INDICATIVE_MAPPING_NAVIGATION;

    } 
    
    
    /**
     * Method returning String for Navigation
     * 
     * @return String CREATE_ADMIN_METHOD_NAVIGATION
     */   
    public String createAdminMethodAction() {
        SessionCleanUp.cleanUp();
        HttpSession httpSession= this.getSession();
		httpSession.setAttribute(AdminMethodMaintainBackingBean.REQ_PARAM_LIST, new ArrayList());
		httpSession.setAttribute(AdminMethodMaintainBackingBean.REQ_PARAM_ID_LIST, new ArrayList());
		setBreadCrumbText("Administration >> Admin Method >> Create");
	
        return CREATE_ADMIN_METHOD_NAVIGATION;
    }

    /**
     * Method returning String for Navigation
     * 
     * @return String MAINTAIN_ADMIN_METHOD_NAVIGATION
     */
    public String searchAdminMethodAction() {
        SessionCleanUp.cleanUp();
        HttpSession httpSession= this.getSession();
		httpSession.setAttribute(AdminMethodMaintainBackingBean.REQ_PARAM_LIST, new ArrayList());
		httpSession.setAttribute(AdminMethodMaintainBackingBean.REQ_PARAM_ID_LIST, new ArrayList());
		setBreadCrumbText("Administration >> Admin Method >> Locate");
        return MAINTAIN_ADMIN_METHOD_NAVIGATION;

    } 
    /**
     * Method returning String for Navigation
     * 
     * @return String CREATE_ADMIN_METHOD_MAPPING_NAVIGATION
     */
    public String createAdminMethodMappingAction() {
        SessionCleanUp.cleanUp();
		setBreadCrumbText("Administration >> Admin Method Mapping >> Create");
		HttpSession httpSession= this.getSession();
		httpSession.setAttribute("questionAnswerMap", new HashMap());
        return CREATE_ADMIN_METHOD_MAPPING_NAVIGATION;
    }
    
    
    public String newCreateStdAccumulator() {
		SessionCleanUp.cleanUp();
		sessionScope = (Map) FacesContext.getCurrentInstance().getApplication()
				.createValueBinding("#{sessionScope}").getValue(
						FacesContext.getCurrentInstance());
		if (null != sessionScope.get("standardAccumBackingBean")) {
			sessionScope.remove("standardAccumBackingBean");
		}
		setValidationMessages(null);
		return CREATE_ACCUM_METHOD_MAPPING_NAVIGATION;
	}
    public String newMaintainStdAccumulator() {
		SessionCleanUp.cleanUp();
		sessionScope = (Map) FacesContext.getCurrentInstance().getApplication()
		.createValueBinding("#{sessionScope}").getValue(
				FacesContext.getCurrentInstance());
      if (null != sessionScope.get("searchStdAccumBackingBean")) {
	   sessionScope.remove("searchStdAccumBackingBean");
}
validationMessages = null;
		return MAINTAIN_ACCUM_METHOD_MAPPING_NAVIGATION;
	}

    /**
     * Method returning String for Navigation
     * 
     * @return String MAINTAIN_ADMIN_METHOD_MAPPING_NAVIGATION
     */
    public String searchAdminMethodMappingAction() {
        SessionCleanUp.cleanUp();
    	HttpSession httpSession= this.getSession();
		httpSession.setAttribute("questionAnswerMap", new HashMap());
		setBreadCrumbText("Administration >> Admin Method >> Mapping Locate");
        return MAINTAIN_ADMIN_METHOD_MAPPING_NAVIGATION;

    } 
    
    
    /**
     * SPS Reference Mapping Code for Sep 2--9
     * Method returning String for Navigation
     * 
     * @return String MAINTAIN_MENU_QUESTIONS_NAVIGATION
     */

    
    
    public String createSpsReferenceMappingAction() {
        SessionCleanUp.cleanUp();
		setBreadCrumbText("Administration >> Reference Mapping  >> Create");
        return CREATE_SPS_MAPPING_NAVIGATION;
    }
    
    
    /**
     * SPS Reference Mapping Code for Sep 2--9
     * Method returning String for Navigation
     * 
     * @return String MAINTAIN_MENU_QUESTIONS_NAVIGATION
     */
    public String searchSpsReferenceMappingAction() {
        SessionCleanUp.cleanUp();
		setBreadCrumbText("Administration >> Reference Mapping  >> Locate");
        return MAINTAIN_SPS_MAPPING_NAVIGATION;

    } 

    


    /**
     * Method returning String for Navigation
     * 
     * @return String CREATE_MENU_QUESTIONS_NAVIGATION
     */
    public String createQuestionsAction() {
        SessionCleanUp.cleanUp();
        return CREATE_MENU_QUESTIONS_NAVIGATION;

    }

    /**
     * Method returning String for Navigation
     * 
     * @return String MAINTAIN_MENU_QUESTIONS_NAVIGATION
     */
    public String searchQuestionsAction() {

        return MAINTAIN_MENU_QUESTIONS_NAVIGATION;

    }

    /**
     * Method returning String for Navigation
     * 
     * @return String CREATE_MENU_ADMIN_OPTIONS_NAVIGATION
     */
    public String createAdminOptionsAction() {
        SessionCleanUp.cleanUp();
        return CREATE_MENU_ADMIN_OPTIONS_NAVIGATION;

    }

    /**
     * Method returning String for Navigation
     * 
     * @return String MAINTAIN_MENU_ADMIN_OPTIONS_NAVIGATION
     */
    public String searchAdminOptionsAction() {
        SessionCleanUp.cleanUp();
        return MAINTAIN_MENU_ADMIN_OPTIONS_NAVIGATION;

    }

    /**
     * Method returning String for Navigation
     * 
     * @return String CREATE_MENU_MANDATE_NAVIGATION
     */
    public String createMandateAction() {

        return CREATE_MENU_MANDATE_NAVIGATION;

    }

    /**
     * Method returning String for Navigation
     * 
     * @return String MAINTAIN_MENU_MANDATE_NAVIGATION
     */
    public String searchMnadateAction() {

        return MAINTAIN_MENU_MANDATE_NAVIGATION;

    }

    /**
     * Method returning String for Navigation
     * 
     * @return String CREATE_ROLE_NAVIGATION
     */
    public String actionRoleCreate() {
        SessionCleanUp.cleanUp();
        return CREATE_ROLE_NAVIGATION;
    }

    /**
     * Method returning String for Navigation
     * 
     * @return String MAINTAIN_ROLE_NAVIGATION
     */
    public String actionRoleMaintain() {
        SessionCleanUp.cleanUp();
        return MAINTAIN_ROLE_NAVIGATION;
    }

    /**
     * Method returning String for Navigation
     * 
     * @return String MODULE_CREATE_NAVIGATION
     */
    public String actionModuleCreate() {
        SessionCleanUp.cleanUp();
        return MODULE_CREATE_NAVIGATION;
    }

    /**
     * Method returning String for Navigation
     * 
     * @return String MODULE_MAINTAIN_NAVIGATION
     */
    public String actionModuleMaintain() {
        SessionCleanUp.cleanUp();
        return MODULE_MAINTAIN_NAVIGATION;
    }

    /**
     * Method returning String for Navigation
     * 
     * @return String TASK_CREATE_NAVIGATION
     */
    public String actionTaskCreate() {
        SessionCleanUp.cleanUp();
        return TASK_CREATE_NAVIGATION;
    }

    /**
     * Method returning String for Navigation
     * 
     * @return String TASK_MAINTAIN_NAVIGATION
     */
    public String actionTaskMaintain() {
        SessionCleanUp.cleanUp();
        return TASK_MAINTAIN_NAVIGATION;
    }

    /**
     * Method returning String for Navigation
     * 
     * @return String RESERVE_CONTRACT_NAVIGATION
     */
    public String reserveContractCreate() {
        SessionCleanUp.cleanUp();
        return RESERVE_CONTRACT_NAVIGATION;
    }

    public String viewUnmappedHeaderRulesAction(){
        SessionCleanUp.cleanUp();
		setBreadCrumbText("Administration >> Blue Exchange >> UnMapped Header Rules");
		return VIEW_UNMAPPED_HEADER_RULES_PAGE;
    }
    /**
     * Method returning String for Navigation
     * 
     * @return String MAINTAIN_RESERVE_CONTRACT_NAVIGATION
     */
    public String reserveContractMaintain() {
        SessionCleanUp.cleanUp();
        return MAINTAIN_RESERVE_CONTRACT_NAVIGATION;
    }
    
    /**
     * Method returning String for Navigation
     * 
     * @return String RELEASE_RESERVE_CONTRACT_NAVIGATION
     */
    public String releaseReservedContract() {
        SessionCleanUp.cleanUp();
        return RELEASE_RESERVE_CONTRACT_NAVIGATION;
    }
    

    public String actionBasicSearch(){
        SessionCleanUp.cleanUp();
		setBreadCrumbText("Search >> Basic Search Criteria");
        Application application =
			FacesContext.getCurrentInstance().getApplication();
        BasicSearchBackingBean basicSearchBackingBean = ((BasicSearchBackingBean)application.createValueBinding(
		"#{basicSearchBackingBean}")
		.getValue(FacesContext.getCurrentInstance()));
        basicSearchBackingBean.setSearchCriteriaEntered(true);
        basicSearchBackingBean.setAnyCheckboxSelected(true);
		return "BasicSearchCriteria";
    }

    public String actionAdvancedSearch(){
        SessionCleanUp.cleanUp();
		setBreadCrumbText("Search >> Advanced Search Criteria");
		return "AdvancedSearchCriteria";
    }
    
    public String actionMassUpdateSearch(){
        SessionCleanUp.cleanUp();
		setBreadCrumbText("Search >> Mass Update");
		return "MassUpdate";
    }
    
	 public String actionAddWord(){
        SessionCleanUp.cleanUp();
		setBreadCrumbText("Dictionary Manager >> Add Word");
		return ADD_WORD_TO_DICTIONARY;
    }
	 public String spsMappingCreate(){
        SessionCleanUp.cleanUp();
		setBreadCrumbText("Administration >> Blue Exchange >> SPS Mapping >> Create");
		return SPS_MAPPING_CREATE;
    }
	 public String spsMappingMaintain(){
        SessionCleanUp.cleanUp();
		setBreadCrumbText("Administration >> Blue Exchange >> SPS Mapping >> Locate");
		return SPS_MAPPING_MAINTAIN;
    }
	 public String serviceTypeMappingCreate(){
        SessionCleanUp.cleanUp();
		setBreadCrumbText("Blue Exchange >> Service Type Mapping >> Create");
		return SERVICE_TYPE_MAPPING_CREATE;
    }
	 public String serviceTypeMappingMaintain(){
        SessionCleanUp.cleanUp();
		setBreadCrumbText("Blue Exchange >> Service Type Mapping >> Maintain");
		return SERVICE_TYPE_MAPPING_MAINTAIN;
    }
	 public String customMessageTextCreate(){
        SessionCleanUp.cleanUp();
		setBreadCrumbText("Administration >> Blue Exchange >> Custom Message Text >> Create");
		return CUSTOM_MESSAGE_TEXT_CREATE;
    }
	 public String customMessageTextMaintain(){
        SessionCleanUp.cleanUp();
		setBreadCrumbText("Administration >> Blue Exchange >> Custom Message Text >> Locate");
		return CUSTOM_MESSAGE_TEXT_MAINTAIN;
    }
	 public String createTestSuite() {
	 	SessionCleanUp.cleanUp();
	 	setBreadCrumbText("Benefit Selection >> Test Suite >> Create");
	 	return CREATE_MENU_TESTSUITE_NAVIGATION;
	 }
	 public String maintainTestSuite(){
	 	SessionCleanUp.cleanUp();
	 	setBreadCrumbText("Benefit Selection >> Test Suite >> Locate");
	 	return MAINTAIN_MENU_TESTSUITE_NAVIGATION;
	 } 	       
	 public String createTestCase(){
	     SessionCleanUp.cleanUp();
	     setBreadCrumbText("Benefit Selection >> Test Case >> Create");
	     return CREATE_MENU_TESTCASE_NAVIGATION;
	 }
	 public String maintainTestCase(){
	      SessionCleanUp.cleanUp();
	      setBreadCrumbText("Benefit Selection >> Test Case >> 	Locate");
	     return MAINTAIN_MENU_TESTCASE_NAVIGATION;
	 }

	/**
	 * This method is added for Indicative Long Term Solution.
	 * This method returns String for Navigation
	 * 
	 * @return String CREATE_INDICATIVE_LAYOUT_NAVIGATION
	 */
	public String createIndicativeLayoutAction() {
		SessionCleanUp.cleanUp();
		setBreadCrumbText("Administration >> Indicative Layout >> Create");
		return CREATE_INDICATIVE_LAYOUT_NAVIGATION;
	}

	 public String navigateToRMA()throws IOException{
		ResourceBundle rmaURL = ResourceBundle.getBundle("rma",Locale.getDefault());
		String rmaLink=rmaURL.getString("url");
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String javaScriptText = "window.open('"+rmaLink+"',null,'toolbar=yes,location=no,status=yes,menubar=yes,scrollbars=yes');";
		AddResource addResource = AddResourceFactory.getInstance(facesContext);
	    addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, javaScriptText);
	 	return "";
	 	
	 }

	public void setValidationMessages(List validationMessages) {
		this.validationMessages = validationMessages;
	}

	public List getValidationMessages() {
		return validationMessages;
	}
}