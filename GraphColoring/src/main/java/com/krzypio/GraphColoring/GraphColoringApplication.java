package com.krzypio.GraphColoring;

import com.krzypio.GraphColoring.Graph.GraphAdjacencyList;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.util.mxCellRenderer;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class GraphColoringApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(GraphColoringApplication.class, args);
		System.out.println("Hello world");
		GraphAdjacencyList g1 = new GraphAdjacencyList();
		g1.createGraph();
		g1.sortAdjLists();
		System.out.println(g1);

		Graph<Long, DefaultEdge> gj = new SimpleGraph<>(DefaultEdge.class);
		Long v0 = Long.valueOf(0);
		Long v1 = Long.valueOf(1);

		gj.addVertex(v0);
		gj.addVertex(v1);
		gj.addEdge(v0, v1);
		gj.addEdge(v1, v0);

		System.out.println(gj);

		JGraphXAdapter<Long, DefaultEdge> graphAdapter = new JGraphXAdapter<Long, DefaultEdge>(gj);
		mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
		layout.execute(graphAdapter.getDefaultParent());

		BufferedImage image =
				mxCellRenderer.createBufferedImage(graphAdapter, null, 2, Color.WHITE, true, null);
		File imgFile = new File("graph.png");
		ImageIO.write(image, "PNG", imgFile);
	}//main()
}
