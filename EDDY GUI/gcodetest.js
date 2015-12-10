var cy;
$(function(){ // on dom ready
  cy = cytoscape({
    container: document.getElementById('cy'),
    style: [{"selector":"core","style":{"selection-box-color":"#AAD8FF","selection-box-border-color":"#8BB0D0","selection-box-opacity":"0.5"}},{"selector":"node","style":{"width":"mapData(btwn, 0, 0.082871, 20, 60)","height":"mapData(btwn, 0, 0.082871, 20, 60)","content":"data(name)","font-size":"12px","text-valign":"center","text-halign":"center","background-color":"#ccc","border-color":"data(nodeEdgeColor)","border-width":4,"border-opacity":"data(ratio)","text-outline-color":"#ccc","text-outline-width":"2px","color":"#000","overlay-padding":"6px","z-index":"10"}},{"selector":"edge:selected","style":{"curve-style":"haystack","haystack-radius":"0.5","opacity":"1.0","line-color":"#FFFFFF","line-style":"solid","width":"mapData(weight, 0, 1, 1, 8)","overlay-padding":"3px"}},{"selector":"node:selected","style":{"border-width":"6px","border-color":"#AAD8FF","border-opacity":"0.5","background-color":"#77828C","text-outline-color":"#77828C"}},{"selector":"edge","style":{"curve-style":"haystack","haystack-radius":"0.5","opacity":"0.4","line-color":"data(edgeColor)","line-style":"data(edgeType)","width":"mapData(weight, 0, 1, 1, 8)","overlay-padding":"3px"}},{"selector":"node.unhighlighted","style":{"opacity":"0.2"}},{"selector":"edge.unhighlighted","style":{"opacity":"0.0"}},{"selector":".highlighted","style":{"z-index":"999999"}},{"selector":"node.highlighted","style":{"border-width":"6px","border-color":"#AAD8FF","border-opacity":"0.5","background-color":"#394855","text-outline-color":"#394855","shadow-blur":"12px","shadow-color":"#000","shadow-opacity":"0.8","shadow-offset-x":"0px","shadow-offset-y":"4px"}},
{"selector":"edge.filtered","style":{"opacity":"0"}},{"selector":"node.filtered_resistant","style":{"width":"mapData(btwn_resistant, 0, 0.273149, 20, 60)","height":"mapData(btwn_resistant, 0, 0.273149, 20, 60)","content":"data(name)","font-size":"10px","text-valign":"center","text-halign":"center","background-color":"#daa","border-color":"pink","border-width":0,"border-opacity":0,"text-outline-color":"#daa","text-outline-width":"0px","color":"#000","overlay-padding":"6px","z-index":"10","pie-size":"80%","pie-1-background-color":"#E8747C","pie-1-background-size":"mapData(exprone_resistant, 0, 1, 0, 100)","pie-2-background-color":"#BBBBBB","pie-2-background-size":"mapData(exprzero_resistant, 0, 1, 0, 100)","pie-3-background-color":"#74E883","pie-3-background-size":"mapData(exprnegone_resistant, 0, 1, 0, 100)"}},{"selector":"node.filtered_sensitive","style":{"width":"mapData(btwn_sensitive, 0, 0.273149, 20, 60)","height":"mapData(btwn_sensitive, 0, 0.273149, 20, 60)","content":"data(name)","font-size":"10px","text-valign":"center","text-halign":"center","background-color":"#aad","border-color":"pink","border-width":0,"border-opacity":0,"text-outline-color":"#aad","text-outline-width":"0px","color":"#000","overlay-padding":"6px","z-index":"10","pie-size":"80%","pie-1-background-color":"#E8747C","pie-1-background-size":"mapData(exprone_sensitive, 0, 1, 0, 100)","pie-2-background-color":"#BBBBBB","pie-2-background-size":"mapData(exprzero_sensitive, 0, 1, 0, 100)","pie-3-background-color":"#74E883","pie-3-background-size":"mapData(exprnegone_sensitive, 0, 1, 0, 100)"}},{"selector":"node.filtered_","style":{"width":"mapData(btwn_, 0, 0.082871, 20, 60)","height":"mapData(btwn_, 0, 0.082871, 20, 60)","content":"data(name)","font-size":"10px","text-valign":"center","text-halign":"center","background-color":"#ccc","border-color":"pink","border-width":0,"border-opacity":0,"text-outline-color":"#ccc","text-outline-width":"0px","color":"#000","overlay-padding":"6px","z-index":"10"}}],
    elements: [
    {"data":{ "id": "CCNA1","idInt":0, "name": "CCNA1","btwn":0.005555556,"btwn_resistant":0.000000,"btwn_sensitive":0.039352,"query":true,"gene":true,"nodeEdgeColor":"blue","ratio":0.666666,"exprone_resistant":0.576923,"exprzero_resistant":0.423077,"exprnegone_resistant":0.000000,"exprone_sensitive":0.409574,"exprzero_sensitive":0.590426,"exprnegone_sensitive":0.000000},"position":{},"group":"nodes","removed":false,"selected":false,"selectable":true,"locked":false,"grabbed":false,"grabbable":true,"classes":""}]