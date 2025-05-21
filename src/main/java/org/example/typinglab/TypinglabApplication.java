package org.example.typinglab;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.FieldPosition;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
public class TypinglabApplication {

	public static void main(String[] args) {
		SpringApplication.run(TypinglabApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper()
				.setSerializationInclusion(JsonInclude.Include.NON_NULL)
				.configure(SerializationFeature.INDENT_OUTPUT, true)
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
				.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true)
				.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.registerModule(new JavaTimeModule())
				.setDateFormat(new ISO8601DateFormat() {
					@Override
					public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
						String value = com.fasterxml.jackson.databind.util.ISO8601Utils.format(date, true, TimeZone.getDefault());
						toAppendTo.append(value);
						return toAppendTo;
					}
				});
	}
}