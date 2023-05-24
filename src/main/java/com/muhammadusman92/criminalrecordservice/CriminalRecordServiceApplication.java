package com.muhammadusman92.criminalrecordservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class CriminalRecordServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CriminalRecordServiceApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper(){
		ModelMapper modelMapper = new ModelMapper();
//		modelMapper.typeMap(Disease.class, DiseaseDto.class).addMappings(mapper->{
//			mapper.skip(DiseaseDto::setPrescriptionSet);
//		});
		return modelMapper;
	}

}
