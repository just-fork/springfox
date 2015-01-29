package com.mangofactory.documentation.swagger.readers.operation;

import com.mangofactory.documentation.spi.DocumentationType;
import com.mangofactory.documentation.spi.service.OperationBuilderPlugin;
import com.mangofactory.documentation.spi.service.contexts.OperationContext;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.mangofactory.documentation.swagger.common.SwaggerPluginSupport.*;

@Component
public class OperationPositionReader implements OperationBuilderPlugin {

  private static final Logger log = LoggerFactory.getLogger(OperationPositionReader.class);

  @Override
  public void apply(OperationContext context) {
    ApiOperation apiOperation = context.getHandlerMethod().getMethodAnnotation(ApiOperation.class);
    if (null != apiOperation && apiOperation.position() > 0) {
      context.operationBuilder().position(apiOperation.position());
      log.debug("Added operation at position: {}", apiOperation.position());
    }
  }

  @Override
  public boolean supports(DocumentationType delimiter) {
    return pluginDoesApply(delimiter);
  }
}
