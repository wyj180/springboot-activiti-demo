package com.neimeng.workflow.diagram;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.image.ProcessDiagramGenerator;

import java.awt.*;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

/**
 * Activiti生成流程图片接口
 */
public interface ICustomProcessDiagramGenerator extends ProcessDiagramGenerator {

    InputStream generateDiagram(BpmnModel bpmnModel,
                                String imageType,
                                List<String> highLightedActivities,
                                List<String> highLightedFlows,
                                String activityFontName,
                                String labelFontName,
                                String annotationFontName,
                                ClassLoader customClassLoader,
                                double scaleFactor,
                                Color[] colors,
                                Set<String> currIds);
}