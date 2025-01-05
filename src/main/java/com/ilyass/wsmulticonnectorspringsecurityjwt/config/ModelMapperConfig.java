package com.ilyass.wsmulticonnectorspringsecurityjwt.config;

import com.ilyass.wsmulticonnectorspringsecurityjwt.common.CommonTools;
import com.ilyass.wsmulticonnectorspringsecurityjwt.service.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.util.Date;

@Configuration
@AllArgsConstructor
public class ModelMapperConfig {
    private CommonTools tools;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().
                setMatchingStrategy(MatchingStrategies.LOOSE).
                setFieldMatchingEnabled(true).
                setSkipNullEnabled(true).
                setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE).
                setAmbiguityIgnored(true);
        Converter<Date, String> dateToStringConverter = new AbstractConverter<>() {
            @Override
            public String convert(Date date) {
                return tools.dateToString(date);
            }
        };
        Converter<String, Date> stringToDateConverter = new AbstractConverter<>() {
            @Override
            public Date convert(String s) {
                try {
                    return tools.stringToDate(s);
                } catch (ParseException e) {
                    throw new BusinessException(String.format("the date %s doesn't respect the format %s ", s, tools.getDateFormat()));
                }
            }
        };
        // String to Boolean Converter
        Converter<String, Boolean> stringToBooleanConverter = new AbstractConverter<>() {
            @Override
            protected Boolean convert(String s) {
                if (s == null) {
                    return null; // Handle null input gracefully
                }
                return Boolean.parseBoolean(s); // Convert String to boolean
            }
        };
        modelMapper.addConverter(dateToStringConverter);
        modelMapper.addConverter(stringToDateConverter);
        modelMapper.addConverter(stringToBooleanConverter);

        return modelMapper;
    }
}
