/*
* bootstrap-table - v1.12.1 - 2018-03-12
* https://github.com/wenzhixin/bootstrap-table
* Copyright (c) 2018 zhixin wen
* Licensed MIT License
*/
!function(a){"use strict";var b=a.fn.bootstrapTable.utils.sprintf;a.extend(a.fn.bootstrapTable.defaults,{stickyHeader:!1});var c=3;try{c=parseInt(a.fn.dropdown.Constructor.VERSION,10)}catch(d){}var e=c>3?"d-none":"hidden",f=a.fn.bootstrapTable.Constructor,g=f.prototype.initHeader;f.prototype.initHeader=function(){function c(b){var c=b.data,g=c.find("thead").attr("id");if(c.length<1||a("#"+i).length<1)return a(window).off("resize."+i),a(window).off("scroll."+i),void c.closest(".fixed-table-container").find(".fixed-table-body").off("scroll."+i);var h="0";f.options.stickyHeaderOffsetY&&(h=f.options.stickyHeaderOffsetY.replace("px",""));var j=a(window).scrollTop(),n=a("#"+l).offset().top-h,o=a("#"+m).offset().top-h-a("#"+g).css("height").replace("px","");if(j>n&&o>=j){a.each(f.$stickyHeader.find("tr").eq(0).find("th"),function(b,c){a(c).css("min-width",a("#"+g+" tr").eq(0).find("th").eq(b).css("width"))}),a("#"+k).removeClass(e).addClass("fix-sticky fixed-table-container"),a("#"+k).css("top",h+"px");var p=a('<div style="position:absolute;width:100%;overflow-x:hidden;" />');a("#"+k).html(p.append(f.$stickyHeader)),d(b)}else a("#"+k).removeClass("fix-sticky").addClass(e)}function d(b){var c=b.data,d=c.find("thead").attr("id");a("#"+k).css("width",+c.closest(".fixed-table-body").css("width").replace("px","")+1),a("#"+k+" thead").parent().scrollLeft(Math.abs(a("#"+d).position().left))}var f=this;if(g.apply(this,Array.prototype.slice.apply(arguments)),this.options.stickyHeader){var h=this.$tableBody.find("table"),i=h.attr("id"),j=h.attr("id")+"-sticky-header",k=j+"-sticky-header-container",l=j+"_sticky_anchor_begin",m=j+"_sticky_anchor_end";h.before(b('<div id="%s" class="%s"></div>',k,e)),h.before(b('<div id="%s"></div>',l)),h.after(b('<div id="%s"></div>',m)),h.find("thead").attr("id",j),this.$stickyHeader=a(a("#"+j).clone(!0,!0)),this.$stickyHeader.removeAttr("id"),a(window).on("resize."+i,h,c),a(window).on("scroll."+i,h,c),h.closest(".fixed-table-container").find(".fixed-table-body").on("scroll."+i,h,d),this.$el.on("all.bs.table",function(){f.$stickyHeader=a(a("#"+j).clone(!0,!0)),f.$stickyHeader.removeAttr("id")})}}}(jQuery);