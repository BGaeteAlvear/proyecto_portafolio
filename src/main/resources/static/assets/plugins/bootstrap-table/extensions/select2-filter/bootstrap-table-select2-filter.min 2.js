/*
* bootstrap-table - v1.12.1 - 2018-03-12
* https://github.com/wenzhixin/bootstrap-table
* Copyright (c) 2018 zhixin wen
* Licensed MIT License
*/
!function(a){"use strict";function b(a){var b=a.$header;return a.options.height&&(b=a.$tableHeader),b}function c(c){if(!a.isEmptyObject(c.filterColumnsPartial)){var d=b(c);a.each(c.columns,function(a,b){var e=c.filterColumnsPartial[b.field];if(b.filter)if(b.filter.setFilterValue){var f=d.find("[data-field="+b.field+"] .filter");b.filter.setFilterValue(f,b.field,e)}else{var g=d.find("[data-filter-field="+b.field+"]");switch(b.filter.type){case"input":g.val(e);case"select":g.val(e).trigger("change")}}})}}function d(b,c){var d,e,f=!1,g=0;a.each(b.columns,function(h,i){if(d="hidden",e=null,i.visible){if(i.filter){var j=i.filter["class"]?" "+i.filter["class"]:"";if(e=a('<div style="margin: 0px 2px 2px 2px;" class="filter'+j+'">'),i.searchable&&(f=!0,d="visible"),i.filter.template)e.append(i.filter.template(b,i,d));else{var k=a(b.options.filterTemplate[i.filter.type.toLowerCase()](b,i,d));switch(i.filter.type){case"input":var l=!0;k.off("compositionstart").on("compositionstart",function(){l=!1}),k.off("compositionend").on("compositionend",function(c){l=!0;var d=a(this);clearTimeout(g),g=setTimeout(function(){b.onColumnSearch(c,i.field,d.val())},b.options.searchTimeOut)}),k.off("keyup").on("keyup",function(c){if(l){var d=a(this);clearTimeout(g),g=setTimeout(function(){b.onColumnSearch(c,i.field,d.val())},b.options.searchTimeOut)}}),k.off("mouseup").on("mouseup",function(c){var d=a(this),e=d.val();""!==e&&setTimeout(function(){var a=d.val();""===a&&(clearTimeout(g),g=setTimeout(function(){b.onColumnSearch(c,i.field,a)},b.options.searchTimeOut))},1)});break;case"select":k.on("select2:select",function(c){b.onColumnSearch(c,i.field,a(this).val())}),k.on("select2:unselecting",function(c){var d=a(this);c.preventDefault(),d.val(null).trigger("change"),b.searchText=void 0,b.onColumnSearch(c,i.field,d.val())})}e.append(k)}}else e=a('<div class="no-filter"></div>');a.each(c.children().children(),function(b,c){return c=a(c),c.data("field")===i.field?(c.find(".fht-cell").append(e),!1):void 0})}}),f||c.find(".filter").hide()}function e(c){var d=b(c);a.each(c.columns,function(a,b){if(b.filter&&"select"===b.filter.type){var e=d.find('select[data-filter-field="'+b.field+'"]');if(e.length>0&&!e.data().select2){var f={placeholder:"",allowClear:!0,data:b.filter.data,dropdownParent:c.$el.closest(".bootstrap-table")};e.select2(f)}}})}a.extend(a.fn.bootstrapTable.defaults,{filter:!1,filterValues:{},filterTemplate:{input:function(a,b,c){return'<input type="text" class="form-control" data-filter-field="'+b.field+'" style="width: 100%; visibility:'+c+'">'},select:function(a,b,c){return'<select data-filter-field="'+b.field+'" style="width: 100%; visibility:'+c+'"></select>'}},onColumnSearch:function(){return!1}}),a.extend(a.fn.bootstrapTable.COLUMN_DEFAULTS,{filter:void 0}),a.extend(a.fn.bootstrapTable.Constructor.EVENTS,{"column-search.bs.table":"onColumnSearch"});var f=a.fn.bootstrapTable.Constructor,g=f.prototype.init,h=f.prototype.initHeader,i=f.prototype.initSearch;f.prototype.init=function(){if(this.options.filter){var b=this;b.options.filterTemplate&&(b.options.filterTemplate=a.extend({},a.fn.bootstrapTable.defaults.filterTemplate,b.options.filterTemplate)),a.isEmptyObject(b.options.filterValues)||(b.filterColumnsPartial=b.options.filterValues,b.options.filterValues={}),this.$el.on("reset-view.bs.table",function(){b.options.height&&(b.$tableHeader.find("select").length>0||b.$tableHeader.find("input").length>0||d(b,b.$tableHeader))}).on("post-header.bs.table",function(){var a=0;e(b),clearTimeout(a),a=setTimeout(function(){c(b)},b.options.searchTimeOut-1e3)}).on("column-switch.bs.table",function(){c(b)})}g.apply(this,Array.prototype.slice.apply(arguments))},f.prototype.initHeader=function(){h.apply(this,Array.prototype.slice.apply(arguments)),this.options.filter&&d(this,this.$header)},f.prototype.initSearch=function(){var b=this,c=b.filterColumnsPartial;"client"===b.options.sidePagination&&(this.data=a.grep(this.data,function(d,e){for(var f in c){var g=b.columns[b.fieldsColumnsIndex[f]],h=c[f].toLowerCase(),i=d[f];if(i=a.fn.bootstrapTable.utils.calculateObjectValue(b.header,b.header.formatters[a.inArray(f,b.header.fields)],[i,d,e],i),g.filterStrictSearch){if(-1===a.inArray(f,b.header.fields)||"string"!=typeof i&&"number"!=typeof i||i.toString().toLowerCase()!==h.toString().toLowerCase())return!1}else if(-1===a.inArray(f,b.header.fields)||"string"!=typeof i&&"number"!=typeof i||-1===(i+"").toLowerCase().indexOf(h))return!1}return!0})),i.apply(this,Array.prototype.slice.apply(arguments))},f.prototype.onColumnSearch=function(b,c,d){a.isEmptyObject(this.filterColumnsPartial)&&(this.filterColumnsPartial={}),d?this.filterColumnsPartial[c]=d:delete this.filterColumnsPartial[c],this.options.pageNumber=1,this.onSearch(b),this.trigger("column-search",c,d)},f.prototype.setSelect2Data=function(c,d){var e=this,f=b(e),g=f.find('select[data-filter-field="'+c+'"]');g.empty(),g.select2({data:d,placeholder:"",allowClear:!0,dropdownParent:e.$el.closest(".bootstrap-table")}),a.each(this.columns,function(a,b){return b.field===c?(b.filter.data=d,!1):void 0})},f.prototype.setFilterValues=function(a){this.filterColumnsPartial=a},a.fn.bootstrapTable.methods.push("setSelect2Data"),a.fn.bootstrapTable.methods.push("setFilterValues")}(jQuery);