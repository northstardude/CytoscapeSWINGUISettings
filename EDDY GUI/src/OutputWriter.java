import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.Vector;

import eddy.BetweenessNode;
import eddy.EasyReader;
import eddy.EasyWriter;
import edu.uci.ics.jung.algorithms.importance.BetweennessCentrality;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;

public class OutputWriter {
	EasyReader networkFileReader;
	EasyReader outputFileReader;
	String[] ClassColorMatrix;
	File geneSetFile;
	Vector<EDDYNode> genes;
	
	public OutputWriter(String networkFileName, String outputFileName) {
		networkFileReader = new EasyReader(networkFileName);
		outputFileReader = new EasyReader(outputFileName);
		
		ClassColorMatrix = new String[3];
		ClassColorMatrix[0] = "red";
		ClassColorMatrix[1] = "blue";
		ClassColorMatrix[2] = "grey";

		genes = new Vector<>();
		
		String geneSetName = outputFileReader.readLine();
		String geneSetURL = outputFileReader.readLine();
		geneSetFile = new File(geneSetName); //Make directory to hold all the three files
		geneSetFile.mkdirs();
		
		try{
			PrintWriter htmlwriter = new PrintWriter(geneSetFile.getAbsolutePath() + "/index.html", "UTF-8");
			PrintWriter csswriter = new PrintWriter(geneSetFile.getAbsolutePath() + "/style.css", "UTF-8");
			PrintWriter writer = new PrintWriter(geneSetFile.getAbsolutePath() + "/gcode.js","UTF-8");
			
			String class1name = outputFileReader.readLine();
			String class2name = outputFileReader.readLine();
			outputFileReader.readLine(); //line after class info has some table headers we don't need
				// write very long intro to gcode.js
				writer.println(
						"var cy; $(function(){ cy = cytoscape({container: document.getElementById('cy'),style: [{\"selector\":\"core\",\"style\":{\"selection-box-color\":\"#AAD8FF\",\"selection-box-border-color\":\"#8BB0D0\",\"selection-box-opacity\":\"0.5\"}},{\"selector\":\"node\",\"style\":{\"width\":\"mapData(btwn, 0, 0.082871, 20, 60)\",\"height\":\"mapData(btwn, 0, 0.082871, 20, 60)\",\"content\":\"data(name)\",\"font-size\":\"12px\",\"text-valign\":\"center\",\"text-halign\":\"center\",\"background-color\":\"#ccc\",\"border-color\":\"data(nodeEdgeColor)\",\"border-width\":4,\"border-opacity\":\"data(ratio)\",\"text-outline-color\":\"#ccc\",\"text-outline-width\":\"2px\",\"color\":\"#000\",\"overlay-padding\":\"6px\",\"z-index\":\"10\"}},{\"selector\":\"edge:selected\",\"style\":{\"curve-style\":\"haystack\",\"haystack-radius\":\"0.5\",\"opacity\":\"1.0\",\"line-color\":\"#FFFFFF\",\"line-style\":\"solid\",\"width\":\"mapData(weight, 0, 1, 1, 8)\",\"overlay-padding\":\"3px\"}},{\"selector\":\"node:selected\",\"style\":{\"border-width\":\"6px\",\"border-color\":\"#AAD8FF\",\"border-opacity\":\"0.5\",\"background-color\":\"#77828C\",\"text-outline-color\":\"#77828C\"}},{\"selector\":\"edge\",\"style\":{\"curve-style\":\"haystack\",\"haystack-radius\":\"0.5\",\"opacity\":\"0.4\",\"line-color\":\"data(edgeColor)\",\"line-style\":\"data(edgeType)\",\"width\":\"mapData(weight, 0, 1, 1, 8)\",\"overlay-padding\":\"3px\"}},{\"selector\":\"node.unhighlighted\",\"style\":{\"opacity\":\"0.2\"}},{\"selector\":\"edge.unhighlighted\",\"style\":{\"opacity\":\"0.0\"}},{\"selector\":\".highlighted\",\"style\":{\"z-index\":\"999999\"}},{\"selector\":\"node.highlighted\",\"style\":{\"border-width\":\"6px\",\"border-color\":\"#AAD8FF\",\"border-opacity\":\"0.5\",\"background-color\":\"#394855\",\"text-outline-color\":\"#394855\",\"shadow-blur\":\"12px\",\"shadow-color\":\"#000\",\"shadow-opacity\":\"0.8\",\"shadow-offset-x\":\"0px\",\"shadow-offset-y\":\"4px\"}},{\"selector\":\"edge.filtered\",\"style\":{\"opacity\":\"0\"}},{\"selector\":\"node.filtered_"
								+ class1name + "\",\"style\":{\"width\":\"mapData(btwn_" + class1name
								+ ", 0, 0.273149, 20, 60)\",\"height\":\"mapData(btwn_" + class1name
								+ ", 0, 0.273149, 20, 60)\",\"content\":\"data(name)\",\"font-size\":\"10px\",\"text-valign\":\"center\",\"text-halign\":\"center\",\"background-color\":\"#daa\",\"border-color\":\"pink\",\"border-width\":0,\"border-opacity\":0,\"text-outline-color\":\"#daa\",\"text-outline-width\":\"0px\",\"color\":\"#000\",\"overlay-padding\":\"6px\",\"z-index\":\"10\",\"pie-size\":\"80%\",\"pie-1-background-color\":\"#E8747C\",\"pie-1-background-size\":\"mapData(exprone_"
								+ class1name
								+ ", 0, 1, 0, 100)\",\"pie-2-background-color\":\"#BBBBBB\",\"pie-2-background-size\":\"mapData(exprzero_"
								+ class1name
								+ ", 0, 1, 0, 100)\",\"pie-3-background-color\":\"#74E883\",\"pie-3-background-size\":\"mapData(exprnegone_"
								+ class1name + ", 0, 1, 0, 100)\"}},{\"selector\":\"node.filtered_" + class2name
								+ "\",\"style\":{\"width\":\"mapData(btwn_" + class1name
								+ ", 0, 0.273149, 20, 60)\",\"height\":\"mapData(btwn_" + class1name
								+ ", 0, 0.273149, 20, 60)\",\"content\":\"data(name)\",\"font-size\":\"10px\",\"text-valign\":\"center\",\"text-halign\":\"center\",\"background-color\":\"#aad\",\"border-color\":\"pink\",\"border-width\":0,\"border-opacity\":0,\"text-outline-color\":\"#aad\",\"text-outline-width\":\"0px\",\"color\":\"#000\",\"overlay-padding\":\"6px\",\"z-index\":\"10\",\"pie-size\":\"80%\",\"pie-1-background-color\":\"#E8747C\",\"pie-1-background-size\":\"mapData(exprone_"
								+ class2name
								+ ", 0, 1, 0, 100)\",\"pie-2-background-color\":\"#BBBBBB\",\"pie-2-background-size\":\"mapData(exprzero_"
								+ class2name
								+ ", 0, 1, 0, 100)\",\"pie-3-background-color\":\"#74E883\",\"pie-3-background-size\":\"mapData(exprnegone_"
								+ class2name
								+ ", 0, 1, 0, 100)\"}},{\"selector\":\"node.filtered_\",\"style\":{\"width\":\"mapData(btwn_, 0, 0.082871, 20, 60)\",\"height\":\"mapData(btwn_, 0, 0.082871, 20, 60)\",\"content\":\"data(name)\",\"font-size\":\"10px\",\"text-valign\":\"center\",\"text-halign\":\"center\",\"background-color\":\"#ccc\",\"border-color\":\"pink\",\"border-width\":0,\"border-opacity\":0,\"text-outline-color\":\"#ccc\",\"text-outline-width\":\"0px\",\"color\":\"#000\",\"overlay-padding\":\"6px\",\"z-index\":\"10\"}}],elements: [");

				String line = outputFileReader.readLine();
				String tempInfo[];
				while(line!=null&&!line.equals("")){
					tempInfo = line.split("\t");
					genes.addElement(new EDDYNode(tempInfo[0], Double.parseDouble(tempInfo[1]), Double.parseDouble(tempInfo[2]), Double.parseDouble(tempInfo[3]), Double.parseDouble(tempInfo[4]), Double.parseDouble(tempInfo[5]), Double.parseDouble(tempInfo[6]), Double.parseDouble(tempInfo[7])));
					line = outputFileReader.readLine();
				}
				//split network line into component part: gene 1, gene 2, class, and prior presence/absence
				String gene1 = networkFileReader.readWord();
				String gene2 = networkFileReader.readWord();
				String classType = networkFileReader.readWord();
				networkFileReader.readLine();
				
				//go through each gene to see if it is one of the two in the network
				for(EDDYNode node:genes){
					if (node.name.equals(gene1)||node.name.equals(gene2)){
						if(classType.equalsIgnoreCase(class1name)){ //if yes update its edge count with the appropriate info
							node.edgesc1++;
						}else if(classType.equalsIgnoreCase(class2name)){
							node.edgesc2++;
						}else if(classType.equalsIgnoreCase("both")){
							node.edgesc1++;
							node.edgesc2++;
						}
					}
				}
				
				while((gene1 = networkFileReader.readWord())!=null && !(gene1 = networkFileReader.readWord()).equals("")){
					gene1 = networkFileReader.readWord();
					gene2 = networkFileReader.readWord();
					classType = networkFileReader.readWord();
					networkFileReader.readLine();
					for(EDDYNode node:genes){
						if (node.name.equals(gene1)||node.name.equals(gene2)){
							if(classType.equalsIgnoreCase(class1name)){
								node.edgesc1++;
							}else if(classType.equalsIgnoreCase(class2name)){
								node.edgesc2++;
							}else if(classType.equalsIgnoreCase("both")){
								node.edgesc1++;
								node.edgesc2++;
							}
						}
					}
				}
				
				//once all edges have been put in for every gene, figure out which one is greater to use in ratio calcs. NOTE: Ratio is calculated in EDDY, but correct color is not known
				for(EDDYNode node:genes){
					if (node.edgesc1>node.edgesc2){
						node.nodeEdgeColor = ClassColorMatrix[0];
					}
					else if (node.edgesc1<node.edgesc2){
						node.nodeEdgeColor = ClassColorMatrix[1];
					}else{
						node.nodeEdgeColor = ClassColorMatrix[2];
					}
				}

				//write out html head
				htmlwriter
						.write("<!DOCTYPE html>  <html>    <head>      <meta charset=utf-8 />            <title>EDDY in Cola.js/Cytoscape.js</title>            <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\">      <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css\">      <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/bootstrap-slider/4.10.3/css/bootstrap-slider.min.css\">      <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css\">      <link href=\"http://cdnjs.cloudflare.com/ajax/libs/qtip2/2.2.0/jquery.qtip.min.css\" rel=\"stylesheet\" type=\"text/css\" />      <link href=\"style.css\" rel=\"stylesheet\" />            <script src=\"https://cdnjs.cloudflare.com/ajax/libs/fastclick/1.0.6/fastclick.min.js\"></script>      <script src=\"https://cdnjs.cloudflare.com/ajax/libs/lodash.js/3.10.0/lodash.min.js\"></script>      <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js\"></script>      <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\"></script>      <script src=\"https://cdnjs.cloudflare.com/ajax/libs/bootstrap-slider/4.10.3/bootstrap-slider.min.js\"></script>      <!--<script src=\"https://cytoscape.github.io/cytoscape.js/api/cytoscape.js-latest/cola.v3.min.js\"></script>-->      <script src=\"http://marvl.infotech.monash.edu/webcola/cola.v3.min.js\"></script>      <script src=\"http://cytoscape.github.io/cytoscape.js/api/cytoscape.js-latest/cytoscape.min.js\"></script>      <script src=\"http://cdnjs.cloudflare.com/ajax/libs/qtip2/2.2.0/jquery.qtip.min.js\"></script>      <script src=\"https://cdn.rawgit.com/cytoscape/cytoscape.js-qtip/2.2.5/cytoscape-qtip.js\"></script>      <script src=\"https://cdn.rawgit.com/cytoscape/cytoscape.js-cola/1.1.1/cytoscape-cola.js\"></script>    ​            <script src=\"gcode.js\"></script>    </head>    <body>      <div id=\"cy\"></div>            <div id=\"config\">        <div class=\"preamble\">  <span><a href="
								+ geneSetURL + ">" + geneSetName+ "<hr>");
				htmlwriter.write(
						"<div id=condition1 style=\"color:" + ClassColorMatrix[0] + "; background-color: white\">");
				htmlwriter.write("<p>" + class1name + "</p>");
				htmlwriter.write("</div><div id=condition2 style=\"color:" + ClassColorMatrix[1]
						+ "; background-color: white\">");
				htmlwriter.write("<p>" + class2name+ "</p></div>");
				htmlwriter.write(
						"<div style=\"color: grey; background-color: white\">  &#8212 Both <p>  </div>  <div style=\"color: black; background-color: white\">  -- Statistical dependency  </div>  <div style=\"color: black; background-color: white\">  &#8212 Known interaction <p>  </div>  <div style=\"color: #E8747C; background-color: white\">  &#8226 high  </div>  <div style=\"color: grey; background-color: white\">  &#8226 intermediate  </div>  <div style=\"color: #74E883; background-color: white\">  &#8226 low <p>  </div>  <hr>          <p>This is a network rendering of EDDY output that uses Cola.js for layout and Cytoscape.js for its graph model and visualisation.  Use the controls below to alter the layout.</p>          <p>            Data by <a href=\"http://biocomputing.tgen.org/software/EDDY/index.html\">EDDY program</a><br/>            Visualisation by <a href=\"http://js.cytoscape.org\">Cytoscape.js</a><br/>            Layout by <a href=\"http://marvl.infotech.monash.edu/webcola/\">Cola.js</a>          </p>        </div>              </div>    </body>  </html>");
				htmlwriter.close();

				csswriter.write(
						"html {    width: 100%;    height: 100%;  }  body {     font: 14px helvetica neue, helvetica, arial, sans-serif;    width: 100%;    height: 100%;  }  #cy {    position: absolute;    left: 0;    top: 0;    bottom: 0;    right: 17em;    background: #eee;  }  #config {    position: absolute;    right: 0;    top: 0;    bottom: 0;    width: 17em;    background: #333;    box-sizing: border-box;    padding: 1em;    color: #fff;  }  .param {    margin-bottom: 1em;  }  .preamble {    margin-bottom: 2em;  }  p {    margin: 0.5em 0;    font-size: 0.8em;  }  .param button {    width: 4.5em;    margin-right: 0.25em;  }  a,  a:hover {    color: #62daea;  }  .label {    display: inline-block;  }  .slider {    display: block;  }");
				csswriter.close();

				PrintWriter linksWriter = new PrintWriter("links.txt", "UTF-8");
				linksWriter.append(geneSetFile.getAbsolutePath() + "/index.html" + "\n");
				linksWriter.close();
				
				int temp = 1;
				//write out node info
				for(EDDYNode node:genes){
					if(temp==1){
						writer.println("{\"data\":{ \"id\": \"" + node.name
								+ "\",\"idInt\":0, \"name\":\"" + node.name + "\",\"btwn\":"
								+ "0.000" + ",\"btwn_"
								+ class1name + "\":" + "0.000"
								+ ",\"btwn_" + class2name + "\":"
								+ "0.000"
								+ ",\"query\":true,\"gene\":true,\"nodeEdgeColor\":\"" + node.nodeEdgeColor
								+ "\",\"ratio\":" + node.ratio + ",\"exprone_" + class1name + "\":" + node.expr1c1
								+ ",\"exprzero_" + class1name + "\":" + node.expr0c1 + ",\"exprnegone_"
								+ class1name + "\":" + node.exprneg1c1 + ",\"exprone_" + class2name + "\":"
								+ node.expr1c2 + ",\"exprzero_" + class2name + "\":" + node.expr0c2
								+ ",\"exprnegone_" + class2name + "\":" + node.exprneg1c2
								+ "},\"position\":{},\"group\":\"nodes\",\"removed\":false,\"selected\":false,\"selectable\":true,\"locked\":false,\"grabbed\":false,\"grabbable\":true,\"classes\":\"\"}");
					}else{
						writer.println(",{\"data\":{ \"id\": \"" + node.name
								+ "\",\"idInt\":0, \"name\":\"" + node.name + "\",\"btwn\":"
								+ "0.000" + ",\"btwn_"
								+ class1name + "\":" + "0.000"
								+ ",\"btwn_" + class2name + "\":"
								+ "0.000"
								+ ",\"query\":true,\"gene\":true,\"nodeEdgeColor\":\"" + node.nodeEdgeColor
								+ "\",\"ratio\":" + node.ratio + ",\"exprone_" + class1name + "\":" + node.expr1c1
								+ ",\"exprzero_" + class1name + "\":" + node.expr0c1 + ",\"exprnegone_"
								+ class1name + "\":" + node.exprneg1c1 + ",\"exprone_" + class2name + "\":"
								+ node.expr1c2 + ",\"exprzero_" + class2name + "\":" + node.expr0c2
								+ ",\"exprnegone_" + class2name + "\":" + node.exprneg1c2
								+ "},\"position\":{},\"group\":\"nodes\",\"removed\":false,\"selected\":false,\"selectable\":true,\"locked\":false,\"grabbed\":false,\"grabbable\":true,\"classes\":\"\"}");
					}
					temp++;
				}
				
				//write out edge info
				
				//restart reading of network file at line 1
				networkFileReader = new EasyReader(networkFileName);
				
				line = networkFileReader.readLine();
				tempInfo = line.split("\t");
				gene1 = tempInfo[0];
				gene2 = tempInfo[1];
				classType = tempInfo[2];
				String hasPrior = tempInfo[3];
				String edgeType;
				String edgeColor;
				
				//determine if edge is solid or dashed
				if(hasPrior.equalsIgnoreCase("none")){
					edgeType = "dashed";
				}else{
					edgeType = "solid";
				}
				
				//determine edge color
				if(classType.equalsIgnoreCase(class1name)){
					edgeColor = ClassColorMatrix[0];
				}else if(classType.equalsIgnoreCase(class2name)){
					edgeColor = ClassColorMatrix[1];
				}else{
					edgeColor = ClassColorMatrix[2];
				}
				
				writer.println(",{\"data\":{\"source\":\"" + gene1
									+ "\",\"target\":\"" + gene2
									+ "\",\"weight\":0.25,\"edgeColor\": \"" + edgeColor
									+ "\",\"edgeType\": \""+edgeType+"\" ,\"networkId\":1133,\"networkGroupId\":18,\"intn\":true,\"rIntnId\":4,\"id\":\""
									+ gene1 + gene2
									+ "\"},\"position\":{},\"group\":\"edges\",\"removed\":false,\"selected\":false,\"selectable\":true,\"locked\":false,\"grabbed\":false,\"grabbable\":true,\"classes\":\"\"}");
				//repeat for rest of the genes
				line = networkFileReader.readLine();
				while(line!=null && !line.equals(""))	{
					tempInfo = line.split("\t");
					gene1 = tempInfo[0];
					gene2 = tempInfo[1];
					classType = tempInfo[2];
					hasPrior = tempInfo[3];
					
					
					//determine if edge is solid or dashed
					if(hasPrior.equalsIgnoreCase("none")){
						edgeType = "dashed";
					}else{
						edgeType = "solid";
					}
					
					//determine edge color
					if(classType.equalsIgnoreCase(class1name)){
						edgeColor = ClassColorMatrix[0];
					}else if(classType.equalsIgnoreCase(class2name)){
						edgeColor = ClassColorMatrix[1];
					}else{
						edgeColor = ClassColorMatrix[2];
					}
					
					writer.println(",{\"data\":{\"source\":\"" + gene1
										+ "\",\"target\":\"" + gene2
										+ "\",\"weight\":0.25,\"edgeColor\": \"" + edgeColor
										+ "\",\"edgeType\": \""+edgeType+"\" ,\"networkId\":1133,\"networkGroupId\":18,\"intn\":true,\"rIntnId\":4,\"id\":\""
										+ gene1 + gene2
										+ "\"},\"position\":{},\"group\":\"edges\",\"removed\":false,\"selected\":false,\"selectable\":true,\"locked\":false,\"grabbed\":false,\"grabbable\":true,\"classes\":\"\"}");
					
					line = networkFileReader.readLine();
				}
				/*
				for (int i = 0; i < targetGeneSetVariable.size() - 1; i++) {
					for (int j = i + 1; j < targetGeneSetVariable.size(); j++) {
						priorIndex1 = targetGeneSetVariable.priorIndexOf(targetGeneSetVariable.get(i).name.toString());
						priorIndex2 = targetGeneSetVariable.priorIndexOf(targetGeneSetVariable.get(j).name.toString());
						int priorval = priorMap.map[priorIndex1][priorIndex2];
						int priorval2 = priorMap.map[priorIndex2][priorIndex1];
						int net0val = original.structureMap.map[i][j];
						if ((priorval == 0) && (priorval2 == 0)) {
							if ((connectionProbability[0][i][j] >= connProbThreshold)
									&& (connectionProbability[1][i][j] < connProbThreshold)) {
								writer.println(",{\"data\":{\"source\":\"" + targetGeneSetVariable.get(i).name
										+ "\",\"target\":\"" + targetGeneSetVariable.get(j).name
										+ "\",\"weight\":0.25,\"edgeColor\": \"" + ClassColorMatrix[0]
										+ "\",\"edgeType\": \"dashed\" ,\"networkId\":1133,\"networkGroupId\":18,\"intn\":true,\"rIntnId\":4,\"id\":\""
										+ targetGeneSetVariable.get(i).name + targetGeneSetVariable.get(j).name
										+ "\"},\"position\":{},\"group\":\"edges\",\"removed\":false,\"selected\":false,\"selectable\":true,\"locked\":false,\"grabbed\":false,\"grabbable\":true,\"classes\":\"\"}");
							} else if ((connectionProbability[0][i][j] < connProbThreshold)
									&& (connectionProbability[1][i][j] >= connProbThreshold)) {

								writer.println(",{\"data\":{\"source\":\"" + targetGeneSetVariable.get(i).name
										+ "\",\"target\":\"" + targetGeneSetVariable.get(j).name
										+ "\",\"weight\":0.25,\"edgeColor\": \"" + ClassColorMatrix[1]
										+ "\",\"edgeType\": \"dashed\" ,\"networkId\":1133,\"networkGroupId\":18,\"intn\":true,\"rIntnId\":4,\"id\":\""
										+ targetGeneSetVariable.get(i).name + targetGeneSetVariable.get(j).name
										+ "\"},\"position\":{},\"group\":\"edges\",\"removed\":false,\"selected\":false,\"selectable\":true,\"locked\":false,\"grabbed\":false,\"grabbable\":true,\"classes\":\"\"}");
							} else if ((connectionProbability[0][i][j] >= connProbThreshold)
									&& (connectionProbability[1][i][j] >= connProbThreshold)) {

								writer.println(",{\"data\":{\"source\":\"" + targetGeneSetVariable.get(i).name
										+ "\",\"target\":\"" + targetGeneSetVariable.get(j).name
										+ "\",\"weight\":0.25,\"edgeColor\": \"" + ClassColorMatrix[2]
										+ "\",\"edgeType\": \"dashed\" ,\"networkId\":1133,\"networkGroupId\":18,\"intn\":true,\"rIntnId\":4,\"id\":\""
										+ targetGeneSetVariable.get(i).name + targetGeneSetVariable.get(j).name
										+ "\"},\"position\":{},\"group\":\"edges\",\"removed\":false,\"selected\":false,\"selectable\":true,\"locked\":false,\"grabbed\":false,\"grabbable\":true,\"classes\":\"\"}");
							}
						} else {
							if (priorval == 1) {
								if ((connectionProbability[0][i][j] >= connProbThreshold * (1 - priorWeight))
										&& (connectionProbability[1][i][j] < connProbThreshold * (1 - priorWeight))) {

									if (priorval == 1) {

										writer.println(",{\"data\":{\"source\":\"" + targetGeneSetVariable.get(i).name
												+ "\",\"target\":\"" + targetGeneSetVariable.get(j).name
												+ "\",\"weight\":0.25,\"edgeColor\": \"" + ClassColorMatrix[0]
												+ "\",\"edgeType\": \"solid\" ,\"networkId\":1133,\"networkGroupId\":18,\"intn\":true,\"rIntnId\":4,\"id\":\""
												+ targetGeneSetVariable.get(i).name + targetGeneSetVariable.get(j).name
												+ "\"},\"position\":{},\"group\":\"edges\",\"removed\":false,\"selected\":false,\"selectable\":true,\"locked\":false,\"grabbed\":false,\"grabbable\":true,\"classes\":\"\"}");
									} else {

										writer.println(",{\"data\":{\"source\":\"" + targetGeneSetVariable.get(i).name
												+ "\",\"target\":\"" + targetGeneSetVariable.get(j).name
												+ "\",\"weight\":0.25,\"edgeColor\": \"" + ClassColorMatrix[0]
												+ "\",\"edgeType\": \"dashed\" ,\"networkId\":1133,\"networkGroupId\":18,\"intn\":true,\"rIntnId\":4,\"id\":\""
												+ targetGeneSetVariable.get(i).name + targetGeneSetVariable.get(j).name
												+ "\"},\"position\":{},\"group\":\"edges\",\"removed\":false,\"selected\":false,\"selectable\":true,\"locked\":false,\"grabbed\":false,\"grabbable\":true,\"classes\":\"\"}");
									}
									bw.write("\n");
								} else if ((connectionProbability[0][i][j] < connProbThreshold * (1 - priorWeight))
										&& (connectionProbability[1][i][j] >= connProbThreshold * (1 - priorWeight))) {

									if (priorval == 1) {

										writer.println(",{\"data\":{\"source\":\"" + targetGeneSetVariable.get(i).name
												+ "\",\"target\":\"" + targetGeneSetVariable.get(j).name
												+ "\",\"weight\":0.25,\"edgeColor\": \"" + ClassColorMatrix[1]
												+ "\",\"edgeType\": \"solid\" ,\"networkId\":1133,\"networkGroupId\":18,\"intn\":true,\"rIntnId\":4,\"id\":\""
												+ targetGeneSetVariable.get(i).name + targetGeneSetVariable.get(j).name
												+ "\"},\"position\":{},\"group\":\"edges\",\"removed\":false,\"selected\":false,\"selectable\":true,\"locked\":false,\"grabbed\":false,\"grabbable\":true,\"classes\":\"\"}");
									} else {

										writer.println(",{\"data\":{\"source\":\"" + targetGeneSetVariable.get(i).name
												+ "\",\"target\":\"" + targetGeneSetVariable.get(j).name
												+ "\",\"weight\":0.25,\"edgeColor\": \"" + ClassColorMatrix[1]
												+ "\",\"edgeType\": \"dashed\" ,\"networkId\":1133,\"networkGroupId\":18,\"intn\":true,\"rIntnId\":4,\"id\":\""
												+ targetGeneSetVariable.get(i).name + targetGeneSetVariable.get(j).name
												+ "\"},\"position\":{},\"group\":\"edges\",\"removed\":false,\"selected\":false,\"selectable\":true,\"locked\":false,\"grabbed\":false,\"grabbable\":true,\"classes\":\"\"}");
									}
									bw.write("\n");
								} else if ((connectionProbability[0][i][j] >= connProbThreshold * (1 - priorWeight))
										&& (connectionProbability[1][i][j] >= connProbThreshold * (1 - priorWeight))) {

									if (priorval == 1) {

										writer.println(",{\"data\":{\"source\":\"" + targetGeneSetVariable.get(i).name
												+ "\",\"target\":\"" + targetGeneSetVariable.get(j).name
												+ "\",\"weight\":0.25,\"edgeColor\": \"" + ClassColorMatrix[2]
												+ "\",\"edgeType\": \"solid\" ,\"networkId\":1133,\"networkGroupId\":18,\"intn\":true,\"rIntnId\":4,\"id\":\""
												+ targetGeneSetVariable.get(i).name + targetGeneSetVariable.get(j).name
												+ "\"},\"position\":{},\"group\":\"edges\",\"removed\":false,\"selected\":false,\"selectable\":true,\"locked\":false,\"grabbed\":false,\"grabbable\":true,\"classes\":\"\"}");
									} else {

										writer.println(",{\"data\":{\"source\":\"" + targetGeneSetVariable.get(i).name
												+ "\",\"target\":\"" + targetGeneSetVariable.get(j).name
												+ "\",\"weight\":0.25,\"edgeColor\": \"" + ClassColorMatrix[2]
												+ "\",\"edgeType\": \"dashed\" ,\"networkId\":1133,\"networkGroupId\":18,\"intn\":true,\"rIntnId\":4,\"id\":\""
												+ targetGeneSetVariable.get(i).name + targetGeneSetVariable.get(j).name
												+ "\"},\"position\":{},\"group\":\"edges\",\"removed\":false,\"selected\":false,\"selectable\":true,\"locked\":false,\"grabbed\":false,\"grabbable\":true,\"classes\":\"\"}");
									}

								} else if (priorval == 1) {

								}
							}
							if (priorDirectionality && (priorval2 == 1)) {
								if ((connectionProbability[0][i][j] >= connProbThreshold * (1 - priorWeight))
										&& (connectionProbability[1][i][j] < connProbThreshold * (1 - priorWeight))) {

									if (priorval2 == 1) {

										writer.println(",{\"data\":{\"source\":\"" + targetGeneSetVariable.get(j).name
												+ "\",\"target\":\"" + targetGeneSetVariable.get(i).name
												+ "\",\"weight\":0.25,\"edgeColor\": \"" + ClassColorMatrix[0]
												+ "\",\"edgeType\": \"solid\" ,\"networkId\":1133,\"networkGroupId\":18,\"intn\":true,\"rIntnId\":4,\"id\":\""
												+ targetGeneSetVariable.get(j).name + targetGeneSetVariable.get(i).name
												+ "\"},\"position\":{},\"group\":\"edges\",\"removed\":false,\"selected\":false,\"selectable\":true,\"locked\":false,\"grabbed\":false,\"grabbable\":true,\"classes\":\"\"}");
									} else {

										writer.println(",{\"data\":{\"source\":\"" + targetGeneSetVariable.get(j).name
												+ "\",\"target\":\"" + targetGeneSetVariable.get(i).name
												+ "\",\"weight\":0.25,\"edgeColor\": \"" + ClassColorMatrix[0]
												+ "\",\"edgeType\": \"dashed\" ,\"networkId\":1133,\"networkGroupId\":18,\"intn\":true,\"rIntnId\":4,\"id\":\""
												+ targetGeneSetVariable.get(j).name + targetGeneSetVariable.get(i).name
												+ "\"},\"position\":{},\"group\":\"edges\",\"removed\":false,\"selected\":false,\"selectable\":true,\"locked\":false,\"grabbed\":false,\"grabbable\":true,\"classes\":\"\"}");
									}

								} else if ((connectionProbability[0][i][j] < connProbThreshold * (1 - priorWeight))
										&& (connectionProbability[1][i][j] >= connProbThreshold * (1 - priorWeight))) {

									if (priorval2 == 1) {

										writer.println(",{\"data\":{\"source\":\"" + targetGeneSetVariable.get(j).name
												+ "\",\"target\":\"" + targetGeneSetVariable.get(i).name
												+ "\",\"weight\":0.25,\"edgeColor\": \"" + ClassColorMatrix[1]
												+ "\",\"edgeType\": \"solid\" ,\"networkId\":1133,\"networkGroupId\":18,\"intn\":true,\"rIntnId\":4,\"id\":\""
												+ targetGeneSetVariable.get(j).name + targetGeneSetVariable.get(i).name
												+ "\"},\"position\":{},\"group\":\"edges\",\"removed\":false,\"selected\":false,\"selectable\":true,\"locked\":false,\"grabbed\":false,\"grabbable\":true,\"classes\":\"\"}");
									} else {

										writer.println(",{\"data\":{\"source\":\"" + targetGeneSetVariable.get(j).name
												+ "\",\"target\":\"" + targetGeneSetVariable.get(i).name
												+ "\",\"weight\":0.25,\"edgeColor\": \"" + ClassColorMatrix[1]
												+ "\",\"edgeType\": \"dashed\" ,\"networkId\":1133,\"networkGroupId\":18,\"intn\":true,\"rIntnId\":4,\"id\":\""
												+ targetGeneSetVariable.get(j).name + targetGeneSetVariable.get(i).name
												+ "\"},\"position\":{},\"group\":\"edges\",\"removed\":false,\"selected\":false,\"selectable\":true,\"locked\":false,\"grabbed\":false,\"grabbable\":true,\"classes\":\"\"}");
									}
									bw.write("\n");
								} else if ((connectionProbability[0][i][j] >= connProbThreshold * (1 - priorWeight))
										&& (connectionProbability[1][i][j] >= connProbThreshold * (1 - priorWeight))) {

									if (priorval2 == 1) {

										writer.println(",{\"data\":{\"source\":\"" + targetGeneSetVariable.get(j).name
												+ "\",\"target\":\"" + targetGeneSetVariable.get(i).name
												+ "\",\"weight\":0.25,\"edgeColor\": \"" + ClassColorMatrix[2]
												+ "\",\"edgeType\": \"solid\" ,\"networkId\":1133,\"networkGroupId\":18,\"intn\":true,\"rIntnId\":4,\"id\":\""
												+ targetGeneSetVariable.get(j).name + targetGeneSetVariable.get(i).name
												+ "\"},\"position\":{},\"group\":\"edges\",\"removed\":false,\"selected\":false,\"selectable\":true,\"locked\":false,\"grabbed\":false,\"grabbable\":true,\"classes\":\"\"}");
									} else {

										writer.println(",{\"data\":{\"source\":\"" + targetGeneSetVariable.get(j).name
												+ "\",\"target\":\"" + targetGeneSetVariable.get(i).name
												+ "\",\"weight\":0.25,\"edgeColor\": \"" + ClassColorMatrix[2]
												+ "\",\"edgeType\": \"dashed\" ,\"networkId\":1133,\"networkGroupId\":18,\"intn\":true,\"rIntnId\":4,\"id\":\""
												+ targetGeneSetVariable.get(j).name + targetGeneSetVariable.get(i).name
												+ "\"},\"position\":{},\"group\":\"edges\",\"removed\":false,\"selected\":false,\"selectable\":true,\"locked\":false,\"grabbed\":false,\"grabbable\":true,\"classes\":\"\"}");
									}
									bw.write("\n");
								} else if (priorval2 == 1) {

								}
							}
						}
					}
				}*/
				// write rest of gcode
				writer.println("]});");
				writer.println("var params = { name: 'cola', nodeSpacing: 10, edgeLengthVal: "
						+ "35, animate: true, randomize: false, maxSimulationTime: "
						+ "1500, splitFlag: false, toggle: true }; var layout = makeLayout(); "
						+ "var running = false; cy.on('layoutstart', function(){ running = true; }).on('layoutstop', function(){ running = false; }); layout.run(); var $config = $('#config'); "
						+ "var $btnParam = $('<div class=\"param\"></div>'); $config.append( $btnParam ); var sliders = [ { label: 'Edge length', param: 'edgeLengthVal', min: 1, max: 200 }, { label: 'Node spacing', param: 'nodeSpacing', min: 1, max: 50 } ]; "
						+ "var buttons = [ { label: '<i class=\"fa fa-random\"></i>', layoutOpts: { randomize: true, flow: null }, param: 'Randomize network' }, { label: '<i>CDN</i>', layoutOpts: {splitparam:true}, param: 'Condition-specific dependency network' }, "
						+ "{ label: '<i>DDN</i>', layoutOpts: {splitparam:false}, param: 'Differential dependency network' } ]; sliders.forEach( makeSlider ); buttons.forEach( makeButton ); function makeLayout( opts ){ params.randomize = false; params.edgeLength = function(e){ return params.edgeLengthVal / e.data('weight'); }; for( var i in opts ){ params[i] = opts[i]; } "
						+ "return cy.makeLayout( params ); } function makeSlider( opts ){ var $input = $('<input></input>'); var $param = $('<div class=\"param\"></div>'); $param.append('<span class=\"label label-default\">'+ opts.label +'</span>'); $param.append( $input ); $config.append( $param ); var p = $input.slider({ min: opts.min, max: opts.max, value: params[ opts.param ] }).on('slide', _.throttle( function(){ params[ opts.param ]"
						+ " = p.getValue(); layout.stop(); layout = makeLayout(); layout.run(); }, 16 ) ).data('slider'); } function makeButton( opts ){ var elnodes = cy.$('node[idInt=0]'); var eledges_"
						+ class1name + " = cy.$('edge[edgeColor=\"red\"],edge[edgeColor=\"grey\"]'); var eledges_"
						+ class2name
						+ " = cy.$('edge[edgeColor=\"blue\"],edge[edgeColor=\"grey\"]'); var $button = $('<button class=\"btn btn-default\" title=\"' + opts.param + '\">'+ opts.label +'</button>'); $btnParam.append( $button ); $button.on('click', function(){ layout.stop(); if (opts.layoutOpts.splitparam) { params.splitFlag=true; params.toggle=!params.toggle; } else { if (!opts.layoutOpts.randomize)"
						+ " { params.splitFlag=false; } } if (params.splitFlag) { if "
						+ "(params.toggle) { elnodes.removeClass('filtered_" + class1name + "').addClass('filtered_"
						+ class2name + "');");
				writer.println("eledges_" + class1name
						+ ".removeClass('highlighted').addClass('unhighlighted'); eledges_" + class2name
						+ ".removeClass('unhighlighted').addClass('highlighted'); } else { elnodes.removeClass('filtered_"
						+ class2name + "').addClass('filtered_" + class1name + "'); eledges_" + class2name
						+ ".removeClass('highlighted').addClass('unhighlighted'); eledges_" + class1name
						+ ".removeClass('unhighlighted').addClass('highlighted'); } } else { elnodes.removeClass('filtered_"
						+ class1name + "').addClass('core'); elnodes.removeClass('filtered_" + class2name
						+ "').addClass('core'); eledges_" + class2name
						+ ".removeClass('unhighlighted').addClass('highlighted'); eledges_" + class1name
						+ ".removeClass('unhighlighted').addClass('highlighted'); } if( opts.fn ){ opts.fn(); } layout = makeLayout( opts.layoutOpts ); layout.run(); }); } cy.edges().forEach(function(n){ var g1 = n.data('source'); var g2 = n.data('target'); n.qtip({ content: [ { name: 'Gene 1 '+ g1 + ' Gene 2 ' + g2 } ].map(function( link ){ return link.name; }).join(''), position: { my: 'top center', at: 'bottom center' }, style: { classes: 'qtip-bootstrap', tip: { width: 16, height: 4 } } }); }); cy.nodes().forEach(function(n){ var g = n.data('name'); n.qtip({ content: [ { \n name: 'GeneCard<br></br>', \n url: 'http://www.genecards.org/cgi-bin/carddisp.pl?gene=' + g }, { \nname: '"
						+ class1name + " mutation data<br></br>', \nurl: 'http://www.cbioportal.org' }, { \nname: '" + class2name
						+ " mutation data', \nurl: 'http://www.cbioportal.org' } ].map(function( link ){ return '<a target=\"_blank\" href=\"' + link.url + '\">' + link.name + '</a>'; }).join(''), position: { my: 'top center', at: 'bottom center' }, style: { classes: 'qtip-bootstrap', tip: { width: 16, height: 8 } } }); }); }); // on dom ready \n$(function() { FastClick.attach( document.body ); });");
				
				writer.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}