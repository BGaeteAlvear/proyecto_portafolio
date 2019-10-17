/*
* bootstrap-table - v1.12.1 - 2018-03-12
* https://github.com/wenzhixin/bootstrap-table
* Copyright (c) 2018 zhixin wen
* Licensed MIT License
*/
!function(a){"use strict";function b(b,c){var d=a("<option />");c?a(c).each(function(a,c){d.clone().text(c.idxNum+" "+c.name).val(c.idxNum).appendTo(b)}):console.log("Please setup options first!!")}function c(c,d){var e=[],f=c,g='<button type="button" class="btn btn-primary btn-sm editable-submit"><i class="glyphicon glyphicon-ok"></i></button>',h='<button type="button" class="btn btn-default btn-sm editable-cancel"><i class="glyphicon glyphicon-remove"></i></button>',i=function(){return e=[],d.find("td").find('input[type="text"]').each(function(b,c){e.push(a(c).eq(0).val())}),d.find("select").each(function(b,c){e.push(a("#"+c.id+" option:selected").val())}),a("#table").bootstrapTable("updateRow",{index:f.$data.thId,row:{noOld:e[0],area:d.find("select").eq(0).children(":selected").text(),town:d.find("select").eq(1).children(":selected").text(),address:e[1]}}),a("#tooling").remove(),f.editing=!0,!1},j=function(){return a("#table").bootstrapTable("updateRow",{index:f.$data.thId,row:{}}),a("#tooling").remove(),f.editing=!0,!1};if(f.editing){f.editing=!1,f.columns.forEach(function(c){if(c.editable)switch(c.editable){case"input":var f=a('<div class="editable-input col-md-12 col-sm-12 col-xs-12" style="position: relative;"/>');e.push(d.find("td").eq(c.fieldIndex).text()),f.append(a('<input type="text" class="form-control input-sm"/>')),f.append(a('<span class="clear"><i class="fa fa-times-circle-o" aria-hidden="true"></i></span>')),d.find("td").eq(c.fieldIndex).text("").append(f);break;case"select":var g=a('<select id="'+c.field+'">'),h=a.selectArray[c.field];d.find("td").eq(c.fieldIndex).text("").append(g),b(a("#"+c.field),h);break;case"textarea":break;default:console.log(c.fieldIndex+" "+c.editable)}},c);for(var k=0,l=e.length;l>k;k++)d.find('input[type="text"]').eq(k).val(e[k]);d.find("td").last().append('<div id="tooling" class="editable-buttons"/>'),a(".clear").on("click",function(){a(this).parent().find("input").val("")}),a(g).on("click",i).appendTo("#tooling"),a(h).on("click",j).appendTo("#tooling")}}a.extend(a.fn.bootstrapTable.defaults,{clickEdit:!1});var d=a.fn.bootstrapTable.Constructor,e=d.prototype.initTable,f=d.prototype.initBody;d.prototype.initTable=function(){this.$data={},e.apply(this,Array.prototype.slice.apply(arguments)),!this.options.clickEdit},d.prototype.initBody=function(){var a=this;if(f.apply(this,Array.prototype.slice.apply(arguments)),this.options.clickEdit){var b=this.$tableBody.find("table");a.editing=!0,b.on("click-row.bs.table",function(a,b,d,e){"no"!==e&&(this.$data.thId=d.data().index,this.$data.itemid=d.data().uniqueid,this.$data.divi=parseInt(b.area),this.$data.town=parseInt(b.town),c(this,d))}.bind(this))}}}(jQuery);