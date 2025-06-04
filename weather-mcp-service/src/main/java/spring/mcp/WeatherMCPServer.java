package spring.mcp;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import spring.mcp.tools.WeatherTool;

@SpringBootApplication
public class WeatherMCPServer {
    public static void main(String[] args) {
        SpringApplication.run(WeatherMCPServer.class, args);
    }

    /**
     * Register the {@code WeatherTool} with the MCP server.
     *
     * @return the tool callback provider
     */
    @Bean
    public ToolCallbackProvider tools(WeatherTool weatherTool) {
        return MethodToolCallbackProvider
                .builder()
                .toolObjects(weatherTool)
                .build();
    }
}
