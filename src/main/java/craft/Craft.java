package craft;

import java.util.List;

import com.google.gson.GsonBuilder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import helpers.LogHelper;

@Configuration
class MyWebMvcConfigurer implements WebMvcConfigurer {
	public MyWebMvcConfigurer() {
		log("ctor");
	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
  	log("extendMessageConverters");
    converters.clear();
    converters.add(new FormHttpMessageConverter());
    converters.add(new ResourceHttpMessageConverter());
    converters.add(new GsonHttpMessageConverter(new GsonBuilder().setPrettyPrinting().serializeNulls().create()));
	}

	private void log(Object... args) {
    new LogHelper(this).log(args);
	}
}

@SpringBootApplication
public class Craft {
  public static void main(String... args) {
    SpringApplication.run(Craft.class, args);
  }
}