// directory of where all the images are
var cmThemeOfficeBase = 'jscookmenu/ThemeOffice/';

if(myThemeOfficeBase)
    cmThemeOfficeBase = myThemeOfficeBase;
    cmThemeOfficeBase='';   

var cmThemeOffice =
{
  	// main menu display attributes
  	//
  	// Note.  When the menu bar is horizontal,
  	// mainFolderLeft and mainFolderRight are
  	// put in <span></span>.  When the menu
  	// bar is vertical, they would be put in
  	// a separate TD cell.

  	// HTML code to the left of the folder item
  	mainFolderLeft: '&#160;',
  	// HTML code to the right of the folder item
  	mainFolderRight: '&#160;',
	// HTML code to the left of the regular item
	mainItemLeft: '&#160;',
	// HTML code to the right of the regular item
	mainItemRight: '&#160;',

	// sub menu display attributes

	// 0, HTML code to the left of the folder item
	//folderLeft: '<img alt="" src="' + cmThemeOfficeBase + 'spacer.gif">',
	folderLeft:'',
	// 1, HTML code to the right of the folder item
	folderRight: '<img  src="../../MenuResources/ThemeOffice/arrow.gif">',
	//folderRight:'',
	// 2, HTML code to the left of the regular item
	//itemLeft: '<img alt="" src="' + cmThemeOfficeBase + 'spacer.gif">',
	itemLeft:'',
	// 3, HTML code to the right of the regular item
	//itemRight: '<img alt="" src="' + cmThemeOfficeBase + 'blank.gif">',
	itemRight:'',
	// 4, cell spacing for main menu
	mainSpacing: 0,
	// 5, cell spacing for sub menus
	subSpacing: 0,
	// 6, auto dispear time for submenus in milli-seconds
	delay: 500
};

// for horizontal menu split
var cmThemeOfficeHSplit = [_cmNoAction, '<td class="ThemeOfficeMenuItemLeft"></td><td colspan="2"><div class="ThemeOfficeMenuSplit"></div></td>'];
var cmThemeOfficeMainHSplit = [_cmNoAction, '<td class="ThemeOfficeMainItemLeft"></td><td colspan="2"><div class="ThemeOfficeMenuSplit"></div></td>'];
var cmThemeOfficeMainVSplit = [_cmNoAction, '|'];
