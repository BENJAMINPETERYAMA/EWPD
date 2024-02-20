/*
  --- menu items --- 
  note that this structure has changed its format since previous version.
  additional third parameter is added for item scope settings.
  Now this structure is compatible with Tigra Menu GOLD.
  Format description can be found in product documentation.
*/
var MENU_ITEMS = [
	['Search', null, null,
		['ET Mandate &nbsp; &nbsp;','../searchMandate/mandatedBenefitsBasicSearch.jsp',null,
		//['Search Criteria','ET_benefit_Search.html']
		//['Modify','ET_Mandate_Modify.html'],
		//['Modify','']
	/*	['Product Entity Structure'],
		['Notes'],
		['Component','componentSearch.html'],
		['Variables &nbsp; &nbsp;<img src="images/arrows.gif" border="0">',null,null,
			['Product Variable','productVariableSearch.html'],
			['Non Product Variable']
		],
		['Business Condition'],
		['Process Steps &nbsp; &nbsp;<img src="images/arrows.gif" border="0">',null,null,
			['Physical Process Step'],
			['Logical Process Step'],
			['Business Process Step']
		]
*/		
	],
	],
//	['Contract Management'], 
	['Maintenance', null, null,
		['ET Mandate&nbsp; &nbsp;',null,null,
		['Create','../mandatedbenefits/mandateGeneralInfo.jsp']
/*		['Product Arrangement Structure'],		
		['Product Entity Structure'],
		['Notes'],
		['Component'],
		['Variable'],
		['Business Conditions'],
		['Process Step']
*/		
],
],
/*	['ET Maintenace',null,null,
	 	['Create ET Mandate','ET_benefit_display.html'],
		['Search ET Mandate']
	],
	['Administration', null, null,
		['User'],
		['Reference Data']
	],
	*/
];


var PRINT_MENU_ITEMS = [
	["Print", null, null,
		["Current Page",null,null,],
		["Detailed",null,null,], 
	],
  ];

var PRINT_PROD_MENU_ITEMS = [
	["Print", null, null,
		["Current Page",null,null,],
		["Detailed",null,null,],
		["Hierarchy",null,null,], 
	],
  ];