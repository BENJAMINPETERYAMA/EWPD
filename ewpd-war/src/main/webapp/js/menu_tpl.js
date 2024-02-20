/*
  --- menu level scope settins structure --- 
  note that this structure has changed its format since previous version.
  Now this structure has the same layout as Tigra Menu GOLD.
  Format description can be found in product documentation.
*/
var MENU_POS = [{
	// item sizes
	'height': 20,
	'width': 210,
	// menu block offset from the origin:
	//	for root level origin is upper left corner of the page
	//	for other levels origin is upper left corner of parent item
	'block_top': 76,
	'block_left': 0,
	// offsets between items of the same level
	'top': 0,
	'left': 211,
	// time in milliseconds before menu is hidden after cursor has gone out
	// of any items
	'hide_delay': 200,
	'expd_delay': 200,
	'css' : {
		'outer' : ['m0l0oout', 'm0l0oover'],
		'inner' : ['m0l0iout', 'm0l0iover']
	}
},{
	'height': 20,
	'width': 210,
	'block_top': 21,
	'block_left': 0,
	'top': 19,
	'left': 0,
	'css' : {
		'outer' : ['m0l1oout', 'm0l1oover'],
		'inner' : ['m0l1iout', 'm0l1iover']
	}
},{
	'block_top': 0,
	'block_left': 210
}
]


var PRINT_MENU_POS = [{
	// item sizes
	'height': 20,
	'width': 103,
	// menu block offset from the origin:
	//	for root level origin is upper left corner of the page
	//	for other levels origin is upper left corner of parent item
	'block_top': 76,
	'block_left': 875,
	// offsets between items of the same level
	'top': 0,
	'left': 211,
	// time in milliseconds before menu is hidden after cursor has gone out
	// of any items
	'hide_delay': 200,
	'expd_delay': 200,
	'css' : {
		'outer' : ['p0l0oout', 'p0l0oover'],
		'inner' : ['p0l0iout', 'p0l0iover']
	}
},{
	'height': 20,
	'width': 103,
	'block_top': 21,
	'block_left': 0,
	'top': 19,
	'left': 0,
	'css' : {
		'outer' : ['p0l1oout', 'p0l1oover'],
		'inner' : ['p0l1iout', 'p0l1iover']
	}
},{
	'block_top': 0,
	'block_left': 210
}
]

var PRINT_MENU_POS_SEARCH = [{
	// item sizes
	'height': 20,
	'width': 127,
	// menu block offset from the origin:
	//	for root level origin is upper left corner of the page
	//	for other levels origin is upper left corner of parent item
	'block_top': 98,
	'block_left': 877,
	// offsets between items of the same level
	'top': 0,
	'left': 211,
	// time in milliseconds before menu is hidden after cursor has gone out
	// of any items
	'hide_delay': 200,
	'expd_delay': 200,
	'css' : {
		'outer' : ['p0l0oout', 'p0l0oover'],
		'inner' : ['p0l0iout', 'p0l0iover']
	}
},{
	'height': 20,
	'width': 127,
	'block_top': 21,
	'block_left': 0,
	'top': 19,
	'left': 0,
	'css' : {
		'outer' : ['p0l1oout', 'p0l1oover'],
		'inner' : ['p0l1iout', 'p0l1iover']
	}
},{
	'block_top': 0,
	'block_left': 210
}
]
var PRINT_MENU_POS_NOTESSEARCH = [{
	// item sizes
	'height': 20,
	'width': 100,
	// menu block offset from the origin:
	//	for root level origin is upper left corner of the page
	//	for other levels origin is upper left corner of parent item
	'block_top': 76,
	'block_left': 903,
	// offsets between items of the same level
	'top': 0,
	'left': 211,
	// time in milliseconds before menu is hidden after cursor has gone out
	// of any items
	'hide_delay': 200,
	'expd_delay': 200,
	'css' : {
		'outer' : ['p0l0oout1', 'p0l0oover1'],
		'inner' : ['p0l0iout1', 'p0l0iover1']
	}
},{
	'height': 20,
	'width': 100,
	'block_top': 21,
	'block_left': 0,
	'top': 19,
	'left': 0,
	'css' : {
		'outer' : ['p0l1oout1', 'p0l1oover1'],
		'inner' : ['p0l1iout1', 'p0l1iover1']
	}
},{
	'block_top': 0,
	'block_left': 210
}
]


